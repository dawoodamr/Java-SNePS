package snepsui.Commands;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

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
public class cmdUndefine extends javax.swing.JPanel {
	private JLabel undefineLabel;
	private JButton removeButton;
	private JComboBox relationsComboBox;
	private JLabel relationsLabel;
	private JList relationsList;
	private DefaultListModel listModel;
	private JScrollPane jScrollPane1;
	private JButton doneButton;
	private JButton addButton;
	private JButton infoButton;
	private Network network;
	private SNePSInterface frame;
	
	private String instructions =
		"<html>"+
        "<h3>Undefine a Relation:</h3>"+
        "<ul>"+
        "<li>Select the relation to be undefined from the drop-down list with all the relations define<br/>" +
        "in the network), and then press on the \"+\" button." +
        "<li>Repeat the steps until all the relation to be undefined from the network are chosen and then<br/>" +
        "click on \"Done\" button, a message will appear to confirm the outcome of the operation."+

        "</ul>"+
        "<h3>Restrictions:</h3>"+
        "<ul>"+
        "<li>No predifined relations can be undefine, they are not included in the list."+
        "<li>The same relation cannot be added twice to the list."+
        "</ul>"+
        
        "</html>";
	
	@Action
    public void add() {}
	
	@Action
    public void remove() {}
	
	@Action
    public void info() {}
	
	private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }
	
	public cmdUndefine(Network network, SNePSInterface frame) {
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
				undefineLabel = new JLabel();
				this.add(undefineLabel);
				undefineLabel.setName("undefineLabel");
				undefineLabel.setBounds(184, 35, 59, 15);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(465, 34, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.setFocusPainted(false);
				addButton.setBorderPainted(false);
				addButton.setContentAreaFilled(false);
				addButton.setMargin(new Insets(0,0,0,0));
				addButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						if(listModel.contains(relationsComboBox.getSelectedItem())) {
							JOptionPane.showMessageDialog(getRootPane(),
									"The relation is already included in the list",
									"Warning",
									JOptionPane.WARNING_MESSAGE);
						} else {
							listModel.addElement(relationsComboBox.getSelectedItem().toString());
							relationsComboBox.setSelectedIndex(0);
							validate();
						}
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
				jScrollPane1.setBounds(255, 66, 193, 103);
				{
					listModel = new DefaultListModel();
					relationsList = new JList();
					jScrollPane1.setViewportView(relationsList);
					relationsList.setModel(listModel);
					relationsList.setBounds(255, 6, 187, 100);
				}
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
				infoButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						JOptionPane.showMessageDialog(getRootPane(), 
		                		instructions, 
		                		"Instructions", 
		                		JOptionPane.INFORMATION_MESSAGE,
		                		new ImageIcon("src/snepsui/Interface/resources/icons/info.png"));
					}
				});
			}
			{
				relationsLabel = new JLabel();
				this.add(relationsLabel);
				relationsLabel.setBounds(255, 12, 80, 15);
				relationsLabel.setName("relationsLabel");
			}
			{
				DefaultComboBoxModel relationsComboBoxModel = new DefaultComboBoxModel();
				String str = "";
				Hashtable<String, Relation> relations = network.getRelations();
				Set<String> set = relations.keySet();

			    Iterator<String> itr = set.iterator();
			    while (itr.hasNext()) {
			      str = itr.next();
			      Relation relation = relations.get(str);
			      if(!( relation.getName().equals("ant") || relation.getName().equals("cq") ||
			    		relation.getName().equals("&ant") || relation.getName().equals("min") ||
			    		relation.getName().equals("max") || relation.getName().equals("thresh") ||
			    		relation.getName().equals("threshmax") || relation.getName().equals("arg") ||
			    		relation.getName().equals("object1") || relation.getName().equals("object2") ||
			    		relation.getName().equals("action") || relation.getName().equals("actObject") ||
			    		relation.getName().equals("condition") || relation.getName().equals("then") ||
			    		relation.getName().equals("act") || relation.getName().equals("plan") ||
			    		relation.getName().equals("precondition") || relation.getName().equals("effect") ||
			    		relation.getName().equals("goal") || relation.getName().equals("do") ||
			    		relation.getName().equals("vars") || relation.getName().equals("suchthat") ||
			    		relation.getName().equals("forall") || relation.getName().equals("emin") ||
			    		relation.getName().equals("emax") || relation.getName().equals("etot") ||
			    		relation.getName().equals("pevb"))) {
			    	  relationsComboBoxModel.addElement(relation.getName());
			      }
			    }
			    
				relationsComboBox = new JComboBox();
				this.add(relationsComboBox);
				relationsComboBox.setModel(relationsComboBoxModel);
				relationsComboBox.setBounds(255, 31, 193, 22);
			}
			{
				removeButton = new JButton();
				this.add(removeButton);
				removeButton.setAction(getAppActionMap().get("remove"));
				removeButton.setBounds(465, 66, 16, 18);
				removeButton.setName("removeButton");
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
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		for (int i = 0; i < listModel.size(); i++) {
			network.undefineRelation(listModel.get(i).toString());
		}
		
		if(listModel.size() == 1) {
			JOptionPane.showMessageDialog(this,
			"The Relation has been successfully undefined");
		} else if (listModel.size() > 1) {
			JOptionPane.showMessageDialog(this,
			"The Relations have been successfully undefined");
		}
		
		//Reinitialize drop-down list
		DefaultComboBoxModel relationsComboBoxModel = new DefaultComboBoxModel();
		String str = "";
		Hashtable<String, Relation> relations = network.getRelations();
		Set<String> set = relations.keySet();

	    Iterator<String> itr = set.iterator();
	    while (itr.hasNext()) {
	      str = itr.next();
	      Relation relation = relations.get(str);
	      if(!( relation.getName().equals("ant") || relation.getName().equals("cq") ||
		    		relation.getName().equals("&ant") || relation.getName().equals("min") ||
		    		relation.getName().equals("max") || relation.getName().equals("thresh") ||
		    		relation.getName().equals("threshmax") || relation.getName().equals("arg") ||
		    		relation.getName().equals("object1") || relation.getName().equals("object2") ||
		    		relation.getName().equals("action") || relation.getName().equals("actObject") ||
		    		relation.getName().equals("condition") || relation.getName().equals("then") ||
		    		relation.getName().equals("act") || relation.getName().equals("plan") ||
		    		relation.getName().equals("precondition") || relation.getName().equals("effect") ||
		    		relation.getName().equals("goal") || relation.getName().equals("do") ||
		    		relation.getName().equals("vars") || relation.getName().equals("suchthat") ||
		    		relation.getName().equals("forall") || relation.getName().equals("emin") ||
		    		relation.getName().equals("emax") || relation.getName().equals("etot") ||
		    		relation.getName().equals("pevb"))) {
	    	  relationsComboBoxModel.addElement(relation.getName());
	      }
	    }
	    relationsComboBox.setModel(relationsComboBoxModel);
		listModel.removeAllElements();
		
		this.repaint();
		this.validate();
		
		frame.getNodesTreePanel1().addTreeInfo();
		frame.getMainFrame().validate();
		frame.getMainFrame().repaint();
	}
	
	private void removeButtonMouseClicked(MouseEvent evt) {
		try {
			int seleceted = relationsList.getSelectedIndex();
			listModel.remove(seleceted);
		} catch (Exception e) {}	
	}
}
