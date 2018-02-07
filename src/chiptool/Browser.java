/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiptool;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import no.geosoft.cc.geometry.Geometry;
import no.geosoft.cc.graphics.GObject;
import no.geosoft.cc.graphics.GPosition;
import no.geosoft.cc.graphics.GScene;
import no.geosoft.cc.graphics.GSegment;
import no.geosoft.cc.graphics.GStyle;
import no.geosoft.cc.graphics.GText;
import no.geosoft.cc.graphics.GWindow;
import no.geosoft.cc.graphics.ZoomInteraction;

/**
 *
 * @author jnu
 */
public class Browser extends JFrame implements ActionListener {

    private GScene scene;
    private JButton next = new JButton("NEXT");
    private JButton prev = new JButton("PREV.");
    private JCheckBox gene_annot = new JCheckBox("gene annotation");
    private JCheckBox chip_annot = new JCheckBox("chip annotation");
    private int thisindex = 0;
    //flags to turn OFF the annotations
    private boolean chipannflag = false;
    private boolean geneannflag = false;

    //arrays for data
    char[] genome;
    int[] chip;
    int[] ann;

    public Browser(char[] g, int[] a, int[] c) {

        super("G Graphics Library - Demo 5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //init vals

        genome = g;
        chip = c;
        ann = a;
        // Create the GUI

        //create menu bar
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu analysis = new JMenu("Extract Data");
        JMenuItem eMenuItem = new JMenuItem("Exit");

        JMenuItem exchip_nc = new JMenuItem("Extract ChIP in noncoding");
        JMenuItem exchip_c = new JMenuItem("Extract ChIP in coding");
        JMenuItem excoding = new JMenuItem("Extract gene coding regions");
        JMenuItem distance = new JMenuItem("Plot distance");
        file.add(eMenuItem);
        analysis.add(exchip_nc);
        analysis.add(exchip_c);
        analysis.add(excoding);
        analysis.add(distance);
        menubar.add(file);
        menubar.add(analysis);
        setJMenuBar(menubar);

        //end menu bar
        JPanel topLevel = new JPanel();
        topLevel.setLayout(new BorderLayout());
        getContentPane().add(topLevel);

        //scroll bar
        final JScrollBar hScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
        getContentPane().add(hScrollBar, BorderLayout.SOUTH);
        final JScrollBar vScrollBar = new JScrollBar(JScrollBar.VERTICAL);
        getContentPane().add(vScrollBar, BorderLayout.EAST);
        //create button panels
        JPanel buttonPanel = new JPanel();
        JPanel buttonPanel_bottom = new JPanel();
        JPanel buttonPanel_west = new JPanel();
        buttonPanel_west.setLayout(new BoxLayout(buttonPanel_west, BoxLayout.Y_AXIS));
        //set colors fonts etc.
        Font myFont = new Font("Courier", Font.CENTER_BASELINE, 14);
        chip_annot.setFont(myFont);
        gene_annot.setFont(myFont);
        chip_annot.setBackground(new Color(0, 225, 20));
        gene_annot.setBackground(new Color(200, 2, 0));

        chip_annot.setBorder(BorderFactory.createRaisedBevelBorder());
        gene_annot.setBorder(BorderFactory.createRaisedBevelBorder());
        chip_annot.setBorderPainted(true);
        gene_annot.setBorderPainted(true);
        buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.DARK_GRAY));
        buttonPanel_west.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2, Color.DARK_GRAY));
        buttonPanel_bottom.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 0, Color.DARK_GRAY));
        buttonPanel_west.setBackground(new Color(250, 255, 250));
        buttonPanel.setBackground(new Color(250, 255, 250));
        buttonPanel_bottom.setBackground(new Color(250, 255, 250));
        //add buttons to panel
        buttonPanel.add(
                new JLabel("Zoom and Pan with mouse buttons"));
        buttonPanel_west.add(chip_annot);

        buttonPanel_west.add(gene_annot);

        chip_annot.setSelected(
                true);
        gene_annot.setSelected(
                true);
        buttonPanel_bottom.add(prev);

        buttonPanel_bottom.add(next);

        topLevel.add(buttonPanel, BorderLayout.NORTH);

        topLevel.add(buttonPanel_bottom, BorderLayout.SOUTH);

        topLevel.add(buttonPanel_west, BorderLayout.WEST);

        // Create the graphic canvas
        final GWindow window = new GWindow(new Color(250, 255, 250));

        topLevel.add(window.getCanvas(), BorderLayout.CENTER);

        // Create scene with default viewport and world extent settings
        final GScene scene = new GScene(window, "Scene");

        //scene.setWorldExtent(0.0, -3111.0, 5500, 5000);
        scene.setWorldExtent(
                -100.0, 4000.0, 5500, -6500);

        // Create graphic object
        final GObject genomeob = new Genomeview();

        //scene.add(da);
        scene.add(genomeob);

        pack();

        setSize(
                new Dimension(1000, 500));
        setVisible(
                true);
        window.startInteraction(
                new ZoomInteraction(scene));
        //scene.shouldWorldExtentFitViewport(true);
        //scene.shouldZoomOnResize(true);
        //scene.setViewport(0, 0,1000,0,0,100);
        scene.installScrollHandler(hScrollBar, vScrollBar);

        //add actions to menu bar items
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                ;
            }
        });

        exchip_nc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Extractchippos ob = new Extractchippos(genome, ann, chip, true);
                ob.run();
            }
        });
        exchip_c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Extractchippos ob = new Extractchippos(genome, ann, chip, false);
                ob.run();
                
            }
        });
        excoding.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
                Extractcodingpos ob = new Extractcodingpos(genome, ann, chip, false);
                ob.run();
            }
        });

        distance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Plotdistribution ob = new Plotdistribution(chip);
                //ob.plotdistance();
                ob.run();

            }
        });

        /*//
         ////////////End menu bar item actions////////////////
         //*/
        next.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.print("Next Clicked" + thisindex);
                thisindex++;
                if (thisindex >= 0) {

                    scene.removeAll();
                    // Create graphic object
                    final GObject genomeob = new Genomeview();
                    scene.add(genomeob);
                    genomeob.draw();

                    scene.refresh();
                    //scene.redraw();

                } else {
                    JOptionPane.showMessageDialog(null, "Cannot go any back");
                }

            }
        }
        );

        prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.print("Prev Clicked " + thisindex);
                thisindex--;
                if (thisindex >= 0) {
                    scene.removeAll();
                    // Create graphic object
                    final GObject genomeob = new Genomeview();
                    scene.add(genomeob);
                    genomeob.draw();
                    scene.refresh();

                } else {
                    JOptionPane.showMessageDialog(null, "Cannot go any back");
                    thisindex++;
                }

            }
        }
        );

        chip_annot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (thisindex >= 0) {

                    //scene.setVisibility(GObject.ANNOTATION_INVISIBLE);
                    if (chipannflag == false) {
                        chipannflag = true;
                        scene.refresh();
                        scene.redraw();
                        scene.refresh();
                    } else if (chipannflag) {
                        chipannflag = false;
                        //add object again with annotations
                        scene.removeAll();
                        // Create graphic object
                        final GObject genomeob = new Genomeview();
                        scene.add(genomeob);
                        genomeob.draw();
                        scene.refresh();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Cannot go any back");
                    thisindex++;
                }

            }
        }
        );

        gene_annot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (thisindex >= 0) {

                    //scene.setVisibility(GObject.ANNOTATION_INVISIBLE);
                    if (geneannflag == false) {
                        geneannflag = true;
                        scene.refresh();
                        scene.redraw();
                        scene.refresh();
                    } else if (geneannflag) {
                        geneannflag = false;
                        //add object again with annotations
                        scene.removeAll();
                        // Create graphic object
                        final GObject genomeob = new Genomeview();
                        scene.add(genomeob);
                        genomeob.draw();
                        scene.refresh();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Cannot go any back");
                    thisindex++;
                }

            }
        }
        );
    }

    @Override

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        boolean showgene = gene_annot.isSelected();
        boolean showannotation = chip_annot.isSelected();

    }

    public class Genomeview extends GObject {

        //num of genome strands in viewport
        int num_strands = 15;
        //define max points in one view
        //15*5000=7500
        int maxpts = num_strands * 5000;
        private GSegment[] g_strands;
        private GSegment[] chip_segment;
        private GSegment[] ann_segment;
        private int[] chipdata = new int[maxpts];
        private int[] anndata = new int[maxpts];

        int index;

        public Genomeview() {

            //e.g.15 line segments for each scene
            g_strands = new GSegment[num_strands];
            //read data
            getdata();
            for (int i = 0; i < g_strands.length; i++) {
                g_strands[i] = new GSegment();
                GStyle curveStyle = new GStyle();
                curveStyle.setForegroundColor(new Color(2, 0, 255));
                curveStyle.setBackgroundColor(Color.BLACK);
                curveStyle.setLineWidth(1);
                g_strands[i].setStyle(curveStyle);
                addSegment(g_strands[i]);
            }

            //create segemnts for annotations
            ann_segment = new GSegment[(anndata.length) / 2];
            for (int i = 0; i < ann_segment.length; i++) {
                ann_segment[i] = new GSegment();
                GStyle annStyle = new GStyle();
                annStyle.setForegroundColor(new Color(200, 5, 0));
                annStyle.setLineWidth(2);
                ann_segment[i].setStyle(annStyle);
                addSegment(ann_segment[i]);
            }

            // create segments for chip data
            chip_segment = new GSegment[(chipdata.length) / 2];
            for (int i = 0; i < chip_segment.length; i++) {
                chip_segment[i] = new GSegment();
                GStyle chipStyle = new GStyle();
                chipStyle.setForegroundColor(new Color(0, 255, 0));
                chipStyle.setFillPattern(i);
                chipStyle.setLineWidth(2);

                chip_segment[i].setStyle(chipStyle);
                addSegment(chip_segment[i]);
            }

            // set text annotation styles
            GStyle textStyle = new GStyle();
            textStyle.setForegroundColor(new Color(0, 0, 0));
            textStyle.setBackgroundColor(new Color(0.0f, 0.0f, 0.0f, 0.0f));
            textStyle.setFont(new Font("Dialog", Font.BOLD, 14));
            GStyle annStyle = new GStyle();
            annStyle.setForegroundColor(new Color(255, 0, 0));
            annStyle.setBackgroundColor(new Color(0.0f, 0.0f, 0.0f, 0.0f));
            //annStyle.setFont(new Font("Dialog", Font.BOLD, 14));
            GStyle chipaStyle = new GStyle();
            chipaStyle.setForegroundColor(new Color(5, 205, 10));
            //chipaStyle.setBackgroundColor(new Color(250, 205, 100));
            //chipStyle.setFont(new Font("Dialog", Font.BOLD, 14));

            //add all annotations
            for (int i = 0; i < g_strands.length; i++) {
                int x = (thisindex * g_strands.length) + i;
                GText text = new GText("" + x, GPosition.WEST);
                text.setStyle(textStyle);
                g_strands[i].addText(text);
            }

            //add annotaions to annotation data
            for (int i = 0; i < ann_segment.length; i = i + 1) {
                //System.out.println(10 * (i + 1)+"*");
                int startpos = anndata[i * 2];
                int endpos = anndata[(i * 2) + 1];
                //add annotations to annotation segments
                if (startpos != 0) {
                    GText text = new GText("" + startpos + "-" + endpos, GPosition.CENTER);
                    text.setStyle(annStyle);
                    ann_segment[i].addText(text);
                }

            }

            //add annotaions to chip data
            for (int i = 0; i < chip_segment.length; i = i + 1) {
                //System.out.println(10 * (i + 1)+"*");
                int startpos = chipdata[i * 2];
                int endpos = chipdata[(i * 2) + 1];
                //add annotations to annotation segments
                if (startpos != 0) {
                    GText text = new GText("" + startpos + "-" + endpos, GPosition.CENTER);
                    text.setStyle(chipaStyle);
                    chip_segment[i].addText(text);
                }

            }

        }

        //fuction to get data to draw annotations and chip seq data
        public void getdata() {
            /*thisindex is a global variable which defines the nth scene 
             thisindex=0 means show genome from 0 to 75000-1, 1 means next 75000 positions etc
             */

            int ctr = 0;
            int pos = 0;
            //
            chipdata = new int[maxpts];
            anndata = new int[maxpts];

            //for(int i=0;i<chipdata.length;i++){
            //  chipdata[i]=0;
            //}
            //read next 75000 values
            for (int i = 0; i < ann.length; i = i + 2) {

                if (ann[i] >= thisindex * maxpts && ann[i] <= (thisindex * maxpts) + maxpts) {
                    //read both start and end positions
                    anndata[pos] = ann[i];
                    //  System.out.println("an=" + anndata[pos]);
                    pos = pos + 1;
                    anndata[pos] = ann[i + 1];
                    //  System.out.println("an=" + anndata[pos]);
                    pos++;
                }

            }

            //read chip data
            pos = 0;
            for (int i = 0; i < chip.length; i = i + 2) {

                if (chip[i] >= thisindex * maxpts && chip[i] <= (thisindex * maxpts) + maxpts) {
                    //read both start and end positions
                    chipdata[pos] = chip[i];
                    // System.out.println("an=" + chipdata[pos]);
                    pos = pos + 1;
                    chipdata[pos] = chip[i + 1];
                    // System.out.println("an=" + chipdata[pos]);
                    pos++;
                }

            }
        }

        public void draw() {
            /*
             Each genome is diveded into strands/linesegments of lengths 5000. on each scene 15 such
             line segments are displayed. so first scene contains positions 0 till 74999. Next scence
             contains 75000 till 149999 so on. For each scene chip and annotation data is read from 
             public arrays in readfile class.
             */

            double[] x = new double[4];
            double[] y = new double[4];

            double xoffset = 100;
            double x0 = 0;
            double x1 = 5000;
            //add x offset to shift display towards right
            x0 = x0 + xoffset;
            x1 = x1 + xoffset;
            for (int i = 0; i < g_strands.length; i++) {
                //System.out.println(10 * (i + 1)+"*");
                g_strands[i].setGeometry(x0, (200 * (i - 6)), x1, (200 * (i - 6)));
            }

            //call function get data to read data
            // getdata();
            //add annotation segments
            for (int i = 0; i < ann_segment.length; i = i + 1) {
                //System.out.println(10 * (i + 1)+"*");
                x0 = anndata[i * 2];
                x1 = anndata[(i * 2) + 1];
                //System.out.println("x"+x0);
                if (x0 != 0 && x1 != 0) {

                    //correct x coordinate
                    x0 = x0 % 5000;
                    if (x0 % 5000 == 0) {
                        x0 = 5000;
                    }
                    x1 = x1 % 5000;

                    if (x1 % 5000 == 0) {
                        x1 = 5000;
                    }
                    //add offset val to x i.e to shift x axis to right
                    x0 = x0 + xoffset;
                    x1 = x1 + xoffset;

                    //System.out.println(x0 + " " + x1 + " " + 200 * ((anndata[i * 2] / 5000) - 6) + 100);
                    //ann_segment[i].setGeometry(x0, (200 * ((anndata[i * 2] / 5000) - 6) + 100), x1, (200 * ((anndata[i * 2] / 5000) - 6) + 100));
                    ann_segment[i].setGeometry(x0, (200 * (((anndata[i * 2] - (thisindex * maxpts)) / 5000) - 6) + 100), x1, (200 * (((anndata[i * 2] - (thisindex * maxpts)) / 5000) - 6) + 100));

                    //add annotations to annotation segments
                }
            }

            //add chip data pos
            for (int i = 0; i < chip_segment.length; i = i + 1) {
                //System.out.println(10 * (i + 1)+"*");
                x0 = chipdata[i * 2];
                x1 = chipdata[(i * 2) + 1];
                if (x0 != 0 && x1 != 0) {

                    //correct x coordinate
                    x0 = x0 % 5000;
                    if (x0 % 5000 == 0) {
                        x0 = 5000;
                    }
                    x1 = x1 % 5000;

                    if (x1 % 5000 == 0) {
                        x1 = 5000;
                    }
                    //add offset val to x i.e to shift x axis to right
                    x0 = x0 + xoffset;
                    x1 = x1 + xoffset;

                    //System.out.println(x0 + " " + x1 + " " + 200 * ((anndata[i * 2] / 5000) - 6) + 100);
                    chip_segment[i].setGeometry(x0, (200 * (((chipdata[i * 2] - (thisindex * maxpts)) / 5000) - 6) + 121), x1, (200 * (((chipdata[i * 2] - (thisindex * maxpts)) / 5000) - 6) + 121));
                    //chip_segment[i].setGeometry(arr);       
                }
            }

            if (chipannflag) {
                for (int i = 0; i < chip_segment.length; i++) {
                    chip_segment[i].removeText();
                }
            }
            if (geneannflag) {
                for (int i = 0; i < ann_segment.length; i++) {
                    ann_segment[i].removeText();
                }
            }

        }
    }

}
