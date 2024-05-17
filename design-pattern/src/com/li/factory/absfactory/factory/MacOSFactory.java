package com.li.factory.absfactory.factory;

import com.li.factory.absfactory.button.Button;
import com.li.factory.absfactory.button.MacOSButton;
import com.li.factory.absfactory.checkbox.Checkbox;
import com.li.factory.absfactory.checkbox.MacOSCheckbox;

/**
 * @author LiXL
 * @date 2024/1/11
 */
public class MacOSFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
