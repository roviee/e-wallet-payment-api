package org.dev.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class MayaCreateReqPayload(
    @field:JsonProperty(value = "totalAmount")
    val totalAmount: MayaCreateTotalAmount,
    @field:JsonProperty(value = "redirectUrl")
    val redirectUrl: MayaCreateRedirectUrl,
    @field:JsonProperty(value = "requestReferenceNumber")
    val reqReferenceNo: String
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class MayaCreateTotalAmount(
    @field:JsonProperty(value = "value")
    val value: Double,
    @field:JsonProperty(value = "currency")
    val currency: String
)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class MayaCreateRedirectUrl(
    @field:JsonProperty(value = "success")
    val successRedirectUrl: String,
    @field:JsonProperty(value = "failure")
    val failRedirectUrl: String,
    @field:JsonProperty(value = "cancel")
    val cancelRedirectUrl: String,
)