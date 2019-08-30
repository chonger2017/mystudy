package com.dsh.daniel.xierqi.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class SearchConfig {
    @Autowired
    EsConfig config;

    @Bean
    public TransportClient client() throws UnknownHostException {
        TransportAddress node = new TransportAddress(InetAddress.getByName(config.getIp()), config.getPort());

        Settings settings = Settings.builder().put("cluster.name",config.getClusterName()).build();

        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddresses(node);
        return client;
    }
}
