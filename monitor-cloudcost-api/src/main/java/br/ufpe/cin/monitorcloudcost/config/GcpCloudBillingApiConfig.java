package br.ufpe.cin.monitorcloudcost.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.cloudbilling.Cloudbilling;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class GcpCloudBillingApiConfig {

    private final ConfigurableApplicationContext configurableApplicationContext;

    public GcpCloudBillingApiConfig(final ConfigurableApplicationContext configurableApplicationContext) {
        this.configurableApplicationContext = configurableApplicationContext;
    }

    @Bean
    public Resource resource() {
        final Resource resource = configurableApplicationContext.getResource("classpath:/json/billing-api.json");
        return resource;
    }

    @Bean
    public GoogleCredential googleCredential() throws IOException {
        // final Resource resource = configurableApplicationContext
        // .getResource("classpath:/json/billing-api.json");
        // .getResource("classpath:/json/billing-cloudcost-api.json");

        final GoogleCredential credential = GoogleCredential.fromStream(resource().getInputStream())
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));

        return credential;
    }

    @Bean
    public Cloudbilling cloudbilling() throws IOException, GeneralSecurityException {
        final HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        final JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        final Cloudbilling cloudbillingService = new Cloudbilling.Builder(httpTransport, jsonFactory,
                googleCredential())
                        .setApplicationName("monitor-cloudcost-api")
                        .build();

        return cloudbillingService;
    }

    @Bean
    public BigQuery bigQuery() throws IOException {
        final BigQuery bigquery = BigQueryOptions.newBuilder().setProjectId("buoyant-sunbeam-281019")
                .setCredentials(ServiceAccountCredentials.fromStream(resource().getInputStream())).build().getService();

        return bigquery;
    }

}
