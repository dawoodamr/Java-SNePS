package snepsui.Commands;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import sneps.AndPath;
import sneps.BUnitPath;
import sneps.ComposePath;
import sneps.ConversePath;
import sneps.CustomException;
import sneps.DomainRestrictPath;
import sneps.FUnitPath;
import sneps.IrreflexiveRestrictPath;
import sneps.KPlusPath;
import sneps.KStarPath;
import sneps.Network;
import sneps.OrPath;
import sneps.Path;
import sneps.RangeRestrictPath;
import sneps.Relation;
import sneps.RelativeComplementPath;


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
public class cmdUndefinePath extends javax.swing.JPanel {
	private JLabel definePathLabel;
	private JLabel pathLabel;
	private DefaultListModel relationModel;
	private DefaultListModel pathModel;
	private JScrollPane jScrollPane1;
	private JComboBox pathsComboBox;
	private JList pathsList;
	private JButton addButton;
	private JButton doneButton;
	private JButton infoButton;
	private Network network;
	private DefaultListModel pathsListModel;
	
	@Action
    public void add() {
    	
    }
	
	@Action
    public void info() {
    	
    }
	
	private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }
	
	public cmdUndefinePath(Network network) {
		super();
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
			    			  + createPath(relations.get(str).getPath()));
			      }
			    }
				
				pathsComboBox = new JComboBox();
				this.add(pathsComboBox);
				pathsComboBox.setModel(pathsComboBoxModel);
				pathsComboBox.setBounds(231, 32, 234, 22);
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String createPath(Path path) {
		if(path instanceof AndPath) {
			AndPath currentPath = (AndPath) path;
		} else if (path instanceof ComposePath) {
			ComposePath currentPath = (ComposePath) path;
		} else if (path instanceof ConversePath) {
			ConversePath currentPath = (ConversePath) path;
		} else if (path instanceof DomainRestrictPath) {
			DomainRestrictPath currentPath = (DomainRestrictPath) path;
		} else if (path instanceof IrreflexiveRestrictPath) {
			IrreflexiveRestrictPath currentPath = (IrreflexiveRestrictPath) path;
		} else if (path instanceof KPlusPath) {
			KPlusPath currentPath = (KPlusPath) path;
		} else if (path instanceof KStarPath) {
			KStarPath currentPath = (KStarPath) path;
		} else if (path instanceof OrPath) {
			OrPath currentPath = (OrPath) path;
		} else if (path instanceof RangeRestrictPath) {
			RangeRestrictPath currentPath = (RangeRestrictPath) path;
		} else if (path instanceof RelativeComplementPath) {
			RelativeComplementPath currentPath = (RelativeComplementPath) path;
		} else if (path instanceof FUnitPath) {
			FUnitPath currentPath = (FUnitPath) path;
		} else if (path instanceof BUnitPath) {
			BUnitPath currentPath = (BUnitPath) path;
		}
		return "";
	}
	
	private void addButtonMouseClicked(MouseEvent evt) {
		pathsListModel.addElement(pathsComboBox.getSelectedItem().toString());
		pathsComboBox.setSelectedIndex(0);
	}
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		for(int i = 0; i < pathsListModel.size(); i++) {
			try {
				network.undefinePath(network.getRelation(pathsListModel.get(i).toString()));
			} catch (CustomException e) {
				e.printStackTrace();
			}
		}
	}
}
