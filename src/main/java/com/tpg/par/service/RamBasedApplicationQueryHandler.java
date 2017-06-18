package com.tpg.par.service;

import com.tpg.par.domain.Address;
import com.tpg.par.domain.DecisionStatus;
import com.tpg.par.domain.PlanningApplication;
import com.tpg.par.domain.builders.PlanningApplicationBuilder;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.tpg.par.domain.DecisionStatus.Decided;
import static java.util.Arrays.asList;

@Service
public class RamBasedApplicationQueryHandler {
    private Address.Builder addressBuilder = new Address.Builder();
    private PlanningApplicationBuilder planningApplicationBuilder = new PlanningApplicationBuilder();

    public List<PlanningApplication> findApplications(SimpleSearchRequest searchRequest) {
        return buildSearchResults();
    }


    private List<PlanningApplication> buildSearchResults() {
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

    private PlanningApplication buildSearchResult(String referenceNumber, String summary,
                                           ZonedDateTime dateReceived, ZonedDateTime dateValidated,
                                           Address address, DecisionStatus decisionStatus) {

        return planningApplicationBuilder
                .referenceNumber(referenceNumber)
                .summary(summary)
                .address(address)
                .dateReceived(dateReceived)
                .dateValidated(dateValidated)
                .decisionStatus(decisionStatus)
                .build();
    }
}
