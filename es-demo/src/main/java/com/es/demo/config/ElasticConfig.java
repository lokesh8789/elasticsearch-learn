package com.es.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;

@Configuration
public class ElasticConfig extends ElasticsearchConfigurationSupport {
    @Override
    protected boolean writeTypeHints() {
        return false;
    }
}
