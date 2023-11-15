package be.kunlabora;

import java.util.HashSet;
import java.util.Set;

public class Ship {
    private final ShipType shipType;
    private final Orientation orientation;
    private Set<Coordinate> coordinates = new HashSet<>();
    public Ship(ShipType shipType, Coordinate startPosition, Orientation orientation) {
        this.shipType = shipType;
        this.orientation = orientation;
        this.coordinates.add(startPosition);
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
}
