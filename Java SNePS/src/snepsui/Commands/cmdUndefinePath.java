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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import sneps.CustomException;
import sneps.Network;
import sneps.Path;
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
public class cmdUndefinePath extends javax.swing.JPanel {
	private JLabel definePathLabel;
	private JLabel pathLabel;
	private JScrollPane jScrollPane1;
	private JComboBox pathsComboBox;
	private JButton removeButton;
	private JList pathsList;
	private JButton addButton;
	private JButton doneButton;
	private JButton infoButton;
	private Network network;
	private DefaultListModel pathsListModel;
	private SNePSInterface frame;
	
	@Action
    public void add() {}
	
	@Action
    public void remove() {}
	
	@Action
    public void info() {}
	
	private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }
	
	public cmdUndefinePath(Network network, SNePSInterface frame) {
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
				definePathLabel = new JLabel();
				this.add(definePathLabel);
				definePathLabel.setName("definePathLabel");
				definePathLabel.setBounds(89, 36, 116, 15);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(477, 35, 16, 18);
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
				doneButton.setBounds(314, 185, 77, 29);
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
				jScrollPane1.setBounds(231, 59, 235, 103);
				{
					pathsListModel = new DefaultListModel();
					pathsList = new JList();
					jScrollPane1.setViewportView(pathsList);
					pathsList.setModel(pathsListModel);
				}
			}
			{
				pathLabel = new JLabel();
				this.add(pathLabel);
				pathLabel.setBounds(231, 12, 66, 15);
				pathLabel.setName("pathLabel");
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
				DefaultComboBoxModel pathsComboBoxModel = new DefaultComboBoxModel();
				
				String str = "";
				Hashtable<String, Relation> relations = network.getRelations();
				Set<String> set = relations.keySet();

			    Iterator<String> itr = set.iterator();
			    while (itr.hasNext()) {
			      str = itr.next();
			      Path path = relations.get(str).getPath();
			      if(path != null) {
			    	  pathsComboBoxModel.addElement(relations.get(str).getName() + ": " 
			    			  + frame.getsNePSULPanel1().getMenuDrivenCommands().createPath(path));
			      }
			    }
				
				pathsComboBox = new JComboBox();
				this.add(pathsComboBox);
				pathsComboBox.setModel(pathsComboBoxModel);
				pathsComboBox.setBounds(231, 32, 234, 22);
			}
			{
				removeButton = new JButton();
				this.add(removeButton);
				removeButton.setAction(getAppActionMap().get("remove"));
				removeButton.setBounds(477, 58, 16, 18);
				removeButton.setName("removeButton");
				removeButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						removeButtonMouseClicked(evt);
					}
				});
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {}
	}
	
	private void addButtonMouseClicked(MouseEvent evt) {
		pathsListModel.addElement(pathsComboBox.getSelectedItem().toString());
		pathsComboBox.setSelectedIndex(0);
	}
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		for(int i = 0; i < pathsListModel.size(); i++) {
			try {
				String relation = pathsListModel.get(i).toString();
				network.undefinePath( network.getRelation(relation.substring(0, relation.indexOf(":"))));
			} catch (CustomException e) {}
		}
		
		if(pathsListModel.size() == 1) {
			JOptionPane.showMessageDialog(this,
			"The Path has been successfully undefined");
		} else if(pathsListModel.size() > 1) {
			JOptionPane.showMessageDialog(this,
			"The Paths have been successfully undefined");
		}
	
		DefaultComboBoxModel pathsComboBoxModel = new DefaultComboBoxModel();
		
		String str = "";
		Hashtable<String, Relation> relations = network.getRelations();
		Set<String> set = relations.keySet();

	    Iterator<String> itr = set.iterator();
	    while (itr.hasNext()) {
	      str = itr.next();
	      Path path = relations.get(str).getPath();
	      if(path != null) {
	    	  pathsComboBoxModel.addElement(relations.get(str).getName() + ": " 
	    			  + frame.getsNePSULPanel1().getMenuDrivenCommands().createPath(path));
	      }
	    }
	    pathsComboBox.setModel(pathsComboBoxModel);
	    
	    pathsListModel.removeAllElements();
	}
	
	private void removeButtonMouseClicked(MouseEvent evt) {
		int selected = pathsList.getSelectedIndex();
		pathsListModel.remove(selected);
	}
}
