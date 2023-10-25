package br.ufpe.cin.monitorcloudcost.feign;

import br.ufpe.cin.monitorcloudcost.micosoft.authentication.AuthenticationADALService;
import org.springframework.stereotype.Component;

@Component
public class MicrosoftBillingApiClientImpl implements MicrosoftBillingApiClient {

    private final MicrosoftBillingApi microsoftBillingApi;

    private final AuthenticationADALService authenticationADALService;

    public MicrosoftBillingApiClientImpl(final MicrosoftBillingApi microsoftBillingApi,
            final AuthenticationADALService authenticationADALService) {
        this.microsoftBillingApi = microsoftBillingApi;
        this.authenticationADALService = authenticationADALService;
    }

    @Override
    public String getBillingPeriods() {
        final String bearerToken = authenticationADALService.genereterBearerToken();
        return microsoftBillingApi.getBillingPeriods(bearerToken);
    }

    @Override
    public String getUsageDetails() {
        final String bearerToken = authenticationADALService.genereterBearerToken();
        return microsoftBillingApi.getUsageDetails(bearerToken);
    }

    @Override
    public String getConsumption() {
        final String bearerToken = authenticationADALService.genereterBearerToken();
        return microsoftBillingApi.getConsumption(bearerToken);
    }

}
