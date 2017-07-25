package com.example.test;

import com.example.screens.*;
import com.example.session.Session;
import com.jagacy.util.JagacyException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.*;

import java.util.List;


/**
 * Created by upgundecha on 14/10/16.
 */
public class Stepdefs {

    private Session session;
    private HomeScreen homeScreen;
    private THDHomeScreen thdHomeScreen;
    private PhonbookMenuScreen phonbookMenuScreen;
    private PhonbookSearchScreen phonbookSearchScreen;
    private THDMenuScreen thdMenuScreen;
    private THDSearchScreen thdSearchScreen;
    private Scenario scenario;

    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("^I start a new emulator session$")
    public void i_start_a_new_emulator_session() throws Throwable {
        System.setProperty("test.window", "true");
        session = new Session("test");
        session.open();
    }

    @When("^I open phonbook application$")
    public void i_open_phonbook_application() throws Throwable {

        homeScreen = new HomeScreen(session);
        scenario.embed(session.getScreenshot(), "image/png");
        phonbookMenuScreen = homeScreen.openPhonbook();

    }

    @When("^search for faculty name \"([^\"]*)\"$")
    public void search_for_faculty_name(String q) throws Throwable {

        scenario.embed(session.getScreenshot(), "image/png");
        phonbookSearchScreen = phonbookMenuScreen.openFacultyStaffListing();
        phonbookSearchScreen.searchByFirstOrMiddleName(q);

    }

    @Then("^I should see the results matching with my search criteria$")
    public void i_should_see_the_results_matching_with_my_search_criteria(List<String> records) throws Throwable {

        scenario.embed(session.getScreenshot(), "image/png");
        assertEquals(records, phonbookSearchScreen.getResults());

    }

    @After
    public void tearDown() throws JagacyException {
        session.close();
    }

    @Then("^I should not see the results matching with my search criteria$")
    public void iShouldNotSeeTheResultsMatchingWithMySearchCriteria(List<String> records) throws Throwable {
        scenario.embed(session.getScreenshot(), "image/png");
        assertNotEquals(records, phonbookSearchScreen.getResults());
    }

    @When("^I login and type tso$")
    public void iLoginAndTypeTso() throws Throwable {
        thdHomeScreen = new THDHomeScreen(session);
        scenario.embed(session.getScreenshot(), "image/png");
        thdMenuScreen = thdHomeScreen.typetso();

    }

    @And("^Enter my login id$")
    public void enterMyLoginIdAndPassword() throws Throwable {
        scenario.embed(session.getScreenshot(), "image/png");
        thdSearchScreen = thdMenuScreen.typeusername();
    }

    @Then("^I should be able to see login screen$")
    public void iShouldBeAbleToLogin() throws Throwable {
        scenario.embed(session.getScreenshot(), "image/png");
        thdSearchScreen.getResults();
    }
}

