package stepDefinition;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AmazonPage;

public class AmazonStepDef {

    AmazonPage objAmazon;

    @When("User searches for the Amazon - {string}")
    public void userSearchesForTheAmazon(String product) {
        objAmazon = new AmazonPage();
        objAmazon.searchforProduct(product);
    }

    @Then("Verify the Amazon Results and click on the Product - {string}")
    public void verifyTheAmazonResultsShownForTheProduct(String product) {
        objAmazon = new AmazonPage();
        objAmazon.verifyAmazonResultsAndClickOn(product);
    }

    @When("User clicks Add to Cart for the Product - {string}")
    public void userClicksAddToCartforTheProduct(String product) {
        objAmazon = new AmazonPage();
        objAmazon.clickOnAddtoCart(product);
    }

    @Then("Verify the Product - {string} added to the Cart")
    public void verifyTheProductAddedToTheCart(String product) {
        objAmazon = new AmazonPage();
        objAmazon.verifyProductAddedtoCart(product);
    }
}
