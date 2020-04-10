package com.jenkov.templateengine;

import java.util.ArrayList;
import java.util.List;

public class Template {
    protected byte[] data = null;
    protected List<InsertionPoint> insertionPoints = new ArrayList();

    public Template() {

    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
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
