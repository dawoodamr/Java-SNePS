package snepsui.Commands;

import java.awt.Dimension;
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

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import sneps.Network;
import sneps.Node;
import snepsui.Interface.SNePSInterface;


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
public class cmdAddToContext extends javax.swing.JPanel {
	private JLabel addToContextLabel;
	private JButton addButton;
	private JButton doneButton;
	private DefaultListModel nodesetListModel;
	private JScrollPane jScrollPane2;
	private JComboBox contextNameComboBox;
	private JComboBox nodesetComboBox;
	private JButton infoButton;
	private JList nodesetList;
	private JLabel contextNameLabel;
	private JLabel nodesetLabel;
	private JTextField nodesetTextField;
	private ButtonGroup group;
	private JButton buildButton;
	private Network network;
	private JButton assertButton;
	private JButton findButton;
	private SNePSInterface frame;

	public cmdAddToContext(Network network, SNePSInterface frame) {
		super();
		this.frame = frame;
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
	
	@Action
	public void find() {
	}
	
	@Action
	public void assertAction() {
	}

	private ActionMap getAppActionMap() {
		return Application.getInstance().getContext().getActionMap(this);
	}

	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				addToContextLabel = new JLabel();
				this.add(addToContextLabel);
				addToContextLabel.setName("addToContextLabel");
				addToContextLabel.setBounds(19, 27, 108, 15);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(369, 26, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						nodesetListModel.addElement(nodesetTextField.getText());
						nodesetTextField.setText("");
						validate();
					}
				});
			}
			{
				doneButton = new JButton();
				this.add(doneButton);
				doneButton.setBounds(314, 185, 77, 29);
				doneButton.setName("doneButton");
			}
			{
				nodesetTextField = new JTextField();
				this.add(nodesetTextField);
				nodesetTextField.setBounds(241, 24, 117, 22);
			}
			{
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setBounds(127, 4, 61, 15);
				nodesetLabel.setName("nodesetLabel");
			}
			{
				contextNameLabel = new JLabel();
				this.add(contextNameLabel);
				contextNameLabel.setBounds(483, 4, 97, 15);
				contextNameLabel.setName("contextNameLabel");
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(130, 57, 228, 105);
				{
					nodesetListModel = new DefaultListModel();
					nodesetList = new JList();
					jScrollPane2.setViewportView(nodesetList);
					nodesetList.setModel(nodesetListModel);
					nodesetList.setBounds(463, 79, 205, 100);
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
				group = new ButtonGroup();
			}
			{
				buildButton = new JButton();
				this.add(buildButton);
				buildButton.setBounds(390, 25, 18, 20);
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
				assertButton.setBounds(413, 25, 18, 20);
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
				findButton.setBounds(436, 25, 18, 20);
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
				DefaultComboBoxModel nodesetComboBoxModel = new DefaultComboBoxModel();
				
				String str = "";
				Hashtable<String, Node> nodes = network.getNodes();
				Set<String> set = nodes.keySet();

			    Iterator<String> itr = set.iterator();
			    while (itr.hasNext()) {
			      str = itr.next();
			      nodesetComboBoxModel.addElement(nodes.get(str).getIdentifier()) ;
			    }
			    
				nodesetComboBox = new JComboBox();
				this.add(nodesetComboBox);
				nodesetComboBox.setModel(nodesetComboBoxModel);
				nodesetComboBox.setBounds(127, 23, 108, 22);
			}
			{
				ComboBoxModel contextNameComboBoxModel = new DefaultComboBoxModel();
				contextNameComboBox = new JComboBox();
				this.add(contextNameComboBox);
				contextNameComboBox.setModel(contextNameComboBoxModel);
				contextNameComboBox.setBounds(483, 25, 170, 22);
			}
			Application.getInstance().getContext().getResourceMap(getClass())
					.injectComponents(this);
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
}
