package snepsui.Commands;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Iterator;
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

import sneps.CustomException;
import sneps.Network;
import sneps.Relation;




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
public class cmdDefine extends javax.swing.JPanel {
	private JLabel defineLabel;
	private JLabel limitLabel;
	private JLabel adjustLabel;
	private JLabel typeLabel;
	private JLabel realtionsLabel;
	private JTextField relationTextField;
	private JTextField limitTextField;
	private JComboBox adjustComboBox;
	private JComboBox typeComboBox;
	private JList relationsList;
	private JList limitList;
	private JList adjustList;
	private JList typeList;
	private DefaultListModel relationModel;
	private DefaultListModel typeModel;
	private DefaultListModel adjustModel;
	private DefaultListModel limitModel;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane4;
	private JScrollPane jScrollPane3;
	private JButton addButton;
	private JButton infoButton;
	private JButton doneButton;
	private Network network;
	private ComboBoxModel typeComboBoxModel;
	private ComboBoxModel adjustComboBoxModel;

	@Action
    public void add() {
    }
	
	@Action
    public void info() {
    }
	
	private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }
	
	public cmdDefine(Network network) {
		super();
		this.network = network;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				defineLabel = new JLabel();
				this.add(defineLabel);
				defineLabel.setName("defineLabel");
				defineLabel.setBounds(4, 35, 46, 15);
			}
			{
				relationTextField = new JTextField();
				this.add(relationTextField);
				relationTextField.setBounds(50, 32, 146, 22);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(668, 34, 16, 18);
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
				doneButton.setBounds(315, 181, 77, 29);
				doneButton.setName("doneButton");
				doneButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						doneButtonMouseClicked(evt);
					}
				});
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(50, 60, 146, 103);
				{
					relationModel = new DefaultListModel();
					relationsList = new JList();
					jScrollPane1.setViewportView(relationsList);
					relationsList.setModel(relationModel);
					relationsList.setBounds(-14, 131, 156, 100);
					relationsList.setPreferredSize(new java.awt.Dimension(96, 60));
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
				realtionsLabel = new JLabel();
				this.add(realtionsLabel);
				realtionsLabel.setBounds(50, 8, 80, 15);
				realtionsLabel.setName("realtionsLabel");
			}
			{
				typeComboBoxModel = new DefaultComboBoxModel(new String [] {"entity", "proposition", "rule", "non rule", "act" , "individual"});
				typeComboBox = new JComboBox();
				this.add(typeComboBox);
				typeComboBox.setModel(typeComboBoxModel);
				typeComboBox.setBounds(202, 31, 146, 22);
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(202, 60, 146, 103);
				{
					typeModel = new DefaultListModel();
					typeList = new JList();
					jScrollPane2.setViewportView(typeList);
					typeList.setModel(typeModel);
					typeList.setBounds(179, 177, 130, 97);
				}
			}
			{
				typeLabel = new JLabel();
				this.add(typeLabel);
				typeLabel.setBounds(202, 8, 45, 15);
				typeLabel.setName("typeLabel");
			}
			{
				adjustComboBoxModel = new DefaultComboBoxModel(new String[] { "none", "reduce", "expand" });
				adjustComboBox = new JComboBox();
				this.add(adjustComboBox);
				adjustComboBox.setModel(adjustComboBoxModel);
				adjustComboBox.setBounds(360, 31, 144, 22);
			}
			{
				jScrollPane3 = new JScrollPane();
				this.add(jScrollPane3);
				jScrollPane3.setBounds(360, 60, 146, 103);
				{
					adjustModel = new DefaultListModel();
					adjustList = new JList();
					jScrollPane3.setViewportView(adjustList);
					adjustList.setModel(adjustModel);
					adjustList.setBounds(383, 131, 143, 100);
				}
			}
			{
				adjustLabel = new JLabel();
				this.add(adjustLabel);
				adjustLabel.setBounds(360, 8, 54, 15);
				adjustLabel.setName("adjustLabel");
			}
			{
				limitTextField = new JTextField();
				this.add(limitTextField);
				limitTextField.setBounds(515, 32, 142, 22);
			}
			{
				limitLabel = new JLabel();
				this.add(limitLabel);
				limitLabel.setBounds(515, 8, 41, 15);
				limitLabel.setName("limitLabel");
			}
			{
				jScrollPane4 = new JScrollPane();
				this.add(jScrollPane4);
				jScrollPane4.setBounds(515, 60, 146, 103);
				{
					limitModel = new DefaultListModel();
					limitList = new JList();
					jScrollPane4.setViewportView(limitList);
					limitList.setModel(limitModel);
					limitList.setBounds(526, 190, 143, 100);
				}
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addButtonMouseClicked(MouseEvent evt) {
		//Relation
		String relation = relationTextField.getText();
		
		//Check if relation name is not empty
		if (relation.isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"Please fill in a name for the relation, the relation field cannot be empty",
					"Warning",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		//Check for relation name consistency
		String str = "";
		try {
			Hashtable<String, Relation> relations = network.getRelations();
			Set<String> set = relations.keySet();

		    Iterator<String> itr = set.iterator();
		    while (itr.hasNext()) {
		      str = itr.next();
		      if (relation.equals(relations.get(str).getName())) {
		    	  JOptionPane.showMessageDialog(this,
		    			  "The relation name already exits",
		    			  "Error",
		    			  JOptionPane.ERROR_MESSAGE);
		    	  return;
		      }
		    }
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
	    
	    //Check for the relation in the relation list
	    for (int i = 0; i<relationModel.size(); i++) {
	    	if (relation.equals(relationModel.get(i))) {
	    		JOptionPane.showMessageDialog(this,
		    			  "The realtion was alredy added to the list",
		    			  "Error",
		    			  JOptionPane.ERROR_MESSAGE);
		    	  return;
	    	}
	    }
	    
	    //Limit
	    String limitString = limitTextField.getText();
	    int limit = -1;
	    //Check for a non-empty field
	    if(limitString.isEmpty()) {
	    	JOptionPane.showMessageDialog(this,
					"Please fill in a limit for the relation, the limit field cannot be empty",
					"Warning",
					JOptionPane.WARNING_MESSAGE);
			return;
	    }
	    
	    //Check for number consistency
	    try{
	    	limit = Integer.parseInt(limitString);
	    } catch (NumberFormatException e) {
	    	JOptionPane.showMessageDialog(this,
	    			  "The limit has to be a non-negative number",
	    			  "Error",
	    			  JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    
	    if (limit < 0) {
	    	JOptionPane.showMessageDialog(this,
	    			  "The limit has to be a non-negative number",
	    			  "Error",
	    			  JOptionPane.ERROR_MESSAGE);
	    	return;
	    }
	    
	    //Check for limit consistency
	    //Know the limit of each semantic type
		
		relationModel.addElement(relation);
		typeModel.addElement(typeComboBox.getSelectedItem().toString());
		adjustModel.addElement(adjustComboBox.getSelectedItem().toString());
		limitModel.addElement(limit);
		relationTextField.setText("");
		typeList.setSelectedIndex(0);
		adjustComboBox.setSelectedIndex(0);
		limitTextField.setText("");
		validate();
	}
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		try {
			for (int i = 0; i<relationModel.size(); i++) {
				Relation relation = network.defineRelation(relationModel.get(i).toString(), 
				typeModel.get(i).toString(), 
				adjustModel.get(i).toString(), 
				Integer.parseInt(limitModel.get(i).toString()));
				
				if(relation==null) {
					JOptionPane.showMessageDialog(this,
			    			  "The relation " + relationModel.get(i).toString() + " couldn't be defined created",
			    			  "Error",
			    			  JOptionPane.ERROR_MESSAGE);
				}
			}
			
			JOptionPane.showMessageDialog(this,
			"The Relations have been successfully defined");
			
			relationModel.removeAllElements();
			typeModel.removeAllElements();
			adjustModel.removeAllElements();
			limitModel.removeAllElements();
		} catch (CustomException e) {
			JOptionPane.showMessageDialog(this,
	    			  "The relation already exits",
	    			  "Error",
	    			  JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
