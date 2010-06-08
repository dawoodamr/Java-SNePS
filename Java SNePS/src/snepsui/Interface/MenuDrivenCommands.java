package snepsui.Interface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdesktop.application.Action;

import snactor.Act;
import snebr.Proposition;
import snebr.Support;
import snepsui.Commands.*;

import sneps.AndPath;
import sneps.BUnitPath;
import sneps.BangPath;
import sneps.Cable;
import sneps.ComposePath;
import sneps.ConversePath;
import sneps.DomainRestrictPath;
import sneps.Entity;
import sneps.FUnitPath;
import sneps.Individual;
import sneps.IrreflexiveRestrictPath;
import sneps.KPlusPath;
import sneps.KStarPath;
import sneps.MolecularNode;
import sneps.Network;
import sneps.Node;
import sneps.NodeSet;
import sneps.OrPath;
import sneps.Path;
import sneps.RangeRestrictPath;
import sneps.Relation;
import sneps.RelativeComplementPath;
import sneps.UpCable;
import snip.fns.AndEntailment;
import snip.fns.AndOr;
import snip.fns.NumericalEntailment;
import snip.fns.OrEntailment;
import snip.fns.Thresh;

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
public class MenuDrivenCommands extends javax.swing.JPanel {
	private JComboBox commandsComboBox;
	private JComboBox commandMenusComboBox;
	private JPanel commandsPanel;
	private DefaultComboBoxModel jComboBox1Model;
	private Network network;
	private SNePSInterface frame;
	private String newLine = "\n";
	private String endLine = "----------------------------------------" + "\n";
	private Point point;

	public MenuDrivenCommands(SNePSInterface frame) {
		super();
		this.network = null;
		this.frame = frame;
		this.point = new Point(10, 10);
		initGUI();
	}
	
	/**
	 * Initializes the components in the MenuDrivenCommands panel
	 */
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			this.setLayout(null);
			{
				jComboBox1Model = 
					new DefaultComboBoxModel(
							new String[] { "define", "undefine", "define-caseframe" , "undefine-caseframe", "deduce", "define-path",
									 "undefine-path", "set-context", "set-default-context", "add-to-context", "remove-from-context",
									 "list-context-names", "describe-context", "list-hypotheses", "build", "assert", "add", "activate", 
									 "erase", "silent-erase", "dump", "describe",
									 "full-describe", "find", "findassert", "findconstant", "findbase", 
									 "findvariable", "findpatter", "deducetrue", "deducefalse", "deducewh", "deducewhnot",
									 "And-Entailment", "Or-Entailment", "Numerrical Entailment", "AndOr", "Thresh", "Universal Quantifier",
									 "Numerical-Quantifier", "ev-trace", "unev-trace", "in-trace", "unin-trace", "multi::print-regs"});
				commandsComboBox = new JComboBox();
				this.add(commandsComboBox);
				commandsComboBox.setModel(jComboBox1Model);
				commandsComboBox.setBounds(397, 28, 196, 22);
				commandsComboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent evt) {
						commandsComboBoxItemStateChanged(evt);
					}
				});
			}
			{
				ComboBoxModel jComboBox2Model = 
					new DefaultComboBoxModel(
							new String[] { "Relations", "Case Frames", "Contexts", "Building Networks", "Wire-based Inference", "Path-Based Inference", 
									"Deleting Information", "Displaying Information", "Retrieving Information", "Connectives", "Quantifiers", 
									"Tracing Inference", "Acting", "Primitive Acts", "Mental Acts", "Control Acts", "Complex Acts", "Goals",  
									"Preconditions and Effects"});
				commandMenusComboBox = new JComboBox();
				this.add(commandMenusComboBox);
				commandMenusComboBox.setModel(jComboBox2Model);
				commandMenusComboBox.setBounds(189, 28, 196, 22);
				commandMenusComboBox.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						commandMenusComboBoxActionPerformed(evt);
					}
				
				});
			}
			{
				commandsPanel = new JPanel();
				this.add(commandsPanel);
				commandsPanel.setBounds(40, 67, 694, 233);
				commandsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			}
			{

			}
		} catch (Exception e) {}
	}
	
	/**
	 * Lists the commands of the chosen command menu in the commands drop-down list and sets the first
	 * command to the commands panel
	 * @param evt he event that triggered the item event
	 */
	private void commandMenusComboBoxActionPerformed(ActionEvent evt) {
		if(network == null) {
			int result = JOptionPane.showConfirmDialog(this, 
    				"Do you want to start a new Session?", 
    				"New Session", 
    				JOptionPane.YES_NO_OPTION);
    		if(result == JOptionPane.YES_OPTION) {
    			frame.newButtonActionPerformed();
    		}
		} else if(commandMenusComboBox.getSelectedItem().equals("Relations")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("define");
			dcbm.addElement("undefine");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDefine(network, frame));
		} 
		else if(commandMenusComboBox.getSelectedItem().equals("Case Frames")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("define-caseframe");
			dcbm.addElement("undefine-caseframe");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdCaseFrame(network, frame));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Path-Based Inference")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("define-path");
			dcbm.addElement("undefine-path");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDefinePath(network, frame));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Contexts")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("set-context");
			dcbm.addElement("set-default-context");
			dcbm.addElement("add-to-context");
			dcbm.addElement("remove-from-context");
			dcbm.addElement("describe-context");
			dcbm.addElement("list-hypotheses");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdSetContext(network, frame));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Building Networks")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("assert");
			dcbm.addElement("add");
			dcbm.addElement("activate");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAssert(network, frame));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Deleting Information")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("erase");
			dcbm.addElement("silent-erase");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdErase(network));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Displaying Information")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("dump");
			dcbm.addElement("describe");
			dcbm.addElement("full-describe");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDump(network, frame));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Retrieving Information")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("find");
			dcbm.addElement("findassert");
			dcbm.addElement("findconstant");
			dcbm.addElement("findbase");
			dcbm.addElement("findvariable");
			dcbm.addElement("findpattern");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFind(network, frame));
		}
		else if (commandMenusComboBox.getSelectedItem().equals("Node-based Inference")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("deduce");
			dcbm.addElement("deducetrue");
			dcbm.addElement("deducefalse");
			dcbm.addElement("deducewh");
			dcbm.addElement("deducewhnot");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDeduce(network, frame));
		} 
		else if(commandMenusComboBox.getSelectedItem().equals("Connectives")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("And-Entailment");
			dcbm.addElement("Or-Entailment");
			dcbm.addElement("Numerical Entailment");
			dcbm.addElement("AndOr");
			dcbm.addElement("Thresh");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAndEntailment(network, frame));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Quantifiers")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("Universal Quantifier");
			dcbm.addElement("Numerical Quantifier");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUniversalQunatifier(network, frame));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Tracing Inference")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("ev-trace");
			dcbm.addElement("unev-trace");
			dcbm.addElement("in-trace");
			dcbm.addElement("unin-trace");
			dcbm.addElement("multi::print-regs");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdEVTrace(network, frame));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Acting")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("perform");
			dcbm.addElement("{<whenever, p>,<do, a>}");
			dcbm.addElement("{<when, p>,<do, a>}");
			dcbm.addElement("{<if, p>,<do, a>}");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdPerform(network, frame));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Primitive Acts")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("define-primaction");
			dcbm.addElement("attach-primaction");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDefinePrimaction(network, frame));
		} else if(commandMenusComboBox.getSelectedItem().equals("Mental Acts")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("believe");
			dcbm.addElement("disbelieve");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdBelieve(network, frame));
		} else if(commandMenusComboBox.getSelectedItem().equals("Control Acts")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("do-all");
			dcbm.addElement("do-one");
			dcbm.addElement("snsequence");
			dcbm.addElement("snif");
			dcbm.addElement("sniterate");
			dcbm.addElement("withall");
			dcbm.addElement("withsome");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDoAll(network, frame));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Complex Acts")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("{<plan, p>,<act, a>}");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdActPlan(network, frame));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Goals")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("achieve");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAchieve(network, frame));
		}
		else if(commandMenusComboBox.getSelectedItem().equals("Preconditions and Effects")) {
			DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
			dcbm.addElement("{<act, a>,<precondition, p>}");
			dcbm.addElement("{<act, a>,<effect, p>}");
			commandsComboBox.setModel(dcbm);
			commandsPanel.removeAll();
			commandsPanel.add(new cmdActPrecondition(network, frame));
		}
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command to the commands panel based on the chosen command from the commands drop-down
	 * list
	 * @param evt the event that triggered the item event
	 */
	private void commandsComboBoxItemStateChanged(ItemEvent evt) {
		
		//define
		if(commandsComboBox.getSelectedItem().equals("define")) {	
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDefine(network, frame));
		}
		//undefine
		else if(commandsComboBox.getSelectedItem().equals("undefine")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUndefine(network, frame));
		}
		//define-caseframe
		else if(commandsComboBox.getSelectedItem().equals("define-caseframe")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdCaseFrame(network, frame));
		}
		//undefine-caseframe
		else if(commandsComboBox.getSelectedItem().equals("undefine-caseframe")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUndefineCaseFrame(network, frame));
		}
		//assert
		else if(commandsComboBox.getSelectedItem().equals("assert")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAssert(network, frame));
		}
		//add
		else if(commandsComboBox.getSelectedItem().equals("add")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAdd(network, frame));
		}
		//activate
		else if(commandsComboBox.getSelectedItem().equals("activate")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdActivate(network, frame));
		}
		//erase
		else if(commandsComboBox.getSelectedItem().equals("erase")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdErase(network));
		}
		//silent-erase
		else if(commandsComboBox.getSelectedItem().equals("silent-erase")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdSilentErase(network));
		}
		//define-path
		else if(commandsComboBox.getSelectedItem().equals("define-path")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDefinePath(network, frame));
		}
		//undefine-path
		else if(commandsComboBox.getSelectedItem().equals("undefine-path")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUndefinePath(network, frame));
		}
		//add-to-context
		else if(commandsComboBox.getSelectedItem().equals("add-to-context")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAddToContext(network, frame));
		}
		//set-context
		else if(commandsComboBox.getSelectedItem().equals("set-context")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdSetContext(network, frame));
		}
		//set-default-context
		else if(commandsComboBox.getSelectedItem().equals("set-default-context")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdSetDefaultContext(network, frame));
		}
		//remove-from-context
		else if(commandsComboBox.getSelectedItem().equals("remove-from-context")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdRemoveFromContext(network, frame));
		}
		//describe-context
		else if(commandsComboBox.getSelectedItem().equals("describe-context")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDescribeContext(network, frame));
		}
		//list-hypotheses
		else if(commandsComboBox.getSelectedItem().equals("list-hypotheses")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdListHypotheses(network, frame));
		}
		//list-nodes
		else if(commandsComboBox.getSelectedItem().equals("list-nodes")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdListNodes(network, frame));
		}
		//dump
		else if(commandsComboBox.getSelectedItem().equals("dump")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDump(network, frame));
		}
		//describe
		else if(commandsComboBox.getSelectedItem().equals("describe")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDescribe(network, frame));
		}
		//full-describe
		else if(commandsComboBox.getSelectedItem().equals("full-describe")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFullDescribe(network, frame));
		}
		//find
		else if(commandsComboBox.getSelectedItem().equals("find")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFind(network, frame));
		}
		//findassert
		else if(commandsComboBox.getSelectedItem().equals("findassert")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFindAssert(network, frame));
		}
		//findconstant
		else if(commandsComboBox.getSelectedItem().equals("findconstant")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFindConstant(network, frame));
		}
		//findbase
		else if(commandsComboBox.getSelectedItem().equals("findbase")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFindBase(network, frame));
		}
		//findvariable
		else if(commandsComboBox.getSelectedItem().equals("findvariable")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFindVariable(network, frame));
		}
		//findpattern
		else if(commandsComboBox.getSelectedItem().equals("findpattern")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdFindPattern(network, frame));
		}
		//ev-trace
		else if(commandsComboBox.getSelectedItem().equals("ev-trace")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdEVTrace(network, frame));
		}
		//unev-trace
		else if(commandsComboBox.getSelectedItem().equals("unev-trace")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUNEVTrace(network, frame));
		}
		//in-trace
		else if(commandsComboBox.getSelectedItem().equals("in-trace")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdINTrace(network, frame));
		}
		//unin-trace
		else if(commandsComboBox.getSelectedItem().equals("unin-trace")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUNINTrace(network, frame));
		}
		//dcbm.addElement("multi::print-regs");
		else if(commandsComboBox.getSelectedItem().equals("multi::print-regs")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdMultiPrintRegs(network, frame));
		}
		//deduce
		else if(commandsComboBox.getSelectedItem().equals("deduce")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDeduce(network, frame));
		}
		//deducetrue
		else if(commandsComboBox.getSelectedItem().equals("deducetrue")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDeduceTrue(network, frame));
		}
		//deducefalse
		else if(commandsComboBox.getSelectedItem().equals("deducefalse")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDeduceFalse(network, frame));
		}
		//deducewh
		else if(commandsComboBox.getSelectedItem().equals("deducewh")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDeduceWH(network, frame));
		}
		//deducewhnot
		else if(commandsComboBox.getSelectedItem().equals("deducewhnot")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDeduceWHNOT(network, frame));
		}
		//perform
		else if(commandsComboBox.getSelectedItem().equals("perform")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdPerform(network, frame));
		}
		//{<whenever, p>,<do, a>}
		else if(commandsComboBox.getSelectedItem().equals("{<whenever, p>,<do, a>}")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdWheneverDo(network, frame));
		}
		//{<when, p>,<do, a>}
		else if(commandsComboBox.getSelectedItem().equals("{<when, p>,<do, a>}")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdWhenDo(network, frame));
		}
		//{<if, p>,<do, a>}
		else if(commandsComboBox.getSelectedItem().equals("{<if, p>,<do, a>}")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdIfDo(network, frame));
		}
		//believe
		else if(commandsComboBox.getSelectedItem().equals("believe")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdBelieve(network, frame));
		}
		//disbelive
		else if(commandsComboBox.getSelectedItem().equals("disbelieve")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDisbelieve(network, frame));
		}
		//do-all
		else if(commandsComboBox.getSelectedItem().equals("do-all")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDoAll(network, frame));
		}
		//do-one
		else if(commandsComboBox.getSelectedItem().equals("do-one")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDoOne(network, frame));
		}
		//snsequence
		else if(commandsComboBox.getSelectedItem().equals("snsequence")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdSNSequence(network, frame));
		}
		//snif
		else if(commandsComboBox.getSelectedItem().equals("snif")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdSnif(network, frame));
		}
		//sniterate
		else if(commandsComboBox.getSelectedItem().equals("sniterate")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdSniterate(network, frame));
		}
		//withall
		else if(commandsComboBox.getSelectedItem().equals("withall")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdWithAll(network, frame));
		}
		//withsome
		else if(commandsComboBox.getSelectedItem().equals("withsome")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdWithSome(network, frame));
		}
		//{<plan, p>,<act, a>}
		else if(commandsComboBox.getSelectedItem().equals("{<plan, p>,<act, a>}")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDoOne(network, frame));
		}
		//dcbm.addElement("Universal Quantifier");
		else if(commandsComboBox.getSelectedItem().equals("Universal Quantifier")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdUniversalQunatifier(network, frame));
		}
		//Numerical Quantifier
		else if(commandsComboBox.getSelectedItem().equals("Numerical Quantifier")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdNumericalQuantifier(network, frame));
		}
		//And-Entailment
		else if(commandsComboBox.getSelectedItem().equals("And-Entailment")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAndEntailment(network, frame));
		}
		//Or-Entailment
		else if(commandsComboBox.getSelectedItem().equals("Or-Entailment")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdOrEntailment(network, frame));
		}
		//Numerical Entailment
		else if(commandsComboBox.getSelectedItem().equals("Numerical Entailment")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdNumericalEntailment(network, frame));
		}
		//AndOr
		else if(commandsComboBox.getSelectedItem().equals("AndOr")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAndOr(network, frame));
		}
		//Thresh
		else if(commandsComboBox.getSelectedItem().equals("Thresh")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdThresh(network, frame));
		}
		//define-primaction
		else if(commandsComboBox.getSelectedItem().equals("define-primaction")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdDefinePrimaction(network, frame));
		}
		//attach-primaction
		else if(commandsComboBox.getSelectedItem().equals("attach-primaction")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAttachPrimaction(network, frame));
		}
		//achieve
		else if(commandsComboBox.getSelectedItem().equals("achieve")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdAchieve(network, frame));
		}
		//{<act, a>,<precondition, p>}
		else if(commandsComboBox.getSelectedItem().equals("{<act, a>,<precondition, p>}")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdActPrecondition(network, frame));
		}
		//{<act, a>,<effect, p>}
		else if(commandsComboBox.getSelectedItem().equals("{<act, a>,<effect, p>}")) {
			commandsPanel.removeAll();
			commandsPanel.add(new cmdActEffect(network, frame));
		}
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to assert
	 */
	public void assertMenuButton() {
		commandsComboBox.setSelectedItem("assert");
		commandMenusComboBox.setSelectedItem("Building Networks");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdAssert(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to define
	 */
	public void defineMenuButton() {
		commandsComboBox.setSelectedItem("define");
		commandMenusComboBox.setSelectedItem("Relations");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDefine(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to find
	 */
	public void findMenuButton() {
		commandsComboBox.setSelectedItem("find");
		commandMenusComboBox.setSelectedItem("Retrieving Information");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdFind(network, frame));
		this.repaint();
		this.validate();
	}

	/**
	 * Sets the command panel to erase
	 */
	public void eraseMenuButton() {
		commandsComboBox.setSelectedItem("erase");
		commandMenusComboBox.setSelectedItem("Deleting Information");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdErase(network));
		this.repaint();
		this.validate();
	}

	/**
	 * Sets the command panel to silent-erase
	 */
	public void silentEraseMenuButton() {
		commandsComboBox.setSelectedItem("silent-erase");
		commandMenusComboBox.setSelectedItem("Deleting Information");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdSilentErase(network));
		this.repaint();
		this.validate();
	}

	/**
	 * Sets the command panel to dump
	 */
	public void dumpMenuButton() {
		commandsComboBox.setSelectedItem("dump");
		commandMenusComboBox.setSelectedItem("Displaying Information");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDump(network, frame));
		this.repaint();
		this.validate();
	}

	/**
	 * Sets the command panel to describe
	 */
	public void describeMenuButton() {
		commandsComboBox.setSelectedItem("describe");
		commandMenusComboBox.setSelectedItem("Displaying Information");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDescribe(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to full-describe
	 */
	public void fullDescribeMenuButton() {
		commandsComboBox.setSelectedItem("full-describe");
		commandMenusComboBox.setSelectedItem("Displaying Information");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdFullDescribe(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to undefine
	 */
	public void undefineMenuButton() {
		commandsComboBox.setSelectedItem("undefine");
		commandMenusComboBox.setSelectedItem("Relations");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdUndefine(network, frame));
		this.repaint();
		this.validate();
	}

	/**
	 * Sets the command panel to add
	 */
	public void addMenuButton() {
		commandsComboBox.setSelectedItem("add");
		commandMenusComboBox.setSelectedItem("Building Networks");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdAdd(network, frame));
		this.repaint();
		this.validate();
	}

	/**
	 * Sets the command panel to activate
	 */
	public void activateMenuButton() {
		commandsComboBox.setSelectedItem("activate");
		commandMenusComboBox.setSelectedItem("Building Networks");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdActivate(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to define-path
	 */
	public void definePathMenuButton() {
		commandsComboBox.setSelectedItem("define-path");
		commandMenusComboBox.setSelectedItem("Path-Based Inference");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDefinePath(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to undefine-path
	 */
	public void undefinePathMenuButton() {
		commandsComboBox.setSelectedItem("undefine-path");
		commandMenusComboBox.setSelectedItem("Path-Based Inference");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdUndefinePath(network, frame));
		this.repaint();
		this.validate();
	}

	/**
	 * Sets the command panel to set-context
	 */
	public void setContextMenuButton() {
		commandsComboBox.setSelectedItem("set-context");
		commandMenusComboBox.setSelectedItem("Contexts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdSetContext(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to set-default-context
	 */
	public void setDefaultContextMenuButton() {
		commandsComboBox.setSelectedItem("set-default-context");
		commandMenusComboBox.setSelectedItem("Contexts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdSetDefaultContext(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to set-to-context
	 */
	public void addToContextMenuButton() {
		commandsComboBox.setSelectedItem("set-to-context");
		commandMenusComboBox.setSelectedItem("Contexts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdAddToContext(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to remove-from-context
	 */
	public void removeFromContextMenuButton() {
		commandsComboBox.setSelectedItem("remove-from-context");
		commandMenusComboBox.setSelectedItem("Contexts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdRemoveFromContext(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to describe-context
	 */
	public void describeContextMenuButton() {
		commandsComboBox.setSelectedItem("describe-context");
		commandMenusComboBox.setSelectedItem("Contexts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDescribeContext(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to list-hypotheses
	 */
	public void listHypothesesMenuButton() {
		commandsComboBox.setSelectedItem("list-hypotheses");
		commandMenusComboBox.setSelectedItem("Contexts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdListHypotheses(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to list-nodes
	 */
	public void listNodesMenuButton() {
		commandsComboBox.setSelectedItem("list-nodes");
		commandMenusComboBox.setSelectedItem("Contexts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdListNodes(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to define-caseframe
	 */
	public void defineCaseFrameMenuButton() {
		commandsComboBox.setSelectedItem("define-caseframe");
		commandMenusComboBox.setSelectedItem("Case Frames");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdCaseFrame(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to undefine-caseframe
	 */
	public void undefineCaseFrameMenuButton() {
		commandsComboBox.setSelectedItem("undefine-caseframe");
		commandMenusComboBox.setSelectedItem("Case Frames");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdUndefineCaseFrame(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to findassert
	 */
	public void findassertMenuButton() {
		commandsComboBox.setSelectedItem("findassert");
		commandMenusComboBox.setSelectedItem("Retrieving Information");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdFindAssert(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to findpattern
	 */
	public void findpatternMenuButton() {
		commandsComboBox.setSelectedItem("findpattern");
		commandMenusComboBox.setSelectedItem("Retrieving Information");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdFindPattern(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to findbase
	 */
	public void findbaseMenuButton() {
		commandsComboBox.setSelectedItem("findbase");
		commandMenusComboBox.setSelectedItem("Retrieving Information");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdFindBase(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to findconstant
	 */
	public void findconstantMenuButton() {
		commandsComboBox.setSelectedItem("findconstant");
		commandMenusComboBox.setSelectedItem("Retrieving Information");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdFindConstant(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to findvariable
	 */
	public void findvariableMenuButton() {
		commandsComboBox.setSelectedItem("findvariable");
		commandMenusComboBox.setSelectedItem("Retrieving Information");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdFindVariable(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to deduce
	 */
	public void deduceMenuButton() {
		commandsComboBox.setSelectedItem("deduce");
		commandMenusComboBox.setSelectedItem("Retrieving Information");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDeduce(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to deducetrue
	 */
	public void deducetrueMenuButton() {
		commandsComboBox.setSelectedItem("deducetrue");
		commandMenusComboBox.setSelectedItem("Retrieving Information");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDeduceTrue(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to deducefalse
	 */
	public void deducefalseMenuButton() {
		commandsComboBox.setSelectedItem("deducefalse");
		commandMenusComboBox.setSelectedItem("Retrieving Information");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDeduceFalse(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to deducewh
	 */
	public void deducewhMenuButton() {
		commandsComboBox.setSelectedItem("deducewh");
		commandMenusComboBox.setSelectedItem("Retrieving Information");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDeduceWH(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to deducewhnot
	 */
	public void deducewhnotMenuButton() {
		commandsComboBox.setSelectedItem("deducewhnot");
		commandMenusComboBox.setSelectedItem("Retrieving Information");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDeduceWHNOT(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to And-Entailment
	 */
	public void andEntailmentMenuButton() {
		commandsComboBox.setSelectedItem("And-Entailment");
		commandMenusComboBox.setSelectedItem("Connectives");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdAndEntailment(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to Or-Entailment
	 */
	public void orEntailmentMenuButton() {
		commandsComboBox.setSelectedItem("Or-Entailment");
		commandMenusComboBox.setSelectedItem("Connectives");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdOrEntailment(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to Numerical Entailment
	 */
	public void numericalEntailmentMenuButton() {
		commandsComboBox.setSelectedItem("Numerical Entailment");
		commandMenusComboBox.setSelectedItem("Connectives");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdNumericalEntailment(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to AndOr
	 */
	public void andOrMenuButton() {
		commandsComboBox.setSelectedItem("AndOr");
		commandMenusComboBox.setSelectedItem("Connectives");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdAndOr(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to Thresh
	 */
	public void threshMenuButton() {
		commandsComboBox.setSelectedItem("Thresh");
		commandMenusComboBox.setSelectedItem("Connectives");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdThresh(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to Numerical Quantifier
	 */
	public void numericalQuantifierMenuButton() {
		commandsComboBox.setSelectedItem("Numerical Quantifier");
		commandMenusComboBox.setSelectedItem("Quantifiers");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdNumericalQuantifier(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to Universal Quantifier
	 */
	public void universalQunatifierMenuButton() {
		commandsComboBox.setSelectedItem("Universal Quantifier");
		commandMenusComboBox.setSelectedItem("Quantifiers");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdUniversalQunatifier(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to unev-trace
	 */
	public void unevTraceMenuButton() {
		commandsComboBox.setSelectedItem("unev-trace");
		commandMenusComboBox.setSelectedItem("Tracing Inference");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdUNEVTrace(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to unin-trace
	 */
	public void uninTraceMenuButton() {
		commandsComboBox.setSelectedItem("unin-trace");
		commandMenusComboBox.setSelectedItem("Tracing Inference");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdUNINTrace(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to in-trace
	 */
	public void inTraceMenuButton() {
		commandsComboBox.setSelectedItem("in-trace");
		commandMenusComboBox.setSelectedItem("Tracing Inference");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdINTrace(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to in-trace
	 */
	public void evTraceMenuButton() {
		commandsComboBox.setSelectedItem("ev-trace");
		commandMenusComboBox.setSelectedItem("Tracing Inference");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdEVTrace(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to multi::print-regs
	 */
	public void multiPrintRegsMenuButton() {
		commandsComboBox.setSelectedItem("multi::print-regs");
		commandMenusComboBox.setSelectedItem("Tracing Inference");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdMultiPrintRegs(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to perform
	 */
	public void performMenuButton() {
		commandsComboBox.setSelectedItem("perform");
		commandMenusComboBox.setSelectedItem("Acting");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdPerform(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to {<whenever, p>,<do, a>}
	 */
	public void wheneverDoMenuButton() {
		commandsComboBox.setSelectedItem("{<whenever, p>,<do, a>}");
		commandMenusComboBox.setSelectedItem("Acting");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdWheneverDo(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to {<when, p>,<do, a>}
	 */
	public void whenDoMenuButton() {
		commandsComboBox.setSelectedItem("{<when, p>,<do, a>}");
		commandMenusComboBox.setSelectedItem("Acting");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdWhenDo(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to {<if, p>,<do, a>}
	 */
	public void ifDoMenuButton() {
		commandsComboBox.setSelectedItem("{<if, p>,<do, a>}");
		commandMenusComboBox.setSelectedItem("Acting");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdIfDo(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to attach-primaction
	 */
	public void definePrimactionMenuButton() {
		commandsComboBox.setSelectedItem("attach-primaction");
		commandMenusComboBox.setSelectedItem("Primitive Acts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdAttachPrimaction(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to define-primaction
	 */
	public void attachPrimactionMenuButton() {
		commandsComboBox.setSelectedItem("define-primaction");
		commandMenusComboBox.setSelectedItem("Primitive Acts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDefinePrimaction(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to believe
	 */
	public void believeMenuButton() {
		commandsComboBox.setSelectedItem("believe");
		commandMenusComboBox.setSelectedItem("Mental Acts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdBelieve(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to disbelive
	 */
	public void disbeliveMenuButton() {
		commandsComboBox.setSelectedItem("disbelive");
		commandMenusComboBox.setSelectedItem("Mental Acts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDisbelieve(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to do-one
	 */
	public void doAllMenuButton() {
		commandsComboBox.setSelectedItem("do-one");
		commandMenusComboBox.setSelectedItem("Control Acts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDoAll(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to do-all
	 */
	public void doOneMenuButton() {
		commandsComboBox.setSelectedItem("do-all");
		commandMenusComboBox.setSelectedItem("Control Acts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdDoOne(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to snsequence
	 */
	public void snsequenceMenuButton() {
		commandsComboBox.setSelectedItem("snsequence");
		commandMenusComboBox.setSelectedItem("Control Acts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdSNSequence(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to snif
	 */
	public void snifMenuButton() {
		commandsComboBox.setSelectedItem("snif");
		commandMenusComboBox.setSelectedItem("Control Acts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdSnif(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to sniterate
	 */
	public void sniterateMenuButton() {
		commandsComboBox.setSelectedItem("sniterate");
		commandMenusComboBox.setSelectedItem("Control Acts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdSniterate(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to withall
	 */
	public void withallMenuButton() {
		commandsComboBox.setSelectedItem("withall");
		commandMenusComboBox.setSelectedItem("Control Acts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdWithAll(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to withsome
	 */
	public void withsomeMenuButton() {
		commandsComboBox.setSelectedItem("withsome");
		commandMenusComboBox.setSelectedItem("Control Acts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdWithSome(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to {<plan, p>,<act, a>}
	 */
	public void actPlanMenuButton() {
		commandsComboBox.setSelectedItem("{<plan, p>,<act, a>}");
		commandMenusComboBox.setSelectedItem("Complex Acts");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdActPlan(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to achieve
	 */
	public void achieveMenuButton() {
		commandsComboBox.setSelectedItem("achieve");
		commandMenusComboBox.setSelectedItem("Goals");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdAchieve(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to {<act, a>,<precondition, p>}
	 */
	public void actPreconditionMenuButton() {
		commandsComboBox.setSelectedItem("{<act, a>,<precondition, p>}");
		commandMenusComboBox.setSelectedItem("Preconditions and Effects");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdActPrecondition(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Sets the command panel to {<act, a>,<effect, p>}
	 */
	public void actEffectMenuButton() {
		commandsComboBox.setSelectedItem("{<act, a>,<effect, p>}");
		commandMenusComboBox.setSelectedItem("Preconditions and Effects");
		commandsPanel.removeAll();
		commandsPanel.add(new cmdActEffect(network, frame));
		this.repaint();
		this.validate();
	}
	
	/**
	 * Displays all the information of the given nodes in the LinkedList
	 * @param nodes the LinkedList of nodes for which the information of each node will be displayed
	 */
	public void nodeInfo(LinkedList<Node> nodes) {
		LinkedList<Node> connectedNodes = new LinkedList<Node>();
		
		for(Node node : nodes) {
			/*Print out node name*/
			frame.getOutputPanel1().writeToTextArea("Node Name: " + node.getIdentifier() + newLine);
			
			/*Print out semantic class*/
			node.getEntity();
			
			/*Print out cable*/
			if(node instanceof MolecularNode) {
				MolecularNode molNode = (MolecularNode) node;
				LinkedList<Cable> cables = molNode.getCableSet().getCables();
				
				if(!cables.isEmpty()) {
					frame.getOutputPanel1().writeToTextArea("Cable Set (Nodes pointing from " + node.getIdentifier() + "):" + newLine);
					for(Cable item1 : cables) {
						item1.getRelation();
						LinkedList<Node> cableNodes = item1.getNodeSet().getNodes();
						
						for(Node item2 : cableNodes) {
							frame.getOutputPanel1().writeToTextArea(item2.getIdentifier() + newLine);
							connectedNodes.add(item2);
						}
					}
				}
			}
			
			/*Print out up cable*/
			LinkedList<UpCable> nodeUpCables = node.getUpCableSet().getUpCables();
			
			if(!nodeUpCables.isEmpty()) {
				frame.getOutputPanel1().writeToTextArea("Up Cable Set (Nodes pointing to " + node.getIdentifier() + "): " + newLine);
				for(UpCable item3 : nodeUpCables) {
					item3.getRelation();
					LinkedList<Node> upCableNodes = item3.getNodeSet().getNodes();
					
					for(Node item4 : upCableNodes) {
						frame.getOutputPanel1().writeToTextArea(item4.getIdentifier() + newLine);
						connectedNodes.add(item4);
					}
				}
			}
			frame.getOutputPanel1().writeToTextArea(endLine);
		}
		
		frame.getNodesResult().addNodes(connectedNodes);
	}
	
	public void nodeInfo(LinkedList<Node> nodes, LinkedList<LinkedList<Support>> supports) {
		LinkedList<Node> connectedNodes = new LinkedList<Node>();
		for(int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			LinkedList<Support> support = supports.get(i);
			
			/*Print out node name*/
			frame.getOutputPanel1().writeToTextArea("Node Name: " + node.getIdentifier() + newLine);
			
			/*Print out semantic class*/
			node.getEntity();
			
			/*Print out cable*/
			if(node instanceof MolecularNode) {
				MolecularNode molNode = (MolecularNode) node;
				LinkedList<Cable> cables = molNode.getCableSet().getCables();
				
				if(!cables.isEmpty()) {
					frame.getOutputPanel1().writeToTextArea("Cable Set (Nodes pointing from " + node.getIdentifier() + "):" + newLine);
					for(Cable item1 : cables) {
						item1.getRelation();
						LinkedList<Node> cableNodes = item1.getNodeSet().getNodes();
						
						for(Node item2 : cableNodes) {
							frame.getOutputPanel1().writeToTextArea(item2.getIdentifier() + newLine);
							connectedNodes.add(item2);
						}
					}
				}
			}
			
			/*Print out up cable*/
			LinkedList<UpCable> nodeUpCables = node.getUpCableSet().getUpCables();
			
			if(!nodeUpCables.isEmpty()) {
				frame.getOutputPanel1().writeToTextArea("Up Cable Set (Nodes pointing to " + node.getIdentifier() + "): " + newLine);
				for(UpCable item3 : nodeUpCables) {
					item3.getRelation();
					LinkedList<Node> upCableNodes = item3.getNodeSet().getNodes();
					
					for(Node item4 : upCableNodes) {
						frame.getOutputPanel1().writeToTextArea(item4.getIdentifier() + newLine);
						connectedNodes.add(item4);
					}
				}
			}
			
			for (Support s : support) {
				frame.getOutputPanel1().writeToTextArea("Support Type: " + s.getSupporttype() + newLine);
				Set<Proposition> ps = s.getOrginSet().getPropositionSet();
				Iterator<Proposition> psItr = ps.iterator();
				Proposition proposition;
				while(psItr.hasNext()){
					proposition = psItr.next();
				}
				s.getRestrictionSet();
				s.getRestrictionSetsSize();
			}
			
			frame.getOutputPanel1().writeToTextArea(endLine);
		}
		
		frame.getNodesResult().addNodes(connectedNodes);
	}
	
	/**
	 * Writes the information of the given node in the OutputPanel
	 * @param node the node to print its corresponding information
	 */
	public void nodeInfo(Node node) {
		LinkedList<Node> connectedNodes = new LinkedList<Node>();
		
		/*Print out node name*/
		frame.getOutputPanel1().writeToTextArea("Node Name: " + node.getIdentifier() + newLine);
		
		/*Print out semantic class*/
		frame.getOutputPanel1().writeToTextArea("Semantic Class: "+ node.getEntity().getClass().getSimpleName() + newLine);
		
		/*Print out cable*/
		if(node instanceof MolecularNode) {
			MolecularNode molNode = (MolecularNode) node;
			LinkedList<Cable> cables = molNode.getCableSet().getCables();
			
			if(!cables.isEmpty()) {
				frame.getOutputPanel1().writeToTextArea("Cable Set (Nodes pointing from " + node.getIdentifier() + "):" + newLine);
				for(Cable item1 : cables) {
					item1.getRelation();
					LinkedList<Node> cableNodes = item1.getNodeSet().getNodes();
					
					for(Node item2 : cableNodes) {
						frame.getOutputPanel1().writeToTextArea(item2.getIdentifier() + newLine);
						connectedNodes.add(item2);
					}
				}
			}
		}
		
		/*Print out up cable*/
		LinkedList<UpCable> nodeUpCables = node.getUpCableSet().getUpCables();
		
		if(!nodeUpCables.isEmpty()) {
			frame.getOutputPanel1().writeToTextArea("Up Cable Set (Nodes pointing to " + node.getIdentifier() + "): " + newLine);
			for(UpCable item3 : nodeUpCables) {
				item3.getRelation();
				LinkedList<Node> upCableNodes = item3.getNodeSet().getNodes();
				
				for(Node item4 : upCableNodes) {
					frame.getOutputPanel1().writeToTextArea(item4.getIdentifier() + newLine);
					connectedNodes.add(item4);
				}
			}
		}
		
		frame.getOutputPanel1().writeToTextArea(endLine);
		frame.getNodesResult().addNodes(connectedNodes);
	}
	
	/**
	 * Checks the consistency of the semantic classes of the given nodes with each corresponding
	 * relation
	 * @param relations the LinkedList of relations to be checked for consistency
	 * @param nodes the LinkeList of nodes to be checked for consistency
	 * @return true if the semantic classes of the nodes and relations are consistent and false
	 * otherwise
	 */
	public boolean checkConsistency(LinkedList<Relation> relations, LinkedList<Object> nodes) {
		int consistencyCounter = 0;
		
		for(int i = 0 ; i < relations.size(); i++) {
			Relation relation = relations.get(i);
			Object object = nodes.get(i);
			
			if(object instanceof Node) {
				Node node = (Node) object;
				
				Entity entity = node.getEntity();
				System.out.println("Node Semantic Type: " + entity.getClass().getSimpleName());
				String semanticType = relation.getType();
				System.out.println("Relation Semantic Type: " + semanticType);
				
				if (semanticType.equals("Entity")) {
					if (entity instanceof Entity) {
						consistencyCounter++;
						System.out.println(entity.getClass().getSimpleName() + "instanceof Entity");
					}
				} else if (semanticType.equals("Proposition")) {
					if (entity instanceof Proposition) {
						consistencyCounter++;
						System.out.println(entity.getClass().getSimpleName() + "instanceof Proposition");
					}
				} else if (semanticType.equals("Individual")) {
					if(entity instanceof Individual) {
						consistencyCounter++;
						System.out.println(entity.getClass().getSimpleName() + "instanceof Individual");
					}
				} else if (semanticType.equals("AndEntailment")) {
					if (entity instanceof AndEntailment) {
						consistencyCounter++;
						System.out.println(entity.getClass().getSimpleName() + "instanceof AndEntailment");
					}
				} else if (semanticType.equals("AndOr")) {
					if (entity instanceof AndOr) {
						consistencyCounter++;
						System.out.println(entity.getClass().getSimpleName() + "instanceof AndOr");
					}
				} else if (semanticType.equals("NumericalEntailment")) {
					if (entity instanceof NumericalEntailment) {
						consistencyCounter++;
						System.out.println(entity.getClass().getSimpleName() + "instanceof NumericalEntailment");
					}
				} else if (semanticType.equals("OrEntailment")) {
					if (entity instanceof OrEntailment) {
						consistencyCounter++;
						System.out.println(entity.getClass().getSimpleName() + "instanceof OrEntailment");
					}
				} else if (semanticType.equals("Thresh")) {
					if (entity instanceof Thresh) {
						consistencyCounter++;
						System.out.println(entity.getClass().getSimpleName() + "instanceof Thresh");
					}
				} else if (semanticType.equals("Act")) {
					if (entity instanceof Act) {
						consistencyCounter++;
						System.out.println(entity.getClass().getSimpleName() + "instanceof Act");
					}
				} else if (semanticType.equals("Action")) {
					if (entity instanceof Action) {
						consistencyCounter++;
						System.out.println(entity.getClass().getSimpleName() + "instanceof Action");
					}
				}
			} else if (object instanceof NodeSet) {
				consistencyCounter++;
			}
		}
		
		System.out.println("Consistency Counter: " + consistencyCounter);
		System.out.println("Relation Size: " + relations.size());
		
		if(consistencyCounter == relations.size()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Converts a SNePS path to a string for display purposes 
	 * @param path the path to be converted to a string
	 * @return the created path string
	 */
	public String createPath(Path path) {
		String pathString = "";
		System.out.println("Path Type: " + path.getClass().getSimpleName());
		
		/*And*/
		if(path instanceof AndPath) {
			AndPath currentPath = (AndPath) path;
			LinkedList<Path> paths = currentPath.getPaths();
			
			System.out.println("Number of paths: " + paths.size());
			
			pathString = "(and ";
			
			for (int i = 0; i < paths.size(); i++) {				
				pathString += createPath(paths.get(i)) + " ";
			}
			
			pathString += ")";
		/*Compose*/	
		} else if (path instanceof ComposePath) {
			ComposePath currentPath = (ComposePath) path;
			LinkedList<Path> paths = currentPath.getPaths();
			
			System.out.println("Number of paths: " + paths.size());
			
			pathString = "(compose ";
			
			for (int i = 0; i < paths.size(); i++) {
				pathString += createPath(paths.get(i)) + " ";
			}
			
			pathString += ")";
		/*Converse*/
		} else if (path instanceof ConversePath) {
			ConversePath currentPath = (ConversePath) path;
			pathString =  "(converse " + createPath(currentPath.getPath()) + ")";
		/*DomainRestrict*/
		} else if (path instanceof DomainRestrictPath) {
			DomainRestrictPath currentPath = (DomainRestrictPath) path;
			pathString =  "(domain-restrict (" + createPath(currentPath.getQ()) + " " +  currentPath.getNode().getIdentifier() 
				+ ") " + createPath(currentPath.getP()) + ")";
		/*IrreflexiveRestrict*/
		} else if (path instanceof IrreflexiveRestrictPath) {
			IrreflexiveRestrictPath currentPath = (IrreflexiveRestrictPath) path;
			pathString =  "(irreflexive-restrict " + createPath(currentPath.getPath()) + ")";
		/*KPlus*/
		} else if (path instanceof KPlusPath) {
			KPlusPath currentPath = (KPlusPath) path;
			pathString =  "(kplus " + createPath(currentPath.getPath()) + ")";
		/*KStar*/
		} else if (path instanceof KStarPath) {
			KStarPath currentPath = (KStarPath) path;
			pathString =  "(kstar " + createPath(currentPath.getPath()) + ")";
		/*Or*/
		} else if (path instanceof OrPath) {
			OrPath currentPath = (OrPath) path;
			LinkedList<Path> paths = currentPath.getPaths();
			pathString = "(or ";
			
			System.out.println("Number of paths: " + paths.size());
			
			for (int i = 0; i < paths.size(); i++) {
				pathString += createPath(paths.get(i)) + " ";
			}
			
			pathString += ")";
		/*RangeRestrict*/
		} else if (path instanceof RangeRestrictPath) {
			RangeRestrictPath currentPath = (RangeRestrictPath) path;
			pathString =  "(range-restrict " + createPath(currentPath.getP()) + "(" + createPath(currentPath.getQ()) + " "
				+ currentPath.getNode().getIdentifier() + "))";
		/*RelativeComplement*/
		} else if (path instanceof RelativeComplementPath) {
			RelativeComplementPath currentPath = (RelativeComplementPath) path;
			pathString =  "(relative-complement " + createPath(currentPath.getP()) + " " + createPath(currentPath.getQ()) + ")";
		/*FUnitPath*/	
		} else if (path instanceof FUnitPath) {
			FUnitPath currentPath = (FUnitPath) path;
			pathString =  currentPath.getRelationName();
		/*BUnitPath*/
		} else if (path instanceof BUnitPath) {
			BUnitPath currentPath = (BUnitPath) path;
			pathString =  currentPath.getRelationName() + "-";
		} else if (path instanceof BangPath) {
			BangPath currentPath = (BangPath) path;
			pathString = "!";
		}
		
		System.out.println(pathString);
		
		return pathString;
	}
	
	/**
	 * Cascades the nested windows so they will not appear at the same position
	 * @return the point where the location of the nested window will be set to
	 */
	public Point cascadePosition() {
		double x = point.getX() + 5;
		double y = point.getY() + 10;
		point.setLocation(x, y);
		return point;
	}
	
	/**
	 * Cascades the window position of the nested windows to one position
	 */
	public void cascadeBack() {
		double x = point.getX() - 5;
		double y = point.getY() - 10;
		point.setLocation(x, y);
	}
	
	/**
	 * Gets the current Network
	 * @return the current Network
	 */
	public Network getNetwork() {
		return network;
	}

	/**
	 * Sets the current Network to the given Network
	 * @param network the network to be set as the current Network
	 */
	public void setNetwork(Network network) {
		this.network = network;
	}
}