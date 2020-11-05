package kockpit.navball;

import org.javatuples.Quartet;
import org.jblas.DoubleMatrix;

import java.util.LinkedList;

public class Circle {
    double radius;
    DoubleMatrix center;
    DoubleMatrix plane;
    LinkedList<DoubleMatrix> points;

    public final int NBPoints = 10;

    private Circle() {
        points = new LinkedList<>();
    }

    public Circle(double radius, DoubleMatrix center, DoubleMatrix plane) {
        if (radius < 0) {
            throw new IllegalArgumentException();
        }
        this.radius = radius;
        if (center.rows != 3 || center.columns != 1) throw new IllegalArgumentException();
        this.center = center;
        if (plane.columns != 2 || plane.rows != 3) throw new IllegalArgumentException();
        this.plane = plane;
        points = new LinkedList<>();

        for (double angle = 0; angle < 2 * Math.PI; angle += 2 * Math.PI / NBPoints) {
            points.add(center.add(plane.getColumn(0).mul(radius * Math.cos(angle))).add(plane.getColumn(1).mul(radius * Math.sin(angle))));
        }
    }

    public void rotate(DoubleMatrix rotation) {
        for (int i = 0; i < points.size(); i++) {
            points.get(i).mmuli(rotation);
            points.set(i, points.get(i).transpose());
        }
    }

    public Circle getRotation(Quartet<Double, Double, Double, Double> rotation) {
        var out = new Circle();
        for (int i = 0; i < points.size(); i++) {
            out.points.add(Quaternion.rotateVect(points.get(i), rotation));
        }
        return out;
    }

    LinkedList<DoubleMatrix> getVisiblePoints() {
        var out = new LinkedList<DoubleMatrix>();
        boolean addFlag = false;
        for (int i = 0; i < 2 * points.size(); i++) {
            var point = points.get(i % points.size());

            if (addFlag) {
                if (point.get(1) >= 0) {
                    out.add(new DoubleMatrix(2, 1, point.get(0), point.get(2)));
                } else {
                    return out;
                }
            }
            addFlag |= point.get(1) < 0 && points.get((i + 1) % points.size()).get(1) >= 0;
        }
        return out;
    }
}
