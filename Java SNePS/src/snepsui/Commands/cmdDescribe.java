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

import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import sneps.CustomException;
import sneps.MolecularNode;
import sneps.Network;
import sneps.Node;
import sneps.PatternNode;
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
public class cmdDescribe extends javax.swing.JPanel {
	private JLabel describeLabel;
	private JButton addButton;
	private JButton doneButton;
	private DefaultListModel nodesetModel;
	private JScrollPane jScrollPane2;
	private JButton assertButton;
	private JButton findButton;
	private JComboBox contextComboBox;
	private JComboBox nodesComboBox;
	private JButton infoButton;
	private JList nodesetList;
	private JLabel contextNameLabel;
	private JLabel nodesetLabel;
	private JButton buildButton;
	private Network network;
	private SNePSInterface frame;

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
	
	public cmdDescribe(Network network, SNePSInterface frame) {
		super();
		this.frame = frame;
		this.network = network;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				describeLabel = new JLabel();
				this.add(describeLabel);
				describeLabel.setName("describeLabel");
				describeLabel.setBounds(50, 28, 58, 15);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(314, 27, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						nodesetModel.addElement(nodesComboBox.getSelectedItem().toString());
						nodesComboBox.setSelectedIndex(0);
						validate();
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
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setBounds(108, 4, 61, 15);
				nodesetLabel.setName("nodesetLabel");
			}
			{
				contextNameLabel = new JLabel();
				this.add(contextNameLabel);
				contextNameLabel.setBounds(414, 4, 97, 15);
				contextNameLabel.setName("contextNameLabel");
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(108, 58, 189, 103);
				{
					nodesetModel = new DefaultListModel();
					nodesetList = new JList();
					jScrollPane2.setViewportView(nodesetList);
					nodesetList.setModel(nodesetModel);
					nodesetList.setBounds(116, 138, 186, 100);
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
				buildButton.setBounds(335, 26, 18, 20);
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
				DefaultComboBoxModel nodesetComboBoxModel = new DefaultComboBoxModel();
				
				String str = "";
				Hashtable<String, Node> nodes = network.getNodes();
				Set<String> set = nodes.keySet();

			    Iterator<String> itr = set.iterator();
			    while (itr.hasNext()) {
			      str = itr.next();
			      Node node = nodes.get(str);
			      if(node instanceof PatternNode || node instanceof MolecularNode) {
			    	  nodesetComboBoxModel.addElement(nodes.get(str).getIdentifier()) ;
			      }
			    }
				nodesComboBox = new JComboBox();
				this.add(nodesComboBox);
				nodesComboBox.setModel(nodesetComboBoxModel);
				nodesComboBox.setBounds(108, 24, 189, 22);
			}
			{
				ComboBoxModel contextComboBoxModel = new DefaultComboBoxModel();
				contextComboBox = new JComboBox();
				this.add(contextComboBox);
				contextComboBox.setModel(contextComboBoxModel);
				contextComboBox.setBounds(414, 24, 139, 22);
			}
			{
				findButton = new JButton();
				this.add(findButton);
				findButton.setAction(getAppActionMap().get("find"));
				findButton.setBounds(381, 26, 18, 20);
				findButton.setName("findButton");
				findButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						findButtonActionPerformed(evt);
					}
				});
			}
			{
				assertButton = new JButton();
				this.add(assertButton);
				assertButton.setAction(getAppActionMap().get("assertAction"));
				assertButton.setBounds(358, 26, 18, 20);
				assertButton.setName("assertButton");
				assertButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						assertButtonActionPerformed(evt);
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
	
	private void assertButtonActionPerformed(ActionEvent evt) {
		JFrame popupFrame = new JFrame("Assert");
		popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popupFrame.getContentPane().add(new cmdAssert(network, frame));
		popupFrame.pack();
		popupFrame.setVisible(true);
	}
	
	private void findButtonActionPerformed(ActionEvent evt) {
		JFrame popupFrame = new JFrame("Find");
		popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popupFrame.getContentPane().add(new cmdFind(network, frame));
		popupFrame.pack();
		popupFrame.setVisible(true);
	}
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		try {
			LinkedList<Node> nodes = new LinkedList<Node>();
			for(int i = 0; i < nodesetModel.size(); i++) {
				Node node = network.getNode(nodesetModel.getElementAt(i).toString());
				nodes.add(node);
			}
			frame.getsNePSULPanel1().getMenuDrivenCommands().nodeInfo(nodes);
		} catch (CustomException e) {
		}
	}
}
