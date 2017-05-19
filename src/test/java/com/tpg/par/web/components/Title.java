package com.tpg.par.web.components;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.tagName;

public class Title extends WebPageComponent {
    public Title(WebDriver webDriver) {
        super(webDriver, tagName("title"));
    }
}
