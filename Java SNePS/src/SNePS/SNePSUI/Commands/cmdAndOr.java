package SNePS.SNePSUI.Commands;

import java.awt.Dimension;

import SNePS.NetworkManagementSystem.Network;


public class cmdAndOr extends javax.swing.JPanel {
	private Network network;

	public cmdAndOr(Network network) {
		super();
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
