package ru.hogwarts.school5.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school5.model.Avatar;
import ru.hogwarts.school5.repository.AvatarRepository;
import ru.hogwarts.school5.repository.StudentRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;

@Service
public class AvatarService {
    private final Logger logger = LoggerFactory.getLogger(AvatarService.class);
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    private final String pathToAvatars;

    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository,
                         @Value("${path.to.avatars.folder}") String pathToAvatars) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
        this.pathToAvatars = pathToAvatars;
    }

    public void save(Long studentId, MultipartFile multipartFile) throws IOException {
        //save to db
        logger.info("Метод save to db был вызван");
        Avatar avatar = avatarRepository.findByStudentId(studentId).orElse(new Avatar());
        avatar.setStudent(studentRepository.getReferenceById(studentId));
        avatar.setData(multipartFile.getBytes());
        avatar.setFileSize(multipartFile.getSize());
        avatar.setMediaType(multipartFile.getContentType());
        //save to disk
        logger.info("Метод save to disk был вызван");
        Files.createDirectories(Path.of("avatars"));
        InputStream is = multipartFile.getInputStream();
        int lastIndexOf = multipartFile.getOriginalFilename().lastIndexOf('.');
        String extension = multipartFile.getOriginalFilename().substring(lastIndexOf);
        String filePath = pathToAvatars + "/" + studentId + "." + extension;
        FileOutputStream fos = new FileOutputStream(filePath);
        is.transferTo(fos);
        fos.close();
        avatar.setFilePath(filePath);
        avatarRepository.save(avatar);
    }

    public Avatar getAvatar(Long studentId) {
        logger.info("Метод getAvatar был вызван");
        return avatarRepository.findByStudentId(studentId).orElseThrow();
    }

    public List<Avatar> findAllStudentAvatars(Integer pageNumber, Integer pageSize) {
        logger.info("Метод findAllStudentAvatars был вызван");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }
  /*  public List<AvatarDto> findAllStudentAvatars(Integer pageNumber, Integer pageSize) {
        return avatarRepository.findAll(pageRequest.of(pageNumber, pageSize)).getContent().stream()
                .map(a -> new AvatarDto(a.getStudent().getName(), "http://localhost.8080/avatar/from-db/" - a.getId()))
                .toList();
    } */
}