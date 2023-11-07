package com.jenkov.templateengine;

import com.sun.scenario.effect.Merge;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

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

    @Test
    public void testMergeWithTemplate() throws UnsupportedEncodingException {
        Merger merger = new Merger();


        Utf8TemplateParser parser = new Utf8TemplateParser();
        byte[] utf8Bytes = "1 ${one} and 2 ${two} 3".getBytes("UTF-8");
        Template template = parser.parse(utf8Bytes);
        merger.setTemplate(template);

        DataProvider dataProvider = new DataProvider();
        dataProvider.addData(new ID("one".getBytes("UTF-8")), new RawData(new byte[]{65,66,67}));
        dataProvider.addData(new ID("two".getBytes("UTF-8")), new RawData(new byte[]{97,98}));

        merger.setDataProvider(dataProvider);

        byte[] dest = new byte[1024];

        //System.out.println("=====================");
        //System.out.println(" Template      : " + template);
        //System.out.println(" Template Data : " + template.getData());
        int bytesWritten = merger.writeDataInto(dest, 0);

        assertEquals(16, bytesWritten);

        int index = 0;
        assertEquals('1', (char) dest[index++]);
        assertEquals(' ', (char) dest[index++]);
        assertEquals('A', (char) dest[index++]);
        assertEquals('B', (char) dest[index++]);
        assertEquals('C', (char) dest[index++]);
        assertEquals(' ', (char) dest[index++]);
        assertEquals('a', (char) dest[index++]);
        assertEquals('n', (char) dest[index++]);
        assertEquals('d', (char) dest[index++]);
        assertEquals(' ', (char) dest[index++]);
        assertEquals('2', (char) dest[index++]);
        assertEquals(' ', (char) dest[index++]);
        assertEquals('a', (char) dest[index++]);
        assertEquals('b', (char) dest[index++]);
        assertEquals(' ', (char) dest[index++]);
        assertEquals('3', (char) dest[index++]);


    }
}
