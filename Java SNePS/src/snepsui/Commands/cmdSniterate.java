package snepsui.Commands;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.ActionMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import sneps.CaseFrame;
import sneps.CustomException;
import sneps.Network;
import sneps.Node;
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
public class cmdSniterate extends javax.swing.JPanel {
	private JLabel addLabel;
	private JButton doneButton;
	private JScrollPane jScrollPane1;
	private JComboBox contextNameComboBox;
	private JLabel buildLabel;
	private JComboBox objectNumberComboBox;
	private JLabel objectNumberLabel;
	private JTable relationNodesetTable;
	private JLabel contextNameLabel;
	private JButton infoButton;
	private Network network;
	private DefaultTableModel relationNodesetTableModel;
	private JComboBox options;
	private JTextField relationTextField;
	private JTextField nodesetTextField;
	private LinkedList<Node> nodes;
	private SNePSInterface frame;
	private JComboBox actions;

	public cmdSniterate(Network network, SNePSInterface frame) {
		super();
		this.frame = frame;
		this.network = network;
		nodes = new LinkedList<Node>();
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
				addLabel.setBounds(6, 28, 68, 15);
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
				optionsComboBoxModel.addElement("Choose Node Type");
				optionsComboBoxModel.addElement("Base Node");
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
					relationNodesetTable = new JTable() {
						public boolean isCellEditable(int row, int column){  
						    if(row == 0 && column == 2) return false;  
						    return true;  
						  }
					};
					jScrollPane1.setViewportView(relationNodesetTable);
					relationNodesetTable.setModel(relationNodesetTableModel);
					
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
				contextNameLabel = new JLabel();
				this.add(contextNameLabel);
				contextNameLabel.setName("contextNameLabel");
				contextNameLabel.setBounds(532, 25, 123, 21);
			}
			{
				objectNumberLabel = new JLabel();
				this.add(objectNumberLabel);
				objectNumberLabel.setBounds(357, 28, 98, 15);
				objectNumberLabel.setName("objectNumberLabel");
			}
			{
				DefaultComboBoxModel objectNumberComboBoxModel = new DefaultComboBoxModel();
				
				for (int i = 1; i < 100; i++) {
					objectNumberComboBoxModel.addElement(i);
				}
				
				objectNumberComboBox = new JComboBox();
				this.add(objectNumberComboBox);
				objectNumberComboBox.setModel(objectNumberComboBoxModel);
				objectNumberComboBox.setBounds(455, 24, 65, 22);
				objectNumberComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						objectNumberComboBoxActionPerformed(evt);
					}
				});
			}
			{
				buildLabel = new JLabel();
				this.add(buildLabel);
				buildLabel.setBounds(80, 28, 49, 15);
				buildLabel.setName("buildLabel");
			}
			{
				ComboBoxModel contextNameComboBoxModel = new DefaultComboBoxModel();
				contextNameComboBox = new JComboBox();
				this.add(contextNameComboBox);
				contextNameComboBox.setModel(contextNameComboBoxModel);
				contextNameComboBox.setBounds(532, 58, 136, 22);
			}
			Application.getInstance().getContext().getResourceMap(getClass())
					.injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void objectNumberComboBoxActionPerformed(ActionEvent evt) {
		
		relationNodesetTableModel.getDataVector().clear();
		
		Vector<Object> actionData = new Vector<Object>();
		//actionData.add(relations.getFirst());
		actionData.add("action");
		actionData.add("sniterate");
		relationNodesetTableModel.addRow(actionData);
	
		int numOfObjects = Integer.parseInt(objectNumberComboBox.getSelectedItem().toString());
		
		for (int i = 0; i < numOfObjects; i++) {
			Vector<Object> objectData = new Vector<Object>();
			//objectData.add(relations.getLast().getName() + (i+1));
			objectData.add("object" + (i+1));
			objectData.add("");
			objectData.add("Choose Node Type");
			relationNodesetTableModel.addRow(objectData);
		}
	}
	
	private void actionsComboBoxActionPerformed(ActionEvent e) {
		
	}
	
	private void optionsComboBoxActionPerformed(ActionEvent e) {
		try {
			int rowNumber = relationNodesetTable.getSelectedRow();
			TableCellEditor cell = relationNodesetTable.getCellEditor(rowNumber, 2);
			
			if (cell.getCellEditorValue().equals("Base Node")) {
				String nodeName = (String) JOptionPane.showInputDialog(
						getRootPane(),
						"Enter the name of the node:",
						"Create a Node",
						JOptionPane.PLAIN_MESSAGE);
				
				String currentNodesetValue = relationNodesetTableModel.getValueAt(rowNumber, 1).toString();
				
				if(currentNodesetValue.isEmpty()) {
					relationNodesetTableModel.setValueAt(nodeName, rowNumber, 1);
		    	} else {
		    		relationNodesetTableModel.setValueAt(currentNodesetValue + ", " + nodeName, rowNumber, 1);
		    	}
				relationNodesetTable.setValueAt(options.getItemAt(0), rowNumber, 2);
			} else if (cell.getCellEditorValue().equals("build")) {
			    cmdBuild build = new cmdBuild(network, frame);
				
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
			    relationNodesetTable.setValueAt(options.getItemAt(0), rowNumber, 2);
			} else if (cell.getCellEditorValue().equals("assert")) {
				cmdAssert assertPanel = new cmdAssert(network, frame);
				
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
			    relationNodesetTable.setValueAt(options.getItemAt(0), rowNumber, 2);
			} else if (cell.getCellEditorValue().equals("find")) {
				cmdFind assertPanel = new cmdFind(network, frame);
				
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
			    relationNodesetTable.setValueAt(options.getItemAt(0), rowNumber, 2);
			}
			validate();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		LinkedList<Relation> relationlist = new LinkedList<Relation>();
		LinkedList<Node> nodelist = new LinkedList<Node>();
		
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
						relationlist.add(relation);
						nodelist.add(node);
						
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
				Object[][] cableset = new Object[relationlist.size()][2];
				
				for(int i = 0; i < relationlist.size(); i++) {
					cableset[i][0] = relationlist.get(i);
					cableset[i][1] = nodelist.get(i);
				}
				
				CaseFrame caseframe = network.getCaseFrame("Act");
				System.out.println("Case Frame: " + caseframe.getId());
				Node node = network.build(cableset, caseframe);
				System.out.println("Created Node: "+ node.getIdentifier());
				nodes.add(node);
			} catch (CustomException e) {
				JOptionPane.showMessageDialog(this,
		    			  e.getMessage(),
		    			  "Error",
		    			  JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		frame.getNodesTreePanel1().initGUI();
		frame.getMainFrame().validate();
		frame.getMainFrame().repaint();
	}
	
	public LinkedList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(LinkedList<Node> nodes) {
		this.nodes = nodes;
	}
}
