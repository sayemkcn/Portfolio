package sayem.picoapps.domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import sayem.picoapps.domains.embedded.CareerInfo;
import sayem.picoapps.domains.embedded.EducationInfo;
import sayem.picoapps.domains.embedded.ExperienceInfo;
import sayem.picoapps.domains.embedded.PersonalInfo;
import sayem.picoapps.domains.embedded.Projects;
import sayem.picoapps.domains.embedded.References;
import sayem.picoapps.domains.embedded.TrainingInfo;

@Entity
public class Cv extends BaseEntity {
	@Embedded
	private PersonalInfo personalInfo;
	@Embedded
	private CareerInfo careerInfo;
	@ElementCollection
	private List<EducationInfo> educationInfoList = new ArrayList<>();
	@ElementCollection
	private List<ExperienceInfo> experienceInfoList = new ArrayList<>();
	@ElementCollection
	private List<Projects> projectsList = new ArrayList<>();
	@ElementCollection
	private List<TrainingInfo> trainingInfoList = new ArrayList<>();
	@ElementCollection
	private List<References> referenceList = new ArrayList<>();

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public CareerInfo getCareerInfo() {
		return careerInfo;
	}

	public void setCareerInfo(CareerInfo careerInfo) {
		this.careerInfo = careerInfo;
	}

	public List<EducationInfo> getEducationInfoList() {
		return educationInfoList;
	}

	public void setEducationInfoList(List<EducationInfo> educationInfoList) {
		this.educationInfoList = educationInfoList;
	}

	public List<ExperienceInfo> getExperienceInfoList() {
		return experienceInfoList;
	}

	public void setExperienceInfoList(List<ExperienceInfo> experienceInfoList) {
		this.experienceInfoList = experienceInfoList;
	}

	public List<Projects> getProjectsList() {
		return projectsList;
	}

	public void setProjectsList(List<Projects> projectsList) {
		this.projectsList = projectsList;
	}

	public List<TrainingInfo> getTrainingInfoList() {
		return trainingInfoList;
	}

	public void setTrainingInfoList(List<TrainingInfo> trainingInfoList) {
		this.trainingInfoList = trainingInfoList;
	}

	public List<References> getReferenceList() {
		return referenceList;
	}

	public void setReferenceList(List<References> referenceList) {
		this.referenceList = referenceList;
	}

	@Override
	public String toString() {
		return "Cv [personalInfo=" + personalInfo + ", careerInfo=" + careerInfo + ", educationInfoList="
				+ educationInfoList + ", experienceInfoList=" + experienceInfoList + ", projectsList=" + projectsList
				+ ", trainingInfoList=" + trainingInfoList + ", referenceList=" + referenceList + "]";
	}

}
