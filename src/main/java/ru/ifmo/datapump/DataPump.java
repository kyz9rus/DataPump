package ru.ifmo.datapump;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ifmo.datapump.model.Label;
import ru.ifmo.datapump.model.Task;
import ru.ifmo.datapump.model.TaskAuthor;
import ru.ifmo.datapump.model.TaskManager;
import ru.ifmo.datapump.service.SeleniumService;
import ru.ifmo.datapump.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class DataPump {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static SeleniumService seleniumService;

    public static void main(String[] args) {
        setup();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter URL of Github project:");
        String url = scanner.nextLine().trim();

        TaskManager taskManager = seleniumService.getTaskManager(url);
        Map<String, TaskAuthor> taskAuthors = seleniumService.getTaskAuthors(url, taskManager);
        Map<String, Label> labels = seleniumService.getLabels(url);
        Map<String, Task> tasks = seleniumService.getTasks(url, taskManager, taskAuthors);

        close();
    }

    private static void setup() {
        System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");

        driver = new SafariDriver();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 2);
        driver.manage().window().maximize();

        seleniumService = new SeleniumService(driver, wait);

        driver.get("https:github.com");
        signIn();
    }

    private static void signIn() {
        wait.until(visibilityOfElementLocated(By.xpath("//a[@href=\"/login\"]"))).click();
        wait.until(visibilityOfElementLocated(By.id("login_field"))).sendKeys(Constants.LOGIN);
        wait.until(visibilityOfElementLocated(By.id("password"))).sendKeys(Constants.PASSWORD);
        wait.until(visibilityOfElementLocated(By.xpath("//input[@value=\"Sign in\"]"))).click();
    }

    private static void close() {
        driver.quit();
    }
}


// https://github.com/facebook/hermes