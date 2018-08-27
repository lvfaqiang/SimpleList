package com.fq.kotlin;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NioBioJava
 *
 * @author lvfq
 * @date 2018/8/26 下午12:17
 * @mainFunction :
 */
public class NioBioJava {

    public static void main(String[] args) {

    }


    public static void copyFileBio(File file, File newFile) {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(newFile);

            byte[] bytes = new byte[1024];
            int byteRead = 0;
            while ((byteRead = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, byteRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(inputStream, outputStream);
        }
    }

    public static void copyFileNio(File file, File newFile) {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(file).getChannel();
            outputChannel = new FileOutputStream(newFile).getChannel();
//            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//            while (true) {
//                byteBuffer.clear();
//                if (inputChannel.read(byteBuffer) < 0) {
//                    break;
//                }
//                outputChannel.write(byteBuffer);
//            }
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(inputChannel, outputChannel);
        }

    }

    public static void close(Closeable... closeables) {
        for (Closeable cloaseable : closeables) {
            if (cloaseable != null) {
                try {
                    cloaseable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
