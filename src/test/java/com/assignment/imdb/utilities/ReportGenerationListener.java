package com.assignment.imdb.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.screenshot;

public class ReportGenerationListener extends TestListenerAdapter {
    LocalDateTime localDateTime;
    DateTimeFormatter dateTimeFormatter;
    StringBuilder stringBuilder;
    ExtentSparkReporter extentSparkReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest
                .createNode(result.getMethod().getDescription())
                .pass(String.format("Test has passed. For Records, Screenshot for the Passed Test Case is kept at: %s", takeScreenCapture()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest
                .createNode(result.getMethod().getDescription())
                .fail(String.format("Test has failed. For Reference, Screenshot for the Failed Test Case is kept at: %s", takeScreenCapture()))
                .fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest
                .createNode(result.getMethod().getDescription())
                .skip("Test has skipped. Possible reason would be Dependent Tests might have failed.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
        localDateTime = LocalDateTime.now();
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        stringBuilder = new StringBuilder();
        stringBuilder.append("./AutomationExecutionReports//");
        stringBuilder.append("AutomationExecutionReport_")
                .append(localDateTime.format(dateTimeFormatter))
                .append(".html");
        extentSparkReporter = new ExtentSparkReporter(stringBuilder.toString());
        extentSparkReporter.config().setDocumentTitle("IMDb Test Suite Automation Execution Report");
        extentSparkReporter.config().setReportName("IMDb Test Automation Execution Report");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Application Name", "IMDb Test Suite");
        extentReports.setSystemInfo("Platform", System.getProperty("os.name"));
        extentReports.setSystemInfo("Environment", "Production");
        extentTest = extentReports.createTest(context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

    public String takeScreenCapture() {
        localDateTime = LocalDateTime.now();
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        stringBuilder = new StringBuilder();
        stringBuilder.append("ScreenCapture_")
                .append(localDateTime.format(dateTimeFormatter));
        screenshot(stringBuilder.toString());
        stringBuilder.append(".png");
        stringBuilder.insert(0,System.getProperty("user.dir"));
        stringBuilder.insert(System.getProperty("user.dir").length(), "\\ScreenCaptures\\");
        return stringBuilder.toString();
    }
}
