package pe.abelmiraval.tenpo.infraestructure.data.repositories.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private Integer amount;
    private String category;
    private String username;

    public TransactionEntity(Integer amount, String category, String username) {
        super();
        this.amount = amount;
        this.category = category;
        this.username = username;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
