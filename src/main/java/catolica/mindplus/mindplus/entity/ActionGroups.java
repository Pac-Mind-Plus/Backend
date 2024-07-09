package catolica.mindplus.mindplus.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "actions_group")
public class ActionGroups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "actionGroup", cascade = CascadeType.ALL)
    private Set<Historic> historics;

    @Column(name = "user_id", nullable = false)
    private String userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setHistorics(Set<Historic> historics) {
		this.historics = historics;
	}

	public String getUserId() {
        return userId;
    }
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
