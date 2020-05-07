package murex.dev.mxem.Environments.model;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RolesPermissions {
    private Set<String> roles;
    private Set<String> permissions;

    public RolesPermissions(Set<String> roles, Set<String> permissions) {
        this.roles = roles;
        this.permissions = permissions;
    }
}
