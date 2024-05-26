package stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.GoogleHome;

public class GoogleStepDef {

    GoogleHome objGoogleHome;

    @When("User searches for the Product - {string}")
    public void userSearchesForTheProduct(String product) {
        objGoogleHome = new GoogleHome();
        objGoogleHome.clickOnSearch(product);
    }


    @Then("Verify the result shows for the Product - {string}")
    public void verifyTheResultShowsForTheProduct(String product) {
        objGoogleHome = new GoogleHome();
        objGoogleHome.verifySearchResults(product);
    }
}
