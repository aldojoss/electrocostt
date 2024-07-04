public abstract class Modelo {

    //Protegido
    protected String name;
    protected int usodiario;
    protected double volteos;
    protected double consumomensual;

//Esta protegido para que solo sus calses hijas puedan usarlo
protected Modelo(String name, int usodiario, double volteos, double consumomensual){
        this.name = name;
        this.usodiario = usodiario;
        this.volteos = volteos;
        this.consumomensual = consumomensual;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public int getUsodiario() {
    return usodiario;
}

public void setUsodiario(int usodiario) {
    this.usodiario = usodiario;
}

public double getConsumomensual() {
    return consumomensual;
}
public void setConsumomensual(double consumomensual) {
    this.consumomensual = consumomensual;
}

public double getVolteos() {
    return volteos;
}
public void setVolteos(double volteos) {
    this.volteos = volteos;
}

//METODO ABSTRACTO
public abstract String toString();

}