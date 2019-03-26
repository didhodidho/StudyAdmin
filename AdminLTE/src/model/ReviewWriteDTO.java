package model;

public class ReviewWriteDTO {
	private String reviewcontents;
	private double score;
	private String writetime;
	private String id;
	private String acaidx;
	private String reviewidx;
	
	private String starRaiting;
	
	public ReviewWriteDTO() {}

	public ReviewWriteDTO(String reviewcontents, double score, String writetime, String id, String acaidx,
			String reviewidx) {
		super();
		this.reviewcontents = reviewcontents;
		this.score = score;
		this.writetime = writetime;
		this.id = id;
		this.acaidx = acaidx;
		this.reviewidx = reviewidx;
	}

	
	public ReviewWriteDTO(String reviewcontents, double score, String writetime, String id, String acaidx,
			String reviewidx, String starRaiting) {
		super();
		this.reviewcontents = reviewcontents;
		this.score = score;
		this.writetime = writetime;
		this.id = id;
		this.acaidx = acaidx;
		this.reviewidx = reviewidx;
		this.starRaiting = starRaiting;
	}

	public String getStarRaiting() {
		return starRaiting;
	}

	public void setStarRaiting(String starRaiting) {
		this.starRaiting = starRaiting;
	}

	public String getReviewcontents() {
		return reviewcontents;
	}

	public void setReviewcontents(String reviewcontents) {
		this.reviewcontents = reviewcontents;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getWritetime() {
		return writetime;
	}

	public void setWritetime(String writetime) {
		this.writetime = writetime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAcaidx() {
		return acaidx;
	}

	public void setAcaidx(String acaidx) {
		this.acaidx = acaidx;
	}

	public String getReviewidx() {
		return reviewidx;
	}

	public void setReviewidx(String reviewidx) {
		this.reviewidx = reviewidx;
	}

    
		
}
