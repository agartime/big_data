package com.agartime.utad.histogram;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by antoniogonzalezartime on 11/12/13.
 */
public class IdRangeComparator extends WritableComparator {

    protected IdRangeComparator() {
        super(CkIdRange.class, true);
    }

    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        CkIdRange cin1 = (CkIdRange) w1;
        CkIdRange cin2 = (CkIdRange) w2;

        int cmp = cin1.getId().compareTo(cin2.getId());
        if (cmp != 0) {
            return cmp;
        }
        return cin1.getRange().compareTo(cin2.getRange());
    }
}
