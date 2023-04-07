package com.roszpapak.timereserve.business;

import com.roszpapak.timereserve.DTO.BusinessDTO;
import com.roszpapak.timereserve.exception.FilePathNotValidException;
import com.roszpapak.timereserve.tag.Tag;
import com.roszpapak.timereserve.tag.TagRepository;
import com.roszpapak.timereserve.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private TagRepository tagRepository;

    public void editBusiness(BusinessEditRequest businessEditRequest, User user) {
        List<Tag> tags = new ArrayList<>();
        String[] myArray = businessEditRequest.getTags().split(",");
        HashSet<Business> hashSet = new HashSet<>();
        hashSet.add(user.getBusiness());
        for (var tag : myArray) {
            if (tagRepository.findByValue(tag).isPresent()) {
                tags.add(tagRepository.findByValue(tag).get());
            } else {
                Tag tag1 = new Tag(tag, hashSet);
                tags.add(tag1);
                tagRepository.save(tag1);
            }

        }

        user.getBusiness().setTags(tags);
        user.getBusiness().setName(businessEditRequest.getName());
        user.getBusiness().setPNumber(businessEditRequest.getPhone());
        user.getBusiness().setAddress(businessEditRequest.getAddress());
        user.getBusiness().setStartTime(businessEditRequest.getStartTime());
        user.getBusiness().setEndTime(businessEditRequest.getEndTime());


        businessRepository.save(user.getBusiness());
    }

    public List<BusinessDTO> listByName(String keyword) {
        List<BusinessDTO> ret = new ArrayList<>();
        for (Long id : businessRepository.findIdByName(keyword)) {
            Business business = businessRepository.findById(id).get();
            ret.add(new BusinessDTO(business.getId(), business.getName(), business.getAddress(), business.getPNumber(), business.getTags()));
        }
        return ret;
    }

    public List<BusinessDTO> listAll() {
        List<BusinessDTO> ret = new ArrayList<>();
        for (Business business : businessRepository.findAll()) {
            ret.add(new BusinessDTO(business.getId(), business.getName(), business.getAddress(), business.getPNumber(), business.getTags()));
        }
        return ret;
    }

    public Business listById(Long id) {
        return businessRepository.findById(id).orElseThrow(() -> new IllegalStateException("Business with id :" + id + " not found"));
    }

    public Business getByUserId(Long id) {
        return businessRepository.findByUserId(id).orElse(null);
    }

    public void changeProfilePicture(MultipartFile file, User user) throws FilePathNotValidException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        if (fileName.contains("..")) {
            throw new FilePathNotValidException("File path is not valid");
        }

        try {
            user.getBusiness().setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        businessRepository.save(user.getBusiness());
    }

    public String getUserIdByBusiness(Long id) {
        return String.valueOf(businessRepository.findById(id).get().getUser().getId());
    }

    public List<String> getTags(Long id) {
        List<String> ret = new ArrayList<>();
        List<Tag> tags = businessRepository.findById(id).get().getTags();
        for (var tag : tags) {
            ret.add(tag.getValue());
        }
        return ret;
    }
}
