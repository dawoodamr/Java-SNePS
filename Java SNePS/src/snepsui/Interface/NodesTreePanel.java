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

import sneps.CaseFrame;
import sneps.CustomException;
import sneps.Network;
import sneps.Node;
import sneps.Relation;

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
	private String endLine = "----------------------------------------" + "\n";

	public NodesTreePanel(SNePSInterface frame) {
		super();
		this.frame = frame;
		initGUI();
	}
	
	public void initGUI() {
		try {
			this.removeAll();
			setPreferredSize(new Dimension(144, 658));
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setPreferredSize(new java.awt.Dimension(139, 351));
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
					jTree1.setPreferredSize(new java.awt.Dimension(98, 308));
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

	/**
	 * Gets all the nodes, relations and case frames and adds them to the tree
	 */
	public void addTreeInfo() {
		
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
	
	/**
	 * Checks the type of the selected tree node and then retrieves it from the network, displays
	 * all its information in the output panel and displays this part of the network in the visualize
	 * network panel
	 * @param evt the event that was fired on a tree selection
	 */
	private void jTree1ValueChanged(TreeSelectionEvent evt) {
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
		if(treeNode.getParent().toString().equals("Nodes")) {
			try {
				Node node = network.getNode(treeNode.toString());
				frame.getsNePSULPanel1().getMenuDrivenCommands().nodeInfo(node);
				
				frame.getsNePSULPanel1().getVisualizeNetworks().removeAll();
				frame.getsNePSULPanel1().getVisualizeNetworks().drawNode(node);
				frame.getsNePSULPanel1().getjTabbedPane1().setSelectedIndex(2);
				frame.getsNePSULPanel1().getVisualizeNetworks().validate();
				frame.getsNePSULPanel1().getVisualizeNetworks().repaint();
				frame.getsNePSULPanel1().setBounds(156, 1, 815, 366);
				frame.getsNePSULPanel1().getjTabbedPane1().setPreferredSize(new Dimension(815, 366));
				frame.getsNePSULPanel1().getjTabbedPane1().getComponent(0).setPreferredSize(new Dimension(815, 366));
				frame.getsNePSULPanel1().getjTabbedPane1().getComponent(0).validate();
				frame.getsNePSULPanel1().getjTabbedPane1().getComponent(0).repaint();
				frame.getOutputPanel1().setVisible(true);
				frame.getTracingPanel1().setVisible(true);
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
				String path = frame.getsNePSULPanel1().getMenuDrivenCommands().createPath(relation.getPath());
				if(!path.isEmpty())
					frame.getOutputPanel1().writeToTextArea("Relation Path: " + path + newLine);
				
				frame.getOutputPanel1().writeToTextArea(endLine);
				
				frame.getsNePSULPanel1().getVisualizeNetworks().removeAll();
				frame.getsNePSULPanel1().getVisualizeNetworks().drawRelation(relation);
				frame.getsNePSULPanel1().getjTabbedPane1().setSelectedIndex(2);
				frame.getsNePSULPanel1().getVisualizeNetworks().validate();
				frame.getsNePSULPanel1().getVisualizeNetworks().repaint();
				frame.getsNePSULPanel1().setBounds(156, 1, 815, 366);
				frame.getsNePSULPanel1().getjTabbedPane1().setPreferredSize(new Dimension(815, 366));
				frame.getsNePSULPanel1().getjTabbedPane1().getComponent(0).setPreferredSize(new Dimension(815, 366));
				frame.getsNePSULPanel1().getjTabbedPane1().getComponent(0).validate();
				frame.getsNePSULPanel1().getjTabbedPane1().getComponent(0).repaint();
				frame.getOutputPanel1().setVisible(true);
				frame.getTracingPanel1().setVisible(true);
				frame.getMainFrame().validate();
				frame.getMainFrame().repaint();
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
				frame.getOutputPanel1().writeToTextArea(endLine);
				
				frame.getsNePSULPanel1().getVisualizeNetworks().removeAll();
				frame.getsNePSULPanel1().getVisualizeNetworks().drawCaseFrame(caseframe);
				frame.getsNePSULPanel1().getjTabbedPane1().setSelectedIndex(2);
				frame.getsNePSULPanel1().getVisualizeNetworks().validate();
				frame.getsNePSULPanel1().getVisualizeNetworks().repaint();
				frame.getsNePSULPanel1().setBounds(156, 1, 815, 366);
				frame.getsNePSULPanel1().getjTabbedPane1().setPreferredSize(new Dimension(815, 366));
				frame.getsNePSULPanel1().getjTabbedPane1().getComponent(0).setPreferredSize(new Dimension(815, 366));
				frame.getsNePSULPanel1().getjTabbedPane1().getComponent(0).validate();
				frame.getsNePSULPanel1().getjTabbedPane1().getComponent(0).repaint();
				frame.getOutputPanel1().setVisible(true);
				frame.getTracingPanel1().setVisible(true);
				frame.getMainFrame().validate();
				frame.getMainFrame().repaint();
			} catch (CustomException e) {
				e.printStackTrace();
			}
		} else {
			return;
		}
	}
	
	/**
	 * @param network the current SNePS network
	 */
	public void setNetwork(Network network) {
		this.network = network;
	}
	
	/**
	 * @return the current SNePS network
	 */
	public Network getNetwork() {
		return network;
	}
}