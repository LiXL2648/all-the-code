package com.li.factory.absfactory.factory;

import com.li.factory.absfactory.checkbox.WindowsCheckbox;
import com.li.factory.absfactory.button.Button;
import com.li.factory.absfactory.button.WindowsButton;
import com.li.factory.absfactory.checkbox.Checkbox;

/**
 * @author LiXL
 * @date 2024/1/11
 */
public class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
