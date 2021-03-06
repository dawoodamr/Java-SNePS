package snepsui.Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.functors.MapTransformer;
import org.apache.commons.collections15.map.LazyMap;

import sneps.CaseFrame;
import sneps.CustomException;
import sneps.Entity;
import sneps.Network;
import sneps.Node;
import sneps.Relation;
import snepsui.Commands.cmdCaseFrame;
import snepsui.Commands.cmdDefine;
import snepsui.Commands.cmdUndefine;
import snepsui.Commands.cmdUndefineCaseFrame;
import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractPopupGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.AbstractVertexShapeTransformer;
import edu.uci.ics.jung.visualization.decorators.ConstantDirectionalEdgeValueTransformer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/

/**
 * @author Alia Taher
 */
@SuppressWarnings({ "unused", "unchecked", "hiding" })
public class DrawNetwork extends javax.swing.JPanel {

    private DirectedGraph<String, String> graph;
    private StaticLayout<String, String> layout;
    private VisualizationViewer<String, String> vv;
    private JComboBox caseframeComboBox;
    private JComboBox relationsComboBox;
    private SNePSInterface frame;
    private Network network;
    private Hashtable<String, CaseFrame> molNodes;
    private Hashtable<String, Node> builtMolNodes;
	private LinkedList<Node> nodesList;
	private Point point;
    
    /**
     * Transforms the color of each vertex depending of its type
     */
    private Transformer<String,Paint> vertexPaint;
    
    /**
     * Transforms the shape of the vertex depending on the name
     * of the vertex for all the text to be displayed inside the 
     * vertex
     */
    private Transformer<String,Integer> shape;
    
    /**
     * Transforms the name of the edge, it deletes the id appended
     *  to the relation name
     */
    private Transformer<String, String> edgeLabel;
    
    /**
     * The instruction on how to use the mouse actions
     */
    String instructions =
        "<html>"+
        "<h3>All Modes:</h3>"+
        "<ul>"+
        "<li>Right-click an empty area for <b>Create Node</b> popup"+
        "<li>Right-click on a Vertex for <b>Delete Node</b> popup"+
        "<li>Right-click on an Edge for <b>Delete Relation</b> popup"+

        "</ul>"+
        "<h3>Editing Mode:</h3>"+
        "<ul>"+
        "<li>Left-click an empty area to create a new Node"+
        "<li>Left-click on a Node and drag to another Node to create a Directed Edge"+
        "</ul>"+
        "<h3>Picking Mode:</h3>"+
        "<ul>"+
        "<li>Left-click on a Node selects the vertex"+
        "<li>Left-click elsewhere unselects all Nodes"+
        "<li>Left-click+Shift on a Node adds/removes Node selection"+
        "<li>Left-click+drag on a Node moves all selected Nodes"+
        "<li>Left-click+drag elsewhere selects Nodes in a region"+
        "<li>Left-click+CTRL on a Node selects the Node and centers the display on it"+
        "</ul>"+
        "<h3>Transforming Mode:</h3>"+
        "<ul>"+
        "<li>Left-click+drag pans the graph"+
        "<li>Left-click+Shift+drag rotates the graph"+
        "<li>Left-click+CTRL(or Command)+drag shears the graph"+
        "</ul>" +
        "</html>";
    
    /**
     * @param frame The main frame where all the panels are included which is SNePSInterface
     */
    public DrawNetwork(SNePSInterface frame) {
    	this.frame = frame;
    	initGUI();
    }
    
    /**
     * Initializes the components in the DrawNetwork panel
     */
    private void initGUI() {
    	nodesList = new LinkedList<Node>();
    	molNodes = new Hashtable<String, CaseFrame>();
    	builtMolNodes = new Hashtable<String, Node>();
    	
    	this.setPreferredSize(new Dimension(815, 600));
    	
        graph = new DirectedOrderedSparseMultigraph<String, String>();
        
        this.layout = new StaticLayout<String,String>(graph, 
        	new Dimension(700,450));
        
        shape = new Transformer<String, Integer>() {
	    	public Integer transform(String vertex) {
	    		int stringLength = 0;
	    		if(molNodes.containsKey(vertex)) {
        			stringLength = 3;
        		} else {
        			for(Node node : nodesList) {
        				if(vertex.equals(node.getIdentifier())) {
        					stringLength = node.getIdentifier().length();
        				}
            		}
        		}
	    		return stringLength;
	    	}
		};
        
        vertexPaint = new Transformer<String,Paint>() {
        	public Paint transform(String vertex) {
        		if(molNodes.containsKey(vertex)) {
        			if(builtMolNodes.containsKey(vertex)) {
        				Node node = builtMolNodes.get(vertex);
        				if (node.getClass().getSimpleName().equals("PatternNode")) {
    						return Color.blue;
    					} else if (node.getClass().getSimpleName().equals("ClosedNode")) {
    						return Color.yellow;
    					}
        			} else
        				return Color.white;
        		} else {
        			for(Node node : nodesList) {
        				if(node.getIdentifier().equals(vertex)) {
        					if(node.getClass().getSimpleName().equals("BaseNode")) {
        						return Color.green;
        					} else if (node.getClass().getSimpleName().equals("VariableNode")) {
        						return Color.gray;
        					} else if (node.getClass().getSimpleName().equals("PatternNode")) {
        						return Color.blue;
        					} else if (node.getClass().getSimpleName().equals("ClosedNode")) {
        						return Color.yellow;
        					} else {
        						return Color.magenta;
        					}
        				}
            		}
        		}
        		return Color.white;
			}
        };        	
        
        edgeLabel = new Transformer<String, String>() {
        	public String transform(String edge) {
        		String result = "";
        		
        		if(edge.isEmpty())
        			graph.removeEdge("");
        			vv.repaint();
        		try {
        			result = edge.substring(0, edge.indexOf(":"));
        		} catch (StringIndexOutOfBoundsException e) {
        			
        		}
        		return result;
        	}
		};
		
		VertexShapeSizeAspect<String> vssa = new VertexShapeSizeAspect<String>(graph, shape);
        
        vv =  new VisualizationViewer<String,String>(layout, new Dimension(700,470));
        vv.setBackground(Color.white);
        
        vv.getRenderContext().setVertexLabelTransformer(MapTransformer.<String,String>getInstance(
        		LazyMap.<String,String>decorate(new HashMap<String,String>(), new ToStringLabeller<String>())));
        vv.getRenderContext().setEdgeLabelTransformer(MapTransformer.<String,String>getInstance(
        		LazyMap.<String,String>decorate(new HashMap<String,String>(), new ToStringLabeller<String>())));
        vv.setVertexToolTipTransformer(vv.getRenderContext().getVertexLabelTransformer());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        vv.getRenderContext().setVertexShapeTransformer(vssa);
        vv.getRenderContext().setEdgeLabelClosenessTransformer(new ConstantDirectionalEdgeValueTransformer(0.5, 0.5));
        vv.getRenderContext().setEdgeLabelTransformer(edgeLabel);
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.addMouseListener(new MouseAdapter() {
        	public void mousePressed(MouseEvent e) {
        		point = e.getPoint();
        	}
        	
        	public void mouseReleased(MouseEvent e) {
        		vv.repaint();
        	}
		});
        vssa.setScaling(true);
        
        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
        this.add(panel);
        Factory<String> vertexFactory = new VertexFactory();
        Factory<String> edgeFactory = new EdgeFactory();
        
        final EditingModalGraphMouse<String,String> graphMouse = 
        	new EditingModalGraphMouse<String,String>(vv.getRenderContext(), vertexFactory, edgeFactory);
        
        graphMouse.add(new CustomEditingPopupGraphMousePlugin<String>(vertexFactory, edgeFactory));
        graphMouse.remove(graphMouse.getPopupEditingPlugin());
        
        vv.setGraphMouse(graphMouse);
        vv.addKeyListener(graphMouse.getModeKeyListener());
      
        graphMouse.setMode(ModalGraphMouse.Mode.EDITING);
        
        final ScalingControl scaler = new CrossoverScalingControl();
        
        vv.scaleToLayout(scaler);
        
        String path = "src/snepsui/Interface/resources/icons/";

        JButton plus = new JButton();
        plus.setIcon(new ImageIcon(path + "zoom_in.png"));
        plus.setSize(18, 18);
        plus.setFocusPainted(false);
        plus.setBorderPainted(false);
        plus.setContentAreaFilled(false);
        plus.setMargin(new Insets(0,0,0,0));
        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1.1f, vv.getCenter());
            }
        });
        
        JButton minus = new JButton();
        minus.setIcon(new ImageIcon(path + "zoom_out.png"));
        minus.setSize(18, 18);
        minus.setFocusPainted(false);
        minus.setBorderPainted(false);
        minus.setContentAreaFilled(false);
        minus.setMargin(new Insets(0,0,0,0));
        minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1/1.1f, vv.getCenter());
            }
        });

        JPanel scaleGrid = new JPanel(new GridLayout(1,2));
        scaleGrid.setBorder(BorderFactory.createTitledBorder("Zoom"));
        
        JButton colors = new JButton(new ImageIcon(path + "colors.png"));
        colors.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(getRootPane(), new NodeColors());
			}
		});
        
		JButton infoButton = new JButton(new ImageIcon(path + "info.png"));
		infoButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(vv, 
                		instructions, 
                		"Instructions", 
                		JOptionPane.INFORMATION_MESSAGE,
                		new ImageIcon("src/snepsui/Interface/resources/icons/info.png"));
        }});
		
		JButton resetbutton = new JButton(new ImageIcon(path + "refresh.png"));
		resetbutton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	ImageIcon icon = new ImageIcon("src/snepsui/Interface/resources/icons/info.png");
                int result = JOptionPane.showConfirmDialog(vv,
                		"Are you sure you want to reset the drawing area?",
                		"Reset",
                		JOptionPane.YES_NO_OPTION,
                		JOptionPane.QUESTION_MESSAGE,
                		icon);
                if(result == JOptionPane.YES_OPTION) {
                	builtMolNodes.clear();
                	molNodes.clear();
                	nodesList.clear();
                	
                	LinkedList<String> vertexList = new LinkedList<String>();
                	Collection<String> vertices = graph.getVertices();
                	for(String vertex : vertices) {
                		vertexList.add(vertex);
                	}
                	
                	for(String item : vertexList) {
                		graph.removeVertex(item);
                	}
                	vv.repaint();
                }
        }});
		
        JLabel caseframeLabel = new JLabel("Case Frames");
        DefaultComboBoxModel caseframeComboBoxModel = new DefaultComboBoxModel(new String []{"define-caseframe","undefine-caseframe"});
        caseframeComboBox = new JComboBox();
        caseframeComboBox.setModel(caseframeComboBoxModel);
        caseframeComboBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	addCaseFrameCommands(e);
            }});
        
        JLabel relationsLabel = new JLabel("Relations");
        DefaultComboBoxModel relationsComboBoxModel = new DefaultComboBoxModel(new String []{"define","undefine"});
        relationsComboBox = new JComboBox();
        relationsComboBox.setModel(relationsComboBoxModel);
        relationsComboBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	addRelationCommands(e);
            }});
        
        JPanel options = new JPanel(new GridLayout(3,1));
        options.add(infoButton);
        options.add(resetbutton);
        options.add(colors);
        this.add(options, BorderLayout.EAST);
        
        JPanel controls = new JPanel();
        scaleGrid.add(plus);
        scaleGrid.add(minus);
        controls.add(scaleGrid);
        controls.add(relationsLabel);
        controls.add(relationsComboBox);
        controls.add(caseframeLabel);
        controls.add(caseframeComboBox);
        JComboBox modeBox = graphMouse.getModeComboBox();
        controls.add(modeBox);
        this.add(controls, BorderLayout.SOUTH);
    }
    
    /**
     * This class creates vertices when a user clicks an empty space on the VisualizationViewer panel
     */
    class VertexFactory implements Factory<String> {
    	int m = 0;
    	
    	/**
    	 * This method creates vertices to be added to the graph and also builds the nodes to be added to the network
    	 * @return the name of the node
    	 */
		public String create() {
			String nodeName = "";
			Icon icon = new ImageIcon();
			Object[] possibilities = {"Base Node", "Molecular Node", "Variable Node"};
			
			try {
				String s = (String)JOptionPane.showInputDialog(
						getRootPane(),
						"Choose the Node you want to create:",
						"Create a Node",
						JOptionPane.OK_OPTION,
						icon,
						possibilities,
						"Base Node");
				
				if (s.equals("Base Node")) {
					nodeName = (String)JOptionPane.showInputDialog(
							getRootPane(),
							"Node Name:",
							"Create a Base Node",
							JOptionPane.PLAIN_MESSAGE);
					if(nodeName.equals(JOptionPane.CANCEL_OPTION)) {
						return null;
					} else {
						Node node = network.build(nodeName);
						nodesList.add(node);
					}
				} else if (s.equals("Molecular Node")) {
					
					String caseframe = "";
					
					String caseframeStr = "";
					int caseframeCounter = 0;
					
					//Get case frames
					Hashtable<String, CaseFrame> caseframes = network.getCaseFrames();
					Object [] caseframePossibilities = new Object[caseframes.size()]; 
					Set<String> caseframeSet = caseframes.keySet();

				    Iterator<String> caseframeItr = caseframeSet.iterator();
				    while (caseframeItr.hasNext()) {
				    	caseframeStr = caseframeItr.next();
				    	caseframePossibilities[caseframeCounter] = caseframes.get(caseframeStr).getId();
				    	caseframeCounter++;
				    }
					
					caseframe = (String) JOptionPane.showInputDialog(
								getRootPane(),
								"Choose the Case Frame:",
								"Case Frame",
								JOptionPane.OK_OPTION,
								icon,
								caseframePossibilities,
								caseframePossibilities[0]);
					m++;
					nodeName = "m"+m;
					try{
						molNodes.put(nodeName, network.getCaseFrame(caseframe));
					} catch (Exception e) {}
						
				} else if (s.equals("Variable Node")) {
					Node node = network.buildVariableNode();
					nodeName = node.getIdentifier();
					nodesList.add(node);
				}
			} catch (NullPointerException e) {}
			catch (CustomException e) {
				JOptionPane.showMessageDialog(getRootPane(),
		    			  "The node " + nodeName + " already exits",
		    			  "Warning",
		    			  JOptionPane.INFORMATION_MESSAGE);
				try {
					nodesList.add(network.getNode(nodeName));
				} catch (CustomException e1) {}
			}
			return nodeName;
		}
    }
    
    /**
     * This class creates an edge when a user clicks on a vertex and connects it to another vertex
     */
    class EdgeFactory implements Factory<String> {
    	int id = 0;
    	/**
    	 * This method creates edges and the case frames for the built node is chose here
    	 * @return the name of the relation
    	 */
		public String create() {
			String relation = "";
			int relationCounter = 0;
			Icon icon = new ImageIcon();
			
			String picked = vv.getPickSupport().getVertex(layout, point.getX(), point.getY());
			Point endPoint = vv.getMousePosition();
			String endVertex = vv.getPickSupport().getVertex(layout, endPoint.getX(), endPoint.getY());
			//System.out.println("Predecessor: " + graph.isPredecessor(endVertex,picked));
			if((!graph.isPredecessor(endVertex,picked)) && (!graph.isPredecessor(picked, endVertex))) {
				//Connecting new node to already built Molecular Nodes
				if (builtMolNodes.containsKey(picked)){
			    	JOptionPane.showMessageDialog(getRootPane(), 
			    			"You can't connect new nodes to an already built node",
			    			"Error",
			    			JOptionPane.ERROR_MESSAGE);
			    	return "";
				}
				
				//Check if an end vertex is a molecular node that was not built
				if(molNodes.containsKey(endVertex) && (!builtMolNodes.containsKey(endVertex)) && endVertex!=picked) {
					int result = JOptionPane.showConfirmDialog(getRootPane(), 
							"You can only connect " + picked + " to " + endVertex + ", only if " + endVertex + " is built. " +
									"Do you want to build " + endVertex + "?", 
							"Build Molecular Node", 
							JOptionPane.YES_NO_OPTION);
					
					if(result == JOptionPane.YES_OPTION) {
						buildMolNode(endVertex);
					} else if(result == JOptionPane.NO_OPTION) {
						return "";
					}
				}
				
				if(molNodes.containsKey(picked) && picked!=endVertex && (!builtMolNodes.containsKey(picked))) {
			    	CaseFrame caseframe = molNodes.get(picked);
			    	LinkedList<Relation> relations = caseframe.getRelations();
			    	
			    	Object [] relationsPossibilities = new Object[relations.size()]; 

				    for (Relation item : relations) {
				      relationsPossibilities[relationCounter] = item.getName();
				      relationCounter++;
				    }
				    
					relation = (String)JOptionPane.showInputDialog(
					getRootPane(),
					"Choose the relation:",
					"Choose a Relation",
					JOptionPane.OK_OPTION,
					icon,
					relationsPossibilities,
					relationsPossibilities[0]);
					
					try {
						Relation rel = network.getRelation(relation);
						Node node = network.getNode(endVertex);
						
						frame.getsNePSULPanel1().getMenuDrivenCommands().checkConsistency(rel, node);
						
					} catch (CustomException e) {}
					
					
					return relation +":"+ id++;
				//A node connected to itself
			    } else if(picked==endVertex) {
			    	JOptionPane.showMessageDialog(getRootPane(), 
			    			"A node can not be connected to itself",
			    			"Error",
			    			JOptionPane.ERROR_MESSAGE);
			    } else {
			    	JOptionPane.showMessageDialog(getRootPane(), 
			    			"Only Molecular Nodes can have outgoing relations",
			    			"Error",
			    			JOptionPane.ERROR_MESSAGE);
			    }
			} else {
				JOptionPane.showMessageDialog(getRootPane(), 
		    			"A node can only be connected with one relation",
		    			"Error",
		    			JOptionPane.ERROR_MESSAGE);
			}
			return relation;
		}
    }
    
    /**
     * Gets the selected case frame command selected in the case frame combobox and puts it in a frame
     * @param e the event that was fired
     */
    private void addCaseFrameCommands(ActionEvent e) {
    	JFrame popupFrame = new JFrame("Case Frames");
    	popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if (caseframeComboBox.getSelectedItem().toString().equals("define-caseframe")) {
        	popupFrame.getContentPane().add(new cmdCaseFrame(network, frame));
        	popupFrame.pack();
        	popupFrame.setVisible(true);
        } else if (caseframeComboBox.getSelectedItem().toString().equals("undefine-caseframe")) {
        	popupFrame.getContentPane().add(new cmdUndefineCaseFrame(network, frame));
        	popupFrame.pack();
        	popupFrame.setVisible(true);
        }
    }
    
    /**
     * Gets the selected relation command selected in the relation combobox and puts it in a frame
     * @param e the event that was fired
     */
    private void addRelationCommands(ActionEvent e) {
    	JFrame popupFrame = new JFrame("Case Frames");
    	popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if (relationsComboBox.getSelectedItem().toString().equals("define")) {
        	popupFrame.getContentPane().add(new cmdDefine(network, frame));
        	popupFrame.pack();
        	popupFrame.setVisible(true);
        } else if (relationsComboBox.getSelectedItem().toString().equals("undefine")) {
        	popupFrame.getContentPane().add(new cmdUndefine(network, frame));
        	popupFrame.pack();
    		popupFrame.setVisible(true);
        }
    }
    
    private final static class VertexShapeSizeAspect<String>
    extends AbstractVertexShapeTransformer <String>
    implements Transformer<String,Shape>  {
    	
        protected boolean scale = false;
        protected boolean funny_shapes = false;
        protected Transformer<String,Integer> shape;
        protected Graph<String,String> graph;
        
        public VertexShapeSizeAspect(Graph<String, String> graph2, Transformer<String,Integer> shapeIn)
        {
        	this.graph = graph2;
            this.shape = shapeIn;
            setSizeTransformer(new Transformer<String,Integer>() {

				public Integer transform(String v) {
		            if (scale) {
		            	int shapeSize = shape.transform(v);
		            	if(shapeSize == 2) {
		            		return 20;
		            	} else if (shapeSize == 3) {
		            		return 30;
		            	} else {
		            		return shapeSize * 8;
		            	}
		            }
		            else
		                return 20;
				}});
        }
        
		public void setScaling(boolean scale)
        {
            this.scale = scale;
        }

		@Override
		public Shape transform(String v) {
			if (funny_shapes)
            {
                if (graph.degree(v) < 5)
                {	
                    int sides = Math.max(graph.degree(v), 3);
                    return factory.getRegularPolygon(v, sides);
                }
                else
                    return factory.getRegularStar(v, graph.degree(v));
            }
            else
                return factory.getEllipse(v);
		}
    }
    
    private class CustomEditingPopupGraphMousePlugin<String> extends AbstractPopupGraphMousePlugin {
        
        protected Factory<String> vertexFactory;
        protected Factory<String> edgeFactory;
        
        public CustomEditingPopupGraphMousePlugin(Factory<String> vertexFactory, Factory<String> edgeFactory) {
            this.vertexFactory = vertexFactory;
            this.edgeFactory = edgeFactory;
        }
        
    	protected void handlePopup(MouseEvent e) {
            final VisualizationViewer<String,String> vv =
                (VisualizationViewer<String,String>)e.getSource();
            final Layout<String,String> layout = vv.getGraphLayout();
            final Graph<String,String> graph = layout.getGraph();
            final Point2D p = e.getPoint();
            final Point2D ivp = p;
            GraphElementAccessor<String,String> pickSupport = vv.getPickSupport();
            JPopupMenu popup = new JPopupMenu();
            if(pickSupport != null) {
                
                final String vertex = pickSupport.getVertex(layout, ivp.getX(), ivp.getY());
                final String edge = pickSupport.getEdge(layout, ivp.getX(), ivp.getY());
                final PickedState<String> pickedVertexState = vv.getPickedVertexState();
                final PickedState<String> pickedEdgeState = vv.getPickedEdgeState();
                
                if(vertex != null) {
            	if(molNodes.containsKey(vertex) && (!builtMolNodes.containsKey(vertex)) && graph.outDegree(vertex) > 0) {
            		 popup.add(new AbstractAction("Build Molecular Node") {
                         public void actionPerformed(ActionEvent e) {
                        	 buildMolNode((java.lang.String)vertex);
                         }});
                	}
            		if(builtMolNodes.containsKey(vertex)) {
            			popup.add(new AbstractAction("Assert") {
                            public void actionPerformed(ActionEvent e) {
                           	 Transformer<String, java.lang.String> vs = vv.getRenderContext().getVertexLabelTransformer();
   				    			if(vs instanceof MapTransformer) {
   				    				Map<String,String> map = ((MapTransformer)vs).getMap();
   				    				String assertNode = (String) (builtMolNodes.get(vertex).getIdentifier() + "!");
   				    				if(vertex != null) {
   			    						map.put(vertex, assertNode);
   			    						vv.repaint();
   				    				}
   				    			}
                            }});
            		}
                		
                    popup.add(new AbstractAction("Delete Node") {
                        public void actionPerformed(ActionEvent e) {
                            pickedVertexState.pick(vertex, false);
                            try {
								Node node = network.getNode(vertex.toString());
								if(node.getUpCableSet().size() == 0) {
									network.removeNode(node);
									graph.removeVertex(vertex);
								} else {
									int result = JOptionPane.showConfirmDialog(
											getRootPane(),
											"The node is connected to other nodes, do you want to" +
											" delete it from the graph you are drawing?",
											"Delete Node",
											JOptionPane.YES_NO_OPTION);
									if(result == JOptionPane.YES_OPTION) {
										graph.removeVertex(vertex);
									}
								}
							} catch (CustomException e1) {}
                            vv.repaint();
                        }});
                } else if(edge != null) {
                    popup.add(new AbstractAction("Delete Relation") {
                        public void actionPerformed(ActionEvent e) {
                            pickedEdgeState.pick(edge, false);
                            graph.removeEdge(edge);
                            vv.repaint();
                        }});
                } else {
                    popup.add(new AbstractAction("Create Node") {
                        public void actionPerformed(ActionEvent e) {
                        	String newVertex = vertexFactory.create();
                            graph.addVertex(newVertex);
                            layout.setLocation(newVertex, vv.getRenderContext().getMultiLayerTransformer().inverseTransform(p));
                            vv.repaint();
                        }
                    });
                }
                if(popup.getComponentCount() > 0) {
                    popup.show(vv, e.getX(), e.getY());
                }
            }
        }
    }
    
    private void buildMolNode(String vertex) {
    	Collection<String> relations = graph.getOutEdges(vertex);
	   	 int cablecounter = 0;
	   	 Object[][] cableset = new Object[relations.size()][2];
	   	 for(String relation : relations) {
	   		 String opposite = graph.getOpposite(vertex, relation);
	   		 String modifiedRelation = relation.substring(0, relation.indexOf(":"));
				 try {
					 Relation rel = network.getRelation(modifiedRelation);
					 cableset[cablecounter][0] = rel;
					 
					 
					 if(builtMolNodes.containsKey(opposite)) {
						 Node node = builtMolNodes.get(opposite);
						 cableset[cablecounter][1] = node;
					 } else {
						 Node node = network.getNode(opposite);
						 cableset[cablecounter][1] = node;
					 }
					 
					 cablecounter++;
				} catch (CustomException e1) {}
	   	 }
	   	 
	   	 try {
	   		 CaseFrame caseframe = molNodes.get(vertex);
	   		 Node node = network.build(cableset, caseframe);
				
			 String nodeName = (String) node.getIdentifier();
				
			 nodesList.add(node);
			 builtMolNodes.put((java.lang.String)vertex, node);
			
			 Transformer<String, java.lang.String> vs = vv.getRenderContext().getVertexLabelTransformer();
			 if(vs instanceof MapTransformer) {
				 Map<String,String> map = ((MapTransformer)vs).getMap();
				
				 if(vertex != null) {
					 map.put(vertex, nodeName);
					 vv.repaint();
				 }
			 }
		} catch (CustomException e1) {}
		
		frame.getNodesTreePanel1().initGUI();
		frame.getNodesTreePanel1().validate();
		frame.getNodesTreePanel1().repaint();
    }
    
    /**
     * Takes a file and converts it to a JPEG image
     * @param file the file to be created
     */
    public void writeJPEGImage(File file) {
        int width = vv.getWidth();
        int height = vv.getHeight();

        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bi.createGraphics();
        vv.paint(graphics);
        graphics.dispose();
        
        try {
            ImageIO.write(bi, "jpeg", file);
        } catch (Exception e) {}
    }
    
    /**
	 * Gets the current Network
	 * @return the current Network
	 */
    public Network getNetwork() {
		return network;
	}
    
    /**
	 * Sets the current Network to the given Network
	 * @param network the network to be set as the current Network
	 */
	public void setNetwork(Network network) {
		this.network = network;
	}
}