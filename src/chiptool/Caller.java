/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chiptool;

/**
 *
 * @author jnu
 */
public class Caller extends Thread {

    char[] g;
    int[] c;
    int[] a;
    
    public Caller(char []g, int[] c, int[]a){
        this.g=g;
        this.a=a;
        this.c=c;
    }
    
    public void run(){
        Browser newob = new Browser(g, a, c);
        newob.setVisible(true);
    }

}
