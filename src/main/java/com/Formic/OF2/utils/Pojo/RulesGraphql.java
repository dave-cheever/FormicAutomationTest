package com.Formic.OF2.utils.Pojo;

import com.Formic.OF2.pages.GraphQLQuery;
import com.Formic.OF2.pages.ProjectIdentifierInput;
import com.Formic.OF2.pages.QueryVariables;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RulesGraphql {

    public FormContentPojo getRules(int projectId){
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


//        String graphQLQuery = """
//                query ProjectFields ($projectIdentifierInput: ProjectIdentifierInput!){\s
//                  project (projectIdentifier: $projectIdentifierInput){
//                  id
//                  name
//                  pages{
//                  		guidId
//                  		objects{\s
//                  			...on CaptureObjectBase {\s
//                  				        guidId
//                  				        __typename
//                  				}
//                  				...on TickboxGroup{
//                  				        subQuestionFields{
//                  					                        guidId
//                  				                            __typename
//                  				        }
//                  				        tickboxResponses{
//                      				                        ordinal
//                      				                        __typename
//                      				                        box{
//                      				                            hidden,
//                      				                            subQuestionIndex
//                      				                        }
//                      				                        label{
//                      				                            text,
//                      				                            __typename
//                      				                        }
//                      			        }
//                  			    }
//                  			...on ManualImageAreaText{
//                  			            guidId
//                                        fieldId
//                                        name
//                                        __typename
//                            }
//                  			...on HandwritingRecognitionObject{
//                     					guidId
//                     					fieldId
//                     					name
//                     					boxes{
//                     					        dimensions{
//                     					                height,
//                     					                width,
//                     					                __typename
//                     					        }
//                     					        ,guidId,
//                          						location{
//                          						        layer,
//                          						        left,
//                          						        top,
//                          						        __typename
//                          						}
//                          						,ordinal
//                          						,__typename
//                          				}
//                     					__typename
//                     		}
//                  		}
//
//                  }
//                  fields {
//                    guidId
//                    id
//                    name
//                    mandatory
//                    dataTypeNew
//                    formatRegex
//                    formatMask
//                    responses {
//                      isMultiResponse
//                      maximum
//                      minimum
//                      __typename
//                    }
//                    __typename
//                  }
//                  routing {
//                    fieldId
//                    conditions {
//                      action
//                      hasValue
//                      whenField
//                      __typename
//                    }
//                    __typename
//                  }
//                  __typename
//                  }
//                } """;

        GraphQLQuery query = new GraphQLQuery();
        query.setQuery(graphQLQuery);
        query.setVariables(variable);

        RestAssured.baseURI = "https://formic-api-test.azurewebsites.net";
        RestAssured.basePath = "/graphql";
        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
        reqSpecBuilder.setContentType("application/json; charset=UTF-8");
        reqSpecBuilder.setBody(query);
        RequestSpecification requestSpecification = RestAssured.given(reqSpecBuilder.build());
        return requestSpecification.expect().when().post().as(FormContentPojo.class);
    }

}
