package br.ufpe.cin.monitorcloudcost.service;

import br.ufpe.cin.monitorcloudcost.vo.CloudCostVO;

public interface AwsCostExplorerApiService {

    String getCostAndUsage();

    CloudCostVO testGetCostAndUsage();

}
