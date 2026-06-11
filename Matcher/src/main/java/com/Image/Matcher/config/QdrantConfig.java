package com.Image.Matcher.config;

import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class QdrantConfig {

    @Value("${qdrant.host}")
    private String hostOrUrl;

    @Value("${qdrant.api-key}")
    private String apiKey;

    private static final int DEFAULT_GRPC_PORT = 6334;

    @Bean
    public QdrantClient qdrantClient() {
        String host = hostOrUrl;
        int port = DEFAULT_GRPC_PORT;
        boolean useTls = true;

        // If user provided a full URL, parse it
        if (hostOrUrl.startsWith("http://") || hostOrUrl.startsWith("https://")) {
            try {
                URI uri = new URI(hostOrUrl);
                host = uri.getHost();
                if (uri.getPort() != -1) {
                    port = uri.getPort();
                } else {
                    // keep default port
                    port = DEFAULT_GRPC_PORT;
                }
                useTls = "https".equalsIgnoreCase(uri.getScheme());
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Invalid qdrant.host URL: " + hostOrUrl, e);
            }
        } else {
            // If host contains ":port" (e.g., host:1234), split it
            if (hostOrUrl.contains(":")) {
                String[] parts = hostOrUrl.split(":", 2);
                host = parts[0];
                try {
                    port = Integer.parseInt(parts[1]);
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Invalid port in qdrant.host: " + hostOrUrl, ex);
                }
            }
        }

        return new QdrantClient(
                QdrantGrpcClient.newBuilder(host, port, useTls)
                        .withApiKey(apiKey)
                        .build()
        );
    }
}
