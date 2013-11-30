package com.agartime.utad.mapreduce.patterns;


import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupIdComparator extends WritableComparator {

	public GroupIdComparator() {
		super(CkIdNum.class, true);
	}

	public int compare(WritableComparable w1, WritableComparable w2) {
		CkIdNum cin1 = (CkIdNum) w1;
		CkIdNum cin2 = (CkIdNum) w2;
		return (cin1.getId()).compareTo(cin2.getId());
	}

}