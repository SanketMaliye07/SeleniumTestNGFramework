package Testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import Pages.AppFunctionsLibrary;
import Report.TestListener;
import Configuration.AppManager; 

import java.io.IOException;


@Listeners(TestListener.class)
public class UserLoginTests extends AppManager {

    AppFunctionsLibrary appFunctionsLibrary;

    @BeforeMethod 
    public void setUp() {
        super.setUp();
        appFunctionsLibrary = new AppFunctionsLibrary(driver);
    }

    @AfterMethod 
    public void tearDown() {
        super.tearDown();
    }

    @Test
    public void testSuccessfulUserLogin() {
        appFunctionsLibrary.clickOnLoginButton()
                            .enterEmail()
                            .enterPassword()
                            .clickOnContinueButtonAfterEnteringPassword(); 
    }

    @Test
    public void testSuccessfulUserLoginWithExcelData() throws IOException {
        appFunctionsLibrary.clickOnLoginButton();
        appFunctionsLibrary.enterCredentialsFromExcel();
        appFunctionsLibrary.clickOnContinueButtonAfterEnteringPassword(); 
    }

    @Test
    public void testSuccessfulUserLoginWithJSONData() throws IOException {
        appFunctionsLibrary.clickOnLoginButton();
        appFunctionsLibrary.enterCredentialsFromJSON();
        appFunctionsLibrary.clickOnContinueButtonAfterEnteringPassword(); 
    }
}
