package ExamResults;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import ConnexionTest;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Result extends Application {
	
	private static String url = "jdbc:mysql:// ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com:3306/db0073184"; // URL de connexion
	private static String utilisateur = "user0073184"; // nom d'utilisateur
	private static String motDePasse = "Yf3IgyBsOPa34WR"; // mot de passe
	
	static Connection connexion;
	static  ResultSet resultSet;
	 static Statement statement;

	
	private Label indicateLabel ;
	private Button validateButton;
	private Button resetButton;
	private static Button detailButton;
	private TextField matriculeInput;
	private VBox firstNodeParent;
	private HBox secondNodeParent;
	private static Label affiche;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage fenetre1) throws Exception {
		
		
		detailButton = new Button("Details".toUpperCase());
		 firstNodeParent = new VBox();
		 secondNodeParent = new HBox();
		 validateButton = new Button("valider".toUpperCase());
		 resetButton = new Button("annuler".toUpperCase());
		 indicateLabel = new Label();
		 matriculeInput = new TextField();
		 affiche = new Label();
		 
		 
		 
		 secondNodeParent.getChildren().addAll(validateButton,detailButton,resetButton);
		 secondNodeParent.setId("secondNodeParent");
		 
		 
		 
		firstNodeParent.getChildren().addAll(indicateLabel,matriculeInput,affiche,secondNodeParent);
		
		
		validateButton.setOnAction(e->{
			result(matriculeInput.getText());
			matriculeInput.clear();
			
		});
		
		
		resetButton.setOnAction(e->{
			fenetre1.close();
		});
		
		
		
		indicateLabel.setText("Entrez votre matricule svp :");
		indicateLabel.setId("indicateLabel");
		
		affiche.setId("affiche");
		 
			
		
				
		Scene scene = new Scene(firstNodeParent);
		scene.getStylesheets().add(getClass().getResource("/ressources/css/style.css").toString());
		
		fenetre1.setScene(scene);
		fenetre1.show();
		fenetre1.setTitle("Consultation de résultat");
		fenetre1.centerOnScreen();
		fenetre1.setResizable(false);
		
		
		
	}


public static void result(String matricule) {
	try {
		connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
		if (connexion != null) {
            System.out.println("Connexion à la base de données db0073184 réussie !");
             
				queryResultSet(matricule);
		
        } else {
            System.out.println("Échec de la connexion.");
        }
	} catch (SQLException e) {
		e.printStackTrace();
	}

}
public static void queryResultSet(String matricule) {
	try {
	statement = connexion.createStatement();
    
	String des;
    resultSet = statement.executeQuery("SELECT * FROM etudiant WHERE Matriculeetudiant = "+matricule+";");
	if(resultSet.next()) {
		String firstName = resultSet.getString("Nometudiant");
		String lastName = resultSet.getString("Prenometudiant");
		String birthday = resultSet.getString("DateNaisetudiant");
		String studentSchool = resultSet.getString("Ecoleetudiant");
			double studentAverage = resultSet.getDouble("Moyetudiant");
			String decision = resultSet.getString("Decisionetudiant");
			String studentMatricule = resultSet.getString("Matriculeetudiant");
			
			
			
			if(studentMatricule.equals(matricule)  ) {
				
				affiche.setTextFill(decision.toLowerCase().equals("Succès".toLowerCase()) ? Color.web("#0be881"):Color.web("#ff3f34"));
				affiche.setText(decision);
			}
			detailButton.setOnAction(e->{
				String[] data = {firstName,lastName,birthday,studentSchool,studentMatricule};
				
			});
			
	}
			
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}
	
}
}
