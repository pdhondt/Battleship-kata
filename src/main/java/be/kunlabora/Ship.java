package be.kunlabora;

public class Ship {
    private final int start;
    private final int end;

    public Ship(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
