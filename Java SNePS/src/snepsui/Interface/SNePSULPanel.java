package snepsui.Interface;

import java.awt.Dimension;

import javax.swing.JTabbedPane;

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
	private JTabbedPane jTabbedPane1;
	private MenuDrivenCommands menuDrivenCommands;
	private VisualizeNetworks visualizeNetworks;
	private DrawNetwork drawNetworks;
	private Network network;
	private SNePSInterface frame;
	
	public SNePSULPanel(SNePSInterface frame) {
		super();
		this.frame = frame;
		initGUI();
	}
	
	public void initGUI() {
		try {
			removeAll();
			{
				jTabbedPane1 = new JTabbedPane();
				this.add(jTabbedPane1);
				jTabbedPane1.setPreferredSize(new Dimension(794, 358));
				{
					menuDrivenCommands = new MenuDrivenCommands(frame);
					jTabbedPane1.addTab("Commands", null, menuDrivenCommands, null);
				}
				{
					drawNetworks = new DrawNetwork(frame);
					jTabbedPane1.addTab("Draw Network", null, drawNetworks, null);
				}
				{
					visualizeNetworks = new VisualizeNetworks();
					jTabbedPane1.addTab("View Network", null, visualizeNetworks, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JTabbedPane getjTabbedPane1() {
		return jTabbedPane1;
	}

	public MenuDrivenCommands getMenuDrivenCommands() {
		return menuDrivenCommands;
	}
	
	public VisualizeNetworks getVisualizeNetworks() {
		return visualizeNetworks;
	}

	public DrawNetwork getDrawNetworks() {
		return drawNetworks;
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}
}
