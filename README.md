# kdtree2d

The K-D tree implementation optimized for searching nearest point in 2 dimensional
surface. API allows to search 1 to n points nearest to specified point.

## Usage

Building K-D tree:

    KDTree tree = KDTree.build(points);

As parameter list of objects implementing `com.binarysanctuary.kdtree.Point` interface should be provided.
To search nearest point invoke:

    Point point = tree.findNearest(otherPoint);

or if you wish to find `n` nearest points:

    List<Point> points = tree.findNearest(otherPoint, amount);

Returned points will be ordered starting from one that is closest to given point.

## Building

Checkout code and run:

    gradle jar

## Todo

* Adding and removing point from tree