package snepsui.Commands;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
public class cmdIfDo extends javax.swing.JPanel {
	private JLabel buildLabel2;
	private JLabel wheneverLabel;
	private JLabel assertLabel;
	private JLabel relationLabel;
	private JLabel buildLabel1;
	private JLabel nodesetLabel;
	private JLabel doLabel;
	private JTextField nodesetTextField2;
	private JTextField nodesetTextField1;
	private JTextField relationTextField2;
	private JTextField relationTextField1;
	private JList nodesetList2;
	private JList relationList1;
	private JList relationList2;
	private JList nodesetList1;
	private DefaultListModel relationModel1;
	private DefaultListModel relationModel2;
	private DefaultListModel nodesetModel1;
	private DefaultListModel nodesetModel2;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane4;
	private JScrollPane jScrollPane3;
	private JButton addButton1;
	private JButton infoButton;
	private JButton addButton2;
	private JButton doneButton;
	private JButton buildButton1;
	private JButton buildButton2;
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

	public cmdIfDo(Network network) {
		super();
		this.network = network;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				buildLabel2 = new JLabel();
				this.add(buildLabel2);
				buildLabel2.setName("buildLabel2");
				buildLabel2.setBounds(141, 106, 45, 15);
			}
			{
				nodesetTextField2 = new JTextField();
				this.add(nodesetTextField2);
				nodesetTextField2.setBounds(408, 103, 189, 22);
			}
			{
				addButton2 = new JButton();
				this.add(addButton2);
				addButton2.setBounds(613, 105, 16, 18);
				addButton2.setAction(getAppActionMap().get("add"));
				addButton2.setFocusable(false);
				addButton2.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						relationModel2.addElement(relationTextField2.getText());
						nodesetModel2.addElement(nodesetTextField2.getText());
						relationTextField2.setText("");
						nodesetTextField2.setText("");
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
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(408, 134, 189, 44);
				{
					nodesetModel2 = new DefaultListModel();
					nodesetList2 = new JList();
					jScrollPane1.setViewportView(nodesetList2);
					nodesetList2.setModel(nodesetModel2);
					nodesetList2.setBounds(566, 171, 186, 33);
				}
			}
			{
				relationTextField2 = new JTextField();
				this.add(relationTextField2);
				relationTextField2.setBounds(204, 103, 189, 22);
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(204, 131, 189, 48);
				{
					relationModel2 = new DefaultListModel();
					relationList2 = new JList();
					jScrollPane2.setViewportView(relationList2);
					relationList2.setModel(relationModel2);
					relationList2.setBounds(103, 152, 186, 33);
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
				addButton1 = new JButton();
				this.add(addButton1);
				addButton1.setAction(getAppActionMap().get("add"));
				addButton1.setBounds(613, 23, 16, 18);
				addButton1.setName("addButton1");
				addButton1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						relationModel1.addElement(relationTextField1.getText());
						nodesetModel1.addElement(nodesetTextField1.getText());
						relationTextField1.setText("");
						nodesetTextField1.setText("");
						validate();
					}
				});
			}
			{
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setName("nodesetLabel");
				nodesetLabel.setBounds(408, 0, 66, 15);
			}
			{
				relationTextField1 = new JTextField();
				this.add(relationTextField1);
				relationTextField1.setBounds(204, 20, 189, 22);
			}
			{
				jScrollPane3 = new JScrollPane();
				this.add(jScrollPane3);
				jScrollPane3.setBounds(408, 48, 189, 49);
				{
					nodesetModel1 = new DefaultListModel();
					nodesetList1 = new JList();
					jScrollPane3.setViewportView(nodesetList1);
					nodesetList1.setModel(nodesetModel1);
					nodesetList1.setBounds(393, 171, 186, 33);
				}
			}
			{
				jScrollPane4 = new JScrollPane();
				this.add(jScrollPane4);
				jScrollPane4.setBounds(204, 48, 189, 49);
				{
					relationModel1 = new DefaultListModel();
					relationList1 = new JList();
					jScrollPane4.setViewportView(relationList1);
					relationList1.setModel(relationModel1);
					relationList1.setBounds(99, 50, 186, 33);
				}
			}
			{
				relationLabel = new JLabel();
				this.add(relationLabel);
				relationLabel.setName("relationLabel");
				relationLabel.setBounds(204, 0, 70, 15);
			}
			{
				nodesetTextField1 = new JTextField();
				this.add(nodesetTextField1);
				nodesetTextField1.setBounds(408, 21, 189, 22);
			}
			{
				buildLabel1 = new JLabel();
				this.add(buildLabel1);
				buildLabel1.setName("buildLabel1");
				buildLabel1.setBounds(141, 23, 45, 15);
			}
			{
				assertLabel = new JLabel();
				this.add(assertLabel);
				assertLabel.setBounds(18, 23, 35, 15);
				assertLabel.setName("assertLabel");
			}
			{
				wheneverLabel = new JLabel();
				this.add(wheneverLabel);
				wheneverLabel.setBounds(70, 23, 65, 15);
				wheneverLabel.setName("wheneverLabel");
			}
			{
				doLabel = new JLabel();
				this.add(doLabel);
				doLabel.setBounds(70, 106, 15, 15);
				doLabel.setName("doLabel");
			}
			{
				buildButton1 = new JButton();
				this.add(buildButton1);
				buildButton1.setBounds(634, 22, 18, 20);
				buildButton1.setAction(getAppActionMap().get("build"));
				buildButton1.setFocusable(false);
				buildButton1.setToolTipText("build");
				buildButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						buildButtonActionPerformed(evt);
					}
				});
			}
			{
				buildButton2 = new JButton();
				this.add(buildButton2);
				buildButton2.setBounds(634, 104, 18, 20);
				buildButton2.setAction(getAppActionMap().get("build"));
				buildButton2.setFocusable(false);
				buildButton2.setToolTipText("build");
				buildButton2.addActionListener(new ActionListener() {
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
	
	private void buildButtonActionPerformed(ActionEvent evt) {
		JFrame frame = new JFrame("Build");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new cmdBuild(network));
		frame.pack();
		frame.setVisible(true);
	}
}
