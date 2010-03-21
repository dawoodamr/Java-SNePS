package SNePS.SNePSUI.Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import SNePS.NetworkManagementSystem.Network;



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
 * 
 */
public class SNePSInterface extends SingleFrameApplication {
    private JMenuBar menuBar;
    private SNeBR sNeBR1;
    private JMenu jMenu1;
    private SNePSULPanel sNePSULPanel1;
    private JMenuItem deduceMenuItem;
    private JMenu jMenu20;
    private JMenuItem jMenuItem86;
    private JMenu jMenu24;
    private JMenu jMenu23;
    private JMenuItem actEffectMenuItem;
    private JMenuItem actPreconditionMenuItem;
    private JMenu jMenu22;
    private JMenuItem achieveMenuItem;
    private JMenu jMenu21;
    private JMenuItem planActMenuItem;
    private JMenuItem attachPrimActionMenuItem;
    private JMenuItem jMenuItem80;
    private JMenuItem jMenuItem79;
    private JMenuItem jMenuItem78;
    private JMenuItem jMenuItem77;
    private JMenuItem jMenuItem76;
    private JMenuItem doOneMenuItem;
    private JMenuItem doAllMenuItem;
    private JMenu jMenu19;
    private JMenuItem disbelieveMenuItem;
    private JMenuItem believeMenuItem;
    private JMenu jMenu18;
    private JMenuItem definePrimactionMenuItem;
    private JMenu jMenu17;
    private JMenuItem ifDoMenuItem;
    private JMenuItem whenDoMenuItem;
    private JMenuItem wheneverDoMenuItem;
    private JMenu jMenu16;
    private JMenuItem multiPrintRegsMenuItem;
    private JMenuItem uninTraceMenuItem;
    private JMenuItem inTraceMenuItem;
    private JMenuItem unevTraceMenuItem;
    private JMenuItem evTraceMenuItem;
    private JMenu jMenu15;
    private JMenuItem numericalQuantifierMenuItem;
    private JMenuItem skolemFunctionMenuItem;
    private JMenuItem universalQuantifierMenuItem;
    private JMenu jMenu14;
    private JMenuItem threshMenuItem;
    private JMenuItem andOrMenuItem;
    private JMenuItem numericalEntailmentMenuItem;
    private JMenuItem orEntailmentMenuItem;
    private JMenuItem andEntailmentMenuItem;
    private JMenu jMenu13;
    private JMenuItem deduceWHNOTMenuItem;
    private JMenuItem deduceWHMenuItem;
    private JMenuItem deduceFalseMenuItem;
    private JMenuItem deduceTrueMenuItem;
    private JMenuItem findPatternMenuItem;
    private JMenuItem findVariableMenuItem;
    private JMenuItem findBaseMenuItem;
    private JMenuItem findConstantMenuItem;
    private JMenuItem findAssertMenuItem;
    private JMenuItem findMenuItem;
    private JMenu jMenu12;
    private JMenuItem fullDescribeMenuItem;
    private JMenuItem describeMenuItem;
    private JMenuItem dumpMenuItem;
    private JMenuItem listNodesMenuItem;
    private JMenuItem greaterThanMenuItem;
    private JMenuItem underscoreMenuItem;
    private JMenuItem equalMenuItem;
    private JMenuItem minusMenuItem;
    private JMenuItem plusMenuItem;
    private JMenuItem jMenuItem34;
    private JMenuItem jMenuItem33;
    private JMenuItem asteriskMenuItem;
    private JMenu jMenu11;
    private JMenuItem listContextNamesMenuItem;
    private JMenuItem listHypothesesMenuItem;
    private JMenuItem describeContextMenuItem;
    private JMenuItem removeFromContextMenuItem;
    private JMenuItem addToContextMenuItem;
    private JMenuItem setDefaultContextMenuItem;
    private JMenuItem setContextMenuItem;
    private JMenuItem undefinePathMenuItem;
    private JMenu jMenu9;
    private JMenuItem definePathMenuItem;
    private JMenu jMenu8;
    private JCheckBoxMenuItem jCheckBoxMenuItem3;
    private JMenuItem resetnetMenuItem;
    private JMenuItem silentEraseMenuItem;
    private JMenuItem eraseMenuItem;
    private JCheckBoxMenuItem jCheckBoxMenuItem2;
    private JCheckBoxMenuItem jCheckBoxMenuItem1;
    private JMenu jMenu7;
    private JMenuItem activateMenuItem;
    private JMenuItem addMenuItem;
    private JMenuItem buildMenuItem;
    private JMenuItem assertMenuItem;
    private JMenu jMenu6;
    private JMenuItem undefineMenuItem;
    private JMenuItem defineMenuItem;
    private JMenu jMenu5;
    private JMenu jMenu4;
    private JMenuItem clearInferAllMenuItem;
    private JMenuItem clearInferMenuItem;
    private JMenu jMenu3;
    private JMenu jMenu2;
    private OutputPanel outputPanel1;
    private JLabel jLabel1;
    private TracingPanel tracingPanel1;
    private NodesTreePanel nodesTreePanel1;
    private JPanel topPanel;
    private JMenuItem jMenuItem7;
    private JMenuItem jMenuItem6;
    private JMenuItem jMenuItem5;
    private JMenuItem jMenuItem4;
    private JMenu editMenu;
    private JMenuItem jMenuItem3;
    private JMenuItem jMenuItem2;
    private JMenuItem jMenuItem1;
    private JMenu fileMenu;
    private JSeparator jSeparator;
    private JButton saveButton;
    private JButton openButton;
    private JButton newButton;
    private JButton assertButton;
    private JButton findButton;
    private JButton settingsButtons;
    private JButton defineButton;
    private JButton resetnetButton;
    private JButton showNetworkButton;
    private JToolBar toolBar;
    private JPanel toolBarPanel;
    private JPanel contentPanel;

    @Action
    public void open() {
    }

    @Action
    public void save() {
    }

    @Action
    public void newFile() {
    }
    
    @Action
    public void settings() {
    }
    
    @Action
    public void assertCMD() {
    }
    
    @Action
    public void findCMD() {
    }
    
    @Action
    public void defineCMD() {
    }
    
    @Action
    public void resetnet() {
    }
    
    @Action
    public void showNetwork() {
    }

    private ActionMap getAppActionMap() {
        return Application.getInstance().getContext().getActionMap(this);
    }

    @Override
    protected void startup() {
        {
            topPanel = new JPanel();
            BorderLayout panelLayout = new BorderLayout();
            topPanel.setLayout(panelLayout);
            topPanel.setPreferredSize(new java.awt.Dimension(1250,700));
            {
                contentPanel = new JPanel();
                contentPanel.setLayout(null);
                topPanel.add(contentPanel, BorderLayout.CENTER);
                contentPanel.setPreferredSize(new java.awt.Dimension(1250, 600));
                {
                	nodesTreePanel1 = new NodesTreePanel();
                	contentPanel.add(nodesTreePanel1, "West");
                	nodesTreePanel1.setBounds(6, 0, 144, 606);
                	nodesTreePanel1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
                }
                {
                	tracingPanel1 = new TracingPanel();
                	contentPanel.add(tracingPanel1);
                	tracingPanel1.setBounds(544, 379, 410, 227);
                	tracingPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Tracing"));
                }
                {
                	jLabel1 = new JLabel();
                	contentPanel.add(jLabel1);
                	jLabel1.setBounds(156, 388, 78, 16);
                	jLabel1.setName("Tracing");
                }
                {
                	outputPanel1 = new OutputPanel();
                	contentPanel.add(outputPanel1);
                	outputPanel1.setBounds(162, 379, 376, 227);
                	outputPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Output"));
                }
                {
                	sNePSULPanel1 = new SNePSULPanel();
                	contentPanel.add(sNePSULPanel1);
                	sNePSULPanel1.setBounds(156, 1, 815, 366);
                	sNePSULPanel1.jTabbedPane1.addMouseListener(new MouseAdapter() {
                		public void mouseClicked(MouseEvent evt) {
                			jTabbedPane1MouseClicked(evt);
                		}
                	});
                }
                {
                	sNeBR1 = new SNeBR();
                	contentPanel.add(sNeBR1);
                	sNeBR1.setBounds(989, 13, 254, 593);
                	sNeBR1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Belief Revision"));
                }
            }
            {
                toolBarPanel = new JPanel();
                topPanel.add(toolBarPanel, BorderLayout.NORTH);
                BorderLayout jPanel1Layout = new BorderLayout();
                toolBarPanel.setLayout(jPanel1Layout);
                {
                    toolBar = new JToolBar();
                    toolBarPanel.add(toolBar, BorderLayout.CENTER);
                    {
                        newButton = new JButton();
                        toolBar.add(newButton);
                        newButton.setAction(getAppActionMap().get("newFile"));
                        newButton.setFocusable(false);
                        newButton.addActionListener(new ActionListener() {
                        	public void actionPerformed(ActionEvent evt) {
                        		newButtonActionPerformed(evt);
                        	}
                        });
                    }
                    {
                        openButton = new JButton();
                        toolBar.add(openButton);
                        openButton.setAction(getAppActionMap().get("open"));
                        openButton.setFocusable(false);
                    }
                    {
                        saveButton = new JButton();
                        toolBar.add(saveButton);
                        saveButton.setAction(getAppActionMap().get("save"));
                        saveButton.setFocusable(false);
                    }
                    {
                    	assertButton = new JButton();
                    	toolBar.add(assertButton);
                    	assertButton.setAction(getAppActionMap().get("assertCMD"));
                    	assertButton.addMouseListener(new MouseAdapter() {
                    		public void mouseClicked(MouseEvent evt) {
                    			assertButtonMouseClicked(evt);
                    		}
                    	});
                    	saveButton.setFocusable(false);
                    }
                    {
                    	findButton = new JButton();
                    	toolBar.add(findButton);
                    	findButton.setAction(getAppActionMap().get("findCMD"));
                    	findButton.setFocusable(false);
                    	findButton.addMouseListener(new MouseAdapter() {
                    		public void mouseClicked(MouseEvent evt) {
                    			findButtonMouseClicked(evt);
                    		}
                    	});
                    }
                    {
                    	defineButton = new JButton();
                    	toolBar.add(defineButton);
                    	defineButton.setAction(getAppActionMap().get("defineCMD"));
                    	defineButton.setFocusable(false);
                    	defineButton.addMouseListener(new MouseAdapter() {
                    		public void mouseClicked(MouseEvent evt) {
                    			defineButtonMouseClicked(evt);
                    		}
                    	});
                    }
                    {
                    	resetnetButton = new JButton();
                    	toolBar.add(resetnetButton);
                    	resetnetButton.setAction(getAppActionMap().get("resetnet"));
                    	resetnetButton.setFocusable(false);
                    }
                    {
                    	showNetworkButton = new JButton();
                    	toolBar.add(showNetworkButton);
                    	showNetworkButton.setAction(getAppActionMap().get("showNetwork"));
                    	showNetworkButton.setFocusable(false);
                    	showNetworkButton.addMouseListener(new MouseAdapter() {
                    		public void mouseClicked(MouseEvent evt) {
                    			showNetworkButtonMouseClicked(evt);
                    		}
                    	});
                    }
                    {
                    	settingsButtons = new JButton();
                    	toolBar.add(settingsButtons);
                    	settingsButtons.setAction(getAppActionMap().get("settings"));
                    	settingsButtons.setFocusable(false);
                    	settingsButtons.addActionListener(new ActionListener() {
                    		public void actionPerformed(ActionEvent evt) {
                    			settingsButtonsActionPerformed(evt);
                    		}
                    	});
                    }
                }
                {
                    jSeparator = new JSeparator();
                    toolBarPanel.add(jSeparator, BorderLayout.SOUTH);
                }
            }
        }
        menuBar = new JMenuBar();
        {
            fileMenu = new JMenu();
            menuBar.add(fileMenu);
            fileMenu.setName("fileMenu");
        {
                jMenuItem1 = new JMenuItem();
                fileMenu.add(jMenuItem1);
                jMenuItem1.setAction(getAppActionMap().get("newFile"));
            }
            {
                jMenuItem2 = new JMenuItem();
                fileMenu.add(jMenuItem2);
                jMenuItem2.setAction(getAppActionMap().get("open"));
            }
            {
                jMenuItem3 = new JMenuItem();
                fileMenu.add(jMenuItem3);
                jMenuItem3.setAction(getAppActionMap().get("save"));
            }
        }
        {
            editMenu = new JMenu();
            menuBar.add(editMenu);
            editMenu.setName("editMenu");
        {
                jMenuItem4 = new JMenuItem();
                editMenu.add(jMenuItem4);
                jMenuItem4.setAction(getAppActionMap().get("copy"));
            }
            {
                jMenuItem5 = new JMenuItem();
                editMenu.add(jMenuItem5);
                jMenuItem5.setAction(getAppActionMap().get("cut"));
            }
            {
                jMenuItem6 = new JMenuItem();
                editMenu.add(jMenuItem6);
                jMenuItem6.setAction(getAppActionMap().get("paste"));
            }
            {
                jMenuItem7 = new JMenuItem();
                editMenu.add(jMenuItem7);
                jMenuItem7.setAction(getAppActionMap().get("delete"));
            }
        }
        {
        	jMenu2 = new JMenu();
        	menuBar.add(jMenu2);
        	jMenu2.setName("jMenu2");
        	{
        		jMenu3 = new JMenu();
        		jMenu2.add(jMenu3);
        		jMenu3.setName("jMenu3");
        		{
        			clearInferMenuItem = new JMenuItem();
        			jMenu3.add(clearInferMenuItem);
        			clearInferMenuItem.setName("clearInferMenuItem");
        		}
        		{
        			clearInferAllMenuItem = new JMenuItem();
        			jMenu3.add(clearInferAllMenuItem);
        			clearInferAllMenuItem.setName("clearInferAllMenuItem");
        		}
        		{
        			eraseMenuItem = new JMenuItem();
        			jMenu3.add(eraseMenuItem);
        			eraseMenuItem.setName("eraseMenuItem");
        			eraseMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					eraseMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			silentEraseMenuItem = new JMenuItem();
        			jMenu3.add(silentEraseMenuItem);
        			silentEraseMenuItem.setName("silentEraseMenuItem");
        			silentEraseMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					silentEraseMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			resetnetMenuItem = new JMenuItem();
        			jMenu3.add(resetnetMenuItem);
        			resetnetMenuItem.setName("resetnetMenuItem");
        		}
        	}
        	{
        		jMenu4 = new JMenu();
        		jMenu2.add(jMenu4);
        		jMenu4.setName("jMenu4");
        		{
        			dumpMenuItem = new JMenuItem();
        			jMenu4.add(dumpMenuItem);
        			dumpMenuItem.setName("dumpMenuItem");
        			dumpMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					dumpMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			describeMenuItem = new JMenuItem();
        			jMenu4.add(describeMenuItem);
        			describeMenuItem.setName("describeMenuItem");
        			describeMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					describeMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			fullDescribeMenuItem = new JMenuItem();
        			jMenu4.add(fullDescribeMenuItem);
        			fullDescribeMenuItem.setName("fullDescribeMenuItem");
        			fullDescribeMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					fullDescribeMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		jMenu5 = new JMenu();
        		jMenu2.add(jMenu5);
        		jMenu5.setName("jMenu5");
        		{
        			defineMenuItem = new JMenuItem();
        			jMenu5.add(defineMenuItem);
        			defineMenuItem.setName("defineMenuItem");
        			defineMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					defineMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			undefineMenuItem = new JMenuItem();
        			jMenu5.add(undefineMenuItem);
        			undefineMenuItem.setName("undefineMenuItem");
        			undefineMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					undefineMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		jMenu6 = new JMenu();
        		jMenu2.add(jMenu6);
        		jMenu6.setName("jMenu6");
        		{
        			assertMenuItem = new JMenuItem();
        			jMenu6.add(assertMenuItem);
        			assertMenuItem.setName("assertMenuItem");
        			assertMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					assertMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			buildMenuItem = new JMenuItem();
        			jMenu6.add(buildMenuItem);
        			buildMenuItem.setName("buildMenuItem");
        			buildMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					buildMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			addMenuItem = new JMenuItem();
        			jMenu6.add(addMenuItem);
        			addMenuItem.setName("addMenuItem");
        			addMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					addMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			activateMenuItem = new JMenuItem();
        			jMenu6.add(activateMenuItem);
        			activateMenuItem.setName("activateMenuItem");
        			activateMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					activateMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		jMenu8 = new JMenu();
        		jMenu2.add(jMenu8);
        		jMenu8.setName("jMenu8");
        		{
        			definePathMenuItem = new JMenuItem();
        			jMenu8.add(definePathMenuItem);
        			definePathMenuItem.setName("definePathMenuItem");
        			definePathMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					definePathMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			undefinePathMenuItem = new JMenuItem();
        			jMenu8.add(undefinePathMenuItem);
        			undefinePathMenuItem.setName("undefinePathMenuItem");
        			undefinePathMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					undefinePathMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		jMenu9 = new JMenu();
        		jMenu2.add(jMenu9);
        		jMenu9.setName("jMenu9");
        		{
        			setContextMenuItem = new JMenuItem();
        			jMenu9.add(setContextMenuItem);
        			setContextMenuItem.setName("setContextMenuItem");
        			setContextMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					setContextMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			setDefaultContextMenuItem = new JMenuItem();
        			jMenu9.add(setDefaultContextMenuItem);
        			setDefaultContextMenuItem.setName("setDefaultContextMenuItem");
        			setDefaultContextMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					setDefaultContextMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			addToContextMenuItem = new JMenuItem();
        			jMenu9.add(addToContextMenuItem);
        			addToContextMenuItem.setName("addToContextMenuItem");
        			addToContextMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					addToContextMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			removeFromContextMenuItem = new JMenuItem();
        			jMenu9.add(removeFromContextMenuItem);
        			removeFromContextMenuItem.setName("removeFromContextMenuItem");
        			removeFromContextMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					removeFromContextMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			describeContextMenuItem = new JMenuItem();
        			jMenu9.add(describeContextMenuItem);
        			describeContextMenuItem.setName("describeContextMenuItem");
        			describeContextMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					describeContextMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			listHypothesesMenuItem = new JMenuItem();
        			jMenu9.add(listHypothesesMenuItem);
        			listHypothesesMenuItem.setName("listHypothesesMenuItem");
        			listHypothesesMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					listHypothesesMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			listContextNamesMenuItem = new JMenuItem();
        			jMenu9.add(listContextNamesMenuItem);
        			listContextNamesMenuItem.setName("listContextNamesMenuItem");
        			listContextNamesMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					listContextNamesMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		jMenu11 = new JMenu();
        		jMenu2.add(jMenu11);
        		jMenu11.setName("jMenu11");
        		{
        			asteriskMenuItem = new JMenuItem();
        			jMenu11.add(asteriskMenuItem);
        			asteriskMenuItem.setName("asteriskMenuItem");
        		}
        		{
        			jMenuItem33 = new JMenuItem();
        			jMenu11.add(jMenuItem33);
        			jMenuItem33.setName("jMenuItem33");
        		}
        		{
        			jMenuItem34 = new JMenuItem();
        			jMenu11.add(jMenuItem34);
        			jMenuItem34.setName("jMenuItem34");
        		}
        		{
        			plusMenuItem = new JMenuItem();
        			jMenu11.add(plusMenuItem);
        			plusMenuItem.setName("plusMenuItem");
        		}
        		{
        			minusMenuItem = new JMenuItem();
        			jMenu11.add(minusMenuItem);
        			minusMenuItem.setName("minusMenuItem");
        		}
        		{
        			equalMenuItem = new JMenuItem();
        			jMenu11.add(equalMenuItem);
        			equalMenuItem.setName("equalMenuItem");
        		}
        		{
        			underscoreMenuItem = new JMenuItem();
        			jMenu11.add(underscoreMenuItem);
        			underscoreMenuItem.setName("underscoreMenuItem");
        		}
        		{
        			greaterThanMenuItem = new JMenuItem();
        			jMenu11.add(greaterThanMenuItem);
        			greaterThanMenuItem.setName("greaterThanMenuItem");
        		}
        		{
        			listNodesMenuItem = new JMenuItem();
        			jMenu11.add(listNodesMenuItem);
        			listNodesMenuItem.setName("listNodesMenuItem");
        			listNodesMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					listNodesMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		jMenu12 = new JMenu();
        		jMenu2.add(jMenu12);
        		jMenu12.setName("jMenu12");
        		{
        			findMenuItem = new JMenuItem();
        			jMenu12.add(findMenuItem);
        			findMenuItem.setName("findMenuItem");
        			findMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					findMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			findAssertMenuItem = new JMenuItem();
        			jMenu12.add(findAssertMenuItem);
        			findAssertMenuItem.setName("findAssertMenuItem");
        			findAssertMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					findAssertMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			findConstantMenuItem = new JMenuItem();
        			jMenu12.add(findConstantMenuItem);
        			findConstantMenuItem.setName("findConstantMenuItem");
        			findConstantMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					findConstantMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			findBaseMenuItem = new JMenuItem();
        			jMenu12.add(findBaseMenuItem);
        			findBaseMenuItem.setName("findBaseMenuItem");
        			findBaseMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					findBaseMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			findVariableMenuItem = new JMenuItem();
        			jMenu12.add(findVariableMenuItem);
        			findVariableMenuItem.setName("findVariableMenuItem");
        			findVariableMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					findVariableMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			findPatternMenuItem = new JMenuItem();
        			jMenu12.add(findPatternMenuItem);
        			findPatternMenuItem.setName("findPatternMenuItem");
        			findPatternMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					findPatternMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			deduceMenuItem = new JMenuItem();
        			jMenu12.add(deduceMenuItem);
        			deduceMenuItem.setName("deduceMenuItem");
        			deduceMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					deduceMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			deduceTrueMenuItem = new JMenuItem();
        			jMenu12.add(deduceTrueMenuItem);
        			deduceTrueMenuItem.setName("deduceTrueMenuItem");
        			deduceTrueMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					deduceTrueMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			deduceFalseMenuItem = new JMenuItem();
        			jMenu12.add(deduceFalseMenuItem);
        			deduceFalseMenuItem.setName("deduceFalseMenuItem");
        			deduceFalseMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					deduceFalseMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			deduceWHMenuItem = new JMenuItem();
        			jMenu12.add(deduceWHMenuItem);
        			deduceWHMenuItem.setName("deduceWHMenuItem");
        			deduceWHMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					deduceWHMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			deduceWHNOTMenuItem = new JMenuItem();
        			jMenu12.add(deduceWHNOTMenuItem);
        			deduceWHNOTMenuItem.setName("deduceWHNOTMenuItem");
        			deduceWHNOTMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					deduceWHNOTMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		jMenu13 = new JMenu();
        		jMenu2.add(jMenu13);
        		jMenu13.setName("jMenu13");
        		{
        			andEntailmentMenuItem = new JMenuItem();
        			jMenu13.add(andEntailmentMenuItem);
        			andEntailmentMenuItem.setName("andEntailmentMenuItem");
        			andEntailmentMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					andEntailmentMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			orEntailmentMenuItem = new JMenuItem();
        			jMenu13.add(orEntailmentMenuItem);
        			orEntailmentMenuItem.setName("orEntailmentMenuItem");
        			orEntailmentMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					orEntailmentMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			numericalEntailmentMenuItem = new JMenuItem();
        			jMenu13.add(numericalEntailmentMenuItem);
        			numericalEntailmentMenuItem.setName("numericalEntailmentMenuItem");
        			numericalEntailmentMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					numericalEntailmentMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			andOrMenuItem = new JMenuItem();
        			jMenu13.add(andOrMenuItem);
        			andOrMenuItem.setName("andOrMenuItem");
        			andOrMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					andOrMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			threshMenuItem = new JMenuItem();
        			jMenu13.add(threshMenuItem);
        			threshMenuItem.setName("threshMenuItem");
        			threshMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					threshMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		jMenu14 = new JMenu();
        		jMenu2.add(jMenu14);
        		jMenu14.setName("jMenu14");
        		{
        			universalQuantifierMenuItem = new JMenuItem();
        			jMenu14.add(universalQuantifierMenuItem);
        			universalQuantifierMenuItem.setName("universalQuantifierMenuItem");
        			universalQuantifierMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					universalQuantifierMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			skolemFunctionMenuItem = new JMenuItem();
        			jMenu14.add(skolemFunctionMenuItem);
        			skolemFunctionMenuItem.setName("skolemFunctionMenuItem");
        			skolemFunctionMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					skolemFunctionMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			numericalQuantifierMenuItem = new JMenuItem();
        			jMenu14.add(numericalQuantifierMenuItem);
        			numericalQuantifierMenuItem.setName("numericalQuantifierMenuItem");
        			numericalQuantifierMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					numericalQuantifierMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		jMenu15 = new JMenu();
        		jMenu2.add(jMenu15);
        		jMenu15.setName("jMenu15");
        		{
        			evTraceMenuItem = new JMenuItem();
        			jMenu15.add(evTraceMenuItem);
        			evTraceMenuItem.setName("evTraceMenuItem");
        			evTraceMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					evTraceMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			unevTraceMenuItem = new JMenuItem();
        			jMenu15.add(unevTraceMenuItem);
        			unevTraceMenuItem.setName("unevTraceMenuItem");
        			unevTraceMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					unevTraceMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			inTraceMenuItem = new JMenuItem();
        			jMenu15.add(inTraceMenuItem);
        			inTraceMenuItem.setName("inTraceMenuItem");
        			inTraceMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					inTraceMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			uninTraceMenuItem = new JMenuItem();
        			jMenu15.add(uninTraceMenuItem);
        			uninTraceMenuItem.setName("uninTraceMenuItem");
        			uninTraceMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					uninTraceMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			multiPrintRegsMenuItem = new JMenuItem();
        			jMenu15.add(multiPrintRegsMenuItem);
        			multiPrintRegsMenuItem.setName("multiPrintRegsMenuItem");
        			multiPrintRegsMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					multiPrintRegsMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		jMenu16 = new JMenu();
        		jMenu2.add(jMenu16);
        		jMenu16.setName("jMenu16");
        		{
        			wheneverDoMenuItem = new JMenuItem();
        			jMenu16.add(wheneverDoMenuItem);
        			wheneverDoMenuItem.setName("wheneverDoMenuItem");
        			wheneverDoMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					wheneverDoMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			whenDoMenuItem = new JMenuItem();
        			jMenu16.add(whenDoMenuItem);
        			whenDoMenuItem.setName("whenDoMenuItem");
        			whenDoMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					whenDoMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			ifDoMenuItem = new JMenuItem();
        			jMenu16.add(ifDoMenuItem);
        			ifDoMenuItem.setName("ifDoMenuItem");
        			ifDoMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					ifDoMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		jMenu17 = new JMenu();
        		jMenu2.add(jMenu17);
        		jMenu17.setName("jMenu17");
        		{
        			definePrimactionMenuItem = new JMenuItem();
        			jMenu17.add(definePrimactionMenuItem);
        			definePrimactionMenuItem.setName("definePrimactionMenuItem");
        			definePrimactionMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					definePrimactionMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			jMenu18 = new JMenu();
        			jMenu17.add(jMenu18);
        			jMenu18.setName("jMenu18");
        			{
        				believeMenuItem = new JMenuItem();
        				jMenu18.add(believeMenuItem);
        				believeMenuItem.setName("believeMenuItem");
        				believeMenuItem.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent evt) {
        						believeMenuItemActionPerformed(evt);
        					}
        				});
        			}
        			{
        				disbelieveMenuItem = new JMenuItem();
        				jMenu18.add(disbelieveMenuItem);
        				disbelieveMenuItem.setName("disbelieveMenuItem");
        				disbelieveMenuItem.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent evt) {
        						disbelieveMenuItemActionPerformed(evt);
        					}
        				});
        			}
        		}
        		{
        			jMenu19 = new JMenu();
        			jMenu17.add(jMenu19);
        			jMenu19.setName("jMenu19");
        			{
        				doAllMenuItem = new JMenuItem();
        				jMenu19.add(doAllMenuItem);
        				doAllMenuItem.setName("doAllMenuItem");
        				doAllMenuItem.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent evt) {
        						doAllMenuItemActionPerformed(evt);
        					}
        				});
        			}
        			{
        				doOneMenuItem = new JMenuItem();
        				jMenu19.add(doOneMenuItem);
        				doOneMenuItem.setName("doOneMenuItem");
        				doOneMenuItem.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent evt) {
        						doOneMenuItemActionPerformed(evt);
        					}
        				});
        			}
        			{
        				jMenuItem76 = new JMenuItem();
        				jMenu19.add(jMenuItem76);
        				jMenuItem76.setName("jMenuItem76");
        			}
        			{
        				jMenuItem77 = new JMenuItem();
        				jMenu19.add(jMenuItem77);
        				jMenuItem77.setName("jMenuItem77");
        			}
        			{
        				jMenuItem78 = new JMenuItem();
        				jMenu19.add(jMenuItem78);
        				jMenuItem78.setName("jMenuItem78");
        			}
        			{
        				jMenuItem79 = new JMenuItem();
        				jMenu19.add(jMenuItem79);
        				jMenuItem79.setName("jMenuItem79");
        			}
        			{
        				jMenuItem80 = new JMenuItem();
        				jMenu19.add(jMenuItem80);
        				jMenuItem80.setName("jMenuItem80");
        			}
        		}
        		{
        			attachPrimActionMenuItem = new JMenuItem();
        			jMenu17.add(attachPrimActionMenuItem);
        			attachPrimActionMenuItem.setName("attachPrimActionMenuItem");
        			attachPrimActionMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					attachPrimActionMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		jMenu20 = new JMenu();
        		jMenu2.add(jMenu20);
        		jMenu20.setName("jMenu20");
        		{
        			planActMenuItem = new JMenuItem();
        			jMenu20.add(planActMenuItem);
        			planActMenuItem.setName("planActMenuItem");
        			planActMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					planActMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		jMenu21 = new JMenu();
        		jMenu2.add(jMenu21);
        		jMenu21.setName("jMenu21");
        		{
        			achieveMenuItem = new JMenuItem();
        			jMenu21.add(achieveMenuItem);
        			achieveMenuItem.setName("achieveMenuItem");
        		}
        	}
        	{
        		jMenu22 = new JMenu();
        		jMenu2.add(jMenu22);
        		jMenu22.setName("jMenu22");
        		{
        			actPreconditionMenuItem = new JMenuItem();
        			jMenu22.add(actPreconditionMenuItem);
        			actPreconditionMenuItem.setName("actPreconditionMenuItem");
        		}
        		{
        			actEffectMenuItem = new JMenuItem();
        			jMenu22.add(actEffectMenuItem);
        			actEffectMenuItem.setName("actEffectMenuItem");
        		}
        	}
        	{
        		jMenu23 = new JMenu();
        		jMenu2.add(jMenu23);
        		jMenu23.setName("jMenu23");
        	}
        	{
        		jMenu24 = new JMenu();
        		jMenu2.add(jMenu24);
        		jMenu24.setName("jMenu24");
        		{
        			jMenuItem86 = new JMenuItem();
        			jMenu24.add(jMenuItem86);
        			jMenuItem86.setName("jMenuItem86");
        		}
        	}
        }
        {
        	jMenu1 = new JMenu();
        	menuBar.add(jMenu1);
        	jMenu1.setName("jMenu1");
        	{
        		jMenu7 = new JMenu();
        		jMenu1.add(jMenu7);
        		jMenu7.setName("jMenu7");
        		{
        			jCheckBoxMenuItem1 = new JCheckBoxMenuItem();
        			jMenu7.add(jCheckBoxMenuItem1);
        			jCheckBoxMenuItem1.setName("jCheckBoxMenuItem1");
        		}
        		{
        			jCheckBoxMenuItem2 = new JCheckBoxMenuItem();
        			jMenu7.add(jCheckBoxMenuItem2);
        			jCheckBoxMenuItem2.setName("jCheckBoxMenuItem2");
        		}
        	}
        	{
        		jCheckBoxMenuItem3 = new JCheckBoxMenuItem();
        		jMenu1.add(jCheckBoxMenuItem3);
        		jCheckBoxMenuItem3.setName("jCheckBoxMenuItem3");
        	}
        }
        getMainFrame().setJMenuBar(menuBar);
        show(topPanel);
        getMainFrame().pack();
        getMainFrame().validate();
    }
    
    private void assertButtonMouseClicked(MouseEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.assertMenuButton();
    }
    
    private void defineButtonMouseClicked(MouseEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.defineMenuButton();
    }
    
    private void showNetworkButtonMouseClicked(MouseEvent evt) {
    	//sNePSULPanel1.jTabbedPane1.setSelectedComponent()
    }
    
    private void findButtonMouseClicked(MouseEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.findMenuButton();
    }
    
    private void settingsButtonsActionPerformed(ActionEvent evt) {
    	
    }
    
    private void findMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.findMenuButton();
    }
    
    private void eraseMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.eraseMenuButton();
    }
    
    private void silentEraseMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.silentEraseMenuButton();
    }
    
    private void dumpMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.dumpMenuButton();
    }
    
    private void describeMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.describeMenuButton();
    }
    
    private void fullDescribeMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.fullDescribeMenuButton();
    }
    
    private void defineMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.defineMenuButton();
    }
    
    private void undefineMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.undefineMenuButton();
    }
    
    private void buildMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.buildMenuButton();
    }
    
    private void addMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.addMenuButton();
    }
    
    private void activateMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.activateMenuButton();
    }
    
    private void assertMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.assertMenuButton();
    }
    
    private void definePathMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.defineMenuButton();
    }
    
    private void undefinePathMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.undefinePathMenuButton();
    }
    
    private void setContextMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.setContextMenuButton();
    }
    
    private void setDefaultContextMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.setDefaultContextMenuButton();
    }
    
    private void addToContextMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.addToContextMenuButton();
    }
    
    private void removeFromContextMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.removeFromContextMenuButton();
    }
    
    private void describeContextMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.describeContextMenuButton();
    }
    
    private void listHypothesesMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.listHypothesesMenuButton();
    }
    
    private void listContextNamesMenuItemActionPerformed(ActionEvent evt) {
    	sNePSULPanel1.menuDrivenCommands.listContextNamesMenuButton();
    }
    
    private void listNodesMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("listNodesMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for listNodesMenuItem.actionPerformed
    }
    
    private void findAssertMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("findAssertMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for findAssertMenuItem.actionPerformed
    }
    
    private void findConstantMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("findConstantMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for findConstantMenuItem.actionPerformed
    }
    
    private void findBaseMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("findBaseMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for findBaseMenuItem.actionPerformed
    }
    
    private void findVariableMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("findVariableMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for findVariableMenuItem.actionPerformed
    }
    
    private void findPatternMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("findPatternMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for findPatternMenuItem.actionPerformed
    }
    
    private void deduceMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("deduceMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for deduceMenuItem.actionPerformed
    }
    
    private void deduceTrueMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("deduceTrueMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for deduceTrueMenuItem.actionPerformed
    }
    
    private void deduceFalseMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("deduceFalseMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for deduceFalseMenuItem.actionPerformed
    }
    
    private void deduceWHMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("deduceWHMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for deduceWHMenuItem.actionPerformed
    }
    
    private void deduceWHNOTMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("deduceWHNOTMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for deduceWHNOTMenuItem.actionPerformed
    }
    
    private void andEntailmentMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("andEntailmentMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for andEntailmentMenuItem.actionPerformed
    }
    
    private void orEntailmentMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("orEntailmentMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for orEntailmentMenuItem.actionPerformed
    }
    
    private void numericalEntailmentMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("numericalEntailmentMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for numericalEntailmentMenuItem.actionPerformed
    }
    
    private void andOrMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("andOrMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for andOrMenuItem.actionPerformed
    }
    
    private void threshMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("threshMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for threshMenuItem.actionPerformed
    }
    
    private void universalQuantifierMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("universalQuantifierMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for universalQuantifierMenuItem.actionPerformed
    }
    
    private void skolemFunctionMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("skolemFunctionMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for skolemFunctionMenuItem.actionPerformed
    }
    
    private void numericalQuantifierMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("numericalQuantifierMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for numericalQuantifierMenuItem.actionPerformed
    }
    
    private void evTraceMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("evTraceMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for evTraceMenuItem.actionPerformed
    }
    
    private void unevTraceMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("unevTraceMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for unevTraceMenuItem.actionPerformed
    }
    
    private void inTraceMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("inTraceMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for inTraceMenuItem.actionPerformed
    }
    
    private void uninTraceMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("uninTraceMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for uninTraceMenuItem.actionPerformed
    }
    
    private void multiPrintRegsMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("multiPrintRegsMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for multiPrintRegsMenuItem.actionPerformed
    }
    
    private void wheneverDoMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("wheneverDoMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for wheneverDoMenuItem.actionPerformed
    }
    
    private void whenDoMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("whenDoMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for whenDoMenuItem.actionPerformed
    }
    
    private void ifDoMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("ifDoMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for ifDoMenuItem.actionPerformed
    }
    
    private void definePrimactionMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("definePrimactionMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for definePrimactionMenuItem.actionPerformed
    }
    
    private void attachPrimActionMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("attachPrimActionMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for attachPrimActionMenuItem.actionPerformed
    }
    
    private void believeMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("believeMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for believeMenuItem.actionPerformed
    }
    
    private void disbelieveMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("disbelieveMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for disbelieveMenuItem.actionPerformed
    }
    
    private void doAllMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("doAllMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for doAllMenuItem.actionPerformed
    }
    
    private void doOneMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("doOneMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for doOneMenuItem.actionPerformed
    }
    
    private void planActMenuItemActionPerformed(ActionEvent evt) {
    	System.out.println("planActMenuItem.actionPerformed, event="+evt);
    	//TODO add your code for planActMenuItem.actionPerformed
    }
    
    private void newButtonActionPerformed(ActionEvent evt) {
    	Network network = new Network();
    	sNePSULPanel1.menuDrivenCommands.setNetwork(network);
    }
    
    private void jTabbedPane1MouseClicked(MouseEvent evt) {
    	if (sNePSULPanel1.jTabbedPane1.getSelectedIndex() == 1) {
    		outputPanel1.setVisible(false);
    		tracingPanel1.setVisible(false);
    	}
    }
    
    public static void main(String[] args) {
        launch(SNePSInterface.class, args);
    }
}