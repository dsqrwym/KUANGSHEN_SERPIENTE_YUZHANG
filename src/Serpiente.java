import java.util.ArrayList;

public class Serpiente {
    private int longitud;
    private int velocidad;
    private String direccion;
    private final ArrayList<Integer> posicionX = new ArrayList<>();
    private final ArrayList<Integer> posicionY = new ArrayList<>();
    public Serpiente(){
        longitud = 3;
        direccion = "R";
        posicionX.add(110); posicionY.add(78);
        posicionX.add(85); posicionY.add(78);
        posicionX.add(60); posicionY.add(78);
        posicionX.add(35); posicionY.add(78);
        velocidad = 500;
    }

    private void setVelocidad() {
        this.velocidad = (int) (500+(double)((longitud-3)*(1-500)/(599-3)));
    }
    public int getLongitud() {
        return longitud;
    }
    public ArrayList<Integer> getPosicionX() {
        return posicionX;
    }
    public ArrayList<Integer> getPosicionY() {
        return posicionY;
    }
    public String getDireccion() {
        return direccion;
    }
    public int getVelocidad() {
        return velocidad;
    }
    public void setLongitud(int longitud) {
        this.longitud = longitud;
        setVelocidad();
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void movimientos(){
        for (int i = longitud; i>0; i--){
            posicionX.set(i, posicionX.get(i-1));
            posicionY.set(i, posicionY.get(i-1));
        }
        switch (direccion) {
            case "R" -> {
                    posicionX.set(0, posicionX.get(0) + 25);
                    if (posicionX.get(0) > 750) {
                        posicionX.set(0, 10);
                    }
                }
            case "U" -> {
                posicionY.set(0, posicionY.get(0) - 25);
                if (posicionY.get(0) < 28){
                    posicionY.set(0, 528);
                }
            }
            case "L" -> {
                posicionX.set(0, posicionX.get(0) - 25);
                if (posicionX.get(0) < 10){
                    posicionX.set(0, 735);
                }
            }
            default -> {
                posicionY.set(0, posicionY.get(0) + 25);
                if (posicionY.get(0) > 528){
                    posicionY.set(0, 28);
                }
            }
        }
    }
}
