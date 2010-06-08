package snepsui.Commands;

import java.awt.Dimension;
import java.awt.Insets;
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
import sneps.NodeSet;
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
@SuppressWarnings("unchecked")
public class cmdNumericalEntailment extends javax.swing.JPanel {
	private JLabel assertLabel;
	private JButton doneButton;
	private JScrollPane jScrollPane1;
	private JComboBox contextNameComboBox;
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

	public cmdNumericalEntailment(Network network, SNePSInterface frame) {
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
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(80, 34, 440, 127);
				{
					relationNodesetTableModel = new DefaultTableModel();
					relationNodesetTableModel.addColumn("Relation");
					relationNodesetTableModel.addColumn("Nodeset");
					relationNodesetTableModel.addColumn("");
					relationNodesetTable = new JTable() {
                        public boolean isCellEditable(int row, int column){  
                            if((row == 0 && column == 2)) return false;  
                            return true;  
                          }
					};
					jScrollPane1.setViewportView(relationNodesetTable);
					relationNodesetTable.setModel(relationNodesetTableModel);
					
					relationNodesetTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(relationTextField));
					relationNodesetTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(nodesetTextField));
					relationNodesetTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(options));
					
					Vector<Object> threshData = new Vector<Object>();
					threshData.add("thresh");
					threshData.add("");
					relationNodesetTableModel.addRow(threshData);
					
					Vector<Object> antData = new Vector<Object>();
					antData.add("&ant");
					antData.add("");
					antData.add("Choose Node Type");
					relationNodesetTableModel.addRow(antData);
					
					Vector<Object> cqData = new Vector<Object>();
					cqData.add("cq");
					cqData.add("");
					cqData.add("Choose Node Type");
					relationNodesetTableModel.addRow(cqData);
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
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		LinkedList<Relation> relationlist = new LinkedList<Relation>();
		LinkedList<Object> nodelist = new LinkedList<Object>();
		
		//Check for a number at thresh
		Vector< Object> threshDataVector = (Vector<Object>) relationNodesetTableModel.getDataVector().elementAt(0);
		String threshString = threshDataVector.get(1).toString();
		try {
			int thresh = Integer.parseInt(threshString);
			if(thresh < 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,
	    			  "Thresh must be a non-negative number",
	    			  "Error",
	    			  JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			for (int i = 0; i < relationNodesetTableModel.getRowCount(); i++) {
				Vector< Object> currentDataVector = (Vector<Object>) relationNodesetTableModel.getDataVector().elementAt(i);
				Relation relation = network.getRelation(currentDataVector.get(0).toString());
				
				String [] nodesetArray = currentDataVector.get(1).toString().split(",");
				if(!nodesetArray[0].isEmpty()) {
					for (int j = 0; j < nodesetArray.length; j++) {
						try {
							Node node = network.build(nodesetArray[j]);
							if (node != null) {
								nodelist.add(node);
								relationlist.add(relation);
								JOptionPane.showMessageDialog(this,
								"The node " + node.getIdentifier() + " was created successfully");
							}
						} catch (CustomException e) {
							JOptionPane.showMessageDialog(this,
					    			  "The node " + nodesetArray[j].toString() + "already exits",
					    			  "Error",
					    			  JOptionPane.ERROR_MESSAGE);
							nodelist.add(network.getNode(nodesetArray[j]));
							relationlist.add(relation);
						}
					}
				} else if(nodesetArray.length == 1 && nodesetArray[0].isEmpty() && relation.getLimit() == 0){
					nodelist.add(new NodeSet());
					relationlist.add(relation);
				} else {
					JOptionPane.showMessageDialog(this,
			    			  "The relation " + relation.getName() + "has to have a node, its limit is not 0",
			    			  "Error",
			    			  JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			
			if(frame.getsNePSULPanel1().getMenuDrivenCommands().checkConsistency(relationlist, nodelist)) {
				try {
					Object[][] cableset = new Object[relationlist.size()][2];
					
					for(int i = 0; i < relationlist.size(); i++) {
						cableset[i][0] = relationlist.get(i);
						cableset[i][1] = nodelist.get(i);
					}
					
					CaseFrame caseframe = network.getCaseFrame("&ant,cq,thresh");
					Node node = network.build(cableset, caseframe);
					nodes.add(node);
				} catch (CustomException e) {
					JOptionPane.showMessageDialog(this,
			    			  e.getMessage(),
			    			  "Error",
			    			  JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this,
		    			  "The semantic classes of the given relations and nodes are inconsistent",
		    			  "Error",
		    			  JOptionPane.ERROR_MESSAGE);
				
				for(int i = 0; i < nodelist.size(); i++) {
					Object object = nodelist.get(i);
					if(object instanceof Node) {
						Node node = (Node) object;
						if(node.getUpCableSet().getUpCables().isEmpty()) {
							network.removeNode(node);
						}
					} else if (object instanceof NodeSet) {
						break;
					}
				}
				return;
			}
			
		} catch (Exception e) {}
		
		relationNodesetTableModel.getDataVector().clear();
		
		Vector<Object> threshData = new Vector<Object>();
		threshData.add("thresh");
		threshData.add("");
		relationNodesetTableModel.addRow(threshData);
		
		Vector<Object> antData = new Vector<Object>();
		antData.add("&ant");
		antData.add("");
		antData.add("Choose Node Type");
		relationNodesetTableModel.addRow(antData);
		
		Vector<Object> cqData = new Vector<Object>();
		cqData.add("cq");
		cqData.add("");
		cqData.add("Choose Node Type");
		relationNodesetTableModel.addRow(cqData);
		
		frame.getNodesTreePanel1().initGUI();
		frame.getNodesTreePanel1().validate();
		frame.getNodesTreePanel1().repaint();
	}
}
