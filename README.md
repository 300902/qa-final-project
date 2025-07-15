
# QA Automation Portfolio Project

## Overview
This repository demonstrates a complete QA Automation framework for both API and Web UI testing, suitable for professional portfolio and real-world projects. The solution uses industry-standard tools and best practices for maintainable, scalable, and robust automated testing.

## Tools Used
- **Cucumber**: BDD framework for writing executable specifications in Gherkin syntax.
- **Selenium**: Browser automation for end-to-end UI testing.
- **Gradle**: Build tool for dependency management and running automated tests.

## Project Structure
```
qa-final-project/
├── src/test/resources/features/      # Gherkin feature files
│   ├── api/                         # API test scenarios
│   └── web/                         # Web UI test scenarios (login, add_cart, place_order)
├── src/test/java/pages/             # Page Object Model classes (LoginPage, CartPage, OrderPage)
├── src/test/java/stepDefinitions/   # Step Definitions for Cucumber
│   ├── api/                         # API step definitions
│   └── web/                         # Web UI step definitions
├── build.gradle                     # Gradle build and dependency configuration
├── README.md                        # Project documentation
└── build/reports/cucumber/          # Cucumber test reports (HTML & JSON)
```

## How to Run Tests
1. Ensure you have Java, Chrome, and ChromeDriver installed on your system.
2. To run all Web UI scenarios:
   ```bash
   ./gradlew test --tests "*RunCucumberTest" -Dcucumber.filter.tags="@web"
   ```
3. To run all API scenarios:
   ```bash
   ./gradlew test --tests "*RunCucumberTest" -Dcucumber.filter.tags="@api"
   ```
4. Test results and Cucumber reports will be generated in `build/reports/cucumber/` (HTML and JSON formats).

## Features
- **Gherkin Scenarios**: All test cases are written in Gherkin for clarity and collaboration.
- **Tag Filtering**: Use `@web` and `@api` tags to selectively run Web UI or API tests.
- **Page Object Model**: All UI interactions are abstracted in POM classes for maintainability.
- **Comprehensive Reporting**: Automated HTML and JSON reports for every test run.
- **Consistent Structure**: Clear separation of features, pages, and step definitions for scalability.

## Continuous Integration
This project includes a sample GitHub Actions workflow (`.github/workflows/main.yml`) for automated test execution on every push or pull request. The workflow runs Gradle tests and publishes Cucumber reports as build artifacts.

## Example Workflow: .github/workflows/main.yml
```yaml
name: QA Automation CI
on:
  push:
    branches: [ main, feature/* ]
  pull_request:
    branches: [ main ]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v3
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
      - name: Install Chrome
        uses: browser-actions/setup-chrome@v1
      - name: Run Gradle tests
        run: ./gradlew test --tests "*RunCucumberTest" -Dcucumber.filter.tags="@web"
      - name: Upload Cucumber HTML Report
        uses: actions/upload-artifact@v3
        with:
          name: cucumber-report
          path: build/reports/cucumber/cucumber-report.html
      - name: Upload Cucumber JSON Report
        uses: actions/upload-artifact@v3
        with:
          name: cucumber-report-json
          path: build/reports/cucumber/cucumber-report.json
```


---
For questions or collaboration, feel free to contact or fork this repository.
