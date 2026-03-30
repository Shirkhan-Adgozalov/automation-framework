package com.shirkhan.tests;

import com.google.api.services.sheets.v4.model.Sheet;
import com.shirkhan.utils.GoogleSheetsHelper;
import com.shirkhan.utils.LoggerHelper;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class GoogleSheetsIntegrationTest {

    @Test
    public void testReadMultipleCells() throws IOException, GeneralSecurityException {
        // Read multiple cells from Google Sheets
        List<List<String>> headerData = GoogleSheetsHelper.readSheetData("Sheet1!A1:H1");
        LoggerHelper.info("Header row: " + headerData);
        
        List<List<String>> allData = GoogleSheetsHelper.readSheetData("Sheet1!A:H");
        LoggerHelper.info("Total rows read: " + allData.size());
        
        // Print first 3 rows
        for (int i = 0; i < Math.min(3, allData.size()); i++) {
            LoggerHelper.info("Row " + (i+1) + ": " + allData.get(i));
        }
    }
    
    @Test
    public void testGetRowCount() throws IOException, GeneralSecurityException {
        // Test row count functionality
        int rowCount = GoogleSheetsHelper.getRowCount("Sheet1");
        LoggerHelper.info("Total rows in Sheet1: " + rowCount);
    }
    
    @Test
    public void testBasicGoogleSheetsConnection() throws IOException, GeneralSecurityException {
        // Simple connection test
        LoggerHelper.info("Testing Google Sheets connection...");
        
        // Get sheet info
        List<Sheet> sheets = GoogleSheetsHelper.getSheetInfo();
        LoggerHelper.info("Available sheets: " + sheets.size());
        
        for (Sheet sheet : sheets) {
            LoggerHelper.info("Sheet: " + sheet.getProperties().getTitle());
        }
        
        // Test reading a specific cell
        String testCell = GoogleSheetsHelper.getCellData("Sheet1!A1");
        LoggerHelper.info("Cell A1 content: " + testCell);
    }
}
