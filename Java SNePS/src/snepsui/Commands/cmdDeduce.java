package snepsui.Commands;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Iterator;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import match.ds.Substitutions;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import snebr.Context;
import sneps.CustomException;
import sneps.MolecularNode;
import sneps.Network;
import sneps.Node;
import snepsui.Interface.SNePSInterface;
import snip.ds.Report;
import snip.ds.ReportSet;


/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */

/**
 * @author Alia Taher
 */
@SuppressWarnings("unused")
public class cmdDeduce extends javax.swing.JPanel {
	private JLabel deduceLabel;
	private JButton addButton;
	private JList nodesetList;
	private JButton doneButton;
	private DefaultListModel relationListModel;
	private DefaultListModel nodesetListModel;
	private JScrollPane jScrollPane1;
	private JButton assertButton;
	private JButton findButton;
	private JComboBox contextNameComboBox;
	private JComboBox nodesComboBox;
	private JLabel contextNameLabel;
	private JButton infoButton;
	private JLabel nodesetLabel;
	private ButtonGroup group;
	private JButton buildButton;
	private Network network;
	private SNePSInterface frame;

	@Action
	public void add() {}
	
	@Action
	public void build() {}

	@Action
	public void info() {}
	
	@Action
	public void find() {}
	
	@Action
	public void assertAction() {}

	private ActionMap getAppActionMap() {
		return Application.getInstance().getContext().getActionMap(this);
	}

	public cmdDeduce(Network network, SNePSInterface frame) {
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
				deduceLabel = new JLabel();
				this.add(deduceLabel);
				deduceLabel.setName("deduceLabel");
				deduceLabel.setBounds(12, 28, 63, 15);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(364, 24, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						nodesetListModel.addElement(nodesComboBox.getSelectedItem().toString());
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
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(113, 56, 242, 103);
				{
					nodesetListModel = new DefaultListModel();
					nodesetList = new JList();
					jScrollPane1.setViewportView(nodesetList);
					nodesetList.setModel(nodesetListModel);
					nodesetList.setBounds(391, 67, 239, 100);
				}
			}
			{
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setBounds(113, 4, 95, 15);
				nodesetLabel.setName("nodesetLabel");
			}
			{
				buildButton = new JButton();
				this.add(buildButton);
				buildButton.setBounds(385, 23, 18, 20);
				buildButton.setAction(getAppActionMap().get("build"));
				buildButton.setToolTipText("build");
				buildButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						buildButtonActionPerformed(evt);
					}
				});
			}
			{
				infoButton = new JButton();
				this.add(infoButton);
				infoButton.setBounds(668, 196, 16, 18);
				infoButton.setAction(getAppActionMap().get("info"));
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
				contextNameLabel.setBounds(472, 2, 107, 19);
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
			      if(node instanceof MolecularNode)
			    	  nodesetComboBoxModel.addElement(node.getIdentifier()) ;
			    }
				nodesComboBox = new JComboBox();
				this.add(nodesComboBox);
				nodesComboBox.setModel(nodesetComboBoxModel);
				nodesComboBox.setBounds(113, 21, 239, 22);
			}
			{
				ComboBoxModel contextNameComboBoxModel = new DefaultComboBoxModel();
				contextNameComboBox = new JComboBox();
				this.add(contextNameComboBox);
				contextNameComboBox.setModel(contextNameComboBoxModel);
				contextNameComboBox.setBounds(472, 21, 151, 22);
			}
			{
				findButton = new JButton();
				this.add(findButton);
				findButton.setAction(getAppActionMap().get("find"));
				findButton.setName("findButton");
				findButton.setBounds(431, 23, 18, 20);
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
				assertButton.setName("assertButton");
				assertButton.setBounds(408, 23, 18, 20);
				assertButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						assertButtonActionPerformed(evt);
					}
				});
			}
			Application.getInstance().getContext().getResourceMap(getClass())
					.injectComponents(this);
		} catch (Exception e) {}
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
		for (int i = 0; i < nodesetListModel.size(); i++) {
			try {
				MolecularNode molNode = (MolecularNode) network.getNode(nodesetListModel.get(i).toString());
				ReportSet reportSet = network.deduce(molNode,new Context());
				if(reportSet == null) {
					break;
				}
				
				for (int j = 0; j < reportSet.cardinality(); j++) {
					Report report = reportSet.getReport(j);
					frame.getsNePSULPanel1().getMenuDrivenCommands().nodeInfo(report.getNode());
					Substitutions sub = report.getSubstitutions();
					frame.getOutputPanel1().writeToTextArea(sub.toString());
				}
				
			} catch (CustomException e) {}
		}
	}
}
