package pages;

import org.openqa.selenium.By;
import utils.BasePage;

public class GoogleHome extends BasePage {

    public GoogleHome(){
        loadJSONPageLocators(this.getClass().getSimpleName());
    }

    public void clickOnSearch(String arg){
        try {
            sendKeysTo("google_searchEditbox", arg);
            Thread.sleep(3000);
            performClick("google_searchBtn");
            Thread.sleep(4000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void verifySearchResults(String product) {
        String totalResults = findElement("google_searchResultStats").getText();
        System.out.println(totalResults);
    }
}

