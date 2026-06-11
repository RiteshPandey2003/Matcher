package com.Image.Matcher.config;

import io.qdrant.client.QdrantClient;
import io.qdrant.client.grpc.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QdrantConnectionTest implements CommandLineRunner {

    private final QdrantClient qdrantClient;

    @Override
    public void run(String... args) {

        try {

            List<String> response =
                    qdrantClient.listCollectionsAsync().get();

            System.out.println("=================================");
            System.out.println("✅ Qdrant Connected Successfully");
            System.out.println("Collections:");



            System.out.println("=================================");

        } catch (Exception e) {

            System.out.println("=================================");
            System.out.println("❌ Qdrant Connection Failed");
            System.out.println(e.getMessage());
            System.out.println("=================================");

            e.printStackTrace();
        }
    }
}