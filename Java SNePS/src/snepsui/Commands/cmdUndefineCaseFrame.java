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

import sneps.CaseFrame;
import sneps.Network;
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
public class cmdUndefineCaseFrame extends javax.swing.JPanel {
	private JLabel undefineLabel;
	private JComboBox caseframeComboBox;
	private JButton removeButton;
	private JLabel relationsLabel;
	private JList relationsList;
	private DefaultListModel listModel;
	private JScrollPane jScrollPane1;
	private JButton doneButton;
	private JButton addButton;
	private JButton infoButton;
	private Network network;
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
	
	public cmdUndefineCaseFrame(Network network, SNePSInterface frame) {
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
				undefineLabel.setBounds(97, 35, 146, 15);
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
						if(listModel.contains(caseframeComboBox.getSelectedItem())) {
							JOptionPane.showMessageDialog(getRootPane(),
									"The case frame is already included in the list",
									"Warning",
									JOptionPane.WARNING_MESSAGE);
						} else {
							listModel.addElement(caseframeComboBox.getSelectedItem().toString());
							caseframeComboBox.setSelectedIndex(0);
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
			}
			{
				relationsLabel = new JLabel();
				this.add(relationsLabel);
				relationsLabel.setBounds(255, 12, 91, 15);
				relationsLabel.setName("relationsLabel");
			}
			{
				DefaultComboBoxModel caseframeComboBoxModel = new DefaultComboBoxModel();
				String str = "";
				Hashtable<String, CaseFrame> caseframes = network.getCaseFrames();
				Set<String> set = caseframes.keySet();

			    Iterator<String> itr = set.iterator();
			    while (itr.hasNext()) {
			      str = itr.next();
			      CaseFrame caseframe = caseframes.get(str);
			      if(!(caseframe.getId().equals("&ant,cq") || caseframe.getId().equals("arg,thresh,threshmax") 
			    	  || caseframe.getId().equals("arg,max,min") || caseframe.getId().equals("object1,object2")
			    	  || caseframe.getId().equals("actObject,action") || caseframe.getId().equals("ant,cq")
			    	  || caseframe.getId().equals("&ant,cq,thresh") || caseframe.getId().equals("act,precondition")
			    	  || caseframe.getId().equals("goal,plan") || caseframe.getId().equals("&ant,cq,emax,emin,etot,pevb")
			    	  || caseframe.getId().equals("&ant,cq,forall") || caseframe.getId().equals("action,do,suchthat,vars")
			    	  || caseframe.getId().equals("act,condition") || caseframe.getId().equals("act,effect")
			    	  || caseframe.getId().equals("act,plan"))) {
			    	  caseframeComboBoxModel.addElement(caseframe.getId()) ;
			      }	  
			    }
				
				caseframeComboBox = new JComboBox();
				this.add(caseframeComboBox);
				caseframeComboBox.setModel(caseframeComboBoxModel);
				caseframeComboBox.setBounds(255, 31, 193, 22);
			}
			{
				removeButton = new JButton();
				this.add(removeButton);
				removeButton.setAction(getAppActionMap().get("remove"));
				removeButton.setName("removeButton");
				removeButton.setBounds(465, 66, 16, 18);
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
			network.undefineCaseFrame(listModel.get(i).toString());
		}
		
		if(listModel.size() == 1) {
			JOptionPane.showMessageDialog(this,
			"The Case frame has been successfully undefined");
		} else if (listModel.size() > 1) {
			JOptionPane.showMessageDialog(this,
			"The Case frames have been successfully undefined");
		}
		
		//Reinitialize drop-down list
		DefaultComboBoxModel caseframeComboBoxModel = new DefaultComboBoxModel();
		String str = "";
		Hashtable<String, CaseFrame> caseframes = network.getCaseFrames();
		Set<String> set = caseframes.keySet();

	    Iterator<String> itr = set.iterator();
	    while (itr.hasNext()) {
	    	str = itr.next();
	    	CaseFrame caseframe = caseframes.get(str);
	    	if(!(caseframe.getId().equals("&ant,cq") || caseframe.getId().equals("arg,thresh,threshmax") 
			    	  || caseframe.getId().equals("arg,max,min") || caseframe.getId().equals("object1,object2")
			    	  || caseframe.getId().equals("actObject,action") || caseframe.getId().equals("ant,cq")
			    	  || caseframe.getId().equals("&ant,cq,thresh") || caseframe.getId().equals("act,precondition")
			    	  || caseframe.getId().equals("goal,plan") || caseframe.getId().equals("&ant,cq,emax,emin,etot,pevb")
			    	  || caseframe.getId().equals("&ant,cq,forall") || caseframe.getId().equals("action,do,suchthat,vars")
			    	  || caseframe.getId().equals("act,condition") || caseframe.getId().equals("act,effect")
			    	  || caseframe.getId().equals("act,plan"))) {
		    	  caseframeComboBoxModel.addElement(caseframe.getId()) ;
		      }	
	    }
	    caseframeComboBox.setModel(caseframeComboBoxModel);
	    
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
