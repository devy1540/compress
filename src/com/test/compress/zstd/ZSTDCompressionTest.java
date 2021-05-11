package com.test.compress.zstd;

//import org.apache.commons.compress.compressors.zstandard.ZstdCompressorInputStream;
import com.github.luben.zstd.ZstdInputStream;
import com.github.luben.zstd.ZstdOutputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorOutputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ZSTDCompressionTest {
    public Map<String, Long> compressZSTD(String fileName, String ZSTDFileName) {
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
//            ZstdCompressorOutputStream out = new ZstdCompressorOutputStream(new FileOutputStream(ZSTDFileName));
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
        Map<String, Long> result = new HashMap<>();
        result.put("time", endTime - startTime);
        return result;
    }
    public void decompressZSTD(String ZSTDFileName, String fileName) {
        long startTime = System.currentTimeMillis();
        byte[] buf = new byte[2048];
        try {
            ZstdInputStream in = new ZstdInputStream(new FileInputStream(ZSTDFileName));
//            ZstdCompressorInputStream in = new ZstdCompressorInputStream(new FileInputStream(ZSTDFileName));
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
