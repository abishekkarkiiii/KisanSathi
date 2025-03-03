package Kisan.Sathi.Repositries;

import Kisan.Sathi.Entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersAccount extends MongoRepository<Users, ObjectId> {
   Users findByusername(String username);
}
