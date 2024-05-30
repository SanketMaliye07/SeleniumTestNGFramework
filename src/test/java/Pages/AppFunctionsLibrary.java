package Pages;

import java.io.IOException;
import java.time.Duration;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import Utilities.ExcelUtils;
import Utilities.JSONUtils;
import Utilities.JavaScriptExecutorUtils;
import Utilities.WaitUtilities;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;

public class AppFunctionsLibrary {

	private WebDriver driver;
	JavaScriptExecutorUtils js;
	ExcelUtils excelutil;
	JSONUtils jsonutil;
	WaitUtilities wait;

	public AppFunctionsLibrary(WebDriver driver) {
		this.driver = driver;
		this.js = new JavaScriptExecutorUtils(driver);
		this.excelutil = new ExcelUtils();
		this.jsonutil =  new JSONUtils();
		this.wait = new WaitUtilities(driver);
	}

	public AppFunctionsLibrary clickOnLoginButton() {
		js.clickElement(HomePage.LoginButton);
		return this;
	}

	public AppFunctionsLibrary enterEmail() {
		js.setInputValue(LoginPage.emailTextField, "automationqa@gmail.com");
		return this;
	}

	public AppFunctionsLibrary enterPassword() {
		driver.findElement(LoginPage.passwordTextField).clear();
		driver.findElement(LoginPage.passwordTextField).sendKeys("automationqa");
		return this;
	}

	public AppFunctionsLibrary clickOnContinueButton() {
		js.clickElement(LoginPage.LoginButton);
		return this;
	}

	public AppFunctionsLibrary clickOnContinueButtonAfterEnteringPassword() {
		driver.findElement(LoginPage.LoginButton).click();
		return this;
	}

	public AppFunctionsLibrary clickOnProfileDropdown() {
		wait.waitForElementPresent(ProfilePage.ProfileDropdown, Duration.ofSeconds(60));
		js.clickElement(ProfilePage.ProfileDropdown);
		return this;
	}

	public AppFunctionsLibrary clickOnLogoutLink() {
		js.clickElement(ProfilePage.LogoutLink);
		return this;
	}

	public void enterCredentialsFromExcel() throws IOException {
		  String email = excelutil.readFromExcel(1, 0);
		  String password = excelutil.readFromExcel(1, 1);		
		  js.setInputValue(LoginPage.emailTextField, email);		   
		  js.setInputValue(LoginPage.passwordTextField, password);			 
	}
	
	public void enterCredentialsFromJSON() throws IOException {
		 JSONObject jsonObject = jsonutil.readJsonFile();
		 if (jsonObject != null) {
		        String email = jsonutil.getJsonValue(jsonObject, "EMAIL");
		        String password = jsonutil.getJsonValue(jsonObject, "PASSWORD");		     
		        js.setInputValue(LoginPage.emailTextField, email);
		        js.setInputValue(LoginPage.passwordTextField, password);
		    }	 
	}
}
