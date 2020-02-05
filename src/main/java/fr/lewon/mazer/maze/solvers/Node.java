package fr.lewon.mazer.maze.solvers;

import fr.lewon.mazer.maze.Move;

public class Node {

    private Node parent;
    private Move move;

    public Node(Node parent, Move move) {
        this.parent = parent;
        this.move = move;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
