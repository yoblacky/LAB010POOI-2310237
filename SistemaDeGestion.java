import java.util.Scanner;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
public class SistemaDeGestion {
    private Vector<Paciente> listaPacientes = new Vector<Paciente>();
    private Vector<Medico> listaMedicos = new Vector<Medico>();
    private Map<String, List<Medico>> medicosPorEspecialidad = new HashMap<>();

    public void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("1. Registrar paciente");
            System.out.println("2. Eliminar paciente");
            System.out.println("3. Modificar paciente");
            System.out.println("4. Mostrar peso más repetido");
            System.out.println("5. Mostrar cantidad de pacientes por peso");
            System.out.println("6. Mostrar peso mayor y menor");
            System.out.println("7. Mostrar cantidad de pacientes por rango de peso");
            System.out.println("8. Mostrar lista de pacientes ordenados por apellidos");
            System.out.println("9. Mostrar médico que atendió a un paciente");
            System.out.println("10. Buscar doctores por especialidad");
            System.out.println("0. Salir");

            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarPaciente();
                    break;
                case 2:
                    eliminarPaciente();
                    break;
                case 3:
                    modificarPaciente();
                    break;
                case 4:
                    mostrarPesoMasRepetido();
                    break;
                case 5:
                    mostrarCantidadPorPeso();
                    break;
                case 6:
                    mostrarPesoMayorYMenor();
                    break;
                case 7:
                    mostrarCantidadPorRangoDePeso();
                    break;
                case 8:
                    mostrarListaPacientesOrdenados();
                    break;
                case 9:
                    mostrarMedicoQueAtendio();
                    break;
                case 10:
                    buscarDoctoresPorEspecialidad();
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }

        } while (opcion != 0);
    }

    private void registrarPaciente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nIngrese el DNI del paciente:");
        int dni = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el nombre del paciente:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la dirección del paciente:");
        String direccion = scanner.nextLine();

        System.out.println("Ingrese el peso del paciente:");
        double peso = scanner.nextDouble();

        System.out.println("Ingrese la temperatura del paciente:");
        double temperatura = scanner.nextDouble();

        Medico medicoAtendio = obtenerMedico();

        Paciente nuevoPaciente = new Paciente(dni, nombre, direccion, peso, temperatura, medicoAtendio);
        listaPacientes.add(nuevoPaciente);

        System.out.println("Paciente registrado con éxito.");
    }

    private void eliminarPaciente() {
        Scanner scanner = new Scanner(System.in);

        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        System.out.println("Lista de pacientes:");
        mostrarListaPacientes();

        System.out.println("Ingrese la posición del paciente que desea eliminar:");
        int posicionEliminar = scanner.nextInt();

        if (posicionEliminar < 1 || posicionEliminar > listaPacientes.size()) {
            System.out.println("Posición no válida. Intente nuevamente.");
            return;
        }

        Paciente pacienteEliminado = listaPacientes.remove(posicionEliminar - 1);

        System.out.println("Paciente eliminado con éxito:");
        mostrarDatosPaciente(pacienteEliminado);
    }

    private void modificarPaciente() {
        Scanner scanner = new Scanner(System.in);

        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados para modificar.");
            return;
        }

        System.out.println("Lista de pacientes:");
        mostrarListaPacientes();

        System.out.println("Ingrese la posición del paciente que desea modificar:");
        int posicionModificar = scanner.nextInt();

        if (posicionModificar < 1 || posicionModificar > listaPacientes.size()) {
            System.out.println("Posición no válida. Intente nuevamente.");
            return;
        }

        Paciente pacienteModificar = listaPacientes.get(posicionModificar - 1);

        System.out.println("Ingrese el nuevo DNI del paciente:");
        int nuevoDNI = scanner.nextInt();
        pacienteModificar.setDNI(nuevoDNI);
        scanner.nextLine();

        System.out.println("Ingrese el nuevo nombre del paciente:");
        String nuevoNombre = scanner.nextLine();
        pacienteModificar.setNombre(nuevoNombre);

        System.out.println("Ingrese la nueva dirección del paciente:");
        String nuevaDireccion = scanner.nextLine();
        pacienteModificar.setDireccion(nuevaDireccion);

        System.out.println("Ingrese el nuevo peso del paciente:");
        double nuevoPeso = scanner.nextDouble();
        pacienteModificar.setPeso(nuevoPeso);

        System.out.println("Ingrese la nueva temperatura del paciente:");
        double nuevaTemperatura = scanner.nextDouble();
        pacienteModificar.setTemperatura(nuevaTemperatura);

            Medico nuevoMedicoAtendio = obtenerNuevoMedico();
        pacienteModificar.setMedicoAtendio(nuevoMedicoAtendio);

        System.out.println("Paciente modificado con éxito:");
        mostrarDatosPaciente(pacienteModificar);
    }

    private void mostrarPesoMasRepetido() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        Map<Double, Integer> PromedioPesos = new HashMap<>();

        for (Paciente paciente : listaPacientes) {
            double peso = paciente.getPeso();
            PromedioPesos.put(peso, PromedioPesos.getOrDefault(peso, 0) + 1);
        }

        double pesoMasRepetido = 0;
        int maxFrecuencia = 0;

        for (Map.Entry<Double, Integer> entry : PromedioPesos.entrySet()) {
            double peso = entry.getKey();
            int frecuencia = entry.getValue();

            if (frecuencia > maxFrecuencia) {
                maxFrecuencia = frecuencia;
                pesoMasRepetido = peso;
            }
        }

        System.out.println("El peso que más se repite es: " + pesoMasRepetido + " kg");
    }

    private void mostrarCantidadPorPeso() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        Map<Double, Integer> cantidadPorPeso = new HashMap<>();

        for (Paciente paciente : listaPacientes) {
            double peso = paciente.getPeso();
            cantidadPorPeso.put(peso, cantidadPorPeso.getOrDefault(peso, 0) + 1);
        }

        System.out.println("Cantidad de pacientes por peso:");

        for (Map.Entry<Double, Integer> entry : cantidadPorPeso.entrySet()) {
            double peso = entry.getKey();
            int cantidad = entry.getValue();
            System.out.println(peso + " kg: " + cantidad + " pacientes");
        }
    }

    private void mostrarPesoMayorYMenor() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        double pesoMayor = Double.MIN_VALUE;
        double pesoMenor = Double.MAX_VALUE;

        for (Paciente paciente : listaPacientes) {
            double peso = paciente.getPeso();
            if (peso > pesoMayor) {
                pesoMayor = peso;
            }
            if (peso < pesoMenor) {
                pesoMenor = peso;
            }
        }

        System.out.println("Peso mayor: " + pesoMayor + " kg");
        System.out.println("Peso menor: " + pesoMenor + " kg");
    }

    private void mostrarCantidadPorRangoDePeso() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        double pesoMinimo = Double.MAX_VALUE;
        double pesoMaximo = Double.MIN_VALUE;

        for (Paciente paciente : listaPacientes) {
            double peso = paciente.getPeso();
            if (peso < pesoMinimo) {
                pesoMinimo = peso;
            }
            if (peso > pesoMaximo) {
                pesoMaximo = peso;
            }
        }

        double rango = 10;
        pesoMinimo = Math.floor(pesoMinimo / 10) * 10;
        pesoMaximo = Math.ceil(pesoMaximo / 10) * 10;

        Map<String, Integer> cantidadPorRango = new HashMap<>();

        for (double i = pesoMinimo; i <= pesoMaximo; i += rango) {
            double rangoInferior = i;
            double rangoSuperior = i + rango;
            String clave = String.format("%.0f - %.0f kg", rangoInferior, rangoSuperior);
            cantidadPorRango.put(clave, 0);
        }

        for (Paciente paciente : listaPacientes) {
            double peso = paciente.getPeso();
            for (double i = pesoMinimo; i <= pesoMaximo; i += rango) {
                double rangoInferior = i;
                double rangoSuperior = i + rango;
                if (peso >= rangoInferior && peso < rangoSuperior) {
                    String clave = String.format("%.0f - %.0f kg", rangoInferior, rangoSuperior);
                    cantidadPorRango.put(clave, cantidadPorRango.get(clave) + 1);
                    break;
                }
            }
        }

        System.out.println("Cantidad de pacientes por rango de peso:");

        for (Map.Entry<String, Integer> entry : cantidadPorRango.entrySet()) {
            String rangoPeso = entry.getKey();
            int cantidad = entry.getValue();
            System.out.println(rangoPeso + ": " + cantidad + " pacientes");
        }
    }

    private void mostrarListaPacientesOrdenados() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        Collections.sort(listaPacientes, Comparator.comparing(Paciente::getNombre));

        System.out.println("Lista de pacientes ordenada por nombres:");

        for (Paciente paciente : listaPacientes) {
            mostrarDatosPaciente(paciente);
            System.out.println("----------------------");
        }
    }

    private void mostrarMedicoQueAtendio() {
        Scanner scanner = new Scanner(System.in);

        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        System.out.println("Ingrese el DNI del paciente:");
        int dniBuscar = scanner.nextInt();

        Paciente pacienteEncontrado = buscarPacientePorDNI(dniBuscar);

        if (pacienteEncontrado != null) {
            Medico medicoAtendio = pacienteEncontrado.getMedicoAtendio();
            if (medicoAtendio != null) {
                System.out.println("El paciente fue atendido por el médico:");
                mostrarDatosMedico(medicoAtendio);
            } else {
                System.out.println("El paciente no tiene asignado un médico.");
            }
        } else {
            System.out.println("No se encontró un paciente con el DNI ingresado.");
        }
    }

    private void buscarDoctoresPorEspecialidad() {
        Scanner scanner = new Scanner(System.in);

        if (listaMedicos.isEmpty()) {
            System.out.println("No hay doctores registrados.");
            return;
        }

        System.out.println("Especialidades disponibles:");

        int contador = 1;
        for (String especialidad : medicosPorEspecialidad.keySet()) {
            System.out.println(contador + ". " + especialidad);
            contador++;
        }

        System.out.println("Ingrese el número de la especialidad que desea buscar:");
        int numeroEspecialidad = scanner.nextInt();

        List<Medico> medicosEspecialidad = obtenerMedicosPorNumeroEspecialidad(numeroEspecialidad);

        if (!medicosEspecialidad.isEmpty()) {
            System.out.println("Doctores con la especialidad seleccionada:");
            for (Medico medico : medicosEspecialidad) {
                mostrarDatosMedico(medico);
            }
        } else {
            System.out.println("No se encontraron doctores con la especialidad seleccionada.");
            System.out.println("----------------------");
        }
    }

    private void mostrarListaPacientes() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        for (int i = 0; i < listaPacientes.size(); i++) {
            System.out.println("Posición " + (i + 1) + ":");
            mostrarDatosPaciente(listaPacientes.get(i));
            System.out.println("----------------------");
        }
    }

    private void mostrarDatosPaciente(Paciente paciente) {
        System.out.println("DNI: " + paciente.getDNI());
        System.out.println("Nombre: " + paciente.getNombre());
        System.out.println("Dirección: " + paciente.getDireccion());
        System.out.println("Peso: " + paciente.getPeso() + " kg");
        System.out.println("Temperatura: " + paciente.getTemperatura() + " °C");

        Medico medicoAtendio = paciente.getMedicoAtendio();
        if (medicoAtendio != null) {
            System.out.println("Médico que atendió: " + medicoAtendio.getNombre() + " (Colegiatura: " + medicoAtendio.getNumeroColegiatura() + ")");
        } else {
            System.out.println("Médico que atendió: No especificado");
        }
    }

    private List<Medico> obtenerMedicosPorNumeroEspecialidad(int numeroEspecialidad) {
        int contador = 1;
        for (String especialidad : medicosPorEspecialidad.keySet()) {
            if (contador == numeroEspecialidad) {
                return medicosPorEspecialidad.get(especialidad);
            }
            contador++;
        }
        return Collections.emptyList();
    }

    private Paciente buscarPacientePorDNI(int dniBuscar) {
        for (Paciente paciente : listaPacientes) {
            if (paciente.getDNI() == dniBuscar) {
                return paciente;
            }
        }
        return null;
    }

    private void mostrarDatosMedico(Medico medico) {
        System.out.println("Nombre: " + medico.getNombre());
        System.out.println("Especialidad: " + medico.getEspecialidad());
        System.out.println("Número de colegiatura: " + medico.getNumeroColegiatura());
        System.out.println("----------------------");
    }

    private Medico obtenerMedico() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del médico:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la especialidad del médico:");
        String especialidad = scanner.nextLine();

        System.out.println("Ingrese el número de colegiatura del médico:");
        int numeroColegiatura = scanner.nextInt();
        scanner.nextLine();

        Medico nuevoMedico = new Medico(numeroColegiatura, nombre, especialidad);
        listaMedicos.add(nuevoMedico);

        medicosPorEspecialidad.computeIfAbsent(especialidad, k -> new ArrayList<>()).add(nuevoMedico);

        return nuevoMedico;
    }

    private Medico obtenerNuevoMedico() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del nuevo médico:");
        String nuevoNombre = scanner.nextLine();

        System.out.println("Ingrese la especialidad del nuevo médico:");
        String nuevaEspecialidad = scanner.nextLine();

        System.out.println("Ingrese el número de colegiatura del nuevo médico:");
        int nuevoNumeroColegiatura = scanner.nextInt();
        scanner.nextLine();

        Medico nuevoMedico = new Medico(nuevoNumeroColegiatura, nuevoNombre, nuevaEspecialidad);
        listaMedicos.add(nuevoMedico);

        System.out.println("Médico registrado con éxito.");

        return nuevoMedico;
    }

}
