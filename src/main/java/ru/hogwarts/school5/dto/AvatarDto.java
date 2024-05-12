package ru.hogwarts.school5.dto;

public class AvatarDto {
    private String studentName;
    private String link;

    public AvatarDto() {
    }

    public AvatarDto(String studentName, String link) {
        this.studentName = studentName;
        this.link = link;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}