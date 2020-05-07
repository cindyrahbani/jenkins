package murex.dev.mxem.Environments.model;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name="tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( length = 4096)
    String token;
    @Column( length = 2048)
    String username;

    public Token(String username, String token){
        this.username=username;
        this.token=token;
    }}
