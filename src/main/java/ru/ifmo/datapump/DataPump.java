package ru.ifmo.datapump;

import org.json.simple.parser.ParseException;
import ru.ifmo.datapump.exception.NotFoundException;
import ru.ifmo.datapump.model.Label;
import ru.ifmo.datapump.model.Task;
import ru.ifmo.datapump.model.TaskManager;
import ru.ifmo.datapump.service.ParseService;

import java.util.List;
import java.util.Scanner;

public class DataPump {

    private static ParseService parseService;

    public static void main(String[] args) {
        parseService = new ParseService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter URL of Github project:");
        String url = scanner.nextLine().trim();
//        String url = "https://github.com/octocat/hello-worId";
        System.out.println("Loading...");

        String parts[] = url.split("/");
        String apiUrl = parts[0] + "//api." + parts[2] + "/repos/" + parts[3] + "/" + parts[4];

        try {
            TaskManager taskManager = parseService.parseTaskManager(apiUrl);
            List<Task> tasks = parseService.parseTasks(apiUrl, taskManager);
            List<Label> labels = parseService.parseLabels(apiUrl);

            System.out.println(); // suspend there and see data values
        } catch (ParseException e) {
            System.out.println("Error during parsing");
            e.printStackTrace();
        } catch (NotFoundException e1) {
            System.out.println("Incorrect URL");
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// https://github.com/octocat/Hello-World
// https://github.com/octocat/hello-worId
// https://api.github.com/repos/octocat/Hello-World
// https://api.github.com/sahat/hackathon-starter/issues