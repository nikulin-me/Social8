package social.net.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import social.net.model.Pupil;

public interface PupilRepo extends JpaRepository<Pupil,Long> {

}