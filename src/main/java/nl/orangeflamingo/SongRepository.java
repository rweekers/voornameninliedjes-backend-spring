package nl.orangeflamingo;

import nl.orangeflamingo.domain.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SongRepository extends CrudRepository<Song, Long> {

    List<Song> findByArtistIgnoreCase(String artist);

    List<Song> findAll();

    List<Song> findByTitleIgnoreCase(String title);
}
