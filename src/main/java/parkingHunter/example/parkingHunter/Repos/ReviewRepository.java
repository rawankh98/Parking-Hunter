package parkingHunter.example.parkingHunter.Repos;

import org.springframework.data.repository.CrudRepository;
import parkingHunter.example.parkingHunter.Models.Review;

public interface ReviewRepository extends CrudRepository<Review,Integer>{
//    Iterable<Review>findByaddingReviewId(Integer addingReviewId );
}
