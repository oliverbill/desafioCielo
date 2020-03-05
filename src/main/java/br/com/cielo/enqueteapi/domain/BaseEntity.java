package br.com.cielo.enqueteapi.domain;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public class BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public final boolean equals(Object other) {

        if (this == other)
            return true;

        if (!(other instanceof BaseEntity))
            return false;

        if (this.getClass() != other.getClass())
            return false;

        BaseEntity that = (BaseEntity) other;
        return id.equals(that.id);
    }
}
