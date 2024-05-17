package com.li.factory.absfactory.app;

import com.li.factory.absfactory.factory.GUIFactory;
import com.li.factory.absfactory.button.Button;
import com.li.factory.absfactory.checkbox.Checkbox;

/**
 * @author LiXL
 * @date 2024/1/11
 */
public class Application {

    private final Button button;
    private final Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}
