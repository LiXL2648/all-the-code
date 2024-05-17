package com.li.facade;

/**
 * @author LiXL
 * @date 2024/3/11
 */
public class CodecFactory {

    public static Codec extract(VideoFile videoFile) {
        if ("mp4".equals(videoFile.getCodecType())) {
            System.out.println("CodecFactory: 提取MP4格式的音频...");
            return new MPEG4CompressionCodec();
        } else {
            System.out.println("CodecFactory: 提取OGG格式的音频");
            return new OggCompressionCodec();
        }
    }
}
