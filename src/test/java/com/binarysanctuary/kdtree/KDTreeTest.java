package com.binarysanctuary.kdtree;

import com.binarysanctuary.support.TestPoint;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class KDTreeTest {
    private static final List<TestPoint> points = Arrays.asList(
            $(0, 0), $(3, 9), $(3, 8), $(1, 2), $(4, 4),
            $(10, 0), $(6, 1), $(9, 4), $(9, 8), $(4.1, 6.4)
    );

    @DataProvider
    public Object[][] itShouldFindNearestPoint() {
        return new Object[][] {
                { points, $(4, 4), 1 },
                { points, $(4.1, 6.4), 0.5 },
                { points, $(4.1, 6.4), 0.5 },
                { points, $(9, 8), 1.99999 },
                { points, $(9, 4), 0.99999 },
        };
    }

    @Test(dataProvider = "itShouldFindNearestPoint")
    public void itShouldFindNearestPoint(List<? extends Point> points, Point expectedPoint, double delta) {
        // when
        KDTree tree = KDTree.build(points);

        // then
        double[] coords = expectedPoint.getCoords();
        assertEquals(tree.findNearest($(coords[0], coords[1] - delta)), expectedPoint);
        assertEquals(tree.findNearest($(coords[0] + delta, coords[1] - delta)), expectedPoint);
        assertEquals(tree.findNearest($(coords[0] + delta, coords[1])), expectedPoint);
        assertEquals(tree.findNearest($(coords[0] + delta, coords[1] + delta)), expectedPoint);
        assertEquals(tree.findNearest($(coords[0], coords[1] + delta)), expectedPoint);
        assertEquals(tree.findNearest($(coords[0] - delta, coords[1] + delta)), expectedPoint);
        assertEquals(tree.findNearest($(coords[0] - delta, coords[1])), expectedPoint);
        assertEquals(tree.findNearest($(coords[0] - delta, coords[1] + delta)), expectedPoint);
    }

    @DataProvider
    public Object[][] itShouldFindNearestPoints() {
        return new Object[][] {
                { points, $(6, 4), Arrays.asList($(4, 4)) },
                { points, $(6, 4), Arrays.asList($(4, 4), $(6, 1)) },
                { points, $(6, 4), Arrays.asList($(4, 4), $(6, 1), $(9, 4)) },
                { points, $(6, 4), Arrays.asList($(4, 4), $(6, 1), $(9, 4), $(4.1, 6.4)) },
        };
    }

    @Test(dataProvider = "itShouldFindNearestPoints")
    public void itShouldFindNearestPoints(List<? extends Point> points, Point point, List<? extends Point> expectedPoints) {
        // when
        KDTree tree = KDTree.build(points);
        List<? extends Point> result = tree.findNearest(point, expectedPoints.size());

        // then
        assertEquals(result.size(), expectedPoints.size());
        for (int i = 0; i < expectedPoints.size(); i++) {
            assertEquals(result.get(i), expectedPoints.get(i));
        }
    }


    private static TestPoint $(double lon, double lat) {
        return new TestPoint(lon, lat);
    }

}
