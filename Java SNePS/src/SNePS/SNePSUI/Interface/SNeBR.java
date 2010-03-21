package SNePS.SNePSUI.Interface;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.WindowConstants;

import org.jdesktop.application.Application;

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
public class SNeBR extends javax.swing.JPanel {

	private JTextArea jTextArea1;
	private JList jList1;
	private JButton jButton1;
	private JButton jButton2;
	private JComboBox jComboBox1;
	private JTextArea jTextArea2;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new SNeBR());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public SNeBR() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setLayout(null);
			setPreferredSize(new Dimension(254, 593));
			{
				jTextArea1 = new JTextArea();
				this.add(jTextArea1);
				jTextArea1.setBounds(6, 27, 243, 147);
				jTextArea1.setName("jTextArea1");
				jTextArea1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Contradiction"));
			}
			{
				ListModel jList1Model = 
					new DefaultComboBoxModel(
							new String[] { "Item One", "Item Two" });
				jList1 = new JList();
				this.add(jList1);
				jList1.setModel(jList1Model);
				jList1.setBounds(6, 219, 243, 144);
				jList1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Culprits"));
			}
			{
				jTextArea2 = new JTextArea();
				this.add(jTextArea2);
				jTextArea2.setBounds(6, 375, 243, 162);
				jTextArea2.setName("jTextArea2");
				jTextArea2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Culprit Details"));
			}
			{
				ComboBoxModel jComboBox1Model = 
					new DefaultComboBoxModel(
							new String[] { "Delete", "Continue" , "Repair" });
				jComboBox1 = new JComboBox();
				this.add(jComboBox1);
				jComboBox1.setModel(jComboBox1Model);
				jComboBox1.setBounds(18, 185, 161, 22);
			}
			{
				jButton1 = new JButton();
				this.add(jButton1);
				jButton1.setBounds(132, 549, 102, 32);
				jButton1.setName("jButton1");
			}
			{
				jButton2 = new JButton();
				this.add(jButton2);
				jButton2.setBounds(12, 549, 99, 32);
				jButton2.setName("jButton2");
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
