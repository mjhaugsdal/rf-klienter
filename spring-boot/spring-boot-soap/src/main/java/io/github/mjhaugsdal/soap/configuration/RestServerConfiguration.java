package io.github.mjhaugsdal.soap.configuration;

import io.github.mjhaugsdal.soap.SoapClient;
import io.github.mjhaugsdal.soap.controller.RestController;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.openapi.OpenApiFeature;
import org.apache.cxf.jaxrs.swagger.ui.SwaggerUiConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RestServerConfiguration {

    @Value("${rest.server.address}")
    String address;

    @Bean
    public Server endpoint(final SoapClient soapClient) {
        JAXRSServerFactoryBean bean = new JAXRSServerFactoryBean();
        bean.setServiceBean(new RestController(soapClient));
        bean.setAddress(address);
        final OpenApiFeature feature = new OpenApiFeature();
        feature.setContactEmail("cxf@apache.org");
        feature.setLicense("Apache 2.0 License");
        feature.setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
        var config = new SwaggerUiConfig();
        config.setQueryConfigEnabled(true);
        feature.setSwaggerUiConfig(config);
        bean.setFeatures(List.of(feature));

        return bean.create();
    }

}
