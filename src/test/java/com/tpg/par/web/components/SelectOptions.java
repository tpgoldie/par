package com.tpg.par.web.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SelectOptions extends WebPageComponent {
    private final Select select;

    public SelectOptions(WebDriver webDriver, By xPath) {
        super(webDriver, xPath);

        select = new Select(findElementBy());
    }

    public void select(String value) {
        select.selectByVisibleText(value);
    }

    public List<SelectOption> getOptions() {
        return select.getOptions().stream()
                .map(o -> new SelectOption(webDriver, By.id(""), o))
                    .collect(toList());
    }
}
