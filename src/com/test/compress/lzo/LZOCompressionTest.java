package com.test.compress.lzo;


import org.anarres.lzo.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class LZOCompressionTest {
    public void compressLZO(String fileName, String LZOFileName) {
        long startTime = System.currentTimeMillis();
        byte[] buf = new byte[2048];
        try {
            long in_startTime = System.currentTimeMillis();
            FileInputStream in = new FileInputStream(fileName);
            long in_endTime = System.currentTimeMillis();

            System.out.println("LZO Input compress Run Time: " + (in_endTime - in_startTime) + "ms");
            System.out.println(in.getChannel().size());

            long out_startTime = System.currentTimeMillis();
            LzoAlgorithm algorithm = LzoAlgorithm.LZO1X;
            LzoCompressor compressor = LzoLibrary.getInstance().newCompressor(algorithm, null);

            LzoOutputStream out = new LzoOutputStream(new FileOutputStream(LZOFileName), compressor, 256);

            int len;
            while((len = in.read(buf)) > 0){
                out.write(buf, 0, len);
            }
            in.close();
            out.close();

            long out_endTIme = System.currentTimeMillis();
            System.out.println("LZO output compress Run Time: " + (out_endTIme - out_startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("LZO Compress Run Time: " + (endTime - startTime) + "ms");
    }

    public void decompressLZO(String LZOFileName, String fileName) {
        long startTime = System.currentTimeMillis();
        byte[] buf = new byte[2048];

        LzoAlgorithm algorithm = LzoAlgorithm.LZO1X;
        LzoDecompressor decompressor = LzoLibrary.getInstance().newDecompressor(algorithm, null);
        try {
            LzoInputStream in = new LzoInputStream(new FileInputStream(LZOFileName), decompressor);
            FileOutputStream out = new FileOutputStream(fileName);

            int len;
            while((len = in.read(buf)) > 0){
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("LZO decompress Run Time: " + (endTime - startTime) + "ms");
    }
}
