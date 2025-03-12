package experiments.randoopTest;

import jgrapht.*;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import net.jqwik.api.randoop.*;

import org.assertj.core.api.*;
import org.jgrapht.alg.connectivity.*;
import org.jgrapht.alg.interfaces.*;
import org.jgrapht.alg.spanning.*;
import org.jgrapht.graph.*;

import java.util.*;

/*
 *Experimentos extraidos de JQF
 * https://rohan.padhye.org/files/jqf-issta19.pdf
 */
public class JgraphtTest {

	public static boolean graphIsConnected(Object o){
		// if(o == null) return false;
		DefaultDirectedGraph graph = (DefaultDirectedGraph) o;
		return new ConnectivityInspector(graph).isConnected() && graph.vertexSet().size() > 1 && graph.edgeSet().size() > 1;
	}

	//@UseMethods(methods = {"addEdge", "addVertex", "setEdgeSupplier", "setVertexSupplier"})
	@Property
	public void testPrim(@ForAll @AssumeMethod(className =  JgraphtTest.class, methodName = "graphIsConnected")
						 @Deps(classes={MySuplier.class}) @IntRange(max=5)
						 DefaultDirectedGraph<Integer, Integer> graph) {
		Assume.that(graph != null);
		int graphSize = graph.vertexSet().size();

		// Assume.assumeTrue(new ConnectivityInspector(graph).isGraphConnected());

		SpanningTreeAlgorithm.SpanningTree tree = new PrimMinimumSpanningTree<>(graph)
													  .getSpanningTree();

		Set<Object> nodes = new HashSet<>();
		Set<Integer> edges = tree.getEdges();
		for (Integer e : edges) {
			nodes.add(graph.getEdgeSource(e));
			nodes.add(graph.getEdgeTarget(e));
		}

		Assertions.assertThat(graphSize).isEqualTo(nodes.size());
	}

}
