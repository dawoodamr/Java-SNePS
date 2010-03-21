package SNePS.SNePSUI.Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.functors.MapTransformer;
import org.apache.commons.collections15.map.LazyMap;

import SNePS.SNePSUI.Commands.cmdCaseFrame;
import SNePS.SNePSUI.Commands.cmdDefine;
import SNePS.SNePSUI.Commands.cmdFind;
import SNePS.SNePSUI.Commands.cmdUndefine;
import SNePS.SNePSUI.Commands.cmdUndefineCaseFrame;
import SNePS.NetworkManagementSystem.CustomException;
import SNePS.NetworkManagementSystem.Network;
import SNePS.NetworkManagementSystem.Node;
import SNePS.NetworkManagementSystem.Relation;

import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.annotations.AnnotationControls;
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
public class DrawNetwork extends javax.swing.JPanel {

	/**
     * the graph
     */
    DirectedOrderedSparseMultigraph<String, String> graph;
    
    StaticLayout<String, String> layout;

    /**
     * the visual component and renderer for the graph
     */
    VisualizationViewer<String, String> vv;
    
    Network network;
    
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
     * create an instance of a simple graph with popup controls to
     * create a graph.
     */
    public DrawNetwork(final Network network) {
        this.network = network;
    	this.setPreferredSize(new Dimension(850, 800));
        // create a simple graph for the demo
        graph = new DirectedOrderedSparseMultigraph<String, String>();

        this.layout = new StaticLayout<String,String>(graph, 
        	new Dimension(600,600));
        
        vv =  new VisualizationViewer<String,String>(layout);
        vv.setBackground(Color.white);

        vv.getRenderContext().setVertexLabelTransformer(MapTransformer.<String,String>getInstance(
        		LazyMap.<String,String>decorate(new HashMap<String,String>(), new ToStringLabeller<String>())));
        vv.getRenderContext().setEdgeLabelTransformer(MapTransformer.<String,String>getInstance(
        		LazyMap.<String,String>decorate(new HashMap<String,String>(), new ToStringLabeller<String>())));
        vv.setVertexToolTipTransformer(vv.getRenderContext().getVertexLabelTransformer());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        

        //Container content = getRootPane();
        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
        this.add(panel);
        Factory<String> vertexFactory = new VertexFactory();
        Factory<String> edgeFactory = new EdgeFactory();
        
        final EditingModalGraphMouse<String,String> graphMouse = 
        	new EditingModalGraphMouse<String,String>(vv.getRenderContext(), vertexFactory, edgeFactory);
        
        // the EditingGraphMouse will pass mouse event coordinates to the
        // vertexLocations function to set the locations of the vertices as
        // they are created
        //Transformer vertexLocations = TransformerUtils.mapTransformer(map);
        //graphMouse.setVertexLocations(vertexLocations);
        vv.setGraphMouse(graphMouse);
        vv.addKeyListener(graphMouse.getModeKeyListener());

        graphMouse.setMode(ModalGraphMouse.Mode.EDITING);
        
        final ScalingControl scaler = new CrossoverScalingControl();
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
        
        JButton help = new JButton("Help");
        help.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(vv, instructions);
            }});
        
        JLabel caseframeLabel = new JLabel("Case Frames");
        DefaultComboBoxModel caseframeComboBoxModel =
        	new DefaultComboBoxModel(new String []{"define-caseframe","undefine-caseframe"});
        final JComboBox caseframeComboBox = new JComboBox();
        caseframeComboBox.setModel(caseframeComboBoxModel);
        caseframeComboBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	JFrame frame = new JFrame("Case Frames");
        		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                if (caseframeComboBox.getSelectedItem().toString().equals("define-caseframe")) {
            		frame.getContentPane().add(new cmdCaseFrame(network));
            		frame.pack();
            		frame.setVisible(true);
                } else if (caseframeComboBox.getSelectedItem().toString().equals("undefine-caseframe")) {
                	frame.getContentPane().add(new cmdUndefineCaseFrame(network));
            		frame.pack();
            		frame.setVisible(true);
                }
            }});
        
        JLabel relationsLabel = new JLabel("Relations");
        DefaultComboBoxModel relationsComboBoxModel =
        	new DefaultComboBoxModel(new String []{"define","undefine"});
        final JComboBox relationsComboBox = new JComboBox();
        relationsComboBox.setModel(relationsComboBoxModel);
        relationsComboBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	JFrame frame = new JFrame("Case Frames");
        		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                if (relationsComboBox.getSelectedItem().toString().equals("define")) {
            		frame.getContentPane().add(new cmdDefine(network));
            		frame.pack();
            		frame.setVisible(true);
                } else if (relationsComboBox.getSelectedItem().toString().equals("undefine")) {
                	frame.getContentPane().add(new cmdUndefine(network));
            		frame.pack();
            		frame.setVisible(true);
                }
            }});

//        AnnotationControls<String,String> annotationControls = 
//        	new AnnotationControls<String,String>(graphMouse.getAnnotatingPlugin());
        JPanel controls = new JPanel();
        controls.add(plus);
        controls.add(minus);
        controls.add(relationsLabel);
        controls.add(relationsComboBox);
        controls.add(caseframeLabel);
        controls.add(caseframeComboBox);
        JComboBox modeBox = graphMouse.getModeComboBox();
        controls.add(modeBox);
        //controls.add(annotationControls.getAnnotationsToolBar());
        controls.add(help);
        this.add(controls, BorderLayout.SOUTH);
    }
    
    /**
     * copy the visible part of the graph to a file as a jpeg image
     * @param file
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
    
    class VertexFactory implements Factory<String> {

    	int b = 0;
    	int m = 0;
    	int v = 0;

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
					b++;
					nodeName = "B" + b;
					network.build(nodeName);
					
				} else if (s.equals("Molecular Node")) {
					m++;
					nodeName = "M" + m;
					network.build(nodeName);
				} else if (s.equals("Variable Node")) {
					v++;
					nodeName = "V" + v;
					network.build(nodeName);
				}
			} catch (NullPointerException e) {
				
			} catch (CustomException e) {
				JOptionPane.showMessageDialog(getRootPane(),
		    			  "The node " + nodeName + "already exits",
		    			  "Error",
		    			  JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			return nodeName;
		}
    }
    
    class EdgeFactory implements Factory<String> {
    	
		public String create() {
			String relation = "";
			String str = "";
			int counter = 0;
			Icon icon = new ImageIcon();
			
			Hashtable<String, Relation> relations = network.getRelations();
			Object [] possibilities = new Object[relations.size()]; 
			Set<String> set = relations.keySet();

		    Iterator<String> itr = set.iterator();
		    while (itr.hasNext()) {
		      str = itr.next();
		      possibilities[counter] = relations.get(str).getName();
		      counter++;
		    }
			
			relation = (String)JOptionPane.showInputDialog(
			getRootPane(),
			"Choose the Node you want to create:",
			"Create a Node",
			JOptionPane.OK_OPTION,
			icon,
			possibilities,
			possibilities[0]);
			return relation;
		}
    }

    /**
     * a driver for this demo
     */
    @SuppressWarnings("serial")
	public static void main(String[] args) {
    	Network network = new Network();
    	try {
			network.defineRelation("Relation 1", "entity", "none", 1);
			network.defineRelation("Relation 2", "entity", "none", 1);
			network.defineRelation("Relation 3", "entity", "none", 1);
		} catch (CustomException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final DrawNetwork demo = new DrawNetwork(network);
        
        JMenu menu = new JMenu("File");
        menu.add(new AbstractAction("Make Image") {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser  = new JFileChooser();
                int option = chooser.showSaveDialog(demo);
                if(option == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    demo.writeJPEGImage(file);
                }
            }});
        
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(demo);
        frame.pack();
        frame.setVisible(true);
    }
}