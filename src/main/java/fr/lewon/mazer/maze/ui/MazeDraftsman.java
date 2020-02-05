package fr.lewon.mazer.maze.ui;

import fr.lewon.mazer.maze.Direction;
import fr.lewon.mazer.maze.Maze;
import fr.lewon.mazer.maze.Tile;
import fr.lewon.mazer.maze.solvers.ISolver;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MazeDraftsman {

    public void drawSolver(Graphics g, ISolver solver, int x, int y, double tileSize) {
        List<Tile> exploredTiles = solver.getExploredTiles();
        List<Tile> processingTiles = solver.getProcessingTiles();
        g.setColor(Color.BLUE);
        for (Tile tile : new ArrayList<>(exploredTiles)) {
            int row = tile.getRow();
            int col = tile.getCol();
            g.fillRect((int) (col * tileSize), (int) (row * tileSize), (int) tileSize, (int) tileSize);
            g.drawRect((int) (col * tileSize), (int) (row * tileSize), (int) tileSize, (int) tileSize);
        }
        g.setColor(Color.RED);
        for (Tile tile : new ArrayList<>(processingTiles)) {
            int row = tile.getRow();
            int col = tile.getCol();
            g.fillRect((int) (col * tileSize), (int) (row * tileSize), (int) tileSize, (int) tileSize);
            g.drawRect((int) (col * tileSize), (int) (row * tileSize), (int) tileSize, (int) tileSize);
        }
    }

    public void drawMaze(Graphics g, Maze maze, int x, int y, double tileSize) {
        int rowCount = maze.getRowCount();
        int colCount = maze.getColCount();
        Tile[][] tiles = maze.getTiles();
        g.setColor(Color.BLACK);
        g.drawRect(x, y, (int) (colCount * tileSize), (int) (rowCount * tileSize));
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                Tile tile = tiles[row][col];
                if (tile != null && !tile.getOpenDirections().contains(Direction.BOTTOM)) {
                    g.drawLine((int) (col * tileSize), (int) ((row + 1) * tileSize), (int) ((col + 1) * tileSize), (int) ((row + 1) * tileSize));
                }
                if (tile != null && !tile.getOpenDirections().contains(Direction.UP)) {
                    g.drawLine((int) (col * tileSize), (int) (row * tileSize), (int) ((col + 1) * tileSize), (int) (row * tileSize));
                }
                if (tile != null && !tile.getOpenDirections().contains(Direction.LEFT)) {
                    g.drawLine((int) (col * tileSize), (int) (row * tileSize), (int) (col * tileSize), (int) ((row + 1) * tileSize));
                }
                if (tile != null && !tile.getOpenDirections().contains(Direction.RIGHT)) {
                    g.drawLine((int) ((col + 1) * tileSize), (int) (row * tileSize), (int) ((col + 1) * tileSize), (int) ((row + 1) * tileSize));
                }
            }
        }
    }

}
