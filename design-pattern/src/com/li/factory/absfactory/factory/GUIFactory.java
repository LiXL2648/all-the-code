package com.li.factory.absfactory.factory;

import com.li.factory.absfactory.button.Button;
import com.li.factory.absfactory.checkbox.Checkbox;

/**
 * @author LiXL
 * @date 2024/1/11
 */
public interface GUIFactory {

    Button createButton();

    Checkbox createCheckbox();
}
