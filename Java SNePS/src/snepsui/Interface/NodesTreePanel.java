package snepsui.Interface;

import java.awt.Dimension;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

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
	private DefaultMutableTreeNode node1;
	private DefaultMutableTreeNode node2;
	private DefaultMutableTreeNode relation1;
	private DefaultMutableTreeNode relation2;
	
	public NodesTreePanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(144, 658));
			{
				top = new DefaultMutableTreeNode("Network");
				nodes = new DefaultMutableTreeNode("Nodes");
				top.add(nodes);
				relations = new DefaultMutableTreeNode("Relations");
				top.add(relations);
				
				node1 = new DefaultMutableTreeNode("Node 1");
				nodes.add(node1);
				node2 = new DefaultMutableTreeNode("Node 2");
				nodes.add(node2);
				
				relation1 = new DefaultMutableTreeNode("Relation 1");
				relations.add(relation1);
				relation2 = new DefaultMutableTreeNode("Relation 2");
				relations.add(relation2);
				
				jTree1 = new JTree(top);
				this.add(jTree1);
				jTree1.setRootVisible(true);
				jTree1.setShowsRootHandles(true);
				jTree1.setPreferredSize(new java.awt.Dimension(136, 581));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
