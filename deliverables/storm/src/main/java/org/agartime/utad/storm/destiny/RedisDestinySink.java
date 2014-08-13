package org.agartime.utad.storm.destiny;

import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by agartime on 13/04/14.
 */
public class RedisDestinySink implements DestinySink<String,Integer>, Serializable {
    private Jedis jedis;
    private static final String REDIS_HOSTNAME="localhost";
    private static final int REDIS_PORT=6379;


    private void connectToRedis() {
        jedis = new Jedis(REDIS_HOSTNAME, REDIS_PORT);
    }

    @Override
    public void put(String key, Integer value) {
        try {
            jedis.set(key, value.toString());
        } catch (Exception e) {
            System.err.println("Couldn't store "+key);
        }
    }

    @Override
    public Integer get(String key) {
        try {
            String value = jedis.get(key);
            if (value != null) {
                return Integer.parseInt(value);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void resetSink() {
        if (jedis == null) {
            connectToRedis();
        }
        jedis.flushDB();
        jedis.disconnect();
        jedis=null;
    }

    @Override
    public void prepareDestiny() {
        connectToRedis();
    }

}
