package org.Omar;

import javax.swing.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> sesiones = new ArrayList<>();

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
                    "📸 DATADIX STUDIO\n\nSeleccione una opción:",
                    "Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (opcion) {

                case 0:

                    JOptionPane.showMessageDialog(
                            null,
                            """
                            📸 ¿QUÉ SON LOS RETRATOS?
                            
                            Son sesiones fotográficas profesionales
                            diseñadas para capturar momentos,
                            emociones y personalidad.
                            
                            ✅ Ideales para:
                            • Redes sociales
                            • Uso personal
                            • Perfil profesional
                            • Recuerdos familiares
                            """,
                            "Información",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    break;

                case 1:

                    JOptionPane.showMessageDialog(
                            null,
                            """
                            📷 TIPOS DE RETRATO
                            
                            👤 Individual
                            👨‍👩‍👧 Familiar
                            🤰 Embarazo
                            👶 Bebés (Newborn)
                            ❤️ Parejas
                            🐶 Mascotas
                            """,
                            "Tipos de Retrato",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    break;

                case 2:

                    String nombre = JOptionPane.showInputDialog(
                            "👤 Nombre del cliente:"
                    );

                    if (nombre == null || nombre.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(
                                null,
                                "⚠️ Debe ingresar un nombre."
                        );
                        break;
                    }

                    String[] tipos = {
                            "Individual",
                            "Familiar",
                            "Embarazo",
                            "Bebés",
                            "Parejas o Mascotas"
                    };

                    String tipo = (String) JOptionPane.showInputDialog(
                            null,
                            "📷 Seleccione el tipo de retrato:",
                            "Agendar Sesión",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            tipos,
                            tipos[0]
                    );

                    if (tipo == null) {
                        break;
                    }

                    String fecha = JOptionPane.showInputDialog(
                            "📅 Fecha de la sesión:"
                    );

                    if (fecha == null || fecha.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(
                                null,
                                "⚠️ Debe ingresar una fecha."
                        );
                        break;
                    }

                    String hora = JOptionPane.showInputDialog(
                            "⏰ Hora de la sesión:"
                    );

                    if (hora == null || hora.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(
                                null,
                                "⚠️ Debe ingresar una hora."
                        );
                        break;
                    }

                    int confirmar = JOptionPane.showConfirmDialog(
                            null,
                            """
                            ¿Desea confirmar la reserva?
                            
                            👤 Cliente: """ + nombre +
                                    "\n📷 Tipo: " + tipo +
                                    "\n📅 Fecha: " + fecha +
                                    "\n⏰ Hora: " + hora,
                            "Confirmar Sesión",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirmar == JOptionPane.YES_OPTION) {

                        String sesion =
                                "👤 " + nombre +
                                        " | 📷 " + tipo +
                                        " | 📅 " + fecha +
                                        " | ⏰ " + hora;

                        sesiones.add(sesion);

                        JOptionPane.showMessageDialog(
                                null,
                                """
                                ✅ SESIÓN REGISTRADA
                                
                                ═══════════════════════
                                      DATADIX STUDIO
                                ═══════════════════════
                                """ +
                                        "\n👤 Cliente: " + nombre +
                                        "\n📷 Tipo: " + tipo +
                                        "\n📅 Fecha: " + fecha +
                                        "\n⏰ Hora: " + hora +
                                        "\n📍 Ubicación: Miguel Hidalgo, CDMX" +
                                        "\n═══════════════════════" +
                                        "\n✨ ¡Te esperamos!"
                        );
                    }

                    break;

                case 3:

                    if (sesiones.isEmpty()) {

                        JOptionPane.showMessageDialog(
                                null,
                                "📭 No hay sesiones registradas."
                        );

                    } else {

                        StringBuilder lista = new StringBuilder(
                                "📋 SESIONES REGISTRADAS\n\n"
                        );

                        for (int i = 0; i < sesiones.size(); i++) {
                            lista.append(i + 1)
                                    .append(". ")
                                    .append(sesiones.get(i))
                                    .append("\n");
                        }

                        JOptionPane.showMessageDialog(
                                null,
                                lista.toString()
                        );
                    }

                    break;

                case 4:

                case JOptionPane.CLOSED_OPTION:

                    JOptionPane.showMessageDialog(
                            null,
                            """
                            👋 Gracias por utilizar
                            DATADIX STUDIO.
                            
                            ¡Hasta pronto!
                            """
                    );

                    System.exit(0);
                    break;
            }
        }
    }
}