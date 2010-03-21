package SNePS.SNePSUI.Commands;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
public class cmdListNodes extends javax.swing.JPanel {
	private JLabel listNodesLabel;
	private JTextField contextNameTextField;
	private JButton doneButton;
	private DefaultListModel listModel;
	private JRadioButton contextNameRadioButton2;
	private JRadioButton contextNameRadioButton1;
	private JButton infoButton;
	private JLabel contextNameLabel;
	private ButtonGroup group;

	@Action
    public void add() {
    	
    }
	
	@Action
    public void info() {
    	
    }
	
	private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }
	
	public cmdListNodes() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				listNodesLabel = new JLabel();
				this.add(listNodesLabel);
				listNodesLabel.setName("listNodesLabel");
				listNodesLabel.setBounds(218, 59, 90, 15);
			}
			{
				contextNameTextField = new JTextField();
				this.add(contextNameTextField);
				contextNameTextField.setBounds(351, 85, 184, 22);
				contextNameTextField.setEditable(false);
			}
			{
				doneButton = new JButton();
				this.add(doneButton);
				doneButton.setBounds(314, 185, 77, 29);
				doneButton.setName("doneButton");
			}
			{
				contextNameLabel = new JLabel();
				this.add(contextNameLabel);
				contextNameLabel.setBounds(326, 32, 97, 15);
				contextNameLabel.setName("contextNameLabel");
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
				contextNameRadioButton1.setBounds(326, 57, 97, 19);
				contextNameRadioButton1.setName("contextNameRadioButton1");
				contextNameRadioButton1.setSelected(true);
			}
			{
				contextNameRadioButton2 = new JRadioButton();
				this.add(contextNameRadioButton2);
				contextNameRadioButton2.setBounds(326, 90, 25, 17);
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

}
