package snepsui.Interface;

import java.awt.Dimension;

import sneps.Network;
import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class VisualizeNetworks extends javax.swing.JPanel {
	Graph<Number,Number> graph;
    AbstractLayout<Number,Number> layout;
    VisualizationViewer<Number,Number> vv;
    private Network network;
	
	public VisualizeNetworks() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			//drawNetwork();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	private void drawNetwork() {
//		Hashtable<String, Node> nodes = network.getNodes();
//		String nodeString = ""; 
//		Set<String> set = nodes.keySet();
//
//	    Iterator<String> itr1 = set.iterator();
//	    while (itr1.hasNext()) {
//	    	nodeString = itr1.next();
//	    	Node node = nodes.get(nodeString);
//	    	String nodeName = node.getIdentifier();
//	    	//Create a custom vertex that takes the name of the vertex as a label
//	    	//graph.addVertex(nodeName);
//	    }
//	    
//	    Iterator<String> itr2 = set.iterator();
//	    while (itr2.hasNext()) {
//	    	nodeString = itr2.next();
//	    	Node node = nodes.get(nodeString);
//	    	UpCableSet upCableSet = node.getUpCableSet();
//	    	//Create the arcs
//	    }
//	}
}
