package SNePS.SNePSUI.Commands;

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

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import SNePS.NetworkManagementSystem.Network;



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
public class cmdDefinePath extends javax.swing.JPanel {
	private JLabel definePathLabel;
	private JLabel pathLabel;
	private JLabel relationLabel;
	private JTextField pathTextField;
	private JTextField relationTextField;
	private JComboBox pathComboBox;
	private JList pathList;
	private JList relationList;
	private DefaultListModel relationModel;
	private DefaultListModel pathModel;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JButton addButton;
	private JButton doneButton;
	private JButton pathButton;
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
	
	public cmdDefinePath(Network network) {
		super();
		this.network = network;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			this.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					thisMouseClicked(evt);
				}
			});
			{
				definePathLabel = new JLabel();
				this.add(definePathLabel);
				definePathLabel.setName("definePathLabel");
				definePathLabel.setBounds(12, 28, 86, 15);
			}
			{
				pathTextField = new JTextField();
				this.add(pathTextField);
				pathTextField.setBounds(422, 25, 145, 22);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(583, 27, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
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
				jScrollPane1.setBounds(308, 59, 259, 103);
				{
					pathModel = new DefaultListModel();
					pathList = new JList();
					jScrollPane1.setViewportView(pathList);
					pathList.setModel(pathModel);
					pathList.setBounds(410, 168, 187, 100);
				}
			}
			{
				relationTextField = new JTextField();
				this.add(relationTextField);
				relationTextField.setBounds(101, 25, 189, 22);
			}
			{
				relationLabel = new JLabel();
				this.add(relationLabel);
				relationLabel.setBounds(101, 4, 70, 15);
				relationLabel.setName("relationLabel");
			}
			{
				pathLabel = new JLabel();
				this.add(pathLabel);
				pathLabel.setBounds(308, 4, 66, 15);
				pathLabel.setName("pathLabel");
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(101, 59, 189, 103);
				{
					relationModel = new DefaultListModel();
					relationList = new JList();
					jScrollPane2.setViewportView(relationList);
					relationList.setModel(relationModel);
					relationList.setBounds(116, 124, 186, 100);
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
				ComboBoxModel pathComboBoxModel = 
					new DefaultComboBoxModel(
							new String[] { "converse", "compose", "kstar", "kplus", "or", "and", "not",
									"relative-complement", "irreflexive-restrict", "exception", "domain-restrict", "range-restrict" });
				pathComboBox = new JComboBox();
				this.add(pathComboBox);
				pathComboBox.setModel(pathComboBoxModel);
				pathComboBox.setBounds(308, 24, 108, 22);
			}
			{
				pathButton = new JButton();
				this.add(pathButton);
				pathButton.setBounds(610, 25, 40, 22);
				pathButton.setName("pathButton");
				pathButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						pathButtonActionPerformed(evt);
					}
				});
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void thisMouseClicked(MouseEvent evt) {
		relationModel.addElement(relationTextField.getText());
		pathModel.addElement(pathComboBox.getSelectedItem().toString() + pathTextField.getText());
		relationTextField.setText("");
		pathComboBox.setSelectedIndex(0);
		pathTextField.setText("");
	}
	
	private void pathButtonActionPerformed(ActionEvent evt) {
		JFrame frame = new JFrame("Path");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new cmdPath());
		frame.pack();
		frame.setVisible(true);
	}
}
