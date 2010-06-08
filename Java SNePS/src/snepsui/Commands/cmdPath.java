package snepsui.Commands;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.ActionMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import sneps.AndPath;
import sneps.BUnitPath;
import sneps.BangPath;
import sneps.ComposePath;
import sneps.ConversePath;
import sneps.CustomException;
import sneps.FUnitPath;
import sneps.IrreflexiveRestrictPath;
import sneps.KPlusPath;
import sneps.KStarPath;
import sneps.Network;
import sneps.OrPath;
import sneps.Path;
import sneps.Relation;
import sneps.RelativeComplementPath;
import snepsui.Interface.SNePSInterface;

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

/**
 * @author Alia Taher
 */
public class cmdPath extends javax.swing.JPanel {
	private JLabel pathLabel;
	private JLabel relationLabel;
	private JComboBox pathComboBox;
	private JButton removeButton;
	private JTextField pathTextField;
	private JButton addButton;
	private JButton doneButton;
	private JButton pathButton;
	private JButton infoButton;
	private Network network;
	private JTextField completePathTextField;
	private SNePSInterface frame;
	private JFrame windowFrame;
	private LinkedList<Path> paths;
	private Path resultPath;
	
	@Action
    public void add() {}
	
	@Action
    public void remove() {}
	
	@Action
    public void info() {}
	
	@Action
    public void path() {}
	
	private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }
	
	public cmdPath(Network network, SNePSInterface frame, JFrame windowFrame) {
		super();
		paths = new LinkedList<Path>();
		this.windowFrame = windowFrame;
		this.frame = frame;
		this.network = network;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(477, 86, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.setFocusPainted(false);
				addButton.setBorderPainted(false);
				addButton.setContentAreaFilled(false);
				addButton.setMargin(new Insets(0,0,0,0));
				addButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						addButtonMouseClicked(evt);
					}
				});
			}
			{
				doneButton = new JButton();
				this.add(doneButton);
				doneButton.setBounds(314, 185, 77, 29);
				doneButton.setName("doneButton");
				doneButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						doneButtonMouseClicked(evt);
					}
				});
				doneButton.setVisible(false);
			}
			{
				relationLabel = new JLabel();
				this.add(relationLabel);
				relationLabel.setBounds(98, 12, 70, 15);
				relationLabel.setName("relationLabel");
			}
			{
				pathLabel = new JLabel();
				this.add(pathLabel);
				pathLabel.setBounds(212, 60, 66, 15);
				pathLabel.setName("pathLabel");
			}
			{
				infoButton = new JButton();
				this.add(infoButton);
				infoButton.setBounds(668, 196, 16, 18);
				infoButton.setAction(getAppActionMap().get("info"));
				infoButton.setFocusable(false);
				infoButton.setFocusPainted(false);
				infoButton.setBorderPainted(false);
				infoButton.setContentAreaFilled(false);
				infoButton.setMargin(new Insets(0,0,0,0));
				infoButton.setToolTipText("info");
			}
			{
				ComboBoxModel pathComboBoxModel = 
					new DefaultComboBoxModel(
							new String[] {"converse", "compose", "kstar", "kplus", "or", "and", "not",
									"relative-complement", "irreflexive-restrict", "exception", "domain-restrict", 
									"range-restrict", "unitpath", "unitpath-", "!"});
				pathComboBox = new JComboBox();
				this.add(pathComboBox);
				pathComboBox.setModel(pathComboBoxModel);
				pathComboBox.setBounds(212, 81, 115, 22);
				pathComboBox.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						pathComboBoxActionPerformed(e);
					}
				});
			}
			{
				pathButton = new JButton();
				this.add(pathButton);
				pathButton.setBounds(498, 86, 16, 18);
				pathButton.setName("pathButton");
				pathButton.setAction(getAppActionMap().get("path"));
				pathButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						pathButtonActionPerformed(evt);
					}
				});
			}
			{
				pathTextField = new JTextField();
				this.add(pathTextField);
				pathTextField.setBounds(333, 82, 138, 22);
			}
			{
				doneButton = new JButton();
				this.add(doneButton);
				doneButton.setBounds(314, 185, 77, 29);
				doneButton.setName("doneButton");
				doneButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						doneButtonMouseClicked(evt);
					}
				});
			}
			{
				completePathTextField = new JTextField();
				this.add(completePathTextField);
				completePathTextField.setBounds(212, 110, 259, 22);
			}
			{
				removeButton = new JButton();
				this.add(removeButton);
				removeButton.setAction(getAppActionMap().get("remove"));
				removeButton.setBounds(477, 113, 16, 18);
				removeButton.setFocusPainted(false);
				removeButton.setBorderPainted(false);
				removeButton.setContentAreaFilled(false);
				removeButton.setMargin(new Insets(0,0,0,0));
				removeButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						removeButtonMouseClicked(evt);
					}
				});
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {}
	}

	private void pathButtonActionPerformed(ActionEvent evt) {
		JFrame popupFrame = new JFrame("Path");
		popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final cmdPath pathPanel = new cmdPath(network, frame, popupFrame);
		popupFrame.getContentPane().add(pathPanel);
		popupFrame.pack();
		popupFrame.setVisible(true);
		Point point = frame.getsNePSULPanel1().getMenuDrivenCommands().cascadePosition();
		popupFrame.setLocation(point);
		doneButton.setEnabled(false);
		popupFrame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("Path Type: " + pathPanel.getResultPath().getClass().getSimpleName());
				Path path = pathPanel.getResultPath();
				paths.add(path);
				
				String currentPath = frame.getsNePSULPanel1().getMenuDrivenCommands().createPath(path);
				
				System.out.println("Current Path: " + currentPath);
				
				if(pathTextField.getText().isEmpty()) {
					pathTextField.setText(currentPath);
				} else {
					String previousPath = pathTextField.getText();
					pathTextField.setText(previousPath + ", " + currentPath);
				}
				
				frame.getsNePSULPanel1().getMenuDrivenCommands().cascadeBack();
				doneButton.setEnabled(true);
			}
		});
	}
	
	private void addButtonMouseClicked(MouseEvent evt) {
		try{
			if(pathComboBox.getSelectedItem().equals("unitpath") || pathComboBox.getSelectedItem().equals("unitpath-")) {
				JOptionPane.showMessageDialog(this, 
						"Choose a path type",
						"Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				//Add Path
				String pathType = pathComboBox.getSelectedItem().toString();
				LinkedList<Path> currentPath = paths;
				
				if (pathType.equals("converse")) {
					resultPath = new ConversePath(currentPath.getFirst());
				} else if (pathType.equals("compose")) {
					resultPath = new ComposePath(currentPath);
				} else if (pathType.equals("kstar")) {
					resultPath = new KStarPath(currentPath.getFirst());
				} else if (pathType.equals("kplus")) {
					resultPath = new KPlusPath(currentPath.getFirst());
				} else if (pathType.equals("or")) {
					resultPath = new OrPath(currentPath);
				} else if (pathType.equals("and")) {
					resultPath = new AndPath(currentPath);
				} else if (pathType.equals("relative-complement")) {
					resultPath = new RelativeComplementPath(currentPath.get(0), currentPath.get(1));
				} else if (pathType.equals("irreflexive-restrict")) {
					resultPath = new IrreflexiveRestrictPath(currentPath.getFirst());;
				} else if (pathType.equals("domain-restrict")) {
					//Path path = new DomainRestrictPath(q, node, p);
					//network.definePath(relation, path);
				} else if (pathType.equals("range-restrict")) {
					//Path path = new RangeRestrictPath(p, q, node);
					//network.definePath(relation, path);
				}
				
				//Add the path to the List
				String completePath = frame.getsNePSULPanel1().getMenuDrivenCommands().createPath(resultPath);
				completePathTextField.setText(completePath);
				
				//Reset TextField
				pathTextField.setText("");
			}
		} catch (Exception e) {}
	}
	
	private void doneButtonMouseClicked(MouseEvent evt) {
		windowFrame.dispose();
	}
	
	private void pathComboBoxActionPerformed(ActionEvent evt) {
		if(pathComboBox.getSelectedItem().toString().equals("unitpath") || 
				pathComboBox.getSelectedItem().toString().equals("unitpath-")) {
			
			String relation = "";
			String relationStr = "";
			int relationCounter = 0;
			Icon icon = new ImageIcon();
			
			Hashtable<String, Relation> relations = network.getRelations();
			Object [] relationPossibilities = new Object[relations.size()]; 
			Set<String> relationSet = relations.keySet();

		    Iterator<String> relationItr = relationSet.iterator();
		    while (relationItr.hasNext()) {
		    	relationStr = relationItr.next();
		    	relationPossibilities[relationCounter] = relations.get(relationStr).getName();
		    	relationCounter++;
		    }
			
			relation = (String) JOptionPane.showInputDialog(
						getRootPane(),
						"Choose the Node you want to create:",
						"Create a Node",
						JOptionPane.OK_OPTION,
						icon,
						relationPossibilities,
						relationPossibilities[0]);
			
			if (pathComboBox.getSelectedItem().toString().equals("unitpath")) {
				try {
					Path path = new FUnitPath(network.getRelation(relation).getName());
					paths.add(path);
					
					if(pathTextField.getText().isEmpty()) {
						pathTextField.setText(relation);
					} else {
						String previousPath = pathTextField.getText();
						pathTextField.setText(previousPath + ", " + relation);
					}
				} catch (CustomException e) {}
			} else if (pathComboBox.getSelectedItem().toString().equals("unitpath-")) {
				try {
					Path path = new BUnitPath(network.getRelation(relation).getName());
					paths.add(path);
					
					if(pathTextField.getText().isEmpty()) {
						pathTextField.setText(relation + "-");
					} else {
						String previousPath = pathTextField.getText();
						pathTextField.setText(previousPath + ", " + relation + "-");
					}
				} catch (CustomException e) {}
			} 
		} else if (pathComboBox.getSelectedItem().toString().equals("!")) {
			Path path = new BangPath();
			paths.add(path);
			
			if(pathTextField.getText().isEmpty()) {
				pathTextField.setText("!");
			} else {
				String previousPath = pathTextField.getText();
				pathTextField.setText(previousPath + ", " + "!");
			}
		} else {
			return;
		}
	}
	
	private void removeButtonMouseClicked(MouseEvent evt) {
		completePathTextField.setText("");
		paths = new LinkedList<Path>();
	}
	
	public Path getResultPath() {
		return resultPath;
	}
}
