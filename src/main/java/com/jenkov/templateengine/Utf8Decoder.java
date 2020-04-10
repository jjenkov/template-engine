package com.jenkov.templateengine;

public class Utf8Decoder {

    private byte[] data = null;

    private int readOffset = 0;

    private int codePoint = 0;

    public Utf8Decoder() {}

    public Utf8Decoder(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
        this.readOffset = 0;
        this.codePoint  = 0;
    }



    public int getReadOffset(){
        return this.readOffset;
    }

    public boolean hasNext() {
        return this.readOffset < this.data.length-1;
    }

    public int getCodePoint() {
        return this.codePoint;
    }

    public int nextCodePoint() {
        int firstByte = 0xff & this.data[readOffset++];

        if(        (firstByte >> 7) == 0 ){
            // 1 byte Unicode char
            this.codePoint = 0b0111_1111 & firstByte;
        } else if( (firstByte >> 5) == 0b110) {
            // 2 byte Unicode char
            this.codePoint = 0b0001_1111 & firstByte;
            this.codePoint <<= 6;
            this.codePoint |= (0b0011_1111 & this.data[readOffset++]);

        } else if( (firstByte >> 4) == 0b1110) {
            // 3 byte Unicode char
            this.codePoint = 0b0000_1111 & firstByte;
            this.codePoint <<= 6;
            this.codePoint |= (0b0011_1111 & this.data[readOffset++]);
            this.codePoint <<= 6;
            this.codePoint |= (0b0011_1111 & this.data[readOffset++]);
        } else if( (firstByte >> 3) == 0b11110) {
            // 4 byte Unicode char
            this.codePoint = 0b0000_0111 & firstByte;
            this.codePoint <<= 6;
            this.codePoint |= (0b0011_1111 & this.data[readOffset++]);
            this.codePoint <<= 6;
            this.codePoint |= (0b0011_1111 & this.data[readOffset++]);
            this.codePoint <<= 6;
            this.codePoint |= (0b0011_1111 & this.data[readOffset++]);
        }

        return this.codePoint;
    }
}
