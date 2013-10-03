package nl.flamingostyle.quooc.service;

import java.util.List;

import javax.annotation.Resource;

import nl.flamingostyle.quooc.domain.Song;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for processing Songs
 * 
 */
@Service("songService")
@Transactional
public class SongService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all songs
	 * 
	 * @return a list of songs
	 */
	@SuppressWarnings("unchecked")
	public List<Song> getAll() {
		logger.debug("Retrieving all persons");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Song");
		
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Retrieves a single song
	 */
	public Song get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing song first
		Song song = (Song) session.get(Song.class, id);
		
		return song;
	}
	/**
	 * Adds a new song
	 */
	public void add(Song song) {
		logger.debug("Adding new song");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Save
		session.save(song);
	}
	
	/**
	 * Deletes an existing person
	 * @param id the id of the existing song
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing song");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing person first
		Song song = (Song) session.get(Song.class, id);
		
		// Delete 
		session.delete(song);
	}
	
	/**
	 * Edits an existing song
	 */
	public void edit(Song song) {
		logger.debug("Editing existing person");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing person via id
		Song existingSong = (Song) session.get(Song.class, song.getId());
		
		// Assign updated values to this person
		existingSong.setArtist(song.getArtist());
		existingSong.setTitle(song.getTitle());
		existingSong.setFirstName(song.getFirstName());

		// Save updates
		session.save(existingSong);
	}
}