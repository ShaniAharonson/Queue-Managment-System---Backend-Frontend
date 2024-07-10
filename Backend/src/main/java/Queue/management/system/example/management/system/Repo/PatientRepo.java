package Queue.management.system.example.management.system.Repo;

import Queue.management.system.example.management.system.beans.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Integer> {
    Patient findByEmailAndPassword(String email, String password);

}
