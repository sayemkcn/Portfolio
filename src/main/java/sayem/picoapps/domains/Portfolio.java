package sayem.picoapps.domains;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import org.hibernate.validator.constraints.URL;

@Entity
public class Portfolio extends BaseEntity {
	private String title;
	private String description;
	@Lob
	@Column(length = 2007215)
	private byte[] image;
	@URL
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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
		return "Portfolio [title=" + title + ", description=" + description + ", image=" + Arrays.toString(image)
				+ ", url=" + url + ", usedTechnologies=" + Arrays.toString(usedTechnologies) + "]";
	}
	
	public String getUsedTechnologiesAsString(){
		String delim = "";
		StringBuilder sb = new StringBuilder();
		for (String i : this.getUsedTechnologies()) {
		    sb.append(delim).append(i);
		    delim = ",";
		}
		return sb.toString();
	}

}
