package com.agartime.utad.histogram;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by antoniogonzalezartime on 11/12/13.
 */
public class GroupIdComparator extends WritableComparator {

    public GroupIdComparator() {
        super(CkIdRange.class, true);
    }

    public int compare(WritableComparable w1, WritableComparable w2) {
        CkIdRange cin1 = (CkIdRange) w1;
        CkIdRange cin2 = (CkIdRange) w2;
        return (cin1.getId()).compareTo(cin2.getId());
    }
}
