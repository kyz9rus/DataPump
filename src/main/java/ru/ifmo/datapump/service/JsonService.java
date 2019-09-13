package ru.ifmo.datapump.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.ifmo.datapump.exception.CustomJsonException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

class JsonService {
    private final static Logger log = LogManager.getLogger(JsonService.class);

    String getJson(String urlString, int timeout) throws CustomJsonException {
        HttpURLConnection connection = null;
        try {
            URL url; url = new URL(urlString);

            connection = (HttpURLConnection) url.openConnection();
            fillConnection(connection, timeout);

            int status = connection.getResponseCode();

            return getJsonContent(connection, status);
        } catch (IOException e) {
            log.error(e);
            throw new CustomJsonException("Error while creating connection " + connection);
        }
    }

    private void fillConnection(HttpURLConnection connection, int timeout) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("/Users/mackyz9/Documents/GIT/DataPump/src/main/resources/request.properties")); // relative doesn't work now

        connection.setRequestMethod(properties.getProperty("method"));
        connection.setRequestProperty("Content-length", properties.getProperty("content-length"));
        connection.setRequestProperty("Authorization", properties.getProperty("token"));
        connection.setUseCaches(Boolean.parseBoolean(properties.getProperty("use-caches")));
        connection.setAllowUserInteraction(Boolean.parseBoolean(properties.getProperty("allow-user-interaction")));
        connection.setConnectTimeout(timeout);
        connection.setReadTimeout(timeout);
        connection.connect();
    }

    private String getJsonContent(HttpURLConnection c, int status) throws CustomJsonException {
        switch (status) {
            case 200:
            case 201:
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(c.getInputStream()))) {
                    StringBuilder sb = new StringBuilder();

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }

                    return sb.toString();
                } catch (IOException e) {
                    log.error(e);
                }
                break;
            default:
                log.error("Error has occured. Status code is " + status);
        }

        throw new CustomJsonException("Error while getting content");
    }
    
}
