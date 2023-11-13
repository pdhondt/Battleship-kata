package be.kunlabora;

public enum ShipType {
    CARRIER(5),
    BATTLESHIP(4),
    DESTROYER(3),
    SUBMARINE(3),
    PATROLBOAT(2);
    private final int length;
    ShipType(int length) {
        this.length = length;
    }
    public int getLength() {
        return this.length;
    }
}
