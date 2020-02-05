package fr.lewon.mazer.maze;

public enum Direction {

    LEFT(0, -1),
    RIGHT(0, 1),
    BOTTOM(1, 0),
    UP(-1, 0);

    static {
        LEFT.oppositeDirection = RIGHT;
        RIGHT.oppositeDirection = LEFT;
        UP.oppositeDirection = BOTTOM;
        BOTTOM.oppositeDirection = UP;
    }

    private final int diffRow;
    private final int diffCol;
    private Direction oppositeDirection;

    Direction(int diffRow, int diffCol) {
        this.diffRow = diffRow;
        this.diffCol = diffCol;
    }

    public int getDiffRow() {
        return diffRow;
    }

    public int getDiffCol() {
        return diffCol;
    }

    public Direction getOppositeDirection() {
        return oppositeDirection;
    }
}
