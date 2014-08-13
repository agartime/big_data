package org.agartime.utad.storm.destiny;

/**
 * Created by agartime on 13/04/14.
 */
public interface DestinySink<K,V> {
    public void put(K key, V value);
    public V get(K key);
    public void resetSink();
    public void prepareDestiny();
}
