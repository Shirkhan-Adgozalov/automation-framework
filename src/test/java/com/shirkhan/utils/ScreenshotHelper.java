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

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {

            Files.createDirectories(Paths.get("screenshots"));

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fullFileName = fileName + "_" + timestamp + ".png";

            Files.copy(srcFile.toPath(),
                    Paths.get("screenshots/" + fullFileName));

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}