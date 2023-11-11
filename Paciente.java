public class Paciente {
    private int DNI;
    private String nombre;
    private String direccion;
    private double peso;
    private double temperatura;
    private Medico medicoAtendio;

    public Paciente(int DNI, String nombre, String direccion, double peso, double temperatura, Medico medicoAtendio) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.direccion = direccion;
        this.peso = peso;
        this.temperatura = temperatura;
        this.medicoAtendio = medicoAtendio;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public Medico getMedicoAtendio() {
        return medicoAtendio;
    }

    public void setMedicoAtendio(Medico medicoAtendio) {
        this.medicoAtendio = medicoAtendio;
    }
}
