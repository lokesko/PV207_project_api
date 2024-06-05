package com.example.pv207api.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ApacheCamelRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("undertow")
                .host("localhost")
                .port(8082);

        // http://localhost:8082/ticket/integration
        rest("/ticket")
                .post("/integration")
                        .consumes("application/json")
                        .produces("text/plain")
                        .to("direct:integrationRoutePost")
                .get("/integration")
                        .produces("text/plain")
                        .to("direct:integrationRouteGet");

        from("direct:integrationRouteGet")
                .routeId("integrationRouteGet")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(405))
                .setBody(constant("GET not allowed on this route."));


        from("direct:integrationRoutePost")
                .routeId("integrationRoutePost")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
                .setBody(constant("Successfully triggered a ticket integration route."));
    }
}
