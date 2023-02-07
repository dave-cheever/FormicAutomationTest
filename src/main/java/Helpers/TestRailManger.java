package Helpers;

import Objects.CheckboxObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static Helpers.ScreenshotHelper.deleteAllScreenshots;
import static Helpers.ScreenshotHelper.getScreenshotNames;

public class TestRailManger {

    private static final String BASE_URL = "https://formic.testrail.io/";
    private static final String USERNAME = "dave.cheever@formic.com";
    private static final String PASSWORD = "Shiever!623519";
    public static final int TEST_CASE_PASSED_STATUS = 1;
    public static final int TEST_CASE_FAILED_STATUS = 5;


    public static void updateResult(int testCaseId, int statusId, int runId) throws IOException, InterruptedException, APIException {
        APIClient client = new APIClient(BASE_URL);
        client.setUser(USERNAME);
        client.setPassword(PASSWORD);
        Map data = new HashMap();
        data.put("status_id",statusId);
        data.put("comment","Test Executed - Status update automatically from Selenium test automation. "+CheckboxObject.errorMessage);
        client.sendPost("add_result_for_case/"+runId+"/"+testCaseId+"",data);
        client.sendPost("add_attachment_to_case/"+testCaseId,"C:\\Users\\dave.cheever\\IdeaProjects\\FormicOF2Test\\.screenShots\\"+getScreenshotNames());
        deleteAllScreenshots();
    }
}
