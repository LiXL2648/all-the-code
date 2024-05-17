package com.li.factory.factorymethod;

import com.li.factory.factorymethod.logistics.RoadLogistics;
import com.li.factory.factorymethod.logistics.SeaLogistics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author LiXL
 * @date 2024/1/11
 */
public class Demo {

    public static void main(String[] args) {
        while (true) {
            String logisticsType = getLogisticsType();
            if ("road".equals(logisticsType)) {
                new RoadLogistics().planDelivery();
            } else if ("sea".equals(logisticsType)) {
                new SeaLogistics().planDelivery();
            } else {
                break;
            }
        }
    }

    public static String getLogisticsType() {
        System.out.println("请输入运输方式：");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
