
package Business;

import Gui.ekran1;
import javax.swing.JFrame;
public class Main{
 
    public static void main(String[] args){
        JFrame ekran = new JFrame();    //ekran adında pencere oluşturduk   
        ekran.setResizable(false);      //Ekranın sabit boyuta kallmasını sağlar.
        ekran.setFocusable(false);      
        ekran.setSize(800,600);         //Ekran boyutu belirledik (width,height).
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Çarpı ile kapat
        ekran1 e =new ekran1(); 
        e.setFocusable(true);
        ekran.add(e);
        ekran.setVisible(true);
    }
}
