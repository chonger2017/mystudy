package cn.web.bike.webbike.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Indexed
    private String phoneNum;

    private Date regDate;

    private String nickName;

    private String name;

    private String idNum;

}
