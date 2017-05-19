package com.tpg.par.web;

import com.tpg.par.context.ParAppConfigurer;
import com.tpg.par.web.app.ParWebApplication;
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

import static org.hamcrest.CoreMatchers.is;
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
    }

    private void assertTab(String tabId) {
//        indexPage.getTab(tabId);
    }
}
