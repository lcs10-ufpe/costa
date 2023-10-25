package br.ufpe.cin.monitorcloudcost.service;

import br.ufpe.cin.monitorcloudcost.vo.CloudCostVO;

public interface GcpCloudBillingApiService {

    String getBillingInfo();

    String getBillingAccount();

    String bigQuery();

    CloudCostVO testBigQuery();

}
