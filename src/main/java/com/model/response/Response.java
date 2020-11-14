
package com.model.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "terms",
    "relations",
        "message",
        "status",
        "error",
        "timestamp"
})
public class Response {

    @JsonProperty("terms")
    private List<Term> terms = null;
    @JsonProperty("relations")
    private List<Relation> relations = null;
    @JsonProperty("timestamp")
    private String timestamp=null;
    @JsonProperty("status")
    private Integer status=null;
    @JsonProperty("error")
    private String error=null;
    @JsonProperty("message")
    private String message=null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Response(List<Term> terms, List<Relation> relations) {
        this.terms = terms;
        this.relations = relations;
    }

    public Response() {
    }
    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }
    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }
    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }
    @JsonProperty("error")
    public String getError() {
        return error;
    }
    @JsonProperty("error")
    public void setError(String error) {
        this.error = error;
    }
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("terms")
    public List<Term> getTerms() {
        return terms;
    }

    @JsonProperty("terms")
    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    @JsonProperty("relations")
    public List<Relation> getRelations() {
        return relations;
    }

    @JsonProperty("relations")
    public void setRelations(List<Relation> relations) {
        this.relations = relations;
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
