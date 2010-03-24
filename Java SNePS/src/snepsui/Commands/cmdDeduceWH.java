package snepsui.Commands;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
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
public class cmdDeduceWH extends javax.swing.JPanel {
	private JLabel deduceWHLabel;
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
	private JRadioButton numbRadioButton2;
	private JRadioButton numbRadioButton1;
	private JRadioButton contextNameRadioButton1;
	private JRadioButton contextNameRadioButton2;
	private JTextField contextNameTextField;
	private JLabel contextNameLabel;
	private JButton infoButton;
	private JList relationList;
	private JLabel nodesetLabel;
	private JLabel relationLabel;
	private JTextField relationTextField;
	private ButtonGroup group;
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

	public cmdDeduceWH(Network network) {
		super();
		this.network = network;
		initGUI();
	}

	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				deduceWHLabel = new JLabel();
				this.add(deduceWHLabel);
				deduceWHLabel.setName("deduceWHLabel");
				deduceWHLabel.setBounds(0, 28, 80, 15);
			}
			{
				nodesetTextField = new JTextField();
				this.add(nodesetTextField);
				nodesetTextField.setBounds(281, 25, 192, 22);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(481, 27, 16, 18);
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
				relationTextField = new JTextField();
				this.add(relationTextField);
				relationTextField.setBounds(80, 25, 189, 22);
			}
			{
				relationLabel = new JLabel();
				this.add(relationLabel);
				relationLabel.setBounds(80, 4, 70, 15);
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
				jScrollPane2.setBounds(79, 59, 189, 103);
				{
					relationListModel = new DefaultListModel();
					relationList = new JList();
					jScrollPane2.setViewportView(relationList);
					relationList.setModel(relationListModel);
					relationList.setBounds(185, 73, 186, 100);
				}
			}
			{
				buildButton = new JButton();
				this.add(buildButton);
				buildButton.setBounds(502, 26, 18, 20);
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
				contextNameLabel.setBounds(533, 2, 107, 19);
			}
			{
				contextNameTextField = new JTextField();
				this.add(contextNameTextField);
				contextNameTextField.setBounds(555, 53, 129, 27);
				contextNameTextField.setName("contextNameTextField");
				contextNameTextField.setEditable(false);
			}
			{
				contextNameRadioButton2 = new JRadioButton();
				this.add(contextNameRadioButton2);
				contextNameRadioButton2.setBounds(533, 52, 23, 26);
				contextNameRadioButton2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						contextNameRadioButton2MouseClicked(evt);
					}
				});
			}
			{
				contextNameRadioButton1 = new JRadioButton();
				this.add(contextNameRadioButton1);
				contextNameRadioButton1.setName("contextNameRadioButton1");
				contextNameRadioButton1.setBounds(533, 24, 107, 23);
			}
			{
				group = new ButtonGroup();
				group.add(contextNameRadioButton1);
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
				group.add(contextNameRadioButton2);
			}
			Application.getInstance().getContext().getResourceMap(getClass())
					.injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void contextNameRadioButton2MouseClicked(MouseEvent evt) {
		if (contextNameRadioButton2.isSelected()) {
			nodesetTextField.setEditable(true);
		} else {
			nodesetTextField.setEditable(false);
		}
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

		JFrame frame = new JFrame("Build");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new cmdBuild(network));
		frame.pack();
		frame.setVisible(true);
	}
}
