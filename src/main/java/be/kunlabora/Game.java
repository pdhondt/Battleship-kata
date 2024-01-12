package be.kunlabora;

import be.kunlabora.util.*;

import java.util.*;

public class Game {
    final private int OCEAN_LIMIT = 10;
    private final Fleet fleetPlayer1 = new Fleet();
    private final Fleet fleetPlayer2 = new Fleet();
    private int hitsPlayer1 = 0;
    private int hitsPlayer2 = 0;
    private int missesPlayer1 = 0;
    private int missesPlayer2 = 0;
    public Fleet getFleetCurrentPlayer(int currentPlayer) {
        return currentPlayer == 1 ? this.fleetPlayer1 : this.fleetPlayer2;
    }
    public int getHitsByPlayer(int currentPlayer) {
        return currentPlayer == 1 ? this.hitsPlayer1 : this.hitsPlayer2;
    }
    public int getMissesByPlayer(int currentPlayer) {
        return currentPlayer == 1 ? this.missesPlayer1 : this.missesPlayer2;
    }
    public void increaseHitsByPlayer(int currentPlayer) {
        if (currentPlayer == 1) {
            this.hitsPlayer1++;
        } else {
            this.hitsPlayer2++;
        }
    }
    public void increaseMissesByPlayer(int currentPlayer) {
        if (currentPlayer == 1) {
            this.missesPlayer1++;
        } else {
            this.missesPlayer2++;
        }
    }

    public String placeShip(ShipType shipType, int x, int y, Orientation orientation, int currentPlayer) {
        Fleet fleetCurrentPlayer;
        try {
            fleetCurrentPlayer = currentPlayer == 1 ? this.fleetPlayer1 : this.fleetPlayer2;
            fleetCurrentPlayer.allShipsPlaced();
            fleetCurrentPlayer.isShipTypeAvailable(shipType);
            Ship aShip = new Ship(shipType, new Coordinate(x, y, Icon.SHIP), orientation);
            verifyShipCoordinates(aShip, x, y);
            fleetCurrentPlayer.isShipOverlapping(aShip);
            fleetCurrentPlayer.addShipToFleet(aShip);
            return "Ship successfully placed";
        } catch (ShipTypeException | OceanLimitsException | ShipOverlapException | AllShipsPlacedException ex) {
            return "Warning: " + ex.getMessage();
        }
    }

    private void verifyShipCoordinates(Ship ship, int x, int y) throws OceanLimitsException {
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

    private boolean verifyPositionWithinLimits(int position) {
        return position <= OCEAN_LIMIT;
    }

    public String render(int currentPlayer, boolean renderOwn) {
        Fleet fleetToRender;
        if (renderOwn) {
            fleetToRender = currentPlayer == 1 ? this.fleetPlayer1 : this.fleetPlayer2;
        } else {
            fleetToRender = currentPlayer == 1 ? this.fleetPlayer2 : this.fleetPlayer1;
        }

        StringBuilder output = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        for (int i = 1; i <= OCEAN_LIMIT; i++) {
            for (int j = 1; j <= OCEAN_LIMIT; j++) {
                boolean isShipPresent = false;
                boolean isDamaged = false;
                boolean isSunk = false;
                for (Ship ship : fleetToRender.getShips()) {
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
        Fleet fleetOtherPlayer = currentPlayer == 1 ? this.fleetPlayer2 : this.fleetPlayer1;
        Coordinate firedAt = new Coordinate(x, y, Icon.SHIP);
        for (Ship ship : fleetOtherPlayer.getShips()) {
            Set<Coordinate> coordinates = ship.getCoordinates();
            if (coordinates.contains(firedAt)) {
                this.increaseHitsByPlayer(currentPlayer);
                ship.increaseHitCount();
                Coordinate hit = new Coordinate(x, y, Icon.DAMAGE);
                coordinates.remove(firedAt);
                coordinates.add(hit);
                ship.setCoordinates(coordinates);
                if (ship.checkDestroyed()) {
                    if (fleetOtherPlayer.checkWinner()) {
                        return "All ships destroyed, player " + currentPlayer + " wins, by " + this.getHitsByPlayer(currentPlayer) + " hits out of " + (this.getHitsByPlayer(currentPlayer) + this.getMissesByPlayer(currentPlayer)) + " attempts!";
                    }
                    return "Ship Destroyed and Sinking!";
                } else {
                    return "Hit! Number of hits: " + this.getHitsByPlayer(currentPlayer);
                }
            }
        }
        this.increaseMissesByPlayer(currentPlayer);
        return "Miss! Number of misses: " + this.getMissesByPlayer(currentPlayer);
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
