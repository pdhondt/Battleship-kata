package be.kunlabora;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Ship> ships = new ArrayList<>();

    public String placeShip(ShipType shipType, int x, int y, Orientation orientation) {
        for (Ship ship : this.ships) {
            if (ship.getShipType().equals(shipType)) {
                return "You already placed a ship of this type. Choose another type.";
            }
        }
        Ship aShip = new Ship(shipType, new Coordinate(x, y), orientation);
        for (int i = 1; i < aShip.getShipType().getLength(); i++) {
            if (aShip.getOrientation().equals(Orientation.HORIZONTAL)) {
                if (verifyPositionWithinLimits(y + i)) {
                    aShip.getCoordinates().add(new Coordinate(x, y + i));
                } else {
                    return "Ship is too long and exceeds row limits";
                }
            }
            if (aShip.getOrientation().equals(Orientation.VERTICAL)) {
                if (verifyPositionWithinLimits(x + i)) {
                    aShip.getCoordinates().add(new Coordinate(x + i, y));
                } else {
                    return "Ship is too long and exceeds column limits";
                }
            }
        }
        if (verifyShipPosition(aShip)) {
            this.ships.add(aShip);
            return "Ship successfully placed";
        } else {
            return "Ship cannot overlap with an already placed ship! Try again.";
        }
    }

    public boolean verifyShipPosition(Ship newShip) {
        if (!this.ships.isEmpty()) {
            for (Coordinate coordinate : newShip.getCoordinates()) {
                for (Ship ship : this.ships) {
                    if (ship.getCoordinates().contains(coordinate)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean verifyPositionWithinLimits(int position) {
        return position <= 10;
    }

    public String render() {

        StringBuilder output = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                boolean isShipPresent = false;
                for (Ship ship : this.ships) {
                    for (Coordinate coordinate : ship.getCoordinates()) {
                        if (i == coordinate.x() && j == coordinate.y()) {
                            isShipPresent = true;
                            break;
                        }
                    }
                }
                if (isShipPresent) {
                    output.append(Icon.SHIP.getIcon());
                } else {
                    output.append(Icon.WAVE.getIcon());
                }
                if (j == 10) {
                    output.append(newLine);
                }
            }
        }
        return output.toString();
    }

    public String fire(int x, int y) {
        Coordinate firedAt = new Coordinate(x, y);
        for (Ship ship : this.ships) {
            if (ship.getCoordinates().contains(firedAt)) {
                return "Hit!";
            }
        }
        return "Miss!";
    }
}
