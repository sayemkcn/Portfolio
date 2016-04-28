package sayem.picoapps.domains.embedded;

import javax.persistence.Embeddable;

@Embeddable
public class CareerInfo {
	private String careerObjective;
	private String careerSummary;
	private String skills;
	private int expectedSalary;

	public String getCareerObjective() {
		return careerObjective;
	}

	public void setCareerObjective(String careerObjective) {
		this.careerObjective = careerObjective;
	}

	public String getCareerSummary() {
		return careerSummary;
	}

	public void setCareerSummary(String careerSummary) {
		this.careerSummary = careerSummary;
	}

	public int getExpectedSalary() {
		return expectedSalary;
	}

	public void setExpectedSalary(int expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	@Override
	public String toString() {
		return "CareerInfo [careerObjective=" + careerObjective + ", careerSummary=" + careerSummary
				+ ", expectedSalary=" + expectedSalary + "]";
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

}
