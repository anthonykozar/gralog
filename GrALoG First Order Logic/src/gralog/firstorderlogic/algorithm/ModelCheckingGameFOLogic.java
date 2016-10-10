/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gralog.firstorderlogic.algorithm;

import gralog.firstorderlogic.logic.firstorder.formula.FirstOrderFormula;
import gralog.firstorderlogic.logic.firstorder.parser.FirstOrderParser;

import gralog.algorithm.*;

import gralog.structure.*;
import gralog.progresshandler.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Set;

import gralog.finitegame.structure.*;
/**
 *
 * @author Hv
 */
@AlgorithmDescription(
  name="Model Checking Game for first Order Logic",
  text="",
  url="https://en.wikipedia.org/wiki/Game_semantics#Classical_logic"
)
public class ModelCheckingGameFOLogic extends Algorithm{
    @Override
    public AlgorithmParameters GetParameters(Structure s) {
         File file=new File("PreviousSearch.txt");
            String str="";
           
            if (file.exists()){
            
            try {
                
                BufferedReader input = new BufferedReader(new FileReader(file));
                str=input.readLine();
            } catch (Exception ex) {
                    str= "ERROR" + ex.toString();
                }
            
            }
            else str="";
           
        return new StringAlgorithmParameter(str);
    }
  /* public void getUniqueGamePositions(FiniteGame game){
       Set<FiniteGamePosition> gp=game.getVertices();
       for(FiniteGamePosition v: (Set<FiniteGamePosition>)gp){
           for(FiniteGamePosition w: gp){
               if(v!=w){
                   if(v.Label.equals(w.Label)){
                       Set<Edge> connectedEdges=w.getConnectedEdges();
                       for(Edge e: connectedEdges){
                           if(e.getSource()!=w){
                               Vertex temp=e.getSource();
                               game.AddEdge(game.CreateEdge(temp,v) );
                           }
                       }
                   }
               }
           }
       }
   }
 */
   public Object Run(Structure s, AlgorithmParameters p,Set<Object> selection, ProgressHandler onprogress) throws Exception {
       StringAlgorithmParameter sp = (StringAlgorithmParameter)(p);
        
        FirstOrderParser parser = new FirstOrderParser();
        FirstOrderFormula phi = parser.parseString(sp.parameter);
        PrintWriter out = new PrintWriter(new BufferedWriter(
                                                 new FileWriter("PreviousSearch.txt", false)));    
            out.println(sp.parameter);  
            out.close();
         
        Set<Vertex> V=s.getVertices();
        int i=0;
        for(Vertex v : V ){
            v.Label=String.valueOf(i);
            i++;
        }
         HashMap<String, Vertex> varassign = new HashMap<>();
         FiniteGame gp=new FiniteGame();
        CoordinateClass ob=new CoordinateClass();
        ob.x=5.0;
        ob.y=5.0;
        
        FiniteGamePosition root= phi.ConstructGameGraph(s,varassign,gp,ob) ;
       

        return gp;
   }
}