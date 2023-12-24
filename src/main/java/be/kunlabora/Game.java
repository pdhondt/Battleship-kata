package be.kunlabora;

import be.kunlabora.util.*;

import java.util.*;

public class Game {
    final private int OCEAN_LIMIT = 10;
    private List<Ship> shipsPlayer1 = new ArrayList<>();
    private List<Ship> shipsPlayer2 = new ArrayList<>();
    public List<Ship> getShipsCurrentPlayer(int currentPlayer) {
        return currentPlayer == 1 ? this.shipsPlayer1 : this.shipsPlayer2;
    }

    public String placeShip(ShipType shipType, int x, int y, Orientation orientation, int currentPlayer) {
        List<Ship> shipsCurrentPlayer;
        try {
            shipsCurrentPlayer = currentPlayer == 1 ? this.shipsPlayer1 : this.shipsPlayer2;
            allShipsPlaced(shipsCurrentPlayer);
            isShipTypeAvailable(shipsCurrentPlayer, shipType);
            Ship aShip = new Ship(shipType, new Coordinate(x, y, Icon.SHIP), orientation);
            verifyShipCoordinates(aShip, x, y, orientation);
            isShipOverlapping(aShip, shipsCurrentPlayer);
            shipsCurrentPlayer.add(aShip);
            return "Ship successfully placed";
        } catch (ShipTypeException | OceanLimitsException | ShipOverlapException | AllShipsPlacedException ex) {
            return "Warning: " + ex.getMessage();
        }
    }

    private void verifyShipCoordinates(Ship ship, int x, int y, Orientation orientation) throws OceanLimitsException {
        for (int i = 1; i < ship.getShipType().getLength(); i++) {
            if (ship.getOrientation().equals(Orientation.HORIZONTAL)) {
                if (verifyPositionWithinLimits(y + i)) {
                    ship.getCoordinates().add(new Coordinate(x, y + i, Icon.SHIP));
                } else {
                    throw new OceanLimitsException("Ship is too long and exceeds ocean limits");
                }
            }
            if (ship.getOrientation().equals(Orientation.VERTICAL)) {
                if (verifyPositionWithinLimits(x + i)) {
                    ship.getCoordinates().add(new Coordinate(x + i, y, Icon.SHIP));
                } else {
                    throw new OceanLimitsException("Ship is too long and exceeds ocean limits");
                }
            }
        }
    }


    private void isShipOverlapping(Ship newShip, List<Ship> ships) throws ShipOverlapException {
        if (!ships.isEmpty()) {
            newShip.getCoordinates().forEach(coordinate -> {
                ships.forEach(ship -> {
                    if (ship.getCoordinates().contains(coordinate)) {
                        throw new ShipOverlapException("Ship cannot overlap with an already placed ship! Try again.");
                    }
                });
            });
        }
    }

    private void allShipsPlaced(List<Ship> ships) throws AllShipsPlacedException {
        if (ships.size() >= 5) {
            throw new AllShipsPlacedException("All 5 shiptypes have been placed!");
        }
    }

    private boolean verifyPositionWithinLimits(int position) {
        return position <= OCEAN_LIMIT;
    }

    private void isShipTypeAvailable(List<Ship> ships, ShipType shipType) throws ShipTypeException {
        ships.forEach(ship -> {
            if (ship.getShipType().equals(shipType)) {
                throw new ShipTypeException("You already placed a ship of this type. Choose another type.");
            }
        });
    }


    public String render(int currentPlayer, boolean renderOwn) {
        List<Ship> shipsToRender;
        if (renderOwn) {
            shipsToRender = currentPlayer == 1 ? this.shipsPlayer1 : this.shipsPlayer2;
        } else {
            shipsToRender = currentPlayer == 1 ? this.shipsPlayer2 : this.shipsPlayer1;
        }

        StringBuilder output = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        for (int i = 1; i <= OCEAN_LIMIT; i++) {
            for (int j = 1; j <= OCEAN_LIMIT; j++) {
                boolean isShipPresent = false;
                boolean isDamaged = false;
                boolean isSunk = false;
                for (Ship ship : shipsToRender) {
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
                } else if (isShipPresent && renderOwn) {
                    output.append(Icon.SHIP.getIcon());
                } else {
                    output.append(Icon.WAVE.getIcon());
                }
                if (j == OCEAN_LIMIT) {
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

    public void askUserInputAndPlaceShips() {
        try {
            System.out.println("Which ship do you want to place? ");
            Scanner scanner = new Scanner(System.in);
            ShipType shipToPlace = verifyInputAndMapToShipType(scanner.nextLine().toUpperCase());
            System.out.println("You want to place a " + shipToPlace);
            System.out.println("Where do you want to place it? ");
            System.out.println("Coordinate x: ");
            int x = scanner.nextInt();
            System.out.println("Coordinate y: ");
            int y = scanner.nextInt();
            System.out.println("Your coordinates are: " + x + "," + y);
            System.out.println("What is the orientation? ");
            Orientation orientation = verifyInputAndMapToOrientation(scanner.next().toUpperCase());
            System.out.println("Orientation is " + orientation);
            System.out.println(this.placeShip(shipToPlace, x, y, orientation, 1));
        } catch (ShipTypeException | OrientationException ex) {
            System.out.println(ex.getMessage());
            askUserInputAndPlaceShips();
        }

    }

    public ShipType verifyInputAndMapToShipType(String shipInput) throws ShipTypeException {
        return switch (shipInput) {
            case "CARRIER" -> ShipType.CARRIER;
            case "DESTROYER" -> ShipType.DESTROYER;
            case "BATTLESHIP" -> ShipType.BATTLESHIP;
            case "SUBMARINE" -> ShipType.SUBMARINE;
            case "PATROLBOAT" -> ShipType.PATROLBOAT;
            default -> throw new ShipTypeException("Not a valid ship type");
        };
    }

    public Orientation verifyInputAndMapToOrientation(String orientationInput) throws OrientationException {
        return switch (orientationInput) {
            case "HORIZONTAL" -> Orientation.HORIZONTAL;
            case "VERTICAL" -> Orientation.VERTICAL;
            default -> throw new OrientationException("Not a valid orientation");
        };
    }
}
