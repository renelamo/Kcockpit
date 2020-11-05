package kockpit.navball;

import java.awt.*;
import java.util.LinkedList;

import org.javatuples.Quartet;
import org.jblas.*;

public class NavBall extends Canvas {
    public static final int HEIGHT = 132;
    public static final int WIDTH = 162;
    public static final int RADIUS = HEIGHT / 2;
    public static final int CENTER_X = WIDTH / 2;
    public static final int CENTER_Y = HEIGHT / 2;

    public static final int NB_POINTS = 20;
    public static final double STEP = 2d / (NB_POINTS - 1);

    final DoubleMatrix up = new DoubleMatrix(3, 1, 1, 0, 0);
    final DoubleMatrix north = new DoubleMatrix(3, 1, 0, 1, 0);
    final DoubleMatrix east = new DoubleMatrix(3, 1, 0, 0, 1);

    DoubleMatrix translation2 = new DoubleMatrix(2, 1, CENTER_X, CENTER_Y);
    DoubleMatrix translation3 = new DoubleMatrix(3, 1, CENTER_X, CENTER_Y, 0);

    private double pitch;
    private double roll = Math.PI / 4;
    private Quartet<Double, Double, Double, Double> rotation = new Quartet<>(0d, 0d, 0d, 1d);

    private final Circle equator;
    private final Circle mer1;
    private final Circle mer2;

    private long time;

    // In degrees
    public void setPitch(double pitch) {// between -90 and 90, angle with horizon
        this.pitch = pitch * Math.PI / 180;
    }

    public void setRoll(double roll) {
        this.roll = roll * Math.PI / 180;
    }

    public void setRotation(Quartet<Double, Double, Double, Double> rotation) {
        this.rotation = rotation;
    }

    public NavBall() {
        setBackground(Color.BLACK);
        setSize(WIDTH, HEIGHT);
        equator = new Circle(1d, DoubleMatrix.zeros(3, 1), DoubleMatrix.concatHorizontally(up, east));
        mer1 = new Circle(1d, DoubleMatrix.zeros(3, 1), DoubleMatrix.concatHorizontally(up, north));
        mer2 = new Circle(1d, DoubleMatrix.zeros(3, 1), DoubleMatrix.concatHorizontally(north, east));
        time = System.currentTimeMillis();
        createBufferStrategy(2);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(50, 100, 255));
        g.fillOval((WIDTH - HEIGHT) / 2, 0, 2 * RADIUS, 2 * RADIUS);
    }

    private double y(double x, double coeff) {
        return Math.sqrt(1 - x * x) * coeff;
    }

    @Override
    public void update(Graphics g) {
        //super.update(g);
        g = getBufferStrategy().getDrawGraphics();
        DoubleMatrix rotation = new DoubleMatrix(2, 2,
                Math.cos(roll), Math.sin(roll), -Math.sin(roll), Math.cos(roll));

        var points = new DoubleMatrix[2 * NB_POINTS];

        double x = -1;
        double sinPitch = Math.sin(pitch);
        for (int i = 0; i < NB_POINTS; ++i) {
            points[i] = new DoubleMatrix(1, 2, x, y(x, sinPitch));
            points[2 * NB_POINTS - 1 - i] = new DoubleMatrix(1, 2, x, y(x, 1));
            x += STEP;
        }

        Polygon poly = new Polygon();
        for (DoubleMatrix p : points) {
            p.mmuli(rotation);
            p.muli(RADIUS);
            p.addi(translation2);
            poly.addPoint((int) p.get(0), (int) p.get(1));
        }

        var drawableEquator = equator.getRotation(this.rotation).getVisiblePoints();
        var drawableMer1 = mer1.getRotation(this.rotation).getVisiblePoints();
        var drawableMer2 = mer2.getRotation(this.rotation).getVisiblePoints();
        for (var p : drawableEquator) {
            p.muli(RADIUS);
            p.addi(translation2);
        }
        for (var p : drawableMer1) {
            p.muli(RADIUS);
            p.addi(translation2);
        }
        for (var p : drawableMer2) {
            p.muli(RADIUS);
            p.addi(translation2);
        }

        //super.update(g);
        paint(g);

        g.setColor(new Color(80, 50, 50));
        g.fillPolygon(poly);

        g.setColor(Color.white);
        drawCircle(g, drawableEquator);
        drawCircle(g, drawableMer2);
        g.setColor(Color.orange);
        drawCircle(g, drawableMer1);

        getBufferStrategy().show();
/*
        System.out.println(1000d/(System.currentTimeMillis()-time));
        time = System.currentTimeMillis();

// */
    }

    private void drawCircle(Graphics g, LinkedList<DoubleMatrix> drawablePoints) {
        for (int i = 1; i < drawablePoints.size(); ++i) {
            var p = drawablePoints.get(i - 1);
            var q = drawablePoints.get(i);
            g.drawLine((int) p.get(0), (int) p.get(1), (int) q.get(0), (int) q.get(1));
        }
    }
}
