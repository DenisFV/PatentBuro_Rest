package kspt.buro.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "usr")
@Data
public class User implements Serializable {

    @Id
    @JsonView(Views.FullRequest.class)
    private String id;
//    @NotBlank(message = "Поле должно быть заполнено")
    @JsonView(Views.IdName.class)
    private String name;
//    @NotBlank(message = "Поле должно быть заполнено")
    @JsonView(Views.FullRequest.class)
    private String password;
    @JsonView(Views.FullRequest.class)
    private boolean active;

//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<Role> roles;

//    private String userpic;
//    private String email;
//    private String gender;
//    private String locale;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullRequest.class)
    private LocalDateTime registrationTime;

//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Request> requests;
}
