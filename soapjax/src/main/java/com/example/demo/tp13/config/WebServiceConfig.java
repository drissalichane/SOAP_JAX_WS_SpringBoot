package com.example.demo.tp13.config;

import com.example.demo.tp13.ws.BankSoapService;
import lombok.AllArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class WebServiceConfig {

    private BankSoapService bankSoapService;
    private Bus bus;

    @Bean
    public EndpointImpl endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, bankSoapService);
        endpoint.publish("/bank");
        return endpoint;
    }
}
