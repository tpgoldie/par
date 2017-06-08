package com.tpg.par.web.components;

import com.tpg.par.domain.ApplicationType;
import com.tpg.par.domain.DecisionStatus;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;
import java.util.TreeMap;

import static org.openqa.selenium.By.id;

public class SimpleSearchTab extends WebPageComponent {
    public SimpleSearchTab(WebDriver webDriver) {
        super(webDriver, id("simple-search-tab"));
    }

    private void switchToNextTab() {
        WebElement element = findElementBy();

        element.sendKeys(Keys.CONTROL +"\t");

        //Switch to current selected tab's content.
        webDriver.switchTo().defaultContent();
    }

    public Map<ApplicationType, CheckBox> getSearchTypeCheckBoxes() {
        Map<ApplicationType, CheckBox> cbs = new TreeMap<>();

        new ApplicationTypeCheckBoxes().getValues().stream()
            .forEach(cb -> cbs.put(cb.getApplicationType(), new CheckBox(webDriver, id(cb.getName()))));

        return cbs;
    }

    public Map<DecisionStatus, SelectOption> getDecisionStatusSelectOptions() {
        Map<DecisionStatus, SelectOption> options = new TreeMap<>();

        new StatusTypeSelectOptions().getValues().stream()
            .forEach(so -> addOption(options, so));

        return options;
    }

    private void addOption(Map<DecisionStatus, SelectOption> options, DecisionStatusSelectOption selectOption) {
        options.put(selectOption.getDecisionStatus(), new SelectOption(webDriver, id(selectOption.getLabel())));
    }

    public InputField getSearchInput() {
        return new InputField(webDriver, id("search-input"));
    }

    public Button getSearchButton() {
        return new Button(webDriver, id("search"));
    }
}
