package ru.ifmo.datapump.service;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ifmo.datapump.model.Label;
import ru.ifmo.datapump.model.Task;
import ru.ifmo.datapump.model.TaskAuthor;
import ru.ifmo.datapump.model.TaskManager;
import ru.ifmo.datapump.model.enums.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SeleniumService {
    private WebDriver driver;
    private WebDriverWait wait;

    public SeleniumService(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public Map<String, TaskAuthor> getTaskAuthors(String url, TaskManager taskManager) {
        Map<String, TaskAuthor> taskAuthors = new HashMap<>();

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

            taskAuthors.put(contributorName, new TaskAuthor(contributorName, email, taskManager));
            driver.navigate().back();
        }

        return taskAuthors;
    }

    public TaskManager getTaskManager(String url) {
        driver.get(url);

        String name = url.trim().split("/")[4];
        String description = wait.until(visibilityOfElementLocated(By.className("f4"))).getText();

        return new TaskManager(name, description, null);
    }

    public Map<String, Task> getTasks(String url, TaskManager taskManager, Map<String, TaskAuthor> taskAuthors) {
        driver.get(url + "/issues");

        Map<String, Task> tasks = new HashMap<>();

        // --- OPEN TASKS
        List<WebElement> openTaskElements = driver.findElements(By.xpath("//div[contains(@id, \"issue_\")]"));
        List<String> taskIds = new ArrayList<>();

        for (WebElement openTaskElement : openTaskElements) {
            taskIds.add(openTaskElement.findElement(By.xpath("//a[contains(@id, \"issue_\")]")).getAttribute("id"));
        }

        for (String taskId : taskIds) {
            driver.findElement(By.id(taskId)).click();

            String name = wait.until(visibilityOfElementLocated(By.xpath("//span[@class=\"js-issue-title\"]"))).getText();
            String createDate = wait.until(visibilityOfElementLocated(By.xpath("//a[contains(@class, \"author\")]"))).findElement(By.xpath("//relative")).getAttribute("datetime");



            tasks.put("", new Task());
        }

        // --- CLOSED TASKS

        List<WebElement> closedTaskElements = driver.findElements(By.xpath("//div[contains(@id, \"issue_\")]"));
        taskIds = new ArrayList<>();

        for (WebElement closedTaskElement : closedTaskElements) {
            taskIds.add(closedTaskElement.findElement(By.xpath("//a[contains(@id, \"issue_\")]")).getAttribute("id"));
        }

        for (String taskId : taskIds) {
            driver.findElement(By.id(taskId)).click();

            tasks.put("", new Task());
        }


    }

    public Map<String, Label> getLabels(String url,) {
        driver.get(url + "/labels");
        Map<String, Label> labels = new HashMap<>();

        List<WebElement> labelElements = driver.findElements(By.xpath("//ul[contains(@class, \"table-list\")]"));
        for (WebElement label : labelElements) {
            String labelName = label.findElement(By.xpath("//span[@class=\"label-name\"]")).getText();
            labels.put(labelName, new Label(labelName));
        }

        return labels;
    }
}
