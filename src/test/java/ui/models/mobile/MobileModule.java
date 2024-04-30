package ui.models.mobile;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class MobileModule {

WebDriver driver;
	@FindBy(xpath="//select[@id='appointment_newAppointmentForm_fields_3__content']")
	public
	WebElement dropdown;

	public MobileModule(WebDriver d){
		this.driver=d;
		PageFactory.initElements(d,this);
	}
	public void Login() throws InterruptedException, MessagingException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		boolean scrollIntoView=false;
		js.executeScript("arguments[0].scrollIntoView(" + scrollIntoView + ")", dropdown);
		Select select;
		boolean isFound = false;
		String requiredOption = "Master students holding an unconditional direct admission letter from a German university valid for summer semester 2024 / Masterstudenten mit direkter Zulassung ohne Bedingungen für das Sommersemester 2024";
		while (!isFound) {
			driver.navigate().refresh();
			wait.until(ExpectedConditions.visibilityOf(dropdown));
			select = new Select(dropdown);
			List<WebElement> dropdownOptions = select.getOptions();
			int numberOfOptions = dropdownOptions.size();
			System.out.println("Checking dropdown options...");
			for (WebElement option : dropdownOptions) {
				if (option.getText().equals(requiredOption) || numberOfOptions >= 7) {
					isFound = true;
					sendEmail("gasadraza@gmail.com", "Option is Available", "The required option is now available in the dropdown.");
					break;
				}
			}
			if (!isFound) {
				Thread.sleep(5000); // Wait for 5 seconds before checking again
			}
		}
	}

	private void sendEmail(String to, String subject, String text) throws MessagingException {
		String from = "gasadraza@gmail.com";
		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("heloasadraza@gmail.com", "hwfv fneq lyij lpnh");
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(text);
			Transport.send(message);
			System.out.println("Sent email successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}