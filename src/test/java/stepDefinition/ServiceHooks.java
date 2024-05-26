package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import pages.HomePage;

public class ServiceHooks {

    HomePage objHomePage;

    @Before
    public void init(){
        objHomePage = new HomePage();
        objHomePage.initialize();
    }

    @After
    public void exit(){
        objHomePage = new HomePage();
        objHomePage.terminate();
    }
}
