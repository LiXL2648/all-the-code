package com.li.facade;

import java.io.File;

/**
 * @author LiXL
 * @date 2024/3/11
 */
public class VideoConversionFacade {
    public File convertVideo(String fileName, String format) {
        System.out.println("视频转换器: 转换开始");
        // 获取视频文件
        VideoFile file = new VideoFile(fileName);
        // 获取源文件的原格式
        Codec sourceCodec = CodecFactory.extract(file);
        Codec destinationCodec;
        // 判断文件的转换格式
        if (format.equals("mp4")) {
            destinationCodec = new MPEG4CompressionCodec();
        } else {
            destinationCodec = new OggCompressionCodec();
        }
        // 读取文件
        VideoFile buffer = BitrateReader.read(file, sourceCodec);
        // 开始转换
        VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
        // 修复文件
        File result = (new AudioMixer()).fix(intermediateResult);
        System.out.println("视频转换器: 转换完成");
        return result;
    }
}
