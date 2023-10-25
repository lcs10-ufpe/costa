package br.ufpe.cin.costestimation.exception;

import org.springframework.http.HttpStatus;

public class CostEstimationAlreadyExistException extends BusinessException {

    public CostEstimationAlreadyExistException() {
        super("costestimation-2", HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
