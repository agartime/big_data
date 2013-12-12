package com.agartime.utad.mapreduce.patterns.secondarysort;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class SongNum implements Writable {
	private Text song;
	private IntWritable num;
	
	public SongNum(){
		song = new Text();
		num = new IntWritable();
	}
	
	public SongNum(Text song, IntWritable num) {
		super();
		this.song = song;
		this.num = num;
	}

	public Text getSong() {
		return song;
	}

	public void setSong(Text song) {
		this.song = song;
	}

	public IntWritable getNum() {
		return num;
	}

	public void setNum(IntWritable num) {
		this.num = num;
	}

	
	@Override
	public String toString() {
		return "[" + song + " - " + num + "]";
	}

	@Override
	public void readFields(DataInput arg0) throws IOException {
		this.song.readFields(arg0);
		this.num.readFields(arg0);
		
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		this.song.write(arg0);
		this.num.write(arg0);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SongNum other = (SongNum) obj;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		if (song == null) {
			if (other.song != null)
				return false;
		} else if (!song.equals(other.song))
			return false;
		return true;
	}


}
