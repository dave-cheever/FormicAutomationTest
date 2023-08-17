package com.Formic.OF2.utils.Pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "isMultiResponse",
        "maximum",
        "minimum",
        "__typename"
})
@Generated("jsonschema2pojo")
public class Responses {

    @JsonProperty("isMultiResponse")
    private Boolean isMultiResponse;
    @JsonProperty("maximum")
    private Integer maximum;
    @JsonProperty("minimum")
    private Integer minimum;
    @JsonProperty("__typename")
    private String typename;

    @JsonProperty("isMultiResponse")
    public Boolean getIsMultiResponse() {
        return isMultiResponse;
    }

    @JsonProperty("isMultiResponse")
    public void setIsMultiResponse(Boolean isMultiResponse) {
        this.isMultiResponse = isMultiResponse;
    }

    @JsonProperty("maximum")
    public Integer getMaximum() {
        return maximum;
    }

    @JsonProperty("maximum")
    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    @JsonProperty("minimum")
    public Integer getMinimum() {
        return minimum;
    }

    @JsonProperty("minimum")
    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    @JsonProperty("__typename")
    public String getTypename() {
        return typename;
    }

    @JsonProperty("__typename")
    public void setTypename(String typename) {
        this.typename = typename;
    }

}
