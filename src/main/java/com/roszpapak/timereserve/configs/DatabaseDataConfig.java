package com.roszpapak.timereserve.configs;

import com.roszpapak.timereserve.business.Business;
import com.roszpapak.timereserve.business.BusinessRepository;
import com.roszpapak.timereserve.tag.Tag;
import com.roszpapak.timereserve.user.User;
import com.roszpapak.timereserve.user.UserRepository;
import com.roszpapak.timereserve.user.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class DatabaseDataConfig {


    @Autowired
    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;

    @GetMapping("/save")
    public void save(){
        User papa = new User("Papa", "Rosz", "roszpa@gmail.com", "$2a$10$i7JhwVSVsWO7dLAt9yHkQ.9Eq8jvzlxLBWBOfs9VXCu.Y5wrNgoIG", UserRole.BUSINESSOWNER);
        papa.setEnabled(true);
        userRepository.save(papa);
        User stefi = new User("Stef", "Csi", "roszpi@gmail.com", "$2a$10$i7JhwVSVsWO7dLAt9yHkQ.9Eq8jvzlxLBWBOfs9VXCu.Y5wrNgoIG\n");
        userRepository.save(stefi);
        User david = new User("Dave", "Roszi", "roszpd@gmail.com", "$2a$10$i7JhwVSVsWO7dLAt9yHkQ.9Eq8jvzlxLBWBOfs9VXCu.Y5wrNgoIG\n");
        userRepository.save(david);
        List<Tag> webRosTagList = new ArrayList<>();
        List<Tag> wobRosTagList = new ArrayList<>();
        Tag developer = new Tag();
        Tag backend = new Tag();
        developer.setValue("Developer");
        backend.setValue("Backend");
        webRosTagList.add(developer);
        wobRosTagList.add(backend);
        Business webRos = new Business("WebRos", "Gorbesor", webRosTagList, "0740345058",
                LocalTime.of(10, 20), LocalTime.of(12, 20), 15, papa);
        businessRepository.save(webRos);
        Business wobRos = new Business("wobRos", "Gorbesor", wobRosTagList, "0740345058",
                LocalTime.of(10, 20), LocalTime.of(12, 20), 15, stefi);
        businessRepository.save(wobRos);
    }
}
