package Helpers;

import Objects.CheckboxObject;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ScreenshotListener implements ITestListener {
    public static List<byte[]> screenshots = new ArrayList<>();

    public void onTestStart(ITestResult result) {
       screenshots.clear();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        attachScreenshots();
        screenshots.clear();
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        attachScreenshots();
        screenshots.clear();
    }

//    private void attachScreenshots() {
//        for (byte[] screenshot : screenshots) {
//            Reporter.log("<img src='data:image/png;base64," +
//                    Base64.getEncoder().encodeToString(screenshot) + "'/>");
//        }
//    }


}