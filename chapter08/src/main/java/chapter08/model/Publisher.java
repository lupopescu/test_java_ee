package chapter08.model;




import javax.persistence.*;

@Entity
@Data
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    
}
