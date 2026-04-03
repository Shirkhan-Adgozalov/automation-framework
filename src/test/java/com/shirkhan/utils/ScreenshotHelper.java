package com.shirkhan.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotHelper {

    public static void takeScreenshot(WebDriver driver, String fileName) {

        if (driver == null) {
            System.out.println("Cannot take screenshot: WebDriver is null");
            return;
        }

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            Files.createDirectories(Paths.get("screenshots"));

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fullFileName = fileName + "_" + timestamp + ".png";

            Files.copy(srcFile.toPath(),
                    Paths.get("screenshots/" + fullFileName));

            System.out.println("Screenshot saved: " + fullFileName);

        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
            e.printStackTrace();

        }
    }
}