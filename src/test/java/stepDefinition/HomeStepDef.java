package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.GoogleHome;
import pages.HomePage;

public class HomeStepDef {

    HomePage objHomePage;

    @Given("User navigates to {string}")
    public void user_navigates_to(String url) {
        objHomePage = new HomePage();
        url = objHomePage.readFromConfig(url);
        objHomePage.navigateTo(url);
    }

}
