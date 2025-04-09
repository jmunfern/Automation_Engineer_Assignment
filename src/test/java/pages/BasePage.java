package pages;
 
// Importaciones necesarias
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class BasePage {
    
    protected static WebDriver driver;
    
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
 

    static {
        WebDriverManager.chromedriver().setup();
 
        
        driver = new ChromeDriver();
    }
 
    
    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }
 
    
    public static void navigateTo(String url) {
        driver.get(url);
    }
    // Cierra el navegadoir
    public static void closeBrowser(){
        driver.quit();
    }
    // Obtiene el elemento
    private WebElement Find(String locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }
    // Click en el elemento
    public void clickElement(String locator){
        Find(locator).click();
    }

    // Completa los campos con el texto
    public void write(String locator, String keysToSend){
        Find(locator).clear();
        Find(locator).sendKeys(keysToSend);
    }
    // Menú desplegable por valor
    public void selectFromDropdownByValue(String locator, String value){
        Select dropdown = new Select(Find(locator));
        dropdown.selectByValue(value);
    }
    // Desplegable por índice
    public void selectFromDropdownByIndex(String locator, Integer index){
        Select dropdown = new Select(Find(locator));
        dropdown.selectByIndex(index);
    }
    
}