package hanoi;

import java.awt.Point;

/**
 * Towers of Hanoi game data (contents of each of the 3 poles).
 * @author Sam Khano
 */
public class Game {

    /**
     * Set up the contents of the TOH game.
     * @param n number of disks to play with
     */
    public Game(int n) {
        pieceMoving = false;
        diskSize = n;
        towers = new HanoiTower[3];
        pieceLocation = new Point();
        setUp();
    }

    /**
     * Add all the disks to the very first tower.
     */
    public void setUp() {
        for (int i = 0; i < 3; i += 1) {
            towers[i] = new HanoiTower(i);
        }

        for (int i = diskSize - 1; i >= 0; i -= 1) {
            towers[0].addDisk(i);
        }
    }

    /**
     * Move top disk from START to END if valid.
     * @param start beginning tower
     * @param end ending tower
     */
    public void move(int start, int end) {
        if (start != end && start >= 0 && end >= 0) {
            if (!towers[start].moveTopTo(towers[end]).equals("")) {
                moves += 1;
            }
        }
        pieceMoving = false;
    }

    /**
     * Push the disk on top of the pole (tower).
     * @param pole tower to push onto
     * @param disk disk to be pushed
     */
    public void push(int pole, int disk) {
        if (pole >= 0 && pole < 4) {
            towers[pole].addDisk(disk);
        }
    }

    /**
     * Solve the puzzle by moving all items from tower 0 to tower 2.
     */
    public void solve() {
        towers[0].moveDisks(diskSize, towers[2], towers[1]);
    }

    /**
     * Return true if THIS game has been solved successfully.
     * @return true if solved
     */
    public boolean isSolved() {
        return getDisks(2).length == diskSize;
    }

    /**
     * Return the lowest total number of moves to solve.
     * @return least number of moves
     */
    public int lowestScore() {
        return towers[0].getLeastMoves() + towers[1].getLeastMoves()
                + towers[2].getLeastMoves();
    }

    /**
     * Set moving to true if a piece is being dragged.
     */
    public void setMovingTrue() {
        pieceMoving = true;
    }

    /**
     * Set the location of the moving piece for display purposes.
     * @param value moving pieces's integer representation
     * @param x position
     * @param y position
     */
    public void setMovingLocation(int value, int x, int y) {
        piece = value;
        pieceLocation.x = x;
        pieceLocation.y = y;
    }

    /**
     * Return true if the piece is moving.
     * @return true if moving, false otherwise
     */
    public boolean isMoving() {
        return pieceMoving;
    }

    /**
     * Return the piece that is being moved.
     * @return moving disk
     */
    public int getPiece() {
        return piece;
    }

    /**
     * Return the location of the moving piece.
     * @return point object
     */
    public Point getPoint() {
        return pieceLocation;
    }

    /**
     * Return the number of moves made so far.
     * @return number of moves
     */
    public int getMoveCount() {
        return moves;
    }

    /**
     * Array of all the disks at the given POLE (tower).
     * @param pole tower
     * @return array of all disks (dynamic type is Integer)
     */
    public Object[] getDisks(int pole) {
        return towers[pole].getDisks();
    }

    /**
     * Return the top disk at the give POLE after POPPING it off the stack of
     * disks.
     * @param pole tower
     * @return integer representation of the disk
     */
    public int getTopDisk(int pole) {
        return towers[pole].getTop();
    }

    /** Number of disks to play with. */
    private int diskSize;

    /** An array of the 3 towers(poles). */
    private HanoiTower[] towers;

    /** Set to true if the user is dragging a piece(disk). */
    private boolean pieceMoving;

    /**
     * The piece(disk) that is moving, corresponds to the disk's integer
     * representation.
     */
    private int piece;

    /** Location of the moving piece (disk). */
    private Point pieceLocation;

    /** Number of times a move has been made. */
    private int moves;

}
