package snepsui.Commands;

import java.awt.Dimension;

public class cmdWithSome extends javax.swing.JPanel {

	public cmdWithSome() {
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
