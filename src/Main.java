import javax.swing.*;

public class Main extends JFrame{
    public static void main(String[] args) {
        try{
            new Main().go();
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    public void go(){
        Juego juego = new Juego();
        setBounds(10, 10, 790, 620);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(juego);
    }
}