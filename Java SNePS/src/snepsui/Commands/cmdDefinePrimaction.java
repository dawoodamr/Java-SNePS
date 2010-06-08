package snepsui.Commands;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;

import sneps.Network;
import sneps.Relation;
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
@SuppressWarnings({ "unused", "unchecked" })
public class cmdDefinePrimaction extends javax.swing.JPanel {
	private JLabel definePrimactionLabel;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JTextArea formTextArea;
	private JTextField classNameTextField;
	private JTextField actionTextField;
	private JComboBox relationComboBox;
	private JList relationList;
	private JLabel formLabel;
	private JLabel relationLabel;
	private JLabel actionLabel;
	private JButton infoButton;
	private JButton doneButton;
	private JButton addButton;
	private DefaultListModel relationModel;
	private Network network;
	private SNePSInterface frame;
	private static File targetDir;
	private static String className;

	@Action
    public void add() {
    	
    }
	
	@Action
    public void info() {
    	
    }
	
	private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }
	
	public cmdDefinePrimaction(Network network, SNePSInterface frame) {
		super();
		this.frame = frame;
		this.network = network;
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(690, 225));
			this.setLayout(null);
			{
				definePrimactionLabel = new JLabel();
				this.add(definePrimactionLabel);
				definePrimactionLabel.setName("definePrimactionLabel");
				definePrimactionLabel.setBounds(18, 40, 127, 15);
			}
			{
				actionLabel = new JLabel();
				this.add(actionLabel);
				actionLabel.setBounds(145, 16, 43, 15);
				actionLabel.setName("actionLabel");
			}
			{
				relationLabel = new JLabel();
				this.add(relationLabel);
				relationLabel.setBounds(309, 16, 75, 15);
				relationLabel.setName("relationLabel");
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1);
				jScrollPane1.setBounds(309, 65, 159, 101);
				{
					relationModel = new DefaultListModel();
					relationList = new JList();
					jScrollPane1.setViewportView(relationList);
					relationList.setModel(relationModel);
					relationList.setBounds(46, 132, 131, 98);
				}
			}
			{
				formLabel = new JLabel();
				this.add(formLabel);
				formLabel.setBounds(501, 16, 43, 15);
				formLabel.setName("formLabel");
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
				doneButton = new JButton();
				this.add(doneButton);
				doneButton.setBounds(314, 185, 77, 29);
				doneButton.setName("doneButton");
				doneButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						doneButtonActionPerformed(evt);
					}
				});
			}
			{
				addButton = new JButton();
				this.add(addButton);
				addButton.setBounds(474, 39, 16, 18);
				addButton.setAction(getAppActionMap().get("add"));
				addButton.setFocusable(false);
				addButton.setFocusPainted(false);
				addButton.setBorderPainted(false);
				addButton.setContentAreaFilled(false);
				addButton.setMargin(new Insets(0,0,0,0));
				addButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						relationModel.addElement(relationComboBox.getSelectedItem().toString());
						relationComboBox.setSelectedIndex(0);
						validate();
					}
				});
			}
			{	
				DefaultComboBoxModel relationsComboBoxModel = new DefaultComboBoxModel();
				String str = "";
				Hashtable<String, Relation> relations = network.getRelations();
				Set<String> set = relations.keySet();

			    Iterator<String> itr = set.iterator();
			    while (itr.hasNext()) {
			      str = itr.next();
			      relationsComboBoxModel.addElement(relations.get(str).getName()) ;
			    }
				relationComboBox = new JComboBox();
				this.add(relationComboBox);
				relationComboBox.setModel(relationsComboBoxModel);
				relationComboBox.setBounds(309, 36, 159, 22);
			}
			{
				actionTextField = new JTextField();
				this.add(actionTextField);
				actionTextField.setBounds(145, 37, 137, 22);
			}
			{
				classNameTextField = new JTextField();
				this.add(classNameTextField);
				classNameTextField.setBounds(501, 37, 177, 22);
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2);
				jScrollPane2.setBounds(501, 65, 177, 101);
				{
					formTextArea = new JTextArea();
					formTextArea.setTabSize(3);
					jScrollPane2.setViewportView(formTextArea);
				}
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {}
	}
	
	static void writeTheClassFile(String theCode) {
	    try {
	      targetDir = new File(System.getProperty("user.dir")
	               + File.separator + "src" + File.separator + "snactor" + File.separator);
	      
	      if (!targetDir.exists()) {
	    	  targetDir.mkdir();
	      }
	      
	      DataOutputStream dataOut = new DataOutputStream(
	             new FileOutputStream("src" + File.separator + "snactor" + File.separator +
	                                        className+".java"));
	                                        
	      System.out.println("Writing the class file.");
	      dataOut.writeBytes(theCode);
	      
	      dataOut.close();

	    } catch(Exception e) {}  
	}

	static Class reloadTheClass(String reloadableClass,File dir)
	{
		URL[] theUrl = null;
		
		try {
			URI uri = dir.toURI();                   
			URL url = uri.toURL();
			theUrl = new URL[]{url};
		} catch(Exception e){}

		Class theClass = null;

		try {
			ClassLoader classLoader = new URLClassLoader(theUrl);
			theClass = classLoader.loadClass(reloadableClass);
		} catch (Exception e) {}
		return theClass; 
	}

	private static boolean compile(String file) throws IOException
	{
		System.out.println("Compiling " + file);
		Process p = Runtime.getRuntime().exec("javac " + file);

		Thread myTimer = new Thread(
               new Timer(Thread.currentThread(),5000));
		myTimer.start();

		System.out.println("Waiting for completion");

		try {
			p.waitFor();
		  
			if(myTimer.isAlive()){
				myTimer.interrupt();
			}
		} catch(InterruptedException e){
			System.out.println("Compilation timeout error.");
			p.destroy();
			return false;
		}

		return p.exitValue() == 0;
	}
	
	private void doneButtonActionPerformed(ActionEvent evt) {
		className = classNameTextField.getText();
		writeTheClassFile(formTextArea.getText());
        
        try {
        	boolean compileStatus = compile(
            "src" + File.separator + "snactor" + File.separator + className+".java");
   
        	if(compileStatus){
        		System.out.println("Compile complete");

        		Class loadedClass = 
                             reloadTheClass(className,
                                             targetDir);
        
        		Object obj = loadedClass.newInstance();
           
        		Method methodObj =  loadedClass.getDeclaredMethod("theMethod", 
        				new Class[]{String.class,double.class});
           
         
           
				String returnVal = (String)methodObj.invoke( obj,
						new Object[]{"Hello",new Double(10.1)});
			} else {
				System.out.println("Probable compiler error.");
			}
       
       } catch (Exception ex) {}
   }
}

class Timer implements Runnable{
	Thread theCompilingThread;
	int delay;

	Timer(Thread theCompilingThread, int delay) { 
		this.theCompilingThread = theCompilingThread;
		this.delay = delay;
	}
	
	@SuppressWarnings("static-access")
	public void run(){
		try{
			Thread.currentThread().sleep(delay);
		}catch(InterruptedException e){
			return;
		}

		theCompilingThread.interrupt();
	}
}




