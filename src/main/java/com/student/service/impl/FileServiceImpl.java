package com.student.service.impl;

import com.student.model.MyFile;
import com.student.repository.FileRepository;
import com.student.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Override
    public void save(MultipartFile file, int uid) {
        MyFile myFile = new MyFile();
        myFile.setUid(uid);
        try {
            myFile.setFileName(file.getOriginalFilename());
            myFile.setContentType(file.getContentType());
            myFile.setContent(file.getBytes());
            fileRepository.save(myFile);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public MyFile findByFileName(String name) {
        return fileRepository.findByFileName(name);
    }

    @Override
    public MyFile findByUid(int uid) {
        return fileRepository.findByUid(uid);
    }
}
