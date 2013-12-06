package com.agartime.utad.mapreduce.patterns.secondarysort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class IdNumComparator extends WritableComparator {
	protected IdNumComparator() {
		super(CkIdNum.class, true);
	}

	@Override
	public int compare(WritableComparable w1, WritableComparable w2) {
		CkIdNum cin1 = (CkIdNum) w1;
		CkIdNum cin2 = (CkIdNum) w2;
		
		int cmp = cin1.getId().compareTo(cin2.getId());
		if (cmp != 0) {
			return cmp;
		}
		// negative because we want most popular songs first
		return -(cin1.getNum()).compareTo(cin2.getNum());
	}
}
