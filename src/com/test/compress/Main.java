package com.test.compress;

import com.test.compress.gzip.GZIPCompressionTest;
import com.test.compress.lz4.LZ4CompressionTest;
import com.test.compress.lzo.LZOCompressionTest;
import com.test.compress.zstd.ZSTDCompressionTest;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
	// write your code here
        LZ4CompressionTest lz4CompressionTest = new LZ4CompressionTest();
        ZSTDCompressionTest zstdCompressionTest = new ZSTDCompressionTest();
        LZOCompressionTest lzoCompressionTest = new LZOCompressionTest();
        GZIPCompressionTest gzipCompressionTest = new GZIPCompressionTest();

        //Test LZ4
        /*System.out.println("----");
        lz4CompressionTest.compressLZ4("./src/resources/testByLZ4.json", "./src/resources/test.lz4");
        lz4CompressionTest.decompressLZ4("./src/resources/test.lz4", "./src/resources/test_lz4.json");

        //Test ZSTD
        System.out.println("----");
        zstdCompressionTest.compressZSTD("./src/resources/testByZSTD.json", "./src/resources/test.zstd");
        zstdCompressionTest.decompressZSTD("./src/resources/test.zstd", "./src/resources/test_zstd.json");

        //Test LZO
        System.out.println("----");
        lzoCompressionTest.compressLZO("./src/resources/testByLZO.json", "./src/resources/test.lzo");
        lzoCompressionTest.decompressLZO("./src/resources/test.lzo", "./src/resources/test_lzo.json");

        //Test GZIP
        System.out.println("----");
        gzipCompressionTest.compressGZIP("./src/resources/testByGZIP.json", "./src/resources/test.gzip");
        gzipCompressionTest.decompressGZIP("./src/resources/test.gzip", "./src/resources/test_gzip.json");*/

        //Test LZ4
        /*System.out.println("----");
        lz4CompressionTest.compressLZ4("./src/resources/kubeInfo_test.json", "./src/resources/lz4tmp/kubeInfo_test.lz4");
        lz4CompressionTest.decompressLZ4("./src/resources/lz4tmp/kubeInfo_test.lz4", "./src/resources/lz4tmp/kubeInfo_test_lz4.json");*/

        //Test ZSTD
        /*System.out.println("----");
        zstdCompressionTest.compressZSTD("./src/resources/kubeInfo_test.json", "./src/resources/kubeInfo_test.zstd");
        zstdCompressionTest.decompressZSTD("./src/resources/kubeInfo_test.zstd", "./src/resources/kubeInfo_test_zstd.json");

        //Test LZO
        System.out.println("----");
        lzoCompressionTest.compressLZO("./src/resources/kubeInfo_test.json", "./src/resources/kubeInfo_test.lzo");
        lzoCompressionTest.decompressLZO("./src/resources/kubeInfo_test.lzo", "./src/resources/kubeInfo_test_lzo.json");

        //Test GZIP
        System.out.println("----");
        gzipCompressionTest.compressGZIP("./src/resources/kubeInfo_test.json", "./src/resources/kubeInfo_test.gzip");
        gzipCompressionTest.decompressGZIP("./src/resources/kubeInfo_test.gzip", "./src/resources/kubeInfo_test_gzip.json");*/

        /*for(int i = 0; i < 100; i++) {
            System.out.println("----");
            lz4CompressionTest.compressLZ4("./src/resources/kubeInfo_test.json", "./src/resources/lz4tmp/kubeInfo_test_"+ i + ".lz4");
            lz4CompressionTest.decompressLZ4("./src/resources/lz4tmp/kubeInfo_test_"+ i + ".lz4", "./src/resources/lz4tmp/kubeInfo_test_lz4_" + i + ".json");
        }*/

        /*for(int i = 0; i < 100; i++) {
            System.out.println("----");
            lz4CompressionTest.compressLZ4("./src/resources/testByLZ4.json", "./src/resources/lz4tmp/kubeInfo_test_"+ i + ".lz4");
            lz4CompressionTest.decompressLZ4("./src/resources/lz4tmp/kubeInfo_test_"+ i + ".lz4", "./src/resources/lz4tmp/kubeInfo_test_lz4_" + i + ".json");
        }*/

        Long testlz4 = 0L;
        for(int i = 0; i < 1000; i++) {
            System.out.println("----");

            Map<String, Long> result = lz4CompressionTest.compressLZ4("./src/resources/kubeInfo_test.json", "./src/resources/lz4tmp/kubeInfo_test.lz4");
            testlz4 += result.get("time");
            lz4CompressionTest.decompressLZ4("./src/resources/lz4tmp/kubeInfo_test.lz4", "./src/resources/lz4tmp/kubeInfo_test_lz4.json");
        }



        Long testzstd = 0L;
        for(int i = 0; i < 1000; i++) {
            System.out.println("----");

            Map<String, Long> result = zstdCompressionTest.compressZSTD("./src/resources/kubeinfo_test.json", "./src/resources/zstdtmp/kubeInfo_test_"+ i + ".zstd");
            testzstd += result.get("time");
            zstdCompressionTest.decompressZSTD("./src/resources/zstdtmp/kubeInfo_test_"+ i + ".zstd", "./src/resources/zstdtmp/kubeInfo_test_zstd_" + i + ".json");
        }


        System.out.println("test: " + testlz4);
        System.out.println("test: " + testlz4 / 500L);

        System.out.println("test: " + testzstd);
        System.out.println("test: " + testzstd / 500L);
    }
}
