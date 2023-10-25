package br.ufpe.cin.executer.config;

import com.offbytwo.jenkins.JenkinsServer;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JenkinsApiConfig {

    @Value("${jenkins.user}")
    private String user;

    @Value("${jenkins.token}")
    private String token;

    @Bean
    public JenkinsServer jenkinsServer() throws URISyntaxException {
        final JenkinsServer jenkins = new JenkinsServer(new URI("http://127.0.0.1:8100"),
                user, token);
        return jenkins;
    }

}
