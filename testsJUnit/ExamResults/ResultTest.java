package ExamResults;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class ResultTest {

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
    public void testResultNull() throws Exception {
        assertTrue(result == null,()->"Mon intance result est null");
    }

    @Test
    public void testQueryResultSet() throws Exception {
        String matricule = "15486";
        Result.queryResultSet(matricule);
        assertNull(Result.affiche,()->"affiche doit être  null");
    }

    @Test
    public void testData() throws Exception {
        String matricule = "adf1125";
        Result.data(matricule);
        assertNull(Result.detail,()->"detail doit être  null");
    }

    @Test
    public void testConnection()throws Exception {
        Result.result();
        assertNotNull(Result.connexion,()->"Connexion à la BD reussi");
    }

    @Test
    public void resulSettNotNull() throws Exception {
        assertNotNull(Result.resultSet,()->"ResultSet ne doit pas être null");
    }
}
