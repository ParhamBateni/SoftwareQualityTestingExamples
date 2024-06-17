package W08_AllTestingMethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookStoreTest3 {
    private WebDriver driver;

    @BeforeEach
    public void initDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("file:///C:/Users/DELL/Desktop/SoftwareQualityTesting/Excercises/src/main/java/W07_IntegrationTesting/BookStore/index.html");
        addAvailableAuthors();
    }

    private void addAvailableAuthors() {
        WebElement authorName = driver.findElement(By.id("author_name"));
        WebElement submitButton = driver.findElement(By.id("author_submit_button"));
        authorName.sendKeys("Mauricio Aniche");
        submitButton.click();
        authorName.clear();
        authorName.sendKeys("Dan Brown");
        submitButton.click();
        authorName.clear();
        authorName.sendKeys("J.R.R. Tolkien");
        submitButton.click();
    }

    private void addBook(String name, String id, WebElement bookName, WebElement authorId, WebElement submitButton) {
        authorId.clear();
        bookName.clear();
        authorId.sendKeys(id);
        bookName.sendKeys(name);
        submitButton.click();
    }

    @Test
    public void testAddBook() {
        WebElement authorId = driver.findElement(By.id("author_id"));
        WebElement bookName = driver.findElement(By.id("book_name"));
        WebElement submitButton = driver.findElement(By.id("book_submit_button"));
        WebElement alertMessage = driver.findElement(By.id("alert-msg"));
        addBook("", "10", bookName, authorId, submitButton);
        assertEquals("Book name cannot be empty", alertMessage.getText());
        addBook("book1", "10", bookName, authorId, submitButton);
        assertEquals("Author with that id does not exist", alertMessage.getText());
        addBook("book1", "1", bookName, authorId, submitButton);
        assertEquals("Book was successfully added!", alertMessage.getText());
        addBook("book1", "1", bookName, authorId, submitButton);
        assertEquals("The author already has a book with that name!", alertMessage.getText());
        addBook("book2", "1", bookName, authorId, submitButton);
        assertEquals("Book was successfully added!", alertMessage.getText());
        addBook("book1", "2", bookName, authorId, submitButton);
        assertEquals("Book was successfully added!", alertMessage.getText());
        addBook("book3", "3", bookName, authorId, submitButton);
    }

    @AfterEach
    public void close() {
        driver.quit();
    }
}
