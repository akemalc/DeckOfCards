 DeckOfCards
 Inside of the link, there are 2 different projects.
 Please download them separately.

1. Cucumber With Gherkin
    Tools;
        Maven - Java - Cucumber - JUnit - Rest-Assured - Gson

        How to run;
        # Run and Get .html and .xml reports
        mvn verify

        Check Target package

2. JUnit5
    Tools;  
        Maven - Java - JUnit - Rest-Assured - Gson

    How to run;
    # Run tests and generate .xml reports
    mvn test

    #Convert .xml reports into .html report, but without the CSS or images
    mvn surefire-report:report-only

    #Put the CSS and images
    mvn site -DgenerateReports=false

    Check site package inside Target package