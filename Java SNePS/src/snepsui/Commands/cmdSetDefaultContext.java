package snepsui.Commands;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JLabel;
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

/**
 * @author Alia Taher
 */
@SuppressWarnings("unused")
public class cmdSetDefaultContext extends javax.swing.JPanel {
	private JLabel setDefaultContextNameLabel;
	private JLabel contextNameLabel;
	private JTextField contextNameTextField;
	private JButton doneButton;
	private JButton infoButton;
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
	
	public cmdSetDefaultContext(Network network, SNePSInterface frame) {
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
				setDefaultContextNameLabel = new JLabel();
				this.add(setDefaultContextNameLabel);
				setDefaultContextNameLabel.setName("setDefaultContextNameLabel");
				setDefaultContextNameLabel.setBounds(165, 92, 129, 15);
			}
			{
				contextNameTextField = new JTextField();
				this.add(contextNameTextField);
				contextNameTextField.setBounds(306, 89, 184, 22);
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
				contextNameLabel.setBounds(306, 68, 97, 15);
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
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {}
	}
}
