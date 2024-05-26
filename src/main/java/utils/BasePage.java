package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class BasePage {

    public static String currentDirectory = System.getProperty("user.dir");
    public static String jsonLocatorsPath = System.getProperty("user.dir") + "/src/main/java/objectRepo/";
    public static WebDriver driver;
    File file;
    FileReader fileReader;
    JSONParser jsonParser;
    JSONObject jsonObject, jsonElementObject;


    public void loadJSONPageLocators(String pageElement) {
        try {
            file = new File(jsonLocatorsPath + pageElement + ".json");
            fileReader = new FileReader(file);
            jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebElement findElement(String... args) {
        WebElement webElement = null;
        try {
            jsonElementObject = (JSONObject) jsonObject.get(args[0]);
            String locatorType = (String) jsonElementObject.get("locatorType");
            String locator = (String) jsonElementObject.get("locator");

            for (int i = 0; i < args.length; i++) {
                locator = locator.replace("{" + i + "}", args[i]);
            }

            switch (locatorType) {
                case "xpath":
                    webElement = driver.findElement(By.xpath(locator));
                    break;

                case "id":
                    webElement = driver.findElement(By.id(locator));
                    break;

                case "css":
                    webElement = driver.findElement(By.cssSelector(locator));
                    break;

                default:
                    System.out.println("Choose valid locator - xpath, id or css");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webElement;
    }

    public String readFromConfig(String key) {
        String value = null;
        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);
            value = properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void sendKeysTo(String object, String value) {
        findElement(object).sendKeys(value);
    }

    public void performClick(String object) {
        findElement(object).click();
    }

    public List<WebElement> findElements(String... args) {
        List<WebElement> listOfElements = null;
        jsonElementObject = (JSONObject) jsonObject.get(args[0]);
        String locatorType = (String) jsonElementObject.get("locatorType");
        String locator = (String) jsonElementObject.get("locator");

        switch (locatorType) {
            case "xpath":
                listOfElements = driver.findElements(By.xpath(locator));
                break;

            case "id":
                listOfElements = driver.findElements(By.id(locator));
                break;

            case "css":
                listOfElements = driver.findElements(By.cssSelector(locator));
                break;
        }

        return listOfElements;
    }

    public String getTextValue(WebElement webElement){
        String text = null;
        try{
            text = webElement.getText().trim();
        }catch(Exception e){
            e.printStackTrace();
        }
        return text;
    }

    public String javascriptExecutorGetText(WebElement webElement){
        String jseText = null;
        try{
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jseText = (String) jse.executeScript("return arguments[0].value", webElement);
        }catch(Exception e){
            e.printStackTrace();
        }
        return jseText;
    }

    protected void verifyAndSwitchToWindowByTitle(String product) {
        Set<String> set = driver.getWindowHandles();
        Iterator<String> iterator = set.iterator();

        while(iterator.hasNext()){
            String it = iterator.next();
            driver.switchTo().window(it);
            String currentTitle = driver.getTitle();
            System.out.println(currentTitle);

            if(currentTitle.contains(product)){
                break;
            }
        }
    }
}
