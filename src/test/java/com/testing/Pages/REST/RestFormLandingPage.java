package com.testing.Pages.REST;

import com.testing.MainREST;
import static com.jayway.restassured.RestAssured.*;
import org.testng.ITestContext;

import java.sql.*;
import java.util.Scanner;
import java.io.File;

import com.jayway.restassured.response.Response;

public class RestFormLandingPage extends MainPageRest {

    private String methodName;
    public Response response;
    public int i;
    public String investmentName;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;
    private Statement stmt;
    private String data;
    private String browser;
    public int maxIDNumber;
    public int previousMaxIDNumber;


    public RestFormLandingPage postFormAndVerifyStatusCode(ITestContext context, String correctForm) throws InterruptedException, Exception {
        methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        context.setAttribute("method", methodName);
        String myJson = new Scanner(new File(MainREST.pathResources + correctForm)).useDelimiter("\\Z").next();

        expect()
                .statusCode(201)
                .log().ifError()
        .given()
                .contentType("application/json")
                .body(myJson)
        .when().with()
                .header("Access-Control-Request-Headers", "content-type")
                .header("Connection", "keep-alive")
                .header("Accept-Language", "en-US,en;q=0.8")
                .header("Host", "localhost:5000")
                .header("Access-Control-Request-Method", "POST")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("Origin", "http://localhost:63342")
                .header("Referer", "http://localhost:63342/TestApp/testApp/angular/app/index.html")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (HTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11")
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3")
        .post("/");
    return this;
    }


    public RestFormLandingPage postWrongFormAndVerifyStatusCode(ITestContext context, String incorrectForm) throws InterruptedException, Exception {
        methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        context.setAttribute("method", methodName);
        String myJson = new Scanner(new File(MainREST.pathResources + incorrectForm)).useDelimiter("\\Z").next();

        expect()
                .statusCode(201)
                .log().ifError()
                .given()
                .contentType("application/json")
                .body(myJson)
                .when().with()
                .header("Access-Control-Request-Headers", "content-type")
                .header("Connection", "keep-alive")
                .header("Accept-Language", "en-US,en;q=0.8")
                .header("Host", "localhost:5000")
                .header("Access-Control-Request-Method", "POST")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("Accept", "application/json, text/plain, */*")
                .header("Origin", "http://localhost:63342")
                .header("Referer", "http://localhost:63342/TestApp/testApp/angular/app/index.html")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (HTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11")
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3")
                .post("/");
        return this;
    }

    public RestFormLandingPage uploadCorrectFileWithBadRequest(ITestContext context, String fileName) throws InterruptedException, Exception {
        methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        context.setAttribute("method", methodName);

        File file = new File(MainREST.pathResources + fileName);

        expect()
                .statusCode(400)
                .log().ifError()
        .given()
                .multiPart(file)
                .contentType("pdf type")//to be corrected for PDF
        .when().with()
                .header("Access-Control-Request-Headers", "origin, content-type, accept")
                .header("Connection", "keep-alive")
                .header("Accept-Language", "en-US,en;q=0.8")
                .header("Host", "localhost:5000")
                .header("Access-Control-Request-Method", "POST")
                .header("Content-Type", "application/json;charset=UTF-8")//to be corrected fpr PDF
                .header("Accept", "application/json, text/plain, */*")
                .header("Origin", "http://localhost:63342")
                .header("Referer", "http://localhost:63342/TestApp/testApp/angular/app/index.html")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (HTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11")
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3")
        .post("/file");

        return this;
    }
}
