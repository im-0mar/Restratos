import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Retratos  {

    static ArrayList<Sesion> sesiones = new ArrayList<>();

    static final String TITULO = "DATADIX STUDIO";
    static final String UBICACION = "Miguel Hidalgo, CDMX";
    static final BigDecimal PRECIO_BASE = new BigDecimal("900.00");

    static final String[] TIPOS_RETRATO = {
            "Individual",
            "Familiar",
            "Embarazo",
            "Bebés (Newborn)",
            "Parejas",
            "Mascotas"
    };

    static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    static final DateTimeFormatter FORMATO_HORA =
            DateTimeFormatter.ofPattern("HH:mm");

    // PALETA DE COLORES ESTRICTAMENTE BLANCO Y NEGRO (MINIMALISTA)
    static final Color COLOR_FONDO = Color.WHITE;
    static final Color COLOR_TEXTO = new Color(15, 15, 15);
    static final Color COLOR_BORDE = new Color(40, 40, 40);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> iniciarPrograma());
    }

    // MENÚ PRINCIPAL
    public static void iniciarPrograma() {
        while (true) {
            String[] opciones = {
                    "¿Qué son los retratos?",
                    "Tipos de retrato",
                    "Agendar sesión",
                    "Ver sesiones registradas",
                    "Salir"
            };

            int opcion = JOptionPane.showOptionDialog(
                    null,
                    crearPanelCentrado(
                            "D A T A D I X   S T U D I O\n\n" +
                                    "─────────────────────────────────────────\n" +
                                    "SISTEMA DE ADMINISTRACIÓN DE CLIENTES\n" +
                                    "─────────────────────────────────────────\n\n" +
                                    "Selecciona una opción del menú:"
                    ),
                    TITULO,
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (opcion) {
                case 0:
                    mostrarInformacion();
                    break;
                case 1:
                    mostrarTiposRetrato();
                    break;
                case 2:
                    agendarSesion();
                    break;
                case 3:
                    mostrarSesiones();
                    break;
                case 4:
                case JOptionPane.CLOSED_OPTION:
                    salirPrograma();
                    return;
                default:
                    salirPrograma();
                    return;
            }
        }
    }

    // INFORMACIÓN DE LOS RETRATOS
    public static void mostrarInformacion() {
        mostrarMensajeCentrado(
                "¿QUÉ SON LOS RETRATOS?\n\n" +
                        "Los retratos son fotografías que buscan\n" +
                        "representar la personalidad, expresión y\n" +
                        "características de una persona, familia o mascota.\n\n" +
                        "En DATADIX STUDIO conservamos tus momentos más especiales."
        );
    }

    // TIPOS DE RETRATO
    public static void mostrarTiposRetrato() {
        mostrarMensajeCentrado(
                "TIPOS DE RETRATO DISPONIBLES\n\n" +
                        "• Individual\n" +
                        "• Familiar\n" +
                        "• Embarazo\n" +
                        "• Bebés (Newborn)\n" +
                        "• Parejas\n" +
                        "• Mascotas\n\n" +
                        "Tarifa única por sesión: $900.00 MXN"
        );
    }

    // AGENDAR UNA SESIÓN
    public static void agendarSesion() {
        String nombre = pedirEntradaCentrada(
                "REGISTRO DE NUEVA SESIÓN\n\n" +
                        "Escribe el nombre completo del cliente:"
        );

        if (nombre == null) return;
        nombre = nombre.trim();
        if (nombre.isEmpty()) {
            mostrarMensajeCentrado("El nombre no puede estar vacío.");
            return;
        }

        String tipo = (String) JOptionPane.showInputDialog(
                null,
                crearPanelCentrado("Selecciona el tipo de retrato ($900.00):"),
                TITULO,
                JOptionPane.QUESTION_MESSAGE,
                null,
                TIPOS_RETRATO,
                TIPOS_RETRATO[0]
        );

        if (tipo == null) return;

        LocalDate fecha = pedirFecha();
        if (fecha == null) return;

        LocalTime hora = pedirHora();
        if (hora == null) return;

        // REVISAR RESERVACIONES DEL MISMO DÍA / HORA
        boolean mismoDia = false;
        boolean mismaHora = false;

        for (Sesion sesion : sesiones) {
            if (sesion.fecha.equals(fecha)) {
                mismoDia = true;
                if (sesion.hora.equals(hora)) {
                    mismaHora = true;
                }
            }
        }

        if (mismoDia) {
            String mensaje = "AVISO DE AGENDA\n\nYa existen sesiones este día (" + fecha.format(FORMATO_FECHA) + ").\n\n";
            for (Sesion sesion : sesiones) {
                if (sesion.fecha.equals(fecha)) {
                    mensaje += "• " + sesion.nombre + " — " + sesion.hora.format(FORMATO_HORA) + " (" + sesion.tipo + ")\n";
                }
            }
            mensaje += "\n¿Deseas continuar con esta reservación?";

            int continuar = JOptionPane.showConfirmDialog(
                    null, crearPanelCentrado(mensaje), TITULO, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE
            );
            if (continuar != JOptionPane.YES_OPTION) return;
        }

        if (mismaHora) {
            int confirmar = JOptionPane.showConfirmDialog(
                    null,
                    crearPanelCentrado("¡ATENCIÓN!\n\nYa hay una cita exactamente a esta misma hora.\n¿Deseas duplicar horario?"),
                    TITULO, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE
            );
            if (confirmar != JOptionPane.YES_OPTION) return;
        }

        // ANTICIPO
        int respuestaAnticipo = JOptionPane.showConfirmDialog(
                null,
                crearPanelCentrado("¿El cliente realizará un anticipo?"),
                TITULO, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (respuestaAnticipo == JOptionPane.CLOSED_OPTION) return;

        BigDecimal anticipo = BigDecimal.ZERO;

        if (respuestaAnticipo == JOptionPane.YES_OPTION) {
            while (true) {
                String cantidad = pedirEntradaCentrada(
                        "CONTROL DE ANTICIPO\n\n" +
                                "Costo total del servicio: $900.00\n\n" +
                                "Escribe la cantidad del anticipo:"
                );
                if (cantidad == null) return;

                try {
                    anticipo = new BigDecimal(cantidad.trim().replace(",", "."));
                    if (anticipo.compareTo(BigDecimal.ZERO) < 0) {
                        mostrarMensajeCentrado("El anticipo no puede ser negativo.");
                        continue;
                    }
                    if (anticipo.compareTo(PRECIO_BASE) > 0) {
                        mostrarMensajeCentrado("El anticipo no puede ser mayor al costo total ($900.00).");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    mostrarMensajeCentrado("Cantidad no válida. Escribe un número correcto.");
                }
            }
        }

        Sesion nueva = new Sesion(nombre, tipo, fecha, hora, anticipo);
        BigDecimal resto = PRECIO_BASE.subtract(anticipo);

        // CONFIRMAR DATOS
        int confirmar = JOptionPane.showConfirmDialog(
                null,
                crearPanelCentrado(
                        "CONFIRMAR RESERVACIÓN\n\n" +
                                "────────────────────────────────────────\n" +
                                "Cliente: " + nombre + "\n" +
                                "Servicio: " + tipo + "\n" +
                                "Fecha: " + fecha.format(FORMATO_FECHA) + "\n" +
                                "Hora: " + hora.format(FORMATO_HORA) + "\n" +
                                "Costo Total: $900.00\n" +
                                "Anticipo: $" + anticipo.toPlainString() + "\n" +
                                "Pendiente: $" + resto.toPlainString() + "\n" +
                                "────────────────────────────────────────\n\n" +
                                "¿Deseas registrar esta sesión?"
                ),
                TITULO, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (confirmar != JOptionPane.YES_OPTION) return;

        sesiones.add(nueva);
        mostrarTicket(nueva);
    }

    public static LocalDate pedirFecha() {
        while (true) {
            String texto = pedirEntradaCentrada(
                    "FECHA DE LA SESIÓN\n\nFormato requerido: DD/MM/AAAA\nEjemplo: 25/12/2026"
            );
            if (texto == null) return null;

            try {
                LocalDate fecha = LocalDate.parse(texto.trim(), FORMATO_FECHA);
                if (fecha.isBefore(LocalDate.now())) {
                    mostrarMensajeCentrado("No puedes registrar una fecha pasada.");
                    continue;
                }
                return fecha;
            } catch (DateTimeParseException e) {
                mostrarMensajeCentrado("Fecha incorrecta. Utiliza el formato DD/MM/AAAA.");
            }
        }
    }

    public static LocalTime pedirHora() {
        while (true) {
            String texto = pedirEntradaCentrada(
                    "HORA DE LA SESIÓN\n\nFormato de 24 horas: HH:mm\nEjemplo: 15:30"
            );
            if (texto == null) return null;

            try {
                return LocalTime.parse(texto.trim(), FORMATO_HORA);
            } catch (DateTimeParseException e) {
                mostrarMensajeCentrado("Hora incorrecta. Utiliza el formato HH:mm.");
            }
        }
    }

    public static void mostrarTicket(Sesion sesion) {
        BigDecimal resto = PRECIO_BASE.subtract(sesion.anticipo);
        String ticket =
                "D A T A D I X   S T U D I O\n" +
                        "TICKET DE RESERVACIÓN\n\n" +
                        "────────────────────────────────────────\n\n" +
                        "Cliente: " + sesion.nombre + "\n" +
                        "Servicio: " + sesion.tipo + "\n" +
                        "Fecha: " + sesion.fecha.format(FORMATO_FECHA) + "\n" +
                        "Hora: " + sesion.hora.format(FORMATO_HORA) + "\n\n" +
                        "Precio Total: $900.00\n" +
                        "Anticipo: $" + sesion.anticipo.toPlainString() + "\n" +
                        "Restante: $" + resto.toPlainString() + "\n\n" +
                        "Estado: CONFIRMADO\n" +
                        "Ubicación: " + UBICACION + "\n\n" +
                        "────────────────────────────────────────\n" +
                        "¡Gracias por elegirnos!";

        mostrarMensajeCentrado(ticket);
    }

    public static void mostrarSesiones() {
        if (sesiones.isEmpty()) {
            mostrarMensajeCentrado("SESIONES REGISTRADAS\n\nNo hay reservaciones activas en este momento.");
            return;
        }

        ArrayList<Sesion> ordenadas = new ArrayList<>(sesiones);
        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();

        ordenadas.sort((a, b) -> {
            boolean aPasada = esPasada(a, hoy, ahora);
            boolean bPasada = esPasada(b, hoy, ahora);
            if (aPasada != bPasada) return Boolean.compare(aPasada, bPasada);
            if (!aPasada) {
                int comp = a.fecha.compareTo(b.fecha);
                return (comp == 0) ? a.hora.compareTo(b.hora) : comp;
            } else {
                int comp = b.fecha.compareTo(a.fecha);
                return (comp == 0) ? b.hora.compareTo(a.hora) : comp;
            }
        });

        StringBuilder lista = new StringBuilder();
        lista.append("LISTADO GENERAL DE SESIONES\n\n");

        for (int i = 0; i < ordenadas.size(); i++) {
            Sesion s = ordenadas.get(i);
            String estado = esPasada(s, hoy, ahora) ? "PASADA" : "PRÓXIMA";
            BigDecimal resto = PRECIO_BASE.subtract(s.anticipo);

            lista.append("[").append(i + 1).append("] ").append(s.nombre).append(" — ").append(estado).append("\n");
            lista.append("   Servicio: ").append(s.tipo).append(" | $900.00\n");
            lista.append("   Fecha: ").append(s.fecha.format(FORMATO_FECHA)).append("  Hora: ").append(s.hora.format(FORMATO_HORA)).append("\n");
            lista.append("   Anticipo: $").append(s.anticipo.toPlainString()).append(" | Restante: $").append(resto.toPlainString()).append("\n");
            lista.append("────────────────────────────────────────\n");
        }

        mostrarMensajeCentrado(lista.toString());
    }

    public static boolean esPasada(Sesion sesion, LocalDate hoy, LocalTime ahora) {
        if (sesion.fecha.isBefore(hoy)) return true;
        return sesion.fecha.isEqual(hoy) && sesion.hora.isBefore(ahora);
    }

    public static void salirPrograma() {
        int respuesta = JOptionPane.showConfirmDialog(
                null,
                crearPanelCentrado("¿Estás seguro de que deseas salir?"),
                TITULO, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(
                    null,
                    crearPanelCentrado("¡Gracias por utilizar DATADIX STUDIO!"),
                    TITULO, JOptionPane.PLAIN_MESSAGE
            );
            System.exit(0);
        }
    }

    // DISEÑO VISUAL AMPLIADO (VENTANAS GRANDES Y CÓMODAS)
    public static JTextPane crearTextoCentrado(String texto) {
        JTextPane panel = new JTextPane();
        panel.setText(texto);
        panel.setEditable(false);
        panel.setFocusable(false);
        panel.setOpaque(true);
        panel.setBackground(COLOR_FONDO);
        panel.setForeground(COLOR_TEXTO);
        panel.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        StyledDocument doc = panel.getStyledDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), attrs, false);

        return panel;
    }

    public static JPanel crearPanelCentrado(String texto) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_FONDO);
        panel.setPreferredSize(new Dimension(650, 420));

        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE, 2),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        JTextPane contenido = crearTextoCentrado(texto);
        panel.add(contenido);
        return panel;
    }

    public static void mostrarMensajeCentrado(String texto) {
        JOptionPane.showMessageDialog(
                null,
                crearPanelCentrado(texto),
                TITULO,
                JOptionPane.PLAIN_MESSAGE
        );
    }

    public static String pedirEntradaCentrada(String mensaje) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_FONDO);
        panel.setPreferredSize(new Dimension(650, 420));

        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE, 2),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(10, 10, 25, 10);

        panel.add(crearTextoCentrado(mensaje), gbc);

        JTextField campo = new JTextField(25);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        campo.setHorizontalAlignment(JTextField.CENTER);
        campo.setBackground(Color.WHITE);
        campo.setForeground(COLOR_TEXTO);
        campo.setBorder(BorderFactory.createLineBorder(COLOR_BORDE, 2));
        campo.setPreferredSize(new Dimension(300, 45));

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(campo, gbc);

        int resultado = JOptionPane.showConfirmDialog(
                null,
                panel,
                TITULO,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (resultado == JOptionPane.OK_OPTION) {
            return campo.getText();
        }
        return null;
    }

    // CLASE SESIÓN
    static class Sesion {
        String nombre;
        String tipo;
        LocalDate fecha;
        LocalTime hora;
        BigDecimal anticipo;

        public Sesion(String nombre, String tipo, LocalDate fecha, LocalTime hora, BigDecimal anticipo) {
            this.nombre = nombre;
            this.tipo = tipo;
            this.fecha = fecha;
            this.hora = hora;
            this.anticipo = anticipo;
        }
    }
}
