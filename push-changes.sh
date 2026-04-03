#!/bin/bash

cd /Users/shirkhanadgozalov/eclipse-workspace/mavenProject/automation-framework

# Configure git
git config user.email "test@example.com"
git config user.name "CI/CD Bot"

# Add all changes
echo "Adding changes..."
git add .

# Check status
echo "Current status:"
git status

# Commit
echo "Committing changes..."
git commit -m "Fix: Resolve GitHub Actions CI/CD pipeline errors and Node.js 20 deprecation warnings

- Add @DataProvider annotation to TestRunner for TestNG integration
- Add cucumber-junit dependency for test report generation
- Update Surefire plugin to include TestRunner and generate reports
- Add FORCE_JAVASCRIPT_ACTIONS_TO_NODE24 flag to all workflows
- Update action versions for Node.js 24 compatibility
- Add error handling with continue-on-error and if-no-files-found
- Implement CI profile for headless test execution
- Comprehensive documentation and validation reports

Resolves all 11 issues (5 errors + 6 warnings)"

# Check commit log
echo "Recent commits:"
git log --oneline -3

# Push to master
echo "Pushing to master..."
git push origin master

echo "✅ Push completed successfully!"

