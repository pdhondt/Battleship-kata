package be.kunlabora;

import java.util.HashSet;
import java.util.Set;

public class Ship {
    private final ShipType shipType;
    private final Coordinate startPosition;

    private final Orientation orientation;

    private Set<Coordinate> coordinates = new HashSet<>();
    public Ship(ShipType shipType, Coordinate startPosition, Orientation orientation) {
        this.shipType = shipType;
        this.startPosition = startPosition;
        this.orientation = orientation;
        this.coordinates.add(startPosition);
    }

    public Set<Coordinate> getCoordinates() {
        return coordinates;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Coordinate getStartPosition() {
        return startPosition;
    }

    public ShipType getShipType() {
        return shipType;
    }
}
