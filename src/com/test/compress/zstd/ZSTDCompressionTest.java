package com.test.compress.zstd;

import com.github.luben.zstd.ZstdInputStream;
import com.github.luben.zstd.ZstdOutputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ZSTDCompressionTest {
    public void compressZSTD(String fileName, String ZSTDFileName) {
        long startTime = System.currentTimeMillis();
        byte[] buf = new byte[2048];
        try {
            long in_startTime = System.currentTimeMillis();
            FileInputStream in = new FileInputStream(fileName);
            long in_endTime = System.currentTimeMillis();

            System.out.println("ZSTD Input compress Run Time: " + (in_endTime - in_startTime) + "ms");
            System.out.println(in.getChannel().size());

            long out_startTime = System.currentTimeMillis();
            ZstdOutputStream out = new ZstdOutputStream(new FileOutputStream(ZSTDFileName));
            int len;
            while((len = in.read(buf)) > 0){
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            long out_endTIme = System.currentTimeMillis();
            System.out.println("ZSTD output compress Run Time: " + (out_endTIme - out_startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("ZSTD Compress Run Time: " + (endTime - startTime) + "ms");
    }
    public void decompressZSTD(String ZSTDFileName, String fileName) {
        long startTime = System.currentTimeMillis();
        byte[] buf = new byte[2048];
        try {
            ZstdInputStream in = new ZstdInputStream(new FileInputStream(ZSTDFileName));
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
        System.out.println("ZSTD decompress Run Time: " + (endTime - startTime) + "ms");
    }
}
