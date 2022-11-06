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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.w3c.dom.html.HTMLSelectElement;

@CucumberContextConfiguration
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
        String manageStudentsBtnText = webDriver.findElement(By.id("add-student-btn")).getText();
        Assert.assertTrue(manageStudentsBtnText.equalsIgnoreCase("Add Student"));
    }

    @SneakyThrows
    @Then("^Click on Add student button$")
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
        webDriver.findElement(By.id("name")).sendKeys("Hamza Mustafa Khan");
        webDriver.findElement(By.id("fathers_name")).sendKeys("Muhammad Muzaffar");
        webDriver.findElement(By.id("email")).sendKeys("hamza26o96@gmail.com");
        webDriver.findElement(By.id("dob")).sendKeys("26/10/1996");
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
        Thread.sleep(5000);
        webDriver.quit();
    }





}
