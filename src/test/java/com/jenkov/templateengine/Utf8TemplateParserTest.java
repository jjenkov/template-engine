package com.jenkov.templateengine;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Utf8TemplateParserTest {


    @Test
    public void test() throws UnsupportedEncodingException {
        Utf8TemplateParser parser = new Utf8TemplateParser();

        byte[] utf8Bytes = "This is a template with IP 1 ${one} and IP 2 ${two} and a bit more text.".getBytes("UTF-8");

        Template template = parser.parse(utf8Bytes);

        List<InsertionPoint> insertionPoints = template.getInsertionPoints();
        assertEquals(2, insertionPoints.size());

        assertEquals(new ID("one".getBytes("UTF-8")), insertionPoints.get(0).getId());
        assertEquals(new ID("two".getBytes("UTF-8")), insertionPoints.get(1).getId());

    }
}
