package com.test.compress;

import com.test.compress.gzip.GZIPCompressionTest;
import com.test.compress.lz4.LZ4CompressionTest;
import com.test.compress.lzo.LZOCompressionTest;
import com.test.compress.zstd.ZSTDCompressionTest;

public class Main {

    public static void main(String[] args) {
	// write your code here
        LZ4CompressionTest lz4CompressionTest = new LZ4CompressionTest();
        ZSTDCompressionTest zstdCompressionTest = new ZSTDCompressionTest();
        LZOCompressionTest lzoCompressionTest = new LZOCompressionTest();
        GZIPCompressionTest gzipCompressionTest = new GZIPCompressionTest();

        //Test LZ4
        System.out.println("----");
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
        gzipCompressionTest.decompressGZIP("./src/resources/test.gzip", "./src/resources/test_gzip.json");
    }
}
