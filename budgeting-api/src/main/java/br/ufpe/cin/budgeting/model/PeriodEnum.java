package br.ufpe.cin.budgeting.model;

public enum PeriodEnum {

    DAY(1, "Day"), //
    WEEK(2, "Week"), //
    MONTH(3, "Month"), //
    QUARTERLY(4, "Quarterly"), //
    YEAR(5, "Year");

    private int code;
    private String description;

    PeriodEnum(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    private String getDescription() {
        return description;
    }

}
