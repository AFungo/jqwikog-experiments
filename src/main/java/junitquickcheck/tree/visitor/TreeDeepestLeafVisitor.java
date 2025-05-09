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

package junitquickcheck.tree.visitor;

import junitquickcheck.tree.Empty;
import junitquickcheck.tree.Leaf;
import junitquickcheck.tree.Node;
import junitquickcheck.tree.TreeVisitor;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;

public class TreeDeepestLeafVisitor implements TreeVisitor {
    @Override public Map.Entry<String, Integer> visit(Empty empty) {
        return new SimpleImmutableEntry<>("", 0);
    }

    @Override public Map.Entry<String, Integer> visit(Leaf leaf) {
        return new SimpleImmutableEntry<>(leaf.value(), 0);
    }

    @Override public Map.Entry<String, Integer> visit(Node node) {
        @SuppressWarnings("unchecked")
        Map.Entry<String, Integer> leftResult =
            (Map.Entry<String, Integer>) node.left().accept(this);

        @SuppressWarnings("unchecked")
        Map.Entry<String, Integer> rightResult =
            (Map.Entry<String, Integer>) node.right().accept(this);

        int leftDepth = leftResult.getValue();
        int rightDepth = rightResult.getValue();
        return leftDepth >= rightDepth
            ? new SimpleImmutableEntry<>(leftResult.getKey(), 1 + leftDepth)
            : new SimpleImmutableEntry<>(rightResult.getKey(), 1 + rightDepth);
    }
}
