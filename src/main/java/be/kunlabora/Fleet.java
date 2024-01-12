package be.kunlabora;

import be.kunlabora.util.AllShipsPlacedException;
import be.kunlabora.util.ShipOverlapException;
import be.kunlabora.util.ShipTypeException;

import java.util.ArrayList;
import java.util.List;

public class Fleet {
    private final List<Ship> ships = new ArrayList<>();
    public List<Ship> getShips() {
        return ships;
    }
    public int getFleetSize() {
        return this.ships.size();
    }
    public void allShipsPlaced() throws AllShipsPlacedException {
        if (this.ships.size() >= 5) {
            throw new AllShipsPlacedException("All 5 shiptypes have been placed!");
        }
    }
    public void isShipTypeAvailable(ShipType shipType) throws ShipTypeException {
        this.ships.forEach(ship -> {
            if (ship.getShipType().equals(shipType)) {
                throw new ShipTypeException("You already placed a ship of this type. Choose another type.");
            }
        });
    }
    public void isShipOverlapping(Ship newShip) throws ShipOverlapException {
        if (!this.ships.isEmpty()) {
            newShip.getCoordinates().forEach(coordinate -> {
                this.ships.forEach(ship -> {
                    if (ship.getCoordinates().contains(coordinate)) {
                        throw new ShipOverlapException("Ship cannot overlap with an already placed ship! Try again.");
                    }
                });
            });
        }
    }
    public void addShipToFleet(Ship ship) {
        this.ships.add(ship);
    }
    public boolean checkWinner() {
        int countSunken = 0;
        for (Ship ship : this.ships) {
            for (Coordinate coordinate : ship.getCoordinates()) {
                if (coordinate.getStatus().equals(Icon.SINK)) {
                    countSunken++;
                }
            }
        }
        return countSunken == 17;
    }
}
