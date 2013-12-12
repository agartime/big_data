package com.agartime.utad.friendsofmyfriends;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableUtils;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by antoniogonzalezartime on 12/12/13.
 */
public class MutualRelationshipFinderReducer extends Reducer<Text, DirectionalRelationshipWritable, Text, Text> {

    @Override
    protected void reduce(Text keyFriend, Iterable<DirectionalRelationshipWritable> relations, Context context) throws IOException, InterruptedException {
        ArrayList<Text> directRelationships = new ArrayList<Text>();
        ArrayList<Text> reverseRelationships = new ArrayList<Text>();

        for (DirectionalRelationshipWritable relation : relations) {
            DirectionalRelationshipWritable copy = WritableUtils.clone(relation, context.getConfiguration());
            if (copy.isRightRelationship()) {
                DirectionalRelationshipWritable now = copy;
                directRelationships.add(copy.getFriend());
            } else {
                reverseRelationships.add(copy.getFriend());
            }
        }

        for (Text originFriend : reverseRelationships) {
            for (Text destinyFriend : directRelationships) {
                if (!originFriend.equals(destinyFriend)) context.write(originFriend,destinyFriend);
            }
        }

    }
}
