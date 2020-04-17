package com.assignment.imdb.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfiguration {
    Properties properties;

    public ReadConfiguration() {
        File file = new File("./Configuration//configuration.properties");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public String getSearchString() {
        return properties.getProperty("searchString");
    }

    public String getBrowserType() {
        return properties.getProperty("browserType");
    }

    public String getMaximizeWindow() {
        return properties.getProperty("maximizeWindow");
    }

    public String getDefaultTimeout() {
        return properties.getProperty("defaultTimeout");
    }

    public String getLoggerFileName() {
        return properties.getProperty("loggerFileName");
    }

    public String getExtentReportPath() {
        return properties.getProperty("extentReportPath");
    }

    public String getReportsPath() {
        return properties.getProperty("reportsPath");
    }
}
