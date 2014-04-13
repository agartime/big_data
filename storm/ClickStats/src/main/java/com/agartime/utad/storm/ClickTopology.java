package com.agartime.utad.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

public class ClickTopology  {
	private TopologyBuilder builder;
	private LocalCluster cluster;
	private Config conf;

	
	public ClickTopology() {
		conf = new Config();
		builder = new TopologyBuilder();
		builder.setSpout("clickSpout", new ClickSpout(), 10);

		//Primera capa de bolts
		builder.setBolt("repeatsBolt", new RepeatVisitBolt(), 10).shuffleGrouping("clickSpout");
		builder.setBolt("geographyBolt",new GeographyBolt(new HttpIPResolver()), 10).shuffleGrouping("clickSpout");

		//segunda capa de bolts: conmutativa
		builder.setBolt("totalStats", new VisitorStatsBolt(), 1).globalGrouping("repeatsBolt");
		builder.setBolt("geoStats", new GeoStatsBolt(), 10).fieldsGrouping("geographyBolt", new backtype.storm.tuple.Fields(Fields.COUNTRY));
		conf.put(Conf.REDIS_PORT_KEY, Conf.DEFAULT_JEDIS_PORT);
	} 

	public TopologyBuilder getBuilder() {
		return builder;
	}

	public LocalCluster getLocalCluster() {
		return cluster;	
	}

	public Config getConf() {
		return conf;	
	}

	public void runLocal(int runTime) {
		conf.setDebug(true);
		conf.put(Conf.REDIS_HOST_KEY, "localhost");
		cluster = new LocalCluster();
		cluster.submitTopology("test", conf, builder.createTopology());
		if (runTime > 0) {
			Utils.sleep(runTime);
			shutDownLocal();
		}
	}
	
	public void shutDownLocal() {
		if (cluster != null) {
			cluster.killTopology("test");
			cluster.shutdown();
		}
	} 
	
	public void runCluster(String name, String redisHost)
			throws AlreadyAliveException, InvalidTopologyException {
		conf.setNumWorkers(20);
		conf.put(Conf.REDIS_HOST_KEY, redisHost);
		StormSubmitter.submitTopology(name, conf, builder.createTopology());
	}
	
	public static void main(String[] args) throws Exception {
		ClickTopology topology = new ClickTopology();
		if (args != null && args.length > 1) {
			topology.runCluster(args[0], args[1]);
		} else {
			if (args != null && args.length == 1)
				System.out.println("Ejecutandose en modo local.");
			topology.runLocal(10000);
		}
	}
}
