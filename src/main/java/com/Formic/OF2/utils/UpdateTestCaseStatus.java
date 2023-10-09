package com.Formic.OF2.utils;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UpdateTestCaseStatus {

    private static final String AZURE_TEST_PLANS_API_URL = "https://dev.azure.com/stuiem/Formic/_apis/test/testcases/{testCaseId}?updateTestResults=true";

    public static void updateTestCaseStatus(String testCaseId, String outcome) throws Exception {
        // Create the HTTP client.
        HttpClient client = HttpClient.newHttpClient();

        // Create the HTTP request.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AZURE_TEST_PLANS_API_URL.replace("{testCaseId}", testCaseId)))
                .method("PUT", HttpRequest.BodyPublishers.ofString("{\"outcome\": \"" + outcome + "\"}"))
                .header("Authorization", "Bearer " + "cduuhzd5asuw7lk7nyoknkgepg3f4sdtypfxdwjh3ljmezni546q")
                .build();

        // Send the HTTP request and get the response.
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the status code of the response.
        if (response.statusCode() != 200) {
            throw new Exception("Failed to update test case status: " + response.statusCode());
        }
    }


}
