package snepsui.Commands;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ActionMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import sneps.Network;
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
public class cmdAttachPrimaction extends javax.swing.JPanel {
	private JLabel buildLabel2;
	private JLabel wheneverLabel;
	private JLabel assertLabel;
	private JLabel relationLabel;
	private JLabel buildLabel1;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JList actionList;
	private JList actnodeList;
	private JLabel actionLabel;
	private JComboBox actionComboBox;
	private JLabel actNodeLabel;
	private JLabel attachPrimactionLabel;
	private JButton addButton1;
	private JComboBox actNodesComboBox;
	private JLabel nodesetLabel;
	private JLabel doLabel;
	private DefaultListModel relationModel1;
	private DefaultListModel relationModel2;
	private DefaultListModel nodesetModel1;
	private DefaultListModel nodesetModel2;
	private JButton infoButton;
	private JButton doneButton;
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

	public cmdAttachPrimaction(Network network, SNePSInterface frame) {
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
				buildLabel2 = new JLabel();
				this.add(buildLabel2);
				buildLabel2.setName("buildLabel2");
				buildLabel2.setBounds(141, 106, 45, 15);
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
				infoButton.setToolTipText("info");
			}
			{
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setName("nodesetLabel");
				nodesetLabel.setBounds(408, 0, 66, 15);
			}
			{
				relationLabel = new JLabel();
				this.add(relationLabel);
				relationLabel.setName("relationLabel");
				relationLabel.setBounds(204, 0, 70, 15);
			}
			{
				buildLabel1 = new JLabel();
				this.add(buildLabel1);
				buildLabel1.setName("buildLabel1");
				buildLabel1.setBounds(141, 23, 45, 15);
			}
			{
				assertLabel = new JLabel();
				this.add(assertLabel);
				assertLabel.setBounds(18, 23, 35, 15);
				assertLabel.setName("assertLabel");
			}
			{
				wheneverLabel = new JLabel();
				this.add(wheneverLabel);
				wheneverLabel.setBounds(70, 23, 65, 15);
				wheneverLabel.setName("wheneverLabel");
			}
			{
				doLabel = new JLabel();
				this.add(doLabel);
				doLabel.setBounds(70, 106, 15, 15);
				doLabel.setName("doLabel");
			}
			{
				ComboBoxModel actNodesComboBoxModel = new DefaultComboBoxModel();
				actNodesComboBox = new JComboBox();
				this.add(actNodesComboBox);
				actNodesComboBox.setModel(actNodesComboBoxModel);
				actNodesComboBox.setBounds(198, 44, 146, 22);
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(199, 78, 145, 84);
				{
					ListModel actnodeListModel = new DefaultComboBoxModel();
					actnodeList = new JList();
					jScrollPane1.setViewportView(actnodeList);
					actnodeList.setModel(actnodeListModel);
				}
			}
			{
				addButton1 = new JButton();
				this.add(addButton1);
				addButton1.setAction(getAppActionMap().get("add"));
				addButton1.setName("addButton1");
				addButton1.setBounds(505, 47, 16, 18);
				addButton1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						
					}
				});
			}
			{
				attachPrimactionLabel = new JLabel();
				this.add(attachPrimactionLabel);
				attachPrimactionLabel.setBounds(57, 48, 126, 15);
				attachPrimactionLabel.setName("attachPrimactionLabel");
			}
			{
				actNodeLabel = new JLabel();
				this.add(actNodeLabel);
				actNodeLabel.setBounds(198, 18, 90, 15);
				actNodeLabel.setName("actNodeLabel");
			}
			{
				ComboBoxModel actionComboBoxModel = new DefaultComboBoxModel();
				actionComboBox = new JComboBox();
				this.add(actionComboBox);
				actionComboBox.setModel(actionComboBoxModel);
				actionComboBox.setBounds(356, 44, 143, 22);
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(356, 78, 143, 84);
				{
					ListModel actionListModel = new DefaultComboBoxModel();
					actionList = new JList();
					jScrollPane2.setViewportView(actionList);
					actionList.setModel(actionListModel);
				}
			}
			{
				actionLabel = new JLabel();
				this.add(actionLabel);
				actionLabel.setBounds(356, 18, 83, 15);
				actionLabel.setName("actionLabel");
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
