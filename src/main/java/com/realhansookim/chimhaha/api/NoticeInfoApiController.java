package com.realhansookim.chimhaha.api;

import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.realhansookim.chimhaha.entity.NoticeImgInfoEntity;
import com.realhansookim.chimhaha.service.NoticeInfoService;
import com.realhansookim.chimhaha.vo.NoticeInfoVO;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/notice")
@RequiredArgsConstructor
public class NoticeInfoApiController {
      private final NoticeInfoService niService;
    @PutMapping("/add")
    public Map<String,Object> addMusic(@RequestPart @Nullable MultipartFile file, @RequestPart NoticeInfoVO data){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        map = niService.addNotice(data, file);
        return map;
    }
@Value("${file.image.notice}") private String notice_img;
@GetMapping("/img/{niUri}")
public ResponseEntity<Resource>getImage(@PathVariable String niUri, HttpServletRequest request) throws Exception{
Path folderLocation = Paths.get(notice_img);
NoticeImgInfoEntity entity = niService.getFileNameByImiFilename(niUri);
String filename = entity.getNiiFilename();
String [] split = filename.split("\\.");
String ext = split[split.length-1];
String exportName = niUri+"."+ext;
Path targetFile = folderLocation.resolve(filename);
Resource r = null;
try{
    r = new UrlResource(targetFile.toUri());
}
catch(Exception e){
    e.printStackTrace();
}
String contentType = null;
try{
    contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
    if(contentType == null){
        contentType = "application/octet-stream";
    }
}
catch(Exception e){
    e.printStackTrace();
}
return ResponseEntity.ok()
.contentType(MediaType.parseMediaType(contentType))
.header(HttpHeaders.CONTENT_DISPOSITION,
"attachment; filename*=\""+ URLEncoder.encode(exportName, "UTF-8")+"\"")
.body(r);
}
}
