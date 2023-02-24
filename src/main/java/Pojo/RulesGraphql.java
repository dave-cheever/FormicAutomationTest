package Pojo;

import Pages.GraphQLQuery;
import Pages.ProjectIdentifierInput;
import Pages.QueryVariables;
import Pojo.FormContentPojo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RulesGraphql {

    public FormContentPojo getRules(int projectId){
        ProjectIdentifierInput input = new ProjectIdentifierInput();
        input.setId(projectId);

        QueryVariables variable = new QueryVariables();
        variable.setProjectIdentifierInput(input);
        String graphQLQuery =" query ProjectFields ($projectIdentifierInput: ProjectIdentifierInput!){ project (projectIdentifier: $projectIdentifierInput){ id name pages{ guidId objects{\s...on CaptureObjectBase {\s guidId __typename}...on TickboxGroup{ subQuestionFields{guidId __typename} tickboxResponses{ordinal __typename box{hidden, subQuestionIndex} label{text, __typename}}}...on ManualImageAreaText{guidId fieldId name __typename}...on HandwritingRecognitionObject{guidId fieldId name boxes{dimensions{height, width, __typename},guidId, location{layer, left, top, __typename},ordinal,__typename} __typename}}}fields {guidId id name mandatory dataTypeNew formatRegex formatMask responses {isMultiResponse maximum minimum __typename}__typename}routing {fieldId conditions {action hasValue whenField __typename}__typename}__typename}} ";


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
