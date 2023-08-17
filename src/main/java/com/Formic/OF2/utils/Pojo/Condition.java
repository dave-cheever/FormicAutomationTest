package com.Formic.OF2.utils.Pojo;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "action",
        "hasValue",
        "whenField",
        "__typename"
})
@Generated("jsonschema2pojo")
public class Condition {

    @JsonProperty("action")
    private String action;
    @JsonProperty("hasValue")
    private String hasValue;
    @JsonProperty("whenField")
    private String whenField;
    @JsonProperty("__typename")
    private String typename;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("hasValue")
    public String getHasValue() {
        return hasValue;
    }

    @JsonProperty("hasValue")
    public void setHasValue(String hasValue) {
        this.hasValue = hasValue;
    }

    @JsonProperty("whenField")
    public String getWhenField() {
        return whenField;
    }

    @JsonProperty("whenField")
    public void setWhenField(String whenField) {
        this.whenField = whenField;
    }

    @JsonProperty("__typename")
    public String getTypename() {
        return typename;
    }

    @JsonProperty("__typename")
    public void setTypename(String typename) {
        this.typename = typename;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}