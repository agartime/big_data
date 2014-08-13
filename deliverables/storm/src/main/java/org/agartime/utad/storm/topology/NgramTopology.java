package org.agartime.utad.storm.topology;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;
import org.agartime.utad.storm.bolt.CounterBolt;
import org.agartime.utad.storm.bolt.NgramBolt;
import org.agartime.utad.storm.bolt.SplitBolt;
import org.agartime.utad.storm.destiny.RedisDestinySink;
import org.agartime.utad.storm.spout.ReaderSpout;

import java.io.FileNotFoundException;

/**
 * Created by agartime on 23/04/14.
 */
public class NgramTopology {
    public static void main(String [] args) throws FileNotFoundException {
        int n=1;
        String filePath="src/resources/elquijote.txt";
        boolean usingCluster=false;
        boolean usingFlajoletMartin=false;

        if (args.length != 4) {
            System.out.println("Aborting: You should you two parameters: ngramSize=int filePath flajoletMartin=yes|no usingCluster=yes|no");
            System.out.println("Example: 3 yes no");
            return;
        } else {
            n =Integer.parseInt(args[0]);
            filePath=args[1];
            if (args[2].equals("yes")) {
                usingFlajoletMartin=true;
            }
            if (args[3].equals("yes")) {
                usingCluster=true;
            }
        }

        TopologyBuilder builder = new TopologyBuilder();

        //If we are not using Redis, we could use a hashTable commenting the next line.
        //InMemoryDestinySink destiny = new InMemoryDestinySink();
        RedisDestinySink destiny = new RedisDestinySink();
        destiny.resetSink();


        //
        // NgramTopology counts with just one splitbolt and one ngrambolt to get the words ordered.
        // This way, we are someway joining also the words appearing in subsequent lines.
        //
        builder.setSpout("filespout", new ReaderSpout(filePath), 1);
        builder.setBolt("splitbolt", new SplitBolt(), 1).shuffleGrouping("filespout");
        builder.setBolt("ngrambolt", new NgramBolt(n),1).shuffleGrouping("splitbolt");
        builder.setBolt("counterbolt", new CounterBolt(destiny,usingFlajoletMartin),4).shuffleGrouping("ngrambolt");

        Config conf = new Config();
        conf.setDebug(true);

        if (usingCluster) {
            conf.setNumWorkers(3);
            try {
                StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("test", conf, builder.createTopology());
            Utils.sleep(60000);
            cluster.killTopology("test");
            cluster.shutdown();
        }
    }
}
