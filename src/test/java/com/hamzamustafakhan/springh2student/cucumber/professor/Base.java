package com.hamzamustafakhan.springh2student.cucumber.professor;

import com.hamzamustafakhan.springh2student.cucumber.professor.stepdefinitions.ProfessorCreateSteps;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

public class Base {
    public static WebDriver webDriver;

    public void initialization(String url){
        if(webDriver == null){
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        }
        openBrowser(url);
    }

    public void dispose()
    {
        webDriver.quit();
    }

    private void openBrowser(String url)
    {
        webDriver.manage().window().maximize();
        webDriver.navigate().to(url);
    }
}
