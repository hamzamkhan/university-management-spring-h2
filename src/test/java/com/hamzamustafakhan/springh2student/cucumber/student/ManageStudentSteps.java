package com.hamzamustafakhan.springh2student.cucumber.student;


import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.spring.ScenarioScope;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.w3c.dom.html.HTMLSelectElement;

import java.time.Duration;

//@CucumberContextConfiguration
public class ManageStudentSteps {

    WebDriver webDriver = null;

    private final static String MAIN_URL = "http://localhost:3000/";

    @Before
    private WebDriver getInstance(){
        if(webDriver == null){
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        }
        return webDriver;
    }

    @SneakyThrows
    @Given("^Open browser and launch application$")
    public void openBrowserAndLaunchApp(){
        if(webDriver == null){
            webDriver = getInstance();
        }
        webDriver.manage().window().maximize();
        webDriver.get(MAIN_URL);
    }

    @SneakyThrows
    @And("^Home page is displayed$")
    public void homePageIsDisplayed(){
        String home = webDriver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
        Assert.assertTrue(home.equalsIgnoreCase("home"));
    }

    @SneakyThrows
    @When("^User clicks on Manage Students link$")
    public void clickOnManagerStudentsLink(){
        String manageStudentsBtnText = webDriver.findElement(By.id("manage-students-btn")).getText();
        Assert.assertTrue(manageStudentsBtnText.equalsIgnoreCase("Manage Students"));
        webDriver.findElement(By.id("manage-students-btn")).click();
    }

    @SneakyThrows
    @Then("^User sees Manage Students page$")
    public void userSeesManageStudentsPage(){
        Thread.sleep(5000);
        String manageStudentsBtnText = webDriver.findElement(By.id("add-student-btn")).getText();
        Assert.assertTrue(manageStudentsBtnText.equalsIgnoreCase("Add Student"));
    }

    @SneakyThrows
    @When("^Click on Add student button$")
    public void clickOnAddStudentButton(){
        if(webDriver == null){
            webDriver = getInstance();
        }
        webDriver.findElement(By.id("add-student-btn")).click();
    }

    @SneakyThrows
    @And("^New form is displayed")
    public void newFormIsDisplayed(){
        Assert.assertTrue(webDriver.findElement(By.xpath("//h2[contains(text(),'Add Student')]")).getText().equalsIgnoreCase("Add Student"));
    }

    @SneakyThrows
    @When("^Enter details in the form$")
    public void enterDetailsInTheForm(){
        webDriver.findElement(By.id("name")).sendKeys("Jane Doe");
        webDriver.findElement(By.id("fathers_name")).sendKeys("John Doe");
        webDriver.findElement(By.id("email")).sendKeys("janedoe@gmail.com");
        webDriver.findElement(By.id("dob")).sendKeys("26/10/1969");
        webDriver.findElement(By.id("batch_year")).sendKeys("2015");

        Select majorSelect = new Select(webDriver.findElement(By.id("major")));
        majorSelect.selectByVisibleText("Data Science");
        Thread.sleep(1000);
        Select degreeSelect = new Select(webDriver.findElement(By.id("degree")));
        degreeSelect.selectByVisibleText("Masters");
    }

    @SneakyThrows
    @Then("^Click submit$")
    public void clickSubmit(){
        Thread.sleep(2000);
        if(webDriver == null){
            System.out.println("****** NULLLLLLL ******");
        }
        webDriver.findElement(By.id("save-student-btn")).click();

    }

    @SneakyThrows
    @When("^Clicks on edit for created student$")
    public void clicksOnEditForCreatedStudent(){
        Thread.sleep(2000);
        Assert.assertEquals(webDriver.findElement(By.xpath("//tbody/tr[1]/td[3]/div[1]/a[1]")).getText(),"Edit");
        webDriver.findElement(By.xpath("//tbody/tr[1]/td[3]/div[1]/a[1]")).click();
    }

    @SneakyThrows
    @And("^Student details displayed$")
    public void studentDetailsDisplayed(){
        Thread.sleep(5000);
        Assert.assertEquals(webDriver.findElement(By.xpath("//input[@id='name']")).getAttribute("value"),"Jane Doe");
    }

    @SneakyThrows
    @Then("^Changes date of birth$")
    public void changesDateOfBirth(){
        webDriver.findElement(By.id("dob")).sendKeys("26/07/1989");
    }

    @SneakyThrows
    @And("^Click submit after edit$")
    public void clickSubmitAfterEdit(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        WebElement webElement = webDriver.findElement(By.id("save-student-btn"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //Scroll down till the bottom of the page
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();

    }

    @SneakyThrows
    @Then("^Clicks on delete for the student$")
    public void clicksOnDeleteForTheStudent(){
        Thread.sleep(2000);
        Assert.assertEquals(webDriver.findElement(By.xpath("//tbody/tr[1]/td[3]/div[1]/button[1]")).getText(), "Delete");
        webDriver.findElement(By.xpath("//tbody/tr[1]/td[3]/div[1]/button[1]")).click();
    }

    @And("^Check if the student still exists$")
    public void checkIfTheStudentStillExists() throws InterruptedException {
        try{
            Thread.sleep(2000);
            webDriver.findElement(By.xpath("//td[contains(text(),'Jane Doe')]"));
        } catch (Exception e){
            System.out.println("Element not found");
        } finally {
            Thread.sleep(5000);
            webDriver.quit();
        }

    }





}
