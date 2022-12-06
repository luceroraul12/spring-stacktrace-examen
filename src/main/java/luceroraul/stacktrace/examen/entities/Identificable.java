package luceroraul.stacktrace.examen.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Identificable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

}
