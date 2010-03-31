package snepsui.Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.functors.ConstantTransformer;

import sneps.CaseFrame;
import sneps.CustomException;
import sneps.Network;
import sneps.Node;
import sneps.Relation;
import sneps.UpCableSet;
import edu.uci.ics.jung.algorithms.layout.BalloonLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.Tree;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalLensGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.layout.LayoutTransition;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import edu.uci.ics.jung.visualization.transform.LensSupport;
import edu.uci.ics.jung.visualization.transform.MutableTransformer;
import edu.uci.ics.jung.visualization.transform.MutableTransformerDecorator;
import edu.uci.ics.jung.visualization.transform.shape.HyperbolicShapeTransformer;
import edu.uci.ics.jung.visualization.transform.shape.ViewLensSupport;
import edu.uci.ics.jung.visualization.util.Animator;

public class VisualizeNetworks extends javax.swing.JPanel {

    /**
     * the graph
     */
	Forest<String,String> graph;

	Factory<DirectedGraph<String, String>> graphFactory = 
		new Factory<DirectedGraph<String,String>>() {

		public DirectedGraph<String, String> create() {
			return new DirectedSparseMultigraph<String,String>();
		}
	};
	
	Factory<Tree<String,String>> treeFactory =
		new Factory<Tree<String,String>> () {

		public Tree<String, String> create() {
			return new DelegateTree<String,String>(graphFactory);
		}
	};

	Factory<String> edgeFactory = new Factory<String>() {
		int i = 0;
		public String create() {
			return "" + i++;
		}};
    
    Factory<String> vertexFactory = new Factory<String>() {
    	int i=0;
		public String create() {
			return "V"+i++;
		}};

    /**
     * the visual component and renderer for the graph
     */
    VisualizationViewer<String, String> vv;
    
    VisualizationServer.Paintable rings;
    
    //String root;
    
    TreeLayout<String, String> layout;
    
    
    BalloonLayout<String,String> radialLayout;
    /**
     * provides a Hyperbolic lens for the view
     */
    LensSupport hyperbolicViewSupport;
    private Network network;

	public VisualizeNetworks(Network network) {
		super();
		this.network = network;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			graph = new DelegateForest<String,String>();
			drawNetwork();
			
			// create a simple graph for the demo
	        
	        layout = new TreeLayout<String,String>(graph);
	        radialLayout = new BalloonLayout<String,String>(graph);
	        radialLayout.setSize(new Dimension(900,900));
	        vv =  new VisualizationViewer<String,String>(layout, new Dimension(600,600));
	        vv.setBackground(Color.white);
	        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());
	        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
	        // add a listener for ToolTips
	        vv.setVertexToolTipTransformer(new ToStringLabeller<String>());
	        vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));
	        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
	        rings = new Rings(radialLayout);
	        
	        //Container content = getContentPane();
	        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
	        this.add(panel);
	        
	        final DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();

	        vv.setGraphMouse(graphMouse);
	        vv.addKeyListener(graphMouse.getModeKeyListener());
	        
	        hyperbolicViewSupport = 
	            new ViewLensSupport<String,String>(vv, new HyperbolicShapeTransformer(vv, 
	            		vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW)), 
	                    new ModalLensGraphMouse());


	        graphMouse.addItemListener(hyperbolicViewSupport.getGraphMouse().getModeListener());

	        JComboBox modeBox = graphMouse.getModeComboBox();
	        modeBox.addItemListener(graphMouse.getModeListener());
	        graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);

	        final ScalingControl scaler = new CrossoverScalingControl();
	        
	        vv.scaleToLayout(scaler);

	        JButton plus = new JButton("+");
	        plus.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                scaler.scale(vv, 1.1f, vv.getCenter());
	            }
	        });
	        JButton minus = new JButton("-");
	        minus.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                scaler.scale(vv, 1/1.1f, vv.getCenter());
	            }
	        });
	        
	        JToggleButton radial = new JToggleButton("Balloon");
	        radial.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED) {

						LayoutTransition<String,String> lt =
							new LayoutTransition<String,String>(vv, layout, radialLayout);
						Animator animator = new Animator(lt);
						animator.start();
						vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).setToIdentity();
						vv.addPreRenderPaintable(rings);
					} else {

						LayoutTransition<String,String> lt =
							new LayoutTransition<String,String>(vv, radialLayout, layout);
						Animator animator = new Animator(lt);
						animator.start();
						vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).setToIdentity();
						vv.removePreRenderPaintable(rings);
					}
					vv.repaint();
				}});
	        final JRadioButton hyperView = new JRadioButton("Hyperbolic View");
	        hyperView.addItemListener(new ItemListener(){
	            public void itemStateChanged(ItemEvent e) {
	                hyperbolicViewSupport.activate(e.getStateChange() == ItemEvent.SELECTED);
	            }
	        });

	        JPanel scaleGrid = new JPanel(new GridLayout(1,0));
	        scaleGrid.setBorder(BorderFactory.createTitledBorder("Zoom"));

	        JPanel controls = new JPanel();
	        scaleGrid.add(plus);
	        scaleGrid.add(minus);
	        controls.add(radial);
	        controls.add(scaleGrid);
	        controls.add(modeBox);
	        controls.add(hyperView);
	        this.add(controls, BorderLayout.SOUTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
class Rings implements VisualizationServer.Paintable {
    	
    	BalloonLayout<String,String> layout;
    	
    	public Rings(BalloonLayout<String,String> layout) {
    		this.layout = layout;
    	}
    	
		public void paint(Graphics g) {
			g.setColor(Color.gray);
		
			Graphics2D g2d = (Graphics2D)g;

			Ellipse2D ellipse = new Ellipse2D.Double();
			for(String v : layout.getGraph().getVertices()) {
				Double radius = layout.getRadii().get(v);
				if(radius == null) continue;
				Point2D p = layout.transform(v);
				ellipse.setFrame(-radius, -radius, 2*radius, 2*radius);
				AffineTransform at = AffineTransform.getTranslateInstance(p.getX(), p.getY());
				Shape shape = at.createTransformedShape(ellipse);
				
				MutableTransformer viewTransformer =
					vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW);
				
				if(viewTransformer instanceof MutableTransformerDecorator) {
					shape = vv.getRenderContext().getMultiLayerTransformer().transform(shape);
				} else {
					shape = vv.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT,shape);
				}

				g2d.draw(shape);
			}
		}

		public boolean useTransform() {
			return true;
		}
    }
	
	private void drawNetwork() {
		Hashtable<String, Node> nodes = network.getNodes();
		String nodeString = ""; 
		Set<String> set = nodes.keySet();

	    Iterator<String> itr1 = set.iterator();
	    while (itr1.hasNext()) {
	    	nodeString = itr1.next();
	    	Node node1 = nodes.get(nodeString);
	    	String nodeName = node1.getIdentifier();
	    	System.out.println(node1.getIdentifier());
	    	System.out.println(nodeName);
	    	graph.addVertex(nodeName);
	    }
	    
	    Iterator<String> itr2 = set.iterator();
	    while (itr2.hasNext()) {
	    	nodeString = itr2.next();
	    	Node node = nodes.get(nodeString);
	    	UpCableSet upCableSet = node.getUpCableSet();
	    	//Create the arcs
	    	for (int i = 0; i < upCableSet.getUpCables().size(); i++) {
	    		Relation relation = upCableSet.getUpCables().get(i).getRelation();
		    	LinkedList<Node> nodeset = upCableSet.getUpCables().get(i).getNodeSet().getNodes();
		    	for(Node item : nodeset) {
		    		graph.addEdge(edgeFactory.create(),item.getIdentifier(),node.getIdentifier());
		    		System.out.println("Relation Name: " + relation.getName());
		    		System.out.println("Node Name: " + node.getIdentifier());
		    		System.out.println("Upcable Node: "+ item.getIdentifier());
		    	}
	    	}
	    }
	}
	
	public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container content = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LinkedList<Relation> setOfRelations = new LinkedList<Relation>();
        
        Network network = new Network();
        try {
        	//Define the relations
        	Relation rr1 = network.defineRelation("member","entity","reduce",0);
        	Relation rr2 = network.defineRelation("class","entity","reduce",0);
        	Relation rr3 = network.defineRelation("object","entity","reduce",0);
        	Relation rr4 = network.defineRelation("isa","entity","reduce",0);
        	
        	//Define the caseframe
        	LinkedList<Relation> relations1 = new LinkedList<Relation>();
        	relations1.add(rr1);
        	relations1.add(rr2);
        	CaseFrame caseframe1 = network.defineCaseFrame("entity", relations1);
        	
        	LinkedList<Relation> relations2 = new LinkedList<Relation>();
        	relations1.add(rr3);
        	relations1.add(rr4);
        	CaseFrame caseframe2 = network.defineCaseFrame("entity", relations2);
        	
        	//(assert member (Clyde, Dumbo) class elephant)
        	Node node = network.build("Clyde");
        	Node node1 = network.build("Dumbo");
        	Node node2 = network.build("elephant");
        	
        	Object[][] o1 = new Object[3][2];
        	o1[0][0] = rr1;
        	o1[0][1] = node;
        	o1[1][0] = rr1;
        	o1[1][1] = node1;
        	o1[2][0] = rr2;
        	o1[2][1] = node2;
        	
        	Node res1 = network.build(o1,caseframe1);
        	System.out.println("Created Node: " + res1.getIdentifier());
        	System.out.println("Network Nodes: " + network.getNodes().get(res1.getIdentifier()).getIdentifier());
        	
        	//(assert member Tweety class canary)
        	Node node3 = network.build("Tweety");
        	Node node4 = network.build("canary");
        	
        	Object[][] o2 = new Object[2][2];
        	o2[0][0] = rr1;
        	o2[0][1] = node3;
        	o2[1][0] = rr2;
        	o2[1][1] = node4;
        	
        	Node res2 = network.build(o2,caseframe1);
        	System.out.println("Created Node: " + res2.getIdentifier());
        	System.out.println("Network Nodes: " + network.getNodes().get(res2.getIdentifier()).getIdentifier());
        	
        	//(assert member Opus class bird)
        	Node node5 = network.build("Opus");
        	Node node6 = network.build("bird");
        	
        	Object[][] o3 = new Object[2][2];
        	o3[0][0] = rr1;
        	o3[0][1] = node5;
        	o3[1][0] = rr2;
        	o3[1][1] = node6;
        	
        	Node res3 = network.build(o3,caseframe1);
        	System.out.println("Created Node: " + res3.getIdentifier());
        	System.out.println("Network Nodes: " + network.getNodes().get(res3.getIdentifier()).getIdentifier());
        	
//        	//(assert object elephant isa animal)
//       
//    		Node node7 = network.build("elephant");
//        	Node node8 = network.build("animal");
//        	
//        	
//        	Object[][] o4 = new Object[2][2];
//        	o4[0][0] = rr3;
//        	o4[0][1] = node7;
//        	o4[1][0] = rr4;
//        	o4[1][1] = node8;
//        	
//        	Node res4 = network.build(o4,caseframe2);
//        	System.out.println("Created Node: " + res4.getIdentifier());
//        	System.out.println("Network Nodes: " + network.getNodes().get(res4.getIdentifier()).getIdentifier());
			
		} catch (CustomException e) {
			e.printStackTrace();
		}
		
        
        content.add(new VisualizeNetworks(network));
        frame.pack();
        frame.setVisible(true);
    }
	
	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}
}