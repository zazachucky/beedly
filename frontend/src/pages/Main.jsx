import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { CategoryBar } from "../components/MainCategoryBar";
import { BannerTable } from "../components/MainBanner";
import {
  HorizonScrollRowTable,
  HorizonScrollColTable,
} from "../components/HorizonScrollTable";
import { getArtistApi, getOnairApi, getPersonalProductListApi, getProductListBySizeApi, getRecommendationProductApi } from "../utils/api";

const StyledTableTitle = styled.div`
  font-size: 16px;
  color: #1f1d1d;
  padding-left: 20px;
  font-weight: 900;
`;
const StyledTableSubtitle = styled.div`
  font-size: 14px;
  padding-left: 20px;
  font-weight: 500;
  color: rgba(31, 29, 29, 0.4);
  `;

export default function MainPage() {
  const [loading, setloading] = useState(true);
  const [OnairList, setOnairList] = useState([]);
  const [ArtistList, setArtistList] = useState([]);
  const [ArtForYouList, setArtForYouList] = useState([]);
  const [NewProductList, setNewProductList] = useState([]);
  const [SizeProductList, setSizeProductList] = useState([]);
  const ProductSizeList = [
    {
      size: "small",
      description: "책상 위 머그컵 사이즈 작품",
      sizeName: "Mug",
    },
    {
      size: "medium",
      description: "작은 벽에 걸어두기 좋은 도화지 사이즈",
      sizeName: "Small Wall",
    },
    {
      size: "large",
      description: "TV 대신 한편의 미술품 감상이 가능한 사이즈",
      sizeName: "TV",
    },
    {
      size: "xlarge",
      description: "당신의 집을 커다란 작품으로!",
      sizeName: "Big",
    }]
  const [Size, setSize] = useState({});
  useEffect(() => {
    if (loading) {

      // 진행중인 경매
      getOnairApi("0", "20", "", (res) => {
        console.log(res);
        setOnairList(res.data.content);
      }, (err) => {
        console.log(err);
      })

      // 작가 목록 가져오기
      getArtistApi("0", "20", "", (res) => {
        setArtistList(res.data.content);
      }, (err) => {
        console.log(err);
      })

      //Art for you 목록 가져오기
      getRecommendationProductApi((res) => {
        setArtForYouList(res.data);
        console.log(res.data);
      }, (err) => {
        console.log(err);
      })

      //신규 작품 목록 가져오기
      getPersonalProductListApi("0", "20", "createdDate,DESC", (res) => {
        setNewProductList(res.data.content);
        console.log(res.data);
      }, (err) => {
        console.log(err);
      })

      //사이즈 목록으로 작품 가져오기
      setSize(ProductSizeList[Math.floor(Math.random() * 4)]);
      setloading(false);
    }
    // eslint-disable-next-line
  }, [loading]);

  useEffect(() => {
    console.log(Size);
    console.log(Size.size);
    if (Size !== null) {
      getProductListBySizeApi(Size.size, "0", "20", "", (res) => {
        console.log(res);
        if (res.data.content.length <= 3) {
          setSize(ProductSizeList[Math.floor(Math.random() * 4)]);
        }
        setSizeProductList(res.data.content);
      }, (err) => {
        console.log(err);
      })
    }
  }, [Size])
  return (
    <div>
      <CategoryBar />
      <BannerTable />
      <div
        style={{
          borderBottom: "1px solid #ebebeb",
          paddingBottom: "20px",
          paddingTop: "20px",
        }}
      >
        <StyledTableTitle>On Air</StyledTableTitle>
        <StyledTableSubtitle>지금 진행중인 개인경매</StyledTableSubtitle>
        {/* 전달 되어야 할 것은? -> 상품객체 배열 */}
        <HorizonScrollRowTable list={OnairList} />
      </div>
      <div
        style={{
          borderBottom: "1px solid #ebebeb",
          paddingBottom: "20px",
          paddingTop: "20px",
        }}
      >
        <StyledTableTitle>Today’s Artist</StyledTableTitle>
        <StyledTableSubtitle>오늘의 인기작가</StyledTableSubtitle>
        <HorizonScrollColTable list={ArtistList} />
      </div>
      <div
        style={{
          borderBottom: "1px solid #ebebeb",
          paddingBottom: "20px",
          paddingTop: "20px",
        }}
      >
        <StyledTableTitle>Art For You</StyledTableTitle>
        <StyledTableSubtitle>이런 작품은 어때요?</StyledTableSubtitle>
        <HorizonScrollRowTable list={ArtForYouList} />
      </div>
      <div
        style={{
          borderBottom: "1px solid #ebebeb",
          paddingBottom: "20px",
          paddingTop: "20px",
        }}
      >
        <StyledTableTitle>New In</StyledTableTitle>
        <StyledTableSubtitle>신규 등록 작품</StyledTableSubtitle>
        <HorizonScrollRowTable list={NewProductList} />
      </div>
      <div style={{ paddingBottom: "20px", paddingTop: "20px" }}>
        <StyledTableTitle>{Size.sizeName} Size</StyledTableTitle>
        <StyledTableSubtitle>{Size.description}</StyledTableSubtitle>
        <HorizonScrollRowTable list={SizeProductList} />
      </div>
    </div>
  );
}
