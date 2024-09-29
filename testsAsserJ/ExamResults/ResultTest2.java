package ExamResults;

import org.junit.jupiter.api.Test;
import  org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;

public class ResultTest2 {

    private Result result;

    @Before
    public void setUp() {
        result = new Result();
    }

    @After
    public void tearDown() {
        result = null;
    }

    @Test
    public void testResultNotNull() {
        Assertions.assertThat(result).isNull();
    }

    @Test
    public void testQueryResultSet() {
        String matricule = "12345";
        Result.queryResultSet(matricule);
        Assertions.assertThat(Result.affiche).isNull();
    }

    @Test
    public void testData() {
        String matricule = "12345";
        Result.data(matricule);
        Assertions.assertThat(Result.detail).isNull();
    }

    @Test
    public void testConnection() {
        Result.result();
        Assertions.assertThat(Result.connexion).isNotNull();
    }

 
    
    @Test
    public void resulSettNotNull() throws Exception {
    	
    	 Assertions.assertThat(Result.resultSet).isNotNull();
    }
}