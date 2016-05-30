package com.testing;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.BooleanColumnBuilder;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.datatype.StringType;
import net.sf.dynamicreports.report.builder.expression.SystemMessageExpression;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.jayway.restassured.RestAssured.baseURI;


public class MainREST {

    public static String uriBase;
    private String pathRest;
    public String dateValue;
    public String nameBuild;
    public static final Date date = new Date();
    public static String pathResources;

    private PreparedStatement preparedStatement;
    private ResultSet rs;
    private Statement stmt;
    private String propertiesFileProfile = "profile_przemek";
    private boolean generateReport;

    public MainREST() {
        if (uriBase == null) {
            initializeProperties();
        }
    }

    private void initializeProperties() {
        Properties defaultProps = new Properties();
        Properties userProps = new Properties(defaultProps);
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        InputStream defaultStream = loader.getResourceAsStream(propertiesFileProfile+".properties");
        try {
            defaultProps.load(defaultStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        pathResources = defaultProps.getProperty("mainRest.pathResources");
        uriBase = defaultProps.getProperty("propertiesFileProfile.uriBase");
        pathRest = defaultProps.getProperty("mainRest.pathRest");
        baseURI = uriBase;
        generateReport = Boolean.parseBoolean(defaultProps.getProperty("mainRest.generateReport", "false"));
    }

    @BeforeSuite(enabled = true)
    public void createBuild() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        dateValue = dateFormat.format(date);
        nameBuild = dateValue;
        System.out.println("Build " + nameBuild + " has been created");
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        System.out.println("Test method being run: " + method.getName());
    }

    @AfterTest(enabled = true)
    public void createExecutionReport() {
        if(generateReport) {

            JasperReportBuilder report = DynamicReports.report();
            report
                    .setTemplate(ReportTemplate.reportTemplate)
                    .columns(Columns.column("Test Name", "test_name", DataTypes.stringType()),
                            Columns.column("Execution Type", "execution_type", DataTypes.stringType()),
                            Columns.column("Result", "result", DataTypes.stringType()),
                            Columns.column("Execution Duration", "execution_duration", DataTypes.stringType()),
                            Columns.column("Execution Notes", "execution_notes", DataTypes.stringType()))
                    .title(Components.text("Automated REST Tests Execution"))
                    .setDataSource(functionalTestsExecutionDataSource());

            try {
                report.toPdf(new FileOutputStream(pathRest + "rest_tests_execution_report-" + nameBuild + ".pdf"));
            } catch (DRException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private JRDataSource functionalTestsExecutionDataSource() {
        DRDataSource dataSource = new DRDataSource("test_name", "execution_type", "result", "execution_duration", "execution_notes");
        String[][] table = Reporter.getReport();
        for(int i=0; i<table.length; i++){
            dataSource.add(table[i][0], table[i][1], table[i][2], table[i][3], table[i][4]);
        }
        return dataSource;
    }
}
