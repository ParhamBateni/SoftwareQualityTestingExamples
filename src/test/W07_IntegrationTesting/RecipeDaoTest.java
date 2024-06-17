package W07_IntegrationTesting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RecipeDaoTest {
    RecipeDaoIntegrationTemplate recipeDaoIntegrationTemplate;
    RecipeDao recipeDao;

    @BeforeEach
    public void setup() {
        recipeDaoIntegrationTemplate = new RecipeDaoIntegrationTemplate();
        try {
            recipeDaoIntegrationTemplate.openConnectionAndCleanup();
            recipeDao = recipeDaoIntegrationTemplate.recipeDao;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void cleanUp() {
        try {
            recipeDaoIntegrationTemplate.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRecipeExists() {
        List<Recipe> recipes = new ArrayList<>() {{
            add(new Recipe(1, 10, 20, true, true, "element1"));
            add(new Recipe(2, 20, 30, true, true, "element2"));
            add(new Recipe(3, 30, 40, false, true, "element3"));
        }};
        recipes.forEach((x) -> recipeDao.save(x));
        assertEquals(10, recipeDao.getQuickestVeganDessert());
    }

    @Test
    public void testRecipeDoesNotExist() {
        List<Recipe> recipes = new ArrayList<>() {{
            add(new Recipe(1, 10, 20, false, false, "element1"));
            add(new Recipe(2, 20, 30, true, false, "element2"));
            add(new Recipe(3, 30, 40, false, true, "element3"));
        }};
        recipes.forEach((x) -> recipeDao.save(x));
        assertEquals(0, recipeDao.getQuickestVeganDessert());
    }
}