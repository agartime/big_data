package org.agartime.utad.storm.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import org.agartime.utad.storm.algorithm.FlajoletMartin;
import org.agartime.utad.storm.destiny.DestinySink;
import org.agartime.utad.storm.util.Constants;

import java.util.BitSet;
import java.util.Map;

/**
 * Created by agartime on 13/04/14.
 */
public class CounterBolt extends BaseRichBolt {
    private OutputCollector collector;
    private DestinySink destiny;
    boolean flajoletMartin;
    long startMillis;

    public CounterBolt(DestinySink destiny, boolean flajoletMartin) {
        this.destiny=destiny; this.flajoletMartin=flajoletMartin;
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector=outputCollector;
        this.destiny.prepareDestiny();
        if (flajoletMartin) {
            this.initializeFlajoletMartinArray();
        }
        this.startMillis=System.currentTimeMillis();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void execute(Tuple tuple) {
        String key = tuple.getStringByField(Constants.WORD_KEY);
        if (flajoletMartin) {
            this.parseFlajoletMartin(key); //Flajolet Martin Counter
        }
        Integer prevValue = (Integer)destiny.get(key);
        if (prevValue == null) {
            destiny.put(key,1);
        } else {
            destiny.put(key,prevValue+1);
        }
        System.out.println("COUNTERBOLT ELAPSED TIME: "+(System.currentTimeMillis()-startMillis)+" ms.");
    }

    @SuppressWarnings("unchecked")
    private void initializeFlajoletMartinArray() {
        for (int i=0; i<FlajoletMartin.N;i++) {
            destiny.put(Constants.FJ_KEY+i,0);
        }
    }

    @SuppressWarnings("unchecked")
    private void parseFlajoletMartin(String word) {
        int zeroes = FlajoletMartin.getTrailingZeroes(word);
        int index = FlajoletMartin.N-zeroes-1;
        if (index > 0 && index < FlajoletMartin.N)
            destiny.put(Constants.FJ_KEY+index,1);
        System.out.println("FLAJOLET MARTIN STIMATION: "+getFlajoletMartinStimation());
    }

    private long getFlajoletMartinStimation() {
        return FlajoletMartin.getNumberOfUniqueElementsFromArray(getFlajoletMartinStatusArray());
    }

    @SuppressWarnings("unchecked")
    private boolean[] getFlajoletMartinStatusArray() {
        boolean [] fjStatus = new boolean[FlajoletMartin.N];
        for (int i=0;i<FlajoletMartin.N;i++) {
            int n = (Integer)destiny.get(Constants.FJ_KEY+i);
            fjStatus[i]=(n != 0);
        }
        return fjStatus;
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    public static void main(String [] args) {
        char[] str = Integer.toBinaryString("PRUEBA".hashCode()).toCharArray();
        System.out.println(Integer.toBinaryString("PRUEBA".hashCode()));
        for (int i=str.length-1; i>0;i--) {
            if (str[i] == '0') {
                System.out.println(str.length-i-1);
            }
        }
        System.out.println(str);
    }
}
