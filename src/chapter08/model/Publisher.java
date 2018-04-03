package chapter08.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.metamodel.source.binder.IdentifierSource;

@Entity
@Data
public class Publisher{
   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
	@Column
    String name;
    public Publisher() {
		
   	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Publisher)) return false;

	        Publisher publiser = (Publisher) o;

	        if (id != null ? !id.equals(publiser.id) : publiser.id != null) return false;
	        return name.equals(publiser.name);

	    }

	    @Override
	    public int hashCode() {
	        int result = id != null ? id.hashCode() : 0;
	        result = 31 * result + name.hashCode();
	        return result;
	    }

	    @Override
	    public String toString() {
	        final StringBuilder sb = new StringBuilder("Publiser{");
	        sb.append("id=").append(id);
	        sb.append(", name='").append(name).append('\'');
	        sb.append('}');
	        return sb.toString();
	    }
}
