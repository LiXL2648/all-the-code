package com.li.factory.absfactory.button;

/**
 * @author LiXL
 * @date 2024/1/11
 */
public class WindowsButton implements Button {

    @Override
    public void paint() {
        System.out.println("You have created WindowsButton.");
    }
}
