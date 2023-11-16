package be.kunlabora;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Game {
    private List<Ship> shipsPlayer1 = new ArrayList<>();
    private List<Ship> shipsPlayer2 = new ArrayList<>();
    private List<Ship> shipsCurrentPlayer = new ArrayList<>();
    public String placeShip(ShipType shipType, int x, int y, Orientation orientation, int currentPlayer) {
        shipsCurrentPlayer = currentPlayer == 1 ? this.shipsPlayer1 : this.shipsPlayer2;
        if (shipsCurrentPlayer.size() < 5) {
            for (Ship ship : shipsCurrentPlayer) {
                if (ship.getShipType().equals(shipType)) {
                    return "You already placed a ship of this type. Choose another type.";
                }
            }
            Ship aShip = new Ship(shipType, new Coordinate(x, y, Icon.SHIP), orientation);
            for (int i = 1; i < aShip.getShipType().getLength(); i++) {
                if (aShip.getOrientation().equals(Orientation.HORIZONTAL)) {
                    if (verifyPositionWithinLimits(y + i)) {
                        aShip.getCoordinates().add(new Coordinate(x, y + i, Icon.SHIP));
                    } else {
                        return "Ship is too long and exceeds row limits";
                    }
                }
                if (aShip.getOrientation().equals(Orientation.VERTICAL)) {
                    if (verifyPositionWithinLimits(x + i)) {
                        aShip.getCoordinates().add(new Coordinate(x + i, y, Icon.SHIP));
                    } else {
                        return "Ship is too long and exceeds column limits";
                    }
                }
            }
            if (verifyShipPosition(aShip, shipsCurrentPlayer)) {
                shipsCurrentPlayer.add(aShip);
                return "Ship successfully placed";
            } else {
                return "Ship cannot overlap with an already placed ship! Try again.";
            }
        } else {
            return "All 5 shiptypes have been placed!";
        }

    }

    public boolean verifyShipPosition(Ship newShip, List<Ship> ships) {
        if (!ships.isEmpty()) {
            for (Coordinate coordinate : newShip.getCoordinates()) {
                for (Ship ship : ships) {
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

    public String render(int currentPlayer) {
        shipsCurrentPlayer = currentPlayer == 1 ? this.shipsPlayer1 : this.shipsPlayer2;

        StringBuilder output = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                boolean isShipPresent = false;
                boolean isDamaged = false;
                boolean isSunk = false;
                for (Ship ship : shipsCurrentPlayer) {
                    for (Coordinate coordinate : ship.getCoordinates()) {
                        if (i == coordinate.getX() && j == coordinate.getY()) {
                            isShipPresent = true;
                            if (coordinate.getStatus() == Icon.DAMAGE) {
                                isDamaged = true;
                            }
                            if (coordinate.getStatus() == Icon.SINK) {
                                isSunk = true;
                            }
                            break;
                        }
                    }
                }
                if (isShipPresent && isSunk) {
                    output.append(Icon.SINK.getIcon());
                } else if (isShipPresent && isDamaged) {
                    output.append(Icon.DAMAGE.getIcon());
                } else if (isShipPresent) {
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

    public String fire(int x, int y, int currentPlayer) {
        List<Ship> shipsOtherPlayer = currentPlayer == 1 ? this.shipsPlayer2 : this.shipsPlayer1;
        Coordinate firedAt = new Coordinate(x, y, Icon.SHIP);
        for (Ship ship : shipsOtherPlayer) {
            Set<Coordinate> coordinates = ship.getCoordinates();
            if (coordinates.contains(firedAt)) {
                Coordinate hit = new Coordinate(x, y, Icon.DAMAGE);
                coordinates.remove(firedAt);
                coordinates.add(hit);
                ship.setCoordinates(coordinates);
                if (checkDestroyed(ship)) {
                    if (checkWinner(shipsOtherPlayer)) {
                        return "All ships destroyed, player " + currentPlayer + " wins!";
                    }
                    return "Ship Destroyed and Sinking!";
                } else {
                    return "Hit!";
                }
            }
        }
        return "Miss!";
    }

    public boolean checkDestroyed(Ship ship) {
        int hitCount = 0;
        Set<Coordinate> coordinates = ship.getCoordinates();
        for (Coordinate coordinate : coordinates) {
            if (coordinate.getStatus().equals(Icon.DAMAGE)) {
                hitCount++;
            }
        }
        if (hitCount == ship.getShipType().getLength()) {
            for (Coordinate coordinate : coordinates) {
                coordinate.setStatus(Icon.SINK);
            }
            ship.setCoordinates(coordinates);
            return true;
        }
        return false;
    }

    public boolean checkWinner(List<Ship> ships) {
        int countSunken = 0;
        for (Ship ship : ships) {
            for (Coordinate coordinate : ship.getCoordinates()) {
                if (coordinate.getStatus().equals(Icon.SINK)) {
                    countSunken++;
                }
            }
        }
        return countSunken == 17;
    }

    public void askUserInput() {
        System.out.println("Which ship do you want to place? ");
        Scanner scanner = new Scanner(System.in);
        String shipToPlace = scanner.nextLine();
        System.out.println("You want to place a " + shipToPlace);
        System.out.println("Where do you want to place it? ");
        System.out.println("Coordinate x: ");
        int x = scanner.nextInt();
        System.out.println("Coordinate y: ");
        int y = scanner.nextInt();
        System.out.println("Your coordinates are: " + x + "," + y);
        System.out.println("What is the orientation? ");
        String orientation = scanner.next();
        System.out.println("Orientation is " + orientation);
    }
}
