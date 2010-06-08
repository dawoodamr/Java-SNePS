package snepsui.Commands;

import java.awt.Dimension;
import java.awt.Insets;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import sneps.CaseFrame;
import sneps.CustomException;
import sneps.Network;
import sneps.Relation;
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

/**
 * @author Alia Taher
 */
public class cmdCaseFrame extends javax.swing.JPanel {
	private JComboBox semanticClassComboBox;
	private JLabel semanticClassLabel;
	private JLabel caseFrameLabel;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JButton removeButton;
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
	private SNePSInterface frame;

	@Action
	public void info() {}
	
	@Action
	public void remove() {}
	
	@Action
	public void add() {}

	private ActionMap getAppActionMap() {
		return Application.getInstance().getContext().getActionMap(this);
	}
	
	public cmdCaseFrame(Network network, SNePSInterface frame) {
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
				ComboBoxModel relationComboBoxModel = new DefaultComboBoxModel(new String [] {"Entity","Proposition","Individual"});
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
				caseFrameLabel.setBounds(32, 54, 125, 15);
				caseFrameLabel.setName("caseFrameLabel");
			}
			{
				infoButton = new JButton();
				this.add(infoButton);
				infoButton.setBounds(668, 196, 16, 18);
				infoButton.setAction(getAppActionMap().get("info"));
				infoButton.setFocusable(false);
				infoButton.setFocusPainted(false);
				infoButton.setBorderPainted(false);
				infoButton.setContentAreaFilled(false);
				infoButton.setMargin(new Insets(0,0,0,0));
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
				addButton.setFocusPainted(false);
				addButton.setBorderPainted(false);
				addButton.setContentAreaFilled(false);
				addButton.setMargin(new Insets(0,0,0,0));
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
					semanticClassList.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent evt) {
							int selected = semanticClassList.getSelectedIndex();
							relationsList.setSelectedIndex(selected);
						}
					});
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
					relationsList.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent evt) {
							int selected = relationsList.getSelectedIndex();
							semanticClassList.setSelectedIndex(selected);
						}
					});
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
						if(!relations.contains(relationsComboBox.getSelectedItem().toString())) {
							if (relations.isEmpty()) {
								relationSetTextField.setText(relationsComboBox.getSelectedItem().toString());
							} else {
								relationSetTextField.setText(relations + "," + relationsComboBox.getSelectedItem().toString());
							}
						} else {
							JOptionPane.showMessageDialog(getRootPane(),
									"The relation is already included in the case frame",
									"Warning",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				});
			}
			{
				removeButton = new JButton();
				this.add(removeButton);
				removeButton.setAction(getAppActionMap().get("remove"));
				removeButton.setToolTipText("remove");
				removeButton.setBounds(623, 85, 16, 18);
				removeButton.setFocusPainted(false);
				removeButton.setBorderPainted(false);
				removeButton.setContentAreaFilled(false);
				removeButton.setMargin(new Insets(0,0,0,0));
				removeButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						removeButtonMouseClicked(evt);
					}
				});
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {}
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}
	
	private void addButtonMouseClicked(MouseEvent evt) {
		//Check for a non-empty relation field 
		if(relationSetTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"Please specify the relations included in the case frame, the relation field cannot be empty",
					"Warning",
					JOptionPane.WARNING_MESSAGE);
		} else if (relationsModel.contains(relationSetTextField.getText())) {
			JOptionPane.showMessageDialog(this,
					"The case frame is already included in the list",
					"Warning",
					JOptionPane.WARNING_MESSAGE);
		} else {
			semanticClassModel.addElement(semanticClassComboBox.getSelectedItem().toString());
			semanticClassComboBox.setSelectedIndex(0);
			relationsModel.addElement(relationSetTextField.getText());
			relationSetTextField.setText("");
			validate();
		}
	}
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		for (int i = 0; i<semanticClassModel.size(); i++) {
			LinkedList<Relation> relationList = new LinkedList<Relation>();
			
			String [] array = relationsModel.get(i).toString().split(",");
			for (int j = 0; j < array.length; j++) {
				try {
					relationList.add(network.getRelation(array[j]));
				} catch (CustomException e) {}
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
			}
		}
		
		JOptionPane.showMessageDialog(this,
		"The Case Frames have been successfully defined");
		
		semanticClassModel.removeAllElements();
		relationsModel.removeAllElements();
		
		frame.getNodesTreePanel1().initGUI();
		frame.getNodesTreePanel1().validate();
		frame.getNodesTreePanel1().repaint();
	}
	
	private void removeButtonMouseClicked(MouseEvent evt) {
		try {
			int selected = semanticClassList.getSelectedIndex();
			relationsModel.remove(selected);
			semanticClassModel.remove(selected);
		} catch (Exception e) {}
		
	}
}