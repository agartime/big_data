package com.agartime.utad.histogram;

import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by antoniogonzalezartime on 11/12/13.
 */
public class CkIdRange implements WritableComparable<CkIdRange> {

    private VIntWritable id;
    private RangeWritable range;

    public CkIdRange() {
        this.id = new VIntWritable();
        this.range = new RangeWritable();
    }

    public CkIdRange(VIntWritable key, RangeWritable range) {
        this.id=key;
        this.range=range;

    }

    public VIntWritable getId() {
        return id;
    }

    public void setId(VIntWritable id) {
        this.id = id;
    }

    public RangeWritable getRange() {
        return range;
    }

    public void setRange(RangeWritable range) {
        this.range = range;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CkIdRange ckIdRange = (CkIdRange) o;

        if (id != null ? !id.equals(ckIdRange.id) : ckIdRange.id != null) return false;
        if (range != null ? !range.equals(ckIdRange.range) : ckIdRange.range != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (range != null ? range.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(CkIdRange ckIdRange) {
        int cmp = this.id.compareTo(ckIdRange.getId());
        if (cmp != 0) {
            return cmp;
        } else
            return range.compareTo(ckIdRange.getRange());
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        this.id.write(dataOutput);
        this.range.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.id.readFields(dataInput);
        this.range.readFields(dataInput);
    }

    @Override
    public String toString() {
        return "CkIdRange{" +
                "id=" + id +
                ", range=" + range +
                '}';
    }
}
