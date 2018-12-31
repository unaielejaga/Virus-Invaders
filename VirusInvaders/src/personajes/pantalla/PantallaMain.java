package personajes.pantalla;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.sun.javafx.tk.Toolkit;

import personajes.Sonido;

public class PantallaMain extends JFrame {
	/**
	 * Dispone de 2 botones
	 * Jugar que te lleva a la Pantalla Jugar
	 * Marcadores que te lleva a la Pantalla MArcadores
	 */
	
	
	public PantallaMain() { 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		setTitle("VentanaPrincipal");
		ImageIcon img = new ImageIcon("symbols-41380_640.png");
		setIconImage(img.getImage());
		JButton bJugar = new JButton("Jugar!");
		JButton bMarcadores = new JButton("Marcadores");
		getContentPane().setLayout( new GridLayout( 2, 1 ) );
		getContentPane().add(bJugar);
		getContentPane().add(bMarcadores);
		
		
		
		bJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Sonido.playSound("click.wav");
				PantallaJuego p = new PantallaJuego();
				p.setLocationRelativeTo(null);
				p.setVisible( true );
				PantallaMain.this.setVisible( false );	
			}
		});
		
		bMarcadores.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Sonido.playSound("click.wav");
				PantallaMarcadores p = new PantallaMarcadores(PantallaMain.this);
				p.setLocationRelativeTo(null);
				p.setVisible(true);
				PantallaMain.this.setVisible(false);
				
			}
		});
	
	}
		
	public static void main(String[] args) {
		PantallaMain v = new PantallaMain();
		v.setLocationRelativeTo(null);
		v.setVisible( true );
	}

}
