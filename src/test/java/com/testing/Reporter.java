package com.testing;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Reporter extends TestListenerAdapter {

    private double time;
    private String dateValue;
    private String failureMessage;
    private String failedMethod;
    private static String testName;
    private String nameBuild;
    private String fail;
    private BigDecimal bd;
    private String timeExecution;
    private String testResult;
    private static List<String[]> report = new ArrayList<String[]>();


    @Override
    public void onTestSuccess(ITestResult tr) {
        failedMethod = "No significant error has been found";
        failureMessage = "";
        time = (tr.getEndMillis() - tr.getStartMillis());
        testName = tr.getName();
        testResult = "Passed";
        log();
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        if(tr.getTestContext().getAttribute("method") != null) {
            failedMethod = tr.getTestContext().getAttribute("method").toString();
        }
        System.out.println("failure " + failedMethod);
        time = (tr.getEndMillis() - tr.getStartMillis());
        testName = tr.getName();
        failureMessage = tr.getThrowable().toString();
        testResult = "Failed";
        log();
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        time = (tr.getEndMillis() - tr.getStartMillis());
        testName = tr.getName();
        testResult = "Skipped";
    }

    public static String[][] getReport() {
        return report.toArray(new String[0][0]);
    }

    private void log() {
        time = (time/1000);
        bd = new BigDecimal((time));
        bd = bd.setScale(2, RoundingMode.CEILING);
        timeExecution = bd.toString();
        System.out.println(timeExecution);
        nameBuild = dateValue;
        fail = failedMethod + "\n" + failureMessage;
        report.add(new String[]{
                testName,
                "Automated",
                testResult,
                timeExecution,
                fail
        });
    }
}
