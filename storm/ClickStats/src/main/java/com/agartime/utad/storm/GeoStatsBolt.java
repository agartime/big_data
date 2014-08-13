package com.agartime.utad.storm;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class GeoStatsBolt extends BaseRichBolt {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TopologyBuilder builder = new TopologyBuilder();

	@Override
	public void prepare(Map stormConf, TopologyContext context,OutputCollector collector) {
		builder.setBolt("geoStats", new GeoStatsBolt(), 10).fieldsGrouping("geographyBolt", new backtype.storm.tuple.Fields(Fields.COUNTRY));
	
	}

	@Override
	public void execute(Tuple input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

}
