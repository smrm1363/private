package entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mmirali on 08/10/2018.
 */


@Repository
public interface ProfileRepository extends JpaRepository<Profile,String> {
}
