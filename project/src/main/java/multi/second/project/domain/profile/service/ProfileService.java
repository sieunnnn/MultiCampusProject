package multi.second.project.domain.profile.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

@Service
public class ProfileService {
    private String base_path = "";


    public int uploadFile(MultipartFile upFile) throws IOException {
        //사진 저장
        String originalFilename = upFile.getOriginalFilename();
        System.out.println("테스트"+originalFilename);
        long fileSize = upFile.getSize();
        byte [] data = upFile.getBytes();
        mkDir();
        FileOutputStream fos = new FileOutputStream(base_path +"\\"+ originalFilename);
        fos.write(upFile.getBytes());
        fos.close();

        return 0;
    }

    public void mkDir() throws IOException {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(cal.YEAR);
        int month = cal.get(cal.MONTH) + 1;
        int date = cal.get(cal.DATE);

        base_path = "/Users/hong-uiju/Documents/project/MultiCampusProject/project/src/main/resources/static/img/image/";

        base_path += Integer.toString(year);
        base_path += Integer.toString(month);
        base_path += Integer.toString(date);

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
