package com.Formic.OF2.utils;

import com.Formic.OF2.test.BasePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHelper extends BasePage {

    private static final String SCREENSHOT_FOLDER = "screenShots/";

    public ScreenshotHelper(WebDriver driver) {
        super(driver);
    }

    public void takeScreenshot(String scenario) {
        File screenshotFile = ((TakesScreenshot) super.driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String screenshotName = scenario + "_" + timestamp + ".png";
        try {
            FileHandler.copy(screenshotFile, new File(SCREENSHOT_FOLDER + screenshotName));
            System.out.println("screenshot taken: "+ screenshotName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getScreenshotPath(String scenarioName) {
        File folder = new File(SCREENSHOT_FOLDER);

        if (folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".png") && file.getName().contains(scenarioName)) {
                        return file.getAbsolutePath();
                    }
                }
            }
        } else {
            System.out.println(SCREENSHOT_FOLDER + " is not a directory.");
        }

        // Return null if no matching screenshot is found
        return null;
    }

    public static void deleteAllScreenshots() {
        File folder = new File(SCREENSHOT_FOLDER);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }
}