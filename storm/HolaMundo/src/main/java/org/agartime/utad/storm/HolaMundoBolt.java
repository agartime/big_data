package org.agartime.utad.storm;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class HolaMundoBolt extends BaseRichBolt {
	private static int myCount;
	public void execute(Tuple input) {
		String test = input.getStringByField("sentence");
		if ("Hola Mundo".equals(test)) {
			myCount++;
			System.out.println("Encontrado Hola Mundo!. Contador: "+Integer.toString(myCount));
		}
	}
	@Override
	public void prepare(Map stormConf, TopologyContext context,
			OutputCollector collector) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

}
