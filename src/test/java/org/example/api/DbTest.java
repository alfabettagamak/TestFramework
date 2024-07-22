package org.example.api;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.api.clients.DbClient;
import org.example.api.clients.HibernateClient;
import org.example.api.models.Pet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DbTest {

    @Test
    public void createTableTesting() throws SQLException {
        String query = "DROP TABLE IF EXISTS public.pets; CREATE TABLE public.pet\n" +
                "(\n" +
                "    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 ),\n" +
                "    category character varying(30),\n" +
                "    name character varying(30),\n" +
                "    photo_urls character varying(100)[],\n" +
                "    tags character varying(30)[],\n" +
                "    status character varying(30),\n" +
                "    PRIMARY KEY (id)\n" +
                ");";
        int result = DbClient.createTableRequest(query);
        assert result == 0;
    }

    @Test
    public void getTableDataTesting() throws SQLException {
        String query = "SELECT * FROM public.superheroes WHERE id=123";
        ResultSet result = DbClient.getDataTable(query);
        Assertions.assertEquals("Wong (Earth-616)", result.getString("name"));
    }

    @Test
    public void addDataTesting(){
        Pet pet = new Pet(23, "dog", "Some Name", new String[]{"eterertretre"}, new String[]{"tags"},  "status");
        SessionFactory sessionFactory = HibernateClient.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(pet);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void getDataHibernateTesting(){
        SessionFactory sessionFactory = HibernateClient.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String queryStr = "FROM Pet where name = : paramId ";
        Query query = session.createQuery(queryStr);
        query.setParameter("paramId", "Some Name");
        List<Pet> pets = query.getResultList();
        session.getTransaction().commit();
        session.close();
        Assertions.assertEquals(5, pets.size());
    }

    @Test
    public void getDataHibernateByCriteriaTesting(){
        SessionFactory sessionFactory = HibernateClient.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Pet> criteriaQuery = criteriaBuilder.createQuery(Pet.class);
        Root<Pet> root = criteriaQuery.from(Pet.class);

        criteriaQuery.select(root).where(root.get("id").equalTo("3"));

        Query query = session.createQuery(criteriaQuery);
        List<Pet> result = query.getResultList();

        session.getTransaction().commit();
        session.close();
        //Assertions.assertEquals(5, pets.size());
    }
}
