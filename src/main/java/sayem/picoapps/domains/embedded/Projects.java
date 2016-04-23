package sayem.picoapps.domains.embedded;

import java.util.Arrays;

import javax.persistence.Embeddable;

@Embeddable
public class Projects {
	private String title;
	private String description;
	private String url;
	private String[] usedTechnologies;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String[] getUsedTechnologies() {
		return usedTechnologies;
	}

	public void setUsedTechnologies(String[] usedTechnologies) {
		this.usedTechnologies = usedTechnologies;
	}

	@Override
	public String toString() {
		return "Projects [title=" + title + ", description=" + description + ", url=" + url + ", usedTechnologies="
				+ Arrays.toString(usedTechnologies) + "]";
	}

	public String getUsedTechnologiesAsString() {
		String delim = "";
		StringBuilder sb = new StringBuilder();
		for (String i : this.getUsedTechnologies()) {
			sb.append(delim).append(i);
			delim = ",";
		}
		return sb.toString();
	}

}
