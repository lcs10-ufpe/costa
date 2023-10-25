package br.ufpe.cin.executer.exception;

import org.springframework.http.HttpStatus;

public class ExecutorNotFoundException extends BusinessException {

    public ExecutorNotFoundException() {
        super("executor-6", HttpStatus.NOT_FOUND);
    }

}
