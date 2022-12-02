package com.hamzamustafakhan.springh2student.cucumber.student;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"json:target/cucumber.json", "pretty","html:target/cucumber-report-html/cucumber-html-reports/cucumber-student.html"},
features = "src/test/java/com/hamzamustafakhan/springh2student/cucumber/student/features",
        glue = {"com.hamzamustafakhan.springh2student.cucumber.student.stepdefinitions","com.hamzamustafakhan.springh2student"})
public class RunStudentTests {
}
