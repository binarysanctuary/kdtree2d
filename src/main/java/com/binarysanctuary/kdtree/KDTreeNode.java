package com.binarysanctuary.kdtree;

import java.util.ArrayList;
import java.util.List;

import static com.binarysanctuary.commons.coreext.Array.rshift;
import static java.lang.Math.pow;

public class KDTreeNode {
    /**
     * Axis for which the node was created.
     */
    private int axis;
    private KDTreeNode leftChild;
    private KDTreeNode rightChild;
    /**
     * Point for which node was created.
     */
    private Point point;

    private Double distance;

    public KDTreeNode(Point point, int axis, KDTreeNode leftChild, KDTreeNode rightChild) {
        this.point = point;
        this.axis = axis;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    private KDTreeNode(KDTreeNode node, Double distance) {
        this(node.point, node.axis, node.leftChild, node.rightChild);
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "KDTreeNode{" +
                "leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                ", point=" + point +
                '}';
    }

    /**
     * Returns the coordinates of point for which node was created.
     *
     * @return The point coordinates.
     */
    public double[] getPointCoords() {
        return point.getCoords();
    }

    public List<Point> findNearest(Point point, int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount of points to return must not be negative, but (" + amount + ") was provided.");

        KDTreeNode[] nodes = findNearestNodes(point, amount);
        List<Point> points = new ArrayList<>();

        for (KDTreeNode node : nodes) {
            if (node != null) points.add(node.point);
        }

        return points;
    }

    private KDTreeNode[] findNearestNodes(Point point, int amount) {
        if (liesOnLeftPlane(point)) {
            return findNearestNodes(point, amount, leftChild, rightChild);
        }
        return findNearestNodes(point, amount, rightChild, leftChild);
    }

    private KDTreeNode[] findNearestNodes(Point point, int amount, KDTreeNode currentChild, KDTreeNode otherChild) {
        KDTreeNode[] bestNodes = null;
        KDTreeNode currentNode = new KDTreeNode(this, distance(point));

        if (currentChild != null) {
            bestNodes = currentChild.findNearestNodes(point, amount);
            insertNode(bestNodes, currentNode);
        } else {
            bestNodes = new KDTreeNode[amount];
            bestNodes[0] = currentNode;
        }

        if (otherChild != null) {
            KDTreeNode[] otherBestNodes = null;
            for (KDTreeNode bestNode : bestNodes) {
                // Check if on the other plane of the node may be nodes that lie closer
                // than current best nodes. If do search the other plane.
                if (bestNode != null && pow(getCoordOnAxist() - point.getCoords()[axis], 2) < bestNode.distance) {
                    otherBestNodes = otherChild.findNearestNodes(point, amount);
                    break;
                }
            }
            if (otherBestNodes != null) merge(bestNodes, otherBestNodes);
        }

        return bestNodes;
    }

    private void insertNode(KDTreeNode[] nodes, KDTreeNode node) {
        if (node != null) {
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i] == null || nodes[i].distance > node.distance) {
                    rshift(nodes, i);
                    nodes[i] = node;
                    break;
                }
            }
        }
    }

    private void merge(KDTreeNode[] mergeTo, KDTreeNode[] mergeFrom) {
        for (KDTreeNode node : mergeFrom) {
            insertNode(mergeTo, node);
        }
    }

    private double distance(Point point) {
        // intentionally skipped square root
        return pow(getPointCoords()[0] - point.getCoords()[0], 2) + pow(getPointCoords()[1] - point.getCoords()[1], 2);
    }


    private boolean liesOnLeftPlane(Point point) {
        return point.getCoords()[axis] <= getCoordOnAxist();
    }

    private double getCoordOnAxist() {
        return point.getCoords()[axis];
    }

    public Point getPoint() {
        return point;
    }
}
