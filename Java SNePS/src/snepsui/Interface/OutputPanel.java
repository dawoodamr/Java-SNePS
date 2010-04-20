package snepsui.Interface;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;

import javax.swing.JTextArea;

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
public class OutputPanel extends javax.swing.JPanel {
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;

	public OutputPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(524, 235));
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setPreferredSize(new java.awt.Dimension(347, 187));
				{
					jTextArea1 = new JTextArea();
					jTextArea1.setEditable(false);
					jScrollPane1.setViewportView(jTextArea1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeToTextArea(String text) {
		jTextArea1.setSelectedTextColor(Color.BLACK);
		jTextArea1.append(text);
		this.validate();
		this.repaint();
	}
	
	public void writeToTextArea(String text, Color color) {
		jTextArea1.setSelectedTextColor(color);
		jTextArea1.append(text);
		this.validate();
		this.repaint();
	}
}
