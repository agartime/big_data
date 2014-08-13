package org.agartime.utad.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

public class HolaMundoTopology {

	public static void main(String [] args) {
		TopologyBuilder builder = new TopologyBuilder();
		
		builder.setSpout("randomHolaMundo", new HolaMundoSpout(), 10);
		
		builder.setBolt("HolaMundoBolt", new HolaMundoBolt(), 2).shuffleGrouping("randomHolaMundo");
		
		Config conf = new Config();
		conf.setDebug(true);
		if (args != null && args.length > 0) {
			conf.setNumWorkers(3);
			try {
				StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("test", conf, builder.createTopology());
			Utils.sleep(10000);
			cluster.killTopology("test");
			cluster.shutdown();
		}
	}
	
}
