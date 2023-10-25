package br.ufpe.cin.monitorcloudcost.service.impl;

import br.ufpe.cin.monitorcloudcost.model.CloudProviderEnum;
import br.ufpe.cin.monitorcloudcost.model.CurrencyEnum;
import br.ufpe.cin.monitorcloudcost.service.GcpCloudBillingApiService;
import br.ufpe.cin.monitorcloudcost.vo.CloudCostVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.cloudbilling.Cloudbilling;
import com.google.api.services.cloudbilling.model.BillingAccount;
import com.google.api.services.cloudbilling.model.ProjectBillingInfo;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.QueryJobConfiguration;
import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class GcpCloudBillingApiServiceImpl implements GcpCloudBillingApiService {

    private final Cloudbilling cloudbillingService;

    private final BigQuery bigQuery;

    public GcpCloudBillingApiServiceImpl(final Cloudbilling cloudbillingService, final BigQuery bigQuery) {
        this.cloudbillingService = cloudbillingService;
        this.bigQuery = bigQuery;
    }

    @Override
    public String getBillingInfo() {
        String result = null;
        try {
            final Cloudbilling.Projects.GetBillingInfo request = cloudbillingService.projects()
                    .getBillingInfo("projects/buoyant-sunbeam-281019");
            final ProjectBillingInfo response = request.execute();

            final ObjectMapper objectMapper = new ObjectMapper();

            result = objectMapper.writeValueAsString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String getBillingAccount() {
        String result = null;
        try {
            Cloudbilling.BillingAccounts.Get request = cloudbillingService.billingAccounts()
                    .get("billingAccounts/01AE4E-6D72BB-155997");
            BillingAccount response = request.execute();

            result = response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public String bigQuery() {
        String result = "";

        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT ");
            query.append("invoice.month, ");
            query.append("SUM(cost) ");
            query.append(" + SUM(IFNULL((SELECT SUM(c.amount) ");
            query.append("               FROM UNNEST(credits) c), 0)) ");
            query.append(" AS total, ");
            query.append("(SUM(CAST(cost * 1000000 AS int64)) ");
            query.append(" + SUM(IFNULL((SELECT SUM(CAST(c.amount * 1000000 as int64)) ");
            query.append("               FROM UNNEST(credits) c), 0))) / 1000000 ");
            query.append(" AS total_exact ");
            query.append("FROM `monitor_cloudcost_api.gcp_billing_export_v1_01AE4E_6D72BB_155997` ");
            query.append("GROUP BY 1 ");
            query.append("ORDER BY 1 ASC ");
            query.append(";");

            QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query.toString()).build();
            for (FieldValueList row : bigQuery.query(queryConfig).getValues()) {
                for (FieldValue val : row) {
                    result += val.toString();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public CloudCostVO testBigQuery() {

        final CloudCostVO vo = new CloudCostVO();
        vo.setCloudProviderEnum(CloudProviderEnum.GOOGLE_CLOUD_PLATFORM);
        vo.setCurrencyEnum(CurrencyEnum.DOLAR);

        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT ");
            query.append("invoice.month, ");
            query.append("SUM(cost) ");
            query.append(" + SUM(IFNULL((SELECT SUM(c.amount) ");
            query.append("               FROM UNNEST(credits) c), 0)) ");
            query.append(" AS total, ");
            query.append("(SUM(CAST(cost * 1000000 AS int64)) ");
            query.append(" + SUM(IFNULL((SELECT SUM(CAST(c.amount * 1000000 as int64)) ");
            query.append("               FROM UNNEST(credits) c), 0))) / 1000000 ");
            query.append(" AS total_exact ");
            query.append("FROM `monitor_cloudcost_api.gcp_billing_export_v1_01AE4E_6D72BB_155997` ");
            query.append("GROUP BY 1 ");
            query.append("ORDER BY 1 ASC ");
            query.append(";");

            QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query.toString()).build();
            for (FieldValueList row : bigQuery.query(queryConfig).getValues()) {
                final String amount = row.get(row.size() - 1).getStringValue();
                vo.setAmount(new BigDecimal(amount));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return vo;
    }

}
