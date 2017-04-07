package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);
		
		login(driver, "pekka", "väärä");
		login(driver, "olematon", "mikälie");
		
		createUser(driver, "make", "install");
		logoutAfterCreation(driver);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
	
	private static void login(WebDriver driver, String username, String password) {
		driver.get("http://localhost:4567");
		
		WebElement element = driver.findElement(By.linkText("login"));
		element.click();
		
		element = driver.findElement(By.name("username"));
		element.sendKeys(username);
		
		element = driver.findElement(By.name("password"));
		element.sendKeys(password);
		
		element = driver.findElement(By.name("login"));
		element.click();
	}
	
	private static void createUser(WebDriver driver, String username, String password) {
		driver.get("http://localhost:4567");
		
		WebElement element = driver.findElement(By.linkText("register new user"));
		element.click();
		
		element = driver.findElement(By.name("username"));
		element.sendKeys(username);
		
		element = driver.findElement(By.name("password"));
		element.sendKeys(password);
		
		element = driver.findElement(By.name("passwordConfirmation"));
		element.sendKeys(password);
		
		element = driver.findElement(By.name("signup"));
		element.click();
	}
	
	public static void logoutAfterCreation(WebDriver driver) {
		WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
		element.click();
		
		element = driver.findElement(By.linkText("logout"));
		element.click();
	}
}
