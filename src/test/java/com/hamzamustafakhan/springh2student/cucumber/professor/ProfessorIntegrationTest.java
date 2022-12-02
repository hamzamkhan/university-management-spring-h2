package com.hamzamustafakhan.springh2student.cucumber.professor;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/hamzamustafakhan/springh2student/cucumber/professor/features",
glue = {"com.hamzamustafakhan.springh2student.cucumber.professor.stepdefinitions","com.hamzamustafakhan.springh2student"},
        plugin = {"json:target/cucumber.json", "pretty"})
public class ProfessorIntegrationTest {
}