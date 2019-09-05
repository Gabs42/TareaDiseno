package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tareadiseno.Adicional;
import tareadiseno.Bebida;
import tareadiseno.Combo;
import tareadiseno.Combo.ComboBuilder;
import tareadiseno.Menu;
import tareadiseno.PlatoPrincipal;
import tareadiseno.PrototypeFactory;

public class Ventana {

	Menu menu;
	
	JFrame frame;
	
	JComboBox<PlatoPrincipal> comboPlatos;
	JComboBox<Bebida> comboBebidas;
	JComboBox<Adicional> comboAdicionales;
	JComboBox<String> comboCombos;
	
	JButton botonPlato;
	JButton botonBebida;
	JButton botonAdicional;
	JButton botonCombo;
	JButton botonNuevo;
	
	JPanel contenedor;
	JScrollPane scroll;
	
	JLabel labelContenedor;
	String informacion = "";
	
	ComboBuilder comboActual = new ComboBuilder();
	
	ArrayList<Combo> combosCreados;
	
	public Ventana(Menu menu) {
		this.menu = menu;
		combosCreados = new ArrayList<Combo>();
	}
	
	public void iniciar() {
		cargarVentana();
		cargarPlatos();
		cargarBebidas();
		cargarAdicionales();
		cargarCombos();
		cargarBotones();
		cargarMostrador();
		mostrarVentana();
	}
	
	public void mostrarEmergente(String mensaje) {
		Emergente emergente = new Emergente(mensaje);
		emergente.mostrar();
	}
	
	private void cargarVentana() {
		frame = new JFrame("Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 480);
	}
	
	private void cargarPlatos() {
		JLabel labelPlatos = new JLabel("Platos Principales:");
		labelPlatos.setBounds(10, 5, 150, 100);
		comboPlatos = new JComboBox<PlatoPrincipal>();
		for(PlatoPrincipal plato : menu.getPlatosPrincipales()) {
			comboPlatos.addItem(plato);
		}
		comboPlatos.setBounds(130, 40, 130, 30);
		botonPlato = new JButton("Añadir");
		botonPlato.setBounds(280, 40, 130, 30);
		botonPlato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			  PlatoPrincipal plato = (PlatoPrincipal)comboPlatos.getSelectedItem();
			  anadirPlato(plato);
			}          
	      });
		frame.add(labelPlatos);
		frame.add(comboPlatos);
		frame.add(botonPlato);
	}

	private void cargarBebidas() {
		JLabel labelBebidas = new JLabel("Bebidas:");
		labelBebidas.setBounds(10, 80, 150, 100);
		comboBebidas = new JComboBox<Bebida>();
		for(Bebida bebida : menu.getBebidas()) {
			comboBebidas.addItem(bebida);
		}
		comboBebidas.setBounds(80, 115, 130, 30);
		botonBebida = new JButton("Añadir");
		botonBebida.setBounds(230, 115, 130, 30);
		botonBebida.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			  Bebida bebida = (Bebida)comboBebidas.getSelectedItem();
			  anadirBebida(bebida);
			}          
	      });
		frame.add(labelBebidas);
		frame.add(comboBebidas);
		frame.add(botonBebida);
	}
	
	private void cargarAdicionales() {
		JLabel labelAdicionales = new JLabel("Adicionales:");
		labelAdicionales.setBounds(10, 155, 150, 100);
		comboAdicionales = new JComboBox<Adicional>();
		for(Adicional adicional : menu.getAdicionales()) {
			comboAdicionales.addItem(adicional);
		}
		comboAdicionales.setBounds(100, 190, 130, 30);
		botonAdicional = new JButton("Añadir");
		botonAdicional.setBounds(250, 190, 130, 30);
		botonAdicional.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			  Adicional adicional = (Adicional)comboAdicionales.getSelectedItem();
			  anadirAdicional(adicional);
			}          
	      });
		frame.add(labelAdicionales);
		frame.add(comboAdicionales);
		frame.add(botonAdicional);
	}
	
	private void cargarCombos() {
		JLabel labelCombos = new JLabel("Combos:");
		labelCombos.setBounds(10, 230, 150, 100);
		comboCombos = new JComboBox<String>();
		comboCombos.addItem("Combo Hamburguesa");
		comboCombos.addItem("Combo Pasta");
		comboCombos.setBounds(90, 265, 170, 30);
		botonCombo = new JButton("Cargar");
		botonCombo.setBounds(280, 265, 130, 30);
		botonCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarCombo();
			}          
	      });
		frame.add(labelCombos);
		frame.add(comboCombos);
		frame.add(botonCombo);
	}

	private void cargarBotones() {
		botonNuevo = new JButton("Nuevo Combo");
		botonNuevo.setBounds(20, 340, 130, 30);
		botonNuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nuevoCombo();
			}          
    	});
		frame.add(botonNuevo);
	}
	
	private void cargarMostrador() {
		contenedor = new JPanel();
		contenedor.setBackground(Color.white);
		scroll = new JScrollPane(contenedor);
		scroll.setBounds(430, 10, 430, 420);
		scroll.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
		labelContenedor = new JLabel("");
		contenedor.add(labelContenedor);
		frame.add(scroll);
	}
	
	private void mostrarVentana() {
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	private void anadirPlato(PlatoPrincipal plato) {
	  comboActual.setPlatoPrincipal(plato);
	  String texto = plato.getNombre() + " " + plato.getPrecio();
	  actualizarMostrador(texto);
	  botonPlato.setEnabled(false);
	  botonCombo.setEnabled(false);
	}
	
	private void anadirBebida(Bebida bebida) {
	  comboActual.anadirBebida(bebida);
	  String texto = bebida.getNombre() + " " + bebida.getPrecio();
	  actualizarMostrador(texto);
	  botonCombo.setEnabled(false);
	}
	
	private void anadirAdicional(Adicional adicional) {
	  comboActual.anadirAdicional(adicional);
	  String texto = adicional.getNombre() + " " + adicional.getPrecio();
	  actualizarMostrador(texto);
	  botonCombo.setEnabled(false);
	}
	
	private void nuevoCombo() {
	  if(botonPlato.isEnabled()) {
	    mostrarEmergente("No tiene plato principal");
	  }
	  else {
	    Combo combo = comboActual.build();
	    combosCreados.add(combo);
	    String precio = String.valueOf(combo.precioTotal());
	    String texto = "Precio Final: " + precio;
	    actualizarMostrador(texto);
	    actualizarMostrador("");
	    comboActual = new ComboBuilder();
	    botonPlato.setEnabled(true);
	    botonCombo.setEnabled(true);
	  }
	}
	
	private void cargarCombo() {
	  String nombre = (String)comboCombos.getSelectedItem();
	  Combo comboPredeterminado = (Combo)PrototypeFactory.obtenerPrototipo(nombre);
	  convertirComboPredeterminado(comboPredeterminado);
	}
	
	private void actualizarMostrador(String texto) {
      informacion += "<br/>";
      informacion += texto;
      labelContenedor.setText("<html>" + informacion + "</html>");
    }
	
	private void convertirComboPredeterminado(Combo combo) {
	  anadirPlato(combo.getPlatoPrincipal());
	  for(Bebida bebida : combo.getBebidas()) {
	    anadirBebida(bebida);
	  }
	  for(Adicional adicional : combo.getAdicionales()) {
	    anadirAdicional(adicional);
	  }
	}
	
}
