package snepsui.Interface;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import sneps.Cable;
import sneps.CustomException;
import sneps.MolecularNode;
import sneps.Network;
import sneps.Node;
import sneps.Relation;
import sneps.UpCable;

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
@SuppressWarnings("unused")
public class ResultNodes extends javax.swing.JPanel {
	private JScrollPane jScrollPane1;
	private JList nodeList;
	private DefaultListModel nodeListModel;
	private SNePSInterface frame;
	private Network network;
	private String newLine = "\n";
	private String endLine = "----------------------------------------" + "\n";
	
	public ResultNodes(SNePSInterface frame) {
		super();
		this.frame = frame;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setPreferredSize(new java.awt.Dimension(131, 186));
				{
					nodeListModel = new DefaultListModel();
					nodeList = new JList();
					jScrollPane1.setViewportView(nodeList);
					nodeList.setModel(nodeListModel);
					nodeList.setPreferredSize(new java.awt.Dimension(95, 113));
					nodeList.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							nodeListMouseClicked(evt);
						}
					});
				}
			}
		} catch (Exception e) {}
	}
	
	public void addNodes(LinkedList<Node> nodes) {
		nodeListModel.removeAllElements();
		
		for(Node node : nodes) {
			nodeListModel.addElement(node.getIdentifier());
		}
		
		this.validate();
		this.repaint();
	}
	
	public void setNetwork(Network network) {
		this.network = network;
	}
	
	private void nodeListMouseClicked(MouseEvent evt) {
		try {
			Node node = network.getNode(nodeListModel.get(nodeList.getSelectedIndex()).toString());
			
			//Print out node name
			frame.getOutputPanel1().writeToTextArea("Node Name: " + node.getIdentifier() + newLine);
			
			//Print out semantic class
			frame.getOutputPanel1().writeToTextArea("Semantic Class: "+ node.getEntity().getClass().getSimpleName() + newLine);
			
			//Print out cable
			if(node instanceof MolecularNode) {
				MolecularNode molNode = (MolecularNode) network.getNode(node.getIdentifier());
				
				if(molNode.getCableSet().size() != 0) {
					frame.getOutputPanel1().writeToTextArea("Cable Set (Nodes pointing from " + node.getIdentifier() + "):" + newLine);
					for(int i = 0; i < molNode.getCableSet().size(); i++) {
						Cable cable = molNode.getCableSet().getCable(i);
						Relation relation = cable.getRelation();
						for(int j = 0; j < cable.getNodeSet().size(); j++) {
							Node cableNode = cable.getNodeSet().getNode(j);
							frame.getOutputPanel1().writeToTextArea("Relation: "+relation.getName()+" Node: "+cableNode.getIdentifier() + newLine);
						}
					}
				}
			}
			
			//Print out up cable
			
			if(node.getUpCableSet().size() != 0) {
				frame.getOutputPanel1().writeToTextArea("Up Cable Set (Nodes pointing to " + node.getIdentifier() + "):" + newLine);
				for(int i = 0; i < node.getUpCableSet().size(); i++) {
					UpCable upcable = node.getUpCableSet().getUpCable(i);
					Relation relation = upcable.getRelation();
					for(int j = 0; j < upcable.getNodeSet().size(); j++) {
						Node upcableNode = upcable.getNodeSet().getNode(j);
						frame.getOutputPanel1().writeToTextArea("Relation: "+relation.getName()+" Node: " + upcableNode.getIdentifier() + newLine);
					}
				}
			}
			
			frame.getOutputPanel1().writeToTextArea(endLine);
			
		} catch (CustomException e) {}
	}
}
