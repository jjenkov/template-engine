package com.jenkov.templateengine;

public class Utf8TemplateParser {

    private Utf8Decoder utf8Decoder = new Utf8Decoder();

    public Template parse(byte[] utf8Bytes) {
        this.utf8Decoder.setData(utf8Bytes);
        return parse(this.utf8Decoder);
    }

    public Template parse(Utf8Decoder utf8Decoder) {

        Template template = new Template();
        template.setData(utf8Decoder.getData());

        int nextCodePoint = 0;
        while(utf8Decoder.hasNext()) {
            nextCodePoint = utf8Decoder.nextCodePoint();

            if('$' == (char) nextCodePoint) {
                int nextNextCodePoint = utf8Decoder.nextCodePoint();
                if('{' == (char) nextNextCodePoint) {
                    int insertPointStartOffset = utf8Decoder.getReadOffset() - 2; // -2 to step back before ${

                    //this is an insertion point. Find end of insertion point ID
                    while(utf8Decoder.hasNext() && '}' != (char) nextNextCodePoint){
                        nextNextCodePoint = utf8Decoder.nextCodePoint();
                    }

                    int insertionPointEndOffset = utf8Decoder.getReadOffset();

                    byte[] idBytes = new byte[insertionPointEndOffset - insertPointStartOffset - 3];
                    System.arraycopy(utf8Decoder.getData(), insertPointStartOffset + 2, idBytes, 0, insertionPointEndOffset - insertPointStartOffset - 3);
                    ID insertionPointId = new ID(idBytes);

                    template.addInsertionPoint(insertionPointId, insertPointStartOffset, insertionPointEndOffset);
                }
            }
        }
        return template;
    }


}
