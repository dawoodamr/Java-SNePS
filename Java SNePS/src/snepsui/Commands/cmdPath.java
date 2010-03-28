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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

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
public class cmdPath extends javax.swing.JPanel {
	private JTextField pathTextField;
	private JButton addButton;
	private JList pathList;
	private JButton doneButton;
	private DefaultListModel pathModel;
	private JScrollPane jScrollPane1;
	private JButton pathButton;
	private JButton cancelButton;
	private JComboBox pathComboBox;
	private JButton infoButton;
	private JLabel pathLabel;
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
	
	public cmdPath(Network network) {
		super();
		this.network = network;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				pathTextField = new JTextField();
				this.add(pathTextField);
				pathTextField.setBounds(332, 25, 174, 22);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(518, 27, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						addButtonMouseClicked(evt);
					}
				});
			}
			{
				doneButton = new JButton();
				this.add(doneButton);
				doneButton.setBounds(240, 180, 77, 29);
				doneButton.setName("doneButton");
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(182, 59, 328, 103);
				{
					pathModel = new DefaultListModel();
					pathList = new JList();
					jScrollPane1.setViewportView(pathList);
					pathList.setModel(pathModel);
					pathList.setBounds(466, 83, 256, 100);
				}
			}
			{
				pathLabel = new JLabel();
				this.add(pathLabel);
				pathLabel.setBounds(182, 3, 66, 15);
				pathLabel.setName("pathLabel");
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
				pathComboBox.setBounds(182, 24, 144, 22);
			}
			{
				pathButton = new JButton();
				this.add(pathButton);
				pathButton.setBounds(549, 25, 40, 22);
				pathButton.setName("pathButton");
				pathButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						pathButtonActionPerformed(evt);
					}
				});
			}
			{
				cancelButton = new JButton();
				this.add(cancelButton);
				cancelButton.setBounds(356, 180, 77, 29);
				cancelButton.setName("cancelButton");
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void pathButtonActionPerformed(ActionEvent evt) {
		cmdPath pathPanel = new cmdPath(network);
		
	    int result = JOptionPane.showConfirmDialog(  
	    	    this, pathPanel, "title", JOptionPane.PLAIN_MESSAGE
	    	);
	}
	
	private void addButtonMouseClicked(MouseEvent evt) {
		pathModel.addElement(pathComboBox.getSelectedItem().toString() + pathTextField.getText());
		pathComboBox.setSelectedIndex(0);
		pathTextField.setText("");
	}
}
