package com.Image.Matcher.config;

import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QdrantConfig {

    @Value("${qdrant.host}")
    private String host;

    @Value("${qdrant.api-key}")
    private String apiKey;

    @Bean
    public QdrantClient qdrantClient() {

        return new QdrantClient(
                QdrantGrpcClient.newBuilder(
                                host,
                                6334,     // Qdrant Cloud gRPC port
                                true      // TLS enabled
                        )
                        .withApiKey(apiKey)
                        .build()
        );
    }
}