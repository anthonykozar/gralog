/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gralog.gralogfx;


import gralog.structure.Structure;
import gralog.algorithm.Algorithm;
import gralog.algorithm.AlgorithmParameters;
import gralog.progresshandler.ProgressHandler;

/**
 *
 * @author viktor
 */
public class AlgorithmThread extends Thread {
    
    Algorithm algo;
    AlgorithmParameters params;
    Structure structure;
    ProgressHandler onprogress;
    
    ThreadCompleteEvent handler = null;
    Exception exception = null;
    Object result = null;
    
    public AlgorithmThread(Algorithm algo, Structure structure, AlgorithmParameters params, ProgressHandler onprogress) {
        setDaemon(true);
        setName(algo.getClass().getName() + " Thread");
        
        this.algo = algo;
        this.params = params;
        this.structure = structure;
        this.onprogress = onprogress;
    }
 
    public void setOnThreadCompete(ThreadCompleteEvent handler) {
        this.handler = handler;
    }
    
    @Override
    public void run() {

        try {
            result = this.algo.DoRun(structure, params, onprogress);
        } catch(Exception ex) {
            this.exception = ex;
        }
        if(handler != null)
            handler.OnThreadCompleted(this);
        
        /*
        while (!this.isInterrupted()) {
             
            // UI updaten
                Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // entsprechende UI Komponente updaten
                    list.getItems().add(0, getName() + " sagt Hallo!");
                }
            });
            
            // Thread schlafen
            try {
                // fuer 3 Sekunden
                sleep(TimeUnit.SECONDS.toMillis(3));
            } catch (InterruptedException ex) {
            }
        }
        */
    }
 
}