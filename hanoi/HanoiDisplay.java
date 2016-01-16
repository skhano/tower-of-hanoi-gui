package hanoi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

/**
 * A widget that displays the Towers of Hanoi Game.
 * @author Sam Khano
 */
public class HanoiDisplay extends JPanel {

    /** Color of display field. */
    private static final Color BACKGROUND_COLOR = new Color(0, 128, 255);

    /** Preferred dimensions of the playing surface. */
    static final int BOARD_WIDTH = 1400, BOARD_HEIGHT = 600;

    /** Height of a pole. */
    static final int POLE_HEIGHT = 200;

    /** Positions for the three poles. */
    static final int POLE_ZERO_X = 250, POLE_ONE_X = 625, POLE_TWO_X = 1000;

    /** Height of a disk. */
    static final int DISK_HEIGHT = 20;

    /**
     * A graphical representation of the Tower of Hanoi game.
     * @param game the Game object that is to be displayed
     */
    public HanoiDisplay(Game game) {
        _game = game;
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
    }

    /**
     * Change the display's Game instance.
     * @param game the new Game object that is to be displayed
     */
    public void changeGame(Game game) {
        _game = game;
    }

    /**
     * Paint a single pole on the widget.
     *
     * @param g Graphics2D object
     * @param pole the index of the pole to be drawn (0-2)
     */
    public void paintPole(Graphics2D g, int pole) {
        g.draw(new Line2D.Double(pole, POLE_HEIGHT, pole, 2 * POLE_HEIGHT - 1));
        g.draw(new Line2D.Double(pole + 1, POLE_HEIGHT, pole + 1,
                2 * POLE_HEIGHT - 1));
    }

    /**
     * Paint all three poles.
     * @param g Graphics2D object
     */
    public void paintAllPoles(Graphics2D g) {
        g.setColor(Color.BLACK);
        paintPole(g, POLE_ZERO_X);
        paintPole(g, POLE_ONE_X);
        paintPole(g, POLE_TWO_X);
    }

    /**
     * Paint a single disk of the given size at location P.
     * @param g Graphics2D object
     * @param disk the integer representation of the disk(size)
     * @param p the location of the disk to be painted
     */
    public void paintDisk(Graphics2D g, int disk, Point p) {
        int diskWidth = disk * 40 + 40;
        g.setColor(Color.BLACK);
        g.fillRect(p.x - diskWidth / 2, p.y - 50, diskWidth, DISK_HEIGHT);
        g.setColor(new Color(255, 178, 102));
        g.fillRect(p.x - diskWidth / 2 + 2, p.y - 50 + 2, diskWidth - 3,
                DISK_HEIGHT - 3);
    }

    /**
     * Paint a single disk of the given size at XLOCATION, with a height of
     * HEIGHT.
     * @param g
     * @param disk
     * @param xlocation
     * @param height
     */
    public void paintDisk(Graphics2D g, int disk, int xlocation, int height) {
        int diskWidth = disk * 40 + 40;
        g.setColor(Color.BLACK);
        g.fillRect(xlocation - diskWidth / 2, height, diskWidth, DISK_HEIGHT);
        g.setColor(new Color(255, 178, 102));
        g.fillRect(xlocation - diskWidth / 2 + 2, height + 1, diskWidth - 3,
                DISK_HEIGHT - 2);
    }

    /**
     * Paint all the disks at a given pole.
     * @param g Graphics2D object
     * @param pole index of pole to paint
     * @param xlocation the x coordinate of the pole
     */
    public void paintPolesDisks(Graphics2D g, int pole, int xlocation) {
        int height = 2 * POLE_HEIGHT - DISK_HEIGHT;
        for (Object d : _game.getDisks(pole)) {
            paintDisk(g, (int) d, xlocation, height);
            height -= DISK_HEIGHT;
        }
    }

    /**
     * Paint ALL 3 poles with ALL disks painted on them.
     * @param g Graphics2D object
     */
    public void paintAllDisks(Graphics2D g) {
        paintPolesDisks(g, 0, POLE_ZERO_X);
        paintPolesDisks(g, 1, POLE_ONE_X);
        paintPolesDisks(g, 2, POLE_TWO_X);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(BACKGROUND_COLOR);
        Rectangle b = g.getClipBounds();
        g2.fillRect(0, 0, b.width, b.height);
        g2.setColor(new Color(50, 255, 100));
        g2.fillRect(0, 2 * POLE_HEIGHT, b.width,
                b.height - 2 * POLE_HEIGHT + 1);
        paintAllPoles(g2);
        paintAllDisks(g2);
        if (_game.isMoving()) {
            paintDisk(g2, _game.getPiece(), new Point(_game.getPoint().x,
                    _game.getPoint().y - DISK_HEIGHT));
        }
    }

    /** The Game instance for the class. */
    private Game _game;

}