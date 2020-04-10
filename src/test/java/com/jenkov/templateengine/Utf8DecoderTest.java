package com.jenkov.templateengine;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Utf8DecoderTest {

    @Test
    public void test() throws UnsupportedEncodingException {

        Utf8Decoder utf8Decoder = new Utf8Decoder(new byte[] { 0b01010101 });

        assertEquals(0b01010101, utf8Decoder.nextCodePoint());
        assertEquals(0b01010101, utf8Decoder.getCodePoint());

        utf8Decoder.setData(new byte[]{(byte) 0b11010101, (byte) 0b10101010});

        assertEquals(0b00000_10101_101010, utf8Decoder.nextCodePoint());
        assertEquals(0b00000_10101_101010, utf8Decoder.getCodePoint());

        utf8Decoder.setData(new byte[]{(byte) 0b11101110, (byte) 0b10101010, (byte) 0b10010101});

        assertEquals(0b1110_101010_010101, utf8Decoder.nextCodePoint());
        assertEquals(0b1110_101010_010101, utf8Decoder.getCodePoint());

        utf8Decoder.setData(new byte[]{(byte) 0b11110111, (byte) 0b10101010, (byte) 0b10010101, (byte) 0b10110011});

        assertEquals(0b111_101010_010101_110011, utf8Decoder.nextCodePoint());
        assertEquals(0b111_101010_010101_110011, utf8Decoder.getCodePoint());


        byte[] data2 = "æøå".getBytes("UTF-8");

        utf8Decoder.setData(data2);

        assertEquals('æ', (char) utf8Decoder.nextCodePoint());
        assertEquals('ø', (char) utf8Decoder.nextCodePoint());
        assertEquals('å', (char) utf8Decoder.nextCodePoint());


        System.out.println("done");

    }
}
