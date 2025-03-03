package Kisan.Sathi.Repositries;

import Kisan.Sathi.Entity.FarmersProduct;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FarmersProductsDB extends MongoRepository<FarmersProduct, ObjectId> {


}
