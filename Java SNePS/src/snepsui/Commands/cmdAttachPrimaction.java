package snepsui.Commands;

import java.awt.Dimension;

import sneps.Network;
import snepsui.Interface.SNePSInterface;

public class cmdAttachPrimaction extends javax.swing.JPanel {
	private Network network;
	private SNePSInterface frame;
	
	public cmdAttachPrimaction(Network network, SNePSInterface frame) {
		super();
		this.frame = frame;
		this.network = network;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}