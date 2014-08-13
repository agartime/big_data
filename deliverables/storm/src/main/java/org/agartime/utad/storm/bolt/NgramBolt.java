package org.agartime.utad.storm.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import org.agartime.utad.storm.util.Constants;

import java.util.LinkedList;
import java.util.Map;

/**
 * Created by agartime on 22/04/14.
 */
public class NgramBolt extends BaseRichBolt {
    private OutputCollector collector;
    LinkedList<String> queue;
    int queueSize=0;
    int n;

    public NgramBolt(int n) {
        this.n=n;
    }

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector=outputCollector;
        this.queue=new LinkedList<String>();
    }

    // NgramBolt uses a Queue for storing N words before emitting.
    // insertInQueueAndReturnNgram will return the ngram, or null if the queue
    // haven't reached n yet.
    private String insertInQueueAndReturnNgram(String word) {
        queue.addFirst(word);
        queueSize++;
        if (queueSize == n) {
            StringBuilder stringBuilder=new StringBuilder();
            for (int i=0; i<n; i++) {
                stringBuilder.append(queue.removeLast());
                stringBuilder.append(" ");
                queueSize--;
            }
            return stringBuilder.toString().substring(0,stringBuilder.length()-1);
        }
        return null;
    }

    @Override
    public void execute(Tuple tuple) {
        String key = tuple.getStringByField(Constants.WORD_KEY);
        String ngram = insertInQueueAndReturnNgram(key);
        if (ngram != null) {
            collector.emit(new Values(ngram));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields(Constants.WORD_KEY));
    }

}
