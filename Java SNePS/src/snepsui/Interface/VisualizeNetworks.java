package snepsui.Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.functors.ConstantTransformer;

import sneps.Cable;
import sneps.CaseFrame;
import sneps.MolecularNode;
import sneps.Network;
import sneps.Node;
import sneps.Relation;
import sneps.UpCable;
import sneps.UpCableSet;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.AbstractVertexShapeTransformer;
import edu.uci.ics.jung.visualization.decorators.ConstantDirectionalEdgeValueTransformer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

/**
 * @author Alia Taher
 */
@SuppressWarnings({"unchecked", "hiding"})
public class VisualizeNetworks extends javax.swing.JPanel {

    private Network network;
    private int id = 0;
    
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
    private VisualizationViewer<String, String> vv;

	public VisualizeNetworks() {
		super();
		if(network!=null) {
			initGUI();
		}
	}
	
	public void initGUI() {
		try {
			this.removeAll();
			setPreferredSize(new Dimension(800, 800));
			drawNetwork();
		} catch (Exception e) {}
	}
	
	private void drawNetwork() {
		try {
			Graph<String, String> graph = new DirectedSparseMultigraph<String, String>();
			final LinkedList<Node> nodesList = new LinkedList<Node>();
			
			Hashtable<String, Node> nodes = network.getNodes();
			String nodeString = ""; 
			Set<String> set = nodes.keySet();

		    Iterator<String> itr1 = set.iterator();
		    while (itr1.hasNext()) {
		    	nodeString = itr1.next();
		    	Node node = nodes.get(nodeString);
		    	String nodeName = node.getIdentifier();
		    	graph.addVertex(nodeName);
		    	nodesList.add(node);
		    }
		    
		    Iterator<String> itr2 = set.iterator();
		    while (itr2.hasNext()) {
		    	nodeString = itr2.next();
		    	Node node = nodes.get(nodeString);
		    	UpCableSet upCableSet = node.getUpCableSet();
		    	for (int i = 0; i < upCableSet.getUpCables().size(); i++) {
		    		Relation relation = upCableSet.getUpCables().get(i).getRelation();
			    	LinkedList<Node> nodeset = upCableSet.getUpCables().get(i).getNodeSet().getNodes();
			    	for(Node item : nodeset) {
			    		graph.addEdge(new RelationEdge(relation.getName()).toString(),item.getIdentifier(),node.getIdentifier());
			    	}
		    	}
		    }
		    
		    ISOMLayout<String, String> layout = new ISOMLayout<String,String>(graph);
		    
		    shape = new Transformer<String, Integer>() {
		    	public Integer transform(String vertex) {
		    		int stringLength = 0;
		    		for(Node node : nodesList) {
	    				if(vertex.equals(node.getIdentifier())) {
	    					stringLength = node.getIdentifier().length();
	    				}
	        		}
		    		return stringLength;
		    	}
			};
	        
	        vertexPaint = new Transformer<String,Paint>() {
	        	public Paint transform(String vertex) {
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
	    					}
	    				}
	        		}
	        		return Color.magenta;
	        	}
	        };
	        
	        edgeLabel = new Transformer<String, String>() {
	        	public String transform(String edge) {
	        		return edge.substring(0, edge.indexOf(":"));
	        	}
			};
	        
	        VertexShapeSizeAspect<String> vssa = new VertexShapeSizeAspect<String>(graph, shape);
	        
	        vv =  new VisualizationViewer<String,String>(layout, new Dimension(700,470));
	        
	        vv.setBackground(Color.white);
	        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());
	        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
	        vv.getRenderContext().setEdgeLabelTransformer(edgeLabel);
	        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
	        vv.setVertexToolTipTransformer(new ToStringLabeller<String>());
	        vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));
	        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
	        vv.getRenderContext().setVertexShapeTransformer(vssa);
	        vv.getRenderContext().setEdgeLabelClosenessTransformer(new ConstantDirectionalEdgeValueTransformer(0.5, 0.5));
	        vssa.setScaling(true);
	        
	        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
	        this.add(panel);
	        
	        final DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();

	        vv.setGraphMouse(graphMouse);
	        vv.addKeyListener(graphMouse.getModeKeyListener());

	        JComboBox modeBox = graphMouse.getModeComboBox();
	        modeBox.addItemListener(graphMouse.getModeListener());
	        graphMouse.setMode(ModalGraphMouse.Mode.PICKING);

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
	        
	        String path = "src/snepsui/Interface/resources/icons/";
	        
	        JButton colors = new JButton(new ImageIcon(path + "colors.png"));
	        colors.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame popupFrame = new JFrame("Node Colors");
					popupFrame.setLocation(450, 350);
					popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					popupFrame.getContentPane().add(new NodeColors());
					popupFrame.pack();
					popupFrame.setResizable(false);
					popupFrame.setVisible(true);
				}
			});

	        JPanel scaleGrid = new JPanel(new GridLayout(1,0));
	        scaleGrid.setBorder(BorderFactory.createTitledBorder("Zoom"));

	        JPanel controls = new JPanel();
	        scaleGrid.add(plus);
	        scaleGrid.add(minus);
	        controls.add(scaleGrid);
	        controls.add(modeBox);
	        controls.add(colors);
	        this.add(controls, BorderLayout.SOUTH);
	        
	        this.validate();
	        this.repaint();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "There is no network to display");
		}
		
	}
	
	public void drawRelation(Relation relation) {
		try {
			Graph<String, String> graph = new DirectedSparseMultigraph<String, String>();
			final LinkedList<Node> nodesList = new LinkedList<Node>();
			
			Hashtable<String, Node> nodes = network.getNodes();
			String nodeString = ""; 
			Set<String> set = nodes.keySet();

		    Iterator<String> itr1 = set.iterator();
		    while (itr1.hasNext()) {
		    	nodeString = itr1.next();
		    	Node node = nodes.get(nodeString);
		    	LinkedList<UpCable> upcables = node.getUpCableSet().getUpCables();
		    	
		    	for(UpCable upcable : upcables) {
		    		if(upcable.getRelation().equals(relation)) {
		    			nodesList.add(node);
		    			LinkedList<Node> upcableNodes = upcable.getNodeSet().getNodes();
		    			
		    			for(Node upcableNode : upcableNodes) {
		    				graph.addEdge(new RelationEdge(upcable.getRelation().getName()).toString(),
		    						upcableNode.getIdentifier(), 
		    						node.getIdentifier());
		    				nodesList.add(upcableNode);
		    			}
		    		}
		    	}
		    }
		    
		    ISOMLayout<String, String> layout = new ISOMLayout<String,String>(graph);
		    
		    shape = new Transformer<String, Integer>() {
		    	public Integer transform(String vertex) {
		    		int stringLength = 0;
		    		for(Node node : nodesList) {
	    				if(vertex.equals(node.getIdentifier())) {
	    					stringLength = node.getIdentifier().length();
	    				}
	        		}
		    		return stringLength;
		    	}
			};
	        
	        vertexPaint = new Transformer<String,Paint>() {
	        	public Paint transform(String vertex) {
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
	    					}
	    				}
	        		}
	        		return Color.magenta;
	        	}
	        };
	        
	        edgeLabel = new Transformer<String, String>() {
	        	public String transform(String edge) {
	        		return edge.substring(0, edge.indexOf(":"));
	        	}
			};
	        
	        VertexShapeSizeAspect<String> vssa = new VertexShapeSizeAspect<String>(graph, shape);
	        
	        vv =  new VisualizationViewer<String,String>(layout, new Dimension(700,220));
	        
	        vv.setBackground(Color.white);
	        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());
	        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
	        vv.getRenderContext().setEdgeLabelTransformer(edgeLabel);
	        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
	        vv.setVertexToolTipTransformer(new ToStringLabeller<String>());
	        vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));
	        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
	        vv.getRenderContext().setVertexShapeTransformer(vssa);
	        vv.getRenderContext().setEdgeLabelClosenessTransformer(new ConstantDirectionalEdgeValueTransformer(0.5, 0.5));
	        vssa.setScaling(true);
	        
	        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
	        this.add(panel);
	        
	        final DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();

	        vv.setGraphMouse(graphMouse);
	        vv.addKeyListener(graphMouse.getModeKeyListener());

	        JComboBox modeBox = graphMouse.getModeComboBox();
	        modeBox.addItemListener(graphMouse.getModeListener());
	        graphMouse.setMode(ModalGraphMouse.Mode.PICKING);

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

	        JPanel scaleGrid = new JPanel(new GridLayout(1,0));
	        scaleGrid.setBorder(BorderFactory.createTitledBorder("Zoom"));
	        
	        String path = "src/snepsui/Interface/resources/icons/";
	        
	        JButton nodeColors = new JButton(new ImageIcon(path + "colors.png"));
	        nodeColors.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame popupFrame = new JFrame("Node Colors");
					popupFrame.setLocation(450, 350);
					popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					popupFrame.getContentPane().add(new NodeColors());
					popupFrame.pack();
					popupFrame.setResizable(false);
					popupFrame.setVisible(true);
				}
			});

	        JPanel controls = new JPanel();
	        scaleGrid.add(plus);
	        scaleGrid.add(minus);
	        controls.add(scaleGrid);
	        controls.add(modeBox);
	        controls.add(nodeColors);
	        this.add(controls, BorderLayout.SOUTH);
	        
	        this.validate();
	        this.repaint();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, 
					"The relation " + relation.getName() + " is not used in the current network", 
					"", 
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void drawNode(Node node) {
		Graph<String, String> graph = new DirectedSparseMultigraph<String, String>();
		final LinkedList<Node> nodesList = new LinkedList<Node>();
		
		String nodeName = node.getIdentifier();
		graph.addVertex(nodeName);
		nodesList.add(node);
		
		LinkedList<UpCable> upcables = node.getUpCableSet().getUpCables();
		
		for(UpCable upcable : upcables) {
			LinkedList<Node> upcableNodes = upcable.getNodeSet().getNodes();
			String relation = upcable.getRelation().getName();
			
			for(Node upcableNode : upcableNodes) {
				graph.addEdge(new RelationEdge(relation).toString(), upcableNode.getIdentifier(), nodeName);
				nodesList.add(upcableNode);
			}
		}
		
		if(node instanceof MolecularNode) {
			MolecularNode molNode = (MolecularNode) node;
			LinkedList<Cable> cables = molNode.getCableSet().getCables();
			
			for(Cable cable : cables) {
				LinkedList<Node> cableNodes = cable.getNodeSet().getNodes();
				String relation = cable.getRelation().getName();
				
				for(Node cableNode : cableNodes) {
					graph.addEdge(new RelationEdge(relation).toString(), nodeName, cableNode.getIdentifier());
					nodesList.add(cableNode);
				}
			}
		}
		
		ISOMLayout<String, String> layout = new ISOMLayout<String,String>(graph);
        
		shape = new Transformer<String, Integer>() {
	    	public Integer transform(String vertex) {
	    		int stringLength = 0;
	    		for(Node node : nodesList) {
    				if(vertex.equals(node.getIdentifier())) {
    					stringLength = node.getIdentifier().length();
    				}
        		}
	    		return stringLength;
	    	}
		};
        
        vertexPaint = new Transformer<String,Paint>() {
        	public Paint transform(String vertex) {
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
    					}
    				}
        		}
        		return Color.magenta;
        	}
        };
        
        edgeLabel = new Transformer<String, String>() {
        	public String transform(String edge) {
        		return edge.substring(0, edge.indexOf(":"));
        	}
		};
		
		VertexShapeSizeAspect<String> vssa = new VertexShapeSizeAspect<String>(graph, shape);
        
        vv =  new VisualizationViewer<String,String>(layout, new Dimension(700,220));
        vv.setBackground(Color.white);
        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
        vv.getRenderContext().setEdgeLabelTransformer(edgeLabel);
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.setVertexToolTipTransformer(new ToStringLabeller<String>());
        vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        vv.getRenderContext().setVertexShapeTransformer(vssa);
        vv.getRenderContext().setEdgeLabelClosenessTransformer(new ConstantDirectionalEdgeValueTransformer(0.5, 0.5));
        vssa.setScaling(true);
        
        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
        this.add(panel);
        
        final DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();

        vv.setGraphMouse(graphMouse);
        vv.addKeyListener(graphMouse.getModeKeyListener());

        JComboBox modeBox = graphMouse.getModeComboBox();
        graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
        modeBox.addItemListener(graphMouse.getModeListener());
      
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
        
        String path = "src/snepsui/Interface/resources/icons/";
        
        JButton colors = new JButton(new ImageIcon(path + "colors.png"));
        colors.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame popupFrame = new JFrame("Node Colors");
				popupFrame.setLocation(450, 350);
				popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				popupFrame.getContentPane().add(new NodeColors());
				popupFrame.pack();
				popupFrame.setResizable(false);
				popupFrame.setVisible(true);
			}
		});

        JPanel scaleGrid = new JPanel(new GridLayout(1,0));
        scaleGrid.setBorder(BorderFactory.createTitledBorder("Zoom"));

        JPanel controls = new JPanel();
        scaleGrid.add(plus);
        scaleGrid.add(minus);
        controls.add(scaleGrid);
        controls.add(modeBox);
        controls.add(colors);
        this.add(controls, BorderLayout.SOUTH);
        
        this.validate();
        this.repaint();
	}
	
	public void drawCaseFrame(CaseFrame caseframe) {
		try {
			Graph<String, String> graph = new DirectedSparseMultigraph<String, String>();
			final LinkedList<Node> nodesList = new LinkedList<Node>();
			
			Hashtable<String, Node> nodes = network.getNodes();
			String nodeString = ""; 
			Set<String> set = nodes.keySet();

		    Iterator<String> itr1 = set.iterator();
		    while (itr1.hasNext()) {
		    	nodeString = itr1.next();
		    	Node node = nodes.get(nodeString);
		    	
		    	if(node instanceof MolecularNode) {
					MolecularNode molNode = (MolecularNode) node;
					if(molNode.getCableSet().getCaseFrame().equals(caseframe)) {
						nodesList.add(node);
						LinkedList<Cable> cables = molNode.getCableSet().getCables();
						
						for(Cable cable : cables) {
							LinkedList<Node> cableNodes = cable.getNodeSet().getNodes();
							String relation = cable.getRelation().getName();
							
							for(Node cableNode : cableNodes) {
								graph.addEdge(new RelationEdge(relation).toString(), molNode.getIdentifier(), cableNode.getIdentifier());
								nodesList.add(cableNode);
							}
						}
					}
				}
		    }
		    
		    ISOMLayout<String, String> layout = new ISOMLayout<String,String>(graph);
		    
		    shape = new Transformer<String, Integer>() {
		    	public Integer transform(String vertex) {
		    		int stringLength = 0;
		    		for(Node node : nodesList) {
	    				if(vertex.equals(node.getIdentifier())) {
	    					stringLength = node.getIdentifier().length();
	    				}
	        		}
		    		return stringLength;
		    	}
			};
	        
	        vertexPaint = new Transformer<String,Paint>() {
	        	public Paint transform(String vertex) {
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
	    					}
	    				}
	        		}
	        		return Color.magenta;
	        	}
	        };
	        
	        edgeLabel = new Transformer<String, String>() {
	        	public String transform(String edge) {
	        		return edge.substring(0, edge.indexOf(":"));
	        	}
			};
	        
	        VertexShapeSizeAspect<String> vssa = new VertexShapeSizeAspect<String>(graph, shape);
	        
	        vv =  new VisualizationViewer<String,String>(layout, new Dimension(700,220));
	        
	        vv.setBackground(Color.white);
	        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());
	        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
	        vv.getRenderContext().setEdgeLabelTransformer(edgeLabel);
	        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
	        vv.setVertexToolTipTransformer(new ToStringLabeller<String>());
	        vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));
	        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
	        vv.getRenderContext().setVertexShapeTransformer(vssa);
	        vv.getRenderContext().setEdgeLabelClosenessTransformer(new ConstantDirectionalEdgeValueTransformer(0.5, 0.5));
	        vssa.setScaling(true);
	        
	        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
	        this.add(panel);
	        
	        final DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();

	        vv.setGraphMouse(graphMouse);
	        vv.addKeyListener(graphMouse.getModeKeyListener());

	        JComboBox modeBox = graphMouse.getModeComboBox();
	        modeBox.addItemListener(graphMouse.getModeListener());
	        graphMouse.setMode(ModalGraphMouse.Mode.PICKING);

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
	        
	        String path = "src/snepsui/Interface/resources/icons/";
	        
	        JButton colors = new JButton(new ImageIcon(path + "colors.png"));
	        colors.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame popupFrame = new JFrame("Node Colors");
					popupFrame.setLocation(450, 350);
					popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					popupFrame.getContentPane().add(new NodeColors());
					popupFrame.pack();
					popupFrame.setResizable(false);
					popupFrame.setVisible(true);
				}
			});

	        JPanel scaleGrid = new JPanel(new GridLayout(1,0));
	        scaleGrid.setBorder(BorderFactory.createTitledBorder("Zoom"));

	        JPanel controls = new JPanel();
	        scaleGrid.add(plus);
	        scaleGrid.add(minus);
	        controls.add(scaleGrid);
	        controls.add(modeBox);
	        controls.add(colors);
	        this.add(controls, BorderLayout.SOUTH);
	        
	        this.validate();
	        this.repaint();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, 
					"The case frame " + caseframe.getId() + " is not used in the current network", 
					"", 
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	class RelationEdge {
		String name;
		
		public RelationEdge(String name) {
			this.name = name;
		}
		
		public String toString() {
			return name + ":" + id++;
		}
	}
	
	private final static class VertexShapeSizeAspect<String>
    extends AbstractVertexShapeTransformer <String>
    implements Transformer<String,Shape>  {
    	
        protected boolean stretch = false;
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
		            	if(shapeSize == 2 || shapeSize == 1) {
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
            setAspectRatioTransformer(new Transformer<String,Float>() {

				public Float transform(String v) {
		            if (stretch) {
		                return (float)(graph.inDegree(v) + 1) / 
		                	(graph.outDegree(v) + 1);
		            } else {
		                return 1.0f;
		            }
				}});
        }
        
        public void setScaling(boolean scale)
        {
            this.scale = scale;
        }
        
        public Shape transform(String v)
        {
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
	
	/**
	 * Converts a given file to a JPEG image
	 * @param file the file to be converted
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
	
	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}
	
	public VisualizationViewer<String, String> getVv() {
		return vv;
	}

	public void setVv(VisualizationViewer<String, String> vv) {
		this.vv = vv;
	}
}