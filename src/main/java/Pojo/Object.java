package Pojo;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "guidId",
        "__typename",
        "subQuestionFields",
        "tickboxResponses",
        "fieldId",
        "name",
        "boxes"
//        "responses"
})
@Generated("jsonschema2pojo")
public class Object {

    @JsonProperty("guidId")
    private String guidId;
    @JsonProperty("__typename")
    private String typename;
    @JsonProperty("subQuestionFields")
    private List<SubQuestionField> subQuestionFields = null;
    @JsonProperty("tickboxResponses")
    public List<TickboxResponse> tickboxResponses = null;
    @JsonProperty("fieldId")
    private String fieldId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("boxes")
    private List<Box__1> boxes = null;
//    @JsonProperty("responses")
//    private List<Responses> responses = null;


    @JsonProperty("guidId")
    public String getGuidId() {
        return guidId;
    }

    @JsonProperty("guidId")
    public void setGuidId(String guidId) {
        this.guidId = guidId;
    }

    @JsonProperty("__typename")
    public String getTypename() {
        return typename;
    }

    @JsonProperty("fieldId")
    public String getFieldId() {
        return fieldId;
    }

    @JsonProperty("fieldId")
    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    @JsonProperty("__typename")
    public void setTypename(String typename) {
        this.typename = typename;
    }

    @JsonProperty("subQuestionFields")
    public List<SubQuestionField> getSubQuestionFields() {
        return subQuestionFields;
    }

    @JsonProperty("subQuestionFields")
    public void setSubQuestionFields(List<SubQuestionField> subQuestionFields) {
        this.subQuestionFields = subQuestionFields;
    }

    @JsonProperty("tickboxResponses")
    public List<TickboxResponse> getTickboxResponses() {
        return tickboxResponses;
    }

    @JsonProperty("tickboxResponses")
    public void setTickboxResponses(List<TickboxResponse> tickboxResponses) {
        this.tickboxResponses = tickboxResponses;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("boxes")
    public List<Box__1> getBoxes() {
        return boxes;
    }
    @JsonProperty("boxes")
    public void setBoxes(List<Box__1> boxes) {
        this.boxes = boxes;
    }

//    @JsonProperty("responses")
//    public List<Responses> getResponses() {
//        return responses;
//    }
//    @JsonProperty("responses")
//    public void setResponses(List<Responses> responses) {
//        this.responses = responses;
//    }

}