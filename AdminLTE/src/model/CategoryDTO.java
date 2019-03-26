package model;

public class CategoryDTO {
	
	private String maincategory; //대분류 (예체능, 대입, 기타)
	private String middlecategory; //중분류 (국,영,수,미술,음악,운동)

	public CategoryDTO() {}

	public CategoryDTO(String maincategory, String middlecategory) {
		this.maincategory = maincategory;
		this.middlecategory = middlecategory;
	}

	public String getMaincategory() {
		return maincategory;
	}

	public void setMaincategory(String maincategory) {
		this.maincategory = maincategory;
	}

	public String getMiddlecategory() {
		return middlecategory;
	}

	public void setMiddlecategory(String middlecategory) {
		this.middlecategory = middlecategory;
	}

	
	
	
	
	
}
