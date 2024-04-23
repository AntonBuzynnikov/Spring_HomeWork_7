package ru.buzynnikov.notemanager.dto;

import lombok.Data;

@Data
public class NoteDTO {
    private String title;
    private String description;

    public NoteDTO(){

    }
    public NoteDTO(String title, String description){
        this.title = title;
        this.description = description;
    }
}
