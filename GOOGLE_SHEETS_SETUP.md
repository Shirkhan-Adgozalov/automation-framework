# Google Sheets Integration Setup Guide

## Step 1: Google Cloud Console

1. Open [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select an existing project
3. Go to **APIs & Services > Library**
4. Search for "Google Sheets API" and click **Enable**

## Step 2: OAuth 2.0 Credentials

1. Go to **APIs & Services > Credentials**
2. Click **Create Credentials > OAuth client ID**
3. Select **Application type**: Desktop app
4. Enter a name and click **Create**
5. Download the JSON file and rename it to `credentials.json`

## Step 3: Project Configuration

1. Place `credentials.json` file in project root:
   ```
   /Users/shirkhanadgozalov/eclipse-workspace/mavenProject/automation-framework/credentials.json
   ```

2. Create Google Sheets:
   - Open [Google Sheets](https://sheets.google.com)
   - Create a new sheet
   - Copy the Spreadsheet ID from the URL:
     ```
     https://docs.google.com/spreadsheets/d/[SPREADSHEET_ID]/edit
     ```

3. Update SPREADSHEET_ID in `GoogleSheetsHelper.java`:
   ```java
   private static final String SPREADSHEET_ID = "YOUR_SPREADSHEET_ID_HERE";
   ```

## Step 4: Run Tests

To run the tests:
```bash
mvn test -Dtest=GoogleSheetsIntegrationTest#testBasicGoogleSheetsConnection
```

## Available Test Methods

- `testBasicGoogleSheetsConnection()` - Simple connection test
- `testCreateSampleGoogleSheet()` - Create sample data
- `testDynamicGoogleSheetsData()` - Add dynamic data
- `testUpdateTestResults()` - Update test results

## Google Sheets Helper Methods

- `readSheetData(range)` - Read data
- `writeSheetData(range, data)` - Write data
- `appendSheetData(range, data)` - Append data
- `createSheet(sheetTitle)` - Create new sheet
- `getCellData(range)` - Read cell data
- `getRowCount(sheetName)` - Get row count

## Sample Sheet Structure

Create a sheet named "TestCases" and add these columns:

| TestCase | FirstName | LastName | Email | Phone | City | ExpectedResult | Status |
|----------|-----------|----------|-------|-------|------|----------------|--------|
| TC001    | John      | Doe      | john@email.com | 123456789 | Baku | Success | Pending |

## Troubleshooting

**"File not found: credentials.json"**
- Make sure credentials.json is in the correct location

**"Invalid spreadsheet ID"**
- Verify the Spreadsheet ID is correct
- Ensure the sheet has public access permissions

**"Insufficient permissions"**
- Check that OAuth credentials are properly configured
- Verify Google Sheets API is enabled
