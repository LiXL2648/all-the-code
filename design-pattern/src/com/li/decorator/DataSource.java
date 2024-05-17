package com.li.decorator;

/**
 * @author LiXL
 * @date 2024/2/27
 */
public interface DataSource {

    void writeData(String data);

    String readData();
}
