package br.ufpe.cin.analyzecloudcost.service.exception;

import org.springframework.http.HttpStatus;

public class BudgetingNotFoundException extends BusinessException {

    public BudgetingNotFoundException() {
        super("budgets-6", HttpStatus.NOT_FOUND);
    }

}
