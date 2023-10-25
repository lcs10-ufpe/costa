package br.ufpe.cin.executer.model;

public enum ActionEnum {

    START_EXPERIMENT(1, "Iniciando o experimento"), //
    MIGRATION(2, "Migrando entre clouds"), //
    END_EXPERIMENT(3, "Finalizando o experimento");

    private int code;
    private String description;

    ActionEnum(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
