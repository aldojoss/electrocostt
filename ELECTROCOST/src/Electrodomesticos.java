public class Electrodomesticos extends Modelo {
    
    public Electrodomesticos (String name, int usodario, double volteos, double consumomensual){
        super(name, usodario, volteos, consumomensual);
    }

    //METODO SOBREESCRITO
    @Override
    public String toString(){
        return getName()+"---"+getUsodiario()+"---"+getVolteos()+"---"+getConsumomensual();
    }
}