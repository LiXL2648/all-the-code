package com.li.facade;

import java.io.File;

/**
 * @author LiXL
 * @date 2024/3/11
 */
public class Demo {
    public static void main(String[] args) {
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4Video = converter.convertVideo("youtubevideo.ogg", "mp4");
    }
}
