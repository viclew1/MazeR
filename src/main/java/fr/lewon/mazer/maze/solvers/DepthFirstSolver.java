package fr.lewon.mazer.maze.solvers;

import fr.lewon.mazer.maze.Maze;
import fr.lewon.mazer.maze.Move;
import fr.lewon.mazer.maze.Tile;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSolver implements ISolver {

    private List<Tile> explored = new ArrayList<>();
    private Node processingNode;

    @Override
    public Node solveMaze(Maze maze, Tile start, Tile dest) {
        this.explored = new ArrayList<>();
        this.processingNode = new Node(null, new Move(null, start, null));
        this.explored.add(start);
        return this.exploreNode(maze, dest, this.processingNode, this.explored);
    }

    private Node exploreNode(Maze maze, Tile dest, Node toExplore, List<Tile> explored) {
        this.processingNode = toExplore;
        if (toExplore.getMove().getTo() == dest) {
            return toExplore;
        }
        List<Move> moves = maze.getMoves(this.processingNode.getMove().getTo());
        for (Move m : moves) {
            if (!explored.contains(m.getTo())) {
                explored.add(m.getTo());
                Node exploredNode = this.exploreNode(maze, dest, new Node(toExplore, m), explored);
                if (exploredNode != null) {
                    return exploredNode;
                }
            }
        }
        return null;
    }

    @Override
    public List<Tile> getExploredTiles() {
        return this.explored;
    }

    @Override
    public List<Tile> getProcessingTiles() {
        List<Tile> processingTiles = new ArrayList<>();
        Node n = this.processingNode;
        while (n != null && n.getMove().getTo() != null) {
            processingTiles.add(n.getMove().getTo());
            n = n.getParent();
        }
        return processingTiles;
    }
}
