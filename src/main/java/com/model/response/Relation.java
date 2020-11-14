
package com.model.response;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "t1",
    "rel",
    "t2"
})
public class Relation {

    @JsonProperty("t1")
    private Integer t1;
    @JsonProperty("rel")
    private String rel;
    @JsonProperty("t2")
    private String t2;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Relation(Integer t1, String rel, String t2) {
        this.t1 = t1;
        this.rel = rel;
        this.t2 = t2;
    }

    public Relation() {
    }

    @JsonProperty("t1")
    public Integer getT1() {
        return t1;
    }

    @JsonProperty("t1")
    public void setT1(Integer t1) {
        this.t1 = t1;
    }

    @JsonProperty("rel")
    public String getRel() {
        return rel;
    }

    @JsonProperty("rel")
    public void setRel(String rel) {
        this.rel = rel;
    }

    @JsonProperty("t2")
    public String getT2() {
        return t2;
    }

    @JsonProperty("t2")
    public void setT2(String t2) {
        this.t2 = t2;
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
