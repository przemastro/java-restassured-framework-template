# JavaRestAssured Test Framework
[![GitHub issues](https://img.shields.io/github/issues/przemastro/java-rest-assured-framework)](https://github.com/przemastro/java-rest-assured-framework/issues)
[![GitHub forks](https://img.shields.io/github/forks/przemastro/java-rest-assured-framework)](https://github.com/przemastro/java-rest-assured-framework/network)
[![GitHub stars](https://img.shields.io/github/stars/przemastro/java-rest-assured-framework)](https://github.com/przemastro/java-rest-assured-framework/stargazers)
[![Java version](https://img.shields.io/badge/Java-1.8-%23b07219)](https://github.com/przemastro/java-rest-assured-framework)

# Features
This is a demo version of framework I used for Rest API testing during development of Compliance project for Barclays Investment Bank. 
Created in the Page Object pattern style. 

1. API tests - Using RestAssured and TestNg frameworks
2. Dynamic Reports - Reporting library

# Installation

1. Open repo in your favourite IDE (I use Intellij because of built-in Maven) and set Project SDK to "java version 1.8.0_*"
2. Right click on pom.xml and set MAVEN project, you might need to re-import maven dependencies

# Run

Run maven build

# Usage

Example Test with POST method

        restFormLandingPage = new RestFormLandingPage()
                .postMethod(context, "example.json");


Example POST method body

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
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (HTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11")
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3")
        .post("/");

