public abstract class Candidata {
    private int id;
    private String nombre;
    private int edad;
    private String distrito;
    private double puntajeJurado;


    public Candidata(int id, String nombre, int edad, String distrito, double puntajeJurado)
            throws DatoInvalidoException {
        if (id <= 0) throw new DatoInvalidoException("El ID debe ser positivo.");
        this.id = id;
        setNombre(nombre);
        setEdad(edad);
        setDistrito(distrito);
        setPuntajeJurado(puntajeJurado);
    }
    public int getId() { return id; }
    public String getDistrito() { return distrito;}
    public int getEdad() { return edad;}
    public String getNombre() {return nombre;}
    public double getPuntajeJurado() {return puntajeJurado;}

    public void setDistrito(String distrito) throws DatoInvalidoException {
        if (distrito == null || distrito.isBlank())
            throw new DatoInvalidoException("El distrito no puede estar vacío.");
        this.distrito = distrito;

    }
    public void setEdad(int edad) throws DatoInvalidoException {
        if (edad < 16)
            throw new DatoInvalidoException("La edad mínima es 16 años.");
        this.edad = edad;
    }
    public void setNombre(String nombre) throws DatoInvalidoException {
        if (nombre == null || nombre.isBlank())
            throw new DatoInvalidoException("El nombre no puede estar vacío.");
        this.nombre = nombre;
    }

    public void setPuntajeJurado(double puntajeJurado) throws DatoInvalidoException {
        if (puntajeJurado < 0 || puntajeJurado > 100)
            throw new DatoInvalidoException("El puntaje del jurado debe estar entre 0 y 100");
        this.puntajeJurado = puntajeJurado;
    }

    public abstract void mostrarDetalles();
}
