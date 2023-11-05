package be.kunlabora;

public enum Icon {
    WAVE("🌊"),
    SHIP("🚢"),
    DAMAGE("💥"),
    SINK("🏊");

    private final String icon;

    Icon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return this.icon;
    }

}
