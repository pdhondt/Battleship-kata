package be.kunlabora;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Ship> ships = new ArrayList<>();

    public String placeShip(int start, int end) {
        Ship aShip = new Ship(start, end);
        if (verifyShipPosition(aShip)) {
            this.ships.add(aShip);
            return "Ship successfully placed";
        } else {
            return "Ship cannot overlap with an already placed ship! Try again.";
        }

    }

    public boolean verifyShipPosition(Ship newShip) {
        if (!this.ships.isEmpty()) {
            for (Ship ship : this.ships) {
                if ((newShip.getStart() >= ship.getStart() && newShip.getStart() <= ship.getEnd()) ||
                        (newShip.getEnd() >= ship.getStart() && newShip.getEnd() <= ship.getEnd())) {
                    return false;
                }
            }
        }
        return true;
    }

    public String render() {

        StringBuilder output = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        for (int i = 1; i <= 100; i++) {
            boolean isShipPresent = false;

            for (Ship ship : this.ships) {
                if (i >= ship.getStart() && i <= ship.getEnd()) {
                    isShipPresent = true;
                    break;
                }
            }

            if (isShipPresent) {
                output.append(Icon.SHIP.getIcon());
            } else {
                output.append(Icon.WAVE.getIcon());
            }

            if (i % 10 == 0) {
                output.append(newLine);
            }
        }
        return output.toString();
    }


}
