public class CandidataProfesional extends Candidata {

    private String profesion;
    private int añosExperiencia;

    public CandidataProfesional(int id, String nombre, int edad, String distrito,
                                double puntajeJurado, String profesion, int años)
            throws DatoInvalidoException {
        super(id, nombre, edad, distrito, puntajeJurado);

        if (profesion.isBlank() || años < 0)
            throw new DatoInvalidoException("Profesión no válida o años negativos.");

        this.profesion = profesion;
        this.añosExperiencia = años ;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("\n-----------|| Candidata Profesional ||-----------");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Edad: " + getEdad() + " – Distrito: " + getDistrito());
        System.out.println("Profesión: " + profesion);
        System.out.println("Experiencia: " + añosExperiencia + " años");
        System.out.println("Puntaje Jurado: " + getPuntajeJurado());
    }
}
