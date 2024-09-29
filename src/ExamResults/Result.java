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
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Result extends Application {
	
	//Parametres de connexion à la base de données
	 static String url = "jdbc:mysql:// ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com:3306/db0073184"; // URL de connexion
	static String utilisateur = "user0073184"; // nom d'utilisateur
	 static String motDePasse = "Yf3IgyBsOPa34WR"; // mot de passe
	
	 //Noeuds et Classes utilisées pour le projé
	 
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
		
		/*
		 *Declaration des differents noeuds de ma fenêtre 
		 * 
		 * */
		
		detailButton = new Button("Voir les details".toUpperCase());
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
		 
		 
		 /*
			 *Initialisation des noeuds enfants dans les noeuds parents 
			 * 
			 * */
		 secondNodeParent.getChildren().addAll(validateButton,detailButton,resetButton);
		 secondNodeParent.setId("secondNodeParent");
		 
		firstNodeParent.getChildren().addAll(indicateLabel,matriculeInput,vider,affiche,titre,detail,secondNodeParent);
		
		
		/*
		 *Les évenements de mes boutons lors d'une action de click 
		 * 
		 * */
		
		//Bouton pour afficher la decision après déliberation(Echec ou Succès)
		validateButton.setOnAction(e->{
			
			queryResultSet(matriculeInput.getText());
			
			if(matriculeInput.getText().equals("") ) {
				
				affiche.setText("Veillez entrer votre matricule!");
				affiche.setTextFill(Color.RED);
			}else if((matriculeInput.getText()).equalsIgnoreCase(studentMatricule) == false){ 
				
					affiche.setText("Matricule inconnu!");
					affiche.setTextFill(Color.RED);
			}
			
			else {
				
				queryResultSet(matriculeInput.getText());
			}
			
		});
		
		//Bouton pour fermer ma fenêtre
		resetButton.setOnAction(e->{
			fenetre1.close();
		});
		
		//Button pour afficher les details des informations etudiant
		detailButton.setOnAction(e->{
			
			//data(matriculeInput.getText());
			
			if(matriculeInput.getText().equals("") ) {
				
				affiche.setText("Veillez entrer votre matricule!");
				affiche.setTextFill(Color.RED);
			}
			
			else if((matriculeInput.getText()).equalsIgnoreCase(studentMatricule) == false){ 
				
				affiche.setText("Matricule inconnu!");
				affiche.setTextFill(Color.RED);
			}
			
			else {
				data(matriculeInput.getText());
				
			}
		});
		 
		//Vider le champs de test ainsi que les autres label apres l'affichage du résultat
		vider.setOnAction(e->{
			matriculeInput.clear();
			affiche.setText("");
			detail.setText("");
			
		});
		vider.setId("vider");
		
		
		//label assistant
		indicateLabel.setText("Entrez votre matricule svp :");
		indicateLabel.setId("indicateLabel");
		
		//Vider le champs par un clic sur celui-ci
		matriculeInput.setOnMouseClicked(e->{
			matriculeInput.clear();
			affiche.setText("");
			detail.setText("");
			
		});
		
		affiche.setId("affiche");
		
		detail.setId("detail");
		
		titre.setId("titre");
		matriculeInput.setId("matriculeInput");
		 
		//Mise en scene des noeuds ainsi que le style css dans la fenêtre		
		Scene scene = new Scene(firstNodeParent,450,650);
		//LIER lae fichier css à mon projet
		scene.getStylesheets().add(getClass().getResource("/ressources/css/style.css").toString());
		
		fenetre1.setScene(scene);
		fenetre1.show();
		fenetre1.setTitle("Consultation de résultat");
		fenetre1.centerOnScreen();
		fenetre1.setResizable(true);
		Image icon = new Image(getClass().getResourceAsStream("/ressources/images/user3.png")); // Assurez-vous que le chemin est correct
		fenetre1.getIcons().add(icon);
		
		
		
	}


	/*
	 * Methodes de gestions de la base de données
	 * */
	
	//Connexion à la base de donnée
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

//Recuperation des données à partie du matricule étudiant
public static void data(String matricule){
	result();
	
	try {
		statement = connexion.createStatement();
	    
	     resultSet = statement.executeQuery("SELECT * FROM etudiant WHERE Matriculeetudiant LIKE \'%"+matricule+"%\';");
		while(resultSet.next()) {
			 firstName = resultSet.getString("Nometudiant");
			 lastName = resultSet.getString("Prenometudiant");
			 birthday = resultSet.getString("DateNaisetudiant");
			 studentSchool = resultSet.getString("Ecoleetudiant");
			 studentAverage = resultSet.getDouble("Moyetudiant");
			 decision = resultSet.getString("Decisionetudiant");
			 studentMatricule = resultSet.getString("Matriculeetudiant");	
		}
		detail.setText("NOM & PRENOM: "+firstName+" "+lastName+"\n\n"+"ANNEE DE NAISSANCE: "+birthday+"\n\n"+"ECOLE: "+studentSchool+"\n\n"+"MATRICULE: "+studentMatricule+"\n\n"+"MOYENNE: "+studentAverage+"/20");
		
				
	} catch (Exception e) {
			e.printStackTrace(); 
			
		}
	}

//Renvoir de la décision à partie du matricule
public static void queryResultSet(String matricule) {
	result();
	
	try {
		statement = connexion.createStatement();
		resultSet = statement.executeQuery("SELECT * FROM etudiant WHERE Matriculeetudiant LIKE \'%"+matricule+"%\';");
		while(resultSet.next()) {
			decision = resultSet.getString("Decisionetudiant");
			 studentMatricule = resultSet.getString("Matriculeetudiant");
		}
		 
			 affiche.setTextFill(decision.equalsIgnoreCase("Succès") ? Color.web("#0be881"):Color.web("#ff3f34"));
				affiche.setText(decision);
		 
				
				
				
	} catch (Exception e) {
			e.printStackTrace();	
	}
}
}
