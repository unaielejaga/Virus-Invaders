package personajes.pantalla;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import personajes.Sonido;

public class PantallaGameOver extends JFrame{
	
	
	


	
	public PantallaGameOver()  {
		PantallaJuego p = new PantallaJuego();
		Sonido.stop(p.getLoop());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 500);
		setTitle("Game Over");
		ImageIcon img = new ImageIcon("symbols-41380_640.png");
		setIconImage(img.getImage());
		JPanelBackground fondo = new JPanelBackground();
		JPanel pbotonera = new JPanel();
		fondo.setBackground("game-over.png");
		Clip loop = Sonido.music("sad.wav");
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
