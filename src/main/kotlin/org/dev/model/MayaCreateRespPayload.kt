package org.dev.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class MayaCreateRespPayload(
    @field:JsonProperty(value = "checkoutId")
    val checkoutId: String? = null,
    @field:JsonProperty(value = "redirectUrl")
    val redirectUrl: String? = null,
)
