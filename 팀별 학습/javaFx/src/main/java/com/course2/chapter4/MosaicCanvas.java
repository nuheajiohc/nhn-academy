package com.course2.chapter4;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MosaicCanvas extends Canvas {

    private final int rows;
    private final int columns;
    private final Color defaultColor;
    private Color groutingColor;
    private final boolean alwaysDrawGrouting;
    private boolean use3D = true;
    private static final boolean autopaint = true;
    private final Color[][] grid;
    private final GraphicsContext g;

    public MosaicCanvas() {
        this(42, 42);
    }

    public MosaicCanvas(int rows, int columns) {
        this(rows, columns, 16, 16);
    }

    public MosaicCanvas(int rows, int columns, int preferredBlockWidth, int preferredBlockHeight) {
        this.rows = rows;
        this.columns = columns;
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Rows and Columns must be greater than zero.");
        }
        preferredBlockHeight = Math.max(preferredBlockHeight, 5);
        preferredBlockWidth = Math.max(preferredBlockWidth, 5);
        grid = new Color[rows][columns];
        defaultColor = Color.BLACK;
        groutingColor = Color.GRAY;
        alwaysDrawGrouting = false;
        setWidth(preferredBlockWidth * columns);
        setHeight(preferredBlockHeight * rows);
        g = getGraphicsContext2D();
    }

    public void setUse3D(boolean use3D) {
        this.use3D = use3D;
    }

    public Color getColor(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            return grid[row][col];
        } else {
            return null;
        }
    }

    public double getRed(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns && grid[row][col] != null) {
            return grid[row][col].getRed();
        } else {
            return defaultColor.getRed();
        }
    }

    public void setGroutingColor(Color c) {
        if (c == null || ! c.equals(groutingColor)) {
            groutingColor = c;
            forceRedraw();
        }
    }


    public double getGreen(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns && grid[row][col] != null) {
            return grid[row][col].getGreen();
        } else {
            return defaultColor.getGreen();
        }
    }

    public double getBlue(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < columns && grid[row][col] != null) {
            return grid[row][col].getBlue();
        } else {
            return defaultColor.getBlue();
        }
    }

    public void setColor(int row, int col, Color c) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            grid[row][col] = c;
            drawSquare(row, col);
        }
    }

    public void setColor(int row, int col, double red, double green, double blue) {
        if (row >= 0 && row < rows && col >= 0 && col < columns) {
            grid[row][col] = Color.color(red, green, blue);
            drawSquare(row, col);
        }
    }

    public void fill(Color c) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = c;
            }
        }
        forceRedraw();
    }

    public void fill(double red, double green, double blue) {
        fill(Color.color(red, green, blue));
    }

    public void fillRandomly() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = Color.color(Math.random(), Math.random(), Math.random());
            }
        }
        forceRedraw();
    }


    public final void forceRedraw() {
        drawAllSquares();
    }

    private void drawSquare(int row, int col) {
        if (autopaint) {
            if (Platform.isFxApplicationThread()) {
                drawOneSquare(row, col);
            } else {
                Platform.runLater(() -> drawOneSquare(row, col));
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    private void drawAllSquares() {
        if (Platform.isFxApplicationThread()) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    drawOneSquare(r, c);
                }
            }
        } else {
            Platform.runLater(() -> {
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < columns; c++) {
                        drawOneSquare(r, c);
                    }
                }
            });
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void drawOneSquare(int row, int col) {
        double rowHeight = getHeight() / rows;
        double colWidth = getWidth() / columns;
        int y = (int) Math.round(rowHeight * row);
        int h = Math.max(1, (int) Math.round(rowHeight * (row + 1)) - y);
        int x = (int) Math.round(colWidth * col);
        int w = Math.max(1, (int) Math.round(colWidth * (col + 1)) - x);
        Color c = grid[row][col];
        g.setFill((c == null) ? defaultColor : c);
        if (groutingColor == null || (c == null && !alwaysDrawGrouting)) {
            if (!use3D || c == null) {
                g.fillRect(x, y, w, h);
            } else {
                fill3DRect(c, x, y, w, h);
            }
        } else {
            if (!use3D || c == null) {
                g.fillRect(x + 1, y + 1, w - 2, h - 2);
            } else {
                fill3DRect(c, x + 1, y + 1, w - 2, h - 2);
            }
            g.setStroke(groutingColor);
            g.strokeRect(x + 0.5, y + 0.5, w - 1, h - 1);
        }
    }

    private void fill3DRect(Color color, int x, int y, int width, int height) {
        double h = color.getHue();
        double b = color.getBrightness();
        double s = color.getSaturation();
        if (b > 0.8) {
            b = 0.8;
            g.setFill(Color.hsb(h, s, b));
        } else if (b < 0.2) {
            b = 0.2;
            g.setFill(Color.hsb(h, s, b));
        }
        g.fillRect(x, y, width, height);
        g.setStroke(Color.hsb(h, s, b + 0.2));
        g.strokeLine(x + 0.5, y + 0.5, x + width - 0.5, y + 0.5);
        g.strokeLine(x + 0.5, y + 0.5, x + 0.5, y + height - 0.5);
        g.setStroke(Color.hsb(h, s, b - 0.2));
        g.strokeLine(x + width - 0.5, y + 1.5, x + width - 0.5, y + height - 0.5);
        g.strokeLine(x + 1.5, y + height - 0.5, x + width - 0.5, y + height - 0.5);
    }
}