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

import static java.lang.System.getProperty;
import static java.util.Collections.nCopies;
import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.List;

public class TreeStructureVisitor implements TreeVisitor {
    private final List<String> lines = new ArrayList<>();

    private int depth;

    @Override public Void visit(Empty empty) {
        addLine("Empty");

        --depth;
        return null;
    }

    @Override public Void visit(Leaf leaf) {
        addLine("Leaf");

        --depth;
        return null;
    }

    @Override public Void visit(Node node) {
        addLine("Node");

        ++depth;
        node.left().accept(this);

        ++depth;
        node.right().accept(this);

        --depth;
        return null;
    }

    @Override public String toString() {
        return lines.stream().collect(joining(getProperty("line.separator")));
    }

    private void addLine(String label) {
        lines.add(repeat(" ", depth * 2) + label + ':' + depth);
    }

    private static String repeat(String s, int times) {
        return String.join("", nCopies(times, s));
    }
}
