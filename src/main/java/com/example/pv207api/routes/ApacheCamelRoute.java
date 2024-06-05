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

        // http://localhost:8082/customer/new
        rest("/customer")
                .post("/new")
                        .consumes("application/json")
                        .produces("text/plain")
                        .to("direct:camelRoutePost")
                .get("/new")
                        .produces("text/plain")
                        .to("direct:camelRouteGet");

        from("direct:camelRouteGet")
                .routeId("camelRouteGet")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(405))
                .setBody(constant("Invalid method"));

                from("direct:camelRoutePost")
                .routeId("camelRoutePost")
                .process(exchange -> {
                    String body = exchange.getIn().getBody(String.class);
                    
                    // we save it into the DB here...

                    // Mock processing
                    String response = "Successfully saved the new customer into the DB.";
                    exchange.getMessage().setBody(response);
                    exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
                });
    }
}
