package br.ufpe.cin.monitorcloudcost.model;

public enum ActiveBillingEnum {

    YES(1, "YES"), NO(2, "NO");

    private int code;
    private String description;

    ActiveBillingEnum(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
