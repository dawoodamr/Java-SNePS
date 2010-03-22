package snepsui.Commands;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import SNePS1.NetworkManagementSystem.Network;


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
public class cmdActPlan extends javax.swing.JPanel {
	private JLabel buildLabel;
	private JTextField nodesetTextField;
	private JButton addButton;
	private JList nodesetList;
	private JButton doneButton;
	private DefaultListModel listModel;
	private JLabel planLabel;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JTextField actTextField;
	private JLabel actLabel;
	private JLabel assertLabel;
	private JButton infoButton;
	private JList relationList;
	private JLabel nodesetLabel;
	private JLabel relationLabel;
	private JTextField relationTextField;
	private Network network;
	private JButton assertButton;
	private JButton findButton;
	private JButton buildButton;

	public cmdActPlan(Network network) {
		super();
		this.network = network;
		initGUI();
	}
	
	@Action
	public void find() {
	}
	
	@Action
	public void assertAction() {
	}
	
	@Action
	public void build() {
	}
	
	@Action
	public void add() {
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
				buildLabel = new JLabel();
				this.add(buildLabel);
				buildLabel.setName("buildLabel");
				buildLabel.setBounds(120, 71, 45, 15);
			}
			{
				nodesetTextField = new JTextField();
				this.add(nodesetTextField);
				nodesetTextField.setBounds(365, 68, 192, 22);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(569, 70, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						listModel.addElement(nodesetTextField.getText());
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
				jScrollPane1.setBounds(365, 103, 192, 70);
				{
					listModel = new DefaultListModel();
					nodesetList = new JList();
					jScrollPane1.setViewportView(nodesetList);
					nodesetList.setModel(listModel);
					nodesetList.setBounds(325, 186, 186, 67);
				}
			}
			{
				relationTextField = new JTextField();
				this.add(relationTextField);
				relationTextField.setBounds(164, 68, 189, 22);
			}
			{
				relationLabel = new JLabel();
				this.add(relationLabel);
				relationLabel.setBounds(165, 53, 70, 15);
				relationLabel.setName("relationLabel");
			}
			{
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setBounds(435, 53, 66, 15);
				nodesetLabel.setName("nodesetLabel");
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(164, 104, 189, 70);
				{
					ListModel jList2Model = new DefaultComboBoxModel();
					relationList = new JList();
					jScrollPane2.setViewportView(relationList);
					relationList.setModel(jList2Model);
					relationList.setBounds(94, 146, 186, 67);
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
				assertLabel = new JLabel();
				this.add(assertLabel);
				assertLabel.setBounds(12, 23, 54, 15);
				assertLabel.setName("assertLabel");
			}
			{
				actLabel = new JLabel();
				this.add(actLabel);
				actLabel.setBounds(72, 23, 40, 15);
				actLabel.setName("actLabel");
			}
			{
				planLabel = new JLabel();
				this.add(planLabel);
				planLabel.setBounds(72, 71, 48, 15);
				planLabel.setName("planLabel");
			}
			{
				actTextField = new JTextField();
				this.add(actTextField);
				actTextField.setBounds(118, 20, 168, 22);
			}
			{
				buildButton = new JButton();
				this.add(buildButton);
				buildButton.setBounds(590, 69, 18, 20);
				buildButton.setAction(getAppActionMap().get("build"));
				buildButton.setFocusable(false);
				buildButton.setToolTipText("build");
				buildButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						buildButtonActionPerformed(evt);
					}
				});
			}
			{
				assertButton = new JButton();
				this.add(assertButton);
				assertButton.setBounds(613, 69, 18, 20);
				assertButton.setAction(getAppActionMap().get("assertAction"));
				assertButton.setFocusable(false);
				assertButton.setToolTipText("assert");
				assertButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						assertButtonActionPerformed(evt);
					}
				});
			}
			{
				findButton = new JButton();
				this.add(findButton);
				findButton.setBounds(636, 69, 18, 20);
				findButton.setAction(getAppActionMap().get("find"));
				findButton.setFocusable(false);
				findButton.setToolTipText("assert");
				findButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						findButtonActionPerformed(evt);
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
	
	private void assertButtonActionPerformed(ActionEvent evt) {
		JFrame frame = new JFrame("Assert");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new cmdAssert(network));
		frame.pack();
		frame.setVisible(true);
	}
	
	private void findButtonActionPerformed(ActionEvent evt) {
		JFrame frame = new JFrame("Find");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new cmdFind(network));
		frame.pack();
		frame.setVisible(true);
	}
}
