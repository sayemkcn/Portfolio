package sayem.picoapps.domains.embedded;

import javax.persistence.Embeddable;

@Embeddable
public class References {
	private String referenceName;
	private String referenceDescription;

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public String getReferenceDescription() {
		return referenceDescription;
	}

	public void setReferenceDescription(String referenceDescription) {
		this.referenceDescription = referenceDescription;
	}

	@Override
	public String toString() {
		return "ReferenceInfo [referenceName=" + referenceName + ", referenceDescription=" + referenceDescription + "]";
	}

}
