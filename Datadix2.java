import java.util.Scanner;

public class Datadix2 {

    static String clientes[];
    static String tipos[];
    static String fechas[];
    static String horas[];
    static double anticipos[];

    static String auxiliar_clientes[];
    static String auxiliar_tipos[];
    static String auxiliar_fechas[];
    static String auxiliar_horas[];
    static double auxiliar_anticipos[];

    static String auxiliar2_clientes[];
    static String auxiliar2_tipos[];
    static String auxiliar2_fechas[];
    static String auxiliar2_horas[];
    static double auxiliar2_anticipos[];

    static int capacidad = 0; // Inicia en 0 para que no haya registros de prueba
    static int contador_de_registros = 0;

    public static void main(String a[]) {
        Scanner recibir = new Scanner(System.in);

        clientes = new String[capacidad];
        tipos = new String[capacidad];
        fechas = new String[capacidad];
        horas = new String[capacidad];
        anticipos = new double[capacidad];

        System.out.println("=================================");
        System.out.println(" DATADIX STUDIO ");
        System.out.println("=================================");

        int respuesta_inicial = menu_inicial();

        while (respuesta_inicial != 0) {
            switch (respuesta_inicial) {
                case 1:
                    mostrarInformacion();
                    System.out.println("-------------------------------------");
                    break;
                case 2:
                    mostrarTipos();
                    System.out.println("-------------------------------------");
                    break;
                case 3:
                    registrarSesion();
                    System.out.println("-------------------------------------");
                    break;
                case 4:
                    mostrarSesiones();
                    System.out.println("-------------------------------------");
                    break;
                case 5:
                    buscarCliente();
                    System.out.println("-------------------------------------");
                    break;
                case 6:
                    eliminarSesion();
                    System.out.println("-------------------------------------");
                    break;
            }

            System.out.println("¿Desea realizar otra acción?");
            System.out.println("1 - Sí");
            System.out.println("0 - No (Salir)");
            System.out.print(">");
            int repetir = recibir.nextInt();

            while (repetir != 1 && repetir != 0) {
                System.out.println("Ingrese una opción válida");
                repetir = recibir.nextInt();
            }

            if (repetir == 1) {
                respuesta_inicial = menu_inicial();
            } else {
                respuesta_inicial = 0;
            }
        }
        System.out.println("\nGracias por usar DATADIX STUDIO. ¡Hasta luego! (^^)");
    }

    static int menu_inicial() {
        int respuesta;
        Scanner recibir = new Scanner(System.in);
        System.out.println("\nSeleccione la opción que desee realizar:");
        System.out.println("1 - ¿Qué son los retratos?");
        System.out.println("2 - Tipos de retrato");
        System.out.println("3 - Agendar sesión nueva");
        System.out.println("4 - Ver sesiones registradas");
        System.out.println("5 - Buscar cliente");
        System.out.println("6 - Eliminar sesión");
        System.out.println("0 - Salir");
        System.out.print(">");
        respuesta = recibir.nextInt();

        while (respuesta < 0 || respuesta > 6) {
            System.out.print("Ingrese una opción válida: >");
            respuesta = recibir.nextInt();
        }
        return respuesta;
    }

    static void mostrarInformacion() {
        System.out.println("\n¿QUÉ SON LOS RETRATOS?");
        System.out.println("Los retratos son fotografías que capturan momentos importantes de personas, familias, parejas o mascotas.");
    }

    static void mostrarTipos() {
        System.out.println("\nTIPOS DE RETRATO");
        System.out.println("1. Individual");
        System.out.println("2. Familiar");
        System.out.println("3. Embarazo");
        System.out.println("4. Bebés");
        System.out.println("5. Parejas");
        System.out.println("6. Mascotas");
        System.out.println("\nCosto por sesión: $900");
    }

    static void registrarSesion() {
        Scanner recibir = new Scanner(System.in);
        capacidad++;

        auxiliar_clientes = new String[capacidad];
        auxiliar_tipos = new String[capacidad];
        auxiliar_fechas = new String[capacidad];
        auxiliar_horas = new String[capacidad];
        auxiliar_anticipos = new double[capacidad];

        if (contador_de_registros == 0) {
            for (int z = 0; z < clientes.length; z++) {
                auxiliar_clientes[z] = clientes[z];
                auxiliar_tipos[z] = tipos[z];
                auxiliar_fechas[z] = fechas[z];
                auxiliar_horas[z] = horas[z];
                auxiliar_anticipos[z] = anticipos[z];
            }
        } else {
            for (int z = 0; z < auxiliar2_clientes.length; z++) {
                auxiliar_clientes[z] = auxiliar2_clientes[z];
                auxiliar_tipos[z] = auxiliar2_tipos[z];
                auxiliar_fechas[z] = auxiliar2_fechas[z];
                auxiliar_horas[z] = auxiliar2_horas[z];
                auxiliar_anticipos[z] = auxiliar2_anticipos[z];
            }
        }

        System.out.print("\nNombre del cliente: ");
        String nuevo_cliente = recibir.nextLine();
        System.out.print("Tipo de retrato: ");
        String nuevo_tipo = recibir.nextLine();
        System.out.print("Fecha de la sesión: ");
        String nueva_fecha = recibir.nextLine();
        System.out.print("Hora de la sesión: ");
        String nueva_hora = recibir.nextLine();
        System.out.print("Anticipo: ");
        double nuevo_anticipo = recibir.nextDouble();

        auxiliar_clientes[capacidad - 1] = nuevo_cliente;
        auxiliar_tipos[capacidad - 1] = nuevo_tipo;
        auxiliar_fechas[capacidad - 1] = nueva_fecha;
        auxiliar_horas[capacidad - 1] = nueva_hora;
        auxiliar_anticipos[capacidad - 1] = nuevo_anticipo;

        auxiliar2_clientes = new String[capacidad];
        auxiliar2_tipos = new String[capacidad];
        auxiliar2_fechas = new String[capacidad];
        auxiliar2_horas = new String[capacidad];
        auxiliar2_anticipos = new double[capacidad];

        for (int x = 0; x < capacidad; x++) {
            auxiliar2_clientes[x] = auxiliar_clientes[x];
            auxiliar2_tipos[x] = auxiliar_tipos[x];
            auxiliar2_fechas[x] = auxiliar_fechas[x];
            auxiliar2_horas[x] = auxiliar_horas[x];
            auxiliar2_anticipos[x] = auxiliar_anticipos[x];
        }

        contador_de_registros++;
        System.out.println("\n¡Sesión registrada correctamente!");

        // Generación del Ticket
        System.out.println("\n=================================");
        System.out.println(" TICKET DE COMPROBANTE ");
        System.out.println("=================================");
        System.out.println("No. de Sesión : " + capacidad);
        System.out.println("Cliente : " + nuevo_cliente);
        System.out.println("Tipo : " + nuevo_tipo);
        System.out.println("Fecha : " + nueva_fecha);
        System.out.println("Hora : " + nueva_hora);
        System.out.println("Costo total : $900.0");
        System.out.println("Anticipo : $" + nuevo_anticipo);
        System.out.println("Restante : $" + (900.0 - nuevo_anticipo));
        System.out.println("=================================");
        System.out.println("¡Gracias por confiar en Datadix!");
    }

    static void mostrarSesiones() {
        if (capacidad == 0) {
            System.out.println("\nNo hay sesiones registradas.");
            return;
        }

        System.out.println("\n====== SESIONES REGISTRADAS ======");
        int totalActual = (contador_de_registros == 0) ? clientes.length : capacidad;

        for (int y = 0; y < totalActual; y++) {
            String c = (contador_de_registros == 0) ? clientes[y] : auxiliar2_clientes[y];
            String t = (contador_de_registros == 0) ? tipos[y] : auxiliar2_tipos[y];
            String f = (contador_de_registros == 0) ? fechas[y] : auxiliar2_fechas[y];
            String h = (contador_de_registros == 0) ? horas[y] : auxiliar2_horas[y];
            double ant = (contador_de_registros == 0) ? anticipos[y] : auxiliar2_anticipos[y];

            System.out.println("\nSesión #" + (y + 1));
            System.out.println("Cliente : " + c);
            System.out.println("Tipo : " + t);
            System.out.println("Fecha : " + f);
            System.out.println("Hora : " + h);
            System.out.println("Anticipo: $" + ant);
            System.out.println("Restante: $" + (900.0 - ant));
        }
    }

    static void buscarCliente() {
        Scanner recibir = new Scanner(System.in);
        if (capacidad == 0) {
            System.out.println("\nNo hay registros para buscar.");
            return;
        }

        System.out.print("\nIngrese el nombre del cliente que desea buscar: >");
        String dato_a_buscar = recibir.nextLine();
        int contador_repeticion = 0;
        int totalActual = (contador_de_registros == 0) ? clientes.length : capacidad;

        System.out.println("\n--- Resultados de Búsqueda ---");
        for (int x = 0; x < totalActual; x++) {
            String c = (contador_de_registros == 0) ? clientes[x] : auxiliar2_clientes[x];
            if (c.equalsIgnoreCase(dato_a_buscar)) {
                contador_repeticion++;
                String t = (contador_de_registros == 0) ? tipos[x] : auxiliar2_tipos[x];
                String f = (contador_de_registros == 0) ? fechas[x] : auxiliar2_fechas[x];
                String h = (contador_de_registros == 0) ? horas[x] : auxiliar2_horas[x];

                System.out.println("Sesión No. " + (x + 1));
                System.out.println("Tipo : " + t);
                System.out.println("Fecha: " + f);
                System.out.println("Hora : " + h);
                System.out.println("-------------------------");
            }
        }
        System.out.println("Se encontró al cliente " + contador_repeticion + " vez(ces).");
    }

    static void eliminarSesion() {
        Scanner recibir = new Scanner(System.in);
        if (capacidad == 0) {
            System.out.println("\nNo hay sesiones para eliminar.");
            return;
        }

        int totalActual = (contador_de_registros == 0) ? clientes.length : capacidad;

        mostrarSesiones();
        System.out.print("\nIngrese el número de sesión que desee eliminar: >");
        int dato_a_eliminar = recibir.nextInt();

        while (dato_a_eliminar < 1 || dato_a_eliminar > totalActual) {
            System.out.print("ERROR - Ingrese un número de sesión válido por favor: >");
            dato_a_eliminar = recibir.nextInt();
        }

        capacidad--;
        auxiliar_clientes = new String[capacidad];
        auxiliar_tipos = new String[capacidad];
        auxiliar_fechas = new String[capacidad];
        auxiliar_horas = new String[capacidad];
        auxiliar_anticipos = new double[capacidad];

        int i = 0;
        if (contador_de_registros == 0) {
            clientes[dato_a_eliminar - 1] = null;
            for (int x = 0; x < clientes.length; x++) {
                if (clientes[x] != null) {
                    auxiliar_clientes[i] = clientes[x];
                    auxiliar_tipos[i] = tipos[x];
                    auxiliar_fechas[i] = fechas[x];
                    auxiliar_horas[i] = horas[x];
                    auxiliar_anticipos[i] = anticipos[x];
                    i++;
                }
            }
        } else {
            auxiliar2_clientes[dato_a_eliminar - 1] = null;
            for (int x = 0; x < auxiliar2_clientes.length; x++) {
                if (auxiliar2_clientes[x] != null) {
                    auxiliar_clientes[i] = auxiliar2_clientes[x];
                    auxiliar_tipos[i] = auxiliar2_tipos[x];
                    auxiliar_fechas[i] = auxiliar2_fechas[x];
                    auxiliar_horas[i] = auxiliar2_horas[x];
                    auxiliar_anticipos[i] = auxiliar2_anticipos[x];
                    i++;
                }
            }
        }

        auxiliar2_clientes = new String[capacidad];
        auxiliar2_tipos = new String[capacidad];
        auxiliar2_fechas = new String[capacidad];
        auxiliar2_horas = new String[capacidad];
        auxiliar2_anticipos = new double[capacidad];

        for (int x = 0; x < capacidad; x++) {
            auxiliar2_clientes[x] = auxiliar_clientes[x];
            auxiliar2_tipos[x] = auxiliar_tipos[x];
            auxiliar2_fechas[x] = auxiliar_fechas[x];
            auxiliar2_horas[x] = auxiliar_horas[x];
            auxiliar2_anticipos[x] = auxiliar_anticipos[x];
        }

        contador_de_registros++;
        System.out.println("\n¡Sesión eliminada correctamente!");
    }
}

