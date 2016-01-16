package hanoi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HanoiTowerTest {

    /**
     * Test the addDisks method.
     */
    @Test
    public void addDiskTest() {
        HanoiTower tower = new HanoiTower(0);
        tower.addDisk(4);
        tower.addDisk(3);
        tower.addDisk(9);

        assertEquals(2, tower.getNumberOfDisks());

        tower.addDisk(0);
        assertEquals(3, tower.getNumberOfDisks());
    }

    /**
     * Test the equals method.
     */
    @Test
    public void equalsTest() {
        HanoiTower tower1 = simpleTower(0);
        HanoiTower tower2 = simpleTower(0);
        HanoiTower tower3 = simpleTower(1);

        assertEquals(tower1, tower2);
        assertFalse(tower1.equals(tower3));
    }

    /**
     * Test the moveTopTo method.
     */
    @Test
    public void moveTopToTest() {
        HanoiTower tower1 = simpleTower(0);
        HanoiTower tower2 = new HanoiTower(1);
        HanoiTower tower3 = new HanoiTower(2);
        tower3.addDisk(2);
        tower1.moveTopTo(tower2);

        assertEquals(3, tower1.getNumberOfDisks());
        assertEquals(1, tower2.getNumberOfDisks());

        tower2.moveTopTo(tower1);

        assertEquals(4, tower1.getNumberOfDisks());
        assertEquals(0, tower2.getNumberOfDisks());

        tower1.moveTopTo(tower2);
        assertTrue(tower3.moveTopTo(tower2).equals(""));
    }

    /**
     * Test the moveDisks method.
     */
    @Test
    public void moveDisksTest() {
        HanoiTower tower1 = simpleTower(0);
        HanoiTower tower2 = new HanoiTower(1);
        HanoiTower tower3 = new HanoiTower(2);
        tower1.moveDisks(4, tower3, tower2);

        assertEquals(0, tower1.getNumberOfDisks());
        assertEquals(0, tower2.getNumberOfDisks());
        assertEquals(4, tower3.getNumberOfDisks());
    }

    /**
     * Helper method that creates a simple tower at index POLE, with 4 disks.
     * @param pole index for the tower
     * @return a hanoi tower
     */
    public static HanoiTower simpleTower(int pole) {
        HanoiTower tower = new HanoiTower(pole);
        tower.addDisk(3);
        tower.addDisk(2);
        tower.addDisk(1);
        tower.addDisk(0);
        return tower;
    }

}
