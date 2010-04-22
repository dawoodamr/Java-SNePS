package snactor;
import java.net.*;
import java.io.*;
import java.lang.reflect.*;
import java.awt.event.*;
import javax.swing.*;

public class AttachTrial2 {
  
  static File targetDir;
  String mycode;
  JTextArea code;
  
  public static void main(String[] args)
  {
    new AttachTrial2();
  }
  
    AttachTrial2(){
 
    JFrame frame = new JFrame("Class Editor");
    frame.setBounds(300,50,700,700);
	frame.setLayout(null);
	frame.setResizable(false);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	
	code = new JTextArea ("Enter Code");
	frame.getContentPane().add(code);
	code.setBounds(100,150,300,300); 
	code.setVisible(true);
  
   JButton button = new JButton("Recompile and Reload");
   frame.getContentPane().add(button);
    button.addActionListener(
      new ActionListener(){
        @SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e){
          //System.out.println(mycode);

     //      System.out.println(code.getText());
           
   		   AttachTrial2.writeTheClassFile(code.getText());
           
           try{
            boolean compileStatus = compile(
               "src" + File.separator + "snactor" + File.separator + "Reload01a.java");
      
            if(compileStatus){
              System.out.println("Compile complete");
 
              Class loadedClass = 
                                reloadTheClass("Reload01a",
                                                targetDir);
           //  System.out.println("Class Loaded");
              Object obj = loadedClass.newInstance();
              
              Method methodObj =  loadedClass.getDeclaredMethod("theMethod", new Class[]{String.class,double.class});
              
            
              @SuppressWarnings("unused")
			String returnVal = (String)methodObj.invoke(
                   obj,
                   new Object[]{"Hello",new Double(10.1)});
          //    System.out.println(returnVal);
            }
            else{
              System.out.println(
                               "Probable compiler error.");
            }
          
          }catch(Exception ex){
            ex.printStackTrace();
          }
   }});
    
    button.setSize(250,100);
    button.setVisible(true); 
  }
  
 static void writeTheClassFile(String theCode){
    try{
      targetDir = new File(System.getProperty("user.dir")
               + File.separator + "src" + File.separator + "snactor" + File.separator);
      if(!targetDir.exists()){
        targetDir.mkdir();
      }
      DataOutputStream dataOut = new DataOutputStream(
             new FileOutputStream("src" + File.separator + "snactor" + File.separator +
                                        "Reload01a.java"));
                                        
      System.out.println("Writing the class file.");
      dataOut.writeBytes(theCode);
      
      dataOut.close();

    }catch(Exception e){
      e.printStackTrace();
    }//end catch
  }
  
  @SuppressWarnings("unchecked")
static Class reloadTheClass(String reloadableClass,File dir)
    	{
         URL[] theUrl = null;
    try{
    	 URI uri = dir.toURI();                   
      URL url = uri.toURL();
      theUrl = new URL[]{url};
    }catch(Exception e){
      e.printStackTrace();
    }
    
  Class theClass = null;
    
    try {

      ClassLoader classLoader = new URLClassLoader(theUrl);
      theClass = classLoader.loadClass(reloadableClass);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
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
    
    try
    {
      p.waitFor();
      
      if(myTimer.isAlive()){
       
        myTimer.interrupt();
      }
    }
    catch(InterruptedException e){

      System.out.println("Compilation timeout error.");
      p.destroy();
      return false;
    }
    
    return p.exitValue() == 0;
  }
  
}


class Timer implements Runnable{
  Thread theCompilingThread;
  int delay;
  
  Timer(Thread theCompilingThread, int delay){ 
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
