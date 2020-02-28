package Gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import Logic.Action;
import Logic.KeyBoard;
import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ekran1 extends JPanel  {
    public boolean oyunDurumu;
    public int puan = 1; // Puan Bar'ımızn başlangıç değeri
    public static int pouX = 310; // Pou nu X konumu
    public static int pouY = 350; // Pou nu Y konumu
    
    public int hamburgerX = 340; //Yukarıdan düşen hamburgerlerin X konunumu
    public int hamburgerY = 0;      //Yukarıdan düşen hamburgerlerin Y konumu
    public int dirHamburgerY = 1;   //Yukarıdan düşecek hamburgerin Y deki değişimi
    
    public static int dirPouX = 1; // Pou nun X konumundaki değişiimi
    public static int dirPouY = 10; // Pou nun Y konumundaki değişiimi
    
    public boolean kontrol = false; //Space tuşuna basılıp basılmadığını kontrol eder
    
    
    public int gecen_sure = 0; //Uykuda 3 saniye kalmasını sağlar
    public ImageIcon buton, buton1, buton2; // Butonların imagelerinin tanımlanması
    private JButton bir; // Butonların tanımlanması
    private JButton iki; // Butonların tanımlanması
    private JButton uc; // Butonların tanımlanması
    java.awt.Color transColor = new java.awt.Color(1f, 0f, 0f, 0f); //Butonların arka plan rengi
    public Image arkaplan,gameover;  //Arkplan ve yukardan düşen hamburgerlerin imagelerinin tanımlanması
    public static Image hamburger; 
    public BufferedImage image; // Pou
    public double aclik = 0.0; // ProgressBar daki açlık uykusuzluk ve eğlence
    public double uykusuzluk = 0.0;
    public double eglence = 100.0;
    public double temizlik = 100.0;
    
    public JProgressBar jAclikBar = new JProgressBar(); //ProgressBar nesnelerinin oluşturulması
    public JProgressBar jUykusuzlukBar = new JProgressBar();
    public JProgressBar jEglenceBar = new JProgressBar();
    public JProgressBar jTemizlikBar = new JProgressBar();
    public JProgressBar jPuanBar = new JProgressBar();
    
    JLabel jAclikLabel; //ProgressBarın yanındaki yazıların tanımı
    JLabel jUykusuzlukLabel;
    JLabel jEglenceLabel;
    JLabel jTemizlikLabel;
    JLabel jPuanLabel;
    
    Action a;  
    KeyBoard k;
    
     public ekran1() {

        arkaplan = new ImageIcon("src\\Gui\\images.png").getImage();
        gameover = new ImageIcon("src\\Gui\\gameover.png").getImage();
        add(getBir());
        buton = new ImageIcon("src\\Gui\\bathtub.png");
        add(getIki());
        buton1 = new ImageIcon("src\\Gui\\mask.png");
        add(getUc());
        buton2 = new ImageIcon("src\\Gui\\burger.png");
        
        jAclikBar.setValue((int) (aclik));  //Bar'ın değerini atıyoruz
        jAclikBar.setStringPainted(true);  //Barların içinde yazı olmasını sağladık
        jAclikBar.setBorderPainted(false);  //Bar'ın kenarlıkları
        jAclikBar.setForeground(java.awt.Color.ORANGE); //Bar rengi
        add(jAclikBar);
        
        jUykusuzlukBar.setValue((int) (aclik));
        jUykusuzlukBar.setStringPainted(true);
        jUykusuzlukBar.setBorderPainted(false);
        jUykusuzlukBar.setForeground(java.awt.Color.BLUE);
        add(jUykusuzlukBar);
        
        jEglenceBar.setValue((int) (aclik));
        jEglenceBar.setStringPainted(true);
        jEglenceBar.setBorderPainted(false);
        jEglenceBar.setForeground(java.awt.Color.RED);
        add(jEglenceBar);
        
        jTemizlikBar.setValue((int)(temizlik));
        jTemizlikBar.setStringPainted(true);
        jTemizlikBar.setBorderPainted(false);
        jTemizlikBar.setForeground(java.awt.Color.MAGENTA);
        add(jTemizlikBar);
        
        jPuanBar.setValue((int)(puan));
        jPuanBar.setStringPainted(true);
        jPuanBar.setBorderPainted(false);
        jPuanBar.setForeground(java.awt.Color.CYAN);
        add(jPuanBar);
        
        jAclikLabel = new JLabel("Açlık");  //Label ın içinde yazcak değer
        jAclikLabel.setVisible(true);   //Label in görünümü
        jAclikLabel.setFont(new Font("Helvetica", Font.PLAIN, 16)); //Label ın font özellikleri
        add(jAclikLabel);
       
        jUykusuzlukLabel = new JLabel("Uyku");
        jUykusuzlukLabel.setVisible(true);
        jUykusuzlukLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        add(jUykusuzlukLabel);
        

        jEglenceLabel = new JLabel("Eglence");
        jEglenceLabel.setVisible(true);
        jEglenceLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        add(jEglenceLabel);
        
        jTemizlikLabel = new JLabel("Temizlik");
        jTemizlikLabel.setVisible(true);
        jTemizlikLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        add(jTemizlikLabel);
        
        jPuanLabel = new JLabel("PUAN");
        jPuanLabel.setVisible(true);
        jPuanLabel.setFont(new Font("Helvetica", Font.PLAIN, 16));
        add(jPuanLabel);

        try {
            image = ImageIO.read(new FileImageInputStream(new File("src\\Gui\\Pou.png")));  //Pou iconu nu ekliyoruz

        } catch (IOException ex) {
            System.out.println("ekran1.ekran1() pou yüklenemedi. Hata: " + ex.getMessage());
        }
       addActionListener(getA());
       addKeyListener(getK());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        bir.setIcon(buton);    //Buton resmini değiştirdik
        bir.setBounds(30, 420, 64, 64);  //Butonun X konumu,Y konumu,yüksekliği,genişliği

        iki.setIcon(buton1);
        iki.setBounds(30, 270, 64, 64);

        uc.setIcon(buton2);
        uc.setBounds(30, 120, 64, 64);

        jAclikBar.setBounds(570, 30, 200, 20);      //Barlar ın X konum,Y konumu,genişliği,yüksekliği
        jUykusuzlukBar.setBounds(570, 60, 200, 20);
        jEglenceBar.setBounds(570, 90, 200, 20);
        jTemizlikBar.setBounds(570, 120, 200, 20);
        jPuanBar.setBounds(30, 30, 250, 25);

        jAclikLabel.setBounds(500, 30, 100, 20);    //Lableler in X konum,Y konumu,genişliği,yüksekliği
        jUykusuzlukLabel.setBounds(500, 60, 100, 20);
        jEglenceLabel.setBounds(500, 90, 100, 20);
        jTemizlikLabel.setBounds(500, 120, 100, 20);
        jPuanLabel.setBounds(290, 30, 100, 20);

        g.drawImage(image, pouX, pouY, image.getWidth() / 2, image.getHeight() / 2, this);  //Pou iconunu çizdiriyoruz
        g.drawImage(hamburger, hamburgerX, hamburgerY, 64,64,this);  //Hamburger iconunu çizdiriyoruz
        
        if(aclik >= 100 || uykusuzluk >= 100 || eglence <= 0 || puan <= 0 || temizlik <= 0)
        {
            oyunDurumu = false;
        }
        
        if(!oyunDurumu) //oyunDurumu false olduğunda aclik,uykusuzluk,temizlik barları eski haline döner
        {
            aclik = 0;
            uykusuzluk = 0;
            eglence = 100;
            puan = 1;
            temizlik = 100;
            
            bir.setVisible(false); //oyunDurumu false olunca butonların görünmemesini ve tıklanmaması sağlar
            bir.setEnabled(false);
            iki.setVisible(false);
            iki.setEnabled(false);
            uc.setVisible(false);
            uc.setEnabled(false);
            g.drawImage(gameover, 0, 0, 800, 600, this);
            
            getA().timer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) { // ArkaPlan resmini gömeriz ve onun arkasına hiç birşey gelemez
        super.paintComponent(g);
        int baslangicx = 0;
        int baslangicy = 0;
        int bitisx = 800;
        int bitisy = 600;
        g.drawImage(arkaplan, baslangicx, baslangicy, bitisx, bitisy, null);
    }
  
    public void repaint() { //göresellerin düzgün çizilmesi arkasında iz olmamsı için
        super.repaint();
    }
    
    public JButton getBir() {   //Temizlik
        if (bir == null) {
            bir = new JButton();
            bir.setVisible(true);
            bir.setRequestFocusEnabled(false);
            bir.setFocusable(true);
            bir.setBorder(null);
            bir.addActionListener(getA());
            bir.setBackground(transColor);
        }
        return bir;
    }

    public void setBir(JButton bir) {
        this.bir = bir;
    }

    public JButton getIki() {   //Uyku
        if (iki == null) {
            iki = new JButton();
            iki.setVisible(true);
            iki.setRequestFocusEnabled(false);
            iki.setFocusable(true);
            iki.setBorder(null);
            iki.addActionListener(getA());
            iki.setBackground(transColor);
        }
        return iki;
    }

    public void setIki(JButton iki) {
        this.iki = iki;
    }

    public JButton getUc() {    //Açlık
        if (uc == null) {
            uc = new JButton();
            uc.setVisible(true);
            uc.setRequestFocusEnabled(false);
            uc.setFocusable(true);
            uc.setBorder(null);
            uc.addActionListener(getA());
            uc.setBackground(transColor);
        }
        return uc;
    }

    public void setUc(JButton uc) {
        this.uc = uc;
    }

    
    public Action getA() {
        if (a== null) {
            a = new Action(this);
        }
        return a;
    }

    public void setA(Action a) {
        this.a = a;
    }

    public KeyBoard getK() {
        if (k== null) {
          k = new KeyBoard(this);
        }
        return k;
    }

   public void setK(KeyBoard k) {
        this.k = k;
    }
}
