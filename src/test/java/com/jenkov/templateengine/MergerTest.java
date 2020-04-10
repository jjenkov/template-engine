package com.jenkov.templateengine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergerTest {

    @Test
    public void testMerge() {
        ID insertionPointId1 = new ID(new byte[]{1});
        ID insertionPointId2 = new ID(new byte[]{2});
        ID insertionPointId3 = new ID(new byte[]{3});

        Merger merger = new Merger();

        Template template = new Template();
        template.setData(new byte[]{0,1,2,3,4,5,6,7,8,9});
        template.addInsertionPoint(insertionPointId1, 1,2);
        template.addInsertionPoint(insertionPointId2, 5,7);
        template.addInsertionPoint(insertionPointId3, 8,9);

        merger.setTemplate(template);

        DataProvider dataProvider = new DataProvider();
        dataProvider.addData(insertionPointId1, new RawData(new byte[]{21,22,23}));
        dataProvider.addData(insertionPointId2, new RawData(new byte[]{31,32}));
        dataProvider.addData(insertionPointId3, new RawData(new byte[]{41}));

        merger.setDataProvider(dataProvider);

        byte[] dest = new byte[1024];

        int bytesWritten = merger.writeDataInto(dest, 0);

        assertEquals(12, bytesWritten);

        int destIndex = 0;
        assertEquals( 0, dest[destIndex++]);
        assertEquals(21, dest[destIndex++]);
        assertEquals(22, dest[destIndex++]);
        assertEquals(23, dest[destIndex++]);
        assertEquals( 2, dest[destIndex++]);
        assertEquals( 3, dest[destIndex++]);
        assertEquals( 4, dest[destIndex++]);
        assertEquals(31, dest[destIndex++]);
        assertEquals(32, dest[destIndex++]);
        assertEquals( 7, dest[destIndex++]);
        assertEquals(41, dest[destIndex++]);
        assertEquals( 9, dest[destIndex++]);
    }
}
