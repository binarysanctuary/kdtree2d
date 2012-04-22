package com.binarysanctuary.kdtree;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KDTree {

    // prevent instantiation
    private KDTree() {}

    private KDTreeNode root;

    public static KDTree build(List<? extends Point> points) {
        KDTree tree = new KDTree();
        tree.root = build(points, 0);
        return tree;
    }

    private static KDTreeNode build(List<? extends Point> points, int depth) {
        if (points.isEmpty()) return null;

        final int axis = depth % 2;

        Collections.sort(points, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                double coord1 = p1.getCoords()[axis];
                double coord2 = p2.getCoords()[axis];

                return Double.compare(coord1, coord2);
            }
        });

        int index = points.size() / 2;

        KDTreeNode leftChild = build(points.subList(0, index), depth + 1);
        KDTreeNode rightChild = build(points.subList(index + 1, points.size()), depth + 1);

        Point point = points.get(index);
        return new KDTreeNode(point, axis, leftChild, rightChild);
    }

    @SuppressWarnings({"unchecked"})
    public <T extends Point> T findNearest(Point point) {
        return (T) findNearest(point, 1).get(0);
    }

    public  List<? extends Point> findNearest(Point point, int amount) {
        return root.findNearest(point, amount);
    }

    @SuppressWarnings({"unchecked"})
    public <T extends Point> T getRootPoint() {
        return (T) root.getPoint();
    }
}
