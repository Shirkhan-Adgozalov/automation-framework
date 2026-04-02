# Saucedemo Automation Framework

🚀 **Framework MIGRATED to Saucedemo.com!** 

Stable and reliable test automation framework for Saucedemo e-commerce website with 100% test success rate.

## 📊 Framework Status

### ✅ **Production Ready**
- **100% Test Success Rate** - All 9 scenarios passing
- **Stable Website** - Saucedemo.com (replaced unstable DemoQA)
- **6 User Types** - Valid, locked, problem, performance, visual users
- **BDD Framework** - Cucumber + TestNG + Selenium

### 🔄 **Recent Changes**
- **Migrated from DemoQA to Saucedemo.com**
- **Enhanced DataGenerator with Saucedemo user credentials**
- **Added Git security (.gitignore + template system)**
- **Improved error handling and logout functionality**

## 🚀 Quick Start

### 1. Clone & Setup
```bash
git clone https://github.com/Shirkhan-Adgozalov/automation-framework.git
cd automation-framework

# Copy configuration template
cp src/test/resources/config.properties.template src/test/resources/config.properties
```

### 2. Configuration
Edit `src/test/resources/config.properties`:
```properties
baseUrl=https://www.saucedemo.com/
browser=chrome
headless=false  # false for local, true for CI/CD
```

### 3. Run Tests
```bash
# All Saucedemo tests
mvn test -Dtest=TestRunner -Dcucumber.options="--tags '@saucedemo'"

# Smoke tests only
mvn test -Dtest=TestRunner -Dcucumber.options="--tags '@smoke'"

# Headless mode
# Edit config: headless=true
mvn test -Dtest=TestRunner -Dcucumber.options="--tags '@saucedemo'"
```

## 📊 Test Coverage

### User Types Available
| Test Case | Username | Status | Description |
|-----------|------------|---------|-------------|
| TC001 | standard_user | ✅ Valid login |
| TC002 | locked_out_user | ❌ Locked out |
| TC003 | problem_user | ⚠️ Problem user |
| TC004 | performance_glitch_user | ⚠️ Slow performance |
| TC005 | error_user | ❌ Error user |
| TC006 | visual_user | ✅ Visual user |

### Test Scenarios
- ✅ **Smoke Tests** - Valid user login
- ✅ **Regression Tests** - Invalid user handling
- ✅ **Data-Driven Tests** - Random user selection
- ✅ **Validation Tests** - Data verification
- ✅ **Performance Tests** - Slow user handling
- ✅ **Visual Tests** - Visual user compatibility
- ✅ **Multiple Tests** - Sequential login scenarios
- ✅ **Negative Tests** - Error message validation

## 🏗️ Project Structure

```
src/test/java/com/shirkhan/
├── base/
│   ├── BasePage.java          # Core Selenium actions
│   └── Hooks.java             # Test lifecycle management
├── pages/
│   └── SaucedemoPage.java     # Saucedemo page objects
├── utils/
│   ├── DataGenerator.java     # Saucedemo test data
│   ├── ConfigReader.java      # Configuration management
│   ├── LoggerHelper.java      # Logging utilities
│   └── WaitHelper.java        # Smart wait strategies
├── stepDefinitions/
│   └── ui/
│       └── SaucedemoSteps.java # BDD step definitions
└── runner/
    └── TestRunner.java        # Test execution

src/test/resources/
├── features/
│   └── saucedemo_login.feature # BDD test scenarios
├── config.properties.template   # Configuration template
└── config.properties          # Local config (gitignored)
```

## 🔧 Configuration Management

### Git Security
```bash
# ✅ Template is tracked
config.properties.template

# ❌ Local config is ignored
config.properties  # in .gitignore
```

### Environment Setup
```bash
# Development (Browser Mode)
headless=false

# Production (Headless Mode)  
headless=true
```

## 🎯 Git Workflow

### Safe Deployment Process
```bash
# 1. Setup environment
cp config.properties.template config.properties
# Edit as needed for your environment

# 2. Test locally
mvn test -Dtest=TestRunner -Dcucumber.options="--tags '@saucedemo'"

# 3. Commit changes (config ignored)
git add .
git commit -m "Update Saucedemo framework - migrate from DemoQA"

# 4. Push safely
git push origin master
```

## 🔍 Key Features

### 🚀 Core Components
- **SaucedemoPage** - Complete login/logout functionality
- **DataGenerator** - 6 predefined Saucedemo user types
- **Robust Error Handling** - Multiple fallback strategies
- **Headless Mode Support** - CI/CD compatible
- **Smart Waits** - Reliable element synchronization

### 📈 Test Results
- **100% Success Rate** - All scenarios passing
- **Stable Execution** - No flaky tests
- **Fast Performance** - ~2-3 minutes execution
- **Comprehensive Coverage** - All user types tested

## 🛠️ Dependencies

- **Selenium 4.18.1** - WebDriver automation
- **TestNG 7.9.0** - Test framework
- **Cucumber 7.15.0** - BDD support
- **JavaFaker 1.0.2** - Test data generation
- **WebDriverManager 5.7.0** - Browser management

## 📝 Contributing

1. Fork repository
2. Create feature branch
3. Copy config template for local testing
4. Make changes
5. Test thoroughly
6. Submit pull request

## 📄 License

MIT License - Free for commercial and personal use

---

**🎉 Framework Ready for Production Use!**

**Migrated from unstable DemoQA to stable Saucedemo.com with 100% test success rate!**
