package ru.ifmo.datapump;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ifmo.datapump.model.TaskAuthor;
import ru.ifmo.datapump.model.TaskManager;
import ru.ifmo.datapump.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class DataPump {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void main(String[] args) {
        setup();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter URL of Github project:");
        String url = scanner.nextLine().trim();

        TaskManager taskManager = getTaskManager(url);
        List<TaskAuthor> taskAuthors = getTaskAuthors(url, taskManager);

        close();
    }

    private static List<TaskAuthor> getTaskAuthors(String url, TaskManager taskManager) {
        List<TaskAuthor> taskAuthors = new ArrayList<>();

        driver.get(url + "/graphs/contributors");

        WebElement webElement = wait.until(visibilityOfElementLocated(By.className("contrib-data")));
        List<WebElement> contributorElements = webElement.findElements(By.xpath("//a[@class=\"text-normal\"]"));
        List<String> contributorNames = new ArrayList<>();

        for (WebElement contributor : contributorElements) {
            contributorNames.add(contributor.getText());
        }

        for (String contributorName : contributorNames) {
            wait.until(visibilityOfElementLocated(By.xpath("//a[@href=\"/" + contributorName + "\"]"))).click();

            String email;
            try {
                email = wait.until(visibilityOfElementLocated(By.xpath("//a[@class=\"u-email \"]"))).getText();
            } catch (TimeoutException e) {
                email = null;
            }

            taskAuthors.add(new TaskAuthor(contributorName, email, taskManager));
            driver.navigate().back();
        }

        return taskAuthors;
}

    private static TaskManager getTaskManager(String url) {
        driver.get(url);

        String name = url.trim().split("/")[4];
        String description = wait.until(visibilityOfElementLocated(By.className("f4"))).getText();

        return new TaskManager(name, description, null);
    }

    private static void setup() {
        System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");

        driver = new SafariDriver();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 2);
        driver.manage().window().maximize();

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