package W07_IntegrationTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

public class BookStoreTest {
    private WebDriver driver;
    @BeforeEach
    public void initDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("file:///C:/Users/DELL/Desktop/SoftwareQualityTesting/Excercises/src/main/java/W07_IntegrationTesting/BookStore/index.html");
    }

    @Test
    public void testAddAuthor(){
        WebElement authorName = driver.findElement(By.id("author_name"));
        WebElement submitButton = driver.findElement(By.id("author_submit_button"));
        WebElement alertMessage =driver.findElement(By.id("alert-msg"));
        authorName.sendKeys("Author1");
        submitButton.click();
        assertEquals(alertMessage.getText(),"Author successfully added with id 1");
        authorName.clear();
        authorName.sendKeys("Author1");
        submitButton.click();
        assertEquals(alertMessage.getText(),"Author with that name already exists!");
        authorName.clear();
        submitButton.click();
        assertEquals(alertMessage.getText(),"Author name cannot be empty");
    }

    @AfterEach
    public void close() {
        driver.quit();
    }
}
