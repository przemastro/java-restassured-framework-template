# JavaRestFramework
[![GitHub issues](https://img.shields.io/github/issues/przemastro/java-rest-assured-framework)](https://github.com/przemastro/java-rest-assured-framework/issues)

Java + TestNG + RestAssured + DynamicReports

This is a demo version of framework I used for Rest API testing during development of Compliance project for Barclays Investment Bank. 
Created in the Page Object pattern style. It is prepared for https://github.com/przemastro/testApp.git application but obviously can be addapted to any application which uses rest api.
It consists of following:

Java classes

    MainREST - initial class for initializing properties and creating report
    RestFormLandingPage - page object methods
    RestForm - tests
    Reporter - listeners
    ReportTemplate - to show report in nice way


Properties file

    profile_przemek.properties - file with properties like (baseURI, file paths, paths for Reports, flags)


XML file 

    rest-smoke-tests - to organize tests execution


Source files 

    json and pdf files for testing purposes

