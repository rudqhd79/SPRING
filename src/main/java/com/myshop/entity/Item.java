package com.myshop.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.myshop.constant.ItemSellStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "item")	//테이블명을 설정
@Getter
@Setter
@ToString
public class Item {
	//not null이 아닐때는 필드 타입을 객체(ex : int - Integer)로 지정해야 한다
	
	@Id
	@Column(name = "item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 	//상품코드
	
	@Column(nullable = false, length = 50)	 //not null
	private String itemNm;	//상품명
	
	@Column(nullable = false, name ="price")	 //not null
	private int price;	//가격
	
	@Column(nullable = false)	 //not null
	private int stockNumber;	//재고수량

	@Lob
	@Column(nullable = false)	 //not null
	private String itemDetail;	//상품 상세설명
	
	@Enumerated(EnumType.STRING)	//열거형 타입이기 때문에 상품이 2개밖에 저장이 안된다 (Enum에 있는 SELL, SOLD_OUT)
	private ItemSellStatus itemSellStatus;	//상품 판매상태
	
	private LocalDateTime regTime;	//등록 시간
	
	private LocalDateTime updateTime;	//수정 시간
	
	
}
