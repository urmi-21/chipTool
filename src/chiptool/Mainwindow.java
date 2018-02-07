/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiptool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author jnu
 */
public class Mainwindow extends javax.swing.JFrame {

    /**
     * Creates new form Mainwindow
     */
    int[] chipdata = null;

    public Mainwindow() {
        initComponents();
        //Readfile ob = new Readfile(jTextFieldgfile.getText(), jTextFieldafile.getText(), jTextFieldcfile.getText());
        //chipdata=ob.chipdata;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldgfile = new javax.swing.JTextField();
        jButtonbgenomefile = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldafile = new javax.swing.JTextField();
        jTextFieldcfile = new javax.swing.JTextField();
        jButtonbrowseannot = new javax.swing.JButton();
        jButtonbrowsechip = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Genome File");

        jTextFieldgfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldgfileActionPerformed(evt);
            }
        });

        jButtonbgenomefile.setText("Browse");
        jButtonbgenomefile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonbgenomefileActionPerformed(evt);
            }
        });

        jLabel2.setText("Annotation");

        jLabel3.setText("ChIP seq");

        jButtonbrowseannot.setText("Browse");
        jButtonbrowseannot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonbrowseannotActionPerformed(evt);
            }
        });

        jButtonbrowsechip.setText("Browse");
        jButtonbrowsechip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonbrowsechipActionPerformed(evt);
            }
        });

        jButton1.setText("Go");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldgfile, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldafile, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(jTextFieldcfile, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))
                .addGap(98, 98, 98)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonbrowsechip)
                    .addComponent(jButtonbrowseannot)
                    .addComponent(jButtonbgenomefile))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldgfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonbgenomefile))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldafile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonbrowseannot)
                    .addComponent(jLabel2))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldcfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonbrowsechip))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldgfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldgfileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldgfileActionPerformed

    private void jButtonbgenomefileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonbgenomefileActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        //chooser.addChoosableFileFilter(ff);

        int status = chooser.showOpenDialog(jLabel1);

        if (status == JFileChooser.APPROVE_OPTION) {
            File chosenFile = chooser.getSelectedFile();
            try {
                jTextFieldgfile.setText(chosenFile.getCanonicalPath());
            } catch (IOException ex) {
                Logger.getLogger(Mainwindow.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButtonbgenomefileActionPerformed
    }
    private void jButtonbrowseannotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonbrowseannotActionPerformed
        // TODO add your handling code here:

        JFileChooser chooser = new JFileChooser();
        //chooser.addChoosableFileFilter(ff);

        int status = chooser.showOpenDialog(jLabel2);

        if (status == JFileChooser.APPROVE_OPTION) {
            File chosenFile = chooser.getSelectedFile();
            try {
                jTextFieldafile.setText(chosenFile.getCanonicalPath());
            } catch (IOException ex) {
                Logger.getLogger(Mainwindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonbrowseannotActionPerformed

    private void jButtonbrowsechipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonbrowsechipActionPerformed
        // TODO add your handling code here:

        JFileChooser chooser = new JFileChooser();
        //chooser.addChoosableFileFilter(ff);

        int status = chooser.showOpenDialog(jLabel3);

        if (status == JFileChooser.APPROVE_OPTION) {
            File chosenFile = chooser.getSelectedFile();
            try {
                jTextFieldcfile.setText(chosenFile.getCanonicalPath());
            } catch (IOException ex) {
                Logger.getLogger(Mainwindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButtonbrowsechipActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        Readfile ob = new Readfile(jTextFieldgfile.getText(), jTextFieldafile.getText(), jTextFieldcfile.getText());
        Toolswindow ob1=new Toolswindow(jTextFieldgfile.getText(),jTextFieldcfile.getText() , jTextFieldafile.getText(),ob.genomedata, ob.chipdata,ob.anndata);
        ob1.setVisible(true);
        
        //Browser newob = new Browser(ob.genomedata, ob.anndata, ob.chipdata);


    }//GEN-LAST:event_jButton1ActionPerformed
    public static void callmain() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mainwindow().setVisible(true);

            }
        });
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonbgenomefile;
    private javax.swing.JButton jButtonbrowseannot;
    private javax.swing.JButton jButtonbrowsechip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextFieldafile;
    private javax.swing.JTextField jTextFieldcfile;
    private javax.swing.JTextField jTextFieldgfile;
    // End of variables declaration//GEN-END:variables
}