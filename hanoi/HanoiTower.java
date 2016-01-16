package hanoi;

import java.util.Stack;

/**
 * Represents a tower of disks in the TOH game.
 * @author Sam Khano
 */
public class HanoiTower {

    /**
     * Create a tower of disks at the given location index.
     * @param p pole (index) of the tower.
     */
    public HanoiTower(int p) {
        disks = new Stack<Integer>();
        pole = p;
        leastMoves = 0;
    }

    /**
     * Return all of the disks that make up THIS tower.
     * @return an array of disks (dynamic type is Integer)
     */
    public Object[] getDisks() {
        return disks.toArray();
    }

    /**
     * Get number of disks in THIS tower.
     * @return number of disks
     */
    public int getNumberOfDisks() {
        return disks.size();
    }

    /**
     * Return the top disk.
     * @return integer representation of the disk on top
     */
    public int getTop() {
        if (!disks.isEmpty()) {
            return disks.pop();
        }
        return -1;
    }

    /**
     * Return the least number of moves.
     * @return leastMoves
     */
    public int getLeastMoves() {
        return leastMoves;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((disks == null) ? 0 : disks.hashCode());
        result = prime * result + pole;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        HanoiTower other = (HanoiTower) obj;
        if (disks == null) {
            if (other.disks != null) {
                return false;
            }
        } else if (!disks.equals(other.disks)) {
            return false;
        }
        if (pole != other.pole) {
            return false;
        }
        return true;
    }

    /**
     * Push DISK onto THIS tower if valid.
     * @param disk to be to pushed
     * @return true if valid
     */
    public boolean addDisk(int disk) {
        if (!disks.isEmpty() && disks.peek() <= disk) {
            return false;
        }
        disks.push(disk);
        return true;
    }

    /**
     * Move the top disk of THIS tower to tower T.
     * @param t the new location of MY top disk
     * @return the move in the form of a string, "" if invalid
     */
    public String moveTopTo(HanoiTower t) {
        int top = disks.peek();
        if (t.addDisk(top)) {
            disks.pop();
            leastMoves += 1;
        } else {
            return "";
        }
        return "Move disk " + top + " from" + pole + " to " + t.pole;
    }

    /**
     * Move every disk from THIS tower to the BUFFER tower, and then to DEST
     * tower.
     * @param n number of disks to move
     * @param dest final position
     * @param buffer middle position
     */
    public void moveDisks(int n, HanoiTower dest, HanoiTower buffer) {
        if (n > 0) {
            moveDisks(n - 1, buffer, dest);
            moveTopTo(dest);
            buffer.moveDisks(n - 1, dest, this);
        }
    }

    /** A stack of the disks at THIS tower. */
    private Stack<Integer> disks;

    /** The index of the tower (pole number). */
    private int pole;

    /** The least number of moves to win. */
    private int leastMoves;
}
