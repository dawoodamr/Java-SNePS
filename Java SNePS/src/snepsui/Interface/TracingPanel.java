package snepsui.Interface;

import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.JTextPane;

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
public class TracingPanel extends javax.swing.JPanel {
	private JTextPane jTextPane1;
	private JTextArea jTextArea1;

	public TracingPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(524, 235));
			{
				jTextArea1 = new JTextArea();
				this.add(jTextArea1);
				jTextArea1.setPreferredSize(new java.awt.Dimension(347, 187));
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
