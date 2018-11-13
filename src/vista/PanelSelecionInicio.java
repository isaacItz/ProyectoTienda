package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PanelSelecionInicio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton boton1;
	private JButton boton2;

	public PanelSelecionInicio() {

		Border bordejpanel = new TitledBorder(new EtchedBorder(), "Iniciar Sesion Como");

		setBorder(bordejpanel);
		setSize(600, 400);
		boton2 = new JButton("Empleado");
		boton2.setBounds(161, 164, 108, 47);
		boton2.addActionListener(new OyenteTrabajador());
		boton1 = new JButton("Administrador");
		boton1.setBounds(161, 106, 108, 47);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(237)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(boton2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(boton1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addContainerGap(252, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(118)
						.addComponent(boton2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE).addGap(53)
						.addComponent(boton1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE).addGap(132)));
		setLayout(groupLayout);
		boton1.addActionListener(new OyenteAdministrador());

	}

	private class OyenteAdministrador implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			PanelInicioAdmin panel = new PanelInicioAdmin();
			add(panel);
			panel.setVisible(true);
			validate();
		}

	}

	private class OyenteTrabajador implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "me Presionaste", "trabajador", 1);
		}

	}

}
