package br.ufpe.cin.monitorcloudcost.model;

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

    public String getDescription() {
        return description;
    }

}
