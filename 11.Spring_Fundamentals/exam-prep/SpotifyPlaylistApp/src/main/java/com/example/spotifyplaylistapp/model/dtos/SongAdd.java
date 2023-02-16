package com.example.spotifyplaylistapp.model.dtos;


import com.example.spotifyplaylistapp.util.enums.MusicGenre;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

import static com.example.spotifyplaylistapp.util.constants.ErrorMessages.*;

public class SongAdd {

    @NotBlank(message = INVALID_PERFORMER)
    @Size(min = 3, max = 20, message = INVALID_PERFORMER)
    private String performer;

    @NotBlank(message = INVALID_TITLE)
    @Size(min = 2, max = 20, message = INVALID_TITLE)
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = INVALID_DATE)
    @PastOrPresent(message = INVALID_DATE)
    private LocalDate releasedDate;

    @NotNull(message = INVALID_DURATION)
    @Positive(message = INVALID_DURATION)
    private Integer duration;

    @NotNull(message = INVALID_STYLE)
    private MusicGenre style;

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public MusicGenre getStyle() {
        return style;
    }

    public void setStyle(MusicGenre style) {
        this.style = style;
    }
}
