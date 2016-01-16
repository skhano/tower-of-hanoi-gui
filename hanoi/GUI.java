package hanoi;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

/**
 * Graphical User Interface for the Towers of Hanoi game.
 * @author Sam Khano
 */
public class GUI {

    /**
     * Create a new GUI.
     * @param size number of disks to play with.
     */
    public GUI(int size) {
        n = size;
        game = new Game(n);
        bestScoringGame = new Game(n);
        bestScoringGame.solve();
        display = new HanoiDisplay(game);
        _initialDrag = new Point();
        _dragPoint = null;
        _clicked = new Point();
    }

    /**
     * Start the GUI.
     */
    public void go() {
        lab = new JLabel("Welcome to Towers of Hanoi");
        lab.setFont(new Font("Serif", Font.PLAIN, 20));
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(BorderLayout.NORTH, lab);
        MouseObject mouse = new MouseObject();
        frame.addMouseListener(mouse);
        frame.addMouseMotionListener(mouse);

        frame.getContentPane().add(display);

        menuBar = new JMenuBar();

        optionMenu = new JMenu("Options");
        optionMenu.setFont(new Font("Serif", Font.BOLD, 18));
        resetGameItem = new JMenuItem("Reset Game");

        resetGameItem.addActionListener(new ResetListener());
        optionMenu.add(resetGameItem);

        menuBar.add(optionMenu);

        JTextField textField = new JTextField(1);
        textField.addActionListener(new ActionListener() {

            /**
             * Get the user input for the number of disks they would like to
             * play with.
             */
            @Override
            public void actionPerformed(ActionEvent evt) {
                String text = textField.getText();
                try {
                    n = Integer.parseInt(text);
                } catch (NumberFormatException e) {
                    n = 0;
                }
                game = new Game(n);
                bestScoringGame = new Game(n);
                bestScoringGame.solve();
                lab.setText("Drag top disk to a valid pole -----> "
                        + "   Number of Moves: " + game.getMoveCount()
                        + "     Least number of steps to win: "
                        + bestScoringGame.lowestScore());
                textField.setText("");
                display.changeGame(game);
                display.repaint();
            }
        });

        JLabel lab2 = new JLabel(
                "Enter number of disks (1-9), then press ENTER");
        lab2.setFont(new Font("Serif", Font.BOLD, 18));
        display.add(lab2);
        display.add(textField);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        frame.setSize(1400, 600);
        display.repaint();
    }

    /**
     * Determine the pole (tower) at which the mouse was dragged or released.
     * @param x position of the mouse
     * @param y postion of the mouse
     * @return the pole that the user clicked on, if no pole was selected,
     *         return -1
     */
    public int poleIndex(int x, int y) {
        if (y > 100 && y < 2 * HanoiDisplay.POLE_HEIGHT
                + 3 * HanoiDisplay.DISK_HEIGHT) {
            if (inPole0(x)) {
                return 0;
            } else if (inPole1(x)) {
                return 1;
            } else if (inPole2(x)) {
                return 2;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    /**
     * Return true if the mouse is at pole 2 (rightmost tower).
     * @param x position of the mouse
     * @return true if mouse is at tower 2
     */
    private boolean inPole2(int x) {
        int pole2 = HanoiDisplay.POLE_TWO_X;
        return (x < 180 + pole2 && x > pole2 - 180);
    }

    /**
     * Return true if the mouse is at pole 1 (middle tower).
     * @param x position of the mouse
     * @return true if mouse is at tower 1
     */
    private boolean inPole1(int x) {
        int pole1 = HanoiDisplay.POLE_ONE_X;
        return (x < 180 + pole1 && x > pole1 - 180);
    }

    /**
     * Return true if the mouse is at pole 0 (leftmost tower).
     * @param x position of the mouse
     * @return true if mouse is at tower 0
     */
    private boolean inPole0(int x) {
        int pole0 = HanoiDisplay.POLE_ZERO_X;
        return (x < 180 + pole0 && x > pole0 - 180);
    }

    /**
     * Nested class that deals with mouse functionality.
     */
    class MouseObject extends MouseAdapter {

        /** Action in response to mouse-clicking event EVENT. */
        @Override
        public synchronized void mouseClicked(MouseEvent event) {
            int x = event.getX(), y = event.getY();
            _clicked.setLocation(x, y);
        }

        /**
         * Once the mouse is released, drop the disk if the move was valid.
         * Update the state of the game, display the updated game.
         */
        @Override
        public synchronized void mouseReleased(MouseEvent event) {
            int ix = _initialDrag.x, iy = _initialDrag.y;
            int dx, dy;
            if (_dragPoint != null) {
                dx = _dragPoint.x;
                dy = _dragPoint.y;
                int start = poleIndex(ix, iy);
                int end = poleIndex(dx, dy);
                if (movingPiece >= 0) {
                    game.push(start, movingPiece);
                    game.move(start, end);
                    lab.setText("Drag top disk to a valid pole -----> "
                            + "   Number of Moves: " + game.getMoveCount()
                            + "     Least number of steps to win: "
                            + bestScoringGame.lowestScore());
                }
                display.repaint();
                pieceStart = -1;
            }
            _dragPoint = null;
            display.repaint();
            if (game.isSolved()) {
                lab.setFont(new Font("Serif", Font.BOLD, 26));
                lab.setText("Great Job! You finished in " + game.getMoveCount()
                        + " steps!  To play again, go to "
                        + "'Options' -> 'Reset Game'");
            }
        }

        /**
         * Constantly re-draw the disk as the user drags it, if the user begins
         * dragging at a valid point (tower).
         */
        @Override
        public synchronized void mouseDragged(MouseEvent event) {
            int x = event.getX(), y = event.getY();
            if (_dragPoint == null) {
                _dragPoint = new Point(x, y);
                _initialDrag.setLocation(x, y);
                pieceStart = poleIndex(_initialDrag.x, _initialDrag.y);
                if (pieceStart >= 0) {
                    movingPiece = game.getTopDisk(pieceStart);
                }
            } else {
                _dragPoint.move(event.getX(), event.getY());
                if (pieceStart >= 0) {
                    game.setMovingTrue();
                    game.setMovingLocation(movingPiece, event.getX(),
                            event.getY());
                }
            }
            display.repaint();
        }
    }

    /**
     * Nested class that resets the GUI.
     */
    class ResetListener implements ActionListener {

        /**
         * Create a new GUI with the initial state of the game.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            GUI g = new GUI(n);
            g.go();
        }

    }

    /** Frame for the GUI. */
    private JFrame frame;

    /** Label that prints useful information for the game. */
    private JLabel lab;

    /** MenuBar for the GUI. */
    private JMenuBar menuBar;
    /** Option menu. */
    private JMenu optionMenu;
    /** Reset menu item for the game. */
    private JMenuItem resetGameItem;

    /** Display for this GUI. */
    private final HanoiDisplay display;

    /** The current game being played. */
    private Game game;
    /** The temporary game which tells the user the best possible score. */
    private Game bestScoringGame;

    /** Number of disks to play with. */
    private int n;

    /** Drag point. */
    private Point _dragPoint;
    /** Clicked point. */
    private Point _clicked;
    /** Initial drag point. */
    private Point _initialDrag;

    /** Piece(disk) that is moving. */
    private int movingPiece;
    /** Where the moving piece (disk) started. */
    private int pieceStart;

}
