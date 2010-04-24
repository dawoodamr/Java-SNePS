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
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			node.getEntity();
			
			//Print out cable
			if(node instanceof MolecularNode) {
				MolecularNode molNode = (MolecularNode) network.getNode(node.getIdentifier());
				LinkedList<Cable> cables = molNode.getCableSet().getCables();
				
				if(!cables.isEmpty()) {
					frame.getOutputPanel1().writeToTextArea("Cable Set (Nodes pointing from " + node.getIdentifier() + "):" + newLine);
					for(Cable item1 : cables) {
						item1.getRelation();
						LinkedList<Node> nodes = item1.getNodeSet().getNodes();
						
						for(Node item2 : nodes) {
							frame.getOutputPanel1().writeToTextArea(item2.getIdentifier() + newLine);
						}
					}
				}
			}
			
			//Print out up cable
			LinkedList<UpCable> nodeUpCables = node.getUpCableSet().getUpCables();
			
			if(!nodeUpCables.isEmpty()) {
				frame.getOutputPanel1().writeToTextArea("Up Cable Set (Nodes pointing to " + node.getIdentifier() + "): " + newLine);
				for(UpCable item3 : nodeUpCables) {
					item3.getRelation();
					LinkedList<Node> nodes = item3.getNodeSet().getNodes();
					
					for(Node item4 : nodes) {
						frame.getOutputPanel1().writeToTextArea(item4.getIdentifier() + newLine);
					}
				}
			}
			
			frame.getOutputPanel1().writeToTextArea(endLine);
			
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}
}
