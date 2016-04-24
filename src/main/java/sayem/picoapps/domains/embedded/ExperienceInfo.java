package sayem.picoapps.domains.embedded;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class ExperienceInfo {
	private String companyName;
	private String companyWebsite;
	@DateTimeFormat(pattern="yyyy-dd-MM")
	@Temporal(TemporalType.DATE)
	private Date experienceFromDate;
	@DateTimeFormat(pattern="yyyy-dd-MM")
	@Temporal(TemporalType.DATE)
	private Date experienceToDate;
	private boolean currentJob;
	private String companyType;
	private String designation;
	private String department;
	private String jobResponsibility;
	private String employmentType;
	private String role;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public Date getExperienceFromDate() {
		return experienceFromDate;
	}

	public void setExperienceFromDate(Date experienceFromDate) {
		this.experienceFromDate = experienceFromDate;
	}

	public Date getExperienceToDate() {
		return experienceToDate;
	}

	public void setExperienceToDate(Date experienceToDate) {
		this.experienceToDate = experienceToDate;
	}

	public boolean isCurrentJob() {
		return currentJob;
	}

	public void setCurrentJob(boolean currentJob) {
		this.currentJob = currentJob;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getJobResponsibility() {
		return jobResponsibility;
	}

	public void setJobResponsibility(String jobResponsibility) {
		this.jobResponsibility = jobResponsibility;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "ExperienceInfo [companyName=" + companyName + ", companyWebsite=" + companyWebsite
				+ ", experienceFromDate=" + experienceFromDate + ", experienceToDate=" + experienceToDate
				+ ", currentJob=" + currentJob + ", companyType=" + companyType + ", designation=" + designation
				+ ", department=" + department + ", jobResponsibility=" + jobResponsibility + ", employmentType="
				+ employmentType + ", role=" + role + "]";
	}

}
