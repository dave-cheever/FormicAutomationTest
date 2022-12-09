
package Pojo;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "text",
        "__typename"
})
@Generated("jsonschema2pojo")
public class Label {

    @JsonProperty("text")
    public String text;
    @JsonProperty("__typename")
    public String __typename;

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("__typename")
    public String get__typename() {
        return __typename;
    }

    @JsonProperty("__typename")
    public void set__typename(String __typename) {
        this.__typename = __typename;
    }
}