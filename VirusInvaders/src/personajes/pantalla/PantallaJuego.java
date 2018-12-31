package personajes.pantalla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.KeyboardFocusManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import javafx.scene.input.KeyCode;
import jdk.nashorn.internal.ir.CatchNode;
import personajes.Sonido;
import personajes.antivirus.Antivirus;
import personajes.antivirus.Disparos;
import personajes.virus.Virus;
import personajes.virus.boss.VirusBoss;
import personajes.virus.comun.VirusComun;
import usuarios.Usuarios;



public class PantallaJuego extends JFrame {
	JFrame ventanaAnterior;
	JLabel lPuntos;
	JLabel lVida;
	JLabelGraficoAjustado lDisparo;
	JLabelGraficoAjustado lv;
	JLabelGraficoAjustado lantivirus ;
	JPanelBackground fondo;
	
	static int puntuacion = 0;
	Antivirus antivirus = new Antivirus(3, 1, 360, 790);
	HashMap<String, Integer> puntos = new HashMap<>();
	ArrayList<Disparos> disparosA = new ArrayList<>(); // Array de disparos del Antivirus
	ArrayList<Disparos> disparosV = new ArrayList<>(); // Array de disparos de los Virus
	ArrayList<VirusComun> virusC = new ArrayList<>(); // Array de VirusComunes
	ArrayList<VirusBoss> virusB = new ArrayList<>(); //Array de VirusBoss
	ArrayList<JLabel> lva = new ArrayList<>(); // Array de los JLabel de VirusComunes
	ArrayList<JLabel> lDisparosA = new ArrayList<>(); // Array de los JLabel de los disparos del Antivirus
	ArrayList<JLabel> lDisparosV = new ArrayList<>(); // Array de los JLabel de los disparos de los Virus
	Clip loop;
	Thread hDisparoVB;
	Thread hMovimientoVB;
	JLabelGraficoAjustado lvb;
	int posicion;
	int posicionB;
	boolean izquierda = false;
	

	public Clip getLoop() { 
		return loop;
	}


	public void setLoop(Clip loop) {
		this.loop = loop;
	}


	public int getPuntuacion() {
		return puntuacion;
	}


	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}


	/**
	 * Es donde el Juego se desarrolla
	 * 
	 */
	
	public PantallaJuego() {
		Random randomGenerator = new Random();
		PantallaMain p = new PantallaMain();
		
		Usuarios.leerDeFicherosAHashPuntos(puntos);
		
		fondo = new JPanelBackground();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(960, 1000);
		loop = Sonido.music("nevergonna.wav"); // Empieza el loop de la musica
				
		setResizable(false);
		ImageIcon img = new ImageIcon("symbols-41380_640.png"); // Icono de imagen
		setIconImage(img.getImage());
		fondo.setBackground("Fondo.jpg"); // Introduzco un fondo para la pantalla
		
		
		JButton bControles = new JButton("Controles");
	
		fondo.setLayout(null);
		
	
		lPuntos = new JLabel(String.valueOf(getPuntuacion()), SwingConstants.CENTER);
		lVida = new JLabel();
		
		
		
		lVida.setText(String.valueOf(antivirus.getVida()));
		lPuntos.setFont(new Font("Apple Casual", Font.BOLD, 30));
		lVida.setFont(new Font("Apple Casual", Font.BOLD, 30));
		lPuntos.setVerticalAlignment(SwingConstants.CENTER);
		
		
		Dimension sizeP = lPuntos.getPreferredSize();
		Dimension sizeV = lVida.getPreferredSize();
		lPuntos.setBounds(800 , 460, 100, sizeP.height );
		lVida.setBounds(845, 100, sizeV.width, sizeV.height);
	

	
		
		fondo.add(lVida);
		fondo.add(lPuntos);
		
		JPanel pbotonera = new JPanel();
		getContentPane().add(fondo);
		getContentPane().add( pbotonera, BorderLayout.SOUTH );
	
		pbotonera.add(bControles);
		
		

		bControles.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(PantallaJuego.this, "Movimiento: <-- Izquierda , --> Derecha, Space -- Disparar");
				
			}
		});
		
		VirusComun virus = new VirusComun(1, 1, 0, 0);
		JLabelGraficoAjustado lvirus = new JLabelGraficoAjustado("WU-1009.png", 70, 70);
		lvirus.setBounds(virus.getPosicion_x(), virus.getPosicion_y(), 70, 70);
		
		lantivirus = new JLabelGraficoAjustado("avast-logo-png--170.png", 70, 70);
		lantivirus.setBounds(antivirus.getPosicion_x(), antivirus.getPosicion_y(), 70, 70);
		fondo.add(lantivirus);
		
	
		bControles.setFocusable(false);
		fondo.setFocusable(false);
		
		
		for(int j=1; j<4; j++) { // Mete viruscomunes en un arraylist
			for(int i=1; i<7; i++) {
				VirusComun v = new VirusComun(1, 1, i*70, j*70);
				virusC.add(v);
				lv = new JLabelGraficoAjustado("virus.png", 70, 70);
				lv.setBounds(v.getPosicion_x(), v.getPosicion_y(), 70, 70);
				lva.add(lv);
				fondo.add(lv);
			}
		}
		
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
					if(ke.getKeyCode() == KeyEvent.VK_LEFT) { // Antivirus se mueve hacia la izquierda
						if(antivirus.getPosicion_x() <= 640 && antivirus.getPosicion_x() >= 0) {
							antivirus.setPosicion_x(antivirus.getPosicion_x()-10);
							lantivirus.setLocation(antivirus.getPosicion_x(), antivirus.getPosicion_y());
							repaint();
						}
						if(antivirus.getPosicion_x() == -10) { // Limite izquierdo
							antivirus.setPosicion_x(antivirus.getPosicion_x()+10);
						}
						
					}if(ke.getKeyCode() == KeyEvent.VK_RIGHT) { // Antivirus de mueve hacia la derecha
						if(antivirus.getPosicion_x() <= 640 && antivirus.getPosicion_x() >= 0) {
							antivirus.setPosicion_x(antivirus.getPosicion_x()+10);
							lantivirus.setLocation(antivirus.getPosicion_x(), antivirus.getPosicion_y());
							repaint();
						}
						if(antivirus.getPosicion_x() == 650) { // Limite derecho
							antivirus.setPosicion_x(antivirus.getPosicion_x()-10);
						}
					}if(ke.getKeyChar() == ' ') { // Antivirus dispara
						
						Disparos d = new Disparos(antivirus.getPosicion_x(), antivirus.getPosicion_y()); // Crea una bala
						disparosA.add(d);
						JLabelGraficoAjustado lDisparo = new JLabelGraficoAjustado("LaserA3.png", 40, 70);
						lDisparo.setBounds(antivirus.getPosicion_x(), antivirus.getPosicion_y(), 40, 70);
						lDisparosA.add(lDisparo);
						fondo.add(lDisparo);
						
						Thread hMovimientoDisp = new Thread() { // Movimiento de la bala
							public void run() {
								try {
									while(true) {
										d.setPosicion_y(d.getPosicion_y()-10);
										lDisparo.setLocation(d.getPoscion_x(), d.getPosicion_y());
										repaint();
										
										Thread.sleep(10);
										for(int i=0; i<virusC.size(); i++) {
											
											if(d.getPoscion_x() >= virusC.get(i).getPosicion_x() && d.getPoscion_x() <= virusC.get(i).getPosicion_x() +40 &&  virusC.get(i).getPosicion_y() == d.getPosicion_y()) { // Choque de la bala
												lva.get(i).setVisible(false);
												lva.remove(lva.get(i));
												lDisparo.setVisible(false);
												choqueV(d, virusC.get(i));
												this.stop();
											}
										}
										if(virusB.size() != 0) {
											if(d.getPoscion_x() >= virusB.get(0).getPosicion_x() && d.getPoscion_x() <= virusB.get(0).getPosicion_x()+200 && virusB.get(0).getPosicion_y() == d.getPosicion_y()) { 
												choqueVB(d, virusB.get(0));
												lDisparo.setVisible(false);
												this.stop();
											}
										}
										
										
										if(d.getPosicion_y() == -50) {
											disparosA.remove(d);
											lDisparo.setVisible(false);
											this.stop();
											
										}
										
									}
								
								} catch (Exception e) {}
							}
						};
						
						hMovimientoDisp.start();
						
						
					}if(ke.getKeyChar() == '') { // Boton Esc para salir rapidamente
						Sonido.playSound("click.wav");
						Sonido.stop(loop);
						PantallaGameOver p = new PantallaGameOver();
						p.setLocationRelativeTo(null);
						p.setVisible(true);
						setVisible(false);
					}
				}
			
			
		});
		
		
		
		posicion = 4;
		Thread hMovimientoVi = new Thread() { // Movimiento de los virus comunes
			public void run() {
				try {
					while(true) {
						while(posicion > 0) { // Derecha
							for(int i=0; i<virusC.size(); i++) {
								virusC.get(i).setPosicion_x(virusC.get(i).getPosicion_x() + 70);
								lva.get(i).setLocation(virusC.get(i).getPosicion_x(), virusC.get(i).getPosicion_y());
								repaint();
							}
								if(posicion == 1) {
									for(int i=0; i<virusC.size(); i++) {
										virusC.get(i).setPosicion_y(virusC.get(i).getPosicion_y() + 70);
										lva.get(i).setLocation(virusC.get(i).getPosicion_x(), virusC.get(i).getPosicion_y());
										repaint();
										
										virusC.get(i).setPosicion_x(virusC.get(i).getPosicion_x() - 70);
										lva.get(i).setLocation(virusC.get(i).getPosicion_x(), virusC.get(i).getPosicion_y());
										repaint();

								}izquierda = true;
								
							}
							posicion = posicion - 1;
							Thread.sleep(2000);
						}if(izquierda) { // Izquierda
							while(posicion < 5) {
								for(int i=0; i<virusC.size(); i++) {
									virusC.get(i).setPosicion_x(virusC.get(i).getPosicion_x() - 70);
									lva.get(i).setLocation(virusC.get(i).getPosicion_x(), virusC.get(i).getPosicion_y());
									repaint();
								}
								if(posicion == 4) {
									for(int i=0; i<virusC.size(); i++) {
										virusC.get(i).setPosicion_y(virusC.get(i).getPosicion_y() + 70);
										lva.get(i).setLocation(virusC.get(i).getPosicion_x(), virusC.get(i).getPosicion_y());
										repaint();
										
										virusC.get(i).setPosicion_x(virusC.get(i).getPosicion_x() + 70);
										lva.get(i).setLocation(virusC.get(i).getPosicion_x(), virusC.get(i).getPosicion_y());
										repaint();
									}
								}
								posicion = posicion + 1;
								Thread.sleep(2000);
							}
						}
						
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
		};	hMovimientoVi.start();
		
		posicionB = 4;		
		hMovimientoVB = new Thread() { // Movimiento del Virus Boss
			public void run() {
				try {
					while(true) {
						while(posicionB > 0) { // Derecha
								virusB.get(0).setPosicion_x(virusB.get(0).getPosicion_x() + 70);
								lvb.setLocation(virusB.get(0).getPosicion_x()-150, virusB.get(0).getPosicion_y()-150);
								repaint();
							if(posicionB == 1) {
								virusB.get(0).setPosicion_y(virusB.get(0).getPosicion_y() + 70);
								lvb.setLocation(virusB.get(0).getPosicion_x()-150, virusB.get(0).getPosicion_y()-150);
								repaint();
								
								virusB.get(0).setPosicion_x(virusB.get(0).getPosicion_x() - 70);
								lvb.setLocation(virusB.get(0).getPosicion_x()-150, virusB.get(0).getPosicion_y()-150);
								repaint();
							}izquierda = true;
							posicionB = posicionB - 1;
							Thread.sleep(2000);
						}if(izquierda) { // Izquierda
							while(posicionB < 4) {
									virusB.get(0).setPosicion_x(virusB.get(0).getPosicion_x() - 70);
									lvb.setLocation(virusB.get(0).getPosicion_x()-150, virusB.get(0).getPosicion_y()-150);
									repaint();
								
								if(posicionB == 3) {
										virusB.get(0).setPosicion_y(virusB.get(0).getPosicion_y() + 70);
										lvb.setLocation(virusB.get(0).getPosicion_x()-150, virusB.get(0).getPosicion_y()-150);
										repaint();
										
										virusB.get(0).setPosicion_x(virusB.get(0).getPosicion_x() + 70);
										lvb.setLocation(virusB.get(0).getPosicion_x()-150, virusB.get(0).getPosicion_y()-150);
										repaint();
									
								}
								posicionB = posicionB + 1;
								Thread.sleep(2000);
							}
						}
						
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
			
		};
		
		
		
		Thread hDisparoV = new Thread() { // Disparos de los Virus
			public void run() {
				try {
					while(true) {
						int i = randomGenerator.nextInt(virusC.size()); // Elige un virus random del array
						Disparos dV = new Disparos(virusC.get(i).getPosicion_x(), virusC.get(i).getPosicion_y()); // Crea una bala en ese virus random
						disparosV.add(dV);
						JLabelGraficoAjustado lDisparoV = new JLabelGraficoAjustado("Laser.png", 40, 70);
						lDisparoV.setBounds(dV.getPoscion_x(), dV.getPosicion_y(), 40, 70);
						lDisparosV.add(lDisparoV);
						fondo.add(lDisparoV);
						
						Thread hMovimientoDisparoV = new Thread() { // Movimiento de la bala
							public void run() {
								try {
									while(true) {
										dV.setPosicion_y(dV.getPosicion_y()+5);
										lDisparoV.setLocation(dV.getPoscion_x(), dV.getPosicion_y());
										repaint();
										Thread.sleep(10);
										if(dV.getPoscion_x() >= antivirus.getPosicion_x() && dV.getPoscion_x() <= antivirus.getPosicion_x()+40 && dV.getPosicion_y() == antivirus.getPosicion_y()) { // Choque de la bala con el Antivirus
											lDisparoV.setVisible(false);
											choqueA(dV, antivirus);
											this.stop();
										}
										if(dV.getPosicion_y() == 1040) {
											disparosV.remove(dV);
											lDisparoV.setVisible(false);
											this.stop();
											
										}
										
									}
									
								}catch (Exception e) {
									// TODO: handle exception
								}
							}
						};hMovimientoDisparoV.start();
						
						Thread.sleep(600);
					}
					
				}catch (Exception e) {}
			}
			
		};hDisparoV.start();
		
		
		hDisparoVB = new Thread() { // Disparo del virus boss, dos cañones
			public void run() {
				try {
					while(true) {
						Disparos dVB1 = new Disparos(virusB.get(0).getPosicion_x(), virusB.get(0).getPosicion_y());
						disparosV.add(dVB1);
						JLabelGraficoAjustado lDisparoV1 = new JLabelGraficoAjustado("Laser.png", 40, 70);
						lDisparoV1.setBounds(dVB1.getPoscion_x(), dVB1.getPosicion_y(), 40, 70);
						lDisparosV.add(lDisparoV1);
						fondo.add(lDisparoV1);
						
						Disparos dVB2 = new Disparos(virusB.get(0).getPosicion_x()+150, virusB.get(0).getPosicion_y());
						disparosV.add(dVB2);
						JLabelGraficoAjustado lDisparoV2 = new JLabelGraficoAjustado("Laser.png", 40, 70);
						lDisparoV2.setBounds(dVB2.getPoscion_x(), dVB2.getPosicion_y(), 40, 70);
						lDisparosV.add(lDisparoV2);
						fondo.add(lDisparoV2);
						
						Thread hMovimientoDisparoVB = new Thread() { // Movimiento de la bala
							public void run() {
								try {
									while(true) {
										dVB1.setPosicion_y(dVB1.getPosicion_y()+5);
										lDisparoV1.setLocation(dVB1.getPoscion_x(), dVB1.getPosicion_y());
										
										dVB2.setPosicion_y(dVB2.getPosicion_y()+5);
										lDisparoV2.setLocation(dVB2.getPoscion_x(), dVB2.getPosicion_y());
										repaint();
										Thread.sleep(10);
										if(dVB1.getPoscion_x() >= antivirus.getPosicion_x() && dVB1.getPoscion_x() <= antivirus.getPosicion_x()+40 && dVB1.getPosicion_y() == antivirus.getPosicion_y()) { // Choque de la bala con el Antivirus
											lDisparoV1.setVisible(false);
											choqueAB(dVB1, antivirus);
											this.stop();
										}
										
										if(dVB2.getPoscion_x() >= antivirus.getPosicion_x() && dVB2.getPoscion_x() <= antivirus.getPosicion_x()+40 && dVB2.getPosicion_y() == antivirus.getPosicion_y()) { // Choque de la bala con el Antivirus
											lDisparoV2.setVisible(false);
											choqueAB(dVB2, antivirus);
											this.stop();
										}
										if(dVB1.getPosicion_y() == 1040) {
											disparosV.remove(dVB1);
											lDisparoV1.setVisible(false);
											this.stop();
											
										}if(dVB2.getPosicion_y() == 1040) {
											disparosV.remove(dVB2);
											lDisparoV2.setVisible(false);
											this.stop();
											
										}
										
									}
									
								}catch (Exception e) {
									// TODO: handle exception
								}
							}
						};hMovimientoDisparoVB.start();
						Thread.sleep(600);
						
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
		
	}
	
	public void choqueV(Disparos d, Virus v) { // Choque de bala Antivirus con Virus
		v.hacerseDaño(v, antivirus.getDaño());
		disparosA.remove(d);
		virusC.remove(v);
		setPuntuacion(getPuntuacion()+10);
		lPuntos.setText(String.valueOf(puntuacion));
		if(virusC.size()==0) {
			VirusBoss vb = new VirusBoss(10, 1, 150, 150);
			vb.disparoAumentado(vb, 2);
			virusB.add(vb);
			lvb = new JLabelGraficoAjustado("WU-1009.png", 400, 400);
			lvb.setBounds(vb.getPosicion_x()-150, vb.getPosicion_y()-150,400 ,400 );
			fondo.add(lvb);
			hMovimientoVB.start();
			hDisparoVB.start();
		}
		
	}
	
	public void choqueA(Disparos d, Antivirus a) { // Choque de bala de Virus con Antivius
		a.vidasRestantes(a, virusC.get(1).getDaño());
		lVida.setText(String.valueOf(antivirus.getVida()));
		if(a.getVida() == 0) {
			Sonido.stop(loop);
			PantallaGameOver p = new PantallaGameOver();
			p.setLocationRelativeTo(null);
			p.setVisible(true);
			setVisible(false);
		}
	}
	
	public void choqueAB(Disparos d, Antivirus a) { // Choque de bala de VirusBoss con Antivirus
		a.vidasRestantes(a, virusB.get(0).getDaño());
		lVida.setText(String.valueOf(antivirus.getVida()));
		if(a.getVida() == 0 || a.getVida() == -1 || a.getVida() == -2) {
			Sonido.stop(loop);
			PantallaGameOver p = new PantallaGameOver();
			p.setLocationRelativeTo(null);
			p.setVisible(true);
			setVisible(false);
		}
		
	}
	
	public void choqueVB(Disparos d, VirusBoss v) { // Choque de bala Antivirus con VirusBoss
		v.hacerseDaño(v, antivirus.getDaño());
		if(v.getVida() <= 0) {
			virusB.remove(0);
			setPuntuacion(getPuntuacion()+1000);
			lvb.setVisible(false);
			Sonido.stop(loop);
			PantallaGanar p = new PantallaGanar();
			p.setLocationRelativeTo(null);
			p.setVisible(true);
			setVisible(false);
		}
		disparosA.remove(d);
		lPuntos.setText(String.valueOf(puntuacion));
		
	}


}




