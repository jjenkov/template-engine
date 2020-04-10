package com.jenkov.templateengine;

import java.io.UnsupportedEncodingException;

public class ID {

    protected byte[] idBytes = null;
    private int hashCode = -1;

    public ID(byte[] idBytes) {
        this.idBytes = idBytes;
    }

    public byte[] getIdBytes() {
        return this.idBytes;
    }

    @Override
    public int hashCode() {
        if(hashCode == -1) {
            for(int i=0; i<this.idBytes.length; i++) {
                hashCode += ((i+1) * (0xff & idBytes[i]));
            }
        }
        return this.hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if(! (obj instanceof ID)){
            return false;
        }

        ID other = (ID) obj;

        if(this.idBytes.length != other.idBytes.length) {
            return false;
        }

        if(hashCode() != other.hashCode()){
            return false;
        }

        for(int i=0; i<this.idBytes.length; i++) {
            if(this.idBytes[i] != other.idBytes[i]){
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        try {
            return new String(this.idBytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new String(this.idBytes);
        }
    }
}
