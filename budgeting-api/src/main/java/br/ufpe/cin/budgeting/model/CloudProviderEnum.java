package br.ufpe.cin.budgeting.model;

public enum CloudProviderEnum {

    AMAZON_WEB_SERVICES(1, "Amazon Web Services (AWS)"), //
    MICROSOFT_AZURE(2, "Microsoft Azure"), //
    GOOGLE_CLOUD_PLATFORM(3, "Google Cloud Platform (GCP)");

    private int code;
    private String description;

    CloudProviderEnum(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    private String getDescription() {
        return description;
    }

}
