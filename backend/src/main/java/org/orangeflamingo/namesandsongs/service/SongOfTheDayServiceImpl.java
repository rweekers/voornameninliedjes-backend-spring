package org.orangeflamingo.namesandsongs.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.orangeflamingo.namesandsongs.domain.Song;
import org.orangeflamingo.namesandsongs.domain.SongOfTheDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for processing Songs
 * 
 */
@Service
@Transactional
public class SongOfTheDayServiceImpl implements SongOfTheDayService {

    private static final Logger LOGGER = Logger
            .getLogger(SongOfTheDayServiceImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    SongService songService;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Retrieves all songsOfTheDay
     * 
     * @return a list of songsOfTheDay
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SongOfTheDay> getAll() {
        LOGGER.debug("Retrieving all songsOfTheDay");

        // Create a Hibernate query (HQL)
        Query query = getCurrentSession().createQuery(
                "FROM  SongOfTheDay songOfTheDay order by songOfTheDay.id");

        // Retrieve all
        return query.list();
    }

    /**
     * Initializes the list with random songs per day
     */
    public void initialize() {
        int numberOfSongsOfTheDay = getAll().size();
        LOGGER.debug("Number of songsOfTheDay in database: "
                + numberOfSongsOfTheDay);

        if (numberOfSongsOfTheDay == 0) {

            LOGGER.debug("Initalizing list with songs of the day...");
            List<Song> songs = songService.getAll(null);
            Collections.shuffle(songs);
            Calendar calendarDay = Calendar.getInstance();

            for (Song song : songs) {
                Date day = new Date(calendarDay.getTimeInMillis());
                SongOfTheDay songOfTheDay = new SongOfTheDay();
                songOfTheDay.setSong(song);
                songOfTheDay.setDay(day);
                calendarDay.add(Calendar.DATE, 1);
                getCurrentSession().save(songOfTheDay);
            }
        } else {
            LOGGER.debug("Initialize not necessary, already songsOfTheDay.");
        }
    }

    /**
     * Returns the song for today
     * 
     * @param today
     *            today
     * @return the song that is due for today
     */
    public Song getSongOfTheDay() {
        Date today = new Date(Calendar.getInstance().getTimeInMillis());
        // Create a Hibernate query (HQL)
        Query query = getCurrentSession()
                .createQuery(
                        "FROM  SongOfTheDay songOfTheDay where songOfTheDay.day = :today");
        query.setParameter("today", today);
        SongOfTheDay songOfTheDay = (SongOfTheDay) query.list().get(0);
        return songOfTheDay.getSong();
    }

    /**
     * Retrieves a single songOfTheDay
     * 
     * @return the songOfTheDay
     * @param id
     *            the id of the songOfTheDay
     */
    public SongOfTheDay get(Integer id) {
        // Retrieve existing visit first
        LOGGER.debug("Calling get songOfTheDay with the id " + id);
        return (SongOfTheDay) getCurrentSession().get(SongOfTheDay.class, id);
    }
}
