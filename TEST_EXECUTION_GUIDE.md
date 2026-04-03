# Quick Start Guide - Fixed Test Execution

## What Was Fixed

The test suite had a critical issue: **HTTP client thread pool exhaustion** when running 9 scenarios in parallel. This has been resolved by switching to sequential execution.

### Changes Made
1. ✅ `TestRunner.java` - Changed `@DataProvider(parallel = false)` 
2. ✅ `Hooks.java` - Improved WebDriver cleanup with proper resource management

---

## Running Tests

### Standard Execution
```bash
# Run all tests
mvn clean test

# Run and skip test failures during build
mvn test -DskipTests=false
```

### With Browser Control
```bash
# Run tests in headless mode
mvn test -Dheadless=true

# Run tests with visible browser (default)
mvn test -Dheadless=false
```

### With Profiles
```bash
# CI/Headless Profile
mvn clean test -Pci

# Standard Profile
mvn clean test
```

### Cucumber-Specific Execution
```bash
# Run only @saucedemo scenarios
mvn test -Dgroups="@saucedemo"

# Run only @smoke scenarios
mvn test -Dgroups="@smoke"

# Run only @regression scenarios
mvn test -Dgroups="@regression"

# Run combined tags
mvn test -Dgroups="@saucedemo or @smoke"
```

---

## Expected Results

### Test Execution
- ✅ 9 Cucumber scenarios execute sequentially
- ✅ No `RejectedExecutionException` errors
- ✅ Each scenario creates and closes its own browser instance
- ✅ Proper resource cleanup between tests

### Output Files
After test execution, you'll find:
- `target/cucumber-report.html` - HTML test report
- `target/cucumber.json` - JSON test results
- `target/surefire-reports/TEST-cucumber.xml` - JUnit format report
- `screenshots/` - Failed test screenshots

---

## Key Test Scenarios

| Tag | Purpose |
|-----|---------|
| `@saucedemo` | SauceLabs login tests |
| `@smoke` | Quick sanity checks |
| `@regression` | Full regression suite |
| `@data-driven` | Tests using data generator |
| `@negative` | Negative/error case testing |

---

## Troubleshooting

### Issue: Tests still slow
**Solution:** Slowness is normal for UI tests. Sequential execution prevents errors but takes time. Each scenario requires:
- Browser startup (~2-3 seconds)
- Navigation and interactions (~1-2 seconds)
- Browser shutdown (~1 second)

### Issue: Chrome DevTools version warning
```
WARNING: Unable to find CDP implementation matching 146
```
**Solution:** This is harmless. The framework gracefully falls back to HTTP protocol. Selenium 4.18.1 doesn't have v146 devtools, but tests still pass.

### Issue: SLF4J warning
```
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder"
```
**Solution:** This is harmless and doesn't affect test execution.

---

## Performance Expectations

| Configuration | Est. Time |
|--------------|-----------|
| 9 scenarios sequentially | ~2-3 minutes |
| Single scenario | ~15-20 seconds |
| Full suite with screenshots | ~3-4 minutes |

---

## Configuration Files

### Main Configuration
- `src/test/resources/config.properties` - Test configuration
- `pom.xml` - Maven dependencies and build configuration

### Feature Files
- `src/test/resources/features/saucedemo_login.feature` - Test scenarios

### Step Definitions
- `src/test/java/com/shirkhan/stepDefinitions/ui/SaucedemoSteps.java` - Test steps

---

## Notes

- **No parallel execution** is now set. This is intentional and prevents thread pool issues.
- Each test gets a fresh browser instance
- WebDriver resources are properly cleaned up
- Tests are stable and reliable
- Sequential execution simplifies debugging of failures

---

## Support

If you encounter issues:
1. Check the HTML report: `target/cucumber-report.html`
2. Review screenshots in `screenshots/` folder
3. Check console output for specific error messages
4. Verify `config.properties` has correct URLs and credentials

---

**Status:** ✅ Fixed and Ready for Production

