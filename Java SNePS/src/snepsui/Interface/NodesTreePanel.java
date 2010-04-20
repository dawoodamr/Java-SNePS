package snepsui.Interface;

import java.awt.Dimension;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import javax.swing.JScrollPane;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import sneps.Cable;
import sneps.CaseFrame;
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
public class NodesTreePanel extends javax.swing.JPanel {
	private JTree jTree1;
	private DefaultMutableTreeNode top;
	private DefaultMutableTreeNode nodes;
	private DefaultMutableTreeNode relations;
	private DefaultMutableTreeNode caseframes;
	private Network network;
	private SNePSInterface frame;
	private JScrollPane jScrollPane1;
	private String newLine = "\n";

	public NodesTreePanel(SNePSInterface frame) {
		super();
		this.frame = frame;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(144, 658));
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setPreferredSize(new java.awt.Dimension(139, 584));
				{
					top = new DefaultMutableTreeNode("Network");
					
					nodes = new DefaultMutableTreeNode("Nodes");
					top.add(nodes);
					relations = new DefaultMutableTreeNode("Relations");
					top.add(relations);
					caseframes = new DefaultMutableTreeNode("Case Frames");
					top.add(caseframes);
					
					jTree1 = new JTree(top);
					jScrollPane1.setViewportView(jTree1);
					jTree1.setRootVisible(true);
					jTree1.setShowsRootHandles(true);
					jTree1.setPreferredSize(new java.awt.Dimension(100, 526));
					jTree1.addTreeSelectionListener(new TreeSelectionListener() {
						public void valueChanged(TreeSelectionEvent evt) {
							jTree1ValueChanged(evt);
						}
					});
					
					if(network!=null)
						addTreeInfo();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addTreeInfo() {
		nodes.removeAllChildren();
	    relations.removeAllChildren();
	    caseframes.removeAllChildren();
		
		String str1 = "";
		Hashtable<String, Node> networkNodes = network.getNodes();
		Set<String> set1 = networkNodes.keySet();

	    Iterator<String> itr1 = set1.iterator();
	    
	    while (itr1.hasNext()) {
	      str1 = itr1.next();
    	  DefaultMutableTreeNode node = new DefaultMutableTreeNode(networkNodes.get(str1).getIdentifier());
    	  nodes.add(node);
	    }
	    
	    String str2 = "";
		Hashtable<String, Relation> networkRelations = network.getRelations();
		Set<String> set2 = networkRelations.keySet();

	    Iterator<String> itr2 = set2.iterator();
	    
	    while (itr2.hasNext()) {
	      str2 = itr2.next();
    	  DefaultMutableTreeNode relation = new DefaultMutableTreeNode(networkRelations.get(str2).getName());
    	  relations.add(relation);
	    }
	    
	    String str3 = "";
		Hashtable<String, CaseFrame> networkCaseframes = network.getCaseFrames();
		Set<String> set3 = networkCaseframes.keySet();

	    Iterator<String> itr = set3.iterator();
	    
	    while (itr.hasNext()) {
	      str3 = itr.next();
    	  DefaultMutableTreeNode caseframe = new DefaultMutableTreeNode(networkCaseframes.get(str3).getId());
    	  caseframes.add(caseframe);
	    }
	    
	    frame.getMainFrame().validate();
	    frame.getMainFrame().repaint();
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public Network getNetwork() {
		return network;
	}
	
	private void jTree1ValueChanged(TreeSelectionEvent evt) {
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
		if(treeNode.getParent().toString().equals("Nodes")) {
			try {
				Node node = network.getNode(treeNode.toString());
				
				//Print out node name
				frame.getOutputPanel1().writeToTextArea("Node Name: " + node.getIdentifier() + newLine);
				
				//Print out semantic class
				node.getEntity();
				
				//Print out cable
				if(node.getClass().getSuperclass().equals("MolecularNode")) {
					MolecularNode molNode = (MolecularNode) network.getNode(treeNode.toString());
					LinkedList<Cable> cables = molNode.getCableSet().getCables();
					
					frame.getOutputPanel1().writeToTextArea("Cable Set (Nodes pointing from " + node.getIdentifier() + "):" + newLine);
					for(Cable item1 : cables) {
						item1.getRelation();
						LinkedList<Node> nodes = item1.getNodeSet().getNodes();
						
						for(Node item2 : nodes) {
							frame.getOutputPanel1().writeToTextArea(item2.getIdentifier() + newLine);
						}
					}
				}
				
				//Print out up cable
				LinkedList<UpCable> nodeUpCables = node.getUpCableSet().getUpCables();
				
				frame.getOutputPanel1().writeToTextArea("Up Cable Set (Nodes pointing to " + node.getIdentifier() + "): " + newLine);
				for(UpCable item3 : nodeUpCables) {
					item3.getRelation();
					LinkedList<Node> nodes = item3.getNodeSet().getNodes();
					
					for(Node item4 : nodes) {
						frame.getOutputPanel1().writeToTextArea(item4.getIdentifier() + newLine);
					}
				}
				
				frame.getsNePSULPanel1().getVisualizeNetworks().drawNode(node);
				frame.getsNePSULPanel1().getjTabbedPane1().setSelectedIndex(2);
				frame.getsNePSULPanel1().getVisualizeNetworks().validate();
				frame.getsNePSULPanel1().getVisualizeNetworks().repaint();
				frame.getMainFrame().validate();
				frame.getMainFrame().repaint();
			} catch (CustomException e) {
				e.printStackTrace();
			}
		} else if (treeNode.getParent().toString().equals("Relations")) {
			try {
				Relation relation = network.getRelation(treeNode.toString());
				frame.getOutputPanel1().writeToTextArea("Relation Name: " + relation.getName() + newLine);
				frame.getOutputPanel1().writeToTextArea("Relation Type: " + relation.getType() + newLine);
				frame.getOutputPanel1().writeToTextArea("Relation Adjustability: " + relation.getAdjust() + newLine);
				frame.getOutputPanel1().writeToTextArea("Relation Limit: " + relation.getLimit() + newLine);
				relation.getPath();
				
				//frame.getsNePSULPanel1().getVisualizeNetworks().drawRelation(relation);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		} else if (treeNode.getParent().toString().equals("Case Frames")) {
			try {
				CaseFrame caseframe = network.getCaseFrame(treeNode.toString());
				frame.getOutputPanel1().writeToTextArea("Case Frame ID: " + caseframe.getId() + newLine);
				frame.getOutputPanel1().writeToTextArea("Case Frame Semantic Class: " + caseframe.getSemanticClass() + newLine);
				
				frame.getOutputPanel1().writeToTextArea("Case Frame Relations:" + newLine);
				LinkedList<Relation> relations = caseframe.getRelations();
				for(Relation relation : relations) {
					frame.getOutputPanel1().writeToTextArea(relation.getName() + newLine);
				}
				
				//frame.getsNePSULPanel1().getVisualizeNetworks().drawCaseFrame(caseframe);
			} catch (CustomException e) {
				e.printStackTrace();
			}
		} else {
			return;
		}
	}
}
