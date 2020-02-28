package Logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Gui.ekran1;
import com.sun.java.accessibility.util.AWTEventMonitor;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Action implements ActionListener {
    
    public Timer timer = new Timer(10, this); //10 milisaniye de bir çalışır
    ekran1 ekran = null;
    public boolean burgerkontrol = false;
    public static boolean uyku = false, temKontrol = false, acKontrol = false;

    public Action(ekran1 ekran) {
        timer.start();
        this.ekran = ekran;
    }

    public ekran1 getE() {

        ekran = new ekran1();
        return ekran;
    }

    public void setE(ekran1 ekran) {
        this.ekran = ekran;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        ekran.oyunDurumu = true;    //oyunDurumu true ya döndüğünde oyun başlar aşağıdaki işlemler gerçekleşir
        if(ekran.oyunDurumu){
        ekran.eglence = ekran.eglence - 0.0056;
        ekran.aclik = ekran.aclik + 0.0072;
        ekran.uykusuzluk = ekran.uykusuzluk + 0.0037;
        ekran.temizlik = ekran.temizlik - 0.003;
        
        ekran.jAclikBar.setValue((int) (ekran.aclik));
        ekran.jUykusuzlukBar.setValue((int) (ekran.uykusuzluk));
        ekran.jEglenceBar.setValue((int) (ekran.eglence));
        ekran.jTemizlikBar.setValue((int) (ekran.temizlik));
        ekran.jPuanBar.setValue((int) (ekran.puan));
        
        if(ekran.puan < 0)
        {
            ekran.puan = 0;
        }
        if(ekran.puan > 100)
        {
            ekran.puan = 100;
        }
            ekran.pouX += ekran.dirPouX;    //Pou nun X konumundaki değişimi (arttırılması)

            if (ekran.pouX >= 320) {            //Pou nu maxium gideceği uzaklık.Bu uzaklığa ulaştığında tersi yönde hareket eder
                ekran.dirPouX = -ekran.dirPouX;
            }
            if (ekran.pouX <= 270) {            //Pou nu minimum gideceği uzaklık.Bu uzaklığa ulaştığında ters yöne gider.
                ekran.dirPouX = -ekran.dirPouX;
            }
     
        if (ekran.kontrol == true) {    //space tuşunu basılıp basılmadığı kontrol edilir

            ekran.pouY -= ekran.dirPouY;
        }
        if (ekran.pouY == 20) {

            ekran.kontrol = false;
        }
        if (ekran.kontrol == false && ekran.pouY <= 350) {
            ekran.pouY += ekran.dirPouY;
        }
        if (ekran.gecen_sure < 1500) {
            ekran.gecen_sure = ekran.gecen_sure + 5;
        }

        if (ekran.gecen_sure == 1500) {
            try {
                ekran.image = ImageIO.read(new FileImageInputStream(new File("src//Gui//pou.png")));
                ekran.arkaplan = new ImageIcon("src//Gui//images.png").getImage();
            } catch (IOException ex) {
                System.out.println("Dosya bulunamadı " + ex);
            }
            ekran.dirPouY = 10; // Pou nun Y konumundaki değişiimi
            ekran.dirPouX = 1;
            ekran.pouX = 310;
            ekran.pouY = 350;
            ekran.gecen_sure = 0;

            acKontrol = false;
            temKontrol = false;
            uyku = false;

        }

        ekran.repaint();

        if (ae.getSource() == ekran.getBir()) { // temizlik
            if(acKontrol || uyku)   //Temizlik butonuna basıldığunda açlık ve uyku butonuna basılırsa hiç bir şey dönmemesini sağlar
                return;
            temKontrol = true;

            ekran.puan -= 5;
            ekran.temizlik = 100;
            ekran.gecen_sure = 0;
            pouSabitTut();

            try {
                ekran.image = ImageIO.read(new FileImageInputStream(new File("src//Gui//WashingPou.png")));

            } catch (IOException ex) {
              
            }
            
        }

        if (ae.getSource() == ekran.getIki()) { // uyku
            if(acKontrol || temKontrol)
                return;
            uyku = true;
            ekran.puan -= 5;
            pouSabitTut();
            ekran.gecen_sure = 0;
            ekran.uykusuzluk = -1;
            try {
                ekran.image = ImageIO.read(new FileImageInputStream(new File("src//Gui//pou2.png")));
                ekran.arkaplan = new ImageIcon("src//Gui//images2.png").getImage();
            } catch (IOException ex) {
                }
        }
        
        if (ae.getSource() == ekran.getUc()) {
            if(temKontrol || uyku)
                return;
            acKontrol = true;
            ekran.aclik = ekran.aclik - 10;
            ekran.puan -= 5;
            ekran.gecen_sure = 0;

            if (ekran.aclik < 0) {
                ekran.aclik = 0;
            }
            try {
                ekran.hamburger = ImageIO.read(new FileImageInputStream(new File("src\\Gui\\hamburger.png")));
                ekran.image = ImageIO.read(new FileImageInputStream(new File("src\\Gui\\EatingPou.png")));
            } catch (IOException ex) {

                System.out.println("ekran1.ekran1() pou yüklenemedi. Hata: " + ex.getMessage());
            }
            burgerkontrol = true;
        }
        
        if(ekran.hamburgerY >= 420){
            burgerkontrol = false;
            ekran.hamburger = null;
            ekran.hamburgerY = 0;
            try {
                ekran.image = ImageIO.read(new FileImageInputStream(new File("src//Gui//pou.png")));
            } catch (IOException ex) {
                System.out.println("ekran1.ekran1() pou yüklenemedi. Hata: " + ex.getMessage());
            }
        }
        
        if (burgerkontrol == true) {
            ekran.hamburgerY = ekran.hamburgerY + 2;
        }
        ekran.repaint();
        }
    }

    private void pouSabitTut(){
        ekran.dirPouX = 0;
        ekran.dirPouY = 0;
        ekran.pouX = 310;
        ekran.pouY = 350;
        if(temKontrol){
            ekran.pouX = 250;
            ekran.pouY = 300;
        }
    }
    
}
