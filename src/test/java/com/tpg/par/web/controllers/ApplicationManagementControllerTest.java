package com.tpg.par.web.controllers;

import com.tpg.par.UniqueIdGeneration;
import com.tpg.par.domain.*;
import com.tpg.par.service.PlanningApplicationsService;
import com.tpg.par.service.Success;
import com.tpg.par.service.requests.NewPlanningApplicationRequest;
import com.tpg.par.util.DateTimeFormatting;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;

import java.time.ZonedDateTime;

import static com.tpg.par.domain.Title.Mr;
import static java.util.Locale.UK;
import static java.util.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplicationManagementController.class)
public class ApplicationManagementControllerTest extends ControllerTest implements UniqueIdGeneration, ZonedDateTimeFixture,
        DateTimeFormatting, AddressFixture, ContactDetailsFixture, PlanningApplicationFixture {

    @MockBean
    private PlanningApplicationsService planningApplicationsService;

    @MockBean(name = "messageSource")
    private MessageSource messageSource;

    @Test
    public void handleCreateNewApplicationRequest_newApplicationRequest_newApplicationSaved() throws Exception {

        Address address = buildAddress("114 St Johnston Road", "Croydon", "Surrey", "UK", "CR3 3QQ");
        NewPlanningApplication newPlanningApplication = buildNewPlanningApplication(Mr.name(), "John", "Doe", buildDate(1977, 4, 23, 0),
                address, "012345123123", "jd@ggmmaaiill.com", ZonedDateTime.now(), newId(), "Erection of dormer extension in rear roof slope");

        NewPlanningApplicationRequest request = buildNewPlanningApplicationRequestFrom(newPlanningApplication);

        Success<NewPlanningApplication> success = new Success<>(of(newPlanningApplication), "New planning application saved");

        when(planningApplicationsService.savePlanningApplication(any(NewPlanningApplicationRequest.class))).thenReturn(success);

        mockMvc.perform(post("/par/planningApplications")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("title", newPlanningApplication.getTitle().name())
            .param("firstName", newPlanningApplication.getName().getFirstName())
            .param("surname", newPlanningApplication.getName().getSurname())
            .param("emailAddress", newPlanningApplication.getContactDetails().getEmailAddress())
            .param("confirmEmailAddress", newPlanningApplication.getContactDetails().getEmailAddress())
            .param("contactNumber", newPlanningApplication.getContactDetails().getContactNumber())
            .param("summary", newPlanningApplication.getSummary())
            .param("lineOne", newPlanningApplication.getContactDetails().getAddress().getLineOne())
            .param("city", newPlanningApplication.getContactDetails().getAddress().getCity())
            .param("region", newPlanningApplication.getContactDetails().getAddress().getRegion())
            .param("country", newPlanningApplication.getContactDetails().getAddress().getCountry())
            .param("postCode", newPlanningApplication.getContactDetails().getAddress().getPostCode())
            .param("dateReceived", newPlanningApplication.getFormattedDateReceived())
            .param("dateOfBirth", newPlanningApplication.getFormattedDateOfBirth())
            .locale(UK))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.newApplication.title",
                is(newPlanningApplication.getTitle())))
            .andExpect(jsonPath("$.newApplication.name.firstName",
                is(newPlanningApplication.getName().getFirstName())))
            .andExpect(jsonPath("$.newApplication.name.surname",
                is(newPlanningApplication.getName().getSurname())))
            .andExpect(jsonPath("$.newApplication.contactDetails.emailAddress",
                is(newPlanningApplication.getContactDetails().getEmailAddress())))
            .andExpect(jsonPath("$.newApplication.contactDetails.contactNumber",
                is(newPlanningApplication.getContactDetails().getContactNumber())))
            .andExpect(jsonPath("$.newApplication.contactDetails.address.lineOne",
                is(newPlanningApplication.getContactDetails().getAddress().getLineOne())))
            .andExpect(jsonPath("$.newApplication.contactDetails.address.city",
                is(newPlanningApplication.getContactDetails().getAddress().getCity())))
            .andExpect(jsonPath("$.newApplication.contactDetails.address.region",
                is(newPlanningApplication.getContactDetails().getAddress().getRegion())))
            .andExpect(jsonPath("$.newApplication.contactDetails.address.country",
                is(newPlanningApplication.getContactDetails().getAddress().getCountry())))
            .andExpect(jsonPath("$.newApplication.contactDetails.address.postCode",
                is(newPlanningApplication.getContactDetails().getAddress().getPostCode())))
            .andExpect(jsonPath("$.newApplication.referenceNumber",
                is(newPlanningApplication.getReferenceNumber())))
            .andExpect(jsonPath("$.newApplication.dateReceived",
                is(newPlanningApplication.getFormattedDateReceived())));

        ArgumentCaptor<NewPlanningApplicationRequest> requestArgumentCaptor = ArgumentCaptor.forClass(NewPlanningApplicationRequest.class);

        verify(planningApplicationsService).savePlanningApplication(requestArgumentCaptor.capture());
        NewPlanningApplicationRequest actual = requestArgumentCaptor.getValue();

        assertThat(actual, NewPlanningApplicationRequestMatcher.matchesNewPlanningApplicationRequest(request));
    }

    private NewPlanningApplicationRequest buildNewPlanningApplicationRequestFrom(NewPlanningApplication newPlanningApplication) {
        NewPlanningApplicationRequest.Builder builder = new NewPlanningApplicationRequest.Builder();
        builder.title(newPlanningApplication.getTitle());
        builder.name(newPlanningApplication.getName());
        builder.contactDetails(newPlanningApplication.getContactDetails());
        builder.dateOfBirth(newPlanningApplication.getDateOfBirth());
        builder.dateReceived(newPlanningApplication.getDateReceived());
        builder.summary(newPlanningApplication.getSummary());

        return builder.build();
    }

    private NewPlanningApplication buildNewPlanningApplication(String title, String firstName, String surname,
                                                               ZonedDateTime dateOfBirth, Address address, String contactNumber, String emailAddress,
                                                               ZonedDateTime dateReceived, String referenceNumber, String summary) {
        NewPlanningApplication.Builder builder = new NewPlanningApplication.Builder();

        ContactDetails contactDetails = buildContactDetails(address, contactNumber, emailAddress);

        builder.title(title)
                .name(firstName, surname)
                .contactDetails(contactDetails)
                .dateOfBirth(IsoZonedDateTimeFormat(dateOfBirth))
                .dateReceived(IsoZonedDateTimeFormat(dateReceived))
                .referenceNumber(referenceNumber)
                .summary(summary);

        return builder.build();
    }
}
