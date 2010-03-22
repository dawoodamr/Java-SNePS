package snepsui.Commands;

import java.awt.Dimension;

import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

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
public class cmdUniversalQunatifier extends javax.swing.JPanel {
	private JLabel forallLabel;
	private JLabel assertLabel;
	private JLabel variableLabel;
	private JScrollPane jScrollPane1;
	private JList variablesList;
	private JLabel antLabel;
	private JScrollPane jScrollPane2;
	private JButton buildButton2;
	private JList cqList;
	private JScrollPane jScrollPane3;
	private JLabel cqLabel;
	private JButton buildButton1;
	private JTextField cqTextField;
	private JList antList;
	private JTextField antTextField;
	private JTextField variablesTextField;
	private JButton infoButton;
	private JButton addButton;
	private JButton doneButton;

	public cmdUniversalQunatifier() {
		super();
		initGUI();
	}
	
	@Action
	public void info() {
	}
	
	@Action
	public void build() {
	}
	
	@Action
	public void add() {
	}
	
	private ActionMap getAppActionMap() {
		return Application.getInstance().getContext().getActionMap(this);
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				forallLabel = new JLabel();
				this.add(forallLabel);
				forallLabel.setName("forallLabel");
				forallLabel.setBounds(58, 27, 35, 15);
			}
			{
				assertLabel = new JLabel();
				this.add(assertLabel);
				assertLabel.setBounds(4, 27, 48, 15);
				assertLabel.setName("assertLabel");
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(243, 26, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
			}
			{
				variablesTextField = new JTextField();
				this.add(variablesTextField);
				variablesTextField.setBounds(105, 24, 121, 22);
			}
			{
				variableLabel = new JLabel();
				this.add(variableLabel);
				variableLabel.setBounds(105, 3, 77, 15);
				variableLabel.setName("variableLabel");
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(106, 58, 120, 116);
				{
					ListModel jList1Model = new DefaultComboBoxModel();
					variablesList = new JList();
					jScrollPane1.setViewportView(variablesList);
					variablesList.setModel(jList1Model);
					variablesList.setBounds(145, 125, 117, 85);
				}
			}
			{
				antTextField = new JTextField();
				this.add(antTextField);
				antTextField.setBounds(304, 24, 143, 22);
			}
			{
				antLabel = new JLabel();
				this.add(antLabel);
				antLabel.setBounds(270, 27, 26, 15);
				antLabel.setName("antLabel");
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(304, 58, 142, 115);
				{
					ListModel jList2Model = new DefaultComboBoxModel();
					antList = new JList();
					jScrollPane2.setViewportView(antList);
					antList.setModel(jList2Model);
					antList.setBounds(195, 96, 139, 118);
					antList.setPreferredSize(new java.awt.Dimension(70, 112));
				}
			}
			{
				cqLabel = new JLabel();
				this.add(cqLabel);
				cqLabel.setBounds(483, 27, 19, 15);
				cqLabel.setName("cqLabel");
			}
			{
				buildButton1 = new JButton();
				this.add(buildButton1);
				buildButton1.setBounds(454, 25, 18, 20);
				buildButton1.setAction(getAppActionMap().get("build"));
				buildButton1.setFocusable(false);
				buildButton1.setToolTipText("build");
			}
			{
				cqTextField = new JTextField();
				this.add(cqTextField);
				cqTextField.setBounds(508, 24, 147, 22);
			}
			{
				jScrollPane3 = new JScrollPane();
				this.add(jScrollPane3);
				jScrollPane3.setBounds(508, 59, 147, 114);
				{
					ListModel jList3Model = new DefaultComboBoxModel();
					cqList = new JList();
					jScrollPane3.setViewportView(cqList);
					cqList.setModel(jList3Model);
					cqList.setBounds(533, 23, 142, 111);
				}
			}
			{
				buildButton2 = new JButton();
				this.add(buildButton2);
				buildButton2.setBounds(661, 25, 18, 20);
				buildButton2.setAction(getAppActionMap().get("build"));
				buildButton2.setFocusable(false);
				buildButton2.setToolTipText("build");
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
				doneButton = new JButton();
				this.add(doneButton);
				doneButton.setBounds(314, 185, 77, 29);
				doneButton.setName("doneButton");
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
