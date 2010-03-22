package snepsui.Commands;

import java.awt.Dimension;

import javax.swing.WindowConstants;
import javax.swing.JFrame;

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
