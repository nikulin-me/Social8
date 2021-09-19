package social.net.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import social.net.model.Pupil;
import social.net.model.Role;
import social.net.repo.PupilRepo;

import javax.xml.ws.Response;
import java.util.UUID;

@Service
public class PupilService implements UserDetailsService {
    private final PupilRepo pupilRepo;
    private final Pupil pupil;
    private final PasswordEncoder passwordEncoder;

    public PupilService(PupilRepo pupilRepo, Pupil pupil, PasswordEncoder passwordEncoder) {
        this.pupilRepo = pupilRepo;
        this.pupil = pupil;
        this.passwordEncoder=passwordEncoder;
    }


    public boolean registerPupil(Pupil pupil) throws Exception {
        Pupil pupilFromDb = pupilRepo.findByEmail(pupil.getEmail());
        if (pupilFromDb!=null){
            throw new Exception("Something was happened word");
        }
        pupil.setActive(false);
        pupil.setActivationCode(UUID.randomUUID().toString());
        pupil.setPassword(passwordEncoder.encode(pupil.getPassword()));
        pupil.setRole(Role.PUPIL);
        pupilRepo.save(pupil);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Pupil byLogin = pupilRepo.findByEmail(s);
        if (byLogin ==null){
            try {
                throw new Exception("User not found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return byLogin;
    }
}
