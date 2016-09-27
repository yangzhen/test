package com.uc.j8;

public class UserScore {

	private Integer userId;

	private String course;

	private Integer score;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "UserScore [userId=" + userId + ", course=" + course + ", score=" + score + "]";
	}

}
