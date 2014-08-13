package com.agartime.utad.storm;

import java.util.Map;

import redis.clients.jedis.Jedis;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class RepeatVisitBolt extends BaseRichBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Jedis jedis;
	private OutputCollector collector;
	private static String host;
	private static int port;
	
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		host = stormConf.get(Conf.REDIS_HOST_KEY).toString();
		port = Integer.valueOf(stormConf.get(Conf.REDIS_PORT_KEY).toString());
		connectToRedis();
		}
	
	private void connectToRedis() {
		jedis = new Jedis(host, port);
		jedis.connect();
	}

	@Override
	public void execute(Tuple input) {
		String clientKey = input.getStringByField(Fields.CLIENT_KEY);
		String url = input.getStringByField(Fields.CLIENT_KEY);
		String key = url + ":" + clientKey;
		String value = jedis.get(key);
		if (value == null) { // es un cliente nuevo
			jedis.set(key, "visited");
			collector.emit(new Values(clientKey, url, Boolean.TRUE.toString()));
		} else {
			collector.emit(new Values(clientKey, url, Boolean.FALSE.toString()));
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

}
