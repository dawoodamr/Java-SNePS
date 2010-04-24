package snepsui.Commands;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
public class cmdSetContext extends javax.swing.JPanel {
	private JLabel setContextLabel;
	private JLabel symbolLabel;
	private JTextField symbolTextField;
	private JButton doneButton;
	private JRadioButton symbolRadioButton2;
	private JScrollPane jScrollPane1;
	private JLabel nodesetLabel;
	private JComboBox nodesetComboBox;
	private JList nodesetList;
	private JTextField nodesetTextField;
	private JButton addButton;
	private JButton buildButton;
	private JButton assertButton;
	private JButton findButton;
	private JRadioButton symbolRadioButton1;
	private ButtonGroup group;
	private JButton infoButton;
	private Network network;
	private DefaultListModel nodesetListModel;
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
	
	public cmdSetContext(Network network, SNePSInterface frame) {
		super();
		this.network = network;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				setContextLabel = new JLabel();
				this.add(setContextLabel);
				setContextLabel.setName("setContextLabel");
				setContextLabel.setBounds(6, 31, 82, 15);
			}
			{
				symbolTextField = new JTextField();
				this.add(symbolTextField);
				symbolTextField.setBounds(479, 52, 163, 22);
				symbolTextField.setEditable(false);
			}
			{
				doneButton = new JButton();
				this.add(doneButton);
				doneButton.setBounds(314, 185, 77, 29);
				doneButton.setName("doneButton");
			}
			{
				symbolLabel = new JLabel();
				this.add(symbolLabel);
				symbolLabel.setBounds(455, 4, 97, 15);
				symbolLabel.setName("symbolLabel");
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
				symbolRadioButton1 = new JRadioButton();
				this.add(symbolRadioButton1);
				symbolRadioButton1.setBounds(455, 26, 97, 19);
				symbolRadioButton1.setName("symbolRadioButton1");
				symbolRadioButton1.setSelected(true);
			}
			{
				symbolRadioButton2 = new JRadioButton();
				this.add(symbolRadioButton2);
				symbolRadioButton2.setBounds(455, 59, 25, 17);
			}
			{
				group = new ButtonGroup();
				group.add(symbolRadioButton1);
				group.add(symbolRadioButton2);
				{
					findButton = new JButton();
					this.add(findButton);
					findButton.setAction(getAppActionMap().get("find"));
					findButton.setBounds(398, 26, 18, 20);
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
					assertButton.setBounds(375, 26, 18, 20);
					assertButton.setName("assertButton");
					assertButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							assertButtonActionPerformed(evt);
						}
					});
				}
				{
					buildButton = new JButton();
					this.add(buildButton);
					buildButton.setAction(getAppActionMap().get("build"));
					buildButton.setBounds(352, 26, 18, 20);
					buildButton.setName("buildButton");
					buildButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buildButtonActionPerformed(evt);
						}
					});
				}
				{
					addButton = new JButton();
					this.add(addButton);
					addButton.setAction(getAppActionMap().get("add"));
					addButton.setBounds(331, 27, 16, 18);
					addButton.setName("addButton");
					addButton.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							nodesetListModel.addElement(nodesetTextField.getText());
							nodesetTextField.setText("");
							validate();
						}
					});
				}
				{
					nodesetTextField = new JTextField();
					this.add(nodesetTextField);
					nodesetTextField.setBounds(202, 25, 117, 22);
				}
				{
					ComboBoxModel nodesetComboBoxModel = new DefaultComboBoxModel();
					nodesetComboBox = new JComboBox();
					this.add(nodesetComboBox);
					nodesetComboBox.setModel(nodesetComboBoxModel);
					nodesetComboBox.setBounds(88, 24, 108, 22);
				}
				{
					nodesetLabel = new JLabel();
					this.add(nodesetLabel);
					nodesetLabel.setName("nodesetLabel");
					nodesetLabel.setBounds(88, 4, 61, 15);
				}
				{
					jScrollPane1 = new JScrollPane();
					this.add(jScrollPane1);
					jScrollPane1.setBounds(88, 52, 231, 111);
					{
						nodesetListModel = new DefaultListModel();
						nodesetList = new JList();
						jScrollPane1.setViewportView(nodesetList);
						nodesetList.setModel(nodesetListModel);
						nodesetList.setBounds(387, 123, 225, 102);
					}
				}
				symbolRadioButton2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						symbolRadioButton2MouseClicked(evt);
					}
				});
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void symbolRadioButton2MouseClicked(MouseEvent evt) {
		if(symbolRadioButton2.isSelected()) {
			symbolTextField.setEditable(true);
		}
		else {
			symbolTextField.setEditable(false);
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
