package com.assignment.imdb.tests;

import com.assignment.imdb.utilities.ReadConfiguration;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class RootClass {

    String searchString;
    Logger logger;
    ReadConfiguration readConfiguration;

    @Parameters("browserType")
    @BeforeClass
    public void initializeTestRun(String browserType) {
        readConfiguration = new ReadConfiguration();
        Configuration.browser = browserType;
        Configuration.startMaximized = Boolean.parseBoolean(readConfiguration.getMaximizeWindow());
        Configuration.timeout = Long.parseLong(readConfiguration.getDefaultTimeout());
        Configuration.reportsFolder = readConfiguration.getReportsPath();
        Configuration.screenshots = false;
        logger = LoggerFactory.getLogger(readConfiguration.getLoggerFileName());
        searchString = readConfiguration.getSearchString();
    }

    @AfterClass
    public void terminateTestRun() {
        WebDriverRunner.closeWebDriver();
    }
}
