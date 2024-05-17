package com.li.facade;

/**
 * @author LiXL
 * @date 2024/3/11
 */
public class VideoFile {

    private final String filename;

    private final String codecType;

    public VideoFile(String filename) {
        this.filename = filename;
        this.codecType = filename.substring(filename.lastIndexOf(".") + 1);
    }

    public String getFilename() {
        return filename;
    }

    public String getCodecType() {
        return codecType;
    }
}
