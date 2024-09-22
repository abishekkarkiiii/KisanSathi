package Kisan.Sathi.Repositries;

import Kisan.Sathi.Entity.Farmers;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersAccount extends MongoRepository<Farmers, ObjectId> {
   Farmers findByusername(String username);
}
