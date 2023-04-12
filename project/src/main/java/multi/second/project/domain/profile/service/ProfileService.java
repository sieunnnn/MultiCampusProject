package multi.second.project.domain.profile.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import multi.second.project.domain.group.repository.TravelGroupRepository;
import multi.second.project.domain.host.repository.HostRepository;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.member.repository.MemberRepository;
import multi.second.project.domain.planner.repository.ParticipantRepository;
import multi.second.project.domain.planner.repository.PlannerRepository;
import multi.second.project.domain.profile.domain.Profile;
import multi.second.project.domain.profile.dto.ProfileDTO;
import multi.second.project.domain.profile.dto.request.ProfileModifyRequest;
import multi.second.project.domain.profile.repository.ProfileRepository;
import multi.second.project.infra.code.Code;

import multi.second.project.infra.code.ErrorCode;
import multi.second.project.infra.exception.HandlableException;
import multi.second.project.infra.util.file.FilePath;
import multi.second.project.infra.util.file.dto.FilePathDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;


import multi.second.project.infra.code.Code;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
@Slf4j
public class ProfileService {
    //private String base_path = "";
    //private String originalFilename="";

    private final ProfileRepository profileRepository;

    private final MemberRepository memberRepository;

    public Profile getProfileData(String userId){
        Profile profile = profileRepository.findProfileByMemberUserId(userId);
        System.out.println("profile :" +profile);
        if(profile == null){
        	Member member = memberRepository.findById(userId).get();
            profile = Profile.builder().member(member).build();
            profileRepository.save(profile);
        }
        
        return profile;
    }

    @Transactional
    public void saveProfileImage(MultipartFile upFile, String userId, ProfileModifyRequest dto) throws IOException {
        String filename = uploadFile(upFile);
        System.out.println("userId"+userId);
        Member member = memberRepository.findById(userId).get();
        Profile profile = profileRepository.findProfileByMemberUserId(userId);
        if(profile == null){
            profile = Profile.builder().member(member).imagePath(filename).build();
            profileRepository.save(profile);
        }else{
           // repository.updateById(member, filename, userId);
        	dto.setImagePath(filename);
        	profile.updateProfile(dto);
        	profileRepository.flush();
        }

        log.info("Entity Id : {} is saved.", profile.getPfIdx());

//        return profile;
    }

    public String uploadFile(MultipartFile upFile) throws IOException {
        //사진 저장
        String originalFilename = "";
        originalFilename = mkDir(originalFilename);
        originalFilename += upFile.getOriginalFilename();
        System.out.println("테스트"+originalFilename);
        long fileSize = upFile.getSize();
        byte [] data = upFile.getBytes();

        FileOutputStream fos = new FileOutputStream(Code.PROFILE_STORAGE_PATH + originalFilename);
        fos.write(upFile.getBytes());
        fos.close();

        return originalFilename;
    }

    public String mkDir(String originalFilename) throws IOException {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(cal.YEAR);
        int month = cal.get(cal.MONTH) + 1;
        int date = cal.get(cal.DATE);

        //String base_path = Code.PROFILE_STORAGE_PATH;
        originalFilename += Integer.toString(year);
        originalFilename += Integer.toString(month);
        originalFilename += Integer.toString(date);

        System.out.println(Code.PROFILE_STORAGE_PATH);
        File folder = new File(Code.PROFILE_STORAGE_PATH+"");

        if(!folder.exists()) {
            folder.mkdirs();
            System.out.println("폴더가 생성되었습니다");
        }else {
            System.out.println("이미 폴더가 존재합니다");
        }
        return originalFilename;
    }

    public ProfileModifyRequest findImagePathByPfIdx(Long pfIdx) {

        Profile profile = profileRepository.findImagePathByPfIdx(pfIdx);

        return new ProfileModifyRequest(profile);
    }

	public ProfileModifyRequest findImagePathByMemberUserId(String otherId) {
		// TODO Auto-generated method stub
		Profile profile = profileRepository.findImagePathByMemberUserId(otherId);

        return new ProfileModifyRequest(profile);
	}

}
