package testcases;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;

public class TC2_Login extends BaseClass {

	//public void testLoginWithValidCredentials() {

	    // Fix 1: Added @Test annotation so TestNG executes this method
	    @Test(groups = { "Sanity", "Master" }) 
	    public void testLoginWithValidCredentials() {

	        // Fix 2: Retrieve the active thread-safe driver instance
	        WebDriver activeDriver = getDriver();

	        // Step 1: Navigate to the application URL
	        String applicationUrl = properties.getProperty("appURL");
	        activeDriver.get(applicationUrl);
	        // Fix 3: Cleaned up a broken escape character syntax error in the logger string below
	        logger.info("Navigated to application URL: " + applicationUrl);

	        // Step 2: Navigate to the Login page from the Home page
	        HomePage homepage = new HomePage(activeDriver);
	        homepage.clickMyAccount();
	        logger.info("Clicked on 'My Account' link.");
	        
	        LoginPage loginPage = homepage.clickLogin();
	        logger.info("Clicked on 'Login' link, navigating to the Login Page.");
	        
	        // Step 3: Perform login with valid credentials
	        logger.info("Entering user login credentials...");
	        String userEmail = properties.getProperty("email");
	        loginPage.setEmail(userEmail);
	        logger.info("Entered email: " + userEmail);

	        String userPassword = properties.getProperty("password");
	        loginPage.setPassword(userPassword);
	        logger.info("Entered password.");

	        loginPage.clickLogin();
	        logger.info("Clicked on the 'Login' button.");

	        // Step 4: Verify login by checking the presence of the 'My Account' page
	        MyAccountPage myAccountPage = new MyAccountPage(activeDriver);
	        logger.info("Verifying if the 'My Account' page is displayed...");
	        
	        boolean isMyAccountPageDisplayed = myAccountPage.isMyAccountPageExists();
	        Assert.assertTrue(
	            isMyAccountPageDisplayed, 
	            "Login failed: 'My Account' page not displayed."
	        );
	        logger.info("Login successful. 'My Account' page is displayed.");
	    }
	}


