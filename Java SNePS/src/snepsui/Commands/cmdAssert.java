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
import java.util.Vector;

import javax.swing.ActionMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
public class cmdAssert extends javax.swing.JPanel {
	private JLabel assertLabel;
	private JButton doneButton;
	private JScrollPane jScrollPane1;
	private JComboBox contextComboBox;
	private JTable relationNodesetTable;
	private JComboBox caseframeComboBox;
	private JLabel caseframeLabel;
	private JLabel contextNameLabel;
	private JButton infoButton;
	private Network network;
	private DefaultTableModel relationNodesetTableModel;
	private JComboBox options;
	private JTextField relationTextField;
	private JTextField nodesetTextField;
	private LinkedList<Node> nodes;
	private SNePSInterface frame;
	private JFrame windowFrame;
	
	public cmdAssert(Network network, SNePSInterface frame) {
		super();
		this.frame = frame;
		this.network = network;
		nodes = new LinkedList<Node>();
		initGUI();
	}
	
	public cmdAssert(Network network, SNePSInterface frame, JFrame windowFrame) {
		super();
		this.frame = frame;
		this.windowFrame = windowFrame;
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
				assertLabel.setBounds(12, 28, 59, 15);
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
					relationNodesetTable = new JTable();
					jScrollPane1.setViewportView(relationNodesetTable);
					relationNodesetTable.setModel(relationNodesetTableModel);
					relationNodesetTable.setEditingRow(0);
					
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
			{
				ComboBoxModel contextComboBoxModel = new DefaultComboBoxModel();
				contextComboBox = new JComboBox();
				this.add(contextComboBox);
				contextComboBox.setModel(contextComboBoxModel);
				contextComboBox.setBounds(532, 58, 136, 22);
			}
			Application.getInstance().getContext().getResourceMap(getClass())
					.injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void optionsComboBoxActionPerformed(ActionEvent e) {
		try {
			final int rowNumber = relationNodesetTable.getSelectedRow();
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
				
			} else if (cell.getCellEditorValue().equals("build")) {
				
				JFrame popupFrame = new JFrame("build");
				popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				final cmdBuild buildPanel = new cmdBuild(network, frame);
				popupFrame.getContentPane().add(buildPanel);
				popupFrame.pack();
				popupFrame.setVisible(true);
				Point point = frame.getsNePSULPanel1().getMenuDrivenCommands().cascadePosition();
				popupFrame.setLocation(point);
				doneButton.setEnabled(false);
				popupFrame.addWindowListener(new WindowAdapter() {
					
					@Override
					public void windowClosed(WindowEvent e) {
						LinkedList<Node> nodes = buildPanel.getNodes();
					    for(Node item : nodes) {
					    	System.out.println(item.getIdentifier());
					    	
					    	String currentNodesetValue = relationNodesetTableModel.getValueAt(rowNumber, 1).toString();
					    	
					    	if(currentNodesetValue.isEmpty()) {
					    		relationNodesetTableModel.setValueAt(item.getIdentifier(), rowNumber, 1);
					    	} else {
					    		relationNodesetTableModel.setValueAt(currentNodesetValue + ", " + item.getIdentifier(), rowNumber, 1);
					    	}
					    }
						frame.getsNePSULPanel1().getMenuDrivenCommands().cascadeBack();
						doneButton.setEnabled(true);
					}
				});
			    
			} else if (cell.getCellEditorValue().equals("assert")) {
				JFrame popupFrame = new JFrame("assert");
				popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				final cmdAssert assertPanel = new cmdAssert(network, frame);
				popupFrame.getContentPane().add(assertPanel);
				popupFrame.pack();
				popupFrame.setVisible(true);
				Point point = frame.getsNePSULPanel1().getMenuDrivenCommands().cascadePosition();
				popupFrame.setLocation(point);
				doneButton.setEnabled(false);
				popupFrame.addWindowListener(new WindowAdapter() {
					
					@Override
					public void windowClosed(WindowEvent e) {
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
						frame.getsNePSULPanel1().getMenuDrivenCommands().cascadeBack();
						doneButton.setEnabled(true);
					}
				});
			    
			} else if (cell.getCellEditorValue().equals("find")) {
				JFrame popupFrame = new JFrame("find");
				popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				final cmdFind findPanel = new cmdFind(network, frame);
				popupFrame.getContentPane().add(findPanel);
				popupFrame.pack();
				popupFrame.setVisible(true);
				Point point = frame.getsNePSULPanel1().getMenuDrivenCommands().cascadePosition();
				popupFrame.setLocation(point);
				doneButton.setEnabled(false);
				popupFrame.addWindowListener(new WindowAdapter() {
					
					@Override
					public void windowClosed(WindowEvent e) {
						LinkedList<Node> nodes = findPanel.getNodes();
					    for(Node item : nodes) {
					    	System.out.println(item.getIdentifier());
					    	
					    	String currentNodesetValue = relationNodesetTableModel.getValueAt(rowNumber, 1).toString();
					    	
					    	if(currentNodesetValue.isEmpty()) {
					    		relationNodesetTableModel.setValueAt(item.getIdentifier(), rowNumber, 1);
					    	} else {
					    		relationNodesetTableModel.setValueAt(currentNodesetValue + ", " + item.getIdentifier(), rowNumber, 1);
					    	}
					    }
						frame.getsNePSULPanel1().getMenuDrivenCommands().cascadeBack();
						doneButton.setEnabled(true);
					}
				});
			}
			relationNodesetTable.setValueAt(options.getItemAt(0), rowNumber, 2);
			validate();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void caseframeComboBoxActionPerformed(ActionEvent evt) {
		try {
			CaseFrame caseframe = network.getCaseFrame(caseframeComboBox.getSelectedItem().toString());
			LinkedList<Relation> relations = caseframe.getRelations();
			relationNodesetTableModel.getDataVector().clear();
			
			for (Relation item : relations) {
				Vector<Object> rowData = new Vector<Object>();
				rowData.add(item.getName());
				rowData.add("");
				rowData.add("Choose Node Type");
				relationNodesetTableModel.addRow(rowData);
			}
			relationNodesetTable.setRowSelectionInterval(0, 0);
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkConsistency() {
		System.out.println("Entered Check Consistency 1");
		for (int i = 0; i < relationNodesetTableModel.getRowCount(); i++) {
			try {
				Vector< Object> currentDataVector = (Vector<Object>) relationNodesetTableModel.getDataVector().elementAt(i);
				Relation relation = network.getRelation(currentDataVector.get(0).toString());
				
				
				String [] nodesetArray = currentDataVector.get(1).toString().split(",");
				
				for (int j = 0; j < nodesetArray.length; j++) {
					Node node = network.getNode(nodesetArray[j]);
				}
			} catch (CustomException e) {
				e.printStackTrace();
			}	
		}
		return false;
	}
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		LinkedList<Relation> relationlist = new LinkedList<Relation>();
		LinkedList<Object> nodelist = new LinkedList<Object>();
		
		//if(checkConsistency()) {
			try {
				for (int i = 0; i < relationNodesetTableModel.getRowCount(); i++) {
					Vector< Object> currentDataVector = (Vector<Object>) relationNodesetTableModel.getDataVector().elementAt(i);
					Relation relation = network.getRelation(currentDataVector.get(0).toString());
					
					String [] nodesetArray = currentDataVector.get(1).toString().split(",");
					System.out.println("Index: " + i);
					System.out.println("Array Length: " + nodesetArray.length);
					System.out.println(nodesetArray[0]);
					if(!nodesetArray[0].isEmpty()) {
						for (int j = 0; j < nodesetArray.length; j++) {
							try {
								System.out.println(nodesetArray[j]);
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
								e.printStackTrace();
							}
						}
					} else if(nodesetArray.length == 1 && nodesetArray[0].isEmpty() && relation.getLimit() == 0){
						System.out.println("Adding an empty nodeset");
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
				
				try {
					Object[][] cableset = new Object[relationlist.size()][2];
					
					for(int i = 0; i < relationlist.size(); i++) {
						cableset[i][0] = relationlist.get(i);
						cableset[i][1] = nodelist.get(i);
					}
					
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
			
			relationNodesetTableModel.getDataVector().clear();
			
			frame.getNodesTreePanel1().initGUI();
			frame.getNodesTreePanel1().validate();
			frame.getNodesTreePanel1().repaint();
			
			if(windowFrame != null) {
				windowFrame.dispose();
			}
		//}
	}
	
	public LinkedList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(LinkedList<Node> nodes) {
		this.nodes = nodes;
	}
}