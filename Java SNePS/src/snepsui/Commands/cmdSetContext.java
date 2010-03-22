package snepsui.Commands;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import SNePS1.NetworkManagementSystem.Network;


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
	private JLabel nodesetLabel;
	private JTextField symbolTextField;
	private JTextField nodesetTextField;
	private JButton addButton;
	private JButton doneButton;
	private JList nodesetList;
	private DefaultListModel nodesetModel;
	private JScrollPane jScrollPane2;
	private JRadioButton symbolRadioButton2;
	private JRadioButton symbolRadioButton1;
	private ButtonGroup group;
	private JButton infoButton;
	private JButton buildButton;
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
	
	private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }
	
	public cmdSetContext(Network network) {
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
				setContextLabel.setBounds(64, 28, 76, 15);
			}
			{
				symbolTextField = new JTextField();
				this.add(symbolTextField);
				symbolTextField.setBounds(450, 54, 184, 22);
				symbolTextField.setEditable(false);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(352, 27, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						nodesetModel.addElement(nodesetTextField.getText());
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
				nodesetTextField.setBounds(152, 25, 189, 22);
			}
			{
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setBounds(152, 4, 61, 15);
				nodesetLabel.setName("nodesetLabel");
			}
			{
				symbolLabel = new JLabel();
				this.add(symbolLabel);
				symbolLabel.setBounds(421, 4, 97, 15);
				symbolLabel.setName("symbolLabel");
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(152, 59, 189, 103);
				{
					nodesetModel = new DefaultListModel();
					nodesetList = new JList();
					jScrollPane2.setViewportView(nodesetList);
					nodesetList.setModel(nodesetModel);
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
				symbolRadioButton1 = new JRadioButton();
				this.add(symbolRadioButton1);
				symbolRadioButton1.setBounds(421, 26, 97, 19);
				symbolRadioButton1.setName("symbolRadioButton1");
				symbolRadioButton1.setSelected(true);
			}
			{
				symbolRadioButton2 = new JRadioButton();
				this.add(symbolRadioButton2);
				symbolRadioButton2.setBounds(420, 61, 25, 17);
			}
			{
				group = new ButtonGroup();
				group.add(symbolRadioButton1);
				group.add(symbolRadioButton2);
				symbolRadioButton2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						symbolRadioButton2MouseClicked(evt);
					}
				});
			}
			{
				buildButton = new JButton();
				this.add(buildButton);
				buildButton.setBounds(379, 26, 18, 20);
				buildButton.setAction(getAppActionMap().get("build"));
				buildButton.setFocusable(false);
				buildButton.setToolTipText("build");
				buildButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						buildButtonActionPerformed(evt);
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
		JFrame frame = new JFrame("Build");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new cmdBuild(network));
		frame.pack();
		frame.setVisible(true);
	}
}
