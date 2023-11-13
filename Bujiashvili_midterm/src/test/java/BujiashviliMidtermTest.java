import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.time.Duration;

public class BujiashviliMidtermTest {
        WebDriver driver;

        @BeforeTest
        public void start() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("https://opencritic.com/");
            driver.manage().window().maximize();
        }

        @BeforeMethod
        public void beforeMethod() {
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        }

        @Test(priority = 0)
        private void SearchUp(){

            //we create an explicit wait here. (it's a surprise tool that'll help us later)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

            //the first dropdown is here, I used Xpath to find it
            WebElement DropdownOpen = driver.findElement(By.xpath("//*[@id=\"opencritic-nav\"]/div/div/ul[1]/li[1]/a"));
            new Actions(driver)
                    .click(DropdownOpen)
                    .perform();
            //first implicit wait
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

            //I used a Xpath to find the dropdown method here but later on i use the correct method too
            WebElement Dropdownclick = driver.findElement(By.xpath("//*[@id=\"opencritic-nav\"]/div/div/ul[1]/li[1]/div/a[5]"));
            new Actions(driver)
                    .click(Dropdownclick)
                    .perform();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            //the selects here kept having problems loading so I added an explicit wait for these two select dropdown menus.
            wait.until(ExpectedConditions.elementToBeClickable(By.id("time-select")));

            //i used the year select dropdown box to look up "2023" year with its Index
            WebElement selectYear = driver.findElement(By.id("time-select"));
            Select selectYearOption = new Select(selectYear);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            selectYearOption.selectByIndex(3);

            //another explicit wait, the browser had a lot of trouble here without it
            wait.until(ExpectedConditions.elementToBeClickable(By.id("sort-select")));

            //i chose the "percent recommendation" in the sorting selection by using its Value here
            WebElement selectSort = driver.findElement(By.id("sort-select"));
            Select selectSortOption = new Select(selectSort);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            selectSortOption.selectByValue("percent-recommended");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        }

    @Test(priority = 1)
    private void SearchInput() {
        //look up a textbox with "findElement" through it's class name
        WebElement SearchInput1 = driver.findElement(By.className("form-control"));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        //sent text to the text box
        SearchInput1.sendKeys("Hi-Fi Rush");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("ngb-typeahead-0-0")));

        //find the "search" button through ID and used some basic mouse actions of "move to element" and "click"
        WebElement MoveToSearch = driver.findElement(By.id("ngb-typeahead-0-0"));
        new Actions(driver)
                .moveToElement(MoveToSearch)
                .perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        WebElement SearchClick = driver.findElement(By.id("ngb-typeahead-0-0"));
        new Actions(driver)
                .click(SearchClick)
                .perform();
    }


    @AfterTest
    public void finish(){
        driver.quit();
    }

}
