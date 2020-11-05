package kockpit.navball;

import org.javatuples.Quartet;
import org.javatuples.Triplet;
import org.jblas.DoubleMatrix;

public class Quaternion {
    public static Quartet<Double, Double, Double, Double> multiply(Quartet<Double, Double, Double, Double> a, Quartet<Double, Double, Double, Double> b) {
        final double q1x = a.getValue0(); //x
        final double q1y = a.getValue1(); //y
        final double q1z = a.getValue2(); //z
        final double q1w = a.getValue3(); //w

        final double q2x = b.getValue0();
        final double q2y = b.getValue1();
        final double q2z = b.getValue2();
        final double q2w = b.getValue3();

        final double w = q1w * q2w - q1x * q2x - q1y * q2y - q1z * q2z;
        final double x = q1x * q2w + q1y * q2z - q1z * q2y + q1w * q2x;
        final double y = q1w * q2y + q1z * q2x + q1y * q2w - q1x * q2z;
        final double z = q1x * q2y - q1y * q2x + q1z * q2w + q1w * q2z;

        return new Quartet<>(x, y, z, w);
    }

    public static Quartet<Double, Double, Double, Double> inverse(Quartet<Double, Double, Double, Double> a) {
        final double x = -a.getValue0();
        final double y = -a.getValue1();
        final double z = -a.getValue2();
        final double w = a.getValue3();
        return new Quartet<>(x, y, z, w);
    }

    public static Quartet<Double, Double, Double, Double> fromVect(Triplet<Double, Double, Double> v) {
        return new Quartet<>(v.getValue0(), v.getValue1(), v.getValue2(), 0d);
    }

    public static Quartet<Double, Double, Double, Double> fromVect(DoubleMatrix v) {
        return new Quartet<>(v.get(0), v.get(1), v.get(2), 0d);
    }

    public static DoubleMatrix rotateVect(DoubleMatrix v, Quartet<Double, Double, Double, Double> r) {
        var qv = fromVect(v);
        var ri = inverse(r);
        var res = multiply(multiply(ri, qv), r);
        return new DoubleMatrix(3, 1, res.getValue0(), res.getValue1(), res.getValue2());
    }
}
