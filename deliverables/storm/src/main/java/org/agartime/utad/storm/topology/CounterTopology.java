package org.agartime.utad.storm.topology;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;
import org.agartime.utad.storm.bolt.CounterBolt;
import org.agartime.utad.storm.bolt.SplitBolt;
import org.agartime.utad.storm.destiny.InMemoryDestinySink;
import org.agartime.utad.storm.destiny.RedisDestinySink;
import org.agartime.utad.storm.spout.ReaderSpout;
import org.agartime.utad.storm.util.Constants;

import java.io.FileNotFoundException;

/**
 * Created by agartime on 13/04/14.
 */
public class CounterTopology {
    public static void main(String [] args) throws FileNotFoundException {
        String filePath="src/resources/metamorphosis.txt";
        boolean usingCluster=false;
        boolean usingFlajoletMartin=false;

        if (args.length != 3) {
            System.out.println("Aborting: You should use three parameters: inputpath flajoletMarin=yes|no usingCluster=yes|no");
            System.out.println("Example: src/resources/metamorphosis.txt yes no");
            return;
        } else {
            filePath=args[0];
            if (args[1].equals("yes")) {
                usingFlajoletMartin=true;
            }
            if (args[2].equals("yes")) {
                usingCluster=true;
            }
        }

        TopologyBuilder builder = new TopologyBuilder();
        //FileOriginSource origin = new FileOriginSource("src/resources/elquijote.txt");
        //InMemoryDestinySink destiny = new InMemoryDestinySink();
        RedisDestinySink destiny = new RedisDestinySink();
        destiny.resetSink();

        builder.setSpout("filespout", new ReaderSpout(filePath), 1);
        builder.setBolt("splitbolt", new SplitBolt(), 4).shuffleGrouping("filespout");
        //FieldsGrouping between SplitBolt and CounterBolt to send similar words to the same bolt.
        builder.setBolt("counterbolt", new CounterBolt(destiny,usingFlajoletMartin),8).fieldsGrouping("splitbolt", new Fields(Constants.WORD_KEY));

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
            System.out.println("CLUSTER START: "+System.currentTimeMillis());
            cluster.submitTopology("test", conf, builder.createTopology());
            Utils.sleep(600000);
            cluster.killTopology("test");
            cluster.shutdown();
        }
    }
}
