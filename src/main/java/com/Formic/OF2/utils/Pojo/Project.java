package com.Formic.OF2.utils.Pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "pages",
        "fields",
        "routing",
        "__typename"
})
@Generated("jsonschema2pojo")
public class Project {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("pages")
    private List<Page> pages = null;
    @JsonProperty("fields")
    private List<Field> fields = null;
    @JsonProperty("routing")
    private List<Routing> routing = null;
    @JsonProperty("__typename")
    private String typename;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("pages")
    public List<Page> getPages() {
        return pages;
    }

    @JsonProperty("pages")
    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    @JsonProperty("fields")
    public List<Field> getFields() {
        return fields;
    }

    @JsonProperty("fields")
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @JsonProperty("routing")
    public List<Routing> getRouting() {
        return routing;
    }

    @JsonProperty("routing")
    public void setRouting(List<Routing> routing) {
        this.routing = routing;
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