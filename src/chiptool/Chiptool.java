/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiptool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jnu
 */
public class Chiptool {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*Readfile ob=new Readfile("NC_001264.fna","annot_chr10.txt" , "chr1high.txt");
        
         System.out.println(ob.anndata);
        
         Browser newob=new Browser(ob.genomedata,ob.anndata,ob.chipdata);
         */
       // Mainwindow winob=new Mainwindow();

        //winob.setVisible(true);
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                new Mainwindow().callmain();

            }
        });

    }

}
