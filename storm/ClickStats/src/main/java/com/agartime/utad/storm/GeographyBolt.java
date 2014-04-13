package com.agartime.utad.storm;

import java.util.Map;

import org.json.simple.JSONObject;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class GeographyBolt extends BaseRichBolt {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IPResolver resolver;
	private OutputCollector collector;
	
	public GeographyBolt(IPResolver resolver) {
		this.resolver = resolver;
	}
	
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	} 
	
	@Override
	public void execute(Tuple tuple) {
		String ip = tuple.getStringByField(Fields.IP);
		JSONObject json = resolver.resolveIP(ip);
		String city = (String) json.get(Fields.CITY);
		String country = (String) json.get(Fields.COUNTRY_NAME);
		collector.emit(new Values(country, city));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new backtype.storm.tuple.Fields(Fields.COUNTRY, Fields.CITY));		
	}
}
