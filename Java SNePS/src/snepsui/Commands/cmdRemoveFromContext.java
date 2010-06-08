package snepsui.Commands;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
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

import sneps.Network;
import sneps.Node;
import snepsui.Interface.SNePSInterface;


/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */

/**
 * @author Alia Taher
 */
@SuppressWarnings("unused")
public class cmdRemoveFromContext extends javax.swing.JPanel {
	private JLabel removeFromContextLabel;
	private JButton addButton;
	private JButton doneButton;
	private DefaultListModel nodesetListModel;
	private JScrollPane jScrollPane2;
	private JComboBox contextComboBox;
	private JComboBox nodesetComboBox;
	private JButton infoButton;
	private JList nodesetList;
	private JLabel contextNameLabel;
	private JLabel nodesetLabel;
	private Network network;
	private SNePSInterface frame;

	public cmdRemoveFromContext(Network network, SNePSInterface frame) {
		super();
		this.frame = frame;
		this.network = network;
		initGUI();
	}
	
	@Action
	public void add() {
	}
	
	@Action
	public void build() {
	}

	@Action
	public void info() {
	}
	
	@Action
	public void find() {
	}
	
	@Action
	public void assertAction() {
	}

	private ActionMap getAppActionMap() {
		return Application.getInstance().getContext().getActionMap(this);
	}

	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				removeFromContextLabel = new JLabel();
				this.add(removeFromContextLabel);
				removeFromContextLabel.setName("removeFromContextLabel");
				removeFromContextLabel.setBounds(28, 27, 142, 15);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(562, 26, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						nodesetListModel.addElement(nodesetComboBox.getSelectedItem().toString());
						nodesetComboBox.setSelectedIndex(0);
						validate();
					}
				});
			}
			{
				doneButton = new JButton();
				this.add(doneButton);
				doneButton.setBounds(314, 185, 77, 29);
				doneButton.setName("doneButton");
			}
			{
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setBounds(337, 4, 61, 15);
				nodesetLabel.setName("nodesetLabel");
			}
			{
				contextNameLabel = new JLabel();
				this.add(contextNameLabel);
				contextNameLabel.setBounds(177, 4, 97, 15);
				contextNameLabel.setName("contextNameLabel");
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(337, 57, 213, 103);
				{
					nodesetListModel = new DefaultListModel();
					nodesetList = new JList();
					jScrollPane2.setViewportView(nodesetList);
					nodesetList.setModel(nodesetListModel);
					nodesetList.setBounds(37, 160, 240, 100);
					nodesetList.setPreferredSize(new java.awt.Dimension(126, 43));
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
				new ButtonGroup();
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
				nodesetComboBox.setBounds(337, 23, 213, 22);
			}
			{
				ComboBoxModel contextComboBoxModel = 
					new DefaultComboBoxModel();
				contextComboBox = new JComboBox();
				this.add(contextComboBox);
				contextComboBox.setModel(contextComboBoxModel);
				contextComboBox.setBounds(177, 23, 148, 22);
			}
			Application.getInstance().getContext().getResourceMap(getClass())
					.injectComponents(this);
		} catch (Exception e) {}
	}
}
