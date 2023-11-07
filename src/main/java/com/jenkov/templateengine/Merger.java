package com.jenkov.templateengine;

import java.util.List;

public class Merger implements Data{

    protected Template     template     = null;
    protected DataProvider dataProvider = null;

    public Merger() {}

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public DataProvider getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Override
    public int writeDataInto(byte[] dest, int firstOffset) {

        byte[]               templateData    = template.getData();
        List<InsertionPoint> insertionPoints = template.getInsertionPoints();

        if(insertionPoints.size() == 0) {
            System.arraycopy(templateData, 0, dest, firstOffset, templateData.length);
            return templateData.length;
        }

        int writeOffset   = firstOffset;
        //int prevEndOffset = 0;
        int prevEndOffset = this.template.getOffset(); //offset for beginning of template data
        for(int i=0; i<insertionPoints.size(); i++) {
            InsertionPoint insertionPoint = insertionPoints.get(i);
            int startOffset = insertionPoint.getStartOffset();

            //copy data from template - from previous insertion point endOffset to insertion point start offset
            int templateDataBlockLength = startOffset - prevEndOffset;
            //System.out.println("templateData: " + templateData);
            //System.out.println("dest: " + dest);
            System.arraycopy(templateData, prevEndOffset, dest, writeOffset, templateDataBlockLength);
            prevEndOffset = insertionPoint.getEndOffset();

            writeOffset += templateDataBlockLength;

            //write data for insertion point into dest array
            writeOffset += this.dataProvider.writeDataInto(dest, writeOffset, insertionPoint.getId());
        }

        //write last template data block - after last insertion point and until end of template.
        int templateDataEndOffset = this.template.getOffset() + this.template.getLength();

        int lastBlockLength = templateDataEndOffset - prevEndOffset;
        System.arraycopy(templateData, prevEndOffset, dest, writeOffset, lastBlockLength);
        writeOffset += lastBlockLength;


        return writeOffset - firstOffset;
    }
}
