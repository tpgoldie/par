package com.tpg.par.web;

import com.tpg.par.context.ParAppConfigurer;
import com.tpg.par.domain.SearchType;
import com.tpg.par.domain.StatusType;
import com.tpg.par.web.app.ParWebApplication;
import com.tpg.par.web.components.*;
import com.tpg.par.web.context.ParWebApplicationInitializer;
import com.tpg.par.web.context.ParWebConfigurer;
import com.tpg.par.web.controllers.SearchController;
import com.tpg.par.web.pages.IndexPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.openqa.selenium.By.tagName;

@RunWith(SpringRunner.class)
@WebMvcTest(SearchController.class)
@ContextConfiguration(classes = { ParWebApplication.class, ParAppConfigurer.class, ParWebApplicationInitializer.class, ParWebConfigurer.class})
@ActiveProfiles(profiles = {"dev"})
public class IndexPageWebTest {
    @Autowired
    private WebDriver webDriver;

    private IndexPage indexPage;

    @Before
    public void setUp() {
        indexPage = new IndexPage(webDriver, "/par/");
    }

    @Test
    public void loadIndexPage() {
        assertThat(indexPage, hasProperty("title", is("Public Access Register")));
        assertThat(indexPage.getHeading(tagName("h1")), is("Public Access Register"));
        assertThat(indexPage.getHeading(tagName("h2")), is("Planning >> Simple Search"));

        String searchSummary = "Search for planning applications, appeals and enforcements by keyword, application reference, postcode or by a single line of an address.";
        assertThat(indexPage.getText("search-summary"), is(searchSummary));

        assertSimpleSearchTab();
    }

    private void assertSimpleSearchTab() {
        SimpleSearchTab tab = indexPage.getSimpleSearchTab();
        assertSearchTypes(tab);
        assertStatusTypes(tab);
        assertSearchInput(tab);
    }

    private void assertStatusTypes(SimpleSearchTab tab) {
        Map<StatusType, SelectOption> selectOptions = tab.getStatusTypeSelectOptions();

        assertThat(selectOptions.size(), is(3));

        new StatusTypeSelectOptions().getValues().stream()
                .forEach(so -> assertSelectOption(selectOptions, so));
    }

    private void assertSelectOption(Map<StatusType, SelectOption> selectOptions, StatusTypeSelectOption selectOption) {
        assertThat(selectOption.getName(), is(selectOptions.get(selectOption.getStatusType()).getText()));
    }

    private void assertSearchTypes(SimpleSearchTab tab) {
        Map<SearchType, CheckBox> checkBoxes = tab.getSearchTypeCheckBoxes();

        assertThat(checkBoxes.size(), is(3));

        new SearchTypeCheckBoxes().getValues().stream()
            .forEach(pst -> assertCheckBox(checkBoxes, pst));
    }

    private void assertCheckBox(Map<SearchType, CheckBox> checkBoxes, SearchTypeCheckBox pst) {
        String actual = pst.getName().toUpperCase();
        String expected = checkBoxes.get(pst.getSearchType()).getText().toUpperCase();

        assertThat(actual, is(expected));
    }

    private void assertSearchInput(SimpleSearchTab tab) {
        InputField inputField = tab.getSearchInput();
        assertThat(inputField, is(notNullValue()));
        assertThat(inputField, hasProperty("nameAttribute", is("searchQuery")));

        Button button = tab.getSearchButton();
        assertThat(button, is(notNullValue()));
    }
}
