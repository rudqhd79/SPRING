package com.myshop.repository;
 
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import com.myshop.constant.ItemSellStatus;
import com.myshop.entity.Item;
import com.myshop.entity.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@SpringBootTest // 스프링 부트에서 테스트 하겠다는 의미를 갖는다
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

	@Autowired // 의존성 주입()
	ItemRepository itemRepository;
	
	//영속성 컨텍스트
	@PersistenceContext
	EntityManager em;	//엔티티 매니저

	/*
	 * // @Test // @DisplayName("상품 저장 테스트") public void createItemTest() { Item
	 * item = new Item(); item.setItemNm("테스트 상품"); item.setPrice(10000);
	 * item.setItemDetail("테스트 상품 상세 설명");
	 * item.setItemSellStatus(ItemSellStatus.SELL); item.setStockNumber(100);
	 * item.setRegTime(LocalDateTime.now());
	 * item.setUpdateTime(LocalDateTime.now());
	 * 
	 * Item savedItem = itemRepository.save(item); //데이터 insert
	 * 
	 * System.out.println(savedItem.toString()); }
	 */

	
	public void createItemTest() {
		for (int i = 1; i <= 5; i++) {

			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());

			Item savedItem = itemRepository.save(item); // 데이터 insert
		}
	}
	
	public void createItemTest2() {
		for (int i = 6; i <= 10; i++) {

			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
			item.setStockNumber(0);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());

			Item savedItem = itemRepository.save(item); // 데이터 insert
		}
	}

	@Test
	@DisplayName("상품명 조회 테스트")
	public void findByItemNmTest() {
		this.createItemTest(); // item 테이블에 insert
		List<Item> itemList = itemRepository.findByItemNm("테스트 상품2");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("상품명, 상품상세설명 or 테스트")
	public void findByItemNmorItemDetailTest() {
		this.createItemTest();// 아이템 10개 DB에 insert
		List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("가격 LessThan 테스트")
	public void findByPriceLessThanTest() {
		this.createItemTest();// 아이템 10개 DB에 insert
		List<Item> itemList = itemRepository.findByPriceLessThan(10005);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("가격 내림차순 테스트")
	public void findByPriceLessThanOrderByPriceDesc() {
		this.createItemTest();// 아이템 10개 DB에 insert
		List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
/*
	@Test
	@DisplayName("퀴즈 1-1")
	public void findByitemNmAndItemSellStatus() {
		this.createItemTest();// 아이템 10개 DB에 insert
		List<Item> itemList = itemRepository.findByitemNmAndItemSellStatus("테스트 상품1", ItemSellStatus.SELL);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("퀴즈 1-2")
	public void findByPriceBetween() {
		this.createItemTest();// 아이템 10개 DB에 insert
		List<Item> itemList = itemRepository.findByPriceBetween(10004, 10008);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("퀴즈 1-3")
	public void findByRegTimeAfter() {
		this.createItemTest();// 아이템 10개 DB에 insert
		List<Item> itemList = itemRepository.findByRegTimeAfter(LocalDateTime.of(2023, 1, 1, 12, 12, 44));
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("퀴즈 1-4")
	public void findByitemSellStatusIsNotNull() {
		this.createItemTest();// 아이템 10개 DB에 insert
		List<Item> itemList = itemRepository.findByitemSellStatusIsNotNull();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("퀴즈 1-5")
	public void findByitemDetailLike() {
		this.createItemTest();// 아이템 10개 DB에 insert
		List<Item> itemList = itemRepository.findByitemDetailLike("%설명1");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	*/
	/*
	@Test
	@DisplayName("@Query를 이용한 상품 조회 테스트")
	public void findByItemDetailTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	
	@Test
	@DisplayName("@native Query를 이용한 상품 조회 테스트")
	public void findByItemDetailByNativeTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품 상세 설명");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}

	@Test
	@DisplayName("퀴즈 2-1")
	public void findByPriceTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByPrice(10005);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("퀴즈 2-2")
	public void findByitemNmOrItemSellStatusTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByitemNmOrItemSellStatus("테스트 상품1", ItemSellStatus.SELL);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	*/
	
	@Test
	@DisplayName("querydsl 조회 테스트")
	public void queryDslTest() {
		this.createItemTest();
		//query를 동적으로 생성하기 위한 객체
		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;
		//DB => select from Item where itemSellStatus = 'SELL'
		JPAQuery<Item> query = qf.selectFrom(qItem)
														//eq는 equal
													    .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
													    .where(qItem.itemDetail.like("%테스트 상품 상세 설명%"))
													    .orderBy(qItem.price.desc());
		//fetch()는 결과를 조회하는 것
		List<Item> itemList = query.fetch();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	/*
	@Test
	@DisplayName("querydsl 조회 테스트2")
	public void queryDslTest2() {
		this.createItemTest2();
		//쿼리에 들어갈 조건을 만들어주는 빌더
		BooleanBuilder b = new BooleanBuilder();
		QItem item = QItem.item;
		
		String itemDetail = "테스트 상품 상세 설명";
		int price = 10003;
		String itemSellStat = "SELL";
		
		b.and(item.itemDetail.like("%" + itemDetail + "%"));
		//gt는 ~보다 큰 ((price = 10003) 보다 큰)
		b.and(item.price.gt(price));
		
		if(StringUtils.equals(itemSellStat, ItemSellStatus.SELL)) {
			b.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
		}
		
		//of (조회할 페이지의 번호, 한페이지당 조회할 데이터의 갯수)
		Pageable page = PageRequest.of(0, 5);
		Page<Item> itemPageResult = itemRepository.findAll(b, page);
	}
	*/
	
	@Test
	@DisplayName("querydsl 조회 테스트 다른 방법")
	public void queryDslTest3() {
		this.createItemTest2();
		//query를 동적으로 생성하기 위한 객체
		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;
		Pageable page = PageRequest.of(0, 2);
		
		JPAQuery<Item> query = qf.selectFrom(qItem)
														//eq는 equal
													    .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
													    .where(qItem.itemDetail.like("%테스트 상품 상세 설명%"))
													    .where(qItem.price.gt(10003))
													    .offset(page.getOffset())
													    .limit(page.getPageSize());
		//fetch()는 결과를 조회하는 것
		List<Item> itemList = query.fetch();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("퀴즈 1-1 querydsl")
	public void queryDslQuiz1() {
		this.createItemTest2();
		//query를 동적으로 생성하기 위한 객체
		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;
		//DB => select from Item where itemSellStatus = 'SELL'
		JPAQuery<Item> query = qf.selectFrom(qItem)
														//eq는 equal
													    .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
													    .where(qItem.itemNm.like("%테스트 상품1%"))
													    .orderBy(qItem.price.desc());
		//fetch()는 결과를 조회하는 것
		List<Item> itemList = query.fetch();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("퀴즈 1-2 querydsl")
	public void queryDslQuiz2() {
		this.createItemTest2();
		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;
		JPAQuery<Item> query = qf.selectFrom(qItem)
													.where(qItem.price.between(10004, 10008));
		List<Item> itemList = query.fetch();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("퀴즈 1-3 querydsl")
	public void queryDslQuiz3() {
		this.createItemTest2();
		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;
		JPAQuery<Item> query = qf.selectFrom(qItem)
													.where(qItem.regTime.after(LocalDateTime.of(2023, 1, 1, 12, 12, 44)));
		List<Item> itemList = query.fetch();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("퀴즈 1-4 querydsl")
	public void queryDslQuiz4() {
		this.createItemTest2();
		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;
		JPAQuery<Item> query = qf.selectFrom(qItem)
													.where(qItem.itemSellStatus.isNotNull());
		List<Item> itemList = query.fetch();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("퀴즈 1-5 querydsl")
	public void queryDslQuiz5() {
		this.createItemTest2();
		this.createItemTest();
		JPAQueryFactory qf = new JPAQueryFactory(em);
		QItem qItem = QItem.item;
		JPAQuery<Item> query = qf.selectFrom(qItem)
													.where(qItem.itemDetail.like("%설명1"));
		List<Item> itemList = query.fetch();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
}
