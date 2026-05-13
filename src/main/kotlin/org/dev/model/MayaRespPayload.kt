package org.dev.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class MayaRespPayload (
    @field:JsonProperty(value = "id")
    val id: String,
    @field:JsonProperty("isPaid")
    val isPaid: Boolean,
    @field:JsonProperty(value = "status")
    val status: String,
    @field:JsonProperty(value = "amount")
    val amount: String,
    @field:JsonProperty(value = "currency")
    val currency: String,
    @field:JsonProperty(value = "canVoid")
    val canVoid: Boolean,
    @field:JsonProperty(value = "canRefund")
    val canRefund: Boolean,
    @field:JsonProperty(value = "canCapture")
    val canCapture: Boolean,
    @field:JsonProperty(value = "createdAt")
    val createdAt: String,
    @field:JsonProperty(value = "updatedAt")
    val updatedAt: String,
    @field:JsonProperty(value = "requestReferenceNumber")
    val requestReferenceNumber: String,
)