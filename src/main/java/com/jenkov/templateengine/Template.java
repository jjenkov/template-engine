package com.jenkov.templateengine;

import java.util.ArrayList;
import java.util.List;

public class Template {
    protected byte[] data   = null;
    protected int    offset = 0;
    protected int    length = 0;

    protected List<InsertionPoint> insertionPoints = new ArrayList();

    public Template() {

    }

    public byte[] getData() {
        return data;
    }

    public int getOffset() {
        return this.offset;
    }

    public int getLength() {
        return this.length;
    }

    public void setData(byte[] data) {
        this.data = data;
        this.offset = 0;
        this.length = data.length;
    }

    public void setData(byte[] data, int offset, int length) {
        this.data = data;
        this.offset = offset;
        this.length = length;
    }

    public List<InsertionPoint> getInsertionPoints() {
        return insertionPoints;
    }

    public void setInsertionPoints(List<InsertionPoint> insertionPoints) {
        this.insertionPoints = insertionPoints;
    }

    public void addInsertionPoint(InsertionPoint insertionPoint) {
        this.insertionPoints.add(insertionPoint);
    }
    public void addInsertionPoint(ID insertionPointId, int startOffset, int endOffset) {
        addInsertionPoint(new InsertionPoint(insertionPointId, startOffset, endOffset));
    }
}
