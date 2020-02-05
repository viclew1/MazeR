package fr.lewon.mazer.maze.solvers;

public enum Solvers {

    CLUSTER(new BreadthFirstSolver(), "Breadth first"),
    DEPTH_FIRST(new DepthFirstSolver(), "Depth first");

    private final String label;
    private final ISolver solver;

    Solvers(ISolver solver, String label) {
        this.solver = solver;
        this.label = label;
    }

    public ISolver getImpl() {
        return this.solver;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
