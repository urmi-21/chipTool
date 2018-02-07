/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiptool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author jnu
 */
public class Extractchippos extends Thread {

    //arrays for data
    char[] genome;
    int[] chip;
    int[] ann;
    //nc flag to extract non-coding if true else coding
    boolean ncflag;

    public Extractchippos(char[] g, int[] a, int[] c, boolean nc) {
        genome = g;
        chip = c;
        ann = a;
        ncflag = nc;

    }

    public void run() {
        
                
        //frame to display results
        JFrame frame = new JFrame();
        frame.setSize(800, 500);
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Serif", Font.PLAIN, 11));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane sp = new JScrollPane(textArea);
        JPanel buttonPanel_bottom = new JPanel();
        JButton extractchipseq = new JButton("Extract Seq");
        buttonPanel_bottom.add(extractchipseq);

        int[] chippos = null;
        if (ncflag == false) {
            chippos = returnchippos();
        } else {
            int notinflag = 0;
            int[] tempchippos = null;
            ArrayList<Integer> list = new ArrayList<Integer>();
            tempchippos = returnchippos();

            for (int i = 0; i < chip.length - 1; i = i + 2) {
                notinflag = 1;
                for (int j = 0; j < tempchippos.length - 1; j = j + 2) {
                    if (tempchippos[j] == chip[i] && tempchippos[j + 1] == chip[i + 1]) {
                        notinflag = 0;
                        break;
                    }
                    if (tempchippos[j] != chip[i] && tempchippos[j + 1] != chip[i + 1]) {
                        //break;
                    }

                }

                if (notinflag != 0) {
                    list.add(chip[i]);
                    list.add(chip[i + 1]);
                }

            }

            chippos = new int[list.size()];
            for (int i = 0; i < chippos.length; i++) {
                chippos[i] = list.get(i);
                //System.out.println(chippos[i]);
            }
        }

        String temp = null;
        String tempseq = "";
        

        for (int i = 0; i < chippos.length - 1; i = i + 2) {
            temp = String.valueOf(">"+chippos[i]) + "\t" + String.valueOf(chippos[i + 1]);
            
            for (int j = chippos[i]; j <= chippos[i + 1]; j++) {
                if (genome[j] == 'A' || genome[j] == 'G'||genome[j] == 'C'||genome[j] == 'T') {
                    tempseq += String.valueOf(genome[j]);
                    
                }
                
                
            }
            
            textArea.append(temp + "\n"+tempseq +"\n");
            tempseq = "";
        }
        frame.add(sp);
        frame.add(buttonPanel_bottom, BorderLayout.SOUTH);
        frame.setVisible(true);
        final String texttowrite=textArea.getText();

        //action for buttons
        extractchipseq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                BufferedWriter writer = null;
                String towrite = "";
                String temp = null;
                String tempseq = "";
                String fname = JOptionPane.showInputDialog("Enter a File name") + ".txt";
                File f = new File(fname);
                try {
                    writer = new BufferedWriter(new FileWriter(f));
                } catch (IOException ex) {
                    Logger.getLogger(Extractchippos.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {

                    towrite = texttowrite;
                    writer.write(towrite);
                    JOptionPane.showMessageDialog(null, fname + " File saved");
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(Extractchippos.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    int[] returnchippos() {
        int[] pos = null;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < chip.length - 1; i = i + 2) {
            //System.out.println(chip[i] + "\t" + chip[i + 1]);
            for (int j = 0; j < ann.length - 1; j = j + 2) {
                if (chip[i] >= ann[j] && chip[i] <= ann[j + 1]) {
                    list.add(chip[i]);
                    list.add(chip[i + 1]);

                    //System.out.println(ann[j] + ">" + chip[i] + ">" + chip[i + 1] + ">" + ann[j + 1]);
                    //System.out.println("adding");
                    //System.out.println(chip[i] + "   " + chip[i + 1]);
                    break;
                } else if (chip[i + 1] >= ann[j] && chip[i + 1] <= ann[j + 1]) {
                    if (chip[i] >= ann[j] && chip[i] <= ann[j + 1]) {
                        list.add(chip[i]);
                        list.add(chip[i + 1]);
                        //System.out.println(ann[j] + ">" + chip[i] + ">" + chip[i + 1] + ">" + ann[j + 1]);
                        //System.out.println("adding");
                        //System.out.println(chip[i] + "   " + chip[i + 1]);
                        break;
                    }
                }

            }
        }

        pos = new int[list.size()];
        for (int i = 0; i < pos.length; i++) {
            pos[i] = list.get(i);
            //System.out.println(pos[i]);
        }

        return pos;
    }

}
