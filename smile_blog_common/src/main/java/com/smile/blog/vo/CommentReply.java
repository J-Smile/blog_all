package com.smile.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-23 15:29
 */
@Data
public class CommentReply {

    private String text;
    private int flog;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime time;

    private String bid;

    private String userName;
    private String title;
    private String userImage;

    public CommentReply(String text, int flog, LocalDateTime time, String userName, String title, String userImage) {
        this.text = text;
        this.flog = flog;
        this.time = time;
        this.userName = userName;
        this.title = title;
        this.userImage = userImage;
    }
    public CommentReply(String text, int flog, LocalDateTime time, String userName) {
        this.text = text;
        this.flog = flog;
        this.time = time;
        this.userName = userName;
    }



    public CommentReply(String text, int flog, LocalDateTime time, String bid, String userName, String title, String userImage) {
        this.text = text;
        this.flog = flog;
        this.time = time;
        this.bid = bid;
        this.userName = userName;
        this.title = title;
        this.userImage = userImage;
    }
}
