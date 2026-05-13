package org.dev

import io.quarkus.vertx.web.Body
import io.quarkus.vertx.web.Param
import jakarta.ws.rs.core.MediaType
import io.quarkus.vertx.web.Route
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import org.dev.model.MayaCreateReqPayload
import org.dev.model.SuccessResp
import org.dev.service.PaymentService

@ApplicationScoped
class EWalletResource (
    private val service: PaymentService
) {
    @Route(
        methods = [Route.HttpMethod.POST],
        path = "/v2/pay/maya",
        consumes = [MediaType.APPLICATION_JSON],
        produces = [MediaType.APPLICATION_JSON]
    )
    fun createPayment(
        @Body payload: MayaCreateReqPayload
    ) : Uni<SuccessResp> {
            return service.pay(payload)
    }

    @Route(
        methods = [Route.HttpMethod.GET],
        path = "/v2/get/maya/:checkoutId",
        produces = [MediaType.APPLICATION_JSON]
    )
    fun getPayment(
        @Param("checkoutId") checkoutId: String,
    ) : Uni<SuccessResp> {
        return service.getPayment(checkoutId)
    }
}