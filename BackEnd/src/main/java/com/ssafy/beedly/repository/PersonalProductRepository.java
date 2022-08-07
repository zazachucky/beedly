package com.ssafy.beedly.repository;

import com.ssafy.beedly.domain.PersonalAuction;
import com.ssafy.beedly.domain.PersonalFavorite;
import com.ssafy.beedly.domain.PersonalProduct;
import com.ssafy.beedly.domain.SearchTag;
import com.ssafy.beedly.domain.User;
import com.ssafy.beedly.dto.PersonalProductCloseDto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

public interface PersonalProductRepository extends JpaRepository<PersonalProduct, Long>{


    //----------- 1. productName 검색하기
    @Query(value="select c from PersonalProduct c where c.productName like %:productName%")
    List<PersonalProduct> findPersonalProductByProductNameLike(@Param("productName") String productName);

    //------------ 2. 카테고리별로 정렬하기
    @Query(value="select p from PersonalProduct p join fetch p.category c where c.categoryName = :categoryName")
    Slice<PersonalProduct> findProductByCategory(@Param("categoryName")String categoryName, @Param("pageable") Pageable pageable);

    ///------------ 2. 현재 진행중인 상품 카테고리별로 정렬하기
    @Query(value="select pa from PersonalAuction pa join fetch pa.personalProduct p"
            +" join fetch p.category c"+" where c.categoryName = :categoryName and  pa.activeFlag = true")
    Slice<PersonalAuction> findProductOnAirByCategory(@Param("categoryName")String categoryName, @Param("pageable") Pageable pageable);

    //----------- 4. size로 찾기
    @Query(value = "select p from PersonalProduct p where p.width =:width and p.height =:height")
    Slice<PersonalProduct> findProductBySize(@Param("width")Integer width, @Param("height")Integer height, @Param("pageable") Pageable pageable);

    //---------- 5. Product 상세 찾기

    //----------- 5-1. 해당 Product의 상시 게시글 찜의 사용자 아이디가 있는지?
    @Query(value="select p.user from PersonalFavorite p where p.personalProduct.id = :productId and p.user.id = :userId")
    Optional<User> findUserIdByPersonalFavorite(Long productId, Long userId);

    //----------- 5-2. 서면 응찰을 했는지?
    @Query(value="select a.user from AbsenteeBid a where a.personalProduct.id = :productId and a.user.id = :userId")
    Optional<User> findUserIdByAbsenteeBid(Long productId, Long userId);

    //---------- 5-3. Tag가 무엇인지?
    @Query(value="select s from SearchTag s join fetch s.tags t where t.personalProduct.id = :productId")
    List<SearchTag> findSearchTagByProductId(Long productId);


}
