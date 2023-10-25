package br.ufpe.cin.budgeting.model;

public enum CurrencyEnum {

    REAL(1, "Real"), //
    DOLAR(2, "Dolar"), //
    EURO(3, "Euro");

    private int code;
    private String description;

    CurrencyEnum(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    private String getDescription() {
        return description;
    }

}
