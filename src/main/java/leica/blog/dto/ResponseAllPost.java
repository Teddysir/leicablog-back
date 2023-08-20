package leica.blog.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ResponseAllPost {

    private Timestamp createAt;
    private Timestamp modifiedAt;
    private String title;
}
