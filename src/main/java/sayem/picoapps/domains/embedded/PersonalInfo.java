package sayem.picoapps.domains.embedded;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class PersonalInfo {
	private String fullName;
	@Column(length = 3000000)
	private byte[] profilePhoto;
	private String phoneNumber;
	private String presentAddress;
	private String parmanentAddress;
	private String resumeTitle;
	private String fathersName;
	private String mothersName;
	@DateTimeFormat(pattern = "yyyy-dd-MM")
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	private String maritalStatus;
	private String religion;
	private String nationality;
	private String nationalIdNumber;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public byte[] getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(byte[] profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public String getParmanentAddress() {
		return parmanentAddress;
	}

	public void setParmanentAddress(String parmanentAddress) {
		this.parmanentAddress = parmanentAddress;
	}

	public String getResumeTitle() {
		return resumeTitle;
	}

	public void setResumeTitle(String resumeTitle) {
		this.resumeTitle = resumeTitle;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getMothersName() {
		return mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNationalIdNumber() {
		return nationalIdNumber;
	}

	public void setNationalIdNumber(String nationalIdNumber) {
		this.nationalIdNumber = nationalIdNumber;
	}

	@Override
	public String toString() {
		return "PersonalInfo [fullName=" + fullName + ", profilePhoto=" + Arrays.toString(profilePhoto)
				+ ", phoneNumber=" + phoneNumber + ", presentAddress=" + presentAddress + ", parmanentAddress="
				+ parmanentAddress + ", resumeTitle=" + resumeTitle + ", fathersName=" + fathersName + ", mothersName="
				+ mothersName + ", birthDate=" + birthDate + ", maritalStatus=" + maritalStatus + ", religion="
				+ religion + ", nationality=" + nationality + ", nationalIdNumber=" + nationalIdNumber + "]";
	}

}
