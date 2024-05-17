package com.li.factory.absfactory.app;

import com.li.factory.absfactory.factory.GUIFactory;
import com.li.factory.absfactory.factory.MacOSFactory;
import com.li.factory.absfactory.factory.WindowsFactory;

/**
 * @author LiXL
 * @date 2024/1/11
 */
public class Demo {

    public static void main(String[] args) {
        Application application = configureApplication();
        application.paint();
    }

    public static Application configureApplication() {
        GUIFactory factory;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            factory = new MacOSFactory();
        } else {
            factory = new WindowsFactory();
        }

        return new Application(factory);
    }
}
