package fr.lewon.mazer.maze;

import fr.lewon.mazer.maze.util.RandomUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Maze {

    private int rowCount;
    private int colCount;
    private Tile[][] tiles;
    private Set<Tile> generated;
    private Set<Tile> built;

    public Maze(int rows, int cols) {
        this.rowCount = rows;
        this.colCount = cols;
        this.tiles = new Tile[rows][cols];
    }

    public int getRowCount() {
        return this.rowCount;
    }

    public int getColCount() {
        return this.colCount;
    }

    public Tile[][] getTiles() {
        return this.tiles;
    }

    /**
     * Generates the maze starting from the tile located at (startCol, startRow)
     *
     * @param startRow
     * @param startCol
     * @param twoDoorsMutationRate
     */
    public void generate(int startRow, int startCol, float twoDoorsMutationRate) {
        this.tiles = new Tile[this.rowCount][this.colCount];
        this.built = new HashSet<>();
        this.generated = new HashSet<>();
        Tile startTile = this.getTile(startRow, startCol);
        Set<Tile> expandingTiles = new HashSet<>();
        expandingTiles.add(startTile);
        this.built.add(startTile);
        this.generated.add(startTile);

        Random rdm = new Random();
        while (!expandingTiles.isEmpty()) {
            Set<Tile> newExpandingTiles = new HashSet<>();
            for (Tile t : expandingTiles) {
                List<Move> possibleMoves = this.getMovesToNonGeneratedNeighbors(t);
                if (!possibleMoves.isEmpty()) {
                    Move nextMove = RandomUtil.getRandomElement(possibleMoves);
                    this.generated.add(nextMove.getTo());
                    newExpandingTiles.add(nextMove.getTo());
                    t.addDirection(nextMove.getDirection());
                    nextMove.getTo().addDirection(nextMove.getDirection().getOppositeDirection());
                    if (rdm.nextFloat() < twoDoorsMutationRate) {
                        newExpandingTiles.add(t);
                    }
                }
            }
            expandingTiles = newExpandingTiles;
            if (expandingTiles.isEmpty() && this.generated.size() != this.rowCount * this.colCount) {
                while (expandingTiles.isEmpty()) {
                    for (Tile t : this.built.stream().filter(t -> !this.generated.contains(t)).collect(Collectors.toList())) {
                        if (rdm.nextFloat() < 0.04) {
                            List<Tile> generatedNeighbors = this.getGeneratedNeighbors(t);
                            if (!generatedNeighbors.isEmpty()) {
                                expandingTiles.add(RandomUtil.getRandomElement(generatedNeighbors));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Row count : ").append(this.rowCount).append("\n");
        sb.append("Col count : ").append(this.colCount).append("\n");
        for (int row = 0; row < this.rowCount; row++) {
            for (int col = 0; col < this.colCount; col++) {
                Tile t = this.getTile(row, col);
                sb.append(t.getOpenDirections().contains(Direction.BOTTOM) ? " " : "_");
                sb.append(t.getOpenDirections().contains(Direction.RIGHT) ? " " : "|");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private Tile getTile(int row, int col) {
        if (row < 0 || row >= this.rowCount || col < 0 || col >= this.colCount) {
            return null;
        }
        if (this.tiles[row][col] == null) {
            this.tiles[row][col] = new Tile(row, col);
            this.built.add(this.tiles[row][col]);
        }
        return this.tiles[row][col];
    }

    private List<Move> getMovesToNonGeneratedNeighbors(Tile tile) {
        List<Move> moves = new ArrayList<>();
        for (Direction d : Direction.values()) {
            Tile neighbor = this.getTile(tile.getRow() + d.getDiffRow(), tile.getCol() + d.getDiffCol());
            if (neighbor != null && !this.generated.contains(neighbor)) {
                this.built.add(neighbor);
                moves.add(new Move(tile, neighbor, d));
            }
        }
        return moves;
    }

    private List<Tile> getGeneratedNeighbors(Tile tile) {
        List<Tile> neighbors = new ArrayList<>();
        for (Direction d : Direction.values()) {
            Tile neighbor = this.getTile(tile.getRow() + d.getDiffRow(), tile.getCol() + d.getDiffCol());
            if (neighbor != null && this.generated.contains(neighbor)) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }

    public List<Move> getMoves(Tile tile) {
        List<Move> moves = new ArrayList<>();
        for (Direction d : tile.getOpenDirections()) {
            Tile neighbor = this.getTile(tile.getRow() + d.getDiffRow(), tile.getCol() + d.getDiffCol());
            moves.add(new Move(tile, neighbor, d));
        }
        return moves;
    }

}
