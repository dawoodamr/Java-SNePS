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

import sneps.CaseFrame;
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
public class cmdUndefine extends javax.swing.JPanel {
	private JLabel undefineLabel;
	private JComboBox relationsComboBox;
	private JLabel relationsLabel;
	private JList relationsList;
	private DefaultListModel listModel;
	private JScrollPane jScrollPane1;
	private JButton doneButton;
	private JButton addButton;
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
	
	public cmdUndefine(Network network) {
		super();
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
				addButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						listModel.addElement(relationsComboBox.getSelectedItem().toString());
						relationsComboBox.setSelectedIndex(0);
						validate();
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
				infoButton.setToolTipText("info");
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
			      relationsComboBoxModel.addElement(relations.get(str).getName()) ;
			    }
			    
				relationsComboBox = new JComboBox();
				this.add(relationsComboBox);
				relationsComboBox.setModel(relationsComboBoxModel);
				relationsComboBox.setBounds(255, 31, 193, 22);
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		for (int i = 0; i < listModel.size(); i++) {
			network.undefineRelation(listModel.get(i).toString());
		}
		
		JOptionPane.showMessageDialog(this,
		"The Relations have been successfully undefined");
		
		listModel.removeAllElements();
		this.repaint();
		this.validate();
	}
}
