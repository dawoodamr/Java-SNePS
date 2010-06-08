package snepsui.Commands;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import sneps.CustomException;
import sneps.Network;
import sneps.Node;

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
public class cmdErase extends javax.swing.JPanel {
	private JLabel eraseLabel;
	private JButton addButton;
	private JList nodesetList;
	private JButton doneButton;
	private DefaultListModel nodesetModel;
	private JScrollPane jScrollPane1;
	private JComboBox nodesetComboBox;
	private JLabel nodesetLabel;
	private JButton infoButton;
	private Network network;
	
	@Action
    public void add() {
    	
    }
	
	@Action
    public void info() {
    	
    }
	
	private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }
	
	public cmdErase(Network network) {
		super();
		this.network = network;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				eraseLabel = new JLabel();
				this.add(eraseLabel);
				eraseLabel.setName("eraseLabel");
				eraseLabel.setBounds(184, 35, 59, 15);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(465, 34, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						addButtonMouseClicked(evt);
					}
				});
			}
			{
				doneButton = new JButton();
				this.add(doneButton);
				doneButton.setBounds(315, 181, 77, 29);
				doneButton.setName("doneButton");
				doneButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						doneButtonMouseClicked(evt);
					}
				});
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(255, 66, 192, 103);
				{
					nodesetModel = new DefaultListModel();
					nodesetList = new JList();
					jScrollPane1.setViewportView(nodesetList);
					nodesetList.setModel(nodesetModel);
					nodesetList.setBounds(410, 75, 187, 100);
				}
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
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setBounds(255, 12, 80, 15);
				nodesetLabel.setName("nodesetLabel");
			}
			{
				DefaultComboBoxModel nodesetComboBoxModel = new DefaultComboBoxModel();
				
				String str = "";
				Hashtable<String, Node> nodes = network.getNodes();
				Set<String> set = nodes.keySet();

			    Iterator<String> itr = set.iterator();
			    while (itr.hasNext()) {
			      str = itr.next();
			      nodesetComboBoxModel.addElement(nodes.get(str).getIdentifier()) ;
			    }
				nodesetComboBox = new JComboBox();
				this.add(nodesetComboBox);
				nodesetComboBox.setModel(nodesetComboBoxModel);
				nodesetComboBox.setBounds(255, 31, 193, 22);
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {}
	}
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		for (int i = 0; i < nodesetModel.size(); i++) {
			try {
				Node node = network.getNode(nodesetModel.get(i).toString());
				network.removeNode(node);
			} catch (CustomException e) {
				JOptionPane.showMessageDialog(this,
		    			  "The node " + nodesetModel.get(i).toString() + " doesn't exist",
		    			  "Error",
		    			  JOptionPane.ERROR_MESSAGE);
			}
		}
		nodesetModel.removeAllElements();
		
		DefaultComboBoxModel nodesetComboBoxModel = new DefaultComboBoxModel();
		String str = "";
		Hashtable<String, Node> nodes = network.getNodes();
		Set<String> set = nodes.keySet();

	    Iterator<String> itr = set.iterator();
	    while (itr.hasNext()) {
	      str = itr.next();
	      nodesetComboBoxModel.addElement(nodes.get(str).getIdentifier()) ;
	    }
	    nodesetComboBox.setModel(nodesetComboBoxModel);
		
		this.repaint();
		this.validate();
	}
	
	private void addButtonMouseClicked(MouseEvent evt) {
		try {
			Node node = network.getNode(nodesetComboBox.getSelectedItem().toString());
			if(node.getUpCableSet().size() == 0) {
				nodesetModel.addElement(nodesetComboBox.getSelectedItem().toString());
				nodesetComboBox.setSelectedIndex(0);
				validate();
			} else {
				JOptionPane.showMessageDialog(this,
		    			  "The node " + node.getIdentifier() + " cannot be deleted because it is connected to other nodes",
		    			  "Error",
		    			  JOptionPane.ERROR_MESSAGE);
			}
		} catch (CustomException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
}
