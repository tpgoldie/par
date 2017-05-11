package com.tpg.par.web.controllers;

import com.tpg.par.domain.*;
import com.tpg.par.web.request.SimpleSearchRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.ResultActions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.tpg.par.domain.DecisionStatus.Decided;
import static com.tpg.par.domain.SearchFor.Applications;
import static java.util.Arrays.asList;
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

    @Before
    public void setUp() {
        addressBuilder = new AddressBuilder();

        searchResultBuilder = new SearchResultBuilder();
    }

    @Test
    public void handleIndexRequest_indexRequest_indexViewReturned() throws Exception {
        mockMvc.perform(get("/par/")
                .contentType(TEXT_HTML))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("index"));
    }

    @Test
    public void handleSimpleSearchRequest_simpleSearchRequest_searchResultsReturned() throws Exception {
        String searchTerm = "CR0 6DG";
        SimpleSearchRequest simpleSearchRequest = new SimpleSearchRequest(Applications.name(), searchTerm, Decided.name());
        List<SearchResult> searchResults = buildSearchResults();

        when(applicationQueryService.findApplications(any(SearchRequest.class))).thenReturn(searchResults);

        ResultActions output = mockMvc.perform(post("/par/search")
                .param("searchFor", simpleSearchRequest.getSearchFor())
                .param("searchTerm", simpleSearchRequest.getSearchTerm())
                .param("decisionStatus", simpleSearchRequest.getDecisionStatus())
                .contentType(APPLICATION_FORM_URLENCODED))
                .andDo(print())
                .andExpect(status().isOk());

        List<Integer> indices = asList(0, 1);

        indices.stream().forEach(i -> {
            try {
                output
                    .andExpect(jsonPath(String.format("$[%d].summary", i), is(searchResults.get(i).getSummary())))
                    .andExpect(jsonPath(String.format("$[%d].referenceNumber", i), is(searchResults.get(i).getReferenceNumber())))
                    .andExpect(jsonPath(String.format("$[%d].formattedDateReceived", i), is(searchResults.get(i).getFormattedDateReceived())))
                    .andExpect(jsonPath(String.format("$[%d].formattedDateValidated", i), is(searchResults.get(i).getFormattedDateValidated())));
            } catch (Exception e) {
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
