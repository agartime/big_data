package com.agartime.utad.mapreduce.patterns.secondarysort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

public class CkIdNum implements WritableComparable <CkIdNum>{
	private IntWritable id;
	private IntWritable num;

	public CkIdNum() {
		id = new IntWritable();
		num = new IntWritable();
	}

	public CkIdNum(IntWritable id, IntWritable num) {
		this.id = id;
		this.num = num;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CkIdNum other = (CkIdNum) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (num == null) {
			if (other.num != null)
				return false;
		}
		return num.equals(other.num);
	}

	public IntWritable getId() {
		return id;
	}

	public void setId(IntWritable id) {
		this.id = id;
	}

	public IntWritable getNum() {
		return num;
	}

	public void setNum(IntWritable num) {
		this.num = num;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		id.readFields(in);
		num.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		id.write(out);
		num.write(out);
	}

	@Override
	public int compareTo(CkIdNum o) {
		int cmp = id.compareTo(o.getId());
		if (cmp != 0) {
			return cmp;
		} else
			return num.compareTo(o.getNum());
	}

	@Override
	public String toString() {
		return "IdSongNum [id=" + id + ", num=" + num + "]";
	}

}
