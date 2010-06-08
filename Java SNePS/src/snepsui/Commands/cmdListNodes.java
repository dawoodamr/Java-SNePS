package snepsui.Commands;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ActionMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

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

/**
 * @author Alia Taher
 */
@SuppressWarnings("unused")
public class cmdListNodes extends javax.swing.JPanel {
	private JLabel listNodesLabel;
	private JButton doneButton;
	private JComboBox contextComboBox;
	private JButton infoButton;
	private JLabel contextNameLabel;
	private Network network;
	private SNePSInterface frame;

	@Action
    public void add() {
    	
    }
	
	@Action
    public void info() {
    	
    }
	
	private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }
	
	public cmdListNodes(Network network, SNePSInterface frame) {
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
				listNodesLabel = new JLabel();
				this.add(listNodesLabel);
				listNodesLabel.setName("listNodesLabel");
				listNodesLabel.setBounds(218, 59, 90, 15);
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
				infoButton.setFocusPainted(false);
				infoButton.setBorderPainted(false);
				infoButton.setContentAreaFilled(false);
				infoButton.setMargin(new Insets(0,0,0,0));
				infoButton.setToolTipText("info");
			}
			{
				ComboBoxModel contextComboBoxModel = new DefaultComboBoxModel();
				contextComboBox = new JComboBox();
				this.add(contextComboBox);
				contextComboBox.setModel(contextComboBoxModel);
				contextComboBox.setBounds(326, 55, 168, 22);
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {}
	}
}
