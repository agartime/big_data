package com.agartime.utad.storm;

import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import redis.clients.jedis.Jedis;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Values;

public class ClickSpout extends BaseRichSpout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Jedis jedis;
	private String host;
	private int port;
	private SpoutOutputCollector collector;

	private void connectToRedis() {
		jedis = new Jedis(host, port);
	}

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		host = conf.get(Conf.REDIS_HOST_KEY).toString();
		port = Integer.valueOf(conf.get(Conf.REDIS_PORT_KEY).toString());
		this.collector = collector;
		connectToRedis();
	}

	@Override
	public void nextTuple() {
		String content=jedis.rpop("count");
		if (content == null || "nil".equals(content)) {
			try{ Thread.sleep(300); }
			catch (InterruptedException e){}
		}else {
			JSONObject obj = (JSONObject) JSONValue.parse(content);
			String ip = obj.get(Fields.IP).toString();
			String url = obj.get(Fields.URL).toString();
			String clientKey = obj.get(Fields.CLIENT_KEY).toString();
			collector.emit(new Values(ip, url, clientKey));
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new backtype.storm.tuple.Fields(Fields.IP,Fields.URL,Fields.CLIENT_KEY));
	}


}
