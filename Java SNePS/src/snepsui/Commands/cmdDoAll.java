package snepsui.Commands;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import sneps.Network;


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
public class cmdDoAll extends javax.swing.JPanel {
	private JLabel buildLabel;
	private JTextField nodesetTextField;
	private JButton addButton;
	private JList nodesetList;
	private JButton doneButton;
	private DefaultListModel relationListModel;
	private DefaultListModel nodesetListModel;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JLabel object1Label;
	private JLabel buildActionDoAllLabel;
	private JLabel performLabel;
	private JButton infoButton;
	private JList relationList;
	private JLabel nodesetLabel;
	private JLabel relationLabel;
	private JTextField relationTextField;
	private JButton buildButton;
	private Network network;

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

	public cmdDoAll(Network network) {
		super();
		this.network = network;
		initGUI();
	}

	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				buildLabel = new JLabel();
				this.add(buildLabel);
				buildLabel.setName("buildLabel");
				buildLabel.setBounds(143, 55, 36, 15);
			}
			{
				nodesetTextField = new JTextField();
				this.add(nodesetTextField);
				nodesetTextField.setBounds(407, 51, 192, 22);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(611, 53, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						relationListModel.addElement(relationTextField.getText());
						nodesetListModel.addElement(nodesetTextField.getText());
						relationTextField.setText("");
						nodesetTextField.setText("");
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
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(409, 81, 190, 91);
				{
					nodesetListModel = new DefaultListModel();
					nodesetList = new JList();
					jScrollPane1.setViewportView(nodesetList);
					nodesetList.setModel(nodesetListModel);
					nodesetList.setBounds(429, 160, 187, 88);
				}
			}
			{
				relationTextField = new JTextField();
				this.add(relationTextField);
				relationTextField.setBounds(206, 52, 189, 22);
			}
			{
				relationLabel = new JLabel();
				this.add(relationLabel);
				relationLabel.setBounds(206, 31, 70, 15);
				relationLabel.setName("relationLabel");
			}
			{
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setBounds(407, 31, 66, 15);
				nodesetLabel.setName("nodesetLabel");
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(206, 80, 189, 93);
				{
					relationListModel = new DefaultListModel();
					relationList = new JList();
					jScrollPane2.setViewportView(relationList);
					relationList.setModel(relationListModel);
					relationList.setBounds(185, 142, 186, 90);
				}
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
				performLabel = new JLabel();
				this.add(performLabel);
				performLabel.setBounds(12, 12, 47, 15);
				performLabel.setName("performLabel");
			}
			{
				buildActionDoAllLabel = new JLabel();
				this.add(buildActionDoAllLabel);
				buildActionDoAllLabel.setBounds(71, 12, 133, 15);
				buildActionDoAllLabel.setName("buildActionDoAllLabel");
			}
			{
				object1Label = new JLabel();
				this.add(object1Label);
				object1Label.setBounds(71, 55, 44, 15);
				object1Label.setName("object1Label");
			}
			{
				buildButton = new JButton();
				this.add(buildButton);
				buildButton.setBounds(638, 52, 18, 20);
				buildButton.setAction(getAppActionMap().get("build"));
				buildButton.setFocusable(false);
				buildButton.setToolTipText("build");
				buildButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						buildButtonActionPerformed(evt);
					}
				});
			}
			Application.getInstance().getContext().getResourceMap(getClass())
					.injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void buildButtonActionPerformed(ActionEvent evt) {
		JFrame frame = new JFrame("Build");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new cmdBuild(network));
		frame.pack();
		frame.setVisible(true);
	}
}
