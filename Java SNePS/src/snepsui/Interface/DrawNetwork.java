package snepsui.Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.imageio.ImageIO;
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

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.TransformerUtils;
import org.apache.commons.collections15.functors.MapTransformer;
import org.apache.commons.collections15.map.LazyMap;

import sneps.CaseFrame;
import sneps.CustomException;
import sneps.Network;
import sneps.Node;
import sneps.Relation;
import snepsui.Commands.cmdCaseFrame;
import snepsui.Commands.cmdDefine;
import snepsui.Commands.cmdUndefine;
import snepsui.Commands.cmdUndefineCaseFrame;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.VisualizationViewer.GraphMouse;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
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
public class DrawNetwork extends javax.swing.JPanel {

    private DirectedOrderedSparseMultigraph<String, String> graph;
    private StaticLayout<String, String> layout;
    private VisualizationViewer<String, String> vv;
    private JComboBox caseframeComboBox;
    private JComboBox relationsComboBox;
    private SNePSInterface frame;
    private Network network;
    private Hashtable<String, CaseFrame> molNodes;
    
    String instructions =
        "<html>"+
        "<h3>All Modes:</h3>"+
        "<ul>"+
        "<li>Right-click an empty area for <b>Create Vertex</b> popup"+
        "<li>Right-click on a Vertex for <b>Delete Vertex</b> popup"+
        "<li>Right-click on a Vertex for <b>Add Edge</b> menus <br>(if there are selected Vertices)"+
        "<li>Right-click on an Edge for <b>Delete Edge</b> popup"+
        "<li>Mousewheel scales with a crossover value of 1.0.<p>"+
        "     - scales the graph layout when the combined scale is greater than 1<p>"+
        "     - scales the graph view when the combined scale is less than 1"+

        "</ul>"+
        "<h3>Editing Mode:</h3>"+
        "<ul>"+
        "<li>Left-click an empty area to create a new Vertex"+
        "<li>Left-click on a Vertex and drag to another Vertex to create an Undirected Edge"+
        "<li>Shift+Left-click on a Vertex and drag to another Vertex to create a Directed Edge"+
        "</ul>"+
        "<h3>Picking Mode:</h3>"+
        "<ul>"+
        "<li>Mouse1 on a Vertex selects the vertex"+
        "<li>Mouse1 elsewhere unselects all Vertices"+
        "<li>Mouse1+Shift on a Vertex adds/removes Vertex selection"+
        "<li>Mouse1+drag on a Vertex moves all selected Vertices"+
        "<li>Mouse1+drag elsewhere selects Vertices in a region"+
        "<li>Mouse1+Shift+drag adds selection of Vertices in a new region"+
        "<li>Mouse1+CTRL on a Vertex selects the vertex and centers the display on it"+
        "<li>Mouse1 double-click on a vertex or edge allows you to edit the label"+
        "</ul>"+
        "<h3>Transforming Mode:</h3>"+
        "<ul>"+
        "<li>Mouse1+drag pans the graph"+
        "<li>Mouse1+Shift+drag rotates the graph"+
        "<li>Mouse1+CTRL(or Command)+drag shears the graph"+
        "<li>Mouse1 double-click on a vertex or edge allows you to edit the label"+
        "</ul>"+
        "<h3>Annotation Mode:</h3>"+
        "<ul>"+
        "<li>Mouse1 begins drawing of a Rectangle"+
        "<li>Mouse1+drag defines the Rectangle shape"+
        "<li>Mouse1 release adds the Rectangle as an annotation"+
        "<li>Mouse1+Shift begins drawing of an Ellipse"+
        "<li>Mouse1+Shift+drag defines the Ellipse shape"+
        "<li>Mouse1+Shift release adds the Ellipse as an annotation"+
        "<li>Mouse3 shows a popup to input text, which will become"+
        "<li>a text annotation on the graph at the mouse location"+
        "</ul>"+
        "</html>";
    
    /**
     * @param frame The main frame where all the panels are included which is SNePSInterface
     */
    public DrawNetwork(SNePSInterface frame) {
    	this.frame = frame;
    	this.setPreferredSize(new Dimension(815, 600));
    	
        graph = new DirectedOrderedSparseMultigraph<String, String>();

        this.layout = new StaticLayout<String,String>(graph, 
        	new Dimension(700,450));
        
        vv =  new VisualizationViewer<String,String>(layout, new Dimension(700,470));
        vv.setBackground(Color.white);

        vv.getRenderContext().setVertexLabelTransformer(MapTransformer.<String,String>getInstance(
        		LazyMap.<String,String>decorate(new HashMap<String,String>(), new ToStringLabeller<String>())));
        vv.getRenderContext().setEdgeLabelTransformer(MapTransformer.<String,String>getInstance(
        		LazyMap.<String,String>decorate(new HashMap<String,String>(), new ToStringLabeller<String>())));
        vv.setVertexToolTipTransformer(vv.getRenderContext().getVertexLabelTransformer());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        
        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
        this.add(panel);
        Factory<String> vertexFactory = new VertexFactory();
        Factory<String> edgeFactory = new EdgeFactory();
        
        final EditingModalGraphMouse<String,String> graphMouse = 
        	new EditingModalGraphMouse<String,String>(vv.getRenderContext(), vertexFactory, edgeFactory);
        
        // the EditingGraphMouse will pass mouse event coordinates to the
        // vertexLocations function to set the locations of the vertices as
        // they are created
//        Transformer vertexLocations = TransformerUtils.mapTransformer(map);
//        graphMouse.setVertexLocations(vertexLocations);
        vv.setGraphMouse(graphMouse);
        vv.addKeyListener(graphMouse.getModeKeyListener());
      
        graphMouse.setMode(ModalGraphMouse.Mode.EDITING);
        
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
        
        JButton help = new JButton("Help");
        help.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(vv, instructions);
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
        //controls.add(help);
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
						network.build(nodeName);
					}
				} else if (s.equals("Molecular Node")) {
					LinkedList<Relation> relations = new LinkedList<Relation>();
					LinkedList<Relation> cablesetRelations = new LinkedList<Relation>();
					LinkedList<Node> cablesetNodes = new LinkedList<Node>();
					String relation = "";
					String caseframe = "";
					String node = "";
					String caseframeStr = "";
					int caseframeCounter = 0;
					int relationCounter = 0;
					int nodeCounter = 0;
					
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
								"Choose the Node you want to create:",
								"Create a Node",
								JOptionPane.OK_OPTION,
								icon,
								caseframePossibilities,
								caseframePossibilities[0]);
					
					//Get relations ins case frame
					try {
						relations = network.getCaseFrame(caseframe).getRelations();
					} catch (CustomException e) {
						e.printStackTrace();
					}
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
					
					cablesetRelations.add(network.getRelation(relation));
					
					//Get nodes
					Collection<String> nodes = graph.getVertices();
					Object [] nodesPossibilities = new Object[nodes.size()]; 
					
				    for (String item : nodes) {
				    	nodesPossibilities[nodeCounter] = item;
				    	nodeCounter++;
				    }
				    
				    node = (String)JOptionPane.showInputDialog(
							getRootPane(),
							"Choose the relation:",
							"Choose a Relation",
							JOptionPane.OK_OPTION,
							icon,
							nodesPossibilities,
							nodesPossibilities[0]);
				    
				    cablesetNodes.add(network.getNode(node));
				    
				    boolean flag = true;
					while (flag) {
						int result = JOptionPane.showConfirmDialog(
								getRootPane(),
								"Do you want to connect another node?",
								"Create Molecular Node",
								JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.NO_OPTION) {
							flag = false;
						} else if (result == JOptionPane.YES_OPTION) {
							relation = (String)JOptionPane.showInputDialog(
									getRootPane(),
									"Choose the relation:",
									"Choose a Relation",
									JOptionPane.OK_OPTION,
									icon,
									relationsPossibilities,
									relationsPossibilities[0]);
							cablesetRelations.add(network.getRelation(relation));
							
							node = (String)JOptionPane.showInputDialog(
									getRootPane(),
									"Choose the relation:",
									"Choose a Relation",
									JOptionPane.OK_OPTION,
									icon,
									nodesPossibilities,
									nodesPossibilities[0]);
							cablesetNodes.add(network.getNode(node));
						}
					}
					
					Object [][] cableset = new Object[cablesetRelations.size()][2];
					for(int i = 0; i < cablesetRelations.size(); i++) {
						cableset[i][0] = cablesetRelations.get(i);
						cableset[i][1] = cablesetNodes.get(i);
					}
					
					Node molNode = network.build(cableset, network.getCaseFrame(caseframe));
					nodeName = molNode.getIdentifier();
					
					for(int i = 0; i < cablesetRelations.size(); i++) {
						graph.addEdge(cablesetRelations.get(i).getName(),
								nodeName, 
								cablesetNodes.get(i).getIdentifier());
					}
						
				} else if (s.equals("Variable Node")) {
					boolean flag = true;
					
					while(flag) {
						nodeName = (String)JOptionPane.showInputDialog(
								getRootPane(),
								"Node Name:",
								"Create a Variable Node",
								JOptionPane.PLAIN_MESSAGE);
						if(nodeName.equals(JOptionPane.CANCEL_OPTION)) {
							return null;
						} else {
							if(nodeName.startsWith("$")) {
								network.buildVariableNode(nodeName);
								flag = false;
							}	
							else {
								JOptionPane.showMessageDialog(getRootPane(),
						    			  "A variabel node has to start with the sign $",
						    			  "Error",
						    			  JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				
			} 
//			catch (CustomException e) {
//				JOptionPane.showMessageDialog(getRootPane(),
//		    			  "The node " + nodeName + "already exits",
//		    			  "Error",
//		    			  JOptionPane.ERROR_MESSAGE);
//				e.printStackTrace();
//			}
			return nodeName;
		}
    }
    
    /**
     * This class creates an edge when a user clicks on a vertex and connects it to another vertex
     */
    class EdgeFactory implements Factory<String> {
    	
    	/**
    	 * This method creates edges and the case frames for the built node is chose here
    	 * @return the name of the relation
    	 */
		public String create() {
//			String relation = "";
//			String caseframe = "";
//			String caseframeStr = "";
//			int caseframeCounter = 0;
//			int relationCounter = 0;
//			Icon icon = new ImageIcon();
//			
//			Hashtable<String, CaseFrame> caseframes = network.getCaseFrames();
//			Object [] caseframePossibilities = new Object[caseframes.size()]; 
//			Set<String> caseframeSet = caseframes.keySet();
//
//		    Iterator<String> caseframeItr = caseframeSet.iterator();
//		    while (caseframeItr.hasNext()) {
//		    	caseframeStr = caseframeItr.next();
//		    	caseframePossibilities[caseframeCounter] = caseframes.get(caseframeStr).getId();
//		    	caseframeCounter++;
//		    }
//			
//			caseframe = (String) JOptionPane.showInputDialog(
//						getRootPane(),
//						"Choose the Node you want to create:",
//						"Create a Node",
//						JOptionPane.OK_OPTION,
//						icon,
//						caseframePossibilities,
//						caseframePossibilities[0]);
//			
//			LinkedList<Relation> relations = new LinkedList<Relation>();
//			try {
//				relations = network.getCaseFrame(caseframe).getRelations();
//			} catch (CustomException e) {
//				e.printStackTrace();
//			}
//			Object [] relationsPossibilities = new Object[relations.size()]; 
//
//		    for (Relation item : relations) {
//		      relationsPossibilities[relationCounter] = item.getName();
//		      relationCounter++;
//		    }
//		    
//			relation = (String)JOptionPane.showInputDialog(
//			getRootPane(),
//			"Choose the relation:",
//			"Choose a Relation",
//			JOptionPane.OK_OPTION,
//			icon,
//			relationsPossibilities,
//			relationsPossibilities[0]);
//			return relation;
			return "";
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}
}