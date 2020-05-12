package com.chemistry.elements.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
@StyleSheet("frontend://styles/style.css")
public class MainGui extends HorizontalLayout {

    private TextField textFieldHeight;
    private TextField textFieldWidth;
    private Button button;

    public MainGui() {
//        UI.getCurrent().getPage().addStyleSheet("./style.css");
        textFieldHeight = new TextField("Wprowadź formułę");
        textFieldHeight.addClassName("aaa");
        textFieldWidth = new TextField("Masa atomowa");
        textFieldWidth.addClassName("bbb");
        button = new Button("Oblicz");
        button.addClickListener(clickEvent -> addRectangle());
        button.addClassName("binkul");

        addClassName("jacek");
        add(textFieldHeight, textFieldWidth, button);
    }

    private void addRectangle() {
        textFieldWidth.setValue(textFieldHeight.getValue());
    }
}
