package Kisan.Sathi.Repositries;

import Kisan.Sathi.Entity.Farmers;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FarmersRepo extends MongoRepository<Farmers, ObjectId> {
}
