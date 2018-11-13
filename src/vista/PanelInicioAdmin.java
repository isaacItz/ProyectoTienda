package vista;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelInicioAdmin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	public PanelInicioAdmin() {
		setToolTipText("Inicio como Adminstrador");
		Border bordejpanel = new TitledBorder(new EtchedBorder(), "Datos Personales");
		setBorder(bordejpanel);
		setLayout(new MigLayout("", "[][][][][grow][][grow]", "[][][][][][][][]"));

		textField = new JTextField();
		textField.setText("");
		add(textField, "cell 4 2,growx");
		textField.setColumns(10);

		textField_1 = new JTextField();
		add(textField_1, "cell 4 4,growx");
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Iniciar Sesion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "iniciando sesion", "panel Admin", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(btnNewButton, "cell 4 7,alignx center");

	}

}
