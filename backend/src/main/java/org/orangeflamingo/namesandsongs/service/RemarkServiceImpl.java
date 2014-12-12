package org.orangeflamingo.namesandsongs.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.orangeflamingo.namesandsongs.domain.Remark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for processing Remarks
 * 
 */
@Service
@Transactional
public class RemarkServiceImpl implements RemarkService {

    private static final Logger LOGGER = Logger
            .getLogger(RemarkServiceImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Remark addRemark(Remark remark) {
        LOGGER.debug("Adding new remark");
        // Retrieve session from Hibernate and save song
        Session session = sessionFactory.openSession();
        session.save(remark);
        return remark;
    }

    /**
     * Retrieves all songs
     * 
     * @return a list of songs
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Remark> getAll() {
        LOGGER.debug("Retrieving all songs");

        // Create a Hibernate query (HQL)
        Session session = sessionFactory.openSession();
        Query query = session
                .createQuery("FROM  Remark remark order by remark.id desc");

        // Retrieve all
        return query.list();
    }

    /**
     * Retrieves a single remark
     */
    public Remark get(Integer id) {
        // Retrieve existing song first
        LOGGER.debug("Calling getRemark() with the id " + id);
        Session session = sessionFactory.openSession();
        return (Remark) session.get(Remark.class, id);
    }

    /**
     * Edits an existing song
     */
    public void update(Remark remark) {
        LOGGER.debug("Editing existing remark");

        // Retrieve session from Hibernate
        Session session = sessionFactory.openSession();
        // Retrieve existing song via id
        Remark existingRemark = (Remark) session.get(Remark.class,
                remark.getId());
        // Assign updated values to this song
        existingRemark.setStatus("In progress");
        // Save updates
        session.save(existingRemark);
    }
}
