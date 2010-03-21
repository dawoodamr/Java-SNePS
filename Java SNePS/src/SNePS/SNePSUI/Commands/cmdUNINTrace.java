package SNePS.SNePSUI.Commands;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.application.Action;
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
public class cmdUNINTrace extends javax.swing.JPanel {
	private JLabel jLabel1;
	private JTextField jTextField1;
	private JButton addButton;
	private JList jList1;
	private JButton doneButton;
	private DefaultListModel listModel;
	private JScrollPane jScrollPane1;
	private JLabel jLabel2;
	private JButton infoButton;
	
	@Action
    public void add() {
    	
    }
	
	@Action
    public void info() {
    	
    }
	
	private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }
	
	public cmdUNINTrace() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setName("jLabel1");
				jLabel1.setBounds(167, 35, 76, 15);
			}
			{
				jTextField1 = new JTextField();
				this.add(jTextField1);
				jTextField1.setBounds(255, 32, 192, 22);
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(465, 34, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						listModel.addElement(jTextField1.getText());
						jTextField1.setText("");
						validate();
					}
				});
			}
			{
				doneButton = new JButton();
				this.add(doneButton);
				doneButton.setBounds(315, 181, 77, 29);
				doneButton.setName("doneButton");
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(258, 66, 190, 103);
				{
					listModel = new DefaultListModel();
					jList1 = new JList();
					jScrollPane1.setViewportView(jList1);
					jList1.setModel(listModel);
					jList1.setBounds(86, 110, 187, 100);
				}
			}
			{
				infoButton = new JButton();
				this.add(infoButton);
				infoButton.setBounds(668, 196, 16, 18);
				infoButton.setAction(getAppActionMap().get("info"));
				infoButton.setFocusable(false);
				infoButton.setToolTipText("info");
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2);
				jLabel2.setBounds(255, 12, 109, 15);
				jLabel2.setName("jLabel2");
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
