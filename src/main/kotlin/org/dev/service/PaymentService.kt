package org.dev.service

import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import org.dev.model.MayaCreateReqPayload
import org.dev.model.SuccessResp

@ApplicationScoped
interface PaymentService {
    fun pay(payload: MayaCreateReqPayload): Uni<SuccessResp>
    fun getPayment(checkoutId: String): Uni<SuccessResp>
}