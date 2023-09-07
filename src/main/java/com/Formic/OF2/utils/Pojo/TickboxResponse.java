package com.Formic.OF2.utils.Pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ordinal",
        "__typename",
        "box",
        "label"
})
@Generated("jsonschema2pojo")
public class TickboxResponse {

    @JsonProperty("ordinal")
    public int ordinal;
    @JsonProperty("__typename")
    public String typename;
    @JsonProperty("box")
    public Box box;
    @JsonProperty("label")
    public Label label;

    @JsonProperty("box")
    public Box getBox() {
        return box;
    }

    @JsonProperty("box")
    public void setBox(Box box) {
        this.box = box;
    }

    @JsonProperty("label")
    public Label getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(Label label) {
        this.label = label;
    }

    @JsonProperty("ordinal")
    public int getOrdinal() {
        return ordinal;
}

    @JsonProperty("ordinal")
    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    @JsonProperty("__typename")
    public String getTypeName() {
        return typename;
    }

    @JsonProperty("__typename")
    public void setTypeName(String typename) {
        this.typename = typename;
    }

}