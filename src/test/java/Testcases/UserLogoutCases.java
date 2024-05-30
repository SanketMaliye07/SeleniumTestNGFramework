package Testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Configuration.AppManager;
import Pages.AppFunctionsLibrary;
import Report.TestListener;

@Listeners(TestListener.class)
public class UserLogoutCases extends AppManager {

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
	public void testSuccessfulUserLogout() {
		appFunctionsLibrary.clickOnLoginButton()
		                   .enterEmail()
		                   .enterPassword()
				           .clickOnContinueButtonAfterEnteringPassword()
				           .clickOnLogoutLink();
	}
}
