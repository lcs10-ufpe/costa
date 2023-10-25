package br.ufpe.cin.budgeting.model;

public enum PaymentTypeEnum {

    ON_DEMAND(1, "On Demand");

    private int code;
    private String description;

    PaymentTypeEnum(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
