package model;

public class AcaTeacherDTO {
	
	private String id;
	private String subject;
	private String teaidx;
	private String teaimage;
	private String teaintro;
	private String teaname;
	
	public AcaTeacherDTO() {
	}

	public AcaTeacherDTO(String id, String subject, String teaidx, String teaimage, String teaintro, String teaname) {

		this.id = id;
		this.subject = subject;
		this.teaidx = teaidx;
		this.teaimage = teaimage;
		this.teaintro = teaintro;
		this.teaname = teaname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTeaidx() {
		return teaidx;
	}

	public void setTeaidx(String teaidx) {
		this.teaidx = teaidx;
	}

	public String getTeaimage() {
		return teaimage;
	}

	public void setTeaimage(String teaimage) {
		this.teaimage = teaimage;
	}

	public String getTeaintro() {
		return teaintro;
	}

	public void setTeaintro(String teaintro) {
		this.teaintro = teaintro;
	}

	public String getTeaname() {
		return teaname;
	}

	public void setTeaname(String teaname) {
		this.teaname = teaname;
	}
	
	
}
