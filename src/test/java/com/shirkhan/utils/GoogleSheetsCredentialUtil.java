package com.shirkhan.utils;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class GoogleSheetsCredentialUtil {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String API_KEY = "AIzaSyClGg9ZU9GIRnu8pgLUGnJ9-i8-YYzYTs8";
    
    // Get Google Sheets service with API Key
    public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, null)
                .setApplicationName("Automation Framework")
                .build();
    }
    
    // Get API Key for requests
    public static String getApiKey() {
        return API_KEY;
    }
}
