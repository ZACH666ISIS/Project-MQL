package org.mql.java.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import org.mql.java.ui.models.ClassData;
import org.mql.java.ui.models.PackageData;

public class ClassSketch extends JPanel{
	
	
	  private static final long serialVersionUID = 1L;
	
	  private Dimension d;
	  private PrintedArea area;
	  public ClassSketch() {
		 d = Toolkit.getDefaultToolkit().getScreenSize();
		 setPreferredSize(d);
		
	 }
	 public void paintComponent(Graphics g){
		 area = new PrintedArea(40,40,new Dimension(1200, 720));
		 List<PackageData> packages = new Vector<>();
		 
		 packages.add(
				 new PackageData(
						 "Package 2",
						 List.of(new ClassData( "Person" , List.of("int a", "int b", "String nom","String prenom"),
							     List.of("getName():String","setName(String name):String","calculAge(int a):int")),
							 new ClassData("Voiture" , List.of("String matricule","int annee"),
								     List.of("getMatricule():String","setMatricule(String matricule):String")),
							 new ClassData("Garage" , List.of("Long id","int metrage"),
								     List.of("getId():Long","setId(Long id):void")),
							 new ClassData("Card" , List.of("String fullName","int code"),
								     List.of("getAmount():double"))
							 
							 )
						 )
				 );
		 packages.add(
				 new PackageData(
						 "Package 1",
						 List.of(new ClassData( "Person" , List.of("int a", "int b", "String nom","String prenom"),
							     List.of("getName():String","setName(String name):String","calculAge(int a):int")),
							 new ClassData("Voiture" , List.of("String matricule","int annee"),
								     List.of("getMatricule():String","setMatricule(String matricule):String")),
							 new ClassData("Garage" , List.of("Long id","int metrage"),
								     List.of("getId():Long","setId(Long id):void")),
							 new ClassData("Card" , List.of("String fullName","int code"),
								     List.of("getAmount():double")),
							 new ClassData("CNE" , List.of("String massar","int annee"),
									 	List.of("getAmount():double"))
							 
							 )
						 )
				 );
		 packages.add(
				 new PackageData(
						 "Package 1",
						 List.of(new ClassData( "Person" , List.of("int a", "int b", "String nom","String prenom"),
							     List.of("getName():String","setName(String name):String","calculAge(int a):int")),
							 new ClassData("Voiture" , List.of("String matricule","int annee"),
								     List.of("getMatricule():String","setMatricule(String matricule):String")),
							 new ClassData("Garage" , List.of("Long id","int metrage"),
								     List.of("getId():Long","setId(Long id):void")),
							 new ClassData("Card" , List.of("String fullName","int code"),
								     List.of("getAmount():double")),
							 new ClassData("CNE" , List.of("String massar","int annee"),
									 	List.of("getAmount():double")),
							 new ClassData("CIN" , List.of("String CODE","Date ddn "),
						 			 List.of("getAmount():double"))
							 
							 )
						 )
				 );
		 PackagePainter p;
		 
		 for(PackageData packageData : packages) {
			 p = new PackagePainter(packageData, area);
			 p.paintPackage(g);
			 area.newPackage(40,area.getMaxY()+60);
		 }
		 

	 	 
	 }

	
	
}
