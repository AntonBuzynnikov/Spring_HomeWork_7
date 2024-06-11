package ru.buzynnikov.notemanager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.buzynnikov.notemanager.dto.NoteDTO;
import ru.buzynnikov.notemanager.models.Note;
import ru.buzynnikov.notemanager.repositories.NoteRepository;
import ru.buzynnikov.notemanager.services.NoteService;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class NoteServiceModuleTest {
    @InjectMocks
    NoteService noteService;
    @Mock
    NoteRepository noteRepository;
    @Test
    public void updateNode(){
        NoteDTO dto = new NoteDTO();
        dto.setTitle("Заметка о тестах");
        dto.setDescription("Это описание изменено для теста");
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Заметка о тестах");
        note.setDescription("Здесь будут изменения");
        note.setCreateTime(LocalDateTime.now());
        Note note2 = new Note();
        note2.setCreateTime(note.getCreateTime());
        note2.setDescription(note.getDescription());
        note2.setTitle(note.getTitle());
        note2.setId(note.getId());

        given(noteRepository.findById(1L)).willReturn(Optional.of(note));

        noteService.updateNote(1L, dto);
        verify(noteRepository).save(note2);
    }
}
