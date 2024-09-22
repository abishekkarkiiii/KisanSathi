package Kisan.Sathi.Repositries;

import Kisan.Sathi.Entity.Doctorsrequest;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorRequestList extends MongoRepository<Doctorsrequest, ObjectId> {
          Doctorsrequest findByusername(String username);
          Doctorsrequest findByobjectId(ObjectId id);

}
