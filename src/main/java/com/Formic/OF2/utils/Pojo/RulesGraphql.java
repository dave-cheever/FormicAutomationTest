package com.Formic.OF2.utils.Pojo;

import com.Formic.OF2.pages.GraphQLQuery;
import com.Formic.OF2.pages.ProjectIdentifierInput;
import com.Formic.OF2.pages.QueryVariables;
import com.Formic.OF2.utils.ConfigLoader;
import com.Formic.OF2.utils.SSLUtilities;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;

public class RulesGraphql {

    public FormContentPojo getRules(int projectId){
        disableSSLVerification();
        ProjectIdentifierInput input = new ProjectIdentifierInput();
        input.setId(projectId);

        QueryVariables variable = new QueryVariables();
        variable.setProjectIdentifierInput(input);
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("                query ProjectFields ($projectIdentifierInput: ProjectIdentifierInput!){");
        queryBuilder.append("                  project (projectIdentifier: $projectIdentifierInput){");
        queryBuilder.append("                  id");
        queryBuilder.append("                  name");
        queryBuilder.append("                  pages{");
        queryBuilder.append("                  guidId");
        queryBuilder.append("                  objects{");
        queryBuilder.append("                  ...on CaptureObjectBase {");
        queryBuilder.append("                          guidId");
        queryBuilder.append("                          __typename");
        queryBuilder.append("                  }");
        queryBuilder.append("                  ...on TickboxGroup{");
        queryBuilder.append("                          subQuestionFields{");
        queryBuilder.append("                                          guidId");
        queryBuilder.append("                                          __typename");
        queryBuilder.append("                          }");
        queryBuilder.append("                          tickboxResponses{");
        queryBuilder.append("                                              ordinal");
        queryBuilder.append("                                              __typename");
        queryBuilder.append("                                              box{");
        queryBuilder.append("                                                  hidden,");
        queryBuilder.append("                                                  subQuestionIndex");
        queryBuilder.append("                                              }");
        queryBuilder.append("                                              label{");
        queryBuilder.append("                                                  text,");
        queryBuilder.append("                                                  __typename");
        queryBuilder.append("                                              }");
        queryBuilder.append("                              }");
        queryBuilder.append("                      }");
        queryBuilder.append("                  ...on ManualImageAreaText{");
        queryBuilder.append("                             guidId");
        queryBuilder.append("                                        fieldId");
        queryBuilder.append("                                        name");
        queryBuilder.append("                                        __typename");
        queryBuilder.append("                            }");

        queryBuilder.append("...on PickList{");
        queryBuilder.append("guidId,");
        queryBuilder.append("fieldId,");
        queryBuilder.append("name,");
        queryBuilder.append("__typename,");
        queryBuilder.append("}");

        queryBuilder.append("                  ...on HandwritingRecognitionObject{");
        queryBuilder.append("                     guidId");
        queryBuilder.append("                     fieldId");
        queryBuilder.append("                     name");
        queryBuilder.append("                     boxes{");
        queryBuilder.append("                             dimensions{");
        queryBuilder.append("                                     height,");
        queryBuilder.append("                                     width,");
        queryBuilder.append("                                     __typename");
        queryBuilder.append("                             }");
        queryBuilder.append("                             ,guidId,");
        queryBuilder.append("                          location{");
        queryBuilder.append("                                  layer,");
        queryBuilder.append("                                  left,");
        queryBuilder.append("                                  top,");
        queryBuilder.append("                                  __typename");
        queryBuilder.append("                          }");
        queryBuilder.append("                          ,ordinal");
        queryBuilder.append("                          ,__typename");
        queryBuilder.append("                          }");
        queryBuilder.append("                     __typename");
        queryBuilder.append("                     }");
        queryBuilder.append("                  }");
        queryBuilder.append("                  }");
        queryBuilder.append("                  fields {");
        queryBuilder.append("                    guidId");
        queryBuilder.append("                    id");
        queryBuilder.append("                    name");
        queryBuilder.append("                    mandatory");
        queryBuilder.append("                    validation");
        queryBuilder.append("                    derivation");
        queryBuilder.append("                    fieldProcesses");
        queryBuilder.append("                    dataTypeNew");
        queryBuilder.append("                    formatRegex");
        queryBuilder.append("                    formatMask");
        queryBuilder.append("                    responses {");
        queryBuilder.append("                      isMultiResponse");
        queryBuilder.append("                      maximum");
        queryBuilder.append("                      minimum");
        queryBuilder.append("                      __typename");
        queryBuilder.append("                    }");
        queryBuilder.append("                    __typename");
        queryBuilder.append("                  }");
        queryBuilder.append("                  routing {");
        queryBuilder.append("                    fieldId");
        queryBuilder.append("                    conditions {");
        queryBuilder.append("                      action");
        queryBuilder.append("                      hasValue");
        queryBuilder.append("                      whenField");
        queryBuilder.append("                      __typename");
        queryBuilder.append("                    }");
        queryBuilder.append("                    __typename");
        queryBuilder.append("                  }");
        queryBuilder.append("                  __typename");
        queryBuilder.append("                  }");
        queryBuilder.append("                }");
        String graphQLQuery = queryBuilder.toString();


        GraphQLQuery query = new GraphQLQuery();
        query.setQuery(graphQLQuery);
        query.setVariables(variable);

        RestAssured.baseURI = ConfigLoader.getProperty("test.urlApi");
        RestAssured.basePath = "/graphql";
        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
        reqSpecBuilder.setContentType("application/json; charset=UTF-8");
        reqSpecBuilder.setBody(query);
        RequestSpecification requestSpecification = RestAssured.given(reqSpecBuilder.build());
        return requestSpecification.expect().when().post().as(FormContentPojo.class);
    }

    private void disableSSLVerification() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                        public void checkServerTrusted(X509Certificate[] certs, String authType) { }
                    }
            };

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

            RestAssured.useRelaxedHTTPSValidation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
