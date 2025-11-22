public class CandidataEstudiante extends Candidata {

    private String universidad;
    private String carrera;

    public CandidataEstudiante(int id, String nombre, int edad, String distrito,
                               double puntajeJurado, String universidad, String carrera)
            throws DatoInvalidoException {
        super(id, nombre, edad, distrito, puntajeJurado);

        if (universidad.isBlank() || carrera.isBlank())
            throw new DatoInvalidoException("Universidad y carrera no pueden estar vacías.");

        this.universidad = universidad;
        this.carrera = carrera;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("\n-----------|| Candidata Estudiante ||-----------");
        System.out.println("ID: " + getId());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Edad: " + getEdad());
        System.out.println("Distrito: " + getDistrito());
        System.out.println("Universidad: " + universidad);
        System.out.println("Carrera: " + carrera);
        System.out.println("Puntaje Jurado: " + getPuntajeJurado());
    }
}
