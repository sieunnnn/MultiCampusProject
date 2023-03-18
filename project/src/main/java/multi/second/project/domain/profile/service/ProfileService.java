package multi.second.project.domain.profile.service;

import lombok.extern.slf4j.Slf4j;
import multi.second.project.domain.member.MemberRepository;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.profile.domain.Profile;
import multi.second.project.domain.profile.dto.ProfileDTO;
import multi.second.project.domain.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProfileService {
    private String base_path = "";
    private String originalFilename="";
    @Autowired
    private ProfileRepository repository;
    @Autowired
    private MemberRepository memberRepository;

    public Profile getProfileData(String userId){
        Profile profile = repository.findProfileByMemberUserId(userId);
        return profile;
    }

    public Profile saveProfileImage(MultipartFile upFile, String userId) throws IOException {
        String filename = uploadFile(upFile);
        System.out.println("userId"+userId);
        Member member = memberRepository.findByUserId(userId);
        Profile profile = repository.findProfileByMemberUserId(userId);
        if(profile == null){
            profile = Profile.builder().member(member).imagePath(filename).build();
            repository.save(profile);
        }else{
            repository.updateById(member, filename, userId);
        }

        log.info("Entity Id : {} is saved.", profile.getPfIdx());

        return profile;
    }

    public String uploadFile(MultipartFile upFile) throws IOException {
        //사진 저장
        mkDir();
        originalFilename += upFile.getOriginalFilename();
        System.out.println("테스트"+originalFilename);
        long fileSize = upFile.getSize();
        byte [] data = upFile.getBytes();

        FileOutputStream fos = new FileOutputStream(base_path +"\\"+ originalFilename);
        fos.write(upFile.getBytes());
        fos.close();

        return originalFilename;
    }

    public void mkDir() throws IOException {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(cal.YEAR);
        int month = cal.get(cal.MONTH) + 1;
        int date = cal.get(cal.DATE);

        base_path = "/Users/hong-uiju/Documents/project/MultiCampusProject/project/src/main/resources/static/img/image/";

        originalFilename += Integer.toString(year);
        originalFilename += Integer.toString(month);
        originalFilename += Integer.toString(date);

        System.out.println(base_path);
//        File folder = new File(base_path);
//
//        if(!folder.exists()) {
//            folder.mkdir();
//            System.out.println("폴더가 생성되었습니다");
//        }else {
//            System.out.println("이미 폴더가 존재합니다");
//        }
    }
}
