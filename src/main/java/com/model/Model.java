
package com.model;

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
    "batchcomplete",
    "query"
})
public class Model {

    @JsonProperty("batchcomplete")
    private String batchcomplete;
    @JsonProperty("query")
    private Query query;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("batchcomplete")
    public String getBatchcomplete() {
        return batchcomplete;
    }

    @JsonProperty("batchcomplete")
    public void setBatchcomplete(String batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    @JsonProperty("query")
    public Query getQuery() {
        return query;
    }

    @JsonProperty("query")
    public void setQuery(Query query) {
        this.query = query;
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
