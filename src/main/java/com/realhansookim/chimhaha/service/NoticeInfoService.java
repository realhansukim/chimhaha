package com.realhansookim.chimhaha.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.realhansookim.chimhaha.entity.NoticeImgInfoEntity;
import com.realhansookim.chimhaha.entity.NoticeInfoEntity;
import com.realhansookim.chimhaha.repository.NoticeImgInfoRepository;
import com.realhansookim.chimhaha.repository.NoticeInfoRepository;
import com.realhansookim.chimhaha.vo.NoticeInfoVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeInfoService {
     private final NoticeInfoRepository niRepo;
    private final NoticeImgInfoRepository niiRepo;
    public Map<String,Object> addNotice(NoticeInfoVO data,MultipartFile file ){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        if(data ==null){
            map.put("status", false);
            map.put("msg", "내용을 입력해주세요");
            map.put("code", HttpStatus.BAD_REQUEST);
            return map;
        }
        NoticeInfoEntity entity = NoticeInfoEntity.builder()
        .niTitle(data.getTitle())
        .niContent(data.getContent())
        .niRegDt(data.getRegDt())
        .niMiSeq(data.getNiMiSeq())
        .niNiiSeq(data.getNiNiiSeq())
        .build();
        entity = niRepo.save(entity);
        map = addNoticeImage(file, entity);
        return map;
    }
    public Map<String,Object> addNoticeImage(MultipartFile file, NoticeInfoEntity data){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        if(file == null){
            map.put("status", true);
            map.put("code",HttpStatus.OK);
            map.put("msg","글이 등록되었습니다.");
        }
        else if(file != null){
            addFileImage(file,data);
            map.put("status", true);
            map.put("code",HttpStatus.OK);
            map.put("msg","글이 등록되었습니다.");
        }
        return map;
    }
    
    @Value("${file.image.notice}") String music_img;
    
    public void addFileImage (MultipartFile file, NoticeInfoEntity data){
        Path folderLocation = Paths.get(music_img);
        String originName = file.getOriginalFilename();
        String noticeFileName = createMusicFileName(originName);
        Path targetFile = folderLocation.resolve(noticeFileName);
        try{
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        NoticeImgInfoEntity entity = NoticeImgInfoEntity.builder()
        .niiFilename(noticeFileName)
        .musicInfo(data)
        .niiUri("http://localhost:9988/api/notice/img/"+noticeFileName)
        .build();
        niiRepo.save(entity);
}
private String createMusicFileName(String originalFilename){
    String ext = extractExt(originalFilename);
    String uuid = UUID.randomUUID().toString();
    return uuid+"."+ext;
}
private String extractExt(String originalFilename){
    int pos = originalFilename.lastIndexOf(".");
    return originalFilename.substring(pos+1);
}
public NoticeImgInfoEntity getFileNameByImiFilename(String niiFilename){
    return niiRepo.findByNiiFilename(niiFilename);
   
}
}
