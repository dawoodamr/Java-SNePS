package snepsui.Commands;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.ActionMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

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
public class cmdActivate extends javax.swing.JPanel {
	private JLabel activateLabel;
	private JButton doneButton;
	private JComboBox contextNameComboBox;
	private JLabel contextNameLabel;
	private JButton infoButton;
	private Network network;
	private JTextField relationTextField;
	private JTextField nodesetTextField;
	private LinkedList<Node> nodes;
	private JLabel nodesetLabel;
	private JComboBox nodesetComboBox;
	private JScrollPane jScrollPane1;
	private JList nodesetList;
	private SNePSInterface frame;

	public cmdActivate(Network network, SNePSInterface frame) {
		super();
		this.frame = frame;
		this.network = network;
		nodes = new LinkedList<Node>();
		initGUI();
	}

	@Action
	public void info() {
	}

	private ActionMap getAppActionMap() {
		return Application.getInstance().getContext().getActionMap(this);
	}

	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				activateLabel = new JLabel();
				this.add(activateLabel);
				activateLabel.setName("activateLabel");
				activateLabel.setBounds(111, 35, 59, 15);
			}
			{
				doneButton = new JButton();
				this.add(doneButton);
				doneButton.setBounds(314, 185, 77, 29);
				doneButton.setName("doneButton");
				doneButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						doneButtonMouseClicked(evt);
					}
				});
			}
			{
				relationTextField = new JTextField();
				relationTextField.setEditable(false);
			}
			{
				nodesetTextField = new JTextField();
				nodesetTextField.setEditable(false);
			}
			{
				infoButton = new JButton();
				this.add(infoButton);
				infoButton.setBounds(668, 196, 16, 18);
				infoButton.setAction(getAppActionMap().get("info"));
				infoButton.setFocusable(false);
				infoButton.setToolTipText("info");
			}
			{
				contextNameLabel = new JLabel();
				this.add(contextNameLabel);
				contextNameLabel.setName("contextNameLabel");
				contextNameLabel.setBounds(371, 10, 123, 15);
			}
			{
				ComboBoxModel contextNameComboBoxModel = new DefaultComboBoxModel();
				contextNameComboBox = new JComboBox();
				this.add(contextNameComboBox);
				contextNameComboBox.setModel(contextNameComboBoxModel);
				contextNameComboBox.setBounds(371, 31, 161, 22);
			}
			{
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setBounds(182, 10, 85, 15);
				nodesetLabel.setName("nodesetLabel");
			}
			{
				ComboBoxModel nodesetComboBoxModel = new DefaultComboBoxModel();
				nodesetComboBox = new JComboBox();
				this.add(nodesetComboBox);
				nodesetComboBox.setModel(nodesetComboBoxModel);
				nodesetComboBox.setBounds(182, 31, 177, 22);
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(182, 65, 177, 103);
				{
					ListModel nodesetListModel = new DefaultComboBoxModel();
					nodesetList = new JList();
					jScrollPane1.setViewportView(nodesetList);
					nodesetList.setModel(nodesetListModel);
				}
			}
			Application.getInstance().getContext().getResourceMap(getClass())
					.injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		
	}
	
	public LinkedList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(LinkedList<Node> nodes) {
		this.nodes = nodes;
	}
}
