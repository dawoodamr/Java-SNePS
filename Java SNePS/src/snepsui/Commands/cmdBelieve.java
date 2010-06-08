package snepsui.Commands;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import sneps.Network;
import sneps.Node;
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
public class cmdBelieve extends javax.swing.JPanel {
	private JLabel assertLabel;
	private JButton doneButton;
	private JScrollPane jScrollPane1;
	private JComboBox contextNameComboBox;
	private JLabel buildLabel;
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

	public cmdBelieve(Network network, SNePSInterface frame) {
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
				assertLabel = new JLabel();
				this.add(assertLabel);
				assertLabel.setName("assertLabel");
				assertLabel.setBounds(6, 28, 68, 15);
			}
			{
				doneButton = new JButton();
				this.add(doneButton);
				doneButton.setBounds(314, 185, 77, 29);
				doneButton.setName("doneButton");
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
					relationNodesetTable.setEditingRow(0);
					
					relationNodesetTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(relationTextField));
					relationNodesetTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(nodesetTextField));
					relationNodesetTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(options));
					
					Vector<Object> actionData = new Vector<Object>();
					actionData.add("action");
					actionData.add("believe");
					relationNodesetTableModel.addRow(actionData);
					
					Vector<Object> objectData = new Vector<Object>();
					objectData.add("object1");
					objectData.add("");
					objectData.add("Choose Node Type");
					relationNodesetTableModel.addRow(objectData);
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
				contextNameLabel = new JLabel();
				this.add(contextNameLabel);
				contextNameLabel.setName("contextNameLabel");
				contextNameLabel.setBounds(532, 25, 123, 21);
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
		} catch (Exception e) {}
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
	
	public LinkedList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(LinkedList<Node> nodes) {
		this.nodes = nodes;
	}
}
