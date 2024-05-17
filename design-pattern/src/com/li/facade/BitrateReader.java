package com.li.facade;

/**
 * @author LiXL
 * @date 2024/3/11
 */
public class BitrateReader {

    public static VideoFile read(VideoFile file, Codec codec) {
        System.out.println("BitrateReader: 读取文件");
        return file;
    }

    public static VideoFile convert(VideoFile buffer, Codec codec) {
        System.out.println("BitrateReader: 写出文件");
        return buffer;
    }
}
