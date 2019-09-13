package ru.ifmo.datapump;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.ifmo.datapump.exception.CustomJsonException;
import ru.ifmo.datapump.exception.CustomParseException;
import ru.ifmo.datapump.model.Label;
import ru.ifmo.datapump.model.Task;
import ru.ifmo.datapump.model.TaskManager;
import ru.ifmo.datapump.service.ParseService;

import java.util.List;
import java.util.Scanner;

public class DataPump {

    private final static Logger log = LogManager.getLogger(DataPump.class);

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

            System.out.println(); // suspend there and check data values
        } catch (CustomParseException | CustomJsonException e) {
            log.error(e);
        }
    }
}

// https://github.com/octocat/Hello-World
// https://github.com/octocat/hello-worId
// https://api.github.com/repos/octocat/Hello-World
// https://api.github.com/sahat/hackathon-starter/issues