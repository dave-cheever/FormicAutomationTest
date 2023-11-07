package com.Formic.OF2.utils.Pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "guidId",
        "id",
        "name",
        "mandatory",
        "responses",
        "__typename",
        "digits",
        "description",
        "dataTypeNew",
        "fieldFormat",
        "validation",
        "derivation",
        "userValidationErrorMessage",
        "formatRegex",
        "formatMask",
        "fieldType"
})
@Generated("jsonschema2pojo")
public class Field {

    @JsonProperty("guidId")
    private String guidId;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("mandatory")
    private Boolean mandatory;
    @JsonProperty("responses")
    private Responses responses;
    @JsonProperty("__typename")
    private String typename;
    @JsonProperty("digits")
    private Integer digits;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("dataTypeNew")
    private String dataTypeNew;
    @JsonProperty("fieldFormat")
    private List<Object> fieldFormat = null;
    @JsonProperty("validation")
    private String validation;
    @JsonProperty("userValidationErrorMessage")
    private Object userValidationErrorMessage;
    @JsonProperty("formatRegex")
    private String formatRegex;
    @JsonProperty("formatMask")
    private String formatMask;
    @JsonProperty("fieldType")
    private String fieldType;
    @JsonProperty("derivation")
    private String derivation;

    @JsonProperty("guidId")
    public String getGuidId() {
        return guidId;
    }

    @JsonProperty("guidId")
    public void setGuidId(String guidId) {
        this.guidId = guidId;
    }

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

    @JsonProperty("mandatory")
    public Boolean getMandatory() {
        return mandatory;
    }

    @JsonProperty("mandatory")
    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    @JsonProperty("responses")
    public Responses getResponses() {
        return responses;
    }

    @JsonProperty("responses")
    public void setResponses(Responses responses) {
        this.responses = responses;
    }

    @JsonProperty("__typename")
    public String getTypename() {
        return typename;
    }

    @JsonProperty("derivation")
    public String getDerivation() {
        return derivation;
    }

    @JsonProperty("__typename")
    public void setTypename(String typename) {
        this.typename = typename;
    }

    @JsonProperty("digits")
    public Integer getDigits() {
        return digits;
    }

    @JsonProperty("digits")
    public void setDigits(Integer digits) {
        this.digits = digits;
    }

    @JsonProperty("description")
    public java.lang.Object getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(java.lang.Object description) {
        this.description = (Object) description;
    }

    @JsonProperty("dataTypeNew")
    public String getDataTypeNew() {
        return dataTypeNew;
    }

    @JsonProperty("dataTypeNew")
    public void setDataTypeNew(String dataTypeNew) {
        this.dataTypeNew = dataTypeNew;
    }

    @JsonProperty("fieldFormat")
    public List<Object> getFieldFormat() {
        return fieldFormat;
    }

    @JsonProperty("fieldFormat")
    public void setFieldFormat(List<Object> fieldFormat) {
        this.fieldFormat = fieldFormat;
    }

    @JsonProperty("validation")
    public String getValidation() {return validation; }

    @JsonProperty("validation")
    public void setValidation(String validation) {
        this.validation = validation;
    }

    @JsonProperty("userValidationErrorMessage")
    public Object getUserValidationErrorMessage() {
        return userValidationErrorMessage;
    }

    @JsonProperty("userValidationErrorMessage")
    public void setUserValidationErrorMessage(Object userValidationErrorMessage) {
        this.userValidationErrorMessage = userValidationErrorMessage;
    }

    @JsonProperty("formatRegex")
    public String getFormatRegex() {
        return formatRegex;
    }

    @JsonProperty("formatRegex")
    public void setFormatRegex(String formatRegex) {
        this.formatRegex = formatRegex;
    }

    @JsonProperty("formatMask")
    public String getFormatMask() {
        return formatMask;
    }

    @JsonProperty("formatMask")
    public void setFormatMask(String formatMask) {
        this.formatMask = formatMask;
    }

    @JsonProperty("fieldType")
    public String getFieldType() {
        return fieldType;
    }

    @JsonProperty("fieldType")
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    @JsonProperty("derivation")
    public String setDerivation() {
        return derivation;
    }

}