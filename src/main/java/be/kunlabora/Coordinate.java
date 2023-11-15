package be.kunlabora;

import java.util.Objects;

public class Coordinate {
    private final int x;
    private final int y;
    private Icon status;
    public Coordinate(int x, int y, Icon status) {
        this.x = x;
        this.y = y;
        this.status = status;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Icon getStatus() {
        return status;
    }

    public void setStatus(Icon status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate that)) return false;
        return getX() == that.getX() && getY() == that.getY() && getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getStatus());
    }
}
