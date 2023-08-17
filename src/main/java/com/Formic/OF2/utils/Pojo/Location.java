package com.Formic.OF2.utils.Pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "layer",
        "left",
        "top",
        "__typename"
})
@Generated("jsonschema2pojo")
public class Location {

    @JsonProperty("layer")
    private Integer layer;
    @JsonProperty("left")
    private Integer left;
    @JsonProperty("top")
    private Integer top;
    @JsonProperty("__typename")
    private String typename;

    @JsonProperty("layer")
    public Integer getLayer() {
        return layer;
    }

    @JsonProperty("layer")
    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    @JsonProperty("left")
    public Integer getLeft() {
        return left;
    }

    @JsonProperty("left")
    public void setLeft(Integer left) {
        this.left = left;
    }

    @JsonProperty("top")
    public Integer getTop() {
        return top;
    }

    @JsonProperty("top")
    public void setTop(Integer top) {
        this.top = top;
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