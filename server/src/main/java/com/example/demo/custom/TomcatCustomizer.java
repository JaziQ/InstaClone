package com.example.demo.custom;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;

import org.springframework.boot.web.embedded.tomcat.*;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;


@Component
public class TomcatCustomizer implements
        WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    private final int maxUploadSizeInMb = 10 * 1024 * 1024;

    @Override
    public void customize(TomcatServletWebServerFactory factory) {

        factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol) {
                    ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(maxUploadSizeInMb);
                }
            }
        });
    }
}

