package com.tpg.par.web.components;

import com.tpg.par.domain.PlanningSearchType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;
import java.util.TreeMap;

public class SimpleSearchTab extends WebPageComponent {
    public SimpleSearchTab(WebDriver webDriver) {
        super(webDriver, By.id("simple-search-tab"));
    }

    private void switchToNextTab() {
        WebElement element = findElementById();

        element.sendKeys(Keys.CONTROL +"\t");

        //Switch to current selected tab's content.
        webDriver.switchTo().defaultContent();
    }

    public Map<PlanningSearchType, CheckBox> getCheckBoxes() {
        Map<PlanningSearchType, CheckBox> cbs = new TreeMap<>();

        new PlanningSearchTypeCheckBoxes().getValues().stream()
                .forEach(cb -> cbs.put(cb.getValue(), new CheckBox(webDriver, By.id(cb.getName()))));

        return cbs;
    }
}
