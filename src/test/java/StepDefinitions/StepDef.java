package StepDefinitions;

import io.cucumber.java8.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StepDef implements En {

    final WebDriver driver = new ChromeDriver();

    public StepDef() {

        Given("I open Casino page \"([^\"]*)\"$", (String url) -> {
            driver.manage().window().maximize();
            driver.get(url);
        });

        Given("I open Casino page \"([^\"]*)\" and login as user \"([^\"]*)\" with password \"([^\"]*)\"$",
                (String url, String name, String pass) -> {
                    driver.manage().window().maximize();
                    driver.get(url);
                    driver.findElement(By.id("UserLogin_username")).sendKeys(name);
                    driver.findElement(By.id("UserLogin_password")).sendKeys(pass);
                    driver.findElement(By.name("yt0")).click();
                });

        Given("I enter login name \"([^\"]*)\"$", (String name) ->
                driver.findElement(By.id("UserLogin_username")).sendKeys(name));

        And("I enter login password \"([^\"]*)\"$", (String pass) ->
                driver.findElement(By.id("UserLogin_password")).sendKeys(pass));

        When("I click on Sign in button", () ->
                driver.findElement(By.name("yt0")).click());

        Then("Admin page \"([^\"]*)\" is open$", (String url) -> Assert.assertEquals(url,
                driver.getCurrentUrl()));

        And("User \"([^\"]*)\" is logged successfully$", (String name) ->
                Assert.assertEquals(driver.findElement(By.className("nav-profile")).getText(), name));

        When("I click on \"([^\"]*)\" tab$", (String tabName) ->
                driver.findElement(By.xpath("//span[text()='" + tabName + "']")).click());

        And("I click on \"([^\"]*)\" sub-tab$", (String subTabName) ->
                driver.findElement(By.xpath("//a[text()='" + subTabName + "']")).click());

        Then("Table \"([^\"]*)\" is open$", (String tableName) ->
                Assert.assertEquals(driver.findElement(By.className("panel-heading")).getText(), tableName));

        When("I click on \"([^\"]*)\" quick access button$", (String quickButton) ->
                driver.findElement(By.xpath("//p[contains(text(),'" + quickButton + "')]")).click());

        And("I click on column header \"([^\"]*)\" to sort \"([^\"]*)\"$",
                (String columnName, String order) -> {
            driver.findElement(By.xpath("//a[contains(@class,'sort-link')][contains(text(),'" + columnName + "')]")).
                    click();
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[contains(@class,'sort-link')]" +
                             "[contains(text(),'" + columnName + "')][contains(@class,'" + order + "')]")));
        });

        Then("Table sorted by date \"([^\"]*)\"$", (String name) -> {
            ArrayList<LocalDateTime> times = new ArrayList<>();
            List<WebElement> elements = driver.
                    findElements(By.xpath("//tbody//tr//td[@class='hide-mobile' and position()=10]"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (WebElement e : elements) {
                times.add(LocalDateTime.parse(e.getText(), formatter));
            }
            for (int i = 0; i < times.size() - 1; i++) {
                if (name.equals("asc")) {
                    Assert.assertTrue(times.get(i).compareTo(times.get(i + 1)) < 0);
                } else if (name.equals("desc")) {
                    Assert.assertTrue(times.get(i).compareTo(times.get(i + 1)) > 0);
                } else {
                    Assert.fail("Please specify correct order for sorting: asc or desc");
                    break;
                }
            }
        });
        After(driver::close);
    }
}
