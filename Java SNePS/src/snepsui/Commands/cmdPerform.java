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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import sneps.Network;

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
public class cmdPerform extends javax.swing.JPanel {
	private JLabel performLabel;
	private JLabel buildLabel;
	private JScrollPane jScrollPane1;
	private JList nodesetList;
	private JList relationList;
	private JLabel nodesetLabel;
	private JLabel relationLabel;
	private JScrollPane jScrollPane3;
	private JTextField nodesetTextField;
	private JTextField relationTextField;
	private JButton addButton;
	private JButton doneButton;
	private JButton infoButton;
	private JButton buildButton;
	private JLabel contextNameLabel;
	private JRadioButton contextNameRadioButton1;
	private JTextField contextNameTextField;
	private JRadioButton contextNameRadioButton2;
	private DefaultListModel relationModel;
	private DefaultListModel nodesetModel;
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

	public cmdPerform(Network network) {
		super();
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
				performLabel.setBounds(12, 31, 55, 15);
			}
			{
				buildLabel = new JLabel();
				this.add(buildLabel);
				buildLabel.setBounds(73, 31, 43, 15);
				buildLabel.setName("buildLabel");
			}
			{
				relationTextField = new JTextField();
				this.add(relationTextField);
				relationTextField.setBounds(115, 28, 170, 22);
				relationTextField.setName("relationTextField");
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(115, 56, 171, 110);
				{
					relationModel = new DefaultListModel();
					relationList = new JList();
					jScrollPane1.setViewportView(relationList);
					relationList.setModel(relationModel);
					relationList.setBounds(85, 172, 168, 36);
				}
			}
			{
				nodesetTextField = new JTextField();
				this.add(nodesetTextField);
				nodesetTextField.setBounds(301, 28, 176, 22);
			}
			{
				jScrollPane3 = new JScrollPane();
				this.add(jScrollPane3);
				jScrollPane3.setBounds(301, 56, 176, 107);
				{
					nodesetModel = new DefaultListModel();
					nodesetList = new JList();
					jScrollPane3.setViewportView(nodesetList);
					nodesetList.setModel(nodesetModel);
					nodesetList.setBounds(407, 174, 173, 36);
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
				infoButton.setToolTipText("info");
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(489, 30, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						addButtonMouseClicked(evt);
					}
				});
			}
			{
				buildButton = new JButton();
				this.add(buildButton);
				buildButton.setBounds(516, 29, 18, 20);
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
				contextNameRadioButton2 = new JRadioButton();
				this.add(contextNameRadioButton2);
				contextNameRadioButton2.setBounds(502, 133, 28, 29);
				contextNameRadioButton2.setName("contextNameRadioButton2");
				contextNameRadioButton2.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						//contextNameRadioButton2MouseClicked(evt);
					}
				});
			}
			{
				contextNameTextField = new JTextField();
				this.add(contextNameTextField);
				contextNameTextField.setName("contextNameTextField");
				contextNameTextField.setBounds(530, 139, 136, 23);
			}
			{
				contextNameRadioButton1 = new JRadioButton();
				this.add(contextNameRadioButton1);
				contextNameRadioButton1.setName("contextNameRadioButton1");
				contextNameRadioButton1.setBounds(502, 105, 123, 21);
			}
			{
				contextNameLabel = new JLabel();
				this.add(contextNameLabel);
				contextNameLabel.setName("contextNameLabel");
				contextNameLabel.setBounds(502, 74, 123, 21);
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
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
	
	private void addButtonMouseClicked(MouseEvent evt) {
		relationModel.addElement(relationTextField.getText());
		nodesetModel.addElement(nodesetTextField.getText());
		relationTextField.setText("");
		nodesetTextField.setText("");
	}
}
