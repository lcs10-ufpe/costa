package br.ufpe.cin.costestimation.exception;

import org.springframework.http.HttpStatus;

public class CostEstimationNotFoundException extends BusinessException {

    public CostEstimationNotFoundException() {
        super("costestimation-1", HttpStatus.NOT_FOUND);
    }

}
