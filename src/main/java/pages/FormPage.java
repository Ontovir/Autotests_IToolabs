package pages;
//Импортирую библиотеки Selenium для того, чтобы взаимодействовать с формой на сайте https://qa-interview.netlify.app/
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;


public class FormPage extends BasePage{
    //Ищу элементы страницы через @FindBy
    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "phone")
    private WebElement phoneField;

    @FindBy(id = "modal")
    public WebElement modalWindow;

    @FindBy(id = "file")
    private WebElement fileUpload;
    //Кнопку формы ищу через xpath
    @FindBy(xpath = "//*[@id=\"ticket-form\"]/button")
    private WebElement submitButton;

    //Взаимодействие с чекбоксом с использованием библиотеки By
    public void clickTermsCheckbox(){
        WebElement checkbox = driver.findElement(By.id("terms"));
        if (!checkbox.isSelected()){
            checkbox.click();
        }
    }

    //Выбор города из выпадающего списка с использованием библиотек By и ui.select
    public void selectCity(int cityIndex){
        WebElement cityList = driver.findElement(By.id("city"));
        Select select = new Select(cityList);
        select.selectByIndex(cityIndex);
    }

    public void fillForm(String name, String email, String phone, String filePath)
    {
        nameField.sendKeys(name);
        emailField.sendKeys(email);
        phoneField.sendKeys(phone);
        fileUpload.sendKeys(filePath);
    }

    public int lettersCount;
    public boolean nameBValuesCheck() {
        // Создаю строку из 100 латинских символов "a"
        String name = "a".repeat(lettersCount);

        // Вводим в поле ФИО
        nameField.sendKeys(name);

        // Считываем, что реально оказалось в поле ФИО
        String actualValue = nameField.getAttribute("value");

        // Сохранилось ли 100 символов?
        return name.equals(actualValue);
    }
    public void submitForm(){
        submitButton.click();
    }
    public FormPage(WebDriver driver){
        super(driver);
    }

}
