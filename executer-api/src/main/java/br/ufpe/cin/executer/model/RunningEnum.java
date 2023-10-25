package br.ufpe.cin.executer.model;

public enum RunningEnum {

    YES(1, "YES"), NO(2, "NO");

    private int code;
    private String description;

    RunningEnum(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
