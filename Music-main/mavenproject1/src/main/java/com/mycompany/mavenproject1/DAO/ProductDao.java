package com.shoping.mavenproject7.Dao;

import com.shoping.mavenproject7.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private final SessionFactory factory; // Use final for immutability

    public ProductDao(SessionFactory factory) {
        this.factory = factory;
    }

    // Method to save a product
    public boolean saveProduct(Product product) {
        Transaction tx = null; // Initialize transaction
        Session session = null; // Initialize session
        try {
            session = this.factory.openSession(); // Open a new session
            tx = session.beginTransaction(); // Start transaction
            session.save(product); // Save the product
            tx.commit(); // Commit the transaction
            return true; // Return true on success
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); // Rollback the transaction on error
            }
            e.printStackTrace(); // Log the exception
            return false; // Return false on failure
        } finally {
            if (session != null) {
                session.close(); // Close the session to prevent resource leaks
            }
        }
    }

    // Method to get all products
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Session session = this.factory.openSession()) { // Use try-with-resources for session management
            Query<Product> query = session.createQuery("from Product", Product.class);
            products = query.getResultList(); // Fetch the results
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }
        return products; // Return the list of products
    }

    // Method to get products by category ID
    public List<Product> getAllProductByID(int cid) {
        List<Product> products = new ArrayList<>();
        try (Session session = this.factory.openSession()) { // Use try-with-resources for session management
            Query<Product> query = session.createQuery("from Product as product where product.category.categoryId =: id", Product.class);
            query.setParameter("id", cid);
            products = query.getResultList(); // Fetch the results
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }
        return products; // Return the list of products
    }

    // Method to count total number of products
    public long getProductCount() {
        long count = 0; // Initialize count
        try (Session session = this.factory.openSession()) { // Use try-with-resources for session management
            String hql = "SELECT COUNT(product) FROM Product product"; // HQL query to count products
            Query<Long> query = session.createQuery(hql, Long.class); // Create query
            count = query.uniqueResult(); // Get the count result
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }
        return count; // Return the total count of products
    }

    // Method to search products by name or description
    public List<Product> searchProductsByNameOrDesc(String searchQuery) {
        List<Product> products = new ArrayList<>();
        try (Session session = this.factory.openSession()) { // Use try-with-resources for session management
            // Create HQL query to search products by name or description
            Query<Product> query = session.createQuery("FROM Product WHERE pName LIKE :searchTerm OR pDisc LIKE :searchTerm", Product.class);
            query.setParameter("searchTerm", "%" + searchQuery + "%");
            products = query.getResultList(); // Fetch the results
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }
        return products; // Return the list of products
    }
}
