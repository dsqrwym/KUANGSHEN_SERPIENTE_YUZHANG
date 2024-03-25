import javax.swing.*;
import java.net.URL;

public class Imagenes {
    private static final URL cabezaL = Imagenes.class.getResource("/imagenes/LEFT.png");
    private static final URL cabezaU = Imagenes.class.getResource("/imagenes/UP.png");
    private static final URL cabezaR = Imagenes.class.getResource("/imagenes/RIGHT.png");
    private static final URL cabezaD = Imagenes.class.getResource("/imagenes/DOWN.png");
    private static final URL cuerpo = Imagenes.class.getResource("/imagenes/BODY.png");
    private static final URL colaL = Imagenes.class.getResource("/imagenes/CL.png");
    private static final URL colaU = Imagenes.class.getResource("/imagenes/CU.png");
    private static final URL colaR = Imagenes.class.getResource("/imagenes/CR.png");
    private static final URL colaD = Imagenes.class.getResource("/imagenes/CD.png");
    private static final ImageIcon up = new ImageIcon(cabezaU);
    private static final ImageIcon left = new ImageIcon(cabezaL);
    private static final ImageIcon right = new ImageIcon(cabezaR);
    private static final ImageIcon down = new ImageIcon(cabezaD);
    private static final ImageIcon body = new ImageIcon(cuerpo);
    private static final ImageIcon cL = new ImageIcon(colaL);
    private static final ImageIcon cU = new ImageIcon(colaU);
    private static final ImageIcon cR = new ImageIcon(colaR);
    private static final ImageIcon cD = new ImageIcon(colaD);

    public static ImageIcon getUp() {
        return up;
    }

    public static ImageIcon getLeft() {
        return left;
    }

    public static ImageIcon getRight() {
        return right;
    }

    public static ImageIcon getDown() {
        return down;
    }

    public static ImageIcon getBody() {
        return body;
    }
    public static ImageIcon getCL(){
        return cL;
    }
    public static ImageIcon getCU(){
        return cU;
    }
    public static ImageIcon getCR(){
        return cR;
    }
    public static ImageIcon getCD(){
        return cD;
    }
}
