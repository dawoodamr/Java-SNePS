package snepsui.Commands;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import sneps.Network;
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
public class cmdDeduceWH extends javax.swing.JPanel {
	private JLabel deduceLabel;
	private JTextField nodesetTextField;
	private JButton addButton;
	private JList nodesetList;
	private JButton doneButton;
	private DefaultListModel relationListModel;
	private DefaultListModel nodesetListModel;
	private JTextField nnegTextField;
	private JLabel nnegLabel;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JLabel numbLabel;
	private JTextField nposTextField;
	private JLabel nposLabel;
	private JTextField numbTextField;
	private JRadioButton numbRadioButton3;
	private JButton assertButton;
	private JButton findButton;
	private JComboBox contextNameComboBox;
	private JComboBox relationComboBox;
	private JComboBox nodesComboBox;
	private JRadioButton numbRadioButton2;
	private JRadioButton numbRadioButton1;
	private JLabel contextNameLabel;
	private JButton infoButton;
	private JList relationList;
	private JLabel nodesetLabel;
	private JLabel relationLabel;
	private JButton buildButton;
	private Network network;
	private SNePSInterface frame;

	@Action
	public void add() {}
	
	@Action
	public void build() {}

	@Action
	public void info() {}
	
	@Action
	public void find() {}
	
	@Action
	public void assertAction() {}

	private ActionMap getAppActionMap() {
		return Application.getInstance().getContext().getActionMap(this);
	}

	public cmdDeduceWH(Network network, SNePSInterface frame) {
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
				deduceLabel = new JLabel();
				this.add(deduceLabel);
				deduceLabel.setName("deduceLabel");
				deduceLabel.setBounds(5, 28, 82, 15);
			}
			{
				nodesetTextField = new JTextField();
				this.add(nodesetTextField);
				nodesetTextField.setBounds(385, 25, 88, 22);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(475, 27, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						relationListModel.addElement(relationComboBox.getSelectedItem().toString());
						nodesetListModel.addElement(nodesetTextField.getText());
						relationComboBox.setSelectedIndex(0);
						nodesComboBox.setSelectedIndex(0);
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
				jScrollPane1.setBounds(280, 58, 190, 103);
				{
					nodesetListModel = new DefaultListModel();
					nodesetList = new JList();
					jScrollPane1.setViewportView(nodesetList);
					nodesetList.setModel(nodesetListModel);
					nodesetList.setBounds(195, 138, 187, 100);
				}
			}
			{
				relationLabel = new JLabel();
				this.add(relationLabel);
				relationLabel.setBounds(92, 4, 70, 15);
				relationLabel.setName("relationLabel");
			}
			{
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setBounds(281, 4, 95, 15);
				nodesetLabel.setName("nodesetLabel");
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(92, 59, 176, 103);
				{
					relationListModel = new DefaultListModel();
					relationList = new JList();
					jScrollPane2.setViewportView(relationList);
					relationList.setModel(relationListModel);
					relationList.setBounds(79, 144, 186, 100);
					relationList.setPreferredSize(new java.awt.Dimension(102, 68));
				}
			}
			{
				buildButton = new JButton();
				this.add(buildButton);
				buildButton.setBounds(496, 26, 18, 20);
				buildButton.setAction(getAppActionMap().get("build"));
				buildButton.setToolTipText("build");
				buildButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						buildButtonActionPerformed(evt);
					}
				});
			}
			{
				infoButton = new JButton();
				this.add(infoButton);
				infoButton.setBounds(668, 196, 16, 18);
				infoButton.setAction(getAppActionMap().get("info"));
				infoButton.setFocusPainted(false);
				infoButton.setBorderPainted(false);
				infoButton.setContentAreaFilled(false);
				infoButton.setMargin(new Insets(0,0,0,0));
				infoButton.setToolTipText("info");
			}
			{
				contextNameLabel = new JLabel();
				this.add(contextNameLabel);
				contextNameLabel.setName("contextNameLabel");
				contextNameLabel.setBounds(533, 45, 107, 19);
			}
			{
				{
					numbLabel = new JLabel();
					this.add(numbLabel);
					numbLabel.setBounds(533, 92, 33, 15);
					numbLabel.setName("numbLabel");
				}
				{
					numbRadioButton1 = new JRadioButton();
					this.add(numbRadioButton1);
					numbRadioButton1.setBounds(533, 111, 80, 19);
					numbRadioButton1.setName("numbRadioButton1");
					numbRadioButton1.setSelected(true);
					numbRadioButton1.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							numbRadioButton1MouseClicked(evt);
						}
					});
				}
				{
					numbRadioButton2 = new JRadioButton();
					this.add(numbRadioButton2);
					numbRadioButton2.setBounds(533, 131, 20, 17);
					numbRadioButton2.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							numbRadioButton2MouseClicked(evt);
						}
					});
				}
				{
					numbRadioButton3 = new JRadioButton();
					this.add(numbRadioButton3);
					numbRadioButton3.setBounds(533, 155, 20, 17);
					numbRadioButton3.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							numbRadioButton3MouseClicked(evt);
						}
					});
				}
				{
					numbTextField = new JTextField();
					this.add(numbTextField);
					numbTextField.setBounds(558, 133, 33, 16);
					numbTextField.setEditable(false);
				}
				{
					nposLabel = new JLabel();
					this.add(nposLabel);
					nposLabel.setBounds(558, 155, 28, 15);
					nposLabel.setName("nposLabel");
				}
				{
					nposTextField = new JTextField();
					this.add(nposTextField);
					nposTextField.setBounds(588, 154, 28, 18);
					nposTextField.setEditable(false);
				}
				{
					nnegLabel = new JLabel();
					this.add(nnegLabel);
					nnegLabel.setBounds(622, 153, 28, 18);
					nnegLabel.setName("nnegLabel");
				}
				{
					nnegTextField = new JTextField();
					this.add(nnegTextField);
					nnegTextField.setBounds(656, 154, 28, 18);
					nnegTextField.setEditable(false);
				}
				{
					ComboBoxModel nodesComboBoxModel = new DefaultComboBoxModel();
					nodesComboBox = new JComboBox();
					this.add(nodesComboBox);
					nodesComboBox.setModel(nodesComboBoxModel);
					nodesComboBox.setBounds(281, 24, 98, 22);
				}
				{
					ComboBoxModel relationComboBoxModel = new DefaultComboBoxModel();
					relationComboBox = new JComboBox();
					this.add(relationComboBox);
					relationComboBox.setModel(relationComboBoxModel);
					relationComboBox.setBounds(92, 24, 173, 23);
				}
				{
					ComboBoxModel contextNameComboBoxModel = new DefaultComboBoxModel();
					contextNameComboBox = new JComboBox();
					this.add(contextNameComboBox);
					contextNameComboBox.setModel(contextNameComboBoxModel);
					contextNameComboBox.setBounds(533, 64, 151, 22);
				}
				{
					findButton = new JButton();
					this.add(findButton);
					findButton.setAction(getAppActionMap().get("find"));
					findButton.setName("findButton");
					findButton.setBounds(540, 26, 18, 20);
					findButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							findButtonActionPerformed(evt);
						}
					});
				}
				{
					assertButton = new JButton();
					this.add(assertButton);
					assertButton.setAction(getAppActionMap().get("assertAction"));
					assertButton.setName("assertButton");
					assertButton.setBounds(519, 26, 18, 20);
					assertButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							assertButtonActionPerformed(evt);
						}
					});
				}
			}
			Application.getInstance().getContext().getResourceMap(getClass())
					.injectComponents(this);
		} catch (Exception e) {}
	}
	
	private void numbRadioButton1MouseClicked(MouseEvent evt) {
		if(numbRadioButton1.isSelected()) {
			numbTextField.setEnabled(false);
			nposTextField.setEnabled(false);
			nnegTextField.setEnabled(false);
		}
	}
	
	private void numbRadioButton2MouseClicked(MouseEvent evt) {
		if(numbRadioButton2.isSelected()) {
			numbTextField.setEnabled(true);
			nposTextField.setEnabled(false);
			nnegTextField.setEnabled(false);
		}
	}
	
	private void numbRadioButton3MouseClicked(MouseEvent evt) {
		if(numbRadioButton3.isSelected()) {
			nposTextField.setEnabled(true);
			nnegTextField.setEnabled(true);
			numbTextField.setEnabled(false);
		}
	}
	
	private void buildButtonActionPerformed(ActionEvent evt) {

		JFrame popupFrame = new JFrame("Build");
		popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popupFrame.getContentPane().add(new cmdBuild(network, frame));
		popupFrame.pack();
		popupFrame.setVisible(true);
	}
	
	private void assertButtonActionPerformed(ActionEvent evt) {
		JFrame popupFrame = new JFrame("Assert");
		popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popupFrame.getContentPane().add(new cmdAssert(network, frame));
		popupFrame.pack();
		popupFrame.setVisible(true);
	}
	
	private void findButtonActionPerformed(ActionEvent evt) {
		JFrame popupFrame = new JFrame("Find");
		popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popupFrame.getContentPane().add(new cmdFind(network, frame));
		popupFrame.pack();
		popupFrame.setVisible(true);
	}
}
