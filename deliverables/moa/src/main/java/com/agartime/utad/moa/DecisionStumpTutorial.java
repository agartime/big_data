package com.agartime.utad.moa;

/**
 * Created by agartime on 12/04/14.
 */

import moa.classifiers.AbstractClassifier;
import moa.classifiers.bayes.NaiveBayes;
import moa.classifiers.core.AttributeSplitSuggestion;
import moa.classifiers.core.attributeclassobservers.AttributeClassObserver;
import moa.classifiers.core.attributeclassobservers.GaussianNumericAttributeClassObserver;
import moa.classifiers.core.attributeclassobservers.NominalAttributeClassObserver;
import moa.classifiers.core.splitcriteria.SplitCriterion;
import moa.core.AutoExpandVector;
import moa.core.DoubleVector;
import moa.evaluation.LearningCurve;
import moa.evaluation.WindowClassificationPerformanceEvaluator;
import moa.options.ClassOption;
import moa.options.FlagOption;
import moa.options.IntOption;
import moa.streams.ArffFileStream;
import moa.streams.generators.RandomRBFGenerator;
import moa.tasks.EvaluatePrequential;
import weka.core.Instance;

public class DecisionStumpTutorial extends AbstractClassifier {
    private static final long serialVersionUID = 1L;

    public IntOption gracePeriodOption = new IntOption("gracePeriod", 'g',"The number of instances to observe between model changes.",1000, 0, Integer.MAX_VALUE);
    public FlagOption binarySplitsOption = new FlagOption("binarySplits", 'b',"Only allow binary splits.");
    public ClassOption splitCriterionOption = new ClassOption("splitCriterion",'c', "Split criterion to use.", SplitCriterion.class,"InfoGainSplitCriterion");

    protected AttributeSplitSuggestion bestSplit;
    protected DoubleVector observedClassDistribution;
    protected AutoExpandVector<AttributeClassObserver> attributeObservers;
    protected double weightSeenAtLastSplit;

    public boolean isRandomizable() {
        return false;
    }

    @Override
    public void resetLearningImpl() {
        this.bestSplit = null;
        this.observedClassDistribution = new DoubleVector();
        this.attributeObservers = new AutoExpandVector<AttributeClassObserver>();
        this.weightSeenAtLastSplit = 0.0;
    }

    @Override
    public void trainOnInstanceImpl(Instance inst) {
        this.observedClassDistribution.addToValue((int) inst.classValue(), inst.weight());
        for (int i = 0; i < inst.numAttributes() - 1; i++) {
            int instAttIndex = modelAttIndexToInstanceAttIndex(i, inst);
            AttributeClassObserver obs = this.attributeObservers.get(i);
            if (obs == null) {
                obs = inst.attribute(instAttIndex).isNominal() ? newNominalClassObserver() : newNumericClassObserver();
                this.attributeObservers.set(i, obs);
            }
            obs.observeAttributeClass(inst.value(instAttIndex), (int) inst.classValue(), inst.weight());
        }
        if (this.trainingWeightSeenByModel - this.weightSeenAtLastSplit >=this.gracePeriodOption.getValue()) {
            this.bestSplit = findBestSplit((SplitCriterion) getPreparedClassOption(this.splitCriterionOption));
            this.weightSeenAtLastSplit = this.trainingWeightSeenByModel;
        }
    }

    public double[] getVotesForInstance(Instance inst) {
        if (this.bestSplit != null) {
            int branch = this.bestSplit.splitTest.branchForInstance(inst);

            if (branch >= 0) {
                return this.bestSplit.resultingClassDistributionFromSplit(branch);
            }
        }
        return this.observedClassDistribution.getArrayCopy();
    }

    protected AttributeClassObserver newNominalClassObserver() {
        return new NominalAttributeClassObserver();
    }

    protected AttributeClassObserver newNumericClassObserver() {
        return new GaussianNumericAttributeClassObserver();
    }


    protected AttributeSplitSuggestion findBestSplit(SplitCriterion criterion) {
        AttributeSplitSuggestion bestFound = null;
        double bestMerit = Double.NEGATIVE_INFINITY;
        double[] preSplitDist = this.observedClassDistribution.getArrayCopy();
        for (int i = 0; i < this.attributeObservers.size(); i++) {
            AttributeClassObserver obs = this.attributeObservers.get(i);
            if (obs != null) {
                AttributeSplitSuggestion suggestion = obs.getBestEvaluatedSplitSuggestion(criterion, preSplitDist, i, this.binarySplitsOption.isSet());
                if (suggestion.merit > bestMerit) {
                    bestMerit = suggestion.merit;
                    bestFound = suggestion;
                }
            }
        }
        return bestFound;
     }

    public void getModelDescription(StringBuilder out, int indent) {
    }

    protected moa.core.Measurement[] getModelMeasurementsImpl() {
        return null;
    }

    public static void main(String []args) {

        DecisionStumpTutorial classifier = new DecisionStumpTutorial();
        //Creamos un stream random utilizando un RandomRBFGenerator
        RandomRBFGenerator generator = new RandomRBFGenerator();
        generator.prepareForUse();

        //Creamos la clase evaluador
        WindowClassificationPerformanceEvaluator windowClassEvaluator = new WindowClassificationPerformanceEvaluator();
        windowClassEvaluator.widthOption.setValue(1000);
        windowClassEvaluator.prepareForUse();

        int maxInstances = 1000000;
        int timeLimit = -1;
        int sampleFrequencyOption = 1000;

        EvaluatePrequential ep = new EvaluatePrequential();
        ep.instanceLimitOption.setValue(maxInstances);

        //Le asignamos nuestro clasificador
        ep.learnerOption.setCurrentObject(classifier);
        ep.streamOption.setCurrentObject(generator);
        ep.sampleFrequencyOption.setValue(sampleFrequencyOption);
        ep.timeLimitOption.setValue(timeLimit);

        ep.evaluatorOption.setCurrentObject(windowClassEvaluator);
        ep.prepareForUse();

        LearningCurve le = (LearningCurve) ep.doTask();

        System.out.println(le);

    }
}

