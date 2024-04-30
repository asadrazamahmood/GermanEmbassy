package ui.steps.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ui.models.mobile.MobileModule;

import javax.mail.MessagingException;
import java.time.Duration;
public class MobileSteps {
	public WebDriver driver;
	MobileModule mobileModule;
	@BeforeTest
	@Parameters("browser")
	public void navigateToURL(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.get("https://service2.diplo.de/rktermin/extern/appointment_showForm.do?locationCode=isla&realmId=108&categoryId=1600");
		driver.manage().window().maximize();
		mobileModule = new MobileModule(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test(priority = 1)
	public void Login() throws MessagingException, InterruptedException {

		mobileModule.Login();
	}


}
