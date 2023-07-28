package com.student.service;

import com.student.model.MyFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void save(MultipartFile file,int uid);
    MyFile findByFileName(String name);
    MyFile findByUid(int uid);
}
