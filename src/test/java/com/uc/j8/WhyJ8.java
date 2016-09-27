package com.uc.j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class WhyJ8 {

	private static final Logger logger = LoggerFactory.getLogger(WhyJ8.class);
	List<UserScore> list = new ArrayList<UserScore>();
	
	public List<UserScore> initList() {
		UserScore score = new UserScore();
		score.setUserId(1);
		score.setCourse("chinese");
		score.setScore(8);
		list.add(score);
		UserScore score2 = new UserScore();
		score2.setUserId(7);
		score2.setCourse("math");
		score2.setScore(3);
		list.add(score2);
		UserScore score3 = new UserScore();
		score3.setUserId(7);
		score3.setCourse("english");
		score3.setScore(5);
		list.add(score3);
		return list;
	}
	
	@org.junit.Before
	public void before() {
		list = initList();
	}
	
	@Test
	public void testJ8Com() {
		//list.sort(comparingDouble(UserScore::getScore).re);
		list.sort((o1,o2)->o1.getScore().compareTo(o2.getScore()));
		list.forEach(t -> System.out.println(t));
		
		list.sort((UserScore o1, UserScore o2) -> o1.getCourse().compareTo(o2.getCourse()));
		list.forEach(t -> logger.info(t.toString()));
	}

	@Test
	public void testOrigin() {
		Collections.sort(list, new Comparator<UserScore>() {
			@Override
			public int compare(UserScore o1, UserScore o2) {
				return Integer.compare(o1.getScore(), o2.getScore());
			}

		});
		list.forEach(t -> System.out.println(t));
	}

}
