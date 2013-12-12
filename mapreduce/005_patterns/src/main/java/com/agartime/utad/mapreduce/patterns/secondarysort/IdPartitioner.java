package com.agartime.utad.mapreduce.patterns.secondarysort;

import org.apache.hadoop.mapreduce.Partitioner;

public class IdPartitioner extends Partitioner<CkIdNum, SongNum> {

	@Override
	public int getPartition(CkIdNum key, SongNum value, int numPartitions) {
		// multiply by 127 to perform some mixing
		return Math.abs(key.getId().get() * 127) % numPartitions;
	}

}
