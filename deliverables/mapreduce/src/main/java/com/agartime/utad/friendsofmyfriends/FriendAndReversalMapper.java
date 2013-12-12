package com.agartime.utad.friendsofmyfriends;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by antoniogonzalezartime on 12/12/13.
 */
public class FriendAndReversalMapper extends Mapper<Text, Text, Text, DirectionalRelationshipWritable> {

    @Override
    protected void map(Text originFriend, Text destinyFriend, Context context) throws IOException, InterruptedException {
        if (originFriend.toString().isEmpty() || destinyFriend.toString().isEmpty())
            return;

        context.write(originFriend, new DirectionalRelationshipWritable(DirectionalRelationshipWritable.RIGHT,destinyFriend));
        context.write(destinyFriend, new DirectionalRelationshipWritable(DirectionalRelationshipWritable.LEFT,originFriend));

    }
}
