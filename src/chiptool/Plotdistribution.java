/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiptool;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.math.plot.Plot2DPanel;

/**
 *
 * @author jnu
 */
public class Plotdistribution extends Thread {

    int[] chipdata = null;

    public Plotdistribution(int[] ch) {
        chipdata = ch;
    }

    public void run() {
        JFrame frame = new JFrame("Distance Distribution plot panel");
        double[] distance_array = new double[(chipdata.length / 2) - 1];

        int ctr = 0;
        for (int i = 1; i < chipdata.length - 1; i = i + 2) {
            //System.out.println(chipdata[i+1] +"-"+chipdata[i]);
            distance_array[ctr++] = chipdata[i + 1] - chipdata[i];
            //System.out.println(distance_array[ctr-1]);
        }
        
        histogram(distance_array,frame);
        //Histogramplots ob=new Histogramplots();
        //ob.doplot();
        
        
        

    }

    public void histogram(double[] x,JFrame frame) {
        // plot histogram
        // create your PlotPanel (you can use it as a JPanel)
        Plot2DPanel plot = new Plot2DPanel();

        // add the histogram (50 slices) of x to the PlotPanel
        plot.addHistogramPlot("Distance", x, 50);
        

        // put the PlotPanel in a JFrame like a JPanel
        
        frame.setSize(600, 600);
        frame.setContentPane(plot);
        frame.setVisible(true);
        //frame.setAlwaysOnTop(true);
        

    }

}
