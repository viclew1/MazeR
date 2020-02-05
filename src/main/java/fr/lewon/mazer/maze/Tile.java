package fr.lewon.mazer.maze;

import java.util.HashSet;
import java.util.Set;

public class Tile {

    private final int row;
    private final int col;
    private Set<Direction> openDirections;

    public Tile(int row, int col) {
        this(row, col, new HashSet<>());
    }

    public Tile(int row, int col, Set<Direction> openDirections) {
        this.row = row;
        this.col = col;
        this.openDirections = openDirections;
    }

    public void addDirection(Direction direction) {
        this.openDirections.add(direction);
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public Set<Direction> getOpenDirections() {
        return this.openDirections;
    }
}
