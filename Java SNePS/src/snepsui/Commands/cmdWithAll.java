package snepsui.Commands;

import java.awt.Dimension;

public class cmdWithAll extends javax.swing.JPanel {

	public cmdWithAll() {
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
