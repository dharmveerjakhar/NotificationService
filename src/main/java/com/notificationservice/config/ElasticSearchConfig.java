package com.notificationservice.config;

import com.notificationservice.constants.Constants;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.notificationservice.repositories.elasticsearch")
@ComponentScan(basePackages = {"com.notificationservice"})
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    @Value("${elasticsearch.url}")
    public String url;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
       final ClientConfiguration clientConfiguration = ClientConfiguration
               .builder()
               .connectedTo(url)
               .build();
       return RestClients.create(clientConfiguration).rest();
    }
}
