package com.agartime.utad.friendsofmyfriends;

import org.apache.hadoop.io.*;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by antoniogonzalezartime on 12/12/13.
 */
public class DirectionalRelationshipWritable implements WritableComparable<DirectionalRelationshipWritable> {
    protected final static BooleanWritable RIGHT = new BooleanWritable(true);
    protected final static BooleanWritable LEFT = new BooleanWritable(false);

    private BooleanWritable direction;
    private Text friend;

    public DirectionalRelationshipWritable() {
        this.direction=new BooleanWritable();
        this.friend=new Text();
    }

    public DirectionalRelationshipWritable(BooleanWritable direction, Text friend) {
        this.direction = direction;
        this.friend = new Text(friend);
    }

    public BooleanWritable getDirection() {
        return direction;
    }

    public void setDirection(BooleanWritable direction) {
        this.direction = direction;
    }

    public Text getFriend() {
        return friend;
    }

    public void setFriend(Text friend) {
        this.friend = friend;
    }

    public boolean isRightRelationship() {
        return this.direction.equals(RIGHT);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        friend.write(dataOutput);
        direction.write(dataOutput);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        friend.readFields(dataInput);
        direction.readFields(dataInput);
    }

    @Override
    public int compareTo(DirectionalRelationshipWritable directionalRelationshipWritable) {
        return (friend.compareTo(directionalRelationshipWritable.getFriend()) + direction.compareTo(directionalRelationshipWritable.direction)); //Lexicographic by friend and direction
    }

}
