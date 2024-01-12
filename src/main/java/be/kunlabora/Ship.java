package be.kunlabora;

import java.util.HashSet;
import java.util.Set;

public class Ship {
    private final ShipType shipType;
    private final Orientation orientation;
    private Set<Coordinate> coordinates = new HashSet<>();
    private int hitCount;
    public Ship(ShipType shipType, Coordinate startPosition, Orientation orientation) {
        this.shipType = shipType;
        this.orientation = orientation;
        this.coordinates.add(startPosition);
        this.hitCount = 0;
    }
    public Set<Coordinate> getCoordinates() {
        return coordinates;
    }
    public Orientation getOrientation() {
        return orientation;
    }
    public ShipType getShipType() {
        return shipType;
    }
    public void setCoordinates(Set<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
    public int getHitCount() {
        return hitCount;
    }
    public void increaseHitCount() {
        this.hitCount++;
    }
    public boolean checkDestroyed() {
        Set<Coordinate> coordinates = this.getCoordinates();
        if (this.getHitCount() == this.getShipType().getLength()) {
            coordinates.forEach((coordinate -> coordinate.setStatus(Icon.SINK)));
            this.setCoordinates(coordinates);
            return true;
        }
        return false;
    }
}
