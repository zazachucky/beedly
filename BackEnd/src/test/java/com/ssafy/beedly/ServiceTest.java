package com.ssafy.beedly;

import java.util.Collections;
import java.util.List;

import com.ssafy.beedly.domain.*;
import com.ssafy.beedly.repository.AbsenteeBidRepository;
import com.ssafy.beedly.repository.SpecialBoardRepository;
import com.ssafy.beedly.repository.query.AbsenteeBidQueryRepository;
import com.ssafy.beedly.service.AbsenteeBidService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.beedly.repository.PersonalProductRepository;
import com.ssafy.beedly.repository.query.PersonalProductQueryRepository;
import com.ssafy.beedly.service.PersonalProductService;

import javax.persistence.EntityExistsException;

@SpringBootTest
//@Transactional
public class ServiceTest {

	/*
	@Autowired
	PersonalProductService personalProductService;

	@Autowired
	SpecialBoardRepository specialBoardRepository;

	@Autowired
	PersonalProductRepository personalProductRepository;
	private Object Collections;

	@Test
	public void CRUD(){
		PersonalProduct personalProduct = new PersonalProduct("안녕");
		personalProductService.save(personalProduct);

		PersonalProduct product1 = personalProductRepository.findPersonalProductByProductNameLike(
			personalProduct.getProductName()).get(0);

		System.out.println(product1.getId());

		product1.setProductName("안녕하세요");
		personalProductService.update(product1);

		List<PersonalProduct> product2 = personalProductRepository.findPersonalProductByProductNameLike(
			"안녕");

		System.out.println(product2);
	}

	@Test
	public void Category(){
		List<PersonalProduct> products = personalProductRepository.findPersonalProductByOrderByStartTime("회화");
		System.out.println(products);
	}

	@Test
	public void SpecialBoardRepository(){
		List<SpecialAuction> boards = specialBoardRepository.findSpecialBoardByOnAirOrderByStartTimeDesc();
		for (SpecialAuction board : boards) {

			System.out.println(board.getId());
		}
	}
	 */

	@Autowired
	AbsenteeBidRepository absenteeBidRepository;

	@Autowired
	AbsenteeBidQueryRepository absenteeBidQueryRepository;

	@Autowired
	AbsenteeBidService absenteeBidService;

	@Test
	public void setAbsenteeBidQueryRepository() {
		AbsenteeBid absenteeBid = new AbsenteeBid(null, 500000, new PersonalProduct(4L, null,null,null,null,null,null,null,null,null,null,null,null), new User(1L, null, null, null, null, null, null, null, null, null));
//		absenteeBidRepository.save(absenteeBid);
		absenteeBidService.save(absenteeBid);
//		absenteeBidService.update(absenteeBid);
	}

}
