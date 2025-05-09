/*
 The MIT License

 Copyright (c) 2010-2021 Paul R. Holser, Jr.

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package junitquickcheck.geom;


import static junitquickcheck.geom.Point.Orientation.COLLINEAR;
import static junitquickcheck.geom.Point.orientation;

public final class Segment {
    private Point a;
     private Point b;

    public Segment(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public boolean intersects(Segment other) {
        Point.Orientation o1 = orientation(a, b, other.a);
        Point.Orientation o2 = orientation(a, b, other.b);
        Point.Orientation o3 = orientation(other.a, other.b, a);
        Point.Orientation o4 = orientation(other.a, other.b, b);

        // General case
        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        if (o1 == COLLINEAR && other.a.between(a, b))
            return true;

        if (o2 == COLLINEAR && other.b.between(a, b))
            return true;

        if (o3 == COLLINEAR && a.between(other.a, other.b))
            return true;

        return o4 == COLLINEAR && b.between(other.a, other.b);
    }

    @Override public String toString() {
        return String.format("[%s %s]", a, b);
    }
}
