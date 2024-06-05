package catolica.mindplus.mindplus.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "historic")
public class Historic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "weight")
    private double weight;

    @Column(name = "reward")
    private String reward;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "actions_group_id", nullable = false)
    ActionGroups actionGroup;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User owner;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setActionGroup(ActionGroups actionGroup) {
		this.actionGroup = actionGroup;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}
