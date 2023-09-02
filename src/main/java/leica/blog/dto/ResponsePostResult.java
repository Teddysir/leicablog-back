package leica.blog.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponsePostResult {

    private int count;
    private List postList;

    public ResponsePostResult(int count, List postList){
        this.count = count;
        this.postList = postList;
    }
}
