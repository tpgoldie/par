package com.tpg.par.web.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class WebPageComponent {
    private final WebDriver webDriver;
    private final By id;

    WebPageComponent(WebDriver webDriver, By id) {
        this.webDriver = webDriver;
        this.id = id;
    }

    public String getText() {
        return findElementById().getText();
    }

    protected WebElement findElementById() {
        return webDriver.findElement(id);
    }
}
