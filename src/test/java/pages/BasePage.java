package pages;
 
// Importaciones necesarias
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class BasePage {
    
    protected static WebDriver driver;
     private static Actions action;
    
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

    // Encuentra el elemento de enlace en la página web usando el texto del enlace y hace clic en él
    public void goToLinkText(String linkText){
        driver.findElement(By.linkText(linkText)).click();;
    }
    
    // Cierra el navegador
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
    
   // Acciones

    // Pasar el cursor sobre el elemento
    public void hoverOverElement(String locator) {
        // Mueve el cursor al elemento encontrado por el localizador
        action.moveToElement(Find(locator));
    }

    // Doble clic
    public void doubleClick(String locator) {
        // Realiza un doble clic en el elemento encontrado por el localizador
        action.doubleClick(Find(locator));
    }

    // Clic derecho
    public void rightClick(String locator) {
        // Realiza un clic derecho en el elemento encontrado por el localizador
        action.contextClick(Find(locator));
    }

    // iFrame y PopUps
    public void switchToIframe(int iFrameIndex) {
        // Cambia al iFrame especificado por el índice
        driver.switchTo().frame(iFrameIndex);
    }

    public void switchToParentFrame() {
        // Cambia al marco padre
        driver.switchTo().parentFrame();
    }

    public void dismissAlert() {
        // Desestima la alerta
        driver.switchTo().alert().dismiss();
    }

    public String textFromElement(String locator) {
        // Obtiene el texto del elemento encontrado por el localizador
        return Find(locator).getText();
    }

    // Elementos booleanos
    public boolean elementEnabled(String locator) {
        // Verifica si el elemento encontrado por el localizador está habilitado para ser clicable
        return Find(locator).isEnabled();
    }

    public boolean elementIsDisplayed(String locator) {
        // Verifica si el elemento encontrado por el localizador está visible en la página
        return Find(locator).isDisplayed();
    }

    public boolean elementIsSelected(String locator) {
        // Verifica si el elemento encontrado por el localizador (como un checkbox) está seleccionado
        return Find(locator).isSelected();
    }

    
}