package Queue.management.system.example.management.system.Repo;

import Queue.management.system.example.management.system.beans.DoctorType;
import Queue.management.system.example.management.system.beans.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Integer> {
    Patient findByEmailAndPassword(String email, String password);

    //List<Patient> findAllByDoctorType(DoctorType doctorType);
    boolean existsByEmailAndPassword(String email, String password);
}
