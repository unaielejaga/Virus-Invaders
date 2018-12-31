package personajes.pantalla;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import javafx.scene.control.TextField;
import personajes.Sonido;
import usuarios.Usuarios;

public class PantallaLogin extends JFrame{
	
	
	/**
	 * Ventana donde los usuarios podrán registrarse y logearse para así ver poder meter
	 * los puntos conseguidos del juego en su perfil
	 * @param v
	 */
	
	
	public PantallaLogin() {
	
		PantallaJuego p = new PantallaJuego();
		Sonido.stop(p.getLoop());
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(550, 200);
		frame.setResizable(false);
		ImageIcon img = new ImageIcon("symbols-41380_640.png");
		frame.setIconImage(img.getImage());
		JPanel panel = new JPanel();
		JLabel lTitulo = new JLabel("Login");
		lTitulo.setHorizontalAlignment(JLabel.CENTER);
		JButton bLogin = new JButton("Login");
		JButton bRegistrate = new JButton("Registrate");
		JButton bVolver = new JButton("Terminar");
		panel.setLayout(null);

		JLabel userLabel = new JLabel("Nick");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Contraseña");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);

		
		bLogin.setBounds(10, 80, 80, 25);
		panel.add(bLogin);
		
		
		bRegistrate.setBounds(180, 80, 100, 25);
		panel.add(bRegistrate);
		
		bVolver.setBounds(360, 80, 100, 25); 
		panel.add(bVolver);
		
		
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		
		bVolver.addActionListener(new ActionListener() { // Boton Volver para volver a la ventana anterior
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Sonido.playSound("click.wav");
				p.setPuntuacion(0);
				PantallaMain pa = new PantallaMain();
				Sonido.stop(p.getLoop());
				frame.setVisible( false );
				pa.setLocationRelativeTo(null);
				
				pa.setVisible( true );
			}
		});
		
		bRegistrate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Sonido.playSound("click.wav");
				
				HashMap<String, String> usuarios = new HashMap<>();
				HashMap<String, Integer> puntos = new HashMap<>();
				String nick = userText.getText();
				String contraseña = passwordText.getText();
				
				Usuarios.leerDeFicherosAHashUsuarios(usuarios);
				Usuarios.leerDeFicherosAHashPuntos(puntos);
				
					if(usuarios.containsKey(nick)) { // Comprobacion de que el nick no exista ya o que no sea nulo
						JOptionPane.showMessageDialog(PantallaLogin.this, "Ese nick esta siendo utilizado", "Warning!", JOptionPane.WARNING_MESSAGE);
					}else if(nick.equals("") || contraseña.equals("")) {
						JOptionPane.showMessageDialog(PantallaLogin.this, "Falta meter nick o contraseña", "Warning!", JOptionPane.WARNING_MESSAGE);
					}else { // Se mete dentro del hashmap para luego escribirlo en el fichero
						usuarios.put(nick, contraseña);
						Usuarios.escribirDeHashAFicheroUsuarios(usuarios);
						HashMap<String, Integer> puntuacion = new HashMap<>();
						Usuarios.leerDeFicherosAHashPuntos(puntuacion);
						puntuacion.put(nick, p.getPuntuacion());
						Usuarios.escribirDeHashAFicheroPuntos(puntuacion);
						JOptionPane.showMessageDialog(PantallaLogin.this, "Bienvenido a la familia " + nick + " ¡Te has registrado correctamente!");
				}
				
			}
		});
		
		bLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Sonido.playSound("click.wav");
				String nick = userText.getText();
				String contraseña = passwordText.getText();
				HashMap<String, String> usuarios = new HashMap<>();
				HashMap<String, Integer> puntos = new HashMap<>();
				Usuarios.leerDeFicherosAHashPuntos(puntos);
				Usuarios.leerDeFicherosAHashUsuarios(usuarios);
				if(usuarios.containsKey(nick) && contraseña.equals(usuarios.get(nick))) { // COmprobacion de que el usuario y la contraseña estan bien
					if(puntos.get(nick) >= p.getPuntuacion()) {
						JOptionPane.showMessageDialog(PantallaLogin.this, "Esta es tu puntuacion " + nick + ": " + puntos.get(nick) );
					} else {
						puntos.put(nick, p.getPuntuacion());
						Usuarios.escribirDeHashAFicheroPuntos(puntos);
						JOptionPane.showMessageDialog(PantallaLogin.this,"Esta es tu puntuacion " + nick + ": " + puntos.get(nick) );
					}
					
				}else {
					JOptionPane.showMessageDialog(PantallaLogin.this , "El usuario o la contraseña introducida son incorrectas", "Warning!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
	
	

}
