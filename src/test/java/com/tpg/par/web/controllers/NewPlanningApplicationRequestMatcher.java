package com.tpg.par.web.controllers;

import com.tpg.par.service.requests.NewPlanningApplicationRequest;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class NewPlanningApplicationRequestMatcher extends TypeSafeMatcher<NewPlanningApplicationRequest> {
    public static NewPlanningApplicationRequestMatcher matchesNewPlanningApplicationRequest(final NewPlanningApplicationRequest request) {
        return new NewPlanningApplicationRequestMatcher(request);
    }

    private NewPlanningApplicationRequest newPlanningApplicationRequest;

    public NewPlanningApplicationRequestMatcher(NewPlanningApplicationRequest newPlanningApplicationRequest) {
        this.newPlanningApplicationRequest = newPlanningApplicationRequest;
    }

    @Override
    protected boolean matchesSafely(NewPlanningApplicationRequest item) {
        return new EqualsBuilder()
            .append(newPlanningApplicationRequest.getTitle(), item.getTitle())
            .append(newPlanningApplicationRequest.getName(), item.getName())
            .append(newPlanningApplicationRequest.getContactDetails(), item.getContactDetails())
            .append(newPlanningApplicationRequest.getDateOfBirth(), item.getDateOfBirth())
            .append(newPlanningApplicationRequest.getDateReceived(), item.getDateReceived())
            .append(newPlanningApplicationRequest.getSummary(), item.getSummary())
            .isEquals();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(String.format("matches new planning application request", newPlanningApplicationRequest));
    }
}
