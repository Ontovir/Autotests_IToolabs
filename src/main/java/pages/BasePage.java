package pages;
//Импортирую библиотеки Selenium
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;

    public BasePage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Здесь я инициализирую элементы страницы
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
}
