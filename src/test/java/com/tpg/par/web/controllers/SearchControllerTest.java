package com.tpg.par.web.controllers;

import com.tpg.par.domain.*;
import com.tpg.par.domain.builders.AddressBuilder;
import com.tpg.par.domain.builders.SearchResultBuilder;
import com.tpg.par.web.components.SearchTypeCheckBox;
import com.tpg.par.web.components.StatusTypeSelectOption;
import com.tpg.par.web.request.SimpleSearchWebRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.web.servlet.ResultActions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.tpg.par.domain.DecisionStatus.Decided;
import static com.tpg.par.domain.SearchFor.Applications;
import static java.util.Arrays.asList;
import static java.util.Locale.UK;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SearchController.class)
public class SearchControllerTest extends ControllerTest {
    private AddressBuilder addressBuilder;
    private SearchResultBuilder searchResultBuilder;

    @MockBean(name = "messageSource")
    private MessageSource messageSource;

    @MockBean(name = "healthIndicator")
    private HealthIndicator healthIndicator;

    @Before
    public void setUp() {
        addressBuilder = new AddressBuilder();

        searchResultBuilder = new SearchResultBuilder();
    }

    @Test
    public void handleIndexRequest_indexRequest_indexViewReturned() throws Exception {
        Map<String, String> messages = setUpMessages();

        mockMvc.perform(get("/par/")
            .contentType(TEXT_HTML)
                .locale(UK))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("index"))
        .andExpect(model().attribute("title", messages.get("index.title")))
        .andExpect(model().attribute("welcome", messages.get("index.h1")))
        .andExpect(model().attribute("simpleSearchSubTitle", messages.get("index.h2")))
        .andExpect(model().attribute("searchSummary", messages.get("index.searchSummary")))
        .andExpect(model().attribute("searchButtonText", messages.get("search.button.text")));

        String[] emptyArray = new String[0];

        messages.entrySet().forEach(es -> verify(messageSource).getMessage(es.getKey(), emptyArray, UK));
    }

    private Map<String, String> setUpMessages() {
        Map<String, String> messages = new HashMap<>();
        messages.put("index.title", "Public Access Register");
        messages.put("index.h1", "Welcome to the Public Access Register");
        messages.put("index.h2", "Planning >> Simple Search");
        messages.put("index.searchSummary", "Search for planning applications, appeals and enforcements by keyword, application reference, postcode or by a single line of an address.");
        messages.put("search.button.text", "Search");
        messages.put("footer.info", "2016 Public Access Register");

        String[] emptyArray = new String[0];

        messages.entrySet().forEach(es -> when(messageSource.getMessage(es.getKey(), emptyArray, UK)).thenReturn(es.getValue()));

        return messages;
    }

    @Test
    public void handleIndexRequest_indexRequest_modelIsPopulated() throws Exception {
        Map<String, String> messages = setUpMessages();

        List<SearchTypeCheckBox> searchTypeCheckBoxes = Stream.of(SearchType.values())
            .map(SearchTypeCheckBox::new).collect(toList());

        List<StatusTypeSelectOption> statusTypeSelectOptions = Stream.of(StatusType.values())
            .map(StatusTypeSelectOption::new).collect(toList());

        mockMvc.perform(get("/par/")
            .contentType(TEXT_HTML)
                .locale(UK))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("index"))
        .andExpect(model().attribute("searchTypes", searchTypeCheckBoxes))
        .andExpect(model().attribute("statusTypes", statusTypeSelectOptions))
        .andExpect(model().attribute("searchButtonText", "Search"));

        String[] emptyArray = new String[0];

        messages.entrySet().forEach(es -> verify(messageSource).getMessage(es.getKey(), emptyArray, UK));
    }

    @Test
    public void handleSimpleSearchRequest_simpleSearchRequest_searchResultsReturned() throws Exception {
        String searchTerm = "CR0 6DG";
        SimpleSearchWebRequest simpleSearchWebRequest = new SimpleSearchWebRequest(Applications.name(), searchTerm, Decided.name());
        List<SearchResult> searchResults = buildSearchResults();

        when(applicationQueryService.findApplications(any(SearchRequest.class))).thenReturn(searchResults);

        ResultActions output = mockMvc.perform(post("/par/search")
                .param("searchFor", simpleSearchWebRequest.getSearchFor())
                .param("searchTerm", simpleSearchWebRequest.getSearchTerm())
                .param("decisionStatus", simpleSearchWebRequest.getDecisionStatus())
                .contentType(APPLICATION_FORM_URLENCODED))
                .andDo(print())
                .andExpect(status().isOk());

        List<Integer> indices = asList(0, 1);

        indices.stream().forEach(i -> {
            try {
                output
                    .andDo(print())
                    .andExpect(jsonPath(String.format("$.values[%d].summary", i), is(searchResults.get(i).getSummary())))
                    .andExpect(jsonPath(String.format("$.values[%d].referenceNumber", i), is(searchResults.get(i).getReferenceNumber())))
                    .andExpect(jsonPath(String.format("$.values[%d].formattedDateReceived", i), is(searchResults.get(i).getFormattedDateReceived())))
                    .andExpect(jsonPath(String.format("$.values[%d].formattedDateValidated", i), is(searchResults.get(i).getFormattedDateValidated())));
            }
            catch (Exception e) {
                // nothing to do
            }
        });

        ArgumentCaptor<SearchRequest> searchRequestArgumentCaptor = ArgumentCaptor.forClass(SearchRequest.class);

        verify(applicationQueryService).findApplications(searchRequestArgumentCaptor.capture());

        SearchRequest actual = searchRequestArgumentCaptor.getValue();
        assertThat(actual, hasProperty("searchFor", is(Applications)));
        assertThat(actual, hasProperty("searchTerm", is(searchTerm)));
        assertThat(actual, hasProperty("decisionStatus", is(Decided)));
    }

    private List<SearchResult> buildSearchResults() {
        return asList(
                buildSearchResult("14/01860/LP",
                        "Erection of dormer extension in rear roof slope and rooflights in front roof slope",
                        buildDate(2014, 5, 8, 9),
                        buildDate(2014, 5, 12, 17),
                    buildAddress("580 Davidson Road", "Croydon", "Surrey", "United Kingdom", "CR0 6DG"),
                    Decided),

                buildSearchResult("10/02094/LP",
                        "Alterations and use of garage at rear as habitable room",
                        buildDate(2010, 6, 24, 9),
                        buildDate(2014, 6, 30, 17),
                    buildAddress("542 Davidson Road", "Croydon", "Surrey", "United Kingdom", "CR0 6DG"),
                    Decided)
                );
    }

    private ZonedDateTime buildDate(int year, int month, int date, int hour) {
        return ZonedDateTime.of(year, month, date, hour, 0, 0, 0, ZoneId.of("Europe/London"));
    }

    private Address buildAddress(String lineOne, String city, String region, String country, String postCode) {
        return addressBuilder
            .lineOne(lineOne)
            .city(city)
            .region(region)
            .country(country)
            .postCode(postCode)
            .build();
    }

    private SearchResult buildSearchResult(String referenceNumber, String summary,
                                           ZonedDateTime dateReceived, ZonedDateTime dateValidated,
                                           Address address, DecisionStatus decisionStatus) {

        return searchResultBuilder
                .referenceNumber(referenceNumber)
                .summary(summary)
                .address(address)
                .dateReceived(dateReceived)
                .dateValidated(dateValidated)
                .decisionStatus(decisionStatus)
                .build();
    }
 }
