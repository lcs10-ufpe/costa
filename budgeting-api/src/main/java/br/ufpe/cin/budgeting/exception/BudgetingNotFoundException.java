package br.ufpe.cin.budgeting.exception;

import org.springframework.http.HttpStatus;

public class BudgetingNotFoundException extends BusinessException {

    public BudgetingNotFoundException() {
        super("budgetings-6", HttpStatus.NOT_FOUND);
    }

}
