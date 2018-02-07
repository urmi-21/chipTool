/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiptool;

import javax.swing.JFrame;
import org.math.plot.Plot2DPanel;

/**
 *
 * @author jnu
 */
public class Histogramplots {

    public Histogramplots() {

    }

    public void doplot() {

        // define your data
        double[] x = new double[10];

        for (int i = 0; i < x.length; i++) {
            x[i] = i;
        }

        // create your PlotPanel (you can use it as a JPanel)
        Plot2DPanel plot = new Plot2DPanel();

        // add the histogram (50 slices) of x to the PlotPanel
        plot.addHistogramPlot("Log Normal population", x, 5);

        // put the PlotPanel in a JFrame like a JPanel
        JFrame frame = new JFrame("a plot panel");
        frame.setSize(600, 600);
        frame.setContentPane(plot);
        frame.setVisible(true);

    }
}
