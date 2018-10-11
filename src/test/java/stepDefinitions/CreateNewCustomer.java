package stepDefinitions;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateNewCustomer {
	WebDriver driver;
	//WebDriverWait wait = new WebDriverWait(driver, 30);
	String Urllogin, userName, Password, email;

	@Given("^I open browser$")
	public void iOpenBrowser() {
		
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}

	@Given("^I get login url$")
	public void iGetLoginUrl() {
		Urllogin = driver.getCurrentUrl();

	}

	@When("^I click to here link$")
	public void iClickToHereLink() {
		WebElement hereLink = driver.findElement(By.xpath("//a[text()='here']"));
		hereLink.click();

	}

	@When("^I input to email textbox$")
	public void iInputToEmailTextbox() {
		WebElement inputEmail = driver.findElement(By.xpath("//input[@name='emailid']"));
		waitForElement(inputEmail);
		inputEmail.sendKeys("Automation" + radomNumber() + "@gmail.com");

	}

	@When("^I click to submit button at register page$")
	public void iClickToSubmitButtonAtRegisterPage() {
		WebElement submitButton = driver.findElement(By.xpath("//input[@name='btnLogin']"));
		submitButton.click();

	}

	@When("^I get to username information$")
	public void iGetToUsernameInformation() {
		WebElement usernameText = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td"));
		waitForElement(usernameText);
		userName = usernameText.getText();

	}

	@When("^I get to password infomation$")
	public void iGetToPasswordInfomation() {
		WebElement passwordText = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td"));
		waitForElement(passwordText);
		Password = passwordText.getText();

	}

	@When("^I open to login page$")
	public void iOpenToLoginPage() {
		driver.get(Urllogin);

	}

	@When("^I input to username textbox$")
	public void iInputToUsernameTextbox() {
		WebElement inputUserName = driver.findElement(By.xpath("//input[@name='uid']"));
		waitForElement(inputUserName);
		inputUserName.sendKeys(userName);

	}

	@When("^I input to password textbox$")
	public void iInputToPasswordTextbox() {
		WebElement inputPass = driver.findElement(By.xpath("//input[@name='password']"));
		inputPass.sendKeys(Password);

	}

	@When("^I click to login button$")
	public void iClickToLoginButton() {
		WebElement loginButton = driver.findElement(By.xpath("//input[@name='btnLogin']"));
		loginButton.click();

	}

	@Then("^Verify home page welcom message is displayed$")
	public void verifyHomePageWelcomMessageIsDisplayed() {
		WebElement homePageMessage = driver
				.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"));
		org.testng.Assert.assertTrue(homePageMessage.isDisplayed());

	}

	@When("^I click to New Customer page$")
	public void iClickToNewCustomerPage() {
		WebElement newCustomerPage = driver.findElement(By.xpath("//a[text()='New Customer']"));
		newCustomerPage.click();

	}

	@When("^I input all information to this page$")
	public void iInputAllInformationToThisPage(DataTable table) {
		WebElement nameTxt = driver.findElement(By.xpath("//input[@name='name']"));
		WebElement gender = driver.findElement(By.xpath("//input[@value='m']"));
		WebElement dateOfBirth = driver.findElement(By.xpath("//input[@name='dob']"));
		removeAttributeInDOM("//input[@name='dob']","type");
		WebElement address = driver.findElement(By.xpath("//textarea[@name='addr']"));
		WebElement city = driver.findElement(By.xpath("//input[@name='city']"));
		WebElement state = driver.findElement(By.xpath("//input[@name='state']"));
		WebElement pin = driver.findElement(By.xpath("//input[@name='pinno']"));
		WebElement telephone = driver.findElement(By.xpath("//input[@name='telephoneno']"));
		WebElement email = driver.findElement(By.xpath("//input[@name='emailid']"));
		WebElement password = driver.findElement(By.xpath("//input[@name='password']"));

		List<Map<String, String>> customer = table.asMaps(String.class, String.class);
		nameTxt.sendKeys(customer.get(0).get("CustomerName"));
		gender.click();
		dateOfBirth.sendKeys(customer.get(0).get("DateOfBirth"));
		address.sendKeys(customer.get(0).get("Address"));
		city.sendKeys(customer.get(0).get("City"));
		state.sendKeys(customer.get(0).get("State"));
		pin.sendKeys(customer.get(0).get("Pin"));
		telephone.sendKeys(customer.get(0).get("Phone"));
		email.sendKeys(customer.get(0).get("Email") + radomNumber() + "@gmail.com");
		password.sendKeys(customer.get(0).get("Password"));

	}

	@When("^I click to submit button$")
	public void iClickToSubmitButton() {
		WebElement submitButton = driver.findElement(By.xpath("//input[@name='sub']"));
		submitButton.click();
	}

	@When("^I close the browser$")
	public void iCloseTheBrowser() {
		driver.close();

	}

	public int radomNumber() {
		Random radome = new Random();
		int number = radome.nextInt(9999999);
		return number;
	}

	public void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public Object removeAttributeInDOM( String locator, String attribute) {
		WebElement element = driver.findElement(By.xpath(locator));
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
}
