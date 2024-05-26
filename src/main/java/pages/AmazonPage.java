package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import utils.BasePage;

import java.util.List;

public class AmazonPage extends BasePage {

    public static String cartValue;

    public AmazonPage() {
        loadJSONPageLocators(this.getClass().getSimpleName());
    }


    public void searchforProduct(String product) {
        try {
            String productHint = product.substring(0, product.indexOf("(")).trim();
            String searchTerm = productHint.replaceAll("[^a-zA-Z ]", "").trim();
            sendKeysTo("amazonHome_searchEditbox", searchTerm);
            boolean flag = false;

            List<WebElement> searchList = findElements("amazonHome_searchList");

            for (int i = 0; i < searchList.size(); i++) {
                Actions actions = new Actions(driver);
                actions.moveToElement(findElement("amazonHome_searchEditbox")).sendKeys(Keys.DOWN).build().perform();

                String currentSearchItemText = javascriptExecutorGetText(findElement("amazonHome_searchEditbox"));

                if (currentSearchItemText.equalsIgnoreCase(productHint)) {
                    findElement("amazonHome_searchBtn").click();
                    flag = true;
                    break;
                }
            }

            if (flag == false) {
                System.out.println("Product: " + product + " not found in the Search List");
            }

            Assert.assertTrue(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyAmazonResultsAndClickOn(String product) {
        try {
            List<WebElement> productNameList = findElements("amazonsearch_productListText");
            List<WebElement> productList = findElements("amazonsearch_productList");

            for (int i = 0; i < productNameList.size(); i++) {
                WebElement webElement = productNameList.get(i);
                Actions actions = new Actions(driver);
                actions.moveToElement(webElement).build().perform();

                String currentProductItemText = webElement.getText();

                if (currentProductItemText.equalsIgnoreCase(product)) {
                    productList.get(i).click();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickOnAddtoCart(String product) {
        try {
            verifyAndSwitchToWindowByTitle(product);

            String priceSymbol = getTextValue(findElement("amazonProduct_priceSymbol"));
            String priceValue = getTextValue(findElement("amazonProduct_priceValue"));

            cartValue = priceSymbol + priceValue;
            System.out.println("Price: " + cartValue);
            findElement("amazonProduct_addtoCartBtn").click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void verifyProductAddedtoCart(String product) {
        try {
            boolean flag = false;

            if (findElements("amazonProduct_addedtoCartSuccess").size() > 0) {
                flag = true;
                String value = getTextValue(findElement("amazonProduct_addedtoCartValue"));
                Assert.assertEquals(cartValue, value);
            } else {
                flag = false;
            }
            Assert.assertTrue(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
