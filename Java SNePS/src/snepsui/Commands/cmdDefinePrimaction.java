package snepsui.Commands;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
public class cmdDefinePrimaction extends javax.swing.JPanel {
	private JLabel definePrimactionLabel;
	private JTextField actionTextField;
	private JScrollPane jScrollPane1;
	private JTextField relationTextField;
	private JList relationList;
	private JTextArea formTextArea;
	private JLabel formLabel;
	private JLabel relationLabel;
	private JLabel actionLabel;
	private JButton infoButton;
	private JButton doneButton;
	private JButton addButton;
	private DefaultListModel relationModel;
	private Network network;

	@Action
    public void add() {
    	
    }
	
	@Action
    public void info() {
    	
    }
	
	private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }
	
	public cmdDefinePrimaction(Network network) {
		super();
		this.network = network;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				definePrimactionLabel = new JLabel();
				this.add(definePrimactionLabel);
				definePrimactionLabel.setName("definePrimactionLabel");
				definePrimactionLabel.setBounds(18, 40, 127, 15);
			}
			{
				actionLabel = new JLabel();
				this.add(actionLabel);
				actionLabel.setBounds(145, 16, 43, 15);
				actionLabel.setName("actionLabel");
			}
			{
				actionTextField = new JTextField();
				this.add(actionTextField);
				actionTextField.setBounds(145, 37, 145, 22);
			}
			{
				relationLabel = new JLabel();
				this.add(relationLabel);
				relationLabel.setBounds(309, 16, 75, 15);
				relationLabel.setName("relationLabel");
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(309, 65, 159, 101);
				{
					relationModel = new DefaultListModel();
					relationList = new JList();
					jScrollPane1.setViewportView(relationList);
					relationList.setModel(relationModel);
					relationList.setBounds(46, 132, 131, 98);
				}
			}
			{
				formLabel = new JLabel();
				this.add(formLabel);
				formLabel.setBounds(511, 16, 43, 15);
				formLabel.setName("formLabel");
			}
			{
				formTextArea = new JTextArea();
				this.add(formTextArea);
				formTextArea.setBounds(510, 37, 168, 129);
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
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(480, 39, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						relationModel.addElement(relationTextField.getText());
						relationTextField.setText("");
						validate();
					}
				});
			}
			{
				relationTextField = new JTextField();
				this.add(relationTextField);
				relationTextField.setBounds(309, 36, 159, 24);
				relationTextField.setName("relationTextField");
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
