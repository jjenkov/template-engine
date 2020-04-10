package com.jenkov.templateengine;

public class InsertionPoint {

    protected ID  id = null;

    protected int startOffset = 0;
    protected int endOffset   = 0;

    public InsertionPoint() {}

    public InsertionPoint(ID id, int startOffset, int endOffset) {
        this.id          = id;
        this.startOffset = startOffset;
        this.endOffset   = endOffset;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public void setStartOffset(int startOffset) {
        this.startOffset = startOffset;
    }

    public int getEndOffset() {
        return endOffset;
    }

    public void setEndOffset(int endOffset) {
        this.endOffset = endOffset;
    }
}
