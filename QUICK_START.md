# 🚀 QUICK REFERENCE - Cucumber BDD Framework

## Framework Status: ✅ PRODUCTION READY

---

## 📋 Quick Commands

```bash
# Navigate to project
cd /Users/shirkhanadgozalov/eclipse-workspace/mavenProject/automation-framework

# Run all tests
mvn clean test -Dtest=TestRunner

# Run in headless mode (CI)
mvn clean test -Dtest=TestRunner -Pci

# Run specific browser
mvn clean test -Dtest=TestRunner -Dbrowser=firefox

# Run with specific tags
mvn clean test -Dtest=TestRunner -Dtags="@smoke"

# Compile only (no tests)
mvn clean compile

# Clean build artifacts
mvn clean
```

---

## 📁 Key Project Locations

| Component | Path |
|-----------|------|
| Feature Files | `src/test/resources/features/saucedemo_login.feature` |
| Step Definitions | `src/test/java/com/shirkhan/stepDefinitions/` |
| Page Objects | `src/test/java/com/shirkhan/pages/` |
| Hooks | `src/test/java/com/shirkhan/base/Hooks.java` ✅ |
| Config | `src/test/resources/config.properties` |
| Test Runner | `src/test/java/com/shirkhan/runner/TestRunner.java` |
| Reports | `target/cucumber-report.html` |
| Screenshots | `screenshots/` |

---

## 🧪 Available Test Tags

```gherkin
@smoke         # Basic functionality tests
@regression    # Full feature tests
@data-driven   # DataGenerator tests
@multiple      # Multiple scenario tests
@visual        # Visual testing
@negative      # Error/failure cases
@row-based     # Excel row tests
@performance   # Performance tests
@validation    # Data validation tests
@saucedemo     # All SauceDemo tests
```

---

## ⚙️ Configuration Properties

**File:** `src/test/resources/config.properties`

```properties
baseUrl=https://www.saucedemo.com/
browser=chrome           # chrome, firefox, edge
headless=false          # true/false
```

---

## 🎯 Test Data (DataGenerator)

| ID | Username | Password | Type |
|----|----------|----------|------|
| TC001 | standard_user | secret_sauce | Valid User |
| TC002 | locked_out_user | secret_sauce | Locked |
| TC003 | problem_user | secret_sauce | Problem User |
| TC004 | performance_glitch_user | secret_sauce | Slow |
| TC005 | error_user | secret_sauce | Error User |
| TC006 | visual_user | secret_sauce | Visual User |

---

## 🔧 Recent Fixes (April 3, 2026)

✅ **Fixed NullPointerException** - Browser property null handling
✅ **Enhanced Error Handling** - Driver crash safety
✅ **Improved Screenshot Capture** - Null driver checks
✅ **GitHub Actions Update** - Node.js 24 compatibility

---

## 📊 Maven Build Profile

```xml
<!-- Default Profile (Normal Mode) -->
mvn clean test

<!-- CI Profile (Headless) -->
mvn clean test -Pci

<!-- With Coverage Report -->
mvn clean test jacoco:report
```

---

## 🌐 Supported Browsers

| Browser | Status | Setup |
|---------|--------|-------|
| Chrome | ✅ Default | WebDriverManager |
| Firefox | ✅ Supported | WebDriverManager |
| Edge | ✅ Supported | WebDriverManager |

**Usage:** `-Dbrowser=chrome|firefox|edge`

---

## 📈 Test Execution Matrix

```
Total Scenarios: 9
├── @smoke (1)
├── @regression (1)
├── @data-driven (1)
├── @multiple (1)
├── @visual (1)
├── @negative (1)
├── @row-based (1)
├── @performance (1)
└── @validation (1)
```

---

## 🔍 Verify Installation

```bash
# Check Java version
java -version
# Expected: Java 11+

# Check Maven
mvn --version
# Expected: Maven 3.6+

# Verify WebDriver setup
mvn dependency:tree | grep webdrivermanager
# Expected: webdrivermanager-5.7.0
```

---

## 📢 Reporting

### Local Report
```bash
mvn clean test
open target/cucumber-report.html
```

### CI/CD Report
- GitHub Actions → Actions tab → Latest run
- Artifacts: cucumber-artifacts (surefire-reports, screenshots)

### Coverage Report
```bash
mvn jacoco:report
open target/site/jacoco/index.html
```

---

## 🐛 Troubleshooting

### Issue: Browser not found
```bash
mvn clean test -Dbrowser=chrome  # Specify browser explicitly
```

### Issue: Tests timeout
```bash
# Increase wait time (in Hooks.java line 100)
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
```

### Issue: Screenshot not captured
```bash
# Check screenshots folder created
ls -la screenshots/
```

---

## 🔗 Dependencies

| Dependency | Version |
|------------|---------|
| Selenium | 4.18.1 |
| Cucumber | 7.15.0 |
| TestNG | 7.9.0 |
| WebDriverManager | 5.7.0 |
| Apache POI | 5.2.5 |
| JaCoCo | 0.8.11 |

---

## 🚀 First Time Setup

```bash
# 1. Clone repository
git clone <repo-url>
cd automation-framework

# 2. Install dependencies
mvn clean install

# 3. Run a test
mvn clean test -Dtest=TestRunner -Dtags="@smoke"

# 4. View report
open target/cucumber-report.html
```

---

## 📝 Git Commands

```bash
# Check status
git status

# Add all changes
git add -A

# Commit changes
git commit -m "Your message"

# Push to repository
git push origin main

# View logs
git log --oneline -10
```

---

## 💡 Pro Tips

1. **Run single scenario:**
   ```bash
   mvn test -Dtest=TestRunner "-Dcucumber.options=src/test/resources/features/saucedemo_login.feature:11"
   ```

2. **Debug mode:**
   ```bash
   mvn test -Dtest=TestRunner -X  # Verbose logging
   ```

3. **Skip tests during build:**
   ```bash
   mvn clean package -DskipTests
   ```

4. **Generate only reports:**
   ```bash
   mvn surefire-report:report
   open target/site/surefire-report.html
   ```

---

## 📞 Support

**Framework Version:** 1.0-SNAPSHOT
**Java Version:** 11+ (17 for CI)
**Build Tool:** Maven 3.6+
**Last Updated:** April 3, 2026

---

**Status: ✅ Ready to Run**

