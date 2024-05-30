package Pages;

import org.openqa.selenium.By;

public class LoginPage {

	public static By emailTextField = By.name("email");
	
	public static By passwordTextField = By.name("password");
	
	public static By LoginButton = By.cssSelector("button[data-qa=\"login-button\"]");
 	 
}
