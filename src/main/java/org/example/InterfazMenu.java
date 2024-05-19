package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfazMenu extends JFrame implements ActionListener {

    private JLabel opciones;
    private JTextField campoOpcion;
    private JButton botonOK;
    private JPanel panelOpciones; 
    private JPanel panelCampoOpcion;

    public InterfazMenu() {
        setTitle("Menú de Opciones");
        setSize(750, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        opciones = new JLabel("<html>1. Calcular tiempos de ejecución de los algortimos con matrices de tamaño 8x8<br><br>"+
                                    "2. Calcular tiempos de ejecución de los algortimos con matrices de tamaño 16x16<br><br>"+
                                    "3. Calcular tiempos de ejecución de los algortimos con matrices de tamaño 32x32<br><br>"+
                                    "4. Calcular tiempos de ejecución de los algortimos con matrices de tamaño 64x64<br><br>"+
                                    "5. Calcular tiempos de ejecución de los algortimos con matrices de tamaño 128x128<br><br>"+
                                    "6. Calcular tiempos de ejecución de los algortimos con matrices de tamaño 256x256<br><br>"+
                                    "7. Calcular tiempos de ejecución de los algortimos con matrices de tamaño 512x512<br><br>"+
                                    "8. Calcular tiempos de ejecución de los algortimos con matrices de tamaño 1024x1024<br><br>"+
                                    "9. Calcular tiempos de ejecución de los algortimos con matrices de tamaño 2048x2048</html>");

        panelOpciones = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelOpciones.add(opciones, gbc);
        add(panelOpciones, BorderLayout.CENTER);

        campoOpcion = new JTextField(15);
        botonOK = new JButton("OK");
        botonOK.addActionListener(this);
        panelCampoOpcion = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCampoOpcion.add(campoOpcion);
        panelCampoOpcion.add(botonOK);
        add(panelCampoOpcion, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonOK) {
            if (campoOpcion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese una opción");
                return;
            }
            if (!campoOpcion.getText().matches("[1-8]")) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un número entre 1 y 8");
                return;
            }
            int opcion = Integer.parseInt(campoOpcion.getText());
            switch (opcion) {
                case 1:
                    CalculadoraTiempos.calcularTiempos(8);
                    break;
                case 2:
                    CalculadoraTiempos.calcularTiempos(16);
                    break;
                case 3:
                    CalculadoraTiempos.calcularTiempos(32);
                    break;
                case 4:
                    CalculadoraTiempos.calcularTiempos(64);
                    break;
                case 5:
                    CalculadoraTiempos.calcularTiempos(128);
                    break;
                case 6:
                    CalculadoraTiempos.calcularTiempos(256);
                    break;
                case 7:
                    CalculadoraTiempos.calcularTiempos(512);
                    break;
                case 8:
                    CalculadoraTiempos.calcularTiempos(1024);
                    break;
                case 9:
                    CalculadoraTiempos.calcularTiempos(2048);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Opción inválida");
            }
            campoOpcion.setText("");
        }
    }
}
