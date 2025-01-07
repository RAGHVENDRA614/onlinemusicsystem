package com.shoping.mavenproject7.Dao;

import com.shoping.mavenproject7.entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    private final SessionFactory factory; // Use SessionFactory to open new sessions

    public CategoryDao(SessionFactory factory) {
        this.factory = factory;
    }

    // Method to save a category
    public int saveCategory(Category category) {
        Transaction transaction = null; // Initialize transaction
        int categoryId = 0; // To store the generated ID

        try (Session session = factory.openSession()) { // Use try-with-resources for session management
            transaction = session.beginTransaction(); // Start the transaction
            categoryId = (int) session.save(category); // Save the category and get the ID
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of an error
            }
            e.printStackTrace(); // Log the error for debugging
        }

        return categoryId; // Return the generated ID (may be 0 if failed)
    }

    // Method to get a category by ID
    public Category getCategoryById(int cid) {
        Category cat = null;

        try (Session session = factory.openSession()) { // Use try-with-resources for session management
            cat = session.get(Category.class, cid); // Fetch the category by ID
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
        }

        return cat; // Return the category (may be null if not found)
    }

    // Method to get all categories
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        
        try (Session session = this.factory.openSession()) { // Use try-with-resources for session management
            Query<Category> query = session.createQuery("from Category", Category.class);
            categories = query.getResultList(); // Fetch all categories
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
        }

        return categories; // Return the list of categories
    }

    // Method to get the total count of categories
    public int getCategoryCount() {
        int count = 0;

        try (Session session = factory.openSession()) { // Use try-with-resources for session management
            Query<Long> query = session.createQuery("SELECT COUNT(c) FROM Category c", Long.class);
            count = query.uniqueResult().intValue(); // Get the count as an int
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
        }

        return count; // Return the total count of categories
    }
}
