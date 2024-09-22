package ExamResults;

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
	
	//private int totalGirlFriendCount = 0;
	private Label indicateLabel ;
	private Button validateButton;
	private Button resetButton;
	private TextField matriculeInput;
	private VBox firstNodeParent;
	private HBox secondNodeParent;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage fenetre1) throws Exception {
		
		
		
		 firstNodeParent = new VBox();
		 secondNodeParent = new HBox();
		 validateButton = new Button("valider".toUpperCase());
		 resetButton = new Button("annuler".toUpperCase());
		 indicateLabel = new Label();
		 matriculeInput = new TextField();
		 
		 
		 String Fontname = "AvenirNext LT Pro Bold";
		 Font font = Font.font(Fontname, 14 );
		 Font font2 = Font.font("Pacifico",FontWeight.BOLD,28);
		 
		 
		 secondNodeParent.setSpacing(25);
		 secondNodeParent.setPadding(new Insets(10, 0, 0, 0));
		 secondNodeParent.setAlignment(Pos.CENTER);
		 secondNodeParent.getChildren().addAll(validateButton,resetButton);
		 
		
		firstNodeParent.setSpacing(10);
		firstNodeParent.setPadding(new Insets(25));
		firstNodeParent.setAlignment(Pos.CENTER);
		firstNodeParent.setBackground(new Background(new BackgroundFill(Color.web("#F0F4F8"), null, null)));
		firstNodeParent.getChildren().addAll(indicateLabel,matriculeInput,secondNodeParent);
		
		
		validateButton.setContentDisplay(ContentDisplay.CENTER);
		validateButton.setAlignment(Pos.CENTER);
		validateButton.setFont(font);
		validateButton.setBackground(new Background(new BackgroundFill(Color.web("#E66A6A"), null, null)));
		validateButton.setTextFill(Color.WHITE);
		validateButton.setPadding(new Insets(8));
		
		resetButton.setContentDisplay(ContentDisplay.CENTER);
		resetButton.setAlignment(Pos.CENTER);
		resetButton.setFont(font);
		resetButton.setBackground(new Background(new BackgroundFill(Color.web("#E66A6A"), null, null)));
		resetButton.setTextFill(Color.WHITE);
		resetButton.setPadding(new Insets(8));
		
		indicateLabel.setText("Entrez votre matricule svp :");
		indicateLabel.setFont(font2);
		indicateLabel.setTextFill(Color.web("#044E54"));
		indicateLabel.setPadding(new Insets(0, 0, 15, 0));		
		
		
				
		Scene scene = new Scene(firstNodeParent);
		
		fenetre1.setScene(scene);
		fenetre1.show();
		fenetre1.setTitle("Consultation de résultat");
		fenetre1.centerOnScreen();
		fenetre1.setResizable(false);
		
		
		
	}

}
