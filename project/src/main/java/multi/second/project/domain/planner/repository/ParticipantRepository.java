package multi.second.project.domain.planner.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.planner.domain.Participant;
import multi.second.project.domain.planner.domain.Planner;


@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long>{

}
