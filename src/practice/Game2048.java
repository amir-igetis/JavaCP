package practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Game2048 extends JPanel implements KeyListener {

    static final int ROWS = 4, COLS = 4;
    static final int TILE_SIZE = 100;
    static final int TILE_MARGIN = 16;
    static final int FONT_SIZE = 36;
    static final int WIDTH = TILE_SIZE * COLS + TILE_MARGIN * 5;
    static final int HEIGHT = TILE_SIZE * ROWS + TILE_MARGIN * 5;

    Map<Point, Integer> tiles;
    Random random = new Random();

    public Game2048() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color(205, 192, 180));
        this.setFocusable(true);
        this.addKeyListener(this);
        initGame();
    }

    public void initGame() {
        tiles = new HashMap<>();
        spawnTile();
        spawnTile();
    }

    private void spawnTile() {
        List<Point> empty = new ArrayList<>();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (!tiles.containsKey(new Point(r, c))) {
                    empty.add(new Point(r, c));
                }
            }
        }
        if (!empty.isEmpty()) {
            Point p = empty.get(random.nextInt(empty.size()));
            tiles.put(p, random.nextDouble() < 0.9 ? 2 : 4);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                drawTile(g, r, c);
            }
        }
    }

    private void drawTile(Graphics g, int row, int col) {
        int x = TILE_MARGIN + col * (TILE_SIZE + TILE_MARGIN);
        int y = TILE_MARGIN + row * (TILE_SIZE + TILE_MARGIN);
        int value = tiles.getOrDefault(new Point(row, col), 0);

        // Draw background rectangle
        g.setColor(getTileColor(value));
        g.fillRoundRect(x, y, TILE_SIZE, TILE_SIZE, 15, 15);

        // Draw number
        if (value != 0) {
            g.setColor(new Color(119, 110, 101));
            g.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
            String s = String.valueOf(value);
            FontMetrics fm = g.getFontMetrics();
            int tx = x + (TILE_SIZE - fm.stringWidth(s)) / 2;
            int ty = y + (TILE_SIZE + fm.getAscent()) / 2 - 8;
            g.drawString(s, tx, ty);
        }
    }

    private Color getTileColor(int value) {
        switch (value) {
            case 2:
                return new Color(237, 229, 218);
            case 4:
                return new Color(238, 225, 201);
            case 8:
                return new Color(243, 178, 122);
            case 16:
                return new Color(246, 150, 101);
            case 32:
                return new Color(247, 124, 95);
            case 64:
                return new Color(247, 95, 59);
            case 128:
                return new Color(237, 208, 115);
            case 256:
                return new Color(237, 204, 99);
            case 512:
                return new Color(236, 202, 80);
            case 1024:
                return new Color(255, 180, 70);
            case 2048:
                return new Color(255, 140, 0);
            default:
                return new Color(60, 58, 50);
        }
    }

    private boolean move(Direction dir) {
        boolean moved = false;
        Map<Point, Integer> newTiles = new HashMap<>();

        for (int i = 0; i < Math.max(ROWS, COLS); i++) {
            List<Integer> line = new ArrayList<>();
            for (int j = 0; j < Math.max(ROWS, COLS); j++) {
                Point p = dir.getPoint(i, j);
                if (tiles.containsKey(p)) {
                    line.add(tiles.get(p));
                }
            }

            List<Integer> merged = mergeLine(line);
            for (int j = 0; j < merged.size(); j++) {
                Point p = dir.getPoint(i, j);
                newTiles.put(p, merged.get(j));
            }
            if (merged.size() != line.size())
                moved = true;
            if (!line.equals(merged))
                moved = true;
        }

        if (!tiles.equals(newTiles))
            moved = true;
        tiles = newTiles;
        return moved;
    }

    private List<Integer> mergeLine(List<Integer> list) {
        List<Integer> merged = new ArrayList<>();
        int i = 0;
        while (i < list.size()) {
            int num = list.get(i);
            if (i + 1 < list.size() && list.get(i).equals(list.get(i + 1))) {
                merged.add(num * 2);
                i += 2;
            } else {
                merged.add(num);
                i++;
            }
        }
        return merged;
    }

    private boolean isGameOver() {
        if (tiles.size() < ROWS * COLS)
            return false;
        for (Point p : tiles.keySet()) {
            int val = tiles.get(p);
            for (Direction d : Direction.values()) {
                Point neighbor = d.getNeighbor(p);
                if (tiles.containsKey(neighbor) && tiles.get(neighbor).equals(val)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        boolean moved = false;

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            moved = move(Direction.LEFT);
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            moved = move(Direction.RIGHT);
        else if (e.getKeyCode() == KeyEvent.VK_UP)
            moved = move(Direction.UP);
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            moved = move(Direction.DOWN);

        if (moved) {
            spawnTile();
            repaint();

            if (isGameOver()) {
                JOptionPane.showMessageDialog(this, "Game Over!");
                initGame();
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    enum Direction {
        LEFT, RIGHT, UP, DOWN;

        Point getPoint(int i, int j) {
            switch (this) {
                case LEFT:
                    return new Point(i, j);
                case RIGHT:
                    return new Point(i, COLS - 1 - j);
                case UP:
                    return new Point(j, i);
                case DOWN:
                    return new Point(ROWS - 1 - j, i);
            }
            return null;
        }

        Point getNeighbor(Point p) {
            switch (this) {
                case LEFT:
                    return new Point(p.x, p.y - 1);
                case RIGHT:
                    return new Point(p.x, p.y + 1);
                case UP:
                    return new Point(p.x - 1, p.y);
                case DOWN:
                    return new Point(p.x + 1, p.y);
            }
            return null;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("2048 in Java");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new Game2048());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
