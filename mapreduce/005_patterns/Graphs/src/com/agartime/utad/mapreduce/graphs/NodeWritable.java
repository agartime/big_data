package com.agartime.utad.mapreduce.graphs;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class NodeWritable implements Writable {
	private IntWritable nodeId;
	private IntWritable nodeDistance;
	private Text friendNodes;

	//Hadoop needs a default constructor.
	public NodeWritable() {
		super();
	}

	public NodeWritable(IntWritable nodeId, IntWritable nodeDistance, Text friendNodes) {
		this.nodeId=nodeId;
		this.nodeDistance=nodeDistance;
		this.friendNodes=friendNodes;
	}
	
	@Override
	public String toString() {
		return "(" + nodeId + ",<"+ nodeDistance + ",("+friendNodes+")>)";
	}

	@Override
	public void readFields(DataInput arg0) throws IOException {
		this.nodeId.readFields(arg0);
		this.nodeDistance.readFields(arg0);
		this.friendNodes.readFields(arg0);
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		this.nodeId.write(arg0);
		this.nodeDistance.write(arg0);
		this.friendNodes.write(arg0);
	}
	
	public IntWritable getNodeId() {
		return nodeId;
	}
	public void setNodeId(IntWritable nodeId) {
		this.nodeId = nodeId;
	}

	public IntWritable getNodeDistance() {
		return nodeDistance;
	}

	public void setNodeDistance(IntWritable nodeDistance) {
		this.nodeDistance = nodeDistance;
	}

	public Text getFriendNodes() {
		return friendNodes;
	}

	public void setFriendNodes(Text friendNodes) {
		this.friendNodes = friendNodes;
	}

}
