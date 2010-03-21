package SNePS.SNePSUI.Commands;

import java.awt.Dimension;

public class cmdListContextNames extends javax.swing.JPanel {
	
	public cmdListContextNames() {
		super();
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
