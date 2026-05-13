package org.dev.service

import com.fasterxml.jackson.databind.ObjectMapper
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.inject.ConfigProperty
import jakarta.ws.rs.ProcessingException
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.jboss.resteasy.reactive.ClientWebApplicationException
import org.dev.client.MayaClient
import org.dev.helper.AuthHelper
import org.dev.model.ErrorResp
import org.dev.model.MayaCreateReqPayload
import org.dev.model.MayaCreateRespPayload
import org.dev.model.MayaRespPayload
import org.dev.model.SuccessResp
import org.dev.model.TxnBillRespPayload

@ApplicationScoped
class PaymentServiceImpl (
    private val mapper: ObjectMapper,
    @RestClient private val client: MayaClient,
): PaymentService {

    @ConfigProperty(name = "maya.public-key")
    lateinit var mayaPublicKey: String
    @ConfigProperty(name = "maya.secret-key")
    lateinit var mayaSecretKey: String

    override fun pay(payload: MayaCreateReqPayload): Uni<SuccessResp>  {
        val authHeader = AuthHelper.basicAuthHeader(mayaPublicKey)

        return client.createPayment(authHeader, payload)
            .onFailure().recoverWithItem { ex ->
                when (ex) {
                    is ClientWebApplicationException -> ex.response
                    is ProcessingException -> Response.status(Response.Status.BAD_GATEWAY).build()
                    else -> throw ex
                }
            }
            .onItem().transform { response ->
                val respBody = response.readEntity(String::class.java)
                if (response.status == 200 || response.status == 201) {
                        val mayaResp = mapper.readValue(respBody, MayaCreateRespPayload::class.java)
                        SuccessResp(
                            payload = TxnBillRespPayload(
                                code = mayaResp.checkoutId,
                                paymentUrl = mayaResp.redirectUrl
                            )
                        )
                } else {
                    val error = mapper.readValue(respBody, ErrorResp::class.java)
                    SuccessResp(error = error)
                }
            }
    }

    override fun getPayment(checkoutId: String): Uni<SuccessResp> {
        val authHeader = AuthHelper.basicAuthHeader(mayaSecretKey)

        return client.getPayment(authHeader, checkoutId)
            .onItem().transform { response ->
                val respBody = response.readEntity(String::class.java)
                if (response.status == 200 || response.status == 201) {
                    val mayaResp = mapper.readValue(respBody, MayaRespPayload::class.java)
                    SuccessResp(
                        payload = MayaRespPayload (
                           id = mayaResp.id,
                           isPaid = mayaResp.isPaid,
                           status = mayaResp.status,
                           amount = mayaResp.amount,
                           currency = mayaResp.currency,
                           canVoid = mayaResp.canVoid,
                           canRefund = mayaResp.canRefund,
                           canCapture = mayaResp.canCapture,
                           createdAt = mayaResp.createdAt,
                           updatedAt = mayaResp.updatedAt,
                           requestReferenceNumber = mayaResp.requestReferenceNumber
                        )
                    )
                } else {
                    val error = mapper.readValue(respBody, ErrorResp::class.java)
                    SuccessResp(error = error)
                }
            }

    }
}
