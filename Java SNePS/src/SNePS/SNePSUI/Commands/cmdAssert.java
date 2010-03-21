package SNePS.SNePSUI.Commands;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import SNePS.NetworkManagementSystem.CaseFrame;
import SNePS.NetworkManagementSystem.CustomException;
import SNePS.NetworkManagementSystem.Network;
import SNePS.NetworkManagementSystem.Node;
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
public class cmdAssert extends javax.swing.JPanel {
	private JLabel assertLabel;
	private JTextField nodesetTextField;
	private JButton addButton;
	private JList nodesetList;
	private JButton doneButton;
	private DefaultListModel relationListModel;
	private DefaultListModel nodesetListModel;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JComboBox caseframeComboBox;
	private JLabel caseframeLabel;
	private JComboBox relationsComboBox;
	private JLabel contextNameLabel;
	private JRadioButton contextNameRadioButton1;
	private JRadioButton contextNameRadioButton2;
	private JTextField contextNameTextField;
	private JButton buildButton;
	private JButton infoButton;
	private JList relationList;
	private JLabel nodesetLabel;
	private JLabel relationLabel;
	private JPopupMenu popup;
	private JButton assertButton;
	private JButton findButton;
	private Network network;

	@Action
	public void add() {
	}
	
	@Action
	public void build() {
	}

	@Action
	public void info() {
	}
	
	@Action
	public void find() {
	}
	
	@Action
	public void assertAction() {
	}

	private ActionMap getAppActionMap() {
		return Application.getInstance().getContext().getActionMap(this);
	}

	public cmdAssert(Network network) {
		super();
		this.network = network;
		initGUI();
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
				nodesetTextField = new JTextField();
				this.add(nodesetTextField);
				nodesetTextField.setBounds(398, 25, 192, 22);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(596, 27, 16, 18);
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
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(398, 58, 190, 103);
				{
					nodesetListModel = new DefaultListModel();
					nodesetList = new JList();
					jScrollPane1.setViewportView(nodesetList);
					nodesetList.setModel(nodesetListModel);
					nodesetList.setBounds(367, 159, 187, 100);
				}
			}
			{
				relationLabel = new JLabel();
				this.add(relationLabel);
				relationLabel.setBounds(193, 4, 70, 15);
				relationLabel.setName("relationLabel");
			}
			{
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setBounds(398, 4, 66, 15);
				nodesetLabel.setName("nodesetLabel");
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(193, 58, 189, 103);
				{
					relationListModel = new DefaultListModel();
					relationList = new JList();
					jScrollPane2.setViewportView(relationList);
					relationList.setModel(relationListModel);
					relationList.setBounds(-42, 176, 186, 100);
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
				buildButton = new JButton();
				this.add(buildButton);
				buildButton.setBounds(617, 26, 18, 20);
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
				assertButton = new JButton();
				this.add(assertButton);
				assertButton.setBounds(640, 26, 18, 20);
				assertButton.setAction(getAppActionMap().get("assertAction"));
				assertButton.setFocusable(false);
				assertButton.setToolTipText("assert");
				assertButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						assertButtonActionPerformed(evt);
					}
				});
			}
			{
				findButton = new JButton();
				this.add(findButton);
				findButton.setBounds(663, 26, 18, 20);
				findButton.setAction(getAppActionMap().get("find"));
				findButton.setFocusable(false);
				findButton.setToolTipText("assert");
				findButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						findButtonActionPerformed(evt);
					}
				});
			}
			{
				contextNameTextField = new JTextField();
				this.add(contextNameTextField);
				contextNameTextField.setBounds(42, 138, 139, 22);
				contextNameTextField.setName("contextNameTextField");
			}
			{
				contextNameRadioButton2 = new JRadioButton();
				this.add(contextNameRadioButton2);
				contextNameRadioButton2.setBounds(12, 143, 25, 17);
				contextNameRadioButton2.setEnabled(false);
				contextNameRadioButton2.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						contextNameRadioButton2MouseClicked(evt);
					}
				});
			}
			{
				contextNameRadioButton1 = new JRadioButton();
				this.add(contextNameRadioButton1);
				contextNameRadioButton1.setName("contextNameRadioButton1");
				contextNameRadioButton1.setBounds(12, 107, 97, 19);
			}
			{
				contextNameLabel = new JLabel();
				this.add(contextNameLabel);
				contextNameLabel.setName("contextNameLabel");
				contextNameLabel.setBounds(12, 82, 97, 15);
			}
			{
				DefaultComboBoxModel relationsComboBoxModel = new DefaultComboBoxModel();
				
				String str = "";
				Hashtable<String, Relation> relations = network.getRelations();
				Set<String> set = relations.keySet();

			    Iterator<String> itr = set.iterator();
			    while (itr.hasNext()) {
			      str = itr.next();
			      relationsComboBoxModel.addElement(relations.get(str).getName()) ;
			    }
			    
				relationsComboBox = new JComboBox();
				this.add(relationsComboBox);
				relationsComboBox.setModel(relationsComboBoxModel);
				relationsComboBox.setBounds(193, 24, 189, 22);
			}
			{
				caseframeLabel = new JLabel();
				this.add(caseframeLabel);
				caseframeLabel.setBounds(60, 4, 98, 15);
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
			      //Add the getName() method here later when it's implemented
			      caseframeComboBoxModel.addElement(caseframes.get(str).toString()) ;
			    }
			    
				caseframeComboBox = new JComboBox();
				this.add(caseframeComboBox);
				caseframeComboBox.setModel(caseframeComboBoxModel);
				caseframeComboBox.setBounds(59, 24, 126, 22);
			}
			Application.getInstance().getContext().getResourceMap(getClass())
					.injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void contextNameRadioButton2MouseClicked(MouseEvent evt) {
		if (contextNameRadioButton2.isSelected()) {
			contextNameTextField.setEditable(true);
		} else {
			contextNameTextField.setEditable(false);
		}
	}
	
	private void buildButtonActionPerformed(ActionEvent evt) {

		JFrame frame = new JFrame("Build");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new cmdBuild(network));
		frame.pack();
		frame.setVisible(true);
	}
	
	private void assertButtonActionPerformed(ActionEvent evt) {
		JFrame frame = new JFrame("Assert");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new cmdAssert(network));
		frame.pack();
		frame.setVisible(true);
	}
	
	private void findButtonActionPerformed(ActionEvent evt) {
		JFrame frame = new JFrame("Find");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new cmdFind(network));
		frame.pack();
		frame.setVisible(true);
	}
	
	private void addButtonMouseClicked(MouseEvent evt) {
		relationListModel.addElement(relationsComboBox.getSelectedItem().toString());
		nodesetListModel.addElement(nodesetTextField.getText());
		relationsComboBox.setSelectedIndex(0);
		nodesetTextField.setText("");
	}
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		for (int i = 0; i < nodesetListModel.size(); i++) {
			String [] nodesetArray = nodesetListModel.get(i).toString().split(",");
			for (int j = 0; j < nodesetArray.length; j++) {
				try {
					Node node = network.build(nodesetArray[j]);
					if (node == null) {
						JOptionPane.showMessageDialog(this,
				    			  "The node " + nodesetArray[j].toString() + "already exits",
				    			  "Error",
				    			  JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(this,
						"The node" + node.getIdentifier() + "was created successfully");
					}
				} catch (CustomException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//Get the case frame by its name
			//CaseFrame caseframe = network.getcas
			
			//The 2D array the nodeset should be an array?
			//network.build(array, caseframeComboBox.getSelectedItem());
		}
	}
}
