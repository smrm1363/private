package entity;

import com.mohammadreza.mirali.energyconsumption.ProfileRepository;
import entity.MonthEnum;
import entity.Profile;
import entity.ProfileFraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

/**
 * Created by mmirali on 07/10/2018.
 */
@Service("FractionService")
@Path("/")
public class FractionService {

    @Autowired
    ProfileRepository profileRepository;

    @GET
    @Path("/test")
    public void transactions()
    {
        System.out.println("Thisi iiii   "+ MonthEnum.APR);
        Profile profile = new Profile();
        profile.setIdentifire("A");
        profileRepository.save(profile);
        ProfileFraction profileFraction = new ProfileFraction();
        profileFraction.setFraction(.09);
        profileFraction.setMonth(MonthEnum.APR);
        profileFraction.setProfile(profile);

    }
}
