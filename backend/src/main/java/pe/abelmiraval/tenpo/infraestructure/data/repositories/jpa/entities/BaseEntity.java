package pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass
public class BaseEntity {

    @Column(name = "active")
    protected Boolean active;
    @Column(name = "created_at")
    protected Date createdAt;
    @Column(name = "updated_at")
    protected Date updatedAt;

    public BaseEntity() {
        this.active = true;
        this.createdAt = new Date();
    }
}
