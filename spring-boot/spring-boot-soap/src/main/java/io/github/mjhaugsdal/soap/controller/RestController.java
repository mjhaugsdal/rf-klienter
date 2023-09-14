package io.github.mjhaugsdal.soap.controller;

import io.github.mjhaugsdal.soap.SoapClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.ergo.reseptformidleren.webservices.rekvirent.M1;

import java.nio.charset.StandardCharsets;

@org.springframework.web.bind.annotation.RestController
@Path("/api")
@Tag(name = "xml controller", description = "API")
public class RestController {

    final SoapClient soapClient;

    public RestController(SoapClient soapClient) {
        this.soapClient = soapClient;
    }

    @POST
    @Path("/m1")
    @Consumes(MediaType.APPLICATION_XML)
    @Operation(
            summary = "Post M1"
    )
    public Response rekvirentM1(String xml) {
        var m1 = new M1();
        m1.setDokument(xml.getBytes(StandardCharsets.UTF_8));
        var response = soapClient.rekvirentWeb().rekvirentWebServiceM1(m1);
        return Response.ok(response.getDokument()).build();
    }
}
