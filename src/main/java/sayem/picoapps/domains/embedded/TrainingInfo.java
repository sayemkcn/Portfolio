package sayem.picoapps.domains.embedded;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class TrainingInfo {

	private String trainingTitle;
	private String institute;
	private Date trainingFromDate;
	private Date trainingToDate;
	private int trainingHours;
	private String trainingDescription;

	public String getTrainingTitle() {
		return trainingTitle;
	}

	public void setTrainingTitle(String trainingTitle) {
		this.trainingTitle = trainingTitle;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public Date getTrainingFromDate() {
		return trainingFromDate;
	}

	public void setTrainingFromDate(Date trainingFromDate) {
		this.trainingFromDate = trainingFromDate;
	}

	public Date getTrainingToDate() {
		return trainingToDate;
	}

	public void setTrainingToDate(Date trainingToDate) {
		this.trainingToDate = trainingToDate;
	}

	public int getTrainingHours() {
		return trainingHours;
	}

	public void setTrainingHours(int trainingHours) {
		this.trainingHours = trainingHours;
	}

	public String getTrainingDescription() {
		return trainingDescription;
	}

	public void setTrainingDescription(String trainingDescription) {
		this.trainingDescription = trainingDescription;
	}

	@Override
	public String toString() {
		return "Training [trainingTitle=" + trainingTitle + ", institute=" + institute + ", trainingFromDate="
				+ trainingFromDate + ", trainingToDate=" + trainingToDate + ", trainingHours=" + trainingHours
				+ ", trainingDescription=" + trainingDescription + "]";
	}

}
