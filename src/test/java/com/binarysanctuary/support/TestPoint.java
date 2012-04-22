package com.binarysanctuary.support;

import com.binarysanctuary.kdtree.Point;

public class TestPoint implements Point {
    double lon;
    double lat;

    public TestPoint(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    @Override
    public double[] getCoords() {
        return new double[] { lon, lat };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestPoint testPoint = (TestPoint) o;

        if (Double.compare(testPoint.lat, lat) != 0) return false;
        if (Double.compare(testPoint.lon, lon) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = lon != +0.0d ? Double.doubleToLongBits(lon) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = lat != +0.0d ? Double.doubleToLongBits(lat) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "[" + lon +
                ", " + lat +
                ']';
    }
}
