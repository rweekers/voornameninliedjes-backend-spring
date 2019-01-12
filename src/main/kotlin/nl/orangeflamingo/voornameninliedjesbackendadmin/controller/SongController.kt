package nl.orangeflamingo.voornameninliedjesbackend.controller

import nl.orangeflamingo.voornameninliedjesbackend.domain.Song
import nl.orangeflamingo.voornameninliedjesbackend.repository.SongRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class SongController {

    @Autowired
    private lateinit var songRepository: SongRepository

    @GetMapping("/songs")
    fun getSongs(): List<Song> {
        return songRepository.findAll()
    }

    @GetMapping("/songs/{id}")
    fun getSongById(@PathVariable("id") id: String): Song {
        return songRepository.findById(id).orElseThrow { RuntimeException("Song with $id not found") }
    }
}