package sk.cybersoft.cybernotes.cybernotesspring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "note")
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "text")
    private String text;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private AccountEntity user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AccountEntity getUser() {
        return user;
    }

    public void setUser(AccountEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "NoteEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
