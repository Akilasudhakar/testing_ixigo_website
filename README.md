# testing_ixigo_website
# Ixigo Automation Testing

## Prerequisites
- Java 11+
- Maven 3.6+
- Chrome / Edge browser

## How to Run
1. Clone the repository
2. Install dependencies: `mvn clean install`
3. Execute tests: `mvn test`
4. Reports will be available under `reports`

## Test Data
- Located in `src/test/resources/TestData.xlsx`
- Add new test cases by updating this file

## Reports
- Cucumber HTML Report: `target/cucumber-report.html`
- Extent Report: `target/reports/ExtentReport.html`
