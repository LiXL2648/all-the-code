package com.li.facade;

import java.io.File;

/**
 * @author LiXL
 * @date 2024/3/11
 */
public class AudioMixer {

    public File fix(VideoFile result){
        System.out.println("AudioMixer: 修复音频...");
        return new File("tmp");
    }
}
