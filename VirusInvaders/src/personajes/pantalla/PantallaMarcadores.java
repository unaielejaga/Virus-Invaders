package personajes.pantalla;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import personajes.Sonido;
import usuarios.Usuarios;

public class PantallaMarcadores extends JFrame {
	JFrame ventanaAnterior;
	public PantallaMarcadores ( JFrame v ) {
		ventanaAnterior = v;
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setSize( 960, 960 );
		ImageIcon img = new ImageIcon("symbols-41380_640.png");
		setIconImage(img.getImage());
		JLabel lTitulo = new JLabel( "RANKING" );
		lTitulo.setHorizontalAlignment( JLabel.CENTER );
		lTitulo.setFont(new Font("Apple Casual", Font.BOLD, 50));
		Clip loop = Sonido.music("saxo.wav");
		
		HashMap<String, Integer> puntuacion = new HashMap<>(); 
		Usuarios.leerDeFicherosAHashPuntos(puntuacion);
		
		Object[] a = puntuacion.entrySet().toArray(); // Ordena los puntos de el hashmap de puntuacion
		Arrays.sort(a, new Comparator() {
		    public int compare(Object o1, Object o2) {
		        return ((Map.Entry<String, Integer>) o2).getValue()
		                   .compareTo(((Map.Entry<String, Integer>) o1).getValue());
		    }
		});
		
		JTable tPuntuacion = new JTable(puntuacion.size(),3); // Tabla para enseñar cada usuario con su puntuacion
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tPuntuacion.setDefaultRenderer(String.class, centerRenderer);
		
		
		int row=0;
		int i = 1;
	
		for(Object entry: a){
			tPuntuacion.setRowHeight(50);
			tPuntuacion.setValueAt(i+".", row, 0);
		    tPuntuacion.setValueAt(((Map.Entry<String, Integer>) entry).getKey(),row,1);
		    tPuntuacion.setValueAt(((Map.Entry<String, Integer>) entry).getValue(),row,2);
		    row++;
		    i++;
		 }
		
	
		tPuntuacion.setFont(new Font("Apple Casual",Font.CENTER_BASELINE, 40));
		JButton bVolver = new JButton( "Volver" );
		
		JPanel pBotonera = new JPanel();
		getContentPane().add( lTitulo, BorderLayout.NORTH );
		getContentPane().add( tPuntuacion, BorderLayout.CENTER );
		getContentPane().add( pBotonera, BorderLayout.SOUTH );
		pBotonera.add( bVolver );
		
		JScrollPane scroll = new JScrollPane(tPuntuacion);
		scroll.setVisible(true);
		add(scroll);
		bVolver.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sonido.playSound("click.wav");
				Sonido.stop(loop);
				setVisible( false );
				ventanaAnterior.setVisible( true );
			}
		});
		
	}

}
