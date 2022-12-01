package com.hamzamustafakhan.springh2student.cucumber.professor.stepdefinitions;

import com.hamzamustafakhan.springh2student.cucumber.professor.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class ProfessorUpdateAndDeleteSteps extends Base {
    private static String PROFESSOR_URL = "http://localhost:3000/professors";
    @SneakyThrows
    @When("^Clicks on edit for created professor$")
    public void clickOnCreatedProfessor(){
        Thread.sleep(5000);
        if(webDriver == null){
            initialization(PROFESSOR_URL);
        }

        WebElement edit = webDriver.findElement(By.cssSelector("div.container-fluid table.mt-2.table:nth-child(3) tbody:nth-child(2) tr:nth-child(1) td:nth-child(3) div.btn-group > a.btn.btn-primary.btn-sm:nth-child(1)"));
        Assert.assertTrue(edit.getText().equalsIgnoreCase("Edit"));
        edit.click();
    }

    @SneakyThrows
    @And("^Professor details displayed$")
    public void professorDetailsDisplayed(){
        Thread.sleep(5000);
        Assert.assertEquals(webDriver.findElement(By.xpath("//input[@id='name']")).getAttribute("value"),"John Doe");
    }

    @SneakyThrows
    @Then("^Changes date of birth of professor$")
    public void changesDateOfBirthOfProfessor(){
        webDriver.findElement(By.id("dob")).sendKeys("26/07/1989");
    }

    @SneakyThrows
    @Then("^Click submit after editing details$")
    public void clickSubmitAfterEditForm(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        WebElement webElement = webDriver.findElement(By.id("save-professor-btn"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //Scroll down till the bottom of the page
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();

    }

    @SneakyThrows
    @When("Click on delete professor")
    public void clickOnDeleteProfessor(){
        Thread.sleep(2000);
        if(webDriver == null){
            System.out.println("Null mil raahaaaaaaaaaa");
        }
        Assert.assertEquals(webDriver.findElement(By.xpath("//button[contains(text(),'Delete')]")).getText(), "Delete");
        webDriver.findElement(By.xpath("//button[contains(text(),'Delete')]")).click();
    }

    //    @SneakyThrows
    @Then("^Professor gets deleted$")
    public void professorGetsDeleted() throws InterruptedException {
        try{
            Thread.sleep(2000);
            webDriver.findElement(By.xpath("//td[contains(text(),'John Doe')]"));
        } catch (Exception e){
            System.out.println("Element not found");
        } finally {
            Thread.sleep(5000);
            webDriver.quit();
        }

    }
}

