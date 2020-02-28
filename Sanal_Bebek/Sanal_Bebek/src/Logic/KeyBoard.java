
package Logic;

import Gui.ekran1;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;


public class KeyBoard implements KeyListener{

    public static boolean kontrol = false;        //Space tuşuna basıldığında zıplamayı kontrol eden değişken
    
    
    ekran1 ekran;
    public KeyBoard(ekran1 ekran) {
        this.ekran =ekran;    
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_ENTER) {
            if(!ekran.oyunDurumu){  //enter tuşunun sadece game over da çalşımasını sağlar
            if(Action.acKontrol || Action.temKontrol || Action.uyku)
            {
                ekran1.pouX = 310;
                ekran1.pouY = 360;
                ekran1.dirPouX = 1;
                ekran1.dirPouY = 10;
                ekran1.hamburger = null;
                try {
                ekran.image = ImageIO.read(new FileImageInputStream(new File("src//Gui//pou.png")));
                ekran.arkaplan = new ImageIcon("src//Gui//images.png").getImage();
               }   catch (IOException ex) {
                System.out.println("Dosya bulunamadı " + ex);
            }
                
            }   
            ekran.oyunDurumu = true;    
            ekran.getBir().setVisible(true);    //Game overdan sonra buronların eski haline gelmesi ve timer ın başlamsı 
            ekran.getBir().setEnabled(true);
            ekran.getIki().setVisible(true);
            ekran.getIki().setEnabled(true);
            ekran.getUc().setVisible(true);
            ekran.getUc().setEnabled(true);
            ekran.getA().timer.start();
            }
        }
        if (c == KeyEvent.VK_SPACE) {   //Space tuşuna basıldığında(Eğlence ve Puan)
                ekran.kontrol = true;
                ekran.puan += 1;
                if(Action.temKontrol || Action.acKontrol || Action.uyku)
                {
                    ekran.eglence = ekran.eglence - 5;  //butonlardan herhangi birine basıldığında eğlencenin sabit kalması için(Space se basıldığında)  
                    ekran.puan = ekran.puan - 1;    //butonlardan herhangi birine basıldığında puanın sabit kalması için
                }
                ekran.eglence = ekran.eglence + 5;
                if (ekran.eglence > 100) {
                    ekran.eglence = 100;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


    
    
}
