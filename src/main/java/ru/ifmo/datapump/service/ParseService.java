package ru.ifmo.datapump.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ru.ifmo.datapump.model.*;
import ru.ifmo.datapump.model.enums.TaskManagerType;
import ru.ifmo.datapump.model.enums.TaskStatus;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ParseService {
    private HttpResponseService httpResponseService;

    public ParseService() {
        this.httpResponseService = new HttpResponseService();
    }

    public TaskManager parseTaskManager(String apiUrl) throws Exception {
        String jsonString = httpResponseService.getJSON(apiUrl, 5000);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);

        return new TaskManager(Long.parseLong(jsonObject.get("id").toString()),
                jsonObject.get("name").toString(), jsonObject.get("description").toString(),
                TaskManagerType.GitHub,
                jsonObject.get("node_id").toString());
    }

    public List<Task> parseTasks(String apiUrl, TaskManager taskManager) throws Exception {
        List<Task> tasks = new ArrayList<>();

        List<TaskToLabel> taskToLabels = new ArrayList<>();

        int counter = 1;
//        boolean isOpen = true;
        do {
            String jsonString;
//            if (isOpen) {
            jsonString = httpResponseService.getJSON(apiUrl + "/issues?page=" + counter, 5000);
//            } else {
//                break;
//                jsonString = httpResponseService.getJSON(apiUrl + "/issues?page=" + counter + "&q=is%3Aissue+is%3Aclosed", 5000);
//            }

//            if (jsonString.equals("[]\n") && isOpen) {
//                isOpen = false;
//                counter = 0;
//            } else if (jsonString.equals("[]\n")) {
            if (jsonString.equals("[]\n")) {
                break;
            }

            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(jsonString);

            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;

                JSONObject jsonTaskAuthorObject = (JSONObject) jsonObject.get("user");
                String userUrl = jsonTaskAuthorObject.get("url").toString();

                String userProfileJson = httpResponseService.getJSON(userUrl, 5000);
                Object userEmailObject = ((JSONObject) jsonParser.parse(userProfileJson)).get("email");
                String userEmail = userEmailObject == null ? null : userEmailObject.toString();

                TaskAuthor taskAuthor = new TaskAuthor(Long.parseLong(jsonTaskAuthorObject.get("id").toString()),
                        jsonTaskAuthorObject.get("login").toString(),
                        userEmail,
                        taskManager // у каждого юзера должен быть ровно один репозиторий ?
                );

                // Get labels
                List<Label> labels = new ArrayList<>();
                JSONArray jsonLabelArray = (JSONArray) jsonObject.get("labels");
                for (Object labelObject : jsonLabelArray) {
                    JSONObject jsonLabelObject = (JSONObject) labelObject;

                    labels.add(new Label(Long.parseLong(jsonLabelObject.get("id").toString()), jsonLabelObject.get("name").toString()));
                }

                tasks.add(new Task(Long.parseLong(jsonObject.get("id").toString()),
                        jsonObject.get("title").toString(),
                        null,
                        Date.valueOf(jsonObject.get("created_at").toString().split("T")[0]),
                        null,
                        TaskStatus.getStatus(jsonObject.get("state").toString()),
                        null,
                        null,
                        taskAuthor,
                        taskManager, labels));
            }
            counter++;

        } while (true);

        return tasks;
    }

    public List<Label> parseLabels(String apiUrl) throws Exception {
        List<Label> labels = new ArrayList<>();

        String jsonString = httpResponseService.getJSON(apiUrl + "/labels", 5000);

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(jsonString);

        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;

            labels.add(new Label(Long.parseLong(jsonObject.get("id").toString()), jsonObject.get("name").toString()));
        }

        return labels;
    }
}
