package ExamResults;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import ConnexionTest;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Result extends Application {
	
	 static String url = "jdbc:mysql:// ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com:3306/db0073184"; // URL de connexion
	static String utilisateur = "user0073184"; // nom d'utilisateur
	 static String motDePasse = "Yf3IgyBsOPa34WR"; // mot de passe
	
	static Connection connexion;
	static  ResultSet resultSet;
	 static Statement statement;

	
	static Label indicateLabel ;
	static Button validateButton;
	static Button resetButton;
	 static Button detailButton;
	 static Button vider;
	static TextField matriculeInput;
	static VBox firstNodeParent;
	static HBox secondNodeParent;
	static Label affiche ;
	 static Label detail;
	 static Label titre;
	
	private static String firstName;
	private static String lastName;
	private static String birthday;
	private static String studentSchool;
	private static double studentAverage;
	private static String decision;
	private static String studentMatricule;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage fenetre1) throws Exception {
		
		
		detailButton = new Button("Details".toUpperCase());
		 firstNodeParent = new VBox();
		 secondNodeParent = new HBox();
		 validateButton = new Button("valider".toUpperCase());
		 resetButton = new Button("fermer".toUpperCase());
		 indicateLabel = new Label();
		 matriculeInput = new TextField();
		 affiche = new Label();
		 detail = new Label();
		 titre = new Label();
		 vider = new Button("vider".toUpperCase());
		 
		 
		 secondNodeParent.getChildren().addAll(validateButton,detailButton,resetButton);
		 secondNodeParent.setId("secondNodeParent");
		 
		 
		 
		firstNodeParent.getChildren().addAll(indicateLabel,matriculeInput,vider,affiche,titre,detail,secondNodeParent);
		
		
		validateButton.setOnAction(e->{
			queryResultSet(matriculeInput.getText());
			
		});
		
		
		resetButton.setOnAction(e->{
			fenetre1.close();
		});
		
		detailButton.setOnAction(e->{
			data(matriculeInput.getText());
		});
		
		vider.setOnAction(e->{
			matriculeInput.clear();
			affiche.setText("");
		});
		vider.setId("vider");
		
		
		
		
		indicateLabel.setText("Entrez votre matricule svp :");
		indicateLabel.setId("indicateLabel");
		
		affiche.setId("affiche");
		
		detail.setId("detail");
		
		titre.setId("titre");
		 
				
		Scene scene = new Scene(firstNodeParent,400,600);
		scene.getStylesheets().add(getClass().getResource("/ressources/css/style.css").toString());
		
		fenetre1.setScene(scene);
		fenetre1.show();
		fenetre1.setTitle("Consultation de résultat");
		fenetre1.centerOnScreen();
		fenetre1.setResizable(true);
		
		
		
	}


public static void result() {
	try {
		connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
		if (connexion != null) {
            System.out.println("Connexion à la base de données db0073184 réussie !");
             
        } else {
            System.out.println("Échec de la connexion.");
        }
	} catch (SQLException e) {
		e.printStackTrace();
	}

}
public static void queryResultSet(String matricule) {
	result();
	
	try {
		statement = connexion.createStatement();
	    
	   resultSet = statement.executeQuery("SELECT * FROM etudiant WHERE Matriculeetudiant = "+matricule+";");
		if(resultSet.next()) {
			 decision = resultSet.getString("Decisionetudiant");
			 studentMatricule = resultSet.getString("Matriculeetudiant");
			 
			 if(studentMatricule.equals(matricule)  ) {
					
					affiche.setTextFill(decision.toLowerCase().equals("Succès".toLowerCase()) ? Color.web("#0be881"):Color.web("#ff3f34"));
					affiche.setText(decision);
				}
						
		}
				
	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
	}
}


public static void data(String matricule){
	result();
	
	try {
		statement = connexion.createStatement();
	    
	     resultSet = statement.executeQuery("SELECT * FROM etudiant WHERE Matriculeetudiant = "+matricule+";");
		if(resultSet.next()) {
			 firstName = resultSet.getString("Nometudiant");
			 lastName = resultSet.getString("Prenometudiant");
			 birthday = resultSet.getString("DateNaisetudiant");
			 studentSchool = resultSet.getString("Ecoleetudiant");
			 studentAverage = resultSet.getDouble("Moyetudiant");
			 decision = resultSet.getString("Decisionetudiant");
			 studentMatricule = resultSet.getString("Matriculeetudiant");	
		}
		detail.setText("NOM & PRENOM: "+firstName+" "+lastName+"\n"+"ANNEE DE NAISSANCE: "+birthday+"\n"+"ECOLE: "+studentSchool+"\n"+"MATRICULE: "+studentMatricule+"\n"+"MOYENNE: "+studentAverage);
		
				
	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			
		}
	}
}
