/*
 * Copyright (C) 2012-2023 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package plazma.kernel.lib.geom;

/**
 * 
 * @author ohapon
 *
 */
public class GeomLib {

    // Functions:
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 1.1
    //
    // - hypot(int x, int y): int                               - 2D: sqrt(x^2 + y^2)
    // - hypot(float x, float y): float                         - 2D: sqrt(x^2 + y^2)
    // - hypot(double x, double y): double                      - 2D: sqrt(x^2 + y^2)
    //
    // - hypot(int x, int y, int z): int                        - 3D: sqrt(x^2 + y^2 + z^2)
    // - hypot(float x, float y, float z): float                - 3D: sqrt(x^2 + y^2 + z^2)
    // - hypot(double x, double y, double z): double            - 3D: sqrt(x^2 + y^2 + z^2)
    //
    // - dot(int x, int y): int                                 - 2D: x^2 + y^2
    // - dot(float x, float y): float                           - 2D: x^2 + y^2
    // - dot(double x, double y): double                        - 2D: x^2 + y^2
    //
    // - dot(int x, int y, int z): int                          - 3D: x^2 + y^2 + z^2
    // - dot(float x, float y, float z): float                  - 3D: x^2 + y^2 + z^2
    // - dot(double x, double y, double z): double              - 3D: x^2 + y^2 + z^2
    //
    // 1.2
    // 
    // - distance(x1, x2)					- 1D: distance point(x1) <-> point(x2)
    // - distance(x1, y1, x2, y2)				- 2D: distance point(x1, y1) <-> point(x2, y2)
    // - distance(x1, y1, z1, x2, y2, z2)			- 3D: distance point(x1, y1, z1) <-> point(x2, y2, z2)
    //
    // - contains(xr, yr, wr, hr, x, y)				- 2D: point(x, y) in rectangle(xr, yr, wr, wh) [ALIAS]
    // - contains(x1, y1, w1, h1, x2, y2, w2, h2)		- 2D: rectangle(x2, y2, w2, h2) in rectangle(x1, y1, w1, h1) [ALIAS]
    //
    // - containsInRectangle(xr, yr, wr, hr, x, y)		- 2D: point(x, y) in rectangle(xr, yr, wr, wh)
    // - containsInRectangle(x1, y1, w1, h1, x2, y2, w2, h2)	- 2D: rectangle(x2, y2, w2, h2) in rectangle(x1, y1, w1, h1)
    //
    // - containsInCircle(xc, yc, r, x, y)			- 2D: point(x, y) in circle(xc, yc, r)
    
    
    // java.awt.geom.Rectangle2D
    // javafx.geometry.Rectangle2D
    
    private GeomLib() {
    }

    // hypot 2D

    public static int hypot(int x, int y) {
        return (int) _hypot(x, y);
    }

    public static float hypot(float x, float y) {
        return (float) _hypot(x, y);
    }

    public static double hypot(double x, double y) {
        return _hypot(x, y);
    }

    //

    private static double _hypot(double x, double y) {
        return Math.hypot(x, y);
    }

    // hypot 3D
    // Java: Not implemented

    public static int hypot(int x, int y, int z) {
        return (int) _hypot(x, y, z);
    }

    public static float hypot(float x, float y, float z) {
        return (float) _hypot(x, y, z);
    }

    public static double hypot(double x, double y, double z) {
        return _hypot(x, y, z);
    }

    //

    private static double _hypot(double x, double y, double z) {
        return Math.sqrt(dot(x, y, z));
    }

    // dot 2D

    public static int dot(int x, int y) {
        // x * x + y * y
        return (int) _dot(x, y);
    }

    public static float dot(float x, float y) {
        // x * x + y * y
        return (float) _dot(x, y);
    }

    public static double dot(double x, double y) {
        // x * x + y * y
        return _dot(x, y);
    }

    //

    private static double _dot(double x, double y) {
        // x * x + y * y
        return Math.pow(x, 2) + Math.pow(y, 2);
    }

    // dot 3D

    public static int dot(int x, int y, int z) {
        // x * x + y * y + z * z
        return (int) _dot(x, y, z);
    }

    public static float dot(float x, float y, float z) {
        // x * x + y * y + z * z
        return (float) _dot(x, y, z);
    }

    public static double dot(double x, double y, double z) {
        // x * x + y * y + z * z
        return _dot(x, y, z);
    }

    //

    private static double _dot(double x, double y, double z) {
        // x * x + y * y + z * z
        return Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);
    }

    // distance 1D

    public static int distance(int x1, int x2) {
        return x2 - x1;
    }

    public static float distance(float x1, float x2) {
        return x2 - x1;
    }

    public static double distance(double x1, double x2) {
        return x2 - x1;
    }

    // distance 2D

    public static int distance(int x1, int y1, int x2, int y2) {
        return hypot(x2 - x1, y2 - y1); // sqrt((x2 - x1) ^ 2 + (y2 - y1) ^ 2)
    }

    public static float distance(float x1, float y1, float x2, float y2) {
        return hypot(x2 - x1, y2 - y1); // sqrt((x2 - x1) ^ 2 + (y2 - y1) ^ 2)
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return hypot(x2 - x1, y2 - y1); // sqrt((x2 - x1) ^ 2 + (y2 - y1) ^ 2)
    }

    // distance 3D

    public static int distance(int x1, int y1, int z1, int x2, int y2, int z2) {
        return hypot(x2 - x1, y2 - y1, z2 - z1); // sqrt((x2 - x1) ^ 2 + (y2 - y1) ^ 2 + (z2 - z1) ^ 2)
    }

    public static float distance(float x1, float y1, float z1, float x2, float y2, float z2) {
        return hypot(x2 - x1, y2 - y1, z2 - z1); // sqrt((x2 - x1) ^ 2 + (y2 - y1) ^ 2 + (z2 - z1) ^ 2)
    }

    public static double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
        return hypot(x2 - x1, y2 - y1, z2 - z1); // sqrt((x2 - x1) ^ 2 + (y2 - y1) ^ 2 + (z2 - z1) ^ 2)
    }

    ////

    /**
     * Tests if Point(x, y) contains in Rectangle(rx, ry, rw, rh)
     * 
     * @param xr - Rectangle X
     * @param yr - Rectangle Y
     * @param wr - Rectangle width
     * @param hr - Rectangle height
     * @param x  - Test X
     * @param y  - Test Y
     * 
     * @return
     */
    public static boolean contains(double xr, double yr, double wr, double hr, double x, double y) {
//	if (wr <= 0 || hr <= 0) {
//	    return false;
//	}
//	return _check(xr, xr + wr, xr, yr + hr, x, y); 

        return containsInRectangle(xr, yr, wr, hr, x, y);
    }

    /**
     * Tests if Rectangle(x2, y2, w2, h2) contains in Rectangle(x1, y1, w1, h1)
     * 
     * @param x1 - Rectangle1 X
     * @param y1 - Rectangle1 Y
     * @param w1 - Rectangle1 width
     * @param h1 - Rectangle1 height
     * @param x2 - Rectangle2 X
     * @param y2 - Rectangle2 Y
     * @param w2 - Rectangle2 width
     * @param h2 - Rectangle2 height
     * 
     * @return
     */
    public static boolean contains(double x1, double y1, double w1, double h1, double x2, double y2, double w2,
            double h2) {
        // return _check(xr, xr + wr, xr, yr + hr, x, y);
        // return x2 >= x1 && y2 >= y1 && x2 + w2 <= x1 + w1 && y2 + h2 <= y1 + h1;
        // return _check(x1, y1, x1 + w1, y1 + h1, x2, y2, x2 + w2, y2 + h2);

        return containsInRectangle(x1, y1, w1, h1, x2, y2, w2, h2);
    }

    /**
     * Tests if Point(x, y) contains in Rectangle(rx, ry, rw, rh)
     * 
     * @param xr - Rectangle X
     * @param yr - Rectangle Y
     * @param wr - Rectangle width
     * @param hr - Rectangle height
     * @param x  - Test X
     * @param y  - Test Y
     * 
     * @return
     */
    public static boolean containsInRectangle(double xr, double yr, double wr, double hr, double x, double y) {
        if (wr <= 0 || hr <= 0) {
            return false;
        }
        return _check(xr, xr + wr, xr, yr + hr, x, y);
    }

    /**
     * Tests if Rectangle(x2, y2, w2, h2) contains in Rectangle(x1, y1, w1, h1)
     * 
     * @param x1 - Rectangle1 X
     * @param y1 - Rectangle1 Y
     * @param w1 - Rectangle1 width
     * @param h1 - Rectangle1 height
     * @param x2 - Rectangle2 X
     * @param y2 - Rectangle2 Y
     * @param w2 - Rectangle2 width
     * @param h2 - Rectangle2 height
     * 
     * @return
     */
    public static boolean containsInRectangle(double x1, double y1, double w1, double h1, double x2, double y2,
            double w2, double h2) {
        // return _check(xr, xr + wr, xr, yr + hr, x, y);
        // return x2 >= x1 && y2 >= y1 && x2 + w2 <= x1 + w1 && y2 + h2 <= y1 + h1;

        return _check(x1, y1, x1 + w1, y1 + h1, x2, y2, x2 + w2, y2 + h2);
    }

    /**
     * Tests if Point(x, y) contains in Circle(rx, ry, rw, rh)
     * 
     * @param xc - Center X
     * @param yc - Center Y
     * @param r  - Radius
     * @param x  - Test X
     * @param y  - Test Y
     * @return
     */
    public static boolean containsInCircle(double xc, double yc, double r, double x, double y) {
        if (r <= 0) {
            return false;
        }
        double distance = distance(xc, yc, x, y);
        return distance <= r;
    }

    ////

    private static boolean _check(double x1, double y1, double x2, double y2, double x, double y) {
        // return (x >= x1 && y >= y1 && x <= x2 && y <= y2);
        return _check(x1, y1, x2, y2, x, y, x, y);
    }

    private static boolean _check(double ax1, double ay1, double ax2, double ay2, double bx1, double by1, double bx2,
            double by2) {
        return (bx1 >= ax1 && by1 >= ay1 && bx2 <= ax2 && by2 <= ay2);
    }
    
}
