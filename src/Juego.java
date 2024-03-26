import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Juego extends JPanel implements ActionListener{
    public static Random aleatorio = new Random();
    private Serpiente serpiente = new Serpiente();
    private double score;
    private double historiScore;
    private int alimentosX;
    private int alimentosY;
    private Color alimentoColor;
    private final Timer temporizador = new Timer(serpiente.getVelocidad(), this);
    private boolean isOver = false;
    private boolean isStart = false;
    private boolean inicio = true;
    public Juego(){
        setBackground(Color.cyan);
        alimentosX = serpiente.getPosicionX().get(0)+25;
        alimentosY = serpiente.getPosicionY().get(0);

        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                int keyCodigo = e.getKeyCode();
                if (isStart && !isOver) {
                    switch (keyCodigo) {
                        case KeyEvent.VK_LEFT:
                        case KeyEvent.VK_A:
                            if (!serpiente.getDireccion().equals("R")) {
                                serpiente.setDireccion("L");
                            }
                            break;
                        case KeyEvent.VK_UP:
                        case KeyEvent.VK_W:
                            if (!serpiente.getDireccion().equals("D")) {
                                serpiente.setDireccion("U");
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                        case KeyEvent.VK_D:
                            if (!serpiente.getDireccion().equals("L")) {
                                serpiente.setDireccion("R");
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                        case KeyEvent.VK_S:
                            if (!serpiente.getDireccion().equals("U")) {
                                serpiente.setDireccion("D");
                            }
                            break;
                    }
                    switch (keyCodigo) {
                        case KeyEvent.VK_LEFT:
                        case KeyEvent.VK_A:
                        case KeyEvent.VK_UP:
                        case KeyEvent.VK_W:
                        case KeyEvent.VK_RIGHT:
                        case KeyEvent.VK_D:
                        case KeyEvent.VK_DOWN:
                        case KeyEvent.VK_S:
                            temporizador.setDelay(10);
                            repaint();
                            break;
                    }
                }
                if (keyCodigo == KeyEvent.VK_SPACE) {
                    isStart = !isStart;
                    if (!temporizador.isRunning()){
                        temporizador.start();
                    }
                    if (isOver) {
                        serpiente = new Serpiente();
                        inicio = true;
                        isOver = false;
                        score = 0;
                    } else {
                        inicio = false;
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCodigo = e.getKeyCode();
                if (isStart && !isOver) {
                    switch (keyCodigo){
                        case KeyEvent.VK_SPACE:
                        case KeyEvent.VK_LEFT:
                        case KeyEvent.VK_A:
                        case KeyEvent.VK_UP:
                        case KeyEvent.VK_W:
                        case KeyEvent.VK_RIGHT:
                        case KeyEvent.VK_D:
                        case KeyEvent.VK_DOWN:
                        case KeyEvent.VK_S: temporizador.setDelay(serpiente.getVelocidad()); repaint();
                    }
                }

            }
        });
        temporizador.start();
        score = 0;
    }
    public Color color(){
        return new Color(Juego.aleatorio.nextInt(256), Juego.aleatorio.nextInt(256), Juego.aleatorio.nextInt(256));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0x5D9B5D));
        g.fillRect(10,28,750,528);

        g.setColor(Color.white);
        g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
        g.drawString("Longitud : " +serpiente.getLongitud() +" Puntaje : "+String.format("%.2f", score), 268, 18);
        g.drawString( "Puntuacion mas alta --> "+String.format("%.2f", historiScore), 268, 576);

        generarAlimentos(g);
        dibujarSerpiente(g);

        g.setColor(Color.white);
        g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
        if (!inicio){
            if (!isStart && !isOver){
                g.drawString("Pulsa espacio para continuar" +serpiente.getVelocidad() +" " +temporizador.getDelay(), 60, 300);
                temporizador.stop();
            }else if (isOver){
                g.drawString("Has fallado, longitud : "+serpiente.getLongitud() +" Puntaje : "+String.format("%.2f", score), 100, 300);
                if (score > historiScore){
                    historiScore = score;
                }
            }
        }else if (isStart && isOver){
            g.drawString("Incrible has ganado (°ロ°) ! !!!!!??? O es un Bug", 170, 300);
            if (score > historiScore){
                historiScore = score;
            }
        } else {
            g.drawString("Pulsa espacio para iniciar juego", 170, 300);
        }
    }
    private void generarAlimentos(Graphics g){
        g.setColor(alimentoColor);
        g.fillOval(alimentosX, alimentosY, 25, 25);
        repaint();
    }
    private void dibujarSerpiente(Graphics g){
        for (int i = 1; i < serpiente.getLongitud()-1; i++){
            Imagenes.getBody().paintIcon(this, g, serpiente.getPosicionX().get(i), serpiente.getPosicionY().get(i));
        }
        if (serpiente.getPosicionX().get(serpiente.getLongitud()-1) < serpiente.getPosicionX().get(serpiente.getLongitud()-2) && serpiente.getPosicionY().get(serpiente.getLongitud()-1) == serpiente.getPosicionY().get(serpiente.getLongitud()-2)){
            Imagenes.getCL().paintIcon(this, g, serpiente.getPosicionX().get(serpiente.getLongitud()-1), serpiente.getPosicionY().get(serpiente.getLongitud()-1));
        }else if (serpiente.getPosicionX().get(serpiente.getLongitud()-1) == serpiente.getPosicionX().get(serpiente.getLongitud()-2) && serpiente.getPosicionY().get(serpiente.getLongitud()-1) < serpiente.getPosicionY().get(serpiente.getLongitud()-2)){
            Imagenes.getCU().paintIcon(this, g, serpiente.getPosicionX().get(serpiente.getLongitud()-1), serpiente.getPosicionY().get(serpiente.getLongitud()-1));
        } else if (serpiente.getPosicionX().get(serpiente.getLongitud()-1) > serpiente.getPosicionX().get(serpiente.getLongitud()-2) && serpiente.getPosicionY().get(serpiente.getLongitud()-1) == serpiente.getPosicionY().get(serpiente.getLongitud()-2)) {
            Imagenes.getCR().paintIcon(this, g, serpiente.getPosicionX().get(serpiente.getLongitud()-1), serpiente.getPosicionY().get(serpiente.getLongitud()-1));
        }else {
            Imagenes.getCD().paintIcon(this, g, serpiente.getPosicionX().get(serpiente.getLongitud()-1), serpiente.getPosicionY().get(serpiente.getLongitud()-1));
        }
        switch (serpiente.getDireccion()){
            case "L" : {
                Imagenes.getLeft().paintIcon(this, g, serpiente.getPosicionX().get(0), serpiente.getPosicionY().get(0));
                break;
            }
            case "U" : {
                Imagenes.getUp().paintIcon(this, g, serpiente.getPosicionX().get(0), serpiente.getPosicionY().get(0));
                break;
            }
            case "R" : {
                Imagenes.getRight().paintIcon(this, g, serpiente.getPosicionX().get(0), serpiente.getPosicionY().get(0));
                break;
            }
            case "D" : {
                Imagenes.getDown().paintIcon(this, g, serpiente.getPosicionX().get(0), serpiente.getPosicionY().get(0));
                break;
            }
        }
        repaint();
    }
    private void solucionarCicloInfinito(){
        int maximo = 0;
        int nuevaposicion;
        aleatorio.setSeed(System.currentTimeMillis());
        boolean repite;
        do {
            repite = false;
            switch (aleatorio.nextInt(3)) {
                case 0 : {
                    nuevaposicion = serpiente.getPosicionX().get(0) + 25;
                    if (nuevaposicion < 750) {
                        alimentosX = nuevaposicion;
                        alimentosY = serpiente.getPosicionY().get(0);
                    }
                    break;
                }
                case 1 : {
                    nuevaposicion = serpiente.getPosicionY().get(0) - 25;
                    if (nuevaposicion >= 28) {
                        alimentosY = nuevaposicion;
                        alimentosX = serpiente.getPosicionX().get(0);
                    }
                    break;
                }
                case 2 : {
                    nuevaposicion = serpiente.getPosicionX().get(0) - 25;
                    if (nuevaposicion >= 10) {
                        alimentosX = nuevaposicion;
                        alimentosY = serpiente.getPosicionY().get(0);
                    }
                    break;
                }
                default : {
                    nuevaposicion = serpiente.getPosicionY().get(0) + 25;
                    if (nuevaposicion < 528) {
                        alimentosY = nuevaposicion;
                        alimentosX = serpiente.getPosicionX().get(0);
                    }
                }
            }
            int i = 0;
            while (i< serpiente.getLongitud()){
                if (serpiente.getPosicionX().get(i) == alimentosX && serpiente.getPosicionY().get(i) == alimentosY){
                    repite = true;
                    break;
                }
                i++;
            }
            maximo++;
        }while (repite && maximo < 10000);
        if (!(maximo<10000)){
            inicio = true;
            isOver = true;
            isStart = true;
            temporizador.stop();
        }
    }
    private void crecer(){
        serpiente.getPosicionX().add(-25);
        serpiente.getPosicionY().add(-25);
        serpiente.setLongitud(serpiente.getLongitud()+1);
        int limitador = 0;
        int finalizar = 0;
        do {
            alimentosX = 10 + 25*aleatorio.nextInt(30);
            aleatorio.setSeed((long) (System.nanoTime()*ThreadLocalRandom.current().nextDouble()));
            alimentosY = 28 + 25*aleatorio.nextInt(20);
            limitador++;
            if (limitador > 10){
                aleatorio.setSeed((long) (System.currentTimeMillis()*ThreadLocalRandom.current().nextDouble()));
                limitador = 0;
                finalizar++;
            }
        }while (serpiente.getPosicionX().contains(alimentosX) && serpiente.getPosicionY().contains(alimentosY) && finalizar<100);
        if (!(finalizar<100)){
            solucionarCicloInfinito();
        }//Es raro, si no break ciclo le queda infinita. los numeros de aleatorios siempre son iguales.
        generarAlimentos(getGraphics());
        repaint();
        alimentoColor = color();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && !isOver) {
            if (serpiente.getPosicionX().get(0) == alimentosX && serpiente.getPosicionY().get(0) == alimentosY) {
                crecer();
                score = ((double) (serpiente.getLongitud()-3)/597)*100;
                temporizador.setDelay(serpiente.getVelocidad());
            }
            serpiente.movimientos();
            int x = serpiente.getPosicionX().get(0);
            int y = serpiente.getPosicionY().get(0);
            int i = 1;
            while (i< serpiente.getLongitud()) {
                if (serpiente.getPosicionX().get(i) == x && serpiente.getPosicionY().get(i) == y) {
                    isOver = true;
                    break;
                }
                i++;
            }
        }
        repaint();
    }
}
