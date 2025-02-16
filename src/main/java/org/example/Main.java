package org.example;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup(); // Автоматически скачивает и подключает драйвер
        WebDriver driver = new ChromeDriver();
        driver.get("https://qa-interview.netlify.app/"); // Открываем сайт
        System.out.println("Title: " + driver.getTitle()); // Выводим заголовок страницы
        driver.quit(); // Закрываем браузер
    }
}