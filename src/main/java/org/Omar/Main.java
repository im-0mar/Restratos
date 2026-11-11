package org.Omar;
import javax.swing.*;
public class Main {

        public static void main(String[] args) {

            while (true) {

                String menu = """
                    
                    ===== RETRATOS =====
                    
                    1. ¿Qué son los retratos?
                    2. Tipos de retrato
                    3. Agendar sesión
                    4. Salir
                    
                    Seleccione una opción:
                    """;

                int opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

                switch (opcion) {

                    case 1:
                        JOptionPane.showMessageDialog(null,
                                "Los retratos son sesiones fotográficas profesionales "
                                        + "para obtener imágenes naturales y de calidad para "
                                        + "redes sociales, uso personal y perfiles profesionales.");
                        break;

                    case 2:
                        JOptionPane.showMessageDialog(null,
                                """
                                TIPOS DE RETRATO
                                
                                • Fotografía individual
                                • Fotografía familiar
                                • Fotografía de embarazo
                                • Fotografía de bebés (newborn)
                                • Fotografía de parejas o mascotas
                                """);
                        break;

                    case 3:

                        String nombre = JOptionPane.showInputDialog("Nombre del cliente:");

                        String[] tipos = {
                                "Individual",
                                "Familiar",
                                "Embarazo",
                                "Bebés",
                                "Parejas o mascotas"
                        };

                        String tipo = (String) JOptionPane.showInputDialog(
                                null,
                                "Seleccione el tipo de retrato:",
                                "Retratos",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                tipos,
                                tipos[0]
                        );

                        String fecha = JOptionPane.showInputDialog("Fecha de la sesión:");
                        String hora = JOptionPane.showInputDialog("Hora de la sesión:");

                        JOptionPane.showMessageDialog(null,
                                """
                                SESIÓN REGISTRADA
                                
                                Cliente: """ + nombre +
                                        "\nTipo: " + tipo +
                                        "\nFecha: " + fecha +
                                        "\nHora: " + hora);

                        break;

                    case 4:
                        JOptionPane.showMessageDialog(null, "Gracias por utilizar el sistema.");
                        System.exit(0);
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida.");
                }
            }
        }
    }