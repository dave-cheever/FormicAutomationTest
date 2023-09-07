package com.Formic.OF2.utils.Pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "dimensions",
        "guidId",
        "location",
        "ordinal",
        "__typename"
})
@Generated("jsonschema2pojo")
public class Box__1 {

    @JsonProperty("dimensions")
    private Dimensions dimensions;
    @JsonProperty("guidId")
    private String guidId;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("ordinal")
    private Integer ordinal;
    @JsonProperty("__typename")
    private String typename;

    @JsonProperty("dimensions")
    public Dimensions getDimensions() {
        return dimensions;
    }

    @JsonProperty("dimensions")
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    @JsonProperty("guidId")
    public String getGuidId() {
        return guidId;
    }

    @JsonProperty("guidId")
    public void setGuidId(String guidId) {
        this.guidId = guidId;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonProperty("ordinal")
    public Integer getOrdinal() {
        return ordinal;
    }

    @JsonProperty("ordinal")
    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
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