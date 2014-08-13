package org.agartime.utad.storm.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;
import org.agartime.utad.storm.util.Constants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by agartime on 13/04/14.
 */
public class ReaderSpout extends BaseRichSpout {

    private SpoutOutputCollector collector;
    private String filePath;

    public ReaderSpout(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields(Constants.SENTENCE_KEY));
    }

    @Override
    public void open(java.util.Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = spoutOutputCollector;

    }

    @Override
    public void nextTuple() {
        try {
            long startTime=System.currentTimeMillis();
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line=reader.readLine()) != null) {
                collector.emit(new Values(line));
            }
            System.out.println("ReaderSpout finished: "+(System.currentTimeMillis()-startTime)+" ms.");
            System.out.println("Sleeping ReaderSpout for 5 minutes.");
            Utils.sleep(300000);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
