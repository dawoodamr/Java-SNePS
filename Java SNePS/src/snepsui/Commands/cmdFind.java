package snepsui.Commands;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import sneps.AndPath;
import sneps.BUnitPath;
import sneps.ComposePath;
import sneps.ConversePath;
import sneps.CustomException;
import sneps.FUnitPath;
import sneps.IrreflexiveRestrictPath;
import sneps.KPlusPath;
import sneps.KStarPath;
import sneps.Network;
import sneps.Node;
import sneps.NodeSet;
import sneps.OrPath;
import sneps.Path;
import sneps.Relation;
import sneps.RelativeComplementPath;
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
public class cmdFind extends javax.swing.JPanel {
	private JLabel findLabel;
	private JButton addButton;
	private JList nodesetList;
	private JButton doneButton;
	private DefaultListModel nodesetModel;
	private JScrollPane jScrollPane1;
	private JLabel contextNameLabel;
	private JButton infoButton;
	private JLabel nodesetLabel;
	private JLabel pathLabel;
	private ButtonGroup group;
	private JList pathList;
	private JButton pathButton;
	private JTextField pathTextField;
	private DefaultListModel pathModel;
	private JScrollPane jScrollPane2;
	private JRadioButton jRadioButton2;
	private JRadioButton jRadioButton1;
	private JComboBox relationComboBox;
	private JTextField nodesetTextField;
	private JComboBox nodesetComboBox;
	private JComboBox contextNameComboBox;
	private JComboBox pathComboBox;
	private JButton buildButton;
	private Network network;
	private LinkedList<Node> nodes;
	private SNePSInterface frame;
	private LinkedList<Path> listModelPaths;
	private LinkedList<Path> paths;
	
	@Action
    public void add() {
    	
    }
	
	@Action
    public void build() {
    	
    }
	
	@Action
    public void info() {
    	
    }
	
	private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }
	
	public cmdFind(Network network, SNePSInterface frame) {
		super();
		listModelPaths = new LinkedList<Path>();
		paths = new LinkedList<Path>();
		this.frame = frame;
		this.network = network;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				findLabel = new JLabel();
				this.add(findLabel);
				findLabel.setName("findLabel");
				findLabel.setBounds(5, 28, 54, 15);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(547, 56, 16, 18);
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
				doneButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						doneButtonActionPerformed(evt);
					}
				});
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(348, 80, 193, 95);
				{
					nodesetModel = new DefaultListModel();
					nodesetList = new JList();
					jScrollPane1.setViewportView(nodesetList);
					nodesetList.setModel(nodesetModel);
					nodesetList.setBounds(475, 117, 187, 92);
				}
			}
			{
				pathLabel = new JLabel();
				this.add(pathLabel);
				pathLabel.setBounds(85, 4, 70, 15);
				pathLabel.setName("pathLabel");
			}
			{
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setBounds(348, 4, 83, 15);
				nodesetLabel.setName("nodesetLabel");
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
				contextNameLabel = new JLabel();
				this.add(contextNameLabel);
				contextNameLabel.setName("contextNameLabel");
				contextNameLabel.setBounds(556, 77, 107, 19);
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(85, 80, 223, 97);
				{
					pathModel = new DefaultListModel();
					pathList = new JList();
					jScrollPane2.setViewportView(pathList);
					pathList.setModel(pathModel);
					pathList.setBounds(62, 154, 204, 100);
					pathList.setPreferredSize(new java.awt.Dimension(172, 63));
				}
			}
			{
				buildButton = new JButton();
				this.add(buildButton);
				buildButton.setBounds(568, 54, 18, 20);
				buildButton.setAction(getAppActionMap().get("build"));
				buildButton.setFocusable(false);
				buildButton.setToolTipText("build");
				buildButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						buildButtonActionPerformed(evt);
					}
				});
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
				pathComboBox.setBounds(85, 51, 114, 22);
				pathComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						pathComboBoxActionPerformed(evt);
					}
				});
			}
			{
				ComboBoxModel contextNameComboBoxModel = new DefaultComboBoxModel();
				contextNameComboBox = new JComboBox();
				this.add(contextNameComboBox);
				contextNameComboBox.setModel(contextNameComboBoxModel);
				contextNameComboBox.setBounds(557, 108, 121, 22);
			}
			{
				DefaultComboBoxModel nodesetComboBoxModel = new DefaultComboBoxModel();
				
				String str = "";
				Hashtable<String, Node> nodes = network.getNodes();
				Set<String> set = nodes.keySet();

			    Iterator<String> itr = set.iterator();
			    while (itr.hasNext()) {
			      str = itr.next();
			      nodesetComboBoxModel.addElement(nodes.get(str).getIdentifier()) ;
			    }
			    
				nodesetComboBox = new JComboBox();
				this.add(nodesetComboBox);
				nodesetComboBox.setModel(nodesetComboBoxModel);
				nodesetComboBox.setBounds(348, 26, 193, 22);
				nodesetComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						String nodeset = nodesetTextField.getText();
						if (nodeset.isEmpty()) {
							nodesetTextField.setText(nodesetComboBox.getSelectedItem().toString());
						} else {
							nodesetTextField.setText(nodeset + "," + nodesetComboBox.getSelectedItem().toString());
						}
					}
				});
			}
			{
				nodesetTextField = new JTextField();
				this.add(nodesetTextField);
				nodesetTextField.setBounds(348, 53, 193, 22);
				nodesetTextField.setName("nodesetTextField");
			}
			{
				DefaultComboBoxModel relationComboBoxModel = new DefaultComboBoxModel();
				
				String str = "";
				Hashtable<String, Relation> relations = network.getRelations();
				Set<String> set = relations.keySet();

			    Iterator<String> itr = set.iterator();
			    while (itr.hasNext()) {
			      str = itr.next();
			      Path path = relations.get(str).getPath();
			      if(path != null) {
			    	  relationComboBoxModel.addElement(relations.get(str).getName() + ": " 
			    			  + frame.getsNePSULPanel1().getMenuDrivenCommands().createPath(relations.get(str).getPath()));
			      }
			    }
				relationComboBox = new JComboBox();
				this.add(relationComboBox);
				relationComboBox.setModel(relationComboBoxModel);
				relationComboBox.setBounds(84, 24, 224, 22);
			}
			{
				jRadioButton1 = new JRadioButton();
				this.add(jRadioButton1);
				jRadioButton1.setBounds(59, 29, 25, 17);
				jRadioButton1.setSelected(true);
			}
			{
				jRadioButton2 = new JRadioButton();
				this.add(jRadioButton2);
				jRadioButton2.setBounds(60, 56, 25, 17);
			}
			{
				group = new ButtonGroup();
				group.add(jRadioButton1);
				group.add(jRadioButton2);
			}
			{
				pathTextField = new JTextField();
				this.add(pathTextField);
				pathTextField.setBounds(205, 52, 103, 22);
			}
			{
				pathButton = new JButton();
				this.add(pathButton);
				pathButton.setBounds(314, 53, 23, 19);
				pathButton.setName("pathButton");
				pathButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						pathButtonActionPerformed(evt);
					}
				});
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void buildButtonActionPerformed(ActionEvent evt) {
		JFrame popupFrame = new JFrame("Build");
		popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popupFrame.getContentPane().add(new cmdBuild(network, frame));
		popupFrame.pack();
		popupFrame.setVisible(true);
	}

	public void setNodes(LinkedList<Node> nodes) {
		this.nodes = nodes;
	}

	public LinkedList<Node> getNodes() {
		return nodes;
	}
	
	private void addButtonMouseClicked(MouseEvent evt) {
		try {
			if(jRadioButton1.isSelected()) {
				pathModel.addElement(relationComboBox.getSelectedItem().toString());
				relationComboBox.setSelectedIndex(0);
				String selected = relationComboBox.getSelectedItem().toString();
				Relation relation = network.getRelation(selected.substring(0, selected.indexOf(":")));
				listModelPaths.add(relation.getPath());
			} else if (jRadioButton2.isSelected()) {
				if(pathComboBox.getSelectedItem().equals("unitpath") || pathComboBox.getSelectedItem().equals("unitpath-")) {
					JOptionPane.showMessageDialog(this, 
							"Choose a path type",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
		
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
					String completePath = frame.getsNePSULPanel1().getMenuDrivenCommands().createPath(listModelPaths.getLast());
					pathModel.addElement(completePath);
					
					//Reset TestField
					pathTextField.setText("");
					
					//Delete previous paths
					for(int i = 0; i < paths.size(); i++) {
						paths.remove(i);
					}
				}
			}
			
			nodesetModel.addElement(nodesetTextField.getText());
			nodesetTextField.setText("");
		} catch (CustomException e) {
			
		}
	}
	
	private void pathButtonActionPerformed(ActionEvent evt) {
		JFrame popupFrame = new JFrame("Path");
		popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final cmdPath pathPanel = new cmdPath(network, frame, popupFrame);
		popupFrame.getContentPane().add(pathPanel);
		popupFrame.pack();
		popupFrame.setVisible(true);
		Point point = frame.getsNePSULPanel1().getMenuDrivenCommands().cascadePosition();
		popupFrame.setLocation(point);
		doneButton.setEnabled(false);
		popupFrame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				paths.add(pathPanel.getResultPath());
				
				String currentPath = frame.getsNePSULPanel1().getMenuDrivenCommands().createPath(pathPanel.getResultPath());
				if(pathTextField.getText().isEmpty()) {
					pathTextField.setText(currentPath);
				} else {
					String previousPath = pathTextField.getText();
					pathTextField.setText(previousPath + ", " + currentPath);
				}
				
				frame.getsNePSULPanel1().getMenuDrivenCommands().cascadeBack();
				doneButton.setEnabled(true);
			}
		});
	}
	
	private void pathComboBoxActionPerformed(ActionEvent evt) {
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
					
					if(pathTextField.getText().isEmpty()) {
						pathTextField.setText(relation);
					} else {
						String previousPath = pathTextField.getText();
						pathTextField.setText(previousPath + ", " + relation);
					}
				} catch (CustomException e) {
					e.printStackTrace();
				}
			} else if (pathComboBox.getSelectedItem().toString().equals("unitpath-")) {
				try {
					Path path = new BUnitPath(network.getRelation(relation).getName());
					paths.add(path);
					
					if(pathTextField.getText().isEmpty()) {
						pathTextField.setText(relation + "-");
					} else {
						String previousPath = pathTextField.getText();
						pathTextField.setText(previousPath + ", " + relation + "-");
					}
				} catch (CustomException e) {
					e.printStackTrace();
				}
			}
		} else {
			return;
		}
	}
	
	public LinkedList<Path> getListModelPaths() {
		return listModelPaths;
	}

	public void setListModelPaths(LinkedList<Path> listModelPaths) {
		this.listModelPaths = listModelPaths;
	}

	public LinkedList<Path> getPaths() {
		return paths;
	}

	public void setPaths(LinkedList<Path> paths) {
		this.paths = paths;
	}
	
	private void doneButtonActionPerformed(ActionEvent evt) {
		try {
			Object [][] array = new Object[listModelPaths.size()][2];
			for(int i = 0; i < listModelPaths.size(); i++) {
				String [] nodeArray = nodesetModel.get(i).toString().split(",");
				LinkedList<Node> nodelist = new LinkedList<Node>();
				
				for(int j = 0; j < nodeArray.length; j++) {
					nodelist.add(network.getNode(nodeArray[j]));
				}
				
				Path path = listModelPaths.get(i);
				NodeSet nodeset = new NodeSet(nodelist);
				
				array[i][0] = path;
				array[i][1] = nodeset;
			}
			
//			NodeSet resultNodeSet = network.find(array);
//			LinkedList<Node> resultnodes = resultNodeSet.getNodes();
//			if(resultnodes.isEmpty()) {
//				JOptionPane.showMessageDialog(this, "No nodes were found");
//			} else {
//				frame.getsNePSULPanel1().getMenuDrivenCommands().nodeInfo(resultnodes);
//			}
			
			pathModel.removeAllElements();
			nodesetModel.removeAllElements();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
