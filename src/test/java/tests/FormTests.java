package tests;
import org.junit.Test;
import pages.FormPage;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

public class FormTests extends BaseTest{
    //Ниже идёт перечень переменных, которые мы можем вводить в поля тестируемой формы.
    //Измените значение переменных, чтобы проверить форму с другими данными.
    String testData_Name = "Antony Docker";
    String testData_Email = "sample@example.com";
    String testData_Phone = "+7889213";
    String testData_FileLink = "D:\\Работа\\Тестирование ITooLabs\\Test Text.txt";

    //Индекс города из выпадающего списка:
    int cityIndex = 1;

    //Количество латинских символов в поле ФИО:
    int lettersCount = 100;

    @Test // Проверка отправки формы. Осуществляется по поиску модального окна (id = "modal") на странице сайта, которое появляется после "успешной" отправки формы
    public void testFormSubmission(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-interview.netlify.app/");
        FormPage formPage = new FormPage(driver);
        formPage.fillForm(testData_Name,testData_Email,testData_Phone,testData_FileLink);
        //Устанавливаем галочку в чекбоксе "Согласен с условиями".
        formPage.clickTermsCheckbox();
        //Выбираем город из списка id = "city".
        formPage.selectCity(cityIndex);
        formPage.submitForm();
        //Проверяем, что форма отправлена.
        WebElement modalWindow = formPage.modalWindow;
        Assert.assertTrue("Модальное окно не появилось", modalWindow.isDisplayed());
        //Сообщение "Модальное окно не появилось" будет в консоли, если форма не отправлена.
        driver.quit();
    }

    @Test // Проверка максимального количества символов, которые можно ввести в поле ФИО.
    public void nameBorderValuesCheck() {
        WebDriver driver = new ChromeDriver();
        FormPage formPage = new FormPage(driver);
        driver.get("https://qa-interview.netlify.app/");
        formPage.lettersCount = lettersCount;
        Assert.assertTrue("Поле ФИО не принимает больше 100 символов!", formPage.nameBValuesCheck());
        driver.quit();
    }

    @Test // Тестируем корректность работы чекбокса.
    public void testCheckboxSelection() {
        // Инициализация WebDriver
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://qa-interview.netlify.app/");
            // Находим чекбокс по ID
            WebElement checkbox = driver.findElement(By.id("terms"));
            // Проверяем, что изначально чекбокс не выбран
            Assert.assertFalse("Чекбокс уже отмечен!", checkbox.isSelected());
            checkbox.click();
            // Проверяем, что чекбокс теперь отмечен
            Assert.assertTrue("Чекбокс не был выбран после клика!", checkbox.isSelected());

        } finally {
            // Закрываем браузер
            driver.quit();
        }
    }

    @Test // Проверяем возможность выбора каждого города из выпадающего списка.
    public void testEachCityCouldBePicked(){
        // Инициализация WebDriver + заходим на сайт.
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-interview.netlify.app/");
        WebElement cityList = driver.findElement(By.id("city"));
        Select select = new Select(cityList);
        List<WebElement> options = select.getOptions();

        // С помощью цикла for проверяем каждый город из списка городов.
        for (int i = 0; i < options.size(); i++){
            select.selectByIndex(i);
            String selectedCity = select.getFirstSelectedOption().getText();
            String expectedCity = options.get(i).getText();
            Assert.assertEquals("Выбранный город не совпадает с ожидаемым!", expectedCity, selectedCity);
        }
        
        driver.quit();
    }
}
