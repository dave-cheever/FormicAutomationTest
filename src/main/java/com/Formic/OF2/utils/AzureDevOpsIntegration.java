package com.Formic.OF2.utils;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AzureDevOpsIntegration {

//    public static void main(String[] args) {
//        // Azure DevOps organization and project information
//
//
//        // Test case and test run information
//        int testCaseId = 123; // Replace with your test case ID
//        int testRunId = 456; // Replace with your test run ID
//
//        // Call the function to update the test case status
//        updateTestCaseStatus(testCaseId, testRunId, "Passed");
//    }

//    public static void updateTestCaseStatus(int testCaseId, int testRunId, String outcome) {
//        try {
//
////            String orgUrl = "https://dev.azure.com/stuiem";
////            String project = "Formic";
////            String patToken = "cduuhzd5asuw7lk7nyoknkgepg3f4sdtypfxdwjh3ljmezni546q";
//
//            // Azure DevOps REST API URL to update test results
//            URI updateUri = new URI(orgUrl + "/" + project + "/_apis/test/Runs/" + testRunId + "/results?api-version=6.0");
//
//            // Prepare the request payload to update the test result
//            String requestBody = "{\"state\":\"Completed\",\"outcome\":\"" + outcome + "\"}";
//
//            // Create and send a PATCH request to update the test result
//            HttpClient client = HttpClient.newHttpClient();
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(updateUri)
//                    .header("Content-Type", "application/json-patch+json")
//                    .header("Authorization", "Basic " + patToken)
//                    .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody))
//                    .build();
//
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            if (response.statusCode() == 200) {
//                System.out.println("Test case status updated successfully.");
//            } else {
//                System.err.println("Error updating test case status: " + response.body());
//            }
//        } catch (URISyntaxException | IOException | InterruptedException ex) {
//            System.err.println("An error occurred: " + ex.getMessage());
//        }
//    }
    public static void updateTestCaseStatus(int testCaseId, int testRunId, String outcome) {
        try {

            String orgUrl = "https://dev.azure.com/stuiem";
            String project = "Formic";
            String patToken = "cduuhzd5asuw7lk7nyoknkgepg3f4sdtypfxdwjh3ljmezni546q";

            // Azure DevOps REST API URL to update a specific test case result within a test run
            URI updateUri = new URI(orgUrl + "/" + project + "/_apis/test/Runs/" + testRunId + "/Results/" + testCaseId + "?api-version=6.0");

            // Prepare the request payload to update the test result
            String requestBody = "{\"state\":\"Completed\",\"outcome\":\"" + outcome + "\"}";

            // Create and send a PATCH request to update the test case result
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(updateUri)
                    .header("Content-Type", "application/json-patch+json")
                    .header("Authorization", "Basic " + patToken)
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Test case status updated successfully.");
            } else {
                System.err.println("Error updating test case status: " + response.body());
            }
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            System.err.println("An error occurred: " + ex.getMessage());
        }
    }
}
