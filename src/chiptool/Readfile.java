/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiptool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author jnu
 */
public class Readfile {

    public int[] anndata = null;
    public int[] chipdata = null;
    public char[] genomedata = null;
    String gfile;
    String annfile;
    String chipfile;
    static private char[] sequence;
    String fileinfo = null;
    String filedata;
    int numlines;

    public Readfile(String g, String a, String c) {
        gfile = g;
        annfile = a;
        chipfile = c;
        genomedata = newread(gfile);
        anndata = read(a);
        chipdata = read(c);

    }

    public int[] read(String path) {
        int[] data = null;
        int size = 0;
        BufferedReader br = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(path));

            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                //data[size]=10;
                size++;
                //System.out.print(data[size-1]);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            data = new int[size * 2];
            size = 0;

            try {

                String sCurrentLine;

                br = new BufferedReader(new FileReader(path));

                while ((sCurrentLine = br.readLine()) != null) {
                    //System.out.println(Integer.parseInt(sCurrentLine));
                    String[] res = new String[2];
                    res = sCurrentLine.split("\t");
                    //store start pos
                    data[size] = Integer.parseInt(res[0]);
                    size++;
                    //store endposition

                    data[size] = Integer.parseInt(res[1]);
                    size++;
                    /*now data contains
                     startpos1
                     endpos1
                     startpos2
                     endpos2
                     ...                       
                     */
                    //System.out.println(size);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return sort(data);
            }
        }
    }

    //Function to sort annotation and chip data files in non decreasing order
    public int[] sort(int[] data) {
        int[] sorted = new int[data.length];
        int tempstrt, tempend;
        for (int i = 0; i < data.length; i = i + 2) {
            for (int j = i + 2; j < data.length; j = j + 2) {
                if (data[i] > data[j]) {
                    //swap start pos
                    tempstrt = data[i];
                    data[i] = data[j];
                    data[j] = tempstrt;
                    //swwap end pos
                    tempend = data[i + 1];
                    data[i + 1] = data[j + 1];
                    data[j + 1] = tempend;
                }

            }
        }

        return data;
    }

    //function to read fasta file
    public char[] newread(String filepath) {

        int x;
        char ch;
        numlines = 0;
        BufferedReader br = null;
        BufferedReader cr = null;
        /*stringBuilderdisp for storing data with new lines to display and stringBuilder
         seq for ignoring newlines and getting only sequence chars*/
        StringBuilder stringBuilderdisp = new StringBuilder();
        StringBuilder stringBuilderseq = new StringBuilder();
        String ls = System.getProperty("line.separator");

        int f = 0;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(filepath));

            while ((sCurrentLine = br.readLine()) != null) {
                if (f == 0 && sCurrentLine.contains(">")) {
                    fileinfo = sCurrentLine;
                    f = 1;
                } else {
                    stringBuilderdisp.append(sCurrentLine);
                    numlines++;
                    if (!(sCurrentLine.isEmpty())) {
                        stringBuilderdisp.append(ls);
                    }

                    stringBuilderseq.append(sCurrentLine);

                }

            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR File not found");
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "ERROR File not found");
                //ex.printStackTrace();
                return null;

            }
        }

        //System.out.println("Total lines=" + numlines);

        String seqstr = stringBuilderseq.toString();

        sequence = new char[seqstr.length()];
        //extra charflag to indicate that sequence contains charecter other than A,G,C,T
        boolean extracharflag = false, checkindex = false;

        for (int i = 0; i < sequence.length; i++) {
            if (seqstr.charAt(i) != '\n') {
                sequence[i] = seqstr.charAt(i);
            }
            if (extracharflag == false) {
                if ((sequence[i] != 'A') && (sequence[i] != 'T') && (sequence[i] != 'G') && (sequence[i] != 'C')) {//||sequence[i]!='C'||sequence[i]!='G'||sequence[i]!='T'){
                    extracharflag = true;
                    System.out.print("** " + sequence[i]);
                }
            }
        }

        if (extracharflag) {
            // JOptionPane.showMessageDialog(null, "Sequence Contains Characters other than A G C T");
        }

        int index = 0, flag = 0;

       // JOptionPane.showMessageDialog(null, "Read Successful");
        //return the sequence with newline properties to display
        //return stringBuilderdisp.toString().toCharArray();
        return sequence;

    }

}
