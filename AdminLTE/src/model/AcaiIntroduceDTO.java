package model;

public class AcaiIntroduceDTO {
	private String acaintrophoto;
	private String category;
	private String id;
	private String introduce;
	
	public AcaiIntroduceDTO(String acaintrophoto, String category, String id, String introduce) {
		super();
		this.acaintrophoto = acaintrophoto;
		this.category = category;
		this.id = id;
		this.introduce = introduce;
	}
	public AcaiIntroduceDTO() {
		super();
	}
	public String getAcaintrophoto() {
		return acaintrophoto;
	}
	public void setAcaintrophoto(String acaintrophoto) {
		this.acaintrophoto = acaintrophoto;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	
}
