package com.tpg.par.web.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class WebPageComponent {
    protected final WebDriver webDriver;
    private final By by;

    WebPageComponent(WebDriver webDriver, By by) {
        this.webDriver = webDriver;
        this.by = by;
    }

    public String getText() {
        return findElementBy().getText();
    }

    protected WebElement findElementBy() {
        return webDriver.findElement(by);
    }
}
