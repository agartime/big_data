package com.agartime.utad.moa;

import moa.classifiers.Classifier;
import moa.classifiers.trees.HoeffdingTree;
import moa.streams.generators.RandomRBFGenerator;
import weka.core.Instance;

/**
 * Created by agartime on 12/04/14.
 */
public class TrainAndEvaluateUsingHoeffdingTree {
    public static void main(String[] args) {
        int numInstances=10000;
        Classifier learner = new HoeffdingTree();
        RandomRBFGenerator stream = new RandomRBFGenerator();
        stream.prepareForUse();

        learner.setModelContext(stream.getHeader());
        learner.prepareForUse();
        int numberSamplesCorrect = 0;
        int numberSamples = 0;

        //Learning Phase
        while(stream.hasMoreInstances() && numberSamples < numInstances) {
            Instance trainInst = stream.nextInstance();
            if(learner.correctlyClassifies(trainInst)) {
                numberSamplesCorrect++;
            }
            numberSamples++;
            learner.trainOnInstance(trainInst);
        }
        double accuracy = 100.0 * (double) numberSamplesCorrect / (double)numberSamples;
        System.out.println(numberSamples + " instances processed with "+accuracy+"% accuracy");


        //Evaluation Phase with new Stream
        System.out.println("Generating new Random Stream: ");
        stream = new RandomRBFGenerator();

        stream.prepareForUse();
        numberSamplesCorrect=0;
        numberSamples = 0;
        numInstances = 100000;
        while (stream.hasMoreInstances() && numberSamples<numInstances) {
            Instance inst = stream.nextInstance();
            numberSamples++;
            if (learner.correctlyClassifies(inst)) {
                numberSamplesCorrect++;
            }
            learner.getVotesForInstance(inst);
        }
        System.out.println("Processed "+numberSamples+". Correctly classified "+numberSamplesCorrect);


    }

}
