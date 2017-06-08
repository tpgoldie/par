package com.tpg.par.web;

import com.tpg.par.context.ParAppConfigurer;
import com.tpg.par.domain.ApplicationType;
import com.tpg.par.domain.DecisionStatus;
import com.tpg.par.es.repositories.PlanningApplicationsQueryRepository;
import com.tpg.par.web.app.ParWebApplication;
import com.tpg.par.web.components.*;
import com.tpg.par.web.context.ParWebApplicationInitializer;
import com.tpg.par.web.context.ParWebConfigurer;
import com.tpg.par.web.controllers.SimpleSearchController;
import com.tpg.par.web.pages.IndexPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
@WebMvcTest(SimpleSearchController.class)
@ContextConfiguration(classes = { ParWebApplication.class, ParAppConfigurer.class, ParWebApplicationInitializer.class, ParWebConfigurer.class})
@ActiveProfiles(profiles = {"dev"})
public class IndexPageWebTest {
    @MockBean
    private PlanningApplicationsQueryRepository planningApplicationsQueryRepository;

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

        assertApplicationTypes(tab);
        assertDecisionStatuses(tab);
        assertSearchInput(tab);
    }

    private void assertDecisionStatuses(SimpleSearchTab tab) {
        Map<DecisionStatus, SelectOption> selectOptions = tab.getDecisionStatusSelectOptions();

        assertThat(selectOptions.size(), is(3));

        new StatusTypeSelectOptions().getValues().stream()
                .forEach(so -> assertSelectOption(selectOptions, so));
    }

    private void assertSelectOption(Map<DecisionStatus, SelectOption> selectOptions, DecisionStatusSelectOption selectOption) {
        assertThat(selectOption.getName(), is(selectOptions.get(selectOption.getDecisionStatus()).getText()));
    }

    private void assertApplicationTypes(SimpleSearchTab tab) {
        Map<ApplicationType, CheckBox> checkBoxes = tab.getSearchTypeCheckBoxes();

        assertThat(checkBoxes.size(), is(3));

        new ApplicationTypeCheckBoxes().getValues().stream()
            .forEach(pst -> assertCheckBox(checkBoxes, pst));
    }

    private void assertCheckBox(Map<ApplicationType, CheckBox> checkBoxes, ApplicationTypeCheckBox pst) {
        String actual = pst.getName().toUpperCase();
        String expected = checkBoxes.get(pst.getApplicationType()).getText().toUpperCase();

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
