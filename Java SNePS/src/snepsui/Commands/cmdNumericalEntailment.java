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
public class cmdNumericalEntailment extends javax.swing.JPanel {
	private JLabel assertLabel;
	private JLabel antLabel;
	private JLabel cqLabel;
	private JLabel buildLabel1;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JList nodesetList2;
	private JList nodesetList1;
	private JTextField threshTextField;
	private JLabel threshLabel;
	private JList relationList2;
	private JList relationList1;
	private JLabel nodesetLabel;
	private JLabel relationLabel;
	private JScrollPane jScrollPane4;
	private JScrollPane jScrollPane3;
	private JTextField nodesetTextField2;
	private JTextField nodesetTextField1;
	private JTextField relationTextField2;
	private JTextField relationTextField1;
	private JLabel buildLabel2;
	private JButton addButton1;
	private JButton addButton2;
	private JButton doneButton;
	private JButton infoButton;
	private JButton buildButton1;
	private JButton buildButton2;
	private DefaultListModel relationModel1;
	private DefaultListModel nodesetModel1;
	private DefaultListModel relationModel2;
	private DefaultListModel nodesetModel2;
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

	public cmdNumericalEntailment(Network network) {
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
				assertLabel.setBounds(12, 31, 49, 15);
			}
			{
				antLabel = new JLabel();
				this.add(antLabel);
				antLabel.setBounds(153, 31, 34, 15);
				antLabel.setName("antLabel");
			}
			{
				cqLabel = new JLabel();
				this.add(cqLabel);
				cqLabel.setBounds(153, 110, 20, 15);
				cqLabel.setName("cqLabel");
			}
			{
				buildLabel1 = new JLabel();
				this.add(buildLabel1);
				buildLabel1.setBounds(199, 31, 43, 15);
				buildLabel1.setName("buildLabel1");
			}
			{
				buildLabel2 = new JLabel();
				this.add(buildLabel2);
				buildLabel2.setBounds(199, 110, 37, 15);
				buildLabel2.setName("buildLabel2");
			}
			{
				relationTextField1 = new JTextField();
				this.add(relationTextField1);
				relationTextField1.setBounds(254, 28, 174, 22);
				relationTextField1.setName("relationTextField1");
			}
			{
				relationTextField2 = new JTextField();
				this.add(relationTextField2);
				relationTextField2.setBounds(254, 107, 171, 22);
				relationTextField2.setName("relationTextField2");
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(254, 56, 171, 39);
				{
					relationModel1 = new DefaultListModel();
					relationList1 = new JList();
					jScrollPane1.setViewportView(relationList1);
					relationList1.setModel(relationModel1);
					relationList1.setBounds(156, 129, 168, 36);
				}
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(254, 135, 171, 44);
				{
					relationModel2 = new DefaultListModel();
					relationList2 = new JList();
					jScrollPane2.setViewportView(relationList2);
					relationList2.setModel(relationModel2);
					relationList2.setBounds(57, 144, 168, 41);
				}
			}
			{
				nodesetTextField1 = new JTextField();
				this.add(nodesetTextField1);
				nodesetTextField1.setBounds(452, 28, 176, 22);
			}
			{
				nodesetTextField2 = new JTextField();
				this.add(nodesetTextField2);
				nodesetTextField2.setBounds(452, 107, 176, 22);
			}
			{
				jScrollPane3 = new JScrollPane();
				this.add(jScrollPane3);
				jScrollPane3.setBounds(452, 56, 176, 39);
				{
					nodesetModel1 = new DefaultListModel();
					nodesetList1 = new JList();
					jScrollPane3.setViewportView(nodesetList1);
					nodesetList1.setModel(nodesetModel1);
					nodesetList1.setBounds(174, 201, 173, 36);
				}
			}
			{
				jScrollPane4 = new JScrollPane();
				this.add(jScrollPane4);
				jScrollPane4.setBounds(452, 135, 176, 45);
				{
					nodesetModel2 = new DefaultListModel();
					nodesetList2 = new JList();
					jScrollPane4.setViewportView(nodesetList2);
					nodesetList2.setModel(nodesetModel2);
					nodesetList2.setBounds(185, 168, 173, 42);
				}
			}
			{
				relationLabel = new JLabel();
				this.add(relationLabel);
				relationLabel.setBounds(254, 7, 46, 15);
				relationLabel.setName("relationLabel");
			}
			{
				nodesetLabel = new JLabel();
				this.add(nodesetLabel);
				nodesetLabel.setBounds(452, 7, 48, 15);
				nodesetLabel.setName("nodesetLabel");
			}
			{
				doneButton = new JButton();
				this.add(doneButton);
				doneButton.setBounds(314, 185, 77, 29);
				doneButton.setName("doneButton");
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
				addButton1.setBounds(634, 30, 16, 18);
				addButton1.setAction(getAppActionMap().get("add"));
				addButton1.setFocusable(false);
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
				addButton2 = new JButton();
				this.add(addButton2);
				addButton2.setBounds(634, 109, 16, 18);
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
				buildButton1 = new JButton();
				this.add(buildButton1);
				buildButton1.setBounds(661, 29, 18, 20);
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
				buildButton2.setBounds(661, 108, 18, 20);
				buildButton2.setAction(getAppActionMap().get("build"));
				buildButton2.setFocusable(false);
				buildButton2.setToolTipText("build");
				buildButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						buildButtonActionPerformed(evt);
					}
				});
			}
			{
				threshLabel = new JLabel();
				this.add(threshLabel);
				threshLabel.setBounds(58, 31, 45, 15);
				threshLabel.setName("threshLabel");
			}
			{
				threshTextField = new JTextField();
				this.add(threshTextField);
				threshTextField.setBounds(103, 28, 34, 22);
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
