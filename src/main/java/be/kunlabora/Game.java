package be.kunlabora;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Ship> ships = new ArrayList<>();

    public void placeShip(int start, int end) {
        Ship aShip = new Ship(start, end);
        ships.add(aShip);
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
