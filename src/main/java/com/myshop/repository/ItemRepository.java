package com.myshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myshop.constant.ItemSellStatus;
import com.myshop.entity.Item;

//JpaRepository : 기본적인 CRUD 및 페이징 처리를 위한 메소드가 정의가 되어있다
//JpaRepository<사용할 엔티티 클래스, 기본키 타입>
public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findByItemNm(String itemNm);
	//쿼리문 => select - from item where item_nm = ?
	
	//쿼리문 => select - from item where item_nm = ? or item_detail = ?
	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
	
	//쿼리문 => select * from item where price < ?
	List<Item> findByPriceLessThan(Integer price);
	
	//쿼리문 => select * from item where price < ? order by price desc
	List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
	
/*	//퀴즈
	List<Item> findByitemNmAndItemSellStatus(String product, ItemSellStatus status);

	List<Item> findByPriceBetween(Integer price, Integer end);
	
	List<Item> findByRegTimeAfter(LocalDateTime dateTime);
	
	List<Item> findByitemSellStatusIsNotNull();
	
	List<Item> findByitemDetailLike(String string);
	*/
	
	//from에는 엔티티 명을 붙여주면 된다 (엔티티클래스 명 == 테이블명)
/*	@Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
	List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);	*/
	//@Param은 jpql을 실행할때 itemDetail을 파라미터로 변환하기 위해 써준다
	
	//like에 %?2%가 들어가면 두번째 값만 띄우는 것이다
	@Query("select i from Item i where i.itemDetail like %?1% order by i.price desc")
	List<Item> findByItemDetail(String itemDetail1);
	//매개변수를 2개를 쓰면 ?에 상관없이 들어간다
	
	@Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
	List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
	
	@Query("select i from Item i where i.price >= :itemPrice")
	List<Item> findByPrice(@Param("itemPrice") int price);
	
	@Query("select i from Item i where itemNm like %:itemNm and i.itemSellStatus = :itemSellStatus")
	List<Item> findByitemNmOrItemSellStatus(@Param("itemNm") String item1, @Param("itemSellStatus") ItemSellStatus item2);
	
	
}
