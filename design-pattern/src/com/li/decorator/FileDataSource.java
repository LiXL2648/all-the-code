package com.li.decorator;

import java.io.*;

/**
 * @author LiXL
 * @date 2024/2/27
 */
public class FileDataSource implements DataSource {

    private String fileName;

    public FileDataSource(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void writeData(String data) {
        File file = new File(fileName);
        try(FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readData() {
        File file = new File(fileName);
        byte[] b = new byte[(int) file.length()];
        try(FileInputStream fis = new FileInputStream(file)) {
            fis.read(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(b);
    }
}
