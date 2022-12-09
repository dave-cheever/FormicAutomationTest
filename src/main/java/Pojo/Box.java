
package Pojo;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "hidden",
        "subQuestionIndex"
})
@Generated("jsonschema2pojo")
public class Box {

    @JsonProperty("hidden")
    public boolean hidden;
    @JsonProperty("subQuestionIndex")
    public Object subQuestionIndex;

    @JsonProperty("hidden")
    public boolean isHidden() {
        return hidden;
    }

    @JsonProperty("hidden")
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @JsonProperty("subQuestionIndex")
    public Object getSubQuestionIndex() {
        return subQuestionIndex;
    }

    @JsonProperty("subQuestionIndex")
    public void setSubQuestionIndex(Object subQuestionIndex) {
        this.subQuestionIndex = subQuestionIndex;
    }
}