package Kisan.Sathi.Repositries;

import Kisan.Sathi.Entity.Doctor;
import Kisan.Sathi.Entity.Doctorsrequest;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepo extends MongoRepository<Doctor, ObjectId> {
    Doctor findByDoctorId(String id);
    Doctor findByemail(String email);
}
