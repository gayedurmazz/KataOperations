package org.example.ui.components;

import com.vaadin.ui.Button;

public class MyButton extends Button {
    String btnCaption;

    public MyButton(String btnCaption) {
        this.btnCaption = btnCaption;
        setCaption(btnCaption);
        setWidth(100, Unit.PIXELS);
        setHeight(50, Unit.PIXELS);
    }
}
