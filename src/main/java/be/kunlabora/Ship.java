package be.kunlabora;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final ShipType shipType;
    private final Coordinate startPosition;

    private final Orientation orientation;

    private List<Coordinate> coordinates = new ArrayList<>();
    public Ship(ShipType shipType, Coordinate startPosition, Orientation orientation) {
        this.shipType = shipType;
        this.startPosition = startPosition;
        this.orientation = orientation;
        this.coordinates.add(startPosition);
    }

    public List<Coordinate> getCoordinates() {
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
