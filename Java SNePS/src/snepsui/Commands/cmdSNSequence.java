package snepsui.Commands;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.ActionMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
@SuppressWarnings({ "unused", "unchecked" })
public class cmdSNSequence extends javax.swing.JPanel {
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
        private JFrame windowFrame;

        public cmdSNSequence(Network network, SNePSInterface frame) {
        	super();
            this.frame = frame;
            this.network = network;
            nodes = new LinkedList<Node>();
            initGUI();
        }
        
        public cmdSNSequence(Network network, SNePSInterface frame, JFrame windowFrame) {
            super();
            this.frame = frame;
            this.network = network;
            this.windowFrame = windowFrame;
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
                                optionsComboBoxModel.addElement("Act Node");
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
                } catch (Exception e) {}
        }
        
        private void objectNumberComboBoxActionPerformed(ActionEvent evt) {
                
                relationNodesetTableModel.getDataVector().clear();
                
                Vector<Object> actionData = new Vector<Object>();
                //actionData.add(relations.getFirst());
                actionData.add("action");
                actionData.add("snsequence");
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
        
        private void optionsComboBoxActionPerformed(ActionEvent e) {
                try {
                        int rowNumber = relationNodesetTable.getSelectedRow();
                        TableCellEditor cell = relationNodesetTable.getCellEditor(rowNumber, 2);
                        
                        if (cell.getCellEditorValue().equals("Act Node")) {
                               frame.getsNePSULPanel1().getMenuDrivenCommands().actNodes(this ,relationNodesetTable, doneButton, relationNodesetTableModel);
                        } else {
                        	return;
                        }
                        relationNodesetTable.setValueAt(options.getItemAt(0), rowNumber, 2);
                        validate();
                } catch (Exception e1) {}
        }
          
        private void doneButtonMouseClicked(MouseEvent evt) {
                LinkedList<LinkedList<Node>> nodelist = new LinkedList<LinkedList<Node>>();
                try {
                        //1) Build the nodes in the node set
                        for (int i = 0; i < relationNodesetTableModel.getRowCount(); i++) {
                                Vector< Object> currentDataVector = (Vector<Object>) relationNodesetTableModel.getDataVector().elementAt(i);
                                
                                //2) Add in LinkedLists, create a LinkedList<LinkedList<Node/Relation>>
                                String [] nodesetArray = currentDataVector.get(1).toString().split(",");
                                LinkedList<Node> currentNodeSet = new LinkedList<Node>();
                                for (int j = 0; j < nodesetArray.length; j++) {
                                        try {
                                                Node node = network.build(nodesetArray[j]);
                                                if (node != null) {
                                                        currentNodeSet.add(node);
//                                                        JOptionPane.showMessageDialog(this,
//                                                        "The node " + node.getIdentifier() + " was created successfully");
                                                }
                                        } catch (CustomException e) {
//                                                JOptionPane.showMessageDialog(this,
//                                                          "The node " + nodesetArray[j].toString() + "already exits",
//                                                          "Error",
//                                                          JOptionPane.ERROR_MESSAGE);
                                                currentNodeSet.add(network.getNode(nodesetArray[j]));
                                        }
                                }
                                nodelist.add(currentNodeSet);
                        }
                        
                        //3) Build the last two nodes
                        CaseFrame caseframe1 = network.getCaseFrame("object1,object2"); 
                        //object1
                        LinkedList<Node> object1Nodes = nodelist.get(nodelist.size() - 2);
                        Relation object1Relation = network.getRelation("object1");
                        //object2
                        LinkedList<Node> object2Nodes = nodelist.get(nodelist.size() - 1);
                        Relation object2Relation = network.getRelation("object2");
                        
                        Object[][] cableset = new Object[object1Nodes.size() + object2Nodes.size()][2];
                        int counter = 0;
                        
                        for(int i = 0; i < object1Nodes.size(); i++) {
                                cableset[counter][0] = object1Relation;
                                cableset[counter][1] = object1Nodes.get(i);
                                counter++;
                        }
                        
                        for(int i = 0; i < object2Nodes.size(); i++) {
                                cableset[counter][0] = object2Relation;
                                cableset[counter][1] = object2Nodes.get(i);
                                counter++;
                        }
                        
                        Node previousNode = network.build(cableset, caseframe1);
                        
                        //4) Loop in reverse to build from the bottom up (CaseFrame: object1, object2)
                        for(int i = nodelist.size() - 3; i > 0 ; i--) {
                                object1Nodes = nodelist.get(i);
                                counter = 0;
                                cableset = new Object[object1Nodes.size() + 1][2];
                                for(int j = 0; j < object1Nodes.size(); j++) {
                                        cableset[counter][0] = object1Relation;
                                        cableset[counter][1] = object1Nodes.get(j);
                                        counter++;
                                }
                                
                                //5) Use the built act node in the next build
                                cableset[counter][0] = object2Relation;
                                cableset[counter][1] = previousNode;
                                
                                previousNode = network.build(cableset, caseframe1);
                        }
                        
                        //6) For the last build (CaseFrame: action, actObject)
                        CaseFrame caseframe2 = network.getCaseFrame("actObject,action");
                        cableset = new Object[2][2];
                        
                        //action
                        Node actionNode = nodelist.get(0).get(0);
                        Relation actionRelation = network.getRelation("action");
                        //actObject
                        Relation actObjectRelation = network.getRelation("actObject");
                        
                        cableset[0][0] = actionRelation;
                        cableset[0][1] = actionNode;
                        cableset[1][0] = actObjectRelation;
                        cableset[1][1] = previousNode;
                        
                        Node actNode = network.build(cableset, caseframe2);
                        nodes.add(actNode);
                } catch (CustomException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                }
                relationNodesetTableModel.getDataVector().clear();
                
                frame.getNodesTreePanel1().initGUI();
                frame.getMainFrame().validate();
                frame.getMainFrame().repaint();
                
                if(windowFrame != null) {
        			windowFrame.dispose();
        		}
        }
        
        public LinkedList<Node> getNodes() {
                return nodes;
        }

        public void setNodes(LinkedList<Node> nodes) {
                this.nodes = nodes;
        }
}