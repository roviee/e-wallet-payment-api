package org.dev.client;
import io.quarkus.vertx.web.Body
import io.smallrye.mutiny.Uni
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.dev.model.MayaCreateReqPayload;

@RegisterRestClient(configKey="maya-service")
interface MayaClient {
    @POST
    @Path("/checkout/v1/checkouts")
    @Consumes(value = [MediaType.APPLICATION_JSON])
    @Produces(value = [MediaType.APPLICATION_JSON])
    fun createPayment(
        @HeaderParam("Authorization") authHeader: String,
        @Body reqPayload: MayaCreateReqPayload
    ): Uni<Response>

    @GET
    @Path("/payments/v1/payments/{checkoutId}")
    @Produces(value = [MediaType.APPLICATION_JSON])
    fun getPayment(
        @HeaderParam("Authorization") authHeader: String,
        @PathParam("checkoutId") checkoutId: String
    ): Uni<Response>
}
