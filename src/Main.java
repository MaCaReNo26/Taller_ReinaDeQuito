
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Candidata> lista = new ArrayList<>();
        Map<Integer, Integer> votos = new HashMap<>();
        int opcion;
        do {
            System.out.println("\n-----------|| SISTEMA ELECCION DE LA REINA DE QUITO ||-----------");
            System.out.println("1. Registrar candidata");
            System.out.println("2. Mostrar candidatas");
            System.out.println("3. Buscar por nombre");
            System.out.println("4. Buscar por distrito");
            System.out.println("5. Votar por candidata");
            System.out.println("6. Simular votos");
            System.out.println("7. Mostrar ganadora");
            System.out.println("8. Salir");
            System.out.print("Ingrese una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1 -> registrarCandidata(sc, lista);
                case 2 -> mostrarCandidatas(lista);
                case 3 -> buscarPorNombre(sc, lista);
                case 4 -> buscarPorDistrito(sc, lista);
                case 5 -> votar(sc, lista, votos);
                case 6 -> simularVotos(lista, votos);
                case 7 -> mostrarGanadora(lista, votos);
                case 8 -> System.out.println("Saliendo...");
                default -> System.out.println("OPCION INVALIDA");
            }
        } while (opcion != 8);
    }

    public static void registrarCandidata(Scanner sc, ArrayList<Candidata> lista) {
        try {
            System.out.println("-----------|| Registrando Candidata ||-----------");
            System.out.print("ID: ");
            int id = sc.nextInt(); sc.nextLine();
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Edad: ");
            int edad = sc.nextInt(); sc.nextLine();
            System.out.print("Distrito: ");
            String distrito = sc.nextLine();
            System.out.print("Puntaje Jurado: ");
            double puntaje = sc.nextDouble(); sc.nextLine();
            System.out.println("Tipo:");
            System.out.println("1. Estudiante");
            System.out.println("2. Profesional");
            System.out.print("Seleccione el tipo de candidata: ");
            int tipo = sc.nextInt(); sc.nextLine();
            Candidata c;
            if (tipo == 1) {
                System.out.print("Universidad: ");
                String u = sc.nextLine();
                System.out.print("Carrera: ");
                String ca = sc.nextLine();
                c = new CandidataEstudiante(id, nombre, edad, distrito, puntaje, u, ca);
            } else {
                System.out.print("Profesion: ");
                String p = sc.nextLine();
                System.out.print("Años de experiencia: ");
                int a = sc.nextInt(); sc.nextLine();
                c = new CandidataProfesional(id, nombre, edad, distrito, puntaje, p, a);
            }
            lista.add(c);
            System.out.println("Candidata registrada con exito");
        } catch (DatoInvalidoException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void mostrarCandidatas(ArrayList<Candidata> lista) {
        System.out.println("-----------|| Candidatas Registradas ||-----------");
        if (lista.isEmpty()) {
            System.out.println("No existen candidatas");
            return;
        }
        for (Candidata c : lista) c.mostrarDetalles();
    }

    public static void buscarPorNombre(Scanner sc, ArrayList<Candidata> lista) {
        System.out.println("-----------|| Buscando Candidata ||-----------");
        System.out.print("Nombre: ");
        String nom = sc.nextLine();
        boolean existe = false;
        for (Candidata c : lista) {
            if (c.getNombre().equalsIgnoreCase(nom)) {
                c.mostrarDetalles();
                existe = true;
            }
        }
        if (!existe) System.out.println("Candidata no encontrada");
    }

    public static void buscarPorDistrito(Scanner sc, ArrayList<Candidata> lista) {
        System.out.println("-----------|| Buscando Candidata por Distrito ||-----------");
        System.out.print("Distrito: ");
        String dis = sc.nextLine();
        boolean existe = false;
        for (Candidata c : lista) {
            if (c.getDistrito().equalsIgnoreCase(dis)) {
                c.mostrarDetalles();
                existe = true;
            }
        }
        if (!existe) System.out.println("No existen candidatas en ese distrito");
    }

    public static void votar(Scanner sc, ArrayList<Candidata> lista, Map<Integer, Integer> votos) {
        System.out.println("-----------|| Etapa de votacion ||-----------");
        if (lista.isEmpty()) {
            System.out.println("Candidas no registradas");
            return;
        }
        System.out.println("LISTA DE CANDIDATAS:");
        for (Candidata c : lista) {
            System.out.println("ID: " + c.getId() + " - " + c.getNombre());
        }
        System.out.print("Ingrese ID para votar: ");
        int id = sc.nextInt(); sc.nextLine();
        boolean existe = false;
        for (Candidata c : lista) {
            if (c.getId() == id) existe = true;
        }
        if (!existe) {
            System.out.println("ID no registrado");
            return;
        }
        votos.put(id, votos.getOrDefault(id, 0) + 1);
        System.out.println("Voto registrado");
    }

    public static void simularVotos(ArrayList<Candidata> lista, Map<Integer, Integer> votos) {
        System.out.println("-----------|| Etapa de votacion ||-----------");
        if (lista.isEmpty()) {
            System.out.println("Aún no hay candidatas.");
            return;
        }
        Random r = new Random();
        for (int i = 0; i < 50; i++) {
            int pos = r.nextInt(lista.size());
            int id = lista.get(pos).getId();
            votos.put(id, votos.getOrDefault(id, 0) + 1);
        }
        System.out.println("50 votos simulados.");
    }

    public static void mostrarGanadora(ArrayList<Candidata> lista, Map<Integer, Integer> votos) {
        if (votos.isEmpty()) {
            System.out.println("No hay votos registrados.");
            return;
        }
        int idGanadora = -1;
        int maxVotos = -1;
        for (Map.Entry<Integer, Integer> entry : votos.entrySet()) {
            int id = entry.getKey();
            int cantidad = entry.getValue();
            if (cantidad > maxVotos) {
                maxVotos = cantidad;
                idGanadora = id;
            }
        }
        Candidata ganadora = null;
        for (Candidata c : lista) {
            if (c.getId() == idGanadora) {
                ganadora = c;
                break;
            }
        }
        if (ganadora == null) {
            System.out.println("Error: candidata no encontrada.");
            return;
        }
        System.out.println("\n-----------|| GANADORA ||-----------");
        System.out.println("Votos obtenidos: " + maxVotos);
        ganadora.mostrarDetalles();
    }

}
