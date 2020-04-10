package com.jenkov.templateengine;

public class RawData implements Data {
    protected byte[] data = null;

    public RawData(byte[] data) {
        this.data = data;
    }

    @Override
    public int writeDataInto(byte[] dest, int offset) {
        System.arraycopy(this.data, 0, dest, offset, this.data.length);
        return data.length;
    }
}
