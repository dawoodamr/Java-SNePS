package snepsui.Interface;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

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
public class SNePSULPanel extends javax.swing.JPanel {
	public JTabbedPane jTabbedPane1;
	public MenuDrivenCommands menuDrivenCommands;
	public VisualizeNetworks visualizeNetworks;
	private DrawNetwork drawNetworks;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new SNePSULPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public SNePSULPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			{
				jTabbedPane1 = new JTabbedPane();
				this.add(jTabbedPane1);
				jTabbedPane1.setPreferredSize(new java.awt.Dimension(794, 358));
				{
					menuDrivenCommands = new MenuDrivenCommands();
					jTabbedPane1.addTab("Commands", null, menuDrivenCommands, null);
					menuDrivenCommands.setPreferredSize(new java.awt.Dimension(766, 220));
				}
				{
					Network network = new Network();
					drawNetworks = new DrawNetwork(network);
					jTabbedPane1.addTab("Draw Network", null, drawNetworks, null);
					drawNetworks.setPreferredSize(new java.awt.Dimension(613, 274));
				}
				{
					visualizeNetworks = new VisualizeNetworks();
					jTabbedPane1.addTab("View Network", null, visualizeNetworks, null);
					visualizeNetworks.setPreferredSize(new java.awt.Dimension(759, 346));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
