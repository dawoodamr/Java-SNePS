package SNePS.SNePSUI.Commands;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import SNePS.NetworkManagementSystem.CaseFrame;
import SNePS.NetworkManagementSystem.CustomException;
import SNePS.NetworkManagementSystem.Network;
import SNePS.NetworkManagementSystem.Relation;

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
public class cmdActivate extends javax.swing.JPanel {
	private JLabel activateLabel;
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

	public cmdActivate(Network network) {
		super();
		this.network = network;
		initGUI();
	}
	
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

	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				activateLabel = new JLabel();
				this.add(activateLabel);
				activateLabel.setName("activateLabel");
				activateLabel.setBounds(12, 28, 59, 15);
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
				relationNodesetTableModel.setValueAt(nodeName, rowNumber, 1);
			} else if (options.getSelectedItem().toString().equals("build")) {
				JDialog dialog = new JDialog();
				dialog.getContentPane().add(new cmdBuild(network));
				dialog.setPreferredSize(new Dimension(690, 225));
				dialog.setVisible(true);
			} else if (options.getSelectedItem().toString().equals("assert")) {
				JDialog dialog = new JDialog();
				dialog.getContentPane().add(new cmdAssert(network));
				dialog.setPreferredSize(new Dimension(690, 225));
				dialog.setVisible(true);
			} else if (options.getSelectedItem().toString().equals("find")) {
				JDialog dialog = new JDialog();
				dialog.getContentPane().add(new cmdFind(network));
				dialog.setPreferredSize(new Dimension(690, 225));
				dialog.setVisible(true);
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
}
