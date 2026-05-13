package org.dev.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ErrorResp(
    @field:JsonProperty(value = "code")
    val code: String,
    @field:JsonProperty(value = "message")
    val message: String,
    @field:JsonProperty(value = "parameters")
    val parameters: List<Parameter>


)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Parameter(
    @field:JsonProperty(value = "description")
    val description: String,
    @field:JsonProperty(value = "field")
    val field: String,
)