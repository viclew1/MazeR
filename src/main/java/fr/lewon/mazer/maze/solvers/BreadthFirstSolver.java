package fr.lewon.mazer.maze.solvers;

import fr.lewon.mazer.maze.Maze;
import fr.lewon.mazer.maze.Move;
import fr.lewon.mazer.maze.Tile;

import java.util.ArrayList;
import java.util.List;

public class BreadthFirstSolver implements ISolver {

    private List<Tile> explored = new ArrayList<>();
    private List<Node> frontier = new ArrayList<>();
    private Node solution;

    @Override
    public Node solveMaze(Maze maze, Tile start, Tile dest) {
        this.explored = new ArrayList<>();
        this.frontier = new ArrayList<>();
        this.solution = null;
        this.explored.add(start);
        this.frontier.add(new Node(null, new Move(null, start, null)));

        long timeRefFps = System.nanoTime();
        while (!this.frontier.isEmpty()) {
            List<Node> newFrontier = new ArrayList<>();
            for (Node n : this.frontier) {
                if (n.getMove().getTo() == dest) {
                    this.solution = n;
                    return n;
                }
                List<Move> moves = maze.getMoves(n.getMove().getTo());
                for (Move m : moves) {
                    if (!this.explored.contains(m.getTo())) {
                        this.explored.add(m.getTo());
                        newFrontier.add(new Node(n, m));
                    }
                }
            }
            this.frontier = newFrontier;
        }
        return null;
    }

    @Override
    public List<Tile> getExploredTiles() {
        return this.explored;
    }

    @Override
    public List<Tile> getProcessingTiles() {
        List<Node> treatedNodes = new ArrayList<>();
        List<Tile> processingTiles = new ArrayList<>();
        if (this.solution != null) {
            Node nn = this.solution;
            while (nn != null && nn.getMove().getTo() != null) {
                processingTiles.add(nn.getMove().getTo());
                nn = nn.getParent();
            }
        } else {
            for (Node n : this.frontier) {
                while (n != null && !treatedNodes.contains(n) && n.getMove().getTo() != null) {
                    treatedNodes.add(n);
                    processingTiles.add(n.getMove().getTo());
                    n = n.getParent();
                }
            }
        }
        return processingTiles;
    }

}