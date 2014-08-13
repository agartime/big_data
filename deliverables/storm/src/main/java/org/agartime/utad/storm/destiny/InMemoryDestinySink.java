package org.agartime.utad.storm.destiny;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by agartime on 13/04/14.
 */
public class InMemoryDestinySink implements DestinySink<String,Integer>, Serializable {
    private HashMap<String,Integer> hash;

    @Override
    public void put(String key, Integer value) {
        hash.put(key,value);
    }

    @Override
    public Integer get(String key) {
        return hash.get(key);
    }

    @Override
    public void prepareDestiny() {
        hash=new HashMap<String, Integer>();
    }

    @Override
    public void resetSink() {
        hash=new HashMap<String, Integer>();
    }

}
