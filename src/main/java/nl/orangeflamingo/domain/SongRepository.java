package nl.orangeflamingo.domain;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SongRepository extends CrudRepository<Song, Long> {

    List<Song> findByArtistLikeIgnoreCase(String artist);

    List<Song> findAll();

    List<Song> findByTitleLikeIgnoreCase(String title);

    List<Song> findAll(Specification<Song> spec);

}