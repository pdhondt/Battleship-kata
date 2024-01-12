package be.kunlabora;

public class Player {
    private String name;
    private final Fleet fleet = new Fleet();
    private int hits = 0;
    private int misses = 0;

    public String getName() {
        return name;
    }

    public Fleet getFleet() {
        return fleet;
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void increaseHits() {
        this.hits++;
    }

    public void increaseMisses() {
        this.misses++;
    }
}
