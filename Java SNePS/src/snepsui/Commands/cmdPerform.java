package snepsui.Commands;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ActionMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import snactor.Act;
import sneps.Entity;
import sneps.Network;
import sneps.Node;
import snepsui.Interface.SNePSInterface;

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
public class cmdPerform extends javax.swing.JPanel {
	private JLabel performLabel;
	private JScrollPane jScrollPane1;
	private JComboBox nodesComboBox;
	private JLabel Nodeset;
	private JComboBox contextComboBox;
	private JList nodesetList;
	private JLabel nodesetLabel;
	private JLabel relationLabel;
	private JButton addButton;
	private JButton doneButton;
	private JButton infoButton;
	private JLabel contextNameLabel;
	private DefaultListModel relationModel;
	private DefaultListModel nodesetModel;
	private Network network;
	private SNePSInterface frame;

	@Action
    public void add() {
    	
    }
	
	@Action
    public void build() {
    	
    }
	
	@Action
    public void info() {
    	
    }
	
	private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }

	public cmdPerform(Network network, SNePSInterface frame) {
		super();
		this.frame = frame;
		this.network = network;
		initGUI();
	}

	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				performLabel = new JLabel();
				this.add(performLabel);
				performLabel.setName("performLabel");
				performLabel.setBounds(174, 31, 55, 15);
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(245, 56, 188, 110);
				{
					nodesetModel = new DefaultListModel();
					nodesetList = new JList();
					jScrollPane1.setViewportView(nodesetList);
					nodesetList.setModel(nodesetModel);
					nodesetList.setBounds(59, 166, 180, 107);
				}
			}
			{
				relationLabel = new JLabel();
				this.add(relationLabel);
				relationLabel.setBounds(116, 7, 46, 15);
				relationLabel.setName("relationLabel");
			}
			{
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setBounds(301, 7, 48, 15);
				nodesetLabel.setName("nodesetLabel");
			}
			{
				doneButton = new JButton();
				this.add(doneButton);
				doneButton.setBounds(314, 185, 77, 29);
				doneButton.setName("doneButton");
			}
			{
				infoButton = new JButton();
				this.add(infoButton);
				infoButton.setBounds(668, 196, 16, 18);
				infoButton.setAction(getAppActionMap().get("info"));
				infoButton.setFocusable(false);
				infoButton.setFocusPainted(false);
				infoButton.setBorderPainted(false);
				infoButton.setContentAreaFilled(false);
				infoButton.setMargin(new Insets(0,0,0,0));
				infoButton.setToolTipText("info");
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(438, 30, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						addButtonMouseClicked(evt);
					}
				});
			}
			{
				contextNameLabel = new JLabel();
				this.add(contextNameLabel);
				contextNameLabel.setName("contextNameLabel");
				contextNameLabel.setBounds(483, 4, 123, 21);
			}
			{
				ComboBoxModel contextComboBoxModel = new DefaultComboBoxModel();
				contextComboBox = new JComboBox();
				this.add(contextComboBox);
				contextComboBox.setModel(contextComboBoxModel);
				contextComboBox.setBounds(483, 30, 147, 22);
			}
			{
				Nodeset = new JLabel();
				this.add(Nodeset);
				Nodeset.setBounds(247, 7, 79, 15);
				Nodeset.setName("Nodeset");
			}
			{
				DefaultComboBoxModel nodesComboBoxModel = new DefaultComboBoxModel();
				
				String str = "";
				Hashtable<String, Node> nodes = network.getNodes();
				Set<String> set = nodes.keySet();

			    Iterator<String> itr = set.iterator();
			    while (itr.hasNext()) {
			      str = itr.next();
			      Node node = nodes.get(str);
			      Entity entity = node.getEntity();
			      if (entity instanceof Act) {
			    	  nodesComboBoxModel.addElement(node.getIdentifier()) ;
			      }
			    }
			    
				nodesComboBox = new JComboBox();
				this.add(nodesComboBox);
				nodesComboBox.setModel(nodesComboBoxModel);
				nodesComboBox.setBounds(247, 27, 185, 22);
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {}
	}
	
	private void addButtonMouseClicked(MouseEvent evt) {
		relationModel.addElement(nodesComboBox.getSelectedItem().toString());
		nodesComboBox.setSelectedIndex(0);
		validate();
	}
}
