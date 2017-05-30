package com.tpg.par.web.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitWebElement;

public class CheckBox extends WebPageComponent {
    public CheckBox(WebDriver webDriver, By id) {
        super(webDriver, id);
    }

    @Override
    public String getText() {
        HtmlUnitWebElement element = (HtmlUnitWebElement) findElementBy();

        return element.getAttribute("id");
    }

    @Override
    public String toString() {
        return getText();
    }
}
