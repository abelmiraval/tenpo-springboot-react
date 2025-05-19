package pe.abelmiraval.tenpo.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private String category;
    private String username;
    private boolean active;
    private Date createdAt;
    private Date updatedAt;
}
