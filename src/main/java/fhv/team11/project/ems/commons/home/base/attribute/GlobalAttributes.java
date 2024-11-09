package fhv.team11.project.ems.commons.home.base.attribute;

import lombok.Data;

@Data
public class GlobalAttributes {
    private final EventAttributes event;
    private final UserViewModel user;

    public GlobalAttributes() {
        this.event = new EventAttributes();
        this.user = new UserViewModel();
    }

    public boolean isAuthenticated() {
        return user.getUsername() != null;
    }
}
