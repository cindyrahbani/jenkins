package murex.dev.mxem.Environments.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message="name is mandatory. Please provide username")
    @Size(min=2, message="Name should be at least 2 characters")
    private String name;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name="user_role",
            joinColumns= @JoinColumn(name="userId", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="roleId", referencedColumnName="id"))
    Set<Role> roles;

    public Boolean isDeleted=false;
    public Date createdOn=new Date();
    public String createdBy="user";
    public Date modifiedOn=new Date();
    public String modifiedBy="user";

    public void updateOnCreation(String username){
        this.setCreatedBy(username);
        this.setModifiedBy(username);
        this.setCreatedOn(new Date());
        this.setModifiedOn(new Date());
    }

    public void updateOnModification(String username){
        this.setModifiedBy(username);
        this.setModifiedOn(new Date());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }
}
