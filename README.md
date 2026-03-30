# Automation Framework

A comprehensive Selenium-based test automation framework with advanced features for web testing, data management, and reporting.

## Features

### 🚀 Core Components
- **BasePage**: Complete Selenium actions library (50+ methods)
- **DataGenerator**: JavaFaker integration for realistic test data
- **LoggerHelper**: Comprehensive logging system
- **WaitHelper**: Smart wait strategies
- **ScreenshotHelper**: Automatic screenshot capture

### 📊 Data Management
- **ExcelHelper**: Read/write Excel files with Apache POI
- **GoogleSheetsHelper**: Cloud-based data management with Google Sheets API
- **ConfigReader**: Property file management

### 🧪 Test Structure
- **TestNG**: Test execution framework
- **Cucumber**: BDD support with Gherkin
- **Page Object Model**: Clean page abstraction
- **Hooks**: Test lifecycle management

### 🔄 CI/CD Pipeline
- **GitHub Actions**: Automated testing and deployment
- **Multi-Java Version Support**: Java 11 & 17
- **Code Quality Checks**: SonarCloud, Checkstyle, SpotBugs
- **Security Scanning**: Trivy vulnerability scanner
- **Test Reports**: Cucumber HTML reports & JaCoCo coverage

## CI/CD Pipeline

### 🚀 Automated Workflows

#### **1. CI Pipeline** (`.github/workflows/ci.yml`)
- ✅ Multi-Java version testing (11, 17)
- ✅ Maven compilation & testing
- ✅ Google Sheets integration tests
- ✅ Test result reporting
- ✅ Build artifact generation

#### **2. Cucumber Tests** (`.github/workflows/cucumber-tests.yml`)
- ✅ BDD scenario execution
- ✅ Chrome/ChromeDriver setup
- ✅ Google Sheets API integration
- ✅ Cucumber HTML reports
- ✅ GitHub Pages deployment

#### **3. Code Quality** (`.github/workflows/code-quality.yml`)
- ✅ Checkstyle validation
- ✅ SpotBugs static analysis
- ✅ SonarCloud code analysis
- ✅ JaCoCo code coverage
- ✅ Trivy security scanning

#### **4. Deployment** (`.github/workflows/deploy.yml`)
- ✅ Production build
- ✅ Deployment package creation
- ✅ Staging environment deployment
- ✅ Smoke test execution
- ✅ Slack notifications

### 📊 Pipeline Triggers

| Event | Trigger | Pipeline |
|-------|----------|-----------|
| Push to master | ✅ | All pipelines |
| Pull Request | ✅ | CI, Quality, Cucumber |
| Manual dispatch | ✅ | Deployment |

### 🔧 Required GitHub Secrets

Add these secrets to your GitHub repository:

```bash
# Google Sheets API Key
GOOGLE_SHEETS_API_KEY=your_google_sheets_api_key

# SonarCloud Token
SONAR_TOKEN=your_sonarcloud_token

# Slack Webhook (optional)
SLACK_WEBHOOK=your_slack_webhook_url
```

### 📈 Pipeline Metrics

- **Test Execution**: ~2-3 minutes
- **Code Coverage**: Target >80%
- **Build Success Rate**: Automated monitoring
- **Security Scanning**: Every push/PR

## Project Structure

```
src/test/java/com/shirkhan/
├── base/
│   ├── BasePage.java          # Core Selenium actions
│   └── Hooks.java             # Test lifecycle hooks
├── pages/
│   └── FormPage.java          # Page object example
├── utils/
│   ├── DataGenerator.java     # Test data generation
│   ├── ExcelHelper.java       # Excel operations
│   ├── GoogleSheetsHelper.java # Google Sheets API
│   ├── LoggerHelper.java      # Logging utilities
│   ├── ScreenshotHelper.java  # Screenshot capture
│   └── WaitHelper.java        # Wait strategies
├── tests/
│   ├── ExcelIntegrationTest.java
│   └── GoogleSheetsIntegrationTest.java
└── stepDefinitions/
    └── ui/FormSteps.java       # BDD step definitions
```

## Quick Start

### Prerequisites
- Java 11+
- Maven 3.6+
- Chrome/Firefox browser

### Installation
```bash
git clone <repository-url>
cd automation-framework
mvn clean install
```

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ExcelIntegrationTest

# Run with TestNG XML
mvn test -DsuiteXmlFile=testng.xml
```

## Data Management Options

### 1. Excel Files
```java
// Read Excel data
List<String> data = ExcelHelper.readExcelData("data.xlsx", "Sheet1", 1);

// Write to Excel
ExcelHelper.writeToExcel("output.xlsx", "Results", testData);
```

### 2. Google Sheets
```java
// Setup: Add credentials.json and update SPREADSHEET_ID

// Read from Google Sheets
List<List<String>> data = GoogleSheetsHelper.readSheetData("Sheet1!A:Z");

// Write to Google Sheets
GoogleSheetsHelper.writeSheetData("Sheet1!A1", testData);
```

### 3. Generated Data
```java
// Generate test data
String firstName = DataGenerator.randomFirstName();
String email = DataGenerator.randomEmail();
String password = DataGenerator.randomPassword();
```

## BasePage Actions

### Element Interactions
- `clickElement(By locator)`
- `sendKeys(By locator, String text)`
- `getText(By locator)`
- `isElementDisplayed(By locator)`

### Form Actions
- `selectDropdownByVisibleText(By locator, String text)`
- `uploadFile(By locator, String filePath)`
- `checkCheckbox(By locator)`
- `uncheckCheckbox(By locator)`

### Mouse & Keyboard
- `hoverOverElement(By locator)`
- `doubleClick(By locator)`
- `rightClick(By locator)`
- `dragAndDrop(By sourceLocator, By targetLocator)`

### Window & Alert Management
- `switchToWindow(int windowIndex)`
- `acceptAlert()`
- `dismissAlert()`
- `getAlertText()`

### Advanced Waits
- `waitForElementToDisappear(By locator)`
- `waitForTextToBePresent(By locator, String text)`
- `waitForAttributeToContain(By locator, String attribute, String value)`

## Configuration

### Browser Configuration
Edit `src/test/resources/config.properties`:
```properties
browser=chrome
headless=false
timeout=10
base_url=https://example.com
```

### Google Sheets Setup
1. Create Google Cloud Project
2. Enable Google Sheets API
3. Create Service Account
4. Download credentials.json
5. Update SPREADSHEET_ID in GoogleSheetsHelper.java

## Dependencies

- Selenium 4.18.1
- TestNG 7.9.0
- Cucumber 7.15.0
- Apache POI 5.2.5
- Google Sheets API
- JavaFaker 1.0.2
- WebDriverManager 5.7.0

## Contributing

1. Fork the repository
2. Create feature branch
3. Make changes
4. Add tests
5. Submit pull request

## License

This project is licensed under the MIT License.
