package snepsui.Commands;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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
public class cmdDefinePath extends javax.swing.JPanel {
	private JLabel definePathLabel;
	private JLabel pathLabel;
	private JLabel relationLabel;
	private JComboBox pathComboBox;
	private JList pathList;
	private JList relationList;
	private DefaultListModel relationModel;
	private DefaultListModel pathModel;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JTextField pathTextField;
	private JComboBox relationComboBox;
	private JButton addButton;
	private JButton doneButton;
	private JButton pathButton;
	private JButton infoButton;
	private Network network;
	private LinkedList<Path> listModelPaths;
	private LinkedList<Path> paths;
	
	@Action
    public void add() {}
	
	@Action
    public void info() {}
	
	private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }
	
	public cmdDefinePath(Network network) {
		super();
		listModelPaths = new LinkedList<Path>();
		paths = new LinkedList<Path>();
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
				definePathLabel.setBounds(7, 34, 86, 15);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(572, 33, 16, 18);
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
				doneButton.setVisible(false);
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(307, 64, 259, 99);
				{
					pathModel = new DefaultListModel();
					pathList = new JList();
					jScrollPane1.setViewportView(pathList);
					pathList.setModel(pathModel);
					pathList.setBounds(316, 179, 256, 81);
				}
			}
			{
				relationLabel = new JLabel();
				this.add(relationLabel);
				relationLabel.setBounds(98, 12, 70, 15);
				relationLabel.setName("relationLabel");
			}
			{
				pathLabel = new JLabel();
				this.add(pathLabel);
				pathLabel.setBounds(307, 12, 66, 15);
				pathLabel.setName("pathLabel");
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(98, 64, 189, 99);
				{
					relationModel = new DefaultListModel();
					relationList = new JList();
					jScrollPane2.setViewportView(relationList);
					relationList.setModel(relationModel);
					relationList.setBounds(64, 196, 186, 82);
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
							new String[] {"converse", "compose", "kstar", "kplus", "or", "and", "not",
									"relative-complement", "irreflexive-restrict", "exception", "domain-restrict", 
									"range-restrict", "unitpath", "unitpath-"});
				pathComboBox = new JComboBox();
				this.add(pathComboBox);
				pathComboBox.setModel(pathComboBoxModel);
				pathComboBox.setBounds(307, 30, 115, 22);
				pathComboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent evt) {
						pathComboBoxItemStateChanged(evt);
					}
				});
			}
			{
				pathButton = new JButton();
				this.add(pathButton);
				pathButton.setBounds(593, 31, 40, 22);
				pathButton.setName("pathButton");
				pathButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						pathButtonMouseClicked(evt);
					}
				});
				pathButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						pathButtonActionPerformed(evt);
					}
				});
			}
			{
				DefaultComboBoxModel relationsComboBoxModel = new DefaultComboBoxModel();
				String str1 = "";
				Hashtable<String, Relation> relations1 = network.getRelations();
				Set<String> set1 = relations1.keySet();

			    Iterator<String> itr1 = set1.iterator();
			    while (itr1.hasNext()) {
			      str1 = itr1.next();
			      relationsComboBoxModel.addElement(relations1.get(str1).getName()) ;
			    }
			    
				relationComboBox = new JComboBox();
				this.add(relationComboBox);
				relationComboBox.setModel(relationsComboBoxModel);
				relationComboBox.setBounds(98, 30, 189, 22);
			}
			{
				pathTextField = new JTextField();
				this.add(pathTextField);
				pathTextField.setBounds(428, 31, 138, 22);
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
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void pathButtonActionPerformed(ActionEvent evt) {
		JFrame frame = new JFrame("Path");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cmdPath pathPanel = new cmdPath(network);
		frame.getContentPane().add(pathPanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void addButtonMouseClicked(MouseEvent evt) {
		try {
			//Add Relation
			Relation relation = network.getRelation(relationComboBox.getSelectedItem().toString());
			relationModel.addElement(relation.getName());

			//Add Path
			String pathType = pathComboBox.getSelectedItem().toString();
			if (pathType.equals("converse")) {
				Path path = new ConversePath(paths.getFirst());
				listModelPaths.add(path);
			} else if (pathType.equals("compose")) {
				Path path = new ComposePath(paths);
				listModelPaths.add(path);
			} else if (pathType.equals("kstar")) {
				Path path = new KStarPath(paths.getFirst());
				listModelPaths.add(path);
			} else if (pathType.equals("kplus")) {
				Path path = new KPlusPath(paths.getFirst());
				listModelPaths.add(path);
			} else if (pathType.equals("or")) {
				Path path = new OrPath(paths);
				listModelPaths.add(path);
			} else if (pathType.equals("and")) {
				Path path = new AndPath(paths);
				listModelPaths.add(path);
			} else if (pathType.equals("relative-complement")) {
				Path path = new RelativeComplementPath(paths.get(0), paths.get(1));
				listModelPaths.add(path);
			} else if (pathType.equals("irreflexive-restrict")) {
				Path path = new IrreflexiveRestrictPath(paths.getFirst());
				listModelPaths.add(path);
			} else if (pathType.equals("domain-restrict")) {
				//Path path = new DomainRestrictPath(q, node, p);
				//network.definePath(relation, path);
			} else if (pathType.equals("range-restrict")) {
				//Path path = new RangeRestrictPath(p, q, node);
				//network.definePath(relation, path);
			}
			
			//Add the path to the List
			
			
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}
	
	private void pathButtonMouseClicked(MouseEvent evt) {
		cmdPath pathPanel = new cmdPath(network);
		
	    int result = JOptionPane.showConfirmDialog(  
	    	    this, pathPanel, "title", JOptionPane.PLAIN_MESSAGE
	    	);
	}
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		try {
			for(int i = 0; i < listModelPaths.size(); i++) {
				Relation relation = network.getRelation(relationModel.get(i).toString());
				Path path = listModelPaths.get(i);
				network.definePath(relation, path);
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}
	
	private void pathComboBoxItemStateChanged(ItemEvent evt) {
		if(pathComboBox.getSelectedItem().toString().equals("unitpath") || 
				pathComboBox.getSelectedItem().toString().equals("unitpath-")) {
			
			String relation = "";
			String relationStr = "";
			int relationCounter = 0;
			Icon icon = new ImageIcon();
			
			Hashtable<String, Relation> relations = network.getRelations();
			Object [] relationPossibilities = new Object[relations.size()]; 
			Set<String> relationSet = relations.keySet();

		    Iterator<String> relationItr = relationSet.iterator();
		    while (relationItr.hasNext()) {
		    	relationStr = relationItr.next();
		    	relationPossibilities[relationCounter] = relations.get(relationStr).getName();
		    	relationCounter++;
		    }
			
			relation = (String) JOptionPane.showInputDialog(
						getRootPane(),
						"Choose the Node you want to create:",
						"Create a Node",
						JOptionPane.OK_OPTION,
						icon,
						relationPossibilities,
						relationPossibilities[0]);
			
			if (pathComboBox.getSelectedItem().toString().equals("unitpath")) {
				try {
					Path path = new FUnitPath(network.getRelation(relation).getName());
					paths.add(path);
				} catch (CustomException e) {
					e.printStackTrace();
				}
			} else if (pathComboBox.getSelectedItem().toString().equals("unitpath-")) {
				try {
					Path path = new BUnitPath(network.getRelation(relation).getName());
					paths.add(path);
				} catch (CustomException e) {
					e.printStackTrace();
				}
			}
		} else {
			return;
		}
	}
}
