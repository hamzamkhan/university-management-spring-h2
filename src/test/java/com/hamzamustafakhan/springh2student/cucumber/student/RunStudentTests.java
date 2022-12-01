package com.hamzamustafakhan.springh2student.cucumber.student;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
features = "src/test/java/com/hamzamustafakhan/springh2student/cucumber/student/features",
        glue = {"com.hamzamustafakhan.springh2student.cucumber.student","com.hamzamustafakhan.springh2student"})
public class RunStudentTests {
}
