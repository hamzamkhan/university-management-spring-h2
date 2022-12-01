package com.hamzamustafakhan.springh2student.cucumber.professor.stepdefinitions;

import com.hamzamustafakhan.springh2student.cucumber.professor.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ProfessorCreateSteps extends Base {
    private final static String MAIN_URL = "http://localhost:3000/";

    private void setup(){
        initialization(MAIN_URL);
    }

    @SneakyThrows
    @Given("^Open browser and launch app$")
    public void openBrowserAndLaunchUrl(){
        setup();
    }

    @SneakyThrows
    @And("^Home page is displayed in browser$")
    public void homePageIsDisplayedOnBrowser(){
        String home = webDriver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
        Assert.assertTrue(home.equalsIgnoreCase("home"));
    }

    @SneakyThrows
    @Then("^User clicks on manage professors link$")
    public void user_clicks_on_manage_professors_link(){
        String manageProfessorsBtnText = webDriver.findElement(By.id("manage-professors-btn")).getText();
        Assert.assertTrue(manageProfessorsBtnText.equalsIgnoreCase("Manage Professors"));
        webDriver.findElement(By.id("manage-professors-btn")).click();
    }

    @SneakyThrows
    @Then("^User sees Manage Professors page$")
    public void userSeesManageProfessorsPage(){
        Thread.sleep(5000);
        String addProfessorBtnText = webDriver.findElement(By.id("add-professor-btn")).getText();
        Assert.assertTrue(addProfessorBtnText.equalsIgnoreCase("Add Professor"));
    }

    @SneakyThrows
    @When("^Click on Add Professor button$")
    public void clickOnAddProfessorButton(){
        webDriver.findElement(By.id("add-professor-btn")).click();
    }

    @SneakyThrows
    @And("^New form is displayed in browser")
    public void newFormIsDisplayedOnBrowser(){
        Assert.assertTrue(webDriver.findElement(By.xpath("//h2[contains(text(),'Add Professor')]")).getText().equalsIgnoreCase("Add Professor"));
    }

    @SneakyThrows
    @When("^Enter details in the form of professor$")
    public void enterDetailsInTheFormOfProfessor(){
        webDriver.findElement(By.id("name")).sendKeys("John Doe");
        webDriver.findElement(By.id("email")).sendKeys("johndoe@gmail.com");
        webDriver.findElement(By.id("dob")).sendKeys("26/10/1969");
        webDriver.findElement(By.id("mobile")).sendKeys("03333938575");
    }

    @SneakyThrows
    @Then("^Click submit after entering details$")
    public void clickSubmitOnForm(){
        Thread.sleep(2000);
        webDriver.findElement(By.id("save-professor-btn")).click();
        Thread.sleep(5000);

    }

}
