package sayem.picoapps.domains.embedded;

import javax.persistence.Embeddable;

@Embeddable
public class EducationInfo {
	private String degree;
	private String majorSubject;
	private String institute;
	private int passingYear;
	private String result;

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getMajorSubject() {
		return majorSubject;
	}

	public void setMajorSubject(String majorSubject) {
		this.majorSubject = majorSubject;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public int getPassingYear() {
		return passingYear;
	}

	public void setPassingYear(int passingYear) {
		this.passingYear = passingYear;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "EducationInfo [degree=" + degree + ", majorSubject=" + majorSubject + ", institute=" + institute
				+ ", passingYear=" + passingYear + ", result=" + result + "]";
	}

}
