package dev.skirtty.webmessaging.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_settings")
@Getter
@Setter @NoArgsConstructor
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user_id;

    @Column(length = 20)
    private String theme;

    @Column(length = 20)
    private String accent_color;

    @Column(length = 10)
    private String language;

    private boolean notifications_enabled;


}
