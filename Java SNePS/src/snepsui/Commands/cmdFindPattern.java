package snepsui.Commands;

import java.awt.Dimension;
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

import SNePS1.NetworkManagementSystem.Network;


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
public class cmdFindPattern extends javax.swing.JPanel {
	private JLabel findPatternLabel;
	private JTextField nodesetTextField;
	private JButton addButton;
	private JList nodesetList;
	private JButton doneButton;
	private DefaultListModel nodesetModel;
	private JScrollPane jScrollPane1;
	private JRadioButton contextNameRadioButton1;
	private JRadioButton contextNameRadioButton2;
	private JTextField contextNameTextField;
	private JLabel contextNameLabel;
	private JButton infoButton;
	private JLabel nodesetLabel;
	private JLabel pathLabel;
	private ButtonGroup group;
	private JTextField pathTextField;
	private JList pathList;
	private DefaultListModel pathModel;
	private JScrollPane jScrollPane2;
	private JButton pathButton;
	private JComboBox pathComboBox;
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
	
	public cmdFindPattern(Network network) {
		super();
		this.network = network;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				findPatternLabel = new JLabel();
				this.add(findPatternLabel);
				findPatternLabel.setName("findPatternLabel");
				findPatternLabel.setBounds(0, 28, 85, 15);
			}
			{
				nodesetTextField = new JTextField();
				this.add(nodesetTextField);
				nodesetTextField.setBounds(329, 25, 192, 22);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(535, 27, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						nodesetModel.addElement(nodesetTextField.getText());
						nodesetTextField.setText("");
						pathModel.addElement(pathComboBox.getSelectedItem().toString() + pathTextField.getText());
						pathComboBox.setSelectedIndex(0);
						pathTextField.setText("");
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
				jScrollPane1.setBounds(331, 60, 190, 103);
				{
					nodesetModel = new DefaultListModel();
					nodesetList = new JList();
					jScrollPane1.setViewportView(nodesetList);
					nodesetList.setModel(nodesetModel);
					nodesetList.setBounds(362, 149, 187, 100);
				}
			}
			{
				pathLabel = new JLabel();
				this.add(pathLabel);
				pathLabel.setBounds(85, 4, 70, 15);
				pathLabel.setName("pathLabel");
			}
			{
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setBounds(329, 4, 95, 15);
				nodesetLabel.setName("nodesetLabel");
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
				contextNameLabel.setBounds(533, 83, 107, 19);
			}
			{
				contextNameTextField = new JTextField();
				this.add(contextNameTextField);
				contextNameTextField.setBounds(556, 132, 129, 27);
				contextNameTextField.setName("contextNameTextField");
				contextNameTextField.setEditable(false);
			}
			{
				contextNameRadioButton2 = new JRadioButton();
				this.add(contextNameRadioButton2);
				contextNameRadioButton2.setBounds(532, 133, 23, 26);
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
				contextNameRadioButton1.setBounds(533, 106, 107, 23);
			}
			{
				group = new ButtonGroup();
				group.add(contextNameRadioButton1);
				group.add(contextNameRadioButton2);
			}
			{
				pathTextField = new JTextField();
				this.add(pathTextField);
				pathTextField.setBounds(181, 25, 117, 22);
			}
			{
				ComboBoxModel pathComboBoxModel = 
					new DefaultComboBoxModel(
							new String[] { "converse", "compose", "kstar", "kplus", "or", "and", "not",
									"relative-complement", "irreflexive-restrict", "exception", "domain-restrict", "range-restrict" });
				pathComboBox = new JComboBox();
				this.add(pathComboBox);
				pathComboBox.setModel(pathComboBoxModel);
				pathComboBox.setBounds(85, 24, 90, 22);
			}
			{
				pathButton = new JButton();
				this.add(pathButton);
				pathButton.setBounds(304, 25, 20, 22);
				pathButton.setName("pathButton");
				pathButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						pathButtonActionPerformed(evt);
					}
				});
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(85, 60, 213, 105);
				{
					pathModel = new DefaultListModel();
					pathList = new JList();
					jScrollPane2.setViewportView(pathList);
					pathList.setModel(pathModel);
					pathList.setBounds(40, 171, 228, 102);
					pathList.setPreferredSize(new java.awt.Dimension(194, 85));
				}
			}
			{
				buildButton = new JButton();
				this.add(buildButton);
				buildButton.setBounds(556, 26, 18, 20);
				buildButton.setAction(getAppActionMap().get("build"));
				buildButton.setFocusable(false);
				buildButton.setToolTipText("build");
				buildButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						buildButtonActionPerformed(evt);
					}
				});
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void contextNameRadioButton2MouseClicked(MouseEvent evt) {
		if(contextNameRadioButton2.isSelected()) {
			nodesetTextField.setEditable(true);
		}
		else {
			nodesetTextField.setEditable(false);
		}
	}
	
	private void pathButtonActionPerformed(ActionEvent evt) {
		JFrame frame = new JFrame("Path");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new cmdPath());
		frame.pack();
		frame.setVisible(true);
	}
	
	private void buildButtonActionPerformed(ActionEvent evt) {
		JFrame frame = new JFrame("Build");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new cmdBuild(network));
		frame.pack();
		frame.setVisible(true);
	}
}
