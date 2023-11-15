package christmas.constants;

public enum Badges {
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private final String badgeName;

    Badges(String badgeName) {
        this.badgeName = badgeName;
    }

    public String badgeName() {
        return badgeName;
    }
}
