package chapter07.lifecycle;

import javax.persistence.PrePersist;

public class UserAccountListener {
    @PrePersist
    void setPasswordHash(Object o) {
        UserAccount ua = (UserAccount) o;
        if (ua.salt == null || ua.salt == 0) {
            ua.salt=((int) (Math.random() * 65535));
        }
        ua.passwordHash=
                ua.password.hashCode() * ua.salt;
    }
}
