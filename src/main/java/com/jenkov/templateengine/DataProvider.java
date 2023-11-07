package com.jenkov.templateengine;

import java.util.HashMap;
import java.util.Map;

public class DataProvider {

    protected Map<ID, Data> dataMap = new HashMap<>();

    public void addData(ID insertionPointId, Data data) {
        this.dataMap.put(insertionPointId, data);
    }

    public int writeDataInto(byte[] dest, int offset, ID insertionPointId){
        Data dataForInsertionPointId = this.dataMap.get(insertionPointId);

        //handle no (null) dataForInsertionPointId situations.
        if(dataForInsertionPointId == null) {
            return 0;
        }

        return dataForInsertionPointId.writeDataInto(dest, offset);
    }

}
