package model;

import java.util.Date;

public class MembersDTO {
	//멤버변수
	private String id;			//아이디
	private String pass;		//패스워드
	private Date regidate;		//가입일자
	private String emailId;		//이메일아이디
	private String emailDomain;	//이메일도메인
	private String mobile1;		//휴대폰1
	private String mobile2;		//휴대폰2
	private String mobile3;		//휴대폰3
	private String name;		//가입자이름(학원은 대표자명)
	private int idx;			//아이디 고유번호
	private int grade;			//아이디 등급
	private String telephone1;	//대표전화1(학원회원만)
	private String telephone2;	//대표전화2(학원회원만)
	private String telephone3;	//대표전화3(학원회원만)
	private String address;		//학원주소(학원회원만)
	private String detailAddress;//학원상세주소(학원회원만)
	private String interest;	//관심사(일반회원만)
	private String acaName;		//학원명(학원회원만
	//기본생성자
	public MembersDTO() {}
	//인자생성자
	public MembersDTO(String id, String pass, Date regidate, String emailId, String emailDomain, String mobile1,
			String mobile2, String mobile3, String name, int idx, int grade, String telephone1, String telephone2,
			String telephone3, String address, String detailAddress, String interest, String acaName) {
		this.id = id;
		this.pass = pass;
		this.regidate = regidate;
		this.emailId = emailId;
		this.emailDomain = emailDomain;
		this.mobile1 = mobile1;
		this.mobile2 = mobile2;
		this.mobile3 = mobile3;
		this.name = name;
		this.idx = idx;
		this.grade = grade;
		this.telephone1 = telephone1;
		this.telephone2 = telephone2;
		this.telephone3 = telephone3;
		this.address = address;
		this.detailAddress = detailAddress;
		this.interest = interest;
		this.acaName = acaName;
	}
	//getter/setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Date getRegidate() {
		return regidate;
	}
	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getEmailDomain() {
		return emailDomain;
	}
	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getMobile3() {
		return mobile3;
	}
	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getTelephone1() {
		return telephone1;
	}
	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}
	public String getTelephone2() {
		return telephone2;
	}
	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}
	public String getTelephone3() {
		return telephone3;
	}
	public void setTelephone3(String telephone3) {
		this.telephone3 = telephone3;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getAcaName() {
		return acaName;
	}
	public void setAcaName(String acaName) {
		this.acaName = acaName;
	}
	
}
