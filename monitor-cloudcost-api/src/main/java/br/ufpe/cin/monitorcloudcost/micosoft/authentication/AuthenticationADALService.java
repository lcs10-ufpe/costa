package br.ufpe.cin.monitorcloudcost.micosoft.authentication;

import br.ufpe.cin.monitorcloudcost.config.MicrosoftBillingApiProperties;
import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.naming.ServiceUnavailableException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationADALService {

    public static final String BEARER_TOKEN = "Bearer %s";

    private final MicrosoftBillingApiProperties microsoftBillingApiProperties;

    private final ExecutorService service;

    public AuthenticationADALService(final MicrosoftBillingApiProperties microsoftBillingApiProperties,
            @Qualifier("singleThreaded") final ExecutorService service) {
        this.microsoftBillingApiProperties = microsoftBillingApiProperties;
        this.service = service;
    }

    public String genereterBearerToken() {
        String bearerToken = null;

        try {
            final String token = getADCredentialsToken();
            bearerToken = String.format(BEARER_TOKEN, token);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return bearerToken;
    }

    /**
     * ADAL (biblioteca de autenticacao Azure Active Directory).
     *
     * No ADAL4J, a AuthenticationContext classe representa sua conexao com o servico de token de seguranca (STS) ou
     * servidor de autorizacao por meio de uma autoridade.
     */
    private String getADCredentialsToken() throws Exception {
        String accessToken;

        try {

            final AuthenticationContext context = new AuthenticationContext("https://login.microsoftonline.com/"
                    + microsoftBillingApiProperties.getTenantId() + "/oauth2/authorize", true, service);

            final ClientCredential cred = new ClientCredential(microsoftBillingApiProperties.getClientId(),
                    microsoftBillingApiProperties.getSecretKey());

            final Future<AuthenticationResult> future = context.acquireToken("https://management.azure.com", cred,
                    null);

            final AuthenticationResult result = future.get();

            if (result == null) {
                throw new ServiceUnavailableException("authentication result was null");
            }

            accessToken = result.getAccessToken();
        } catch (final Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return accessToken;
    }

}
