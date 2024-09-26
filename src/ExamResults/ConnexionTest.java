package ExamResults;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionTest {
	static Connection connexion;
	static  ResultSet resultSet;
	 static Statement statement;

	public static void main(String[] args) {
		String url = "jdbc:mysql:// ls-0f19f4268096a452a869b6f8467bc299c51da519.cz6cgwgke8xd.eu-west-3.rds.amazonaws.com:3306/db0073184"; // URL de connexion
		String utilisateur = "user0073184"; // nom d'utilisateur
		String motDePasse = "Yf3IgyBsOPa34WR"; // mot de passe
		//String matricule = "";

		try {
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
			if (connexion != null) {
                System.out.println("Connexion à la base de données db0073184 réussie !");
                String i = "0073158";
                String req = "SELECT * FROM etudiant WHERE Matriculeetudiant = "+i+";";
                 
					queryResultSet(req);
			
            } else {
                System.out.println("Échec de la connexion.");
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void queryResultSet(String query) {
		try {
		statement = connexion.createStatement();
        
    	
        resultSet = statement.executeQuery(query);
		if(resultSet.next()) {
			int studentID = resultSet.getInt("IDetudiant");
			String firstName = resultSet.getString("Nometudiant");
			String lastName = resultSet.getString("Prenometudiant");
			String birthday = resultSet.getString("DateNaisetudiant");
			String studentSchool = resultSet.getString("Ecoleetudiant");
				double studentAverage = resultSet.getDouble("Moyetudiant");
				String decision = resultSet.getString("Moyetudiant");
				String studentMatricule = resultSet.getString("Matriculeetudiant");
				System.out.println( studentID+"\n"+studentMatricule+"\n "+ firstName + "\n " + lastName+"\n "+birthday + "\n" + studentSchool+"\n "+studentAverage + "\n " + decision);
		}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}