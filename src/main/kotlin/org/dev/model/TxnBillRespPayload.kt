package org.dev.model

import com.fasterxml.jackson.annotation.JsonProperty

data class TxnBillRespPayload(
    @field:JsonProperty(value = "code")
    val code: String? = null,
    @field:JsonProperty(value = "paymentUrl")
    val paymentUrl: String? = null,
)
