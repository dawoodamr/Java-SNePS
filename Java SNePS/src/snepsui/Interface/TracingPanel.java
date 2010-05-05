package snepsui.Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

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
public class TracingPanel extends javax.swing.JPanel {
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;

	public TracingPanel() {
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
			redirectSystemStreams();
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateTextArea(final String text) {  
		  SwingUtilities.invokeLater(new Runnable() {  
		    public void run() {  
		    	jTextArea1.append(text);  
		    }  
		  });  
		}
	
	private void redirectSystemStreams() {  
		  OutputStream out = new OutputStream() {  
		    @Override  
		    public void write(int b) throws IOException {  
		      updateTextArea(String.valueOf((char) b));  
		    }  
		  
		    @Override  
		    public void write(byte[] b, int off, int len) throws IOException {  
		      updateTextArea(new String(b, off, len));  
		    }  
		  
		    @Override  
		    public void write(byte[] b) throws IOException {  
		      write(b, 0, b.length);  
		    }  
		  };  
		  
		  System.setOut(new PrintStream(out, true));  
		  System.setErr(new PrintStream(out, true));  
		}
	
	public void writeToTextArea(String text) {
		jTextArea1.setSelectedTextColor(Color.BLACK);
		jTextArea1.append(text);
		
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			int i = 0 ;
			@Override
			public void run() {
				getText(i);
				i++;
			}
		};
		timer.schedule(timerTask, 60000, Long.MAX_VALUE);
		
		this.validate();
		this.repaint();
	}
	
	private void getText(int i) {
		jTextArea1.append("Counter" + i + "\n");
		System.out.println("Counter" + i);
		this.validate();
		this.repaint();
	}
}
