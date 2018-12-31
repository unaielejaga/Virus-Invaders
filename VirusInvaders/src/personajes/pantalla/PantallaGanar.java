package personajes.pantalla;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import personajes.Sonido;

public class PantallaGanar extends JFrame {
	
	public PantallaGanar() {
		
		PantallaJuego p = new PantallaJuego();
		Sonido.stop(p.getLoop());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 500);
		setTitle("Has Ganado");
		ImageIcon img = new ImageIcon("symbols-41380_640.png");
		setIconImage(img.getImage());
		JPanelBackground fondo = new JPanelBackground();
		JPanel pbotonera = new JPanel();
		fondo.setBackground("ganar.jpg");
		Clip loop = Sonido.music("ganar.wav");
		JButton bGuardarPuntos = new JButton("Guardar Puntos");
		JButton bVolver = new JButton("Salir Sin Guardar");
		add(fondo);
		fondo.setLayout(new BorderLayout());
		fondo.add( pbotonera, BorderLayout.SOUTH);
		pbotonera.setOpaque(false);
		pbotonera.add(bGuardarPuntos);
		pbotonera.add(bVolver);
		

		
		
		
		bVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Sonido.playSound("click.wav");
				Sonido.stop(loop);
				p.setPuntuacion(0);
				PantallaMain p = new PantallaMain();
				setVisible( false );
				p.setLocationRelativeTo(null);
				p.setVisible(true);
				
				
				
			}
		});
		
		bGuardarPuntos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PantallaLogin p = new PantallaLogin();
				Sonido.stop(loop);
				p.setLocationRelativeTo(null);
				setVisible(false);
	
			}
		});
		
	}
		
}

