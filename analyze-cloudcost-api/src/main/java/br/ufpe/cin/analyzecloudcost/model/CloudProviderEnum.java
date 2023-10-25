package br.ufpe.cin.analyzecloudcost.model;

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

    public String getDescription() {
        return description;
    }

    public static CloudProviderEnum getCloudProviderEnum(final Integer code) {

        for (CloudProviderEnum cloudProviderEnum : values()) {

            if (cloudProviderEnum.code == code) {
                return cloudProviderEnum;
            }

        }

        return null;
    }
}
