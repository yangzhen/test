package com.uc.j8;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * JDK8 学习
 * 需要JDK8
 * 
 * @author ray
 *
 */
public class ProcessMax {
	public static void main(String[] args) {
		Tran tr1 = new Tran(LocalDate.of(2016, 7, 1), "A", 5000); //
		Tran tr2 = new Tran(LocalDate.of(2016, 7, 1), "B", 4000);
		Tran tr3 = new Tran(LocalDate.of(2016, 7, 1), "C", 3000);
		Tran tr4 = new Tran(LocalDate.of(2016, 7, 7), "A", 6000); //
		Tran tr5 = new Tran(LocalDate.of(2016, 7, 7), "B", 6000); //
		Tran tr6 = new Tran(LocalDate.of(2016, 7, 7), "C", 5000);
		Tran tr7 = new Tran(LocalDate.of(2016, 7, 9), "A", 3000);
		Tran tr8 = new Tran(LocalDate.of(2016, 7, 9), "B", 4000);
		Tran tr9 = new Tran(LocalDate.of(2016, 7, 9), "C", 5000); //
		Tran tr10 = new Tran(LocalDate.of(2016, 7, 9), "D", 5000); //
		
		List<Tran> list = new ArrayList<>();
		list.add(tr1);
		list.add(tr2);
		list.add(tr3);
		list.add(tr4);
		list.add(tr5);
		list.add(tr6);
		list.add(tr7);
		list.add(tr8);
		list.add(tr9);
		list.add(tr10);
		
		Stream<Tran> s = list.stream();
		// Function<Tran, LocalDate> getDate = t -> t.getDate();
		// Map<LocalDate, List<Tran>> map = s.collect(Collectors.groupingBy(getDate));
		
		List<Tran> l = new ArrayList<Tran>();
		List<Tran> l2 = new ArrayList<Tran>();
		
		// try 1
		s.collect(Collectors.groupingBy(t -> t.getDate())).values().forEach(lt -> {
			BigDecimal amount = lt.stream().max(Comparator.comparing(Tran::getAmount)).get().getAmount();
			lt.stream().forEach(t -> {
				if (t.getAmount().compareTo(amount) == 0) {
					l.add(t);
				}
			});
		});
		System.out.println("this is the answer:" + l);
		
		// try 2, bad
		list.stream().collect(Collectors.groupingBy(t -> t.getDate())).values().forEach(lt -> {
				Tran maxTr = lt.stream().collect(Collectors.maxBy(Comparator.comparing(Tran::getAmount))).get();
			l2.add(maxTr);
		});
		System.out.println("this is bad:" + l2);

		Map<LocalDate, List<Tran>> h = list.stream().collect(Collectors.groupingBy(Tran::getDate));
		System.out.println(h);
		System.out.println("=====");
		h.values().forEach(t->{
			System.out.println(t);
		});
		
	}
}


class Tran {
	private LocalDate date;
	private String name;
	private BigDecimal amount = BigDecimal.ZERO;

	public Tran(LocalDate date, String name, int amount) {
		this.date = date;
		this.name = name;
		this.amount = new BigDecimal(amount);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Tran [date=" + date + ", name=" + name + ", amount=" + amount + "]";
	}
	
	

}