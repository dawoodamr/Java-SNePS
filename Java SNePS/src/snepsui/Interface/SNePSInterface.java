package snepsui.Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import sneps.CaseFrame;
import sneps.Network;
import sneps.Node;
import sneps.PatternNode;
import sneps.Relation;

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
@SuppressWarnings("unused")
public class SNePSInterface extends SingleFrameApplication {

    private JPanel topPanel;
    private JPanel toolBarPanel;
    private JPanel contentPanel;
    private SNeBR sNeBR1;
    private SNePSULPanel sNePSULPanel1;
	private TracingPanel tracingPanel1;
	private NodesTreePanel nodesTreePanel1;
	private OutputPanel outputPanel1;
	private JMenuBar menuBar;
    private JMenu jMenu1;
    private JMenuItem deduceMenuItem;
    private JMenu complexActsMenu;
    private JMenuItem actEffectMenuItem;
    private JMenuItem actPreconditionMenuItem;
    private JMenu preconditionsAndEffectsMenu;
    private JMenuItem achieveMenuItem;
    private JMenu goalsMenu;
    private JMenuItem planActMenuItem;
    private JMenuItem attachPrimActionMenuItem;
    private JMenuItem withsomeMenuItem;
    private JMenuItem withallMenuItem;
    private JMenuItem sniterateMenuItem;
    private JMenuItem snifMenuItem;
    private JMenuItem snsequenceMenuItem;
    private JMenuItem doOneMenuItem;
    private JMenuItem networkresetMenuItem;
    private JCheckBoxMenuItem keepsomeinforCheckBoxMenuItem;
    private JCheckBoxMenuItem relationresetCheckBoxMenuItem;
    private JMenu resetnetrelationMenu;
    private JMenuItem caseframesresteMenuItem;
    private JMenuItem relationsresetMenuItem;
    private JMenuItem nodesresetMenuItem;
    private JMenu resetmenuMenu;
    private JCheckBoxMenuItem tracingCheckBoxMenuItem;
	private JMenuItem undefineCaseframeMenuItem;
    private JMenuItem defineCaseframeMenuItem;
    private JMenu caseframesMenu;
    private JMenuItem performMenuItem;
    private JMenuItem doAllMenuItem;
    private JMenu controlActsMenu;
    private JMenuItem disbelieveMenuItem;
    private JMenuItem believeMenuItem;
    private JMenu mentalFunctionsMenu;
    private JMenuItem definePrimactionMenuItem;
    private JMenu premitiveActsMenu;
    private JMenuItem ifDoMenuItem;
    private JMenuItem whenDoMenuItem;
    private JMenuItem wheneverDoMenuItem;
    private JMenu actingMenu;
    private JMenuItem multiPrintRegsMenuItem;
    private JMenuItem uninTraceMenuItem;
    private JMenuItem inTraceMenuItem;
    private JMenuItem unevTraceMenuItem;
    private JMenuItem evTraceMenuItem;
    private JMenu tracingInferenceMenu;
    private JMenuItem numericalQuantifierMenuItem;
    private JMenuItem universalQuantifierMenuItem;
    private JMenu quantifiersMenu;
    private JMenuItem threshMenuItem;
    private JMenuItem andOrMenuItem;
    private JMenuItem numericalEntailmentMenuItem;
    private JMenuItem orEntailmentMenuItem;
    private JMenuItem andEntailmentMenuItem;
    private JMenu connectivesMenu;
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
    private JMenu retrievingInformationMenu;
    private JMenuItem fullDescribeMenuItem;
    private JMenuItem describeMenuItem;
    private JMenuItem dumpMenuItem;
    private JMenuItem listHypothesesMenuItem;
    private JMenuItem describeContextMenuItem;
    private JMenuItem removeFromContextMenuItem;
    private JMenuItem addToContextMenuItem;
    private JMenuItem setDefaultContextMenuItem;
    private JMenuItem setContextMenuItem;
    private JMenuItem undefinePathMenuItem;
    private JMenu contextsMenu;
    private JMenuItem definePathMenuItem;
    private JMenu pathbasedInferenceMenu;
    private JMenuItem silentEraseMenuItem;
    private JMenuItem eraseMenuItem;
    private JMenuItem activateMenuItem;
    private JMenuItem addMenuItem;
    private JMenuItem assertMenuItem;
    private JMenu buildingNetworksMenu;
    private JMenuItem undefineMenuItem;
    private JMenuItem defineMenuItem;
    private JMenu relationsMenu;
    private JMenu displayingInformationMenu;
    private JMenuItem clearInferAllMenuItem;
    private JMenuItem clearInferMenuItem;
    private JMenu deletingInformationMenu;
    private JMenu jMenu2;
    private JLabel jLabel1;
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
    private ResultNodes nodesResult;
    private ButtonGroup resetButtonGroup;
    private int previousTab = 0;
    private boolean viewNetworkClickCount;
    private Network network;

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
        	getMainFrame().setResizable(false);

            topPanel = new JPanel();
            BorderLayout panelLayout = new BorderLayout();
            topPanel.setLayout(panelLayout);
            topPanel.setPreferredSize(new java.awt.Dimension(1250,650));
            {
                contentPanel = new JPanel();
                contentPanel.setLayout(null);
                topPanel.add(contentPanel, BorderLayout.CENTER);
                contentPanel.setPreferredSize(new java.awt.Dimension(1250, 600));
                {
                	nodesTreePanel1 = new NodesTreePanel(this);
                	contentPanel.add(nodesTreePanel1, "West");
                	nodesTreePanel1.setBounds(6, 0, 144, 373);
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
                	outputPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Information"));
                }
                {
                	nodesResult = new ResultNodes(this);
                	contentPanel.add(nodesResult);
                	nodesResult.setBounds(6, 379, 144, 227);
                	nodesResult.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Resulting Node Set"));
                }
                {
                	sNePSULPanel1 = new SNePSULPanel(this);
                	contentPanel.add(sNePSULPanel1);
                	sNePSULPanel1.setBounds(156, 1, 815, 366);
                	sNePSULPanel1.getjTabbedPane1().addMouseListener(new MouseAdapter() {
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
                        		newButtonActionPerformed();
                        	}
                        });
                    }
                    {
                        openButton = new JButton();
                        toolBar.add(openButton);
                        openButton.setAction(getAppActionMap().get("open"));
                        openButton.setFocusable(false);
                        openButton.addMouseListener(new MouseAdapter() {
                        	public void mouseClicked(MouseEvent evt) {
                        		openButtonMouseClicked(evt);
                        	}
                        });
                    }
                    {
                        saveButton = new JButton();
                        toolBar.add(saveButton);
                        saveButton.setAction(getAppActionMap().get("save"));
                        saveButton.setFocusable(false);
                    	saveButton.addMouseListener(new MouseAdapter() {
                    		public void mouseClicked(MouseEvent evt) {
                    			saveButtonMouseClicked(evt);
                    		}
                    	});
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
                    	resetnetButton.addMouseListener(new MouseAdapter() {
                    		public void mouseClicked(MouseEvent evt) {
                    			resetnetButtonMouseClicked(evt);
                    		}
                    	});
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
                jMenuItem1.addMouseListener(new MouseAdapter() {
                	public void mouseClicked(MouseEvent evt) {
                		newButtonActionPerformed();
                	}
                });
            }
            {
                jMenuItem2 = new JMenuItem();
                fileMenu.add(jMenuItem2);
                jMenuItem2.setAction(getAppActionMap().get("open"));
                jMenuItem2.addMouseListener(new MouseAdapter() {
                	public void mouseClicked(MouseEvent evt) {
                		openButtonMouseClicked(evt);
                	}
                });
            }
            {
                jMenuItem3 = new JMenuItem();
                fileMenu.add(jMenuItem3);
                jMenuItem3.setAction(getAppActionMap().get("save"));
                jMenuItem3.addMouseListener(new MouseAdapter() {
                	public void mouseClicked(MouseEvent evt) {
                		saveButtonMouseClicked(evt);
                	}
                });
            }
        }
        {
        	jMenu2 = new JMenu();
        	menuBar.add(jMenu2);
        	jMenu2.setName("jMenu2");
        	{
        		relationsMenu = new JMenu();
        		jMenu2.add(relationsMenu);
        		relationsMenu.setName("relationsMenu");
        		{
        			defineMenuItem = new JMenuItem();
        			relationsMenu.add(defineMenuItem);
        			defineMenuItem.setName("defineMenuItem");
        			defineMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					defineMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			undefineMenuItem = new JMenuItem();
        			relationsMenu.add(undefineMenuItem);
        			undefineMenuItem.setName("undefineMenuItem");
        			undefineMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					undefineMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		caseframesMenu = new JMenu();
        		jMenu2.add(caseframesMenu);
        		caseframesMenu.setName("caseframesMenu");
        		{
        			defineCaseframeMenuItem = new JMenuItem();
        			caseframesMenu.add(defineCaseframeMenuItem);
        			defineCaseframeMenuItem.setName("defineCaseframeMenuItem");
        			defineCaseframeMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					defineCaseframeMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			undefineCaseframeMenuItem = new JMenuItem();
        			caseframesMenu.add(undefineCaseframeMenuItem);
        			undefineCaseframeMenuItem.setName("undefineCaseframeMenuItem");
        			undefineCaseframeMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					undefineCaseframeMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		pathbasedInferenceMenu = new JMenu();
        		jMenu2.add(pathbasedInferenceMenu);
        		pathbasedInferenceMenu.setName("pathbasedInferenceMenu");
        		{
        			definePathMenuItem = new JMenuItem();
        			pathbasedInferenceMenu.add(definePathMenuItem);
        			definePathMenuItem.setName("definePathMenuItem");
        			definePathMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					definePathMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			undefinePathMenuItem = new JMenuItem();
        			pathbasedInferenceMenu.add(undefinePathMenuItem);
        			undefinePathMenuItem.setName("undefinePathMenuItem");
        			undefinePathMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					undefinePathMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		contextsMenu = new JMenu();
        		jMenu2.add(contextsMenu);
        		contextsMenu.setName("contextsMenu");
        		{
        			setContextMenuItem = new JMenuItem();
        			contextsMenu.add(setContextMenuItem);
        			setContextMenuItem.setName("setContextMenuItem");
        			setContextMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					setContextMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			setDefaultContextMenuItem = new JMenuItem();
        			contextsMenu.add(setDefaultContextMenuItem);
        			setDefaultContextMenuItem.setName("setDefaultContextMenuItem");
        			setDefaultContextMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					setDefaultContextMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			addToContextMenuItem = new JMenuItem();
        			contextsMenu.add(addToContextMenuItem);
        			addToContextMenuItem.setName("addToContextMenuItem");
        			addToContextMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					addToContextMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			removeFromContextMenuItem = new JMenuItem();
        			contextsMenu.add(removeFromContextMenuItem);
        			removeFromContextMenuItem.setName("removeFromContextMenuItem");
        			removeFromContextMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					removeFromContextMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			describeContextMenuItem = new JMenuItem();
        			contextsMenu.add(describeContextMenuItem);
        			describeContextMenuItem.setName("describeContextMenuItem");
        			describeContextMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					describeContextMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			listHypothesesMenuItem = new JMenuItem();
        			contextsMenu.add(listHypothesesMenuItem);
        			listHypothesesMenuItem.setName("listHypothesesMenuItem");
        			listHypothesesMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					listHypothesesMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		buildingNetworksMenu = new JMenu();
        		jMenu2.add(buildingNetworksMenu);
        		buildingNetworksMenu.setName("buildingNetworksMenu");
        		{
        			assertMenuItem = new JMenuItem();
        			buildingNetworksMenu.add(assertMenuItem);
        			assertMenuItem.setName("assertMenuItem");
        			assertMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					assertMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			addMenuItem = new JMenuItem();
        			buildingNetworksMenu.add(addMenuItem);
        			addMenuItem.setName("addMenuItem");
        			addMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					addMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			activateMenuItem = new JMenuItem();
        			buildingNetworksMenu.add(activateMenuItem);
        			activateMenuItem.setName("activateMenuItem");
        			activateMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					activateMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		deletingInformationMenu = new JMenu();
        		jMenu2.add(deletingInformationMenu);
        		deletingInformationMenu.setName("deletingInformationMenu");
        		{
        			clearInferMenuItem = new JMenuItem();
        			deletingInformationMenu.add(clearInferMenuItem);
        			clearInferMenuItem.setName("clearInferMenuItem");
        		}
        		{
        			clearInferAllMenuItem = new JMenuItem();
        			deletingInformationMenu.add(clearInferAllMenuItem);
        			clearInferAllMenuItem.setName("clearInferAllMenuItem");
        		}
        		{
        			eraseMenuItem = new JMenuItem();
        			deletingInformationMenu.add(eraseMenuItem);
        			eraseMenuItem.setName("eraseMenuItem");
        			eraseMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					eraseMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			silentEraseMenuItem = new JMenuItem();
        			deletingInformationMenu.add(silentEraseMenuItem);
        			silentEraseMenuItem.setName("silentEraseMenuItem");
        			silentEraseMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					silentEraseMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			resetmenuMenu = new JMenu();
        			deletingInformationMenu.add(resetmenuMenu);
        			resetmenuMenu.setName("resetmenuMenu");
        			{
        				nodesresetMenuItem = new JMenuItem();
        				resetmenuMenu.add(nodesresetMenuItem);
        				nodesresetMenuItem.setName("nodesresetMenuItem");
        			}
        			{
        				relationsresetMenuItem = new JMenuItem();
        				resetmenuMenu.add(relationsresetMenuItem);
        				relationsresetMenuItem.setName("relationsresetMenuItem");
        			}
        			{
        				caseframesresteMenuItem = new JMenuItem();
        				resetmenuMenu.add(caseframesresteMenuItem);
        				caseframesresteMenuItem.setName("caseframesresteMenuItem");
        			}
        			{
        				networkresetMenuItem = new JMenuItem();
        				resetmenuMenu.add(networkresetMenuItem);
        				networkresetMenuItem.setName("networkresetMenuItem");
        			}
        		}
        	}
        	{
        		displayingInformationMenu = new JMenu();
        		jMenu2.add(displayingInformationMenu);
        		displayingInformationMenu.setName("displayingInformationMenu");
        		{
        			dumpMenuItem = new JMenuItem();
        			displayingInformationMenu.add(dumpMenuItem);
        			dumpMenuItem.setName("dumpMenuItem");
        			dumpMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					dumpMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			describeMenuItem = new JMenuItem();
        			displayingInformationMenu.add(describeMenuItem);
        			describeMenuItem.setName("describeMenuItem");
        			describeMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					describeMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			fullDescribeMenuItem = new JMenuItem();
        			displayingInformationMenu.add(fullDescribeMenuItem);
        			fullDescribeMenuItem.setName("fullDescribeMenuItem");
        			fullDescribeMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					fullDescribeMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		retrievingInformationMenu = new JMenu();
        		jMenu2.add(retrievingInformationMenu);
        		retrievingInformationMenu.setName("retrievingInformationMenu");
        		{
        			findMenuItem = new JMenuItem();
        			retrievingInformationMenu.add(findMenuItem);
        			findMenuItem.setName("findMenuItem");
        			findMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					findMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			findAssertMenuItem = new JMenuItem();
        			retrievingInformationMenu.add(findAssertMenuItem);
        			findAssertMenuItem.setName("findAssertMenuItem");
        			findAssertMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					findAssertMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			findConstantMenuItem = new JMenuItem();
        			retrievingInformationMenu.add(findConstantMenuItem);
        			findConstantMenuItem.setName("findConstantMenuItem");
        			findConstantMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					findConstantMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			findBaseMenuItem = new JMenuItem();
        			retrievingInformationMenu.add(findBaseMenuItem);
        			findBaseMenuItem.setName("findBaseMenuItem");
        			findBaseMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					findBaseMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			findVariableMenuItem = new JMenuItem();
        			retrievingInformationMenu.add(findVariableMenuItem);
        			findVariableMenuItem.setName("findVariableMenuItem");
        			findVariableMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					findVariableMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			findPatternMenuItem = new JMenuItem();
        			retrievingInformationMenu.add(findPatternMenuItem);
        			findPatternMenuItem.setName("findPatternMenuItem");
        			findPatternMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					findPatternMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			deduceMenuItem = new JMenuItem();
        			retrievingInformationMenu.add(deduceMenuItem);
        			deduceMenuItem.setName("deduceMenuItem");
        			deduceMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					deduceMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			deduceTrueMenuItem = new JMenuItem();
        			retrievingInformationMenu.add(deduceTrueMenuItem);
        			deduceTrueMenuItem.setName("deduceTrueMenuItem");
        			deduceTrueMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					deduceTrueMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			deduceFalseMenuItem = new JMenuItem();
        			retrievingInformationMenu.add(deduceFalseMenuItem);
        			deduceFalseMenuItem.setName("deduceFalseMenuItem");
        			deduceFalseMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					deduceFalseMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			deduceWHMenuItem = new JMenuItem();
        			retrievingInformationMenu.add(deduceWHMenuItem);
        			deduceWHMenuItem.setName("deduceWHMenuItem");
        			deduceWHMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					deduceWHMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			deduceWHNOTMenuItem = new JMenuItem();
        			retrievingInformationMenu.add(deduceWHNOTMenuItem);
        			deduceWHNOTMenuItem.setName("deduceWHNOTMenuItem");
        			deduceWHNOTMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					deduceWHNOTMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		connectivesMenu = new JMenu();
        		jMenu2.add(connectivesMenu);
        		connectivesMenu.setName("connectivesMenu");
        		{
        			andEntailmentMenuItem = new JMenuItem();
        			connectivesMenu.add(andEntailmentMenuItem);
        			andEntailmentMenuItem.setName("andEntailmentMenuItem");
        			andEntailmentMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					andEntailmentMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			orEntailmentMenuItem = new JMenuItem();
        			connectivesMenu.add(orEntailmentMenuItem);
        			orEntailmentMenuItem.setName("orEntailmentMenuItem");
        			orEntailmentMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					orEntailmentMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			numericalEntailmentMenuItem = new JMenuItem();
        			connectivesMenu.add(numericalEntailmentMenuItem);
        			numericalEntailmentMenuItem.setName("numericalEntailmentMenuItem");
        			numericalEntailmentMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					numericalEntailmentMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			andOrMenuItem = new JMenuItem();
        			connectivesMenu.add(andOrMenuItem);
        			andOrMenuItem.setName("andOrMenuItem");
        			andOrMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					andOrMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			threshMenuItem = new JMenuItem();
        			connectivesMenu.add(threshMenuItem);
        			threshMenuItem.setName("threshMenuItem");
        			threshMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					threshMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		quantifiersMenu = new JMenu();
        		jMenu2.add(quantifiersMenu);
        		quantifiersMenu.setName("quantifiersMenu");
        		{
        			universalQuantifierMenuItem = new JMenuItem();
        			quantifiersMenu.add(universalQuantifierMenuItem);
        			universalQuantifierMenuItem.setName("universalQuantifierMenuItem");
        			universalQuantifierMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					universalQuantifierMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			numericalQuantifierMenuItem = new JMenuItem();
        			quantifiersMenu.add(numericalQuantifierMenuItem);
        			numericalQuantifierMenuItem.setName("numericalQuantifierMenuItem");
        			numericalQuantifierMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					numericalQuantifierMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		tracingInferenceMenu = new JMenu();
        		jMenu2.add(tracingInferenceMenu);
        		tracingInferenceMenu.setName("tracingInferenceMenu");
        		{
        			evTraceMenuItem = new JMenuItem();
        			tracingInferenceMenu.add(evTraceMenuItem);
        			evTraceMenuItem.setName("evTraceMenuItem");
        			evTraceMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					evTraceMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			unevTraceMenuItem = new JMenuItem();
        			tracingInferenceMenu.add(unevTraceMenuItem);
        			unevTraceMenuItem.setName("unevTraceMenuItem");
        			unevTraceMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					unevTraceMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			inTraceMenuItem = new JMenuItem();
        			tracingInferenceMenu.add(inTraceMenuItem);
        			inTraceMenuItem.setName("inTraceMenuItem");
        			inTraceMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					inTraceMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			uninTraceMenuItem = new JMenuItem();
        			tracingInferenceMenu.add(uninTraceMenuItem);
        			uninTraceMenuItem.setName("uninTraceMenuItem");
        			uninTraceMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					uninTraceMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			multiPrintRegsMenuItem = new JMenuItem();
        			tracingInferenceMenu.add(multiPrintRegsMenuItem);
        			multiPrintRegsMenuItem.setName("multiPrintRegsMenuItem");
        			multiPrintRegsMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					multiPrintRegsMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		actingMenu = new JMenu();
        		jMenu2.add(actingMenu);
        		actingMenu.setName("actingMenu");
        		{
        			performMenuItem = new JMenuItem();
        			actingMenu.add(performMenuItem);
        			performMenuItem.setName("performMenuItem");
        			performMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					performMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			wheneverDoMenuItem = new JMenuItem();
        			actingMenu.add(wheneverDoMenuItem);
        			wheneverDoMenuItem.setName("wheneverDoMenuItem");
        			wheneverDoMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					wheneverDoMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			whenDoMenuItem = new JMenuItem();
        			actingMenu.add(whenDoMenuItem);
        			whenDoMenuItem.setName("whenDoMenuItem");
        			whenDoMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					whenDoMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			ifDoMenuItem = new JMenuItem();
        			actingMenu.add(ifDoMenuItem);
        			ifDoMenuItem.setName("ifDoMenuItem");
        			ifDoMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					ifDoMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		premitiveActsMenu = new JMenu();
        		jMenu2.add(premitiveActsMenu);
        		premitiveActsMenu.setName("premitiveActsMenu");
        		{
        			definePrimactionMenuItem = new JMenuItem();
        			premitiveActsMenu.add(definePrimactionMenuItem);
        			definePrimactionMenuItem.setName("definePrimactionMenuItem");
        			definePrimactionMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					definePrimactionMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			mentalFunctionsMenu = new JMenu();
        			premitiveActsMenu.add(mentalFunctionsMenu);
        			mentalFunctionsMenu.setName("mentalFunctionsMenu");
        			{
        				believeMenuItem = new JMenuItem();
        				mentalFunctionsMenu.add(believeMenuItem);
        				believeMenuItem.setName("believeMenuItem");
        				believeMenuItem.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent evt) {
        						believeMenuItemActionPerformed(evt);
        					}
        				});
        			}
        			{
        				disbelieveMenuItem = new JMenuItem();
        				mentalFunctionsMenu.add(disbelieveMenuItem);
        				disbelieveMenuItem.setName("disbelieveMenuItem");
        				disbelieveMenuItem.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent evt) {
        						disbelieveMenuItemActionPerformed(evt);
        					}
        				});
        			}
        		}
        		{
        			controlActsMenu = new JMenu();
        			premitiveActsMenu.add(controlActsMenu);
        			controlActsMenu.setName("controlActsMenu");
        			{
        				doAllMenuItem = new JMenuItem();
        				controlActsMenu.add(doAllMenuItem);
        				doAllMenuItem.setName("doAllMenuItem");
        				doAllMenuItem.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent evt) {
        						doAllMenuItemActionPerformed(evt);
        					}
        				});
        			}
        			{
        				doOneMenuItem = new JMenuItem();
        				controlActsMenu.add(doOneMenuItem);
        				doOneMenuItem.setName("doOneMenuItem");
        				doOneMenuItem.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent evt) {
        						doOneMenuItemActionPerformed(evt);
        					}
        				});
        			}
        			{
        				snsequenceMenuItem = new JMenuItem();
        				controlActsMenu.add(snsequenceMenuItem);
        				snsequenceMenuItem.setName("snsequenceMenuItem");
        				snsequenceMenuItem.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent evt) {
        						snsequenceMenuItemActionPerformed(evt);
        					}
        				});
        			}
        			{
        				snifMenuItem = new JMenuItem();
        				controlActsMenu.add(snifMenuItem);
        				snifMenuItem.setName("snifMenuItem");
        				snifMenuItem.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent evt) {
        						snifMenuItemActionPerformed(evt);
        					}
        				});
        			}
        			{
        				sniterateMenuItem = new JMenuItem();
        				controlActsMenu.add(sniterateMenuItem);
        				sniterateMenuItem.setName("sniterateMenuItem");
        				sniterateMenuItem.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent evt) {
        						sniterateMenuItemActionPerformed(evt);
        					}
        				});
        			}
        			{
        				withallMenuItem = new JMenuItem();
        				controlActsMenu.add(withallMenuItem);
        				withallMenuItem.setName("withallMenuItem");
        				withallMenuItem.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent evt) {
        						withallMenuItemActionPerformed(evt);
        					}
        				});
        			}
        			{
        				withsomeMenuItem = new JMenuItem();
        				controlActsMenu.add(withsomeMenuItem);
        				withsomeMenuItem.setName("withsomeMenuItem");
        				withsomeMenuItem.addActionListener(new ActionListener() {
        					public void actionPerformed(ActionEvent evt) {
        						withsomeMenuItemActionPerformed(evt);
        					}
        				});
        			}
        		}
        		{
        			attachPrimActionMenuItem = new JMenuItem();
        			premitiveActsMenu.add(attachPrimActionMenuItem);
        			attachPrimActionMenuItem.setName("attachPrimActionMenuItem");
        			attachPrimActionMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					attachPrimActionMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		complexActsMenu = new JMenu();
        		jMenu2.add(complexActsMenu);
        		complexActsMenu.setName("complexActsMenu");
        		{
        			planActMenuItem = new JMenuItem();
        			complexActsMenu.add(planActMenuItem);
        			planActMenuItem.setName("planActMenuItem");
        			planActMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					planActMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		goalsMenu = new JMenu();
        		jMenu2.add(goalsMenu);
        		goalsMenu.setName("goalsMenu");
        		{
        			achieveMenuItem = new JMenuItem();
        			goalsMenu.add(achieveMenuItem);
        			achieveMenuItem.setName("achieveMenuItem");
        			achieveMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					achieveMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        	{
        		preconditionsAndEffectsMenu = new JMenu();
        		jMenu2.add(preconditionsAndEffectsMenu);
        		preconditionsAndEffectsMenu.setName("preconditionsAndEffectsMenu");
        		{
        			actPreconditionMenuItem = new JMenuItem();
        			preconditionsAndEffectsMenu.add(actPreconditionMenuItem);
        			actPreconditionMenuItem.setName("actPreconditionMenuItem");
        			actPreconditionMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					actPreconditionMenuItemActionPerformed(evt);
        				}
        			});
        		}
        		{
        			actEffectMenuItem = new JMenuItem();
        			preconditionsAndEffectsMenu.add(actEffectMenuItem);
        			actEffectMenuItem.setName("actEffectMenuItem");
        			actEffectMenuItem.addActionListener(new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					actEffectMenuItemActionPerformed(evt);
        				}
        			});
        		}
        	}
        }
        {
        	jMenu1 = new JMenu();
        	menuBar.add(jMenu1);
        	jMenu1.setName("jMenu1");
        	{
        		tracingCheckBoxMenuItem = new JCheckBoxMenuItem();
        		jMenu1.add(tracingCheckBoxMenuItem);
        		tracingCheckBoxMenuItem.setName("tracingCheckBoxMenuItem");
        	}
        	{
        		resetnetrelationMenu = new JMenu();
        		jMenu1.add(resetnetrelationMenu);
        		resetnetrelationMenu.setName("resetnetrelationMenu");
        		resetButtonGroup = new ButtonGroup();
        		{
        			relationresetCheckBoxMenuItem = new JCheckBoxMenuItem();
        			resetButtonGroup.add(relationresetCheckBoxMenuItem);
        			relationresetCheckBoxMenuItem.setSelected(true);
        			relationresetCheckBoxMenuItem.setName("relationresetCheckBoxMenuItem");
        			resetnetrelationMenu.add(relationresetCheckBoxMenuItem);
        		}
        		{
        			keepsomeinforCheckBoxMenuItem = new JCheckBoxMenuItem();
        			resetButtonGroup.add(keepsomeinforCheckBoxMenuItem);
        			resetnetrelationMenu.add(keepsomeinforCheckBoxMenuItem);
        			keepsomeinforCheckBoxMenuItem.setName("keepsomeinforCheckBoxMenuItem");
        		}
        	}
        }
        getMainFrame().setJMenuBar(menuBar);
        show(topPanel);
        getMainFrame().pack();
        getMainFrame().validate();
    }
    
    private void assertButtonMouseClicked(MouseEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().assertMenuButton();
    }
    
    private void defineButtonMouseClicked(MouseEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().defineMenuButton();
    }
    
    private void showNetworkButtonMouseClicked(MouseEvent evt) {
    	sNePSULPanel1.getjTabbedPane1().setSelectedIndex(2);
    	switchToVisualizeNetwork();
    }
    
    private void findButtonMouseClicked(MouseEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().findMenuButton();
    }
    
    private void settingsButtonsActionPerformed(ActionEvent evt) {
    	
    }
    
    private void findMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().findMenuButton();
    }
    
    private void eraseMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().eraseMenuButton();
    }
    
    private void silentEraseMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().silentEraseMenuButton();
    }
    
    private void dumpMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().dumpMenuButton();
    }
    
    private void describeMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().describeMenuButton();
    }
    
    private void fullDescribeMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().fullDescribeMenuButton();
    }
    
    private void defineMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().defineMenuButton();
    }
    
    private void undefineMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().undefineMenuButton();
    }
    
    private void addMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().addMenuButton();
    }
    
    private void activateMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().activateMenuButton();
    }
    
    private void assertMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().assertMenuButton();
    }
    
    private void definePathMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().definePathMenuButton();
    }
    
    private void undefinePathMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().undefinePathMenuButton();
    }
    
    private void setContextMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().setContextMenuButton();
    }
    
    private void setDefaultContextMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().setDefaultContextMenuButton();
    }
    
    private void addToContextMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().addToContextMenuButton();
    }
    
    private void removeFromContextMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().removeFromContextMenuButton();
    }
    
    private void describeContextMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().describeContextMenuButton();
    }
    
    private void listHypothesesMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().listHypothesesMenuButton();
    }
    
    private void findAssertMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().findassertMenuButton();
    }
    
    private void findConstantMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().findconstantMenuButton();
    }
    
    private void findBaseMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().findbaseMenuButton();
    }
    
    private void findVariableMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().findvariableMenuButton();
    }
    
    private void findPatternMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().findpatternMenuButton();
    }
    
    private void deduceMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().deduceMenuButton();
    }
    
    private void deduceTrueMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().deducetrueMenuButton();
    }
    
    private void deduceFalseMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().deducefalseMenuButton();
    }
    
    private void deduceWHMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().deducewhMenuButton();
    }
    
    private void deduceWHNOTMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().deducewhnotMenuButton();
    }
    
    private void andEntailmentMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().andEntailmentMenuButton();
    }
    
    private void orEntailmentMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().orEntailmentMenuButton();
    }
    
    private void numericalEntailmentMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().numericalEntailmentMenuButton();
    }
    
    private void andOrMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().andOrMenuButton();
    }
    
    private void threshMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().threshMenuButton();
    }
    
    private void universalQuantifierMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().universalQunatifierMenuButton();
    }
    
    private void numericalQuantifierMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().numericalQuantifierMenuButton();
    }
    
    private void evTraceMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().evTraceMenuButton();
    }
    
    private void unevTraceMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().unevTraceMenuButton();
    }
    
    private void inTraceMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().inTraceMenuButton();
    }
    
    private void uninTraceMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().uninTraceMenuButton();
    }
    
    private void multiPrintRegsMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().multiPrintRegsMenuButton();
    }
    
    private void performMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
		sNePSULPanel1.getMenuDrivenCommands().performMenuButton();
	}
    
    private void wheneverDoMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().wheneverDoMenuButton();
    }
    
    private void whenDoMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().whenDoMenuButton();
    }
    
    private void ifDoMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().ifDoMenuButton();
    }
    
    private void definePrimactionMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().definePrimactionMenuButton();
    }
    
    private void attachPrimActionMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().attachPrimactionMenuButton();
    }
    
    private void believeMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().believeMenuButton();
    }
    
    private void disbelieveMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().disbeliveMenuButton();
    }
    
    private void doAllMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().doAllMenuButton();
    }
    
    private void doOneMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().doOneMenuButton();
    }
    
    private void planActMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().actPlanMenuButton();
    }
    
    private void snsequenceMenuItemActionPerformed(ActionEvent evt) {
    	switchToCommandsTab();
    	sNePSULPanel1.getMenuDrivenCommands().snsequenceMenuButton();
	}
	
	private void snifMenuItemActionPerformed(ActionEvent evt) {
		switchToCommandsTab();
		sNePSULPanel1.getMenuDrivenCommands().snifMenuButton();
	}
	
	private void sniterateMenuItemActionPerformed(ActionEvent evt) {
		switchToCommandsTab();
		sNePSULPanel1.getMenuDrivenCommands().sniterateMenuButton();
	}
	
	private void withallMenuItemActionPerformed(ActionEvent evt) {
		switchToCommandsTab();
		sNePSULPanel1.getMenuDrivenCommands().withallMenuButton();
	}
	
	private void withsomeMenuItemActionPerformed(ActionEvent evt) {
		switchToCommandsTab();
		sNePSULPanel1.getMenuDrivenCommands().withsomeMenuButton();
	}
	
	private void achieveMenuItemActionPerformed(ActionEvent evt) {
		switchToCommandsTab();
		sNePSULPanel1.getMenuDrivenCommands().achieveMenuButton();
	}
	
	private void actPreconditionMenuItemActionPerformed(ActionEvent evt) {
		switchToCommandsTab();
		sNePSULPanel1.getMenuDrivenCommands().actPreconditionMenuButton();
	}
	
	private void actEffectMenuItemActionPerformed(ActionEvent evt) {
		switchToCommandsTab();
		sNePSULPanel1.getMenuDrivenCommands().actEffectMenuButton();
	}
	
	private void defineCaseframeMenuItemActionPerformed(ActionEvent evt) {
		switchToCommandsTab();
		sNePSULPanel1.getMenuDrivenCommands().defineCaseFrameMenuButton();
	}
	
	private void undefineCaseframeMenuItemActionPerformed(ActionEvent evt) {
		switchToCommandsTab();
		sNePSULPanel1.getMenuDrivenCommands().undefineCaseFrameMenuButton();
	}
    
	/**
	 * 
	 */
	private void switchToCommandsTab() {
		sNePSULPanel1.getjTabbedPane1().setSelectedIndex(0);
		
		outputPanel1.setVisible(true);
		tracingPanel1.setVisible(true);
		
		sNePSULPanel1.setBounds(156, 1, 815, 366);
		sNePSULPanel1.getjTabbedPane1().setPreferredSize(new Dimension(815, 366));
		sNePSULPanel1.getjTabbedPane1().getComponent(0).setPreferredSize(new Dimension(815, 366));
		
		sNePSULPanel1.getjTabbedPane1().getComponent(0).validate();
		sNePSULPanel1.getjTabbedPane1().getComponent(0).repaint();
		
		sNePSULPanel1.getjTabbedPane1().validate();
		sNePSULPanel1.getjTabbedPane1().repaint();
		
		sNePSULPanel1.validate();
		sNePSULPanel1.repaint();
    	
    	this.getMainFrame().validate();
		this.getMainFrame().repaint();
		
		previousTab = 0;
	}
	
	/**
	 * 
	 */
	private void switchToVisualizeNetwork() {
		outputPanel1.setVisible(false);
		tracingPanel1.setVisible(false);
		
		sNePSULPanel1.setBounds(156, 1, 815, 600);
		sNePSULPanel1.getjTabbedPane1().setPreferredSize(new Dimension(815, 600));
		sNePSULPanel1.getjTabbedPane1().getComponent(0).setPreferredSize(new Dimension(815, 600));
		
		((VisualizeNetworks)sNePSULPanel1.getjTabbedPane1().getComponent(2)).initGUI();
		
		sNePSULPanel1.getjTabbedPane1().getComponent(2).validate();
		sNePSULPanel1.getjTabbedPane1().getComponent(2).repaint();
		
		sNePSULPanel1.getjTabbedPane1().validate();
		sNePSULPanel1.getjTabbedPane1().repaint();
		
		sNePSULPanel1.validate();
		sNePSULPanel1.repaint();
    	
    	this.getMainFrame().validate();
		this.getMainFrame().repaint();
		
		previousTab = 2;
	}
	
    /**
     * Creates a new session
     * @param evt the event that was fired
     */
    public void newButtonActionPerformed() {
    	Network network = new Network();
    	this.network = network;
    	try {
    		/*Connectives*/
    		Relation relation1 = network.defineRelation("&ant", "Entity", "expand",2);
    		Relation relation2 = network.defineRelation("cq", "Entity", "expand", 1);
    		Relation relation3 = network.defineRelation("ant", "Entity", "expand", 1);
    		Relation relation4 = network.defineRelation("thresh", "Entity", "none", 1);
    		Relation relation5 = network.defineRelation("arg", "Entity", "expand", 1);
    		Relation relation6 = network.defineRelation("min", "Entity", "none", 1);
    		Relation relation7 = network.defineRelation("max", "Entity", "none", 1);
    		Relation relation8 = network.defineRelation("threshmax", "Entity", "none", 1);
    		
    		//AndEntailment
    		LinkedList<Relation> relations1 = new LinkedList<Relation>();
    		relations1.add(relation1); //&ant
    		relations1.add(relation2); //cq
    		CaseFrame caseframe1 = network.defineCaseFrame("AndEntailment", relations1);
    		
    		//OrEntailment
    		LinkedList<Relation> relations2 = new LinkedList<Relation>();
    		relations2.add(relation3); //ant
    		relations2.add(relation2); //cq
    		CaseFrame caseframe2 = network.defineCaseFrame("OrEntailment", relations2);
    		
    		//NumericalEntailment
    		LinkedList<Relation> relations3 = new LinkedList<Relation>();
    		relations3.add(relation4); //thresh
    		relations3.add(relation1); //&ant
    		relations3.add(relation2); //cq
    		CaseFrame caseframe3 = network.defineCaseFrame("NumericalEntailment", relations3);
    		
    		//AndOr
    		LinkedList<Relation> relations4 = new LinkedList<Relation>();
    		relations4.add(relation6); //min
    		relations4.add(relation7); //max
    		relations4.add(relation5); //arg
    		CaseFrame caseframe4 = network.defineCaseFrame("AndOr", relations4);
    		
    		//Thresh
    		LinkedList<Relation> relations5 = new LinkedList<Relation>();
    		relations5.add(relation4); //thresh
    		relations5.add(relation8); //threshmax
    		relations5.add(relation5); //arg
    		CaseFrame caseframe5 = network.defineCaseFrame("Thresh", relations5);
    		
    		/*Acting*/
    		Relation relation9 = network.defineRelation("action", "Action", "none", 1);
    		Relation relation10 = network.defineRelation("actObject", "Entity", "none", 0);
    		Relation relation11 = network.defineRelation("object1", "Entity", "none", 1);
    		Relation relation12 = network.defineRelation("object2", "Entity", "none", 0);
    		Relation relation13 = network.defineRelation("condition", "Entity", "none", 1);
    		Relation relation14 = network.defineRelation("then", "Entity", "none", 0);
    		Relation relation15 = network.defineRelation("act", "Act", "none", 1);
    		Relation relation16 = network.defineRelation("plan", "Entity", "none", 1);
    		Relation relation17 = network.defineRelation("precondition", "Entity", "none", 1);
    		Relation relation18 = network.defineRelation("effect", "Entity", "none", 1);
    		Relation relation19 = network.defineRelation("goal", "Entity", "none", 1);
    		Relation relation20 = network.defineRelation("do", "Entity", "none", 1);
    		Relation relation21 = network.defineRelation("vars", "Entity", "none", 1);
    		Relation relation22 = network.defineRelation("suchthat", "Entity", "none", 1);
    		
    		//Act
    		LinkedList<Relation> relations6 = new LinkedList<Relation>();
    		relations6.add(relation9);  //action
    		relations6.add(relation10); //actObject
    		CaseFrame caseframe6 = network.defineCaseFrame("Act", relations6);
    		
    		//Action
    		LinkedList<Relation> relations7 = new LinkedList<Relation>();
    		relations7.add(relation11); //object1
    		relations7.add(relation12); //object2
    		CaseFrame caseframe7 = network.defineCaseFrame("Entity", relations7);
    		
    		//Guarded Node
    		LinkedList<Relation> relations8 = new LinkedList<Relation>();
    		relations8.add(relation13); //condition
    		relations8.add(relation15); //act
    		CaseFrame caseframe8 = network.defineCaseFrame("Entity", relations8);
    		
    		//Withall and Withsome
    		LinkedList<Relation> relations9 = new LinkedList<Relation>();
    		relations9.add(relation9);  //action
    		relations9.add(relation21); //vars
    		relations9.add(relation22); //suchthat
    		relations9.add(relation20); //do
    		//relations9.add();			//else
    		CaseFrame caseframe9 = network.defineCaseFrame("Entity", relations9);
    		
    		//Complex Act
    		LinkedList<Relation> relations10 = new LinkedList<Relation>();
    		relations10.add(relation15); //act
    		relations10.add(relation16); //plan
    		CaseFrame caseframe10 = network.defineCaseFrame("Entity", relations10);
    		
    		//Goals
    		LinkedList<Relation> relations11 = new LinkedList<Relation>();
    		relations11.add(relation19); //goal
    		relations11.add(relation16); //plan
    		CaseFrame caseframe11 = network.defineCaseFrame("Entity", relations11);
    		
    		//Precondition
    		LinkedList<Relation> relations12 = new LinkedList<Relation>();
    		relations12.add(relation15); //act
    		relations12.add(relation17); //precondition
    		CaseFrame caseframe12 = network.defineCaseFrame("Entity", relations12);
    		
    		//Effect
    		LinkedList<Relation> relations13 = new LinkedList<Relation>();
    		relations13.add(relation15); //act
    		relations13.add(relation18); //effect
    		CaseFrame caseframe13 = network.defineCaseFrame("Entity", relations13);
    		
    		/*Quantifiers*/
    		Relation relation23 = network.defineRelation("forall", "Entity", "expand", 1);
    		Relation relation24 = network.defineRelation("emin", "Entity", "none", 1);
    		Relation relation25 = network.defineRelation("emax", "Entity", "none", 1);
    		Relation relation26 = network.defineRelation("etot", "Entity", "none", 1);
    		Relation relation27 = network.defineRelation("pevb", "Entity", "expand", 1);
    		
    		//Universal Quantifier
    		LinkedList<Relation> relations14 = new LinkedList<Relation>();
    		relations14.add(relation23); //forall
    		relations14.add(relation1);  //&ant
    		relations14.add(relation2);  //cq
    		CaseFrame caseframe14 = network.defineCaseFrame("Entity", relations14);
    		
    		//Numerical Quantifier
    		LinkedList<Relation> relations15 = new LinkedList<Relation>();
    		relations15.add(relation24); //emin
    		relations15.add(relation25); //emax
    		relations15.add(relation26); //etot
    		relations15.add(relation27); //pevb
    		relations15.add(relation1); //&ant
    		relations15.add(relation2); //cq
    		CaseFrame caseframe15 = network.defineCaseFrame("Entity", relations15);
    	} catch (Exception e) {}
    	sNePSULPanel1.getMenuDrivenCommands().setNetwork(network);
    	sNePSULPanel1.getVisualizeNetworks().setNetwork(network);
    	sNePSULPanel1.getDrawNetworks().setNetwork(network);
    	nodesTreePanel1.setNetwork(network);
    	nodesTreePanel1.addTreeInfo();
    	nodesResult.setNetwork(network);
    	this.getMainFrame().validate();
    	this.getMainFrame().repaint();
    }

	/**
     * Adjusts the interface depending on the chosen tab
     * @param evt the event that was fired
     */
    private void jTabbedPane1MouseClicked(MouseEvent evt) {
    	int selectedIndex = sNePSULPanel1.getjTabbedPane1().getSelectedIndex();
    	Dimension dimension = new Dimension(815, 600);
    	if(network==null){
    		int result = JOptionPane.showConfirmDialog(getMainFrame(), 
    				"Do you want to start a new Session?", 
    				"New Session", 
    				JOptionPane.YES_NO_OPTION);
    		if(result == JOptionPane.YES_OPTION) {
    			newButtonActionPerformed();
    		}
    	}
    	
    	if(evt.getClickCount() == 2 && selectedIndex == 2) {
    		if(viewNetworkClickCount) {
    			outputPanel1.setVisible(true);
        		tracingPanel1.setVisible(true);
        		
        		((VisualizeNetworks)sNePSULPanel1.getjTabbedPane1().getComponent(2)).getVv().setPreferredSize(new Dimension(700,220));
        		sNePSULPanel1.setBounds(156, 1, 815, 366);
        		sNePSULPanel1.getjTabbedPane1().setPreferredSize(new Dimension(815, 366));
        		sNePSULPanel1.getjTabbedPane1().getComponent(2).setPreferredSize(new Dimension(815, 366));
        		
        		sNePSULPanel1.getjTabbedPane1().getComponent(2).validate();
        		sNePSULPanel1.getjTabbedPane1().getComponent(2).repaint();
        		
    			viewNetworkClickCount = false;
    		} else {
    			outputPanel1.setVisible(false);
        		tracingPanel1.setVisible(false);
        		
        		((VisualizeNetworks)sNePSULPanel1.getjTabbedPane1().getComponent(2)).getVv().setPreferredSize(new Dimension(700,470));
        		sNePSULPanel1.setBounds(156, 1, 815, 600);
        		sNePSULPanel1.getjTabbedPane1().setPreferredSize(dimension);
        		sNePSULPanel1.getjTabbedPane1().getComponent(2).setPreferredSize(dimension);
        		
        		sNePSULPanel1.getjTabbedPane1().getComponent(2).validate();
        		sNePSULPanel1.getjTabbedPane1().getComponent(2).repaint();
        		
    			viewNetworkClickCount = true;
    		}
    	} else if(selectedIndex != previousTab && evt.getClickCount() == 1) {
    		if (selectedIndex == 1) {
        		outputPanel1.setVisible(false);
        		tracingPanel1.setVisible(false);
        		
        		sNePSULPanel1.setBounds(156, 1, 815, 600);
        		sNePSULPanel1.getjTabbedPane1().setPreferredSize(dimension);
        		sNePSULPanel1.getjTabbedPane1().getComponent(1).setPreferredSize(dimension);
        		
        		sNePSULPanel1.getjTabbedPane1().getComponent(1).validate();
        		sNePSULPanel1.getjTabbedPane1().getComponent(1).repaint();
        		
        	} else if (selectedIndex == 0) {
        		outputPanel1.setVisible(true);
        		tracingPanel1.setVisible(true);
        		
        		sNePSULPanel1.setBounds(156, 1, 815, 366);
        		sNePSULPanel1.getjTabbedPane1().setPreferredSize(new Dimension(815, 366));
        		sNePSULPanel1.getjTabbedPane1().getComponent(0).setPreferredSize(new Dimension(815, 366));
        		
        		sNePSULPanel1.getjTabbedPane1().getComponent(0).validate();
        		sNePSULPanel1.getjTabbedPane1().getComponent(0).repaint();
        		
        	} else if (selectedIndex == 2) {
        		outputPanel1.setVisible(false);
        		tracingPanel1.setVisible(false);
        		
        		sNePSULPanel1.setBounds(156, 1, 815, 600);
        		sNePSULPanel1.getjTabbedPane1().setPreferredSize(dimension);
        		sNePSULPanel1.getjTabbedPane1().getComponent(0).setPreferredSize(dimension);
        		
        		((VisualizeNetworks)sNePSULPanel1.getjTabbedPane1().getComponent(2)).initGUI();
        		
        		sNePSULPanel1.getjTabbedPane1().getComponent(2).validate();
        		sNePSULPanel1.getjTabbedPane1().getComponent(2).repaint();
        	}
    		previousTab = selectedIndex;
    	}
    	sNePSULPanel1.getjTabbedPane1().validate();
		sNePSULPanel1.getjTabbedPane1().repaint();
		
		sNePSULPanel1.validate();
		sNePSULPanel1.repaint();
    	
    	this.getMainFrame().validate();
		this.getMainFrame().repaint();
    }
    
    /**
     * This method saves the drawn network or the visualized network depending on the tab that is selected, it also saves the
     * current network. The save button saves the corresponding option based on the current tab.
     * @param evt the event that was fired
     */
    private void saveButtonMouseClicked(MouseEvent evt) {
		if(sNePSULPanel1.getjTabbedPane1().getSelectedIndex() == 1) {
			JFileChooser chooser  = new JFileChooser();
	        int option = chooser.showSaveDialog(sNePSULPanel1.getDrawNetworks());
	        if(option == JFileChooser.APPROVE_OPTION) {
	            File file = chooser.getSelectedFile();
	            sNePSULPanel1.getDrawNetworks().writeJPEGImage(file);
	        }
		} else if (sNePSULPanel1.getjTabbedPane1().getSelectedIndex() == 2) {
			JFileChooser chooser  = new JFileChooser();
	        int option = chooser.showSaveDialog(sNePSULPanel1.getVisualizeNetworks());
	        if(option == JFileChooser.APPROVE_OPTION) {
	            File file = chooser.getSelectedFile();
	            sNePSULPanel1.getVisualizeNetworks().writeJPEGImage(file);
	        }
		} else if (sNePSULPanel1.getjTabbedPane1().getSelectedIndex() == 0) {
			JFileChooser chooser  = new JFileChooser();
	        int option = chooser.showSaveDialog(this.getMainFrame());
	        if(option == JFileChooser.APPROVE_OPTION) {
	        	File file = chooser.getSelectedFile();
				try {
					FileOutputStream fos = new FileOutputStream(file);
					ObjectOutputStream out = new ObjectOutputStream(fos);
		        	out.writeObject(this.getNetwork());
		        	out.close();
				} catch (FileNotFoundException e) {} 
				  catch (IOException e) {}
	        }
		}
	}
    
    /**
     * 
     * @param evt
     */
    private void openButtonMouseClicked(MouseEvent evt) {
	JFileChooser chooser  = new JFileChooser();
	int option = chooser.showOpenDialog(this.getMainFrame());
	if(option == JFileChooser.APPROVE_OPTION) {
    File file = chooser.getSelectedFile();
	try {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fis);
		Network network = (Network) in.readObject();
		in.close();
		this.network = network;
		sNePSULPanel1.getMenuDrivenCommands().setNetwork(network);
    	sNePSULPanel1.getVisualizeNetworks().setNetwork(network);
    	sNePSULPanel1.getDrawNetworks().setNetwork(network);
    	nodesTreePanel1.setNetwork(network);
	} catch (FileNotFoundException e) {} 
	  catch (IOException e) {} 
	  catch (ClassNotFoundException e) {}
			nodesTreePanel1.addTreeInfo();
	    	nodesResult.setNetwork(network);
	    	this.getMainFrame().validate();
	    	this.getMainFrame().repaint();
        }
	}
    
    /**
     * 
     * @param evt
     */
    private void resetnetButtonMouseClicked(MouseEvent evt) {
		Icon icon = new ImageIcon();
		Object[] possibilities = {"Network", "Nodes", "Relations", "Case Frames"};
		
		String result = (String)JOptionPane.showInputDialog(
				getMainFrame(),
				"Reset:",
				"Reset Network",
				JOptionPane.OK_OPTION,
				icon,
				possibilities,
				"Network");
		
		if(result == "Nodes") {
			while(true) {
				LinkedList<Node> nodesList = new LinkedList<Node>();
				Hashtable<String, Node> nodes = network.getNodes();
				
				if(nodes.isEmpty()) {
					break;
				}
				
				Set<String> nodesSet = nodes.keySet();
				Iterator<String> nodesItr = nodesSet.iterator();
				while (nodesItr.hasNext()) {
					nodesList.add(nodes.get(nodesItr.next()));
				}
				
				for (Node node : nodesList) {
					if(node.getUpCableSet().size() == 0) {
						network.removeNode(node);
					}
				}
			}
  		} else if (result == "Relations") {
  			LinkedList<Relation> relationsList = new LinkedList<Relation>();
			Hashtable<String, Relation> relations = network.getRelations();
			
			Set<String> relationSet = relations.keySet();
			Iterator<String> relationItr = relationSet.iterator();
			while (relationItr.hasNext()) {
				relationsList.add(relations.get(relationItr.next()));
			}
			
			for (Relation relation : relationsList) {
				network.undefineRelation(relation.getName());
			}
		} else if (result == "Case Frames") {
			LinkedList<CaseFrame> caseframesList = new LinkedList<CaseFrame>();
			Hashtable<String, CaseFrame> caseframes = network.getCaseFrames();
			
			Set<String> caseframesSet = caseframes.keySet();
			Iterator<String> relationItr = caseframesSet.iterator();
			while (relationItr.hasNext()) {
				caseframesList.add(caseframes.get(relationItr.next()));
			}
			
			for (CaseFrame caseframe : caseframesList) {
				network.undefineCaseFrame(caseframe.getId());
			}
		} else if(result == "Network") {
			if(relationresetCheckBoxMenuItem.isSelected()) {
				newButtonActionPerformed();
			} else if (keepsomeinforCheckBoxMenuItem.isSelected()) {
				while(true) {
					LinkedList<Node> nodesList = new LinkedList<Node>();
					Hashtable<String, Node> nodes = network.getNodes();
					
					if(nodes.isEmpty()) {
						break;
					}
					
					Set<String> nodesSet = nodes.keySet();
					Iterator<String> nodesItr = nodesSet.iterator();
					while (nodesItr.hasNext()) {
						nodesList.add(nodes.get(nodesItr.next()));
					}
					
					for (Node node : nodesList) {
						if(node.getUpCableSet().size() == 0) {
							network.removeNode(node);
						}
					}
				}
			}
		} else {
			return;
		}
		
		nodesTreePanel1.initGUI();
		nodesTreePanel1.validate();
		nodesTreePanel1.repaint();
	}
    
    /**
     * @return the panel that includes the tabs with the commands, drawing network and visualizing network panels
     */
    public SNePSULPanel getsNePSULPanel1() {
		return sNePSULPanel1;
	}
    
    /**
     * @return the panel that any output is displayed in
     */
	public OutputPanel getOutputPanel1() {
		return outputPanel1;
	}
	
	/**
	 * @return the panel that includes the tree with all the network information such as all the nodes, relations 
	 * and case frames in the network
	 */
	public NodesTreePanel getNodesTreePanel1() {
		return nodesTreePanel1;
	}
    
	/**
	 * @return the panels that includes a list of all the nodes that resulted from an output
	 */
    public ResultNodes getNodesResult() {
		return nodesResult;
	}
    
    /**
     * 
     * @return
     */
    public TracingPanel getTracingPanel1() {
		return tracingPanel1;
	}
    
    /**
     * 
     * @return
     */
    public Network getNetwork() {
		return network;
	}
    
    /**
     * 
     * @return
     */
    public JCheckBoxMenuItem getTracingCheckBoxMenuItem() {
		return tracingCheckBoxMenuItem;
	}
	
	public static void main(String[] args) {
        launch(SNePSInterface.class, args);
    }
}