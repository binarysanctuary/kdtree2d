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

# License

The MIT License (MIT)

Copyright (c) 2015 Michał Orman

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
