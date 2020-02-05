package fr.lewon.mazer.maze.solvers;

import fr.lewon.mazer.maze.Maze;
import fr.lewon.mazer.maze.Move;
import fr.lewon.mazer.maze.Tile;

import java.util.List;

public interface ISolver {

    Node solveMaze(Maze maze, Tile start, Tile dest);

    List<Tile> getExploredTiles();

    List<Tile> getProcessingTiles();
}
