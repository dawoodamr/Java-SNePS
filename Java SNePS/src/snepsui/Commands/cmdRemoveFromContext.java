package snepsui.Commands;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import sneps.Network;



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
public class cmdRemoveFromContext extends javax.swing.JPanel {
	private JLabel removeFromContextLabel;
	private JLabel contextNameLabel;
	private JLabel nodesetLabel;
	private JTextField contextNameTextField;
	private JTextField nodesetTextField;
	private JList nodesetList;
	private DefaultListModel listModel;
	private JScrollPane jScrollPane2;
	private JRadioButton contextNameRadioButton2;
	private JRadioButton contextNameRadioButton1;
	private ButtonGroup group;
	private JButton buildButton;
	private JButton addButton;
	private JButton doneButton;
	private JButton infoButton;
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
	
	public cmdRemoveFromContext(Network network) {
		super();
		this.network = network;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				removeFromContextLabel = new JLabel();
				this.add(removeFromContextLabel);
				removeFromContextLabel.setName("removeFromContextLabel");
				removeFromContextLabel.setBounds(12, 28, 150, 15);
			}
			{
				contextNameTextField = new JTextField();
				this.add(contextNameTextField);
				contextNameTextField.setBounds(459, 62, 184, 22);
				contextNameTextField.setEditable(false);
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
						listModel.addElement(contextNameTextField.getText());
						contextNameTextField.setText("");
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
				nodesetTextField.setBounds(151, 25, 189, 22);
			}
			{
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setBounds(151, 4, 61, 15);
				nodesetLabel.setName("nodesetLabel");
			}
			{
				contextNameLabel = new JLabel();
				this.add(contextNameLabel);
				contextNameLabel.setBounds(433, 4, 97, 15);
				contextNameLabel.setName("contextNameLabel");
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(151, 62, 189, 103);
				{
					ListModel jList2Model = 
						new DefaultComboBoxModel();
					nodesetList = new JList();
					jScrollPane2.setViewportView(nodesetList);
					nodesetList.setModel(jList2Model);
					nodesetList.setBounds(161, 136, 186, 100);
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
				contextNameRadioButton1 = new JRadioButton();
				this.add(contextNameRadioButton1);
				contextNameRadioButton1.setBounds(433, 29, 97, 19);
				contextNameRadioButton1.setName("contextNameRadioButton1");
				contextNameRadioButton1.setSelected(true);
			}
			{
				contextNameRadioButton2 = new JRadioButton();
				this.add(contextNameRadioButton2);
				contextNameRadioButton2.setBounds(433, 67, 25, 17);
			}
			{
				group = new ButtonGroup();
				group.add(contextNameRadioButton1);
				group.add(contextNameRadioButton2);
				contextNameRadioButton2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						contextNameRadioButton2MouseClicked(evt);
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
	
	private void contextNameRadioButton2MouseClicked(MouseEvent evt) {
		if(contextNameRadioButton2.isSelected()) {
			contextNameTextField.setEditable(true);
		}
		else {
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
}
