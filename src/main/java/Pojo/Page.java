package Pojo;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "guidId",
        "objects"
})
@Generated("jsonschema2pojo")
public class Page {

    @JsonProperty("guidId")
    private String guidId;
    @JsonProperty("objects")
    private List<Object> objects = null;

    @JsonProperty("guidId")
    public String getGuidId() {
        return guidId;
    }

    @JsonProperty("guidId")
    public void setGuidId(String guidId) {
        this.guidId = guidId;
    }

    @JsonProperty("objects")
    public List<Object> getObjects() {
        return objects;
    }

    @JsonProperty("objects")
    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

}