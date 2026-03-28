package com.shirkhan.utils;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.services.sheets.v4.SheetsScopes;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleSheetsHelper {

    private static final String APPLICATION_NAME = "Automation Framework";
    private static final String SPREADSHEET_ID = "YOUR_SPREADSHEET_ID_HERE"; // Replace with your Google Sheet ID
    
    // Get Google Sheets service
    private static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        return GoogleSheetsCredentialUtil.getSheetsService();
    }
    
    // Read data from Google Sheet
    public static List<List<String>> readSheetData(String range) throws IOException, GeneralSecurityException {
        Sheets service = getSheetsService();
        ValueRange response = service.spreadsheets().values()
                .get(SPREADSHEET_ID, range)
                .execute();
        
        List<List<Object>> values = response.getValues();
        List<List<String>> result = new ArrayList<>();
        
        if (values == null || values.isEmpty()) {
            LoggerHelper.info("No data found in range: " + range);
            return result;
        }
        
        for (List<Object> row : values) {
            List<String> stringRow = new ArrayList<>();
            for (Object cell : row) {
                stringRow.add(cell != null ? cell.toString() : "");
            }
            result.add(stringRow);
        }
        
        LoggerHelper.info("Read " + result.size() + " rows from Google Sheets");
        return result;
    }
    
    // Write data to Google Sheet
    public static void writeSheetData(String range, List<List<String>> data) throws IOException, GeneralSecurityException {
        Sheets service = getSheetsService();
        
        List<List<Object>> values = new ArrayList<>();
        for (List<String> row : data) {
            List<Object> objectRow = new ArrayList<>();
            objectRow.addAll(row);
            values.add(objectRow);
        }
        
        ValueRange body = new ValueRange().setValues(values);
        service.spreadsheets().values()
                .update(SPREADSHEET_ID, range, body)
                .setValueInputOption("USER_ENTERED")
                .execute();
        
        LoggerHelper.info("Written " + data.size() + " rows to Google Sheets");
    }
    
    // Append data to Google Sheet
    public static void appendSheetData(String range, List<String> data) throws IOException, GeneralSecurityException {
        Sheets service = getSheetsService();
        
        List<Object> values = new ArrayList<>();
        values.addAll(data);
        
        ValueRange body = new ValueRange().setValues(Arrays.asList(values));
        service.spreadsheets().values()
                .append(SPREADSHEET_ID, range, body)
                .setValueInputOption("USER_ENTERED")
                .execute();
        
        LoggerHelper.info("Appended new row to Google Sheets");
    }
    
    // Get sheet info
    public static List<Sheet> getSheetInfo() throws IOException, GeneralSecurityException {
        Sheets service = getSheetsService();
        Spreadsheet spreadsheet = service.spreadsheets()
                .get(SPREADSHEET_ID)
                .execute();
        
        return spreadsheet.getSheets();
    }
    
    // Create new sheet
    public static void createSheet(String sheetTitle) throws IOException, GeneralSecurityException {
        Sheets service = getSheetsService();
        
        BatchUpdateSpreadsheetRequest batchUpdateRequest = new BatchUpdateSpreadsheetRequest();
        List<Request> requests = new ArrayList<>();
        
        requests.add(new Request()
                .setAddSheet(new AddSheetRequest()
                        .setProperties(new SheetProperties()
                                .setTitle(sheetTitle))));
        
        batchUpdateRequest.setRequests(requests);
        service.spreadsheets()
                .batchUpdate(SPREADSHEET_ID, batchUpdateRequest)
                .execute();
        
        LoggerHelper.info("Created new sheet: " + sheetTitle);
    }
    
    // Clear sheet data
    public static void clearSheetData(String range) throws IOException, GeneralSecurityException {
        Sheets service = getSheetsService();
        
        ClearValuesRequest requestBody = new ClearValuesRequest();
        service.spreadsheets().values()
                .clear(SPREADSHEET_ID, range, requestBody)
                .execute();
        
        LoggerHelper.info("Cleared data in range: " + range);
    }
    
    // Read specific cell
    public static String getCellData(String range) throws IOException, GeneralSecurityException {
        List<List<String>> data = readSheetData(range);
        if (!data.isEmpty() && !data.get(0).isEmpty()) {
            return data.get(0).get(0);
        }
        return "";
    }
    
    // Get row count
    public static int getRowCount(String sheetName) throws IOException, GeneralSecurityException {
        List<List<String>> data = readSheetData(sheetName + "!A:A");
        return data.size();
    }
    
    // Create sample test data
    public static void createSampleTestData() throws IOException, GeneralSecurityException {
        String sheetName = "TestCases";
        
        // Create sample data
        List<List<String>> testData = new ArrayList<>();
        
        // Header
        testData.add(List.of("TestCase", "FirstName", "LastName", "Email", "Phone", "City", "ExpectedResult", "Status"));
        
        // Sample test cases
        for (int i = 1; i <= 10; i++) {
            testData.add(List.of(
                "TC00" + i,
                DataGenerator.randomFirstName(),
                DataGenerator.randomLastName(),
                DataGenerator.randomEmail(),
                DataGenerator.randomPhoneNumber(),
                DataGenerator.randomCity(),
                "Success",
                "Pending"
            ));
        }
        
        // Write to sheet
        writeSheetData(sheetName + "!A1", testData);
        LoggerHelper.info("Sample test data created in Google Sheets");
    }
}
