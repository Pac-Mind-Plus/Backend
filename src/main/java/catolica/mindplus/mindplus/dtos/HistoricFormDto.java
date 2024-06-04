package catolica.mindplus.mindplus.dtos;

import java.sql.Date;

public class HistoricFormDto {
    private double weight;

    private String reward;

    private Date date;

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
}
