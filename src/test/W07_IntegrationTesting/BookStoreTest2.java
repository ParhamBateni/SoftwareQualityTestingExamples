package W07_IntegrationTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

public class BookStoreTest2 {
    private WebDriver driver;

    @BeforeEach
    public void initDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("file:///C:/Users/DELL/Desktop/SoftwareQualityTesting/Excercises/src/main/java/W07_IntegrationTesting/BookStore/index.html");
    }

    private void addAuthor(WebElement authorElement, WebElement submitButton, String authorName) {
        authorElement.clear();
        authorElement.sendKeys(authorName);
        submitButton.click();
    }

    private void addBookAuthor(WebElement authorElement, WebElement bookElement, WebElement submitButton,
                               String authorName, String bookName) {
        authorElement.clear();
        authorElement.sendKeys(authorName);
        bookElement.clear();
        bookElement.sendKeys(bookName);
        submitButton.click();
    }

    private void search(WebElement searchElement, String query) {
        searchElement.clear();
        searchElement.sendKeys(query);
        searchElement.sendKeys(Keys.ENTER);
    }

    @Test
    public void testSearchAuthorAndBook() {
        WebElement authorName = driver.findElement(By.id("author_name"));
        WebElement authorSubmitButton = driver.findElement(By.id("author_submit_button"));
        WebElement author_id = driver.findElement(By.id("author_id"));
        WebElement book_name = driver.findElement(By.id("book_name"));
        WebElement bookSubmitButton = driver.findElement(By.id("book_submit_button"));
        WebElement searchBar = driver.findElement(By.id("search_bar"));
        WebElement searchBookList = driver.findElement(By.id("book_list"));
        WebElement searchAuthorList = driver.findElement(By.id("author_list"));

        addAuthor(authorName, authorSubmitButton, "Author1");
        addAuthor(authorName, authorSubmitButton, "Author2");
        addAuthor(authorName, authorSubmitButton, "Author3");

        addBookAuthor(author_id, book_name, bookSubmitButton, "1", "book1");
        addBookAuthor(author_id, book_name, bookSubmitButton, "1", "book2");
        addBookAuthor(author_id, book_name, bookSubmitButton, "2", "book3");
        addBookAuthor(author_id, book_name, bookSubmitButton, "3", "book3");

        search(searchBar, "1");
        assertEquals("1 : Author1", searchAuthorList.getText());
        assertEquals("\"book1\" by: Author1", searchBookList.getText());

        search(searchBar, "book2");
        assertEquals("No matching authors were found!", searchAuthorList.getText());
        assertEquals("\"book2\" by: Author1", searchBookList.getText());

        search(searchBar, "book3");
        assertEquals("No matching authors were found!", searchAuthorList.getText());
        assertEquals("\"book3\" by: Author2\n" +
                "\"book3\" by: Author3", searchBookList.getText());

        search(searchBar, "baby");
        assertEquals("No matching authors were found!", searchAuthorList.getText());
        assertEquals("No matching books were found!", searchBookList.getText());
    }

    @AfterEach
    public void close() {
        driver.quit();
    }
}
