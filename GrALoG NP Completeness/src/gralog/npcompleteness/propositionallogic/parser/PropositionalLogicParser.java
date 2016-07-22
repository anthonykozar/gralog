
//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Mon Jul 18 14:53:47 CEST 2016
//----------------------------------------------------

package gralog.npcompleteness.propositionallogic.parser;

import java_cup.runtime.*;
import gralog.npcompleteness.propositionallogic.formula.*;
import java.io.ByteArrayInputStream;
import java.util.Vector;

/** CUP v0.11a beta 20060608 generated parser.
  * @version Mon Jul 18 14:53:47 CEST 2016
  */
public class PropositionalLogicParser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public PropositionalLogicParser() {super();}

  /** Constructor which sets the default scanner. */
  public PropositionalLogicParser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public PropositionalLogicParser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\011\000\002\002\003\000\002\002\004\000\002\003" +
    "\005\000\002\003\003\000\002\004\005\000\002\004\003" +
    "\000\002\005\004\000\002\005\005\000\002\005\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\020\000\010\004\010\006\006\011\004\001\002\000" +
    "\012\002\ufff9\005\ufff9\007\ufff9\010\ufff9\001\002\000\012" +
    "\002\ufffc\005\ufffc\007\021\010\ufffc\001\002\000\010\004" +
    "\010\006\006\011\004\001\002\000\010\002\ufffe\005\ufffe" +
    "\010\016\001\002\000\010\004\010\006\006\011\004\001" +
    "\002\000\006\002\001\005\001\001\002\000\004\002\013" +
    "\001\002\000\004\002\000\001\002\000\004\005\015\001" +
    "\002\000\012\002\ufffa\005\ufffa\007\ufffa\010\ufffa\001\002" +
    "\000\010\004\010\006\006\011\004\001\002\000\006\002" +
    "\uffff\005\uffff\001\002\000\012\002\ufffb\005\ufffb\007\ufffb" +
    "\010\ufffb\001\002\000\010\004\010\006\006\011\004\001" +
    "\002\000\010\002\ufffd\005\ufffd\010\ufffd\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\020\000\012\002\011\003\010\004\006\005\004\001" +
    "\001\000\002\001\001\000\002\001\001\000\004\005\017" +
    "\001\001\000\002\001\001\000\012\002\013\003\010\004" +
    "\006\005\004\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\010\003\016\004\006\005\004\001\001\000\002\001\001" +
    "\000\002\001\001\000\006\004\021\005\004\001\001\000" +
    "\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$PropositionalLogicParser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$PropositionalLogicParser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$PropositionalLogicParser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}




    String errorMsg = null;

    public Boolean hasError()
    {
        return errorMsg == null;
    }
    
    public String getErrorMsg()
    {
        if(errorMsg == null)
            return "no error";
        else
            return errorMsg;
    }
    
    public void syntax_error(Symbol cur_token)
    {
        if(errorMsg == null)
            errorMsg = "Syntax Error: " + cur_token.toString();
    }

    public void report_fatal_error(String message, Object info) throws Exception
    {
        throw new Exception("Fatal parsing error: " + message + "\n" + info.toString());
    }

    public PropositionalLogicFormula parseString(String str) throws Exception
    {
        String charset = "UTF8";
        byte[] bytes = str.getBytes(charset);
        ByteArrayInputStream stringstream = new ByteArrayInputStream(bytes);
                
        DefaultSymbolFactory symbolfactory = new DefaultSymbolFactory();
        PropositionalLogicScanner scanner = new PropositionalLogicScanner(stringstream, symbolfactory);
        this.setScanner(scanner);
        
        Symbol parserresult = this.parse();
        return (PropositionalLogicFormula) parserresult.value;
    }


}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$PropositionalLogicParser$actions {
  private final PropositionalLogicParser parser;

  /** Constructor */
  CUP$PropositionalLogicParser$actions(PropositionalLogicParser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$PropositionalLogicParser$do_action(
    int                        CUP$PropositionalLogicParser$act_num,
    java_cup.runtime.lr_parser CUP$PropositionalLogicParser$parser,
    java.util.Stack            CUP$PropositionalLogicParser$stack,
    int                        CUP$PropositionalLogicParser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$PropositionalLogicParser$result;

      /* select the action based on the action number */
      switch (CUP$PropositionalLogicParser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // atomicformula ::= STRING 
            {
              PropositionalLogicFormula RESULT =null;
		int varleft = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()).left;
		int varright = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()).right;
		String var = (String)((java_cup.runtime.Symbol) CUP$PropositionalLogicParser$stack.peek()).value;
		 RESULT = new PropositionalLogicVariable(var); 
              CUP$PropositionalLogicParser$result = parser.getSymbolFactory().newSymbol("atomicformula",3, ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()), RESULT);
            }
          return CUP$PropositionalLogicParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // atomicformula ::= OPEN formula CLOSE 
            {
              PropositionalLogicFormula RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-1)).left;
		int fright = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-1)).right;
		PropositionalLogicFormula f = (PropositionalLogicFormula)((java_cup.runtime.Symbol) CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-1)).value;
		 RESULT = f; 
              CUP$PropositionalLogicParser$result = parser.getSymbolFactory().newSymbol("atomicformula",3, ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-2)), ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()), RESULT);
            }
          return CUP$PropositionalLogicParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // atomicformula ::= NEG atomicformula 
            {
              PropositionalLogicFormula RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()).left;
		int fright = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()).right;
		PropositionalLogicFormula f = (PropositionalLogicFormula)((java_cup.runtime.Symbol) CUP$PropositionalLogicParser$stack.peek()).value;
		 RESULT = new PropositionalLogicNot(f); 
              CUP$PropositionalLogicParser$result = parser.getSymbolFactory().newSymbol("atomicformula",3, ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-1)), ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()), RESULT);
            }
          return CUP$PropositionalLogicParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // wedgeformula ::= atomicformula 
            {
              PropositionalLogicFormula RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()).left;
		int fright = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()).right;
		PropositionalLogicFormula f = (PropositionalLogicFormula)((java_cup.runtime.Symbol) CUP$PropositionalLogicParser$stack.peek()).value;
		 RESULT = f; 
              CUP$PropositionalLogicParser$result = parser.getSymbolFactory().newSymbol("wedgeformula",2, ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()), RESULT);
            }
          return CUP$PropositionalLogicParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // wedgeformula ::= atomicformula AND wedgeformula 
            {
              PropositionalLogicFormula RESULT =null;
		int leftleft = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-2)).left;
		int leftright = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-2)).right;
		PropositionalLogicFormula left = (PropositionalLogicFormula)((java_cup.runtime.Symbol) CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-2)).value;
		int rightleft = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()).left;
		int rightright = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()).right;
		PropositionalLogicFormula right = (PropositionalLogicFormula)((java_cup.runtime.Symbol) CUP$PropositionalLogicParser$stack.peek()).value;
		 RESULT = new PropositionalLogicAnd(left, right); 
              CUP$PropositionalLogicParser$result = parser.getSymbolFactory().newSymbol("wedgeformula",2, ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-2)), ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()), RESULT);
            }
          return CUP$PropositionalLogicParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // veeformula ::= wedgeformula 
            {
              PropositionalLogicFormula RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()).left;
		int fright = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()).right;
		PropositionalLogicFormula f = (PropositionalLogicFormula)((java_cup.runtime.Symbol) CUP$PropositionalLogicParser$stack.peek()).value;
		 RESULT = f; 
              CUP$PropositionalLogicParser$result = parser.getSymbolFactory().newSymbol("veeformula",1, ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()), RESULT);
            }
          return CUP$PropositionalLogicParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // veeformula ::= wedgeformula OR veeformula 
            {
              PropositionalLogicFormula RESULT =null;
		int leftleft = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-2)).left;
		int leftright = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-2)).right;
		PropositionalLogicFormula left = (PropositionalLogicFormula)((java_cup.runtime.Symbol) CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-2)).value;
		int rightleft = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()).left;
		int rightright = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()).right;
		PropositionalLogicFormula right = (PropositionalLogicFormula)((java_cup.runtime.Symbol) CUP$PropositionalLogicParser$stack.peek()).value;
		 RESULT = new PropositionalLogicOr(left, right); 
              CUP$PropositionalLogicParser$result = parser.getSymbolFactory().newSymbol("veeformula",1, ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-2)), ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()), RESULT);
            }
          return CUP$PropositionalLogicParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= formula EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-1)).right;
		PropositionalLogicFormula start_val = (PropositionalLogicFormula)((java_cup.runtime.Symbol) CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-1)).value;
		RESULT = start_val;
              CUP$PropositionalLogicParser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.elementAt(CUP$PropositionalLogicParser$top-1)), ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$PropositionalLogicParser$parser.done_parsing();
          return CUP$PropositionalLogicParser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // formula ::= veeformula 
            {
              PropositionalLogicFormula RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()).left;
		int fright = ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()).right;
		PropositionalLogicFormula f = (PropositionalLogicFormula)((java_cup.runtime.Symbol) CUP$PropositionalLogicParser$stack.peek()).value;
		 RESULT = f; 
              CUP$PropositionalLogicParser$result = parser.getSymbolFactory().newSymbol("formula",0, ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()), ((java_cup.runtime.Symbol)CUP$PropositionalLogicParser$stack.peek()), RESULT);
            }
          return CUP$PropositionalLogicParser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

