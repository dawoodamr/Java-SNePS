package snepsui.Commands;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Vector;

import javax.swing.ActionMap;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import sneps.CaseFrame;
import sneps.CustomException;
import sneps.Network;
import sneps.Node;
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
public class cmdAdd extends javax.swing.JPanel {
	private JLabel addLabel;
	private JButton doneButton;
	private JScrollPane jScrollPane1;
	private JTable relationNodesetTable;
	private JComboBox caseframeComboBox;
	private JLabel caseframeLabel;
	private JLabel contextNameLabel;
	private JRadioButton contextNameRadioButton1;
	private JTextField contextNameTextField;
	private JRadioButton contextNameRadioButton2;
	private JButton infoButton;
	private Network network;
	private DefaultTableModel relationNodesetTableModel;
	private JComboBox options;
	private JTextField relationTextField;
	private JTextField nodesetTextField;
	private LinkedList<Node> nodes;

	public cmdAdd(Network network) {
		super();
		this.network = network;
		initGUI();
	}

	@Action
	public void info() {
	}

	private ActionMap getAppActionMap() {
		return Application.getInstance().getContext().getActionMap(this);
	}

	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				addLabel = new JLabel();
				this.add(addLabel);
				addLabel.setName("addLabel");
				addLabel.setBounds(12, 28, 59, 15);
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
				options = new JComboBox();
				DefaultComboBoxModel optionsComboBoxModel = new DefaultComboBoxModel();
				optionsComboBoxModel.addElement("String");
				optionsComboBoxModel.addElement("build");
				optionsComboBoxModel.addElement("find");
				optionsComboBoxModel.addElement("assert");
				options.setModel(optionsComboBoxModel);
				options.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						optionsComboBoxActionPerformed(e);
					}
				});
			}
			{
				relationTextField = new JTextField();
				relationTextField.setEditable(false);
			}
			{
				nodesetTextField = new JTextField();
				nodesetTextField.setEditable(false);
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(80, 58, 440, 103);
				{
					relationNodesetTableModel = new DefaultTableModel();
					relationNodesetTableModel.addColumn("Relation");
					relationNodesetTableModel.addColumn("Nodeset");
					relationNodesetTableModel.addColumn("");
					relationNodesetTable = new JTable();
					jScrollPane1.setViewportView(relationNodesetTable);
					relationNodesetTable.setModel(relationNodesetTableModel);
					relationNodesetTable.isRowSelected(0);
					
					relationNodesetTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(relationTextField));
					relationNodesetTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(nodesetTextField));
					relationNodesetTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(options));
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
				contextNameRadioButton2 = new JRadioButton();
				this.add(contextNameRadioButton2);
				contextNameRadioButton2.setBounds(532, 86, 30, 29);
				contextNameRadioButton2.setName("contextNameRadioButton2");
			}
			{
				contextNameTextField = new JTextField();
				this.add(contextNameTextField);
				contextNameTextField.setName("contextNameTextField");
				contextNameTextField.setBounds(562, 92, 116, 23);
			}
			{
				contextNameRadioButton1 = new JRadioButton();
				this.add(contextNameRadioButton1);
				contextNameRadioButton1.setName("contextNameRadioButton1");
				contextNameRadioButton1.setBounds(532, 62, 123, 21);
			}
			{
				contextNameLabel = new JLabel();
				this.add(contextNameLabel);
				contextNameLabel.setName("contextNameLabel");
				contextNameLabel.setBounds(532, 25, 123, 21);
			}
			{
				caseframeLabel = new JLabel();
				this.add(caseframeLabel);
				caseframeLabel.setBounds(80, 28, 90, 15);
				caseframeLabel.setName("caseframeLabel");
			}
			{
				DefaultComboBoxModel caseframeComboBoxModel = new DefaultComboBoxModel();
				
				String str = "";
				Hashtable<String, CaseFrame> caseframes = network.getCaseFrames();
				Set<String> set = caseframes.keySet();

			    Iterator<String> itr = set.iterator();
			    while (itr.hasNext()) {
			      str = itr.next();
			      caseframeComboBoxModel.addElement(caseframes.get(str).getId()) ;
			    }
			    
				caseframeComboBox = new JComboBox();
				this.add(caseframeComboBox);
				caseframeComboBox.setModel(caseframeComboBoxModel);
				caseframeComboBox.setBounds(176, 24, 157, 22);
				caseframeComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						caseframeComboBoxActionPerformed(evt);
					}
				});
			}
			Application.getInstance().getContext().getResourceMap(getClass())
					.injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void optionsComboBoxActionPerformed(ActionEvent e) {
		try {
			int rowNumber = relationNodesetTable.getSelectedRow();
			System.out.println("Row Number: " + rowNumber);
			if (options.getSelectedItem().toString().equals("String")) {
				String nodeName = (String) JOptionPane.showInputDialog(
						getRootPane(),
						"Enter the name of the node:",
						"Create a Node",
						JOptionPane.PLAIN_MESSAGE);
//				Vector< Object> currentDataVector = (Vector<Object>) relationNodesetTableModel.getDataVector().elementAt(rowNumber);
//				System.out.println(currentDataVector.get(0));
//				System.out.println(currentDataVector.get(1));
//				System.out.println(currentDataVector.get(2));
//				currentDataVector.set(1, nodeName);
				
				if(relationNodesetTableModel.getValueAt(rowNumber, 1).toString().isEmpty()) {
					relationNodesetTableModel.setValueAt(nodeName, rowNumber, 1);
		    	} else {
		    		relationNodesetTableModel.setValueAt(", " + nodeName, rowNumber, 1);
		    	}
				
			} else if (options.getSelectedItem().toString().equals("build")) {
//				JTextField text = new JTextField();
//				text.setVisible(false);
				
//				JOptionPane pane = new JOptionPane(new cmdBuild(network), JOptionPane.PLAIN_MESSAGE);
//				JDialog dialog = pane.createDialog(null, "Build");
//				dialog.setResizable(true);
//			    dialog.setVisible(true);
			    cmdBuild build = new cmdBuild(network);
				
			    int result = JOptionPane.showConfirmDialog(  
			    	    this, build, "title", JOptionPane.PLAIN_MESSAGE
			    	);
			    
			    if (result == JOptionPane.OK_OPTION) {
			    	LinkedList<Node> nodes = build.getNodes();
				    for(Node item : nodes) {
				    	System.out.println(item.getIdentifier());
				    	
				    	String currentNodesetValue = relationNodesetTableModel.getValueAt(rowNumber, 1).toString();
				    	
				    	if(currentNodesetValue.isEmpty()) {
				    		relationNodesetTableModel.setValueAt(item.getIdentifier(), rowNumber, 1);
				    	} else {
				    		relationNodesetTableModel.setValueAt(currentNodesetValue + ", " + item.getIdentifier(), rowNumber, 1);
				    	}
				    }
			    }
			    
//				JDialog dialog = new JDialog();
//				dialog.getContentPane().add(new cmdBuild(network));
//				dialog.setPreferredSize(new Dimension(690, 225));
//				dialog.setVisible(true);
			} else if (options.getSelectedItem().toString().equals("assert")) {
//				JDialog dialog = new JDialog();
//				dialog.getContentPane().add(new cmdAssert(network));
//				dialog.setPreferredSize(new Dimension(690, 225));
//				dialog.setVisible(true);
				
				cmdAssert assertPanel = new cmdAssert(network);
				
			    int result = JOptionPane.showConfirmDialog(  
			    	    this, assertPanel, "title", JOptionPane.PLAIN_MESSAGE
			    	);
			    
			    if (result == JOptionPane.OK_OPTION) {
			    	LinkedList<Node> nodes = assertPanel.getNodes();
				    for(Node item : nodes) {
				    	System.out.println(item.getIdentifier());
				    	
				    	String currentNodesetValue = relationNodesetTableModel.getValueAt(rowNumber, 1).toString();
				    	
				    	if(currentNodesetValue.toString().isEmpty()) {
				    		relationNodesetTableModel.setValueAt(item.getIdentifier(), rowNumber, 1);
				    	} else {
				    		relationNodesetTableModel.setValueAt(currentNodesetValue + ", " + item.getIdentifier(), rowNumber, 1);
				    	}
				    }
			    }
			    
			} else if (options.getSelectedItem().toString().equals("find")) {
//				JDialog dialog = new JDialog();
//				dialog.getContentPane().add(new cmdFind(network));
//				dialog.setPreferredSize(new Dimension(690, 225));
//				dialog.setVisible(true);
				
				cmdFind assertPanel = new cmdFind(network);
				
			    int result = JOptionPane.showConfirmDialog(  
			    	    this, assertPanel, "title", JOptionPane.PLAIN_MESSAGE
			    	);
			    
			    if (result == JOptionPane.OK_OPTION) {
			    	LinkedList<Node> nodes = assertPanel.getNodes();
				    for(Node item : nodes) {
				    	System.out.println(item.getIdentifier());
				    	
				    	String currentNodesetValue = relationNodesetTableModel.getValueAt(rowNumber, 1).toString();
				    	
				    	if(currentNodesetValue.isEmpty()) {
				    		relationNodesetTableModel.setValueAt(item.getIdentifier(), rowNumber, 1);
				    	} else {
				    		relationNodesetTableModel.setValueAt(currentNodesetValue + ", " + item.getIdentifier(), rowNumber, 1);
				    	}
				    }
			    }
			}
			validate();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void caseframeComboBoxActionPerformed(ActionEvent evt) {
		try {
			CaseFrame caseframe = network.getCaseFrame(caseframeComboBox.getSelectedItem().toString());
			LinkedList<Relation> relations = caseframe.getRelations();
			
			for (int i = 0; i< relationNodesetTableModel.getRowCount(); i++) {
				relationNodesetTableModel.removeRow(i);
			}
			
			for (Relation item : relations) {
				Vector<Object> rowData = new Vector<Object>();
				rowData.add(item.getName());
				rowData.add("");
				rowData.add("String");
				relationNodesetTableModel.addRow(rowData);
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		int nodeCounter = 0;
		Object[][] cableset = new Object[100][2];
		
		try {
			for (int i = 0; i < relationNodesetTableModel.getRowCount(); i++) {
				Vector< Object> currentDataVector = (Vector<Object>) relationNodesetTableModel.getDataVector().elementAt(i);
				Relation relation = network.getRelation(currentDataVector.get(0).toString());
				
				String [] nodesetArray = currentDataVector.get(1).toString().split(",");
				
				for (int j = 0; j < nodesetArray.length; j++) {
					try {
						Node node = network.build(nodesetArray[j]);
						if (node != null) {
							JOptionPane.showMessageDialog(this,
							"The node " + node.getIdentifier() + "was created successfully");
						}
						
						System.out.println("Relation Name: " + relation.getName());
						System.out.println("Node Name: " + node.getIdentifier());
						cableset[nodeCounter][0] = relation;
						cableset[nodeCounter][1] = node;
						
					} catch (CustomException e) {
						JOptionPane.showMessageDialog(this,
				    			  "The node " + nodesetArray[j].toString() + "already exits",
				    			  "Error",
				    			  JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			}
			
			try {
				CaseFrame caseframe = network.getCaseFrame(caseframeComboBox.getSelectedItem().toString());
				System.out.println("Case Frame: " + caseframe.getId());
				Node node = network.build(cableset, caseframe);
				System.out.println("Created Node: "+ node.getIdentifier());
				nodes.add(node);
			} catch (CustomException e) {
				JOptionPane.showMessageDialog(this,
		    			  "The semantic type of the relation does not match the semantic type of the corresponding node in the array",
		    			  "Error",
		    			  JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public LinkedList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(LinkedList<Node> nodes) {
		this.nodes = nodes;
	}
}
