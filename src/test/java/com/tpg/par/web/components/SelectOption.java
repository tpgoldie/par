package com.tpg.par.web.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelectOption extends WebPageComponent {
    private WebElement webElement;

    public SelectOption(WebDriver webDriver, By id) {
        super(webDriver, id);

        this.webElement = findElementBy();
    }

    public SelectOption(WebDriver webDriver, By id, WebElement webElement) {
        super(webDriver, id);

        this.webElement = webElement;
    }

    public String getText() {
        return webElement.getText();
    }
}
