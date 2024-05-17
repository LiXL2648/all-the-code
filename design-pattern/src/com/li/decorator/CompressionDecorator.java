package com.li.decorator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * @author LiXL
 * @date 2024/2/28
 */
public class CompressionDecorator extends DataSourceDecorator {

    private int compLevel = 6;

    public CompressionDecorator(DataSource dataSource) {
        super(dataSource);
    }

    public int getCompressionLevel() {
        return this.compLevel;
    }

    public void setCompressionLevel(int compLevel) {
        this.compLevel = compLevel;
    }

    @Override
    public void writeData(String data) {
        String compress = compress(data);
        super.writeData(compress);
    }

    @Override
    public String readData() {
        return decompress(super.readData());
    }

    private String compress(String data) {
        byte[] bytes = data.getBytes();
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(512);
            DeflaterOutputStream dos = new DeflaterOutputStream(bos, new Deflater(compLevel));
            dos.write(bytes);
            dos.close();
            bos.close();
            return Base64.getEncoder().encodeToString(bos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String decompress(String data) {
        byte[] bytes = Base64.getDecoder().decode(data);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            InflaterInputStream iis = new InflaterInputStream(bis);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(512);
            int b;
            while ((b = iis.read()) != -1) {
                bos.write(b);
            }
            bis.close();
            iis.close();
            bos.close();
            return bos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
