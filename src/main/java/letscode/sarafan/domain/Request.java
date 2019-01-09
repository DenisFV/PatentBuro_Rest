package letscode.sarafan.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@ToString(of = {"id", "text", "ptype", "filename", "author"})
@EqualsAndHashCode(of = {"id"})
@Data
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;

    @JsonView(Views.IdName.class)
    private String text;

    @JsonView(Views.FullRequest.class)
    private String ptype;

    @JsonView(Views.IdName.class)
    private User author;

    @JsonView(Views.FullRequest.class)
    private String filename;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(Views.FullRequest.class)
    private String creationDate;

//    @ElementCollection(targetClass = Checks.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "request_checks", joinColumns = @JoinColumn(name = "request_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<Checks> checks;

//    private String message;
}
