package com.tpg.par.web.pages;

import com.tpg.par.web.components.Heading;
import com.tpg.par.web.components.SimpleSearchTab;
import com.tpg.par.web.components.Title;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndexPage {
    private WebDriver webDriver;

    public IndexPage(WebDriver webDriver, String uri) {
        this.webDriver = webDriver;
        this.webDriver.get(uri);
    }

    public String getTitle() {
        return new Title(webDriver).getText();
    }

    public String getHeading(By by) {
        return new Heading(webDriver, by).getText();
    }

    public String getText(String id) {
        return webDriver.findElement(By.id(id)).getText();
    }

    public SimpleSearchTab getSimpleSearchTab() {
        return new SimpleSearchTab(webDriver);
    }
}
