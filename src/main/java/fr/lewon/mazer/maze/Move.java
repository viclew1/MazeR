package fr.lewon.mazer.maze;

public class Move {

    private Tile from;
    private Tile to;
    private Direction direction;

    public Move(Tile from, Tile to, Direction direction) {
        this.from = from;
        this.to = to;
        this.direction = direction;
    }

    public Tile getFrom() {
        return from;
    }

    public void setFrom(Tile from) {
        this.from = from;
    }

    public Tile getTo() {
        return to;
    }

    public void setTo(Tile to) {
        this.to = to;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
