package sayem.picoapps.domains.embedded;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class PersonalInfo {
	private String fullName;
	private byte[] profilePhoto;
	private String phoneNumber;
	private String presentAddress;
	private String parmanentAddress;
	private String resumeTitle;
	private String[] keywords;
	private String fathersName;
	private String mothersName;
	private Date bithDate;
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

	public String[] getKeywords() {
		return keywords;
	}

	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
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

	public Date getBithDate() {
		return bithDate;
	}

	public void setBithDate(Date bithDate) {
		this.bithDate = bithDate;
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
		return "PersonalInfo [fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", presentAddress="
				+ presentAddress + ", parmanentAddress=" + parmanentAddress + ", resumeTitle=" + resumeTitle
				+ ", keywords=" + Arrays.toString(keywords) + ", fathersName=" + fathersName + ", mothersName="
				+ mothersName + ", bithDate=" + bithDate + ", maritalStatus=" + maritalStatus + ", religion=" + religion
				+ ", nationality=" + nationality + ", nationalIdNumber=" + nationalIdNumber + "]";
	}

}
