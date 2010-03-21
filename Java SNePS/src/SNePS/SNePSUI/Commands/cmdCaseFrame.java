package SNePS.SNePSUI.Commands;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.ActionMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import SNePS.NetworkManagementSystem.CaseFrame;
import SNePS.NetworkManagementSystem.CustomException;
import SNePS.NetworkManagementSystem.Network;
import SNePS.NetworkManagementSystem.Relation;


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
public class cmdCaseFrame extends javax.swing.JPanel {
	private JComboBox semanticClassComboBox;
	private JLabel semanticClassLabel;
	private JLabel caseFrameLabel;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JTextField relationSetTextField;
	private JComboBox relationsComboBox;
	private JList relationsList;
	private JList semanticClassList;
	private JLabel relationsLabel;
	private JButton infoButton;
	private JButton doneButton;
	private JButton addButton;
	private DefaultListModel semanticClassModel;
	private DefaultListModel relationsModel;
	private Network network;

	@Action
	public void info() {
	}
	
	@Action
	public void add() {
	}

	private ActionMap getAppActionMap() {
		return Application.getInstance().getContext().getActionMap(this);
	}
	
	public cmdCaseFrame(Network network) {
		super();
		this.network = network;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				ComboBoxModel relationComboBoxModel = new DefaultComboBoxModel(new String [] {"entity",
						"proposition", "act", "individual", "simple proposition", "generic proposition", "rule"});
				semanticClassComboBox = new JComboBox();
				this.add(semanticClassComboBox);
				semanticClassComboBox.setModel(relationComboBoxModel);
				semanticClassComboBox.setBounds(175, 50, 165, 22);
			}
			{
				semanticClassLabel = new JLabel();
				this.add(semanticClassLabel);
				semanticClassLabel.setBounds(175, 19, 130, 15);
				semanticClassLabel.setName("semanticClassLabel");
			}
			{
				relationsLabel = new JLabel();
				this.add(relationsLabel);
				relationsLabel.setBounds(363, 19, 70, 15);
				relationsLabel.setName("relationsLabel");
			}
			{
				caseFrameLabel = new JLabel();
				this.add(caseFrameLabel);
				caseFrameLabel.setBounds(64, 54, 93, 15);
				caseFrameLabel.setName("caseFrameLabel");
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
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(623, 50, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						addButtonMouseClicked(evt);
					}
				});
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(175, 84, 165, 89);
				{
					semanticClassModel = new DefaultListModel();
					semanticClassList = new JList();
					jScrollPane1.setViewportView(semanticClassList);
					semanticClassList.setModel(semanticClassModel);
				}
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(363, 85, 246, 88);
				{
					relationsModel = new DefaultListModel();
					relationsList = new JList();
					jScrollPane2.setViewportView(relationsList);
					relationsList.setModel(relationsModel);
					relationsList.setBounds(363, 144, 161, 85);
				}
			}
			{
				relationSetTextField = new JTextField();
				this.add(relationSetTextField);
				relationSetTextField.setBounds(490, 48, 119, 22);
			}
			{
				DefaultComboBoxModel relationsComboBoxModel = new DefaultComboBoxModel();
				
				String str = "";
				Hashtable<String, Relation> relations = network.getRelations();
				Set<String> set = relations.keySet();

			    Iterator<String> itr = set.iterator();
			    while (itr.hasNext()) {
			      str = itr.next();
			      relationsComboBoxModel.addElement(relations.get(str).getName()) ;
			    }
			    
				relationsComboBox = new JComboBox();
				this.add(relationsComboBox);
				relationsComboBox.setModel(relationsComboBoxModel);
				relationsComboBox.setBounds(363, 47, 121, 22);
				relationsComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						String relations = relationSetTextField.getText();
						if (relations.isEmpty()) {
							relationSetTextField.setText(relationsComboBox.getSelectedItem().toString());
						} else {
							relationSetTextField.setText(relations + "," + relationsComboBox.getSelectedItem().toString());
						}
					}
				});
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		for (int i = 0; i<semanticClassModel.size(); i++) {
			LinkedList<Relation> relationList = new LinkedList<Relation>();
			
			String [] array = relationsModel.get(i).toString().split(",");
			for (int j = 0; j < array.length; j++) {
				try {
					relationList.add(network.getRelation(array[j]));
				} catch (CustomException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			try {
				CaseFrame caseframe = network.defineCaseFrame(semanticClassModel.get(i).toString(),
						relationList);
				if (caseframe == null) {
					JOptionPane.showMessageDialog(this,
			    			  "Case frame number " + i + " wasn't created",
			    			  "Error",
			    			  JOptionPane.ERROR_MESSAGE);
				}
			} catch (CustomException e) {
				JOptionPane.showMessageDialog(this,
		    			  "Case frame number " + i + " already exists",
		    			  "Error",
		    			  JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		
		JOptionPane.showMessageDialog(this,
		"The Case Frames have been successfully defined");
		
		semanticClassModel.removeAllElements();
		relationsModel.removeAllElements();
	}
	
	private void addButtonMouseClicked(MouseEvent evt) {
		semanticClassModel.addElement(semanticClassComboBox.getSelectedItem().toString());
		semanticClassComboBox.setSelectedIndex(0);
		relationsModel.addElement(relationSetTextField.getText());
		relationsComboBox.setSelectedIndex(0);
		relationSetTextField.setText("");
		validate();
	}
}