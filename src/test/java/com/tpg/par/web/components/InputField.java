package com.tpg.par.web.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InputField extends WebPageComponent {
    InputField(WebDriver webDriver, By by) {
        super(webDriver, by);
    }

    public String getNameAttribute() {
        return findElementBy().getAttribute("name");
    }
}
