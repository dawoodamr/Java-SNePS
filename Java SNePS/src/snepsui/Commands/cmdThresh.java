package snepsui.Commands;

import java.awt.Dimension;

import sneps.Network;
import snepsui.Interface.SNePSInterface;

public class cmdThresh extends javax.swing.JPanel {
	private Network network;
	private SNePSInterface frame;
	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	
	public cmdThresh(Network network, SNePSInterface frame) {
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
