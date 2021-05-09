package com.test.compress.gzip;

import com.github.luben.zstd.ZstdInputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPCompressionTest {
    public void compressGZIP(String fileName, String GZIPFileName) {
        long startTime = System.currentTimeMillis();
        byte[] buf = new byte[2048];
        try {
            long in_startTime = System.currentTimeMillis();
            FileInputStream in = new FileInputStream(fileName);
            long in_endTime = System.currentTimeMillis();

            System.out.println("GZIP Input compress Run Time: " + (in_endTime - in_startTime) + "ms");
            System.out.println(in.getChannel().size());

            long out_startTime = System.currentTimeMillis();
            GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(GZIPFileName));
            int len;
            while((len = in.read(buf)) > 0){
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            long out_endTIme = System.currentTimeMillis();
            System.out.println("GZIP output compress Run Time: " + (out_endTIme - out_startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("GZIP Compress Run Time: " + (endTime - startTime) + "ms");
    }

    public void decompressGZIP(String ZSTDFileName, String fileName) {
        long startTime = System.currentTimeMillis();
        byte[] buf = new byte[2048];
        try {
            GZIPInputStream in = new GZIPInputStream(new FileInputStream(ZSTDFileName));
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
        System.out.println("GZIP decompress Run Time: " + (endTime - startTime) + "ms");
    }
}
