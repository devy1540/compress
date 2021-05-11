package com.test.compress.lz4;

import net.jpountz.lz4.LZ4BlockInputStream;
import net.jpountz.lz4.LZ4BlockOutputStream;
import org.apache.commons.compress.compressors.lz4.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LZ4CompressionTest {
//    LZ4Factory factory = LZ4Factory.fastestInstance();

    /*public void compressLZ4() {
        try {
            InputStream fis = new FileInputStream("./src/resources/test.json");
            byte[] jsonByteData = IOUtils.toByteArray(fis);
            int beforeJsonByteDataLength = jsonByteData.length;
            System.out.println("read before json file and convert to byte array: " +  Arrays.toString(jsonByteData));
            System.out.println("before json file length: " +  beforeJsonByteDataLength);

            LZ4Compressor compressor = factory.fastCompressor();
            int maxCompressedLength = compressor.maxCompressedLength(beforeJsonByteDataLength);
            System.out.println("after json file length: " +  maxCompressedLength);
            byte[] compressed = new byte[maxCompressedLength];

            System.out.println("read after json file and convert to byte array: " +  Arrays.toString(compressed));
            int compressedLength = compressor.compress(jsonByteData, 0, beforeJsonByteDataLength, compressed, 0, maxCompressedLength);
            System.out.println("compressedLength: " +  compressedLength);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public Map<String, Long> compressLZ4(String filename, String lz4file) {
        long startTime = System.currentTimeMillis();
        byte[] buf = new byte[2048];
        try {

            long in_startTime = System.currentTimeMillis();
            FileInputStream in = new FileInputStream(filename);
            long in_endTime = System.currentTimeMillis();

            System.out.println("LZ4 Input compress Run Time: " + (in_endTime - in_startTime) + "ms");
            System.out.println(in.getChannel().size());

            long out_startTime = System.currentTimeMillis();
//            LZ4BlockOutputStream out = new LZ4BlockOutputStream(new FileOutputStream(lz4file), 32*1024*1024);
            LZ4BlockOutputStream out = new LZ4BlockOutputStream(new FileOutputStream(lz4file));
//            BlockLZ4CompressorOutputStream out = new BlockLZ4CompressorOutputStream(new FileOutputStream(lz4file));
            int len;
            while((len = in.read(buf)) > 0){
                out.write(buf, 0, len);
            }
            long out_endTIme = System.currentTimeMillis();

            in.close();
            out.close();
            System.out.println(out.toString());
            System.out.println("LZ4 output compress Run Time: " + (out_endTIme - out_startTime) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("LZ4compress Run Time: " + (endTime - startTime) + "ms");
        Map<String, Long> result = new HashMap<>();
        result.put("time", endTime - startTime);
        return result;
    }

    /*public void compressLZ4V2(String filename, String lz4file) {
        File f1 = new File(filename);
        File f2 = new File(lz4file);

        byte[] b = new byte[10<<20];
//        LZ4Factory factory = LZ4Factory.safeInstance();

        try(FileInputStream fin = new FileInputStream(f1);
            FileOutputStream out = new FileOutputStream(f2);

        ){
            int decompressedLength = fin.read(b, 0, b.length);

            // compress data
            LZ4Compressor compressor = factory.fastCompressor();

            int maxCompressedLength = compressor.maxCompressedLength(decompressedLength);

            byte[] compressed = new byte[maxCompressedLength];

            int compressedLength = compressor.compress(b, 0, decompressedLength, compressed, 0, maxCompressedLength);

            out.write(compressed, 0, compressedLength);
//			// decompress data
//			// - method 1: when the decompressed length is known
//			LZ4FastDecompressor decompressor = factory.fastDecompressor();
//			byte[] restored = new byte[decompressedLength];
//			int compressedLength2 = decompressor.decompress(compressed, 0, restored, 0, decompressedLength);
//			// compressedLength == compressedLength2
//
//			// - method 2: when the compressed length is known (a little slower)
//			// the destination buffer needs to be over-sized
//			LZ4SafeDecompressor decompressor2 = factory.safeDecompressor();
//			int decompressedLength2 = decompressor2.decompress(compressed, 0, compressedLength, restored, 0);
//
//			if( decompressedLength == decompressedLength2)
//			{
//				System.out.println(String.format("complete! %d:%d", decompressedLength, compressedLength2) );
//			}

        } catch(Exception e) {
            e.printStackTrace();
        }
    }*/

    public void decompressLZ4(String lz4file, String filename) {
        long startTime = System.currentTimeMillis();
        byte[] buf = new byte[2048];
        try {
            LZ4BlockInputStream in = new LZ4BlockInputStream(new FileInputStream(lz4file));
//            BlockLZ4CompressorInputStream in = new BlockLZ4CompressorInputStream(new FileInputStream(lz4file));
            FileOutputStream out = new FileOutputStream(filename);
            int len;
            while((len = in.read(buf)) > 0){
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("LZ4 decompress Run Time: " + (endTime - startTime) + "ms");
    }
}
