<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/head.jsp" %>
	<link rel="stylesheet" href="${root}/css/main.css" />
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	    <section>
      <!-- 배너 -->
      <article id="banner">
        <div id="banner-container">
          <!-- 페이지설명 -->
          <div id="banner-left">
            <h2>우리 지역의 관광지를 알아보고</h2>
            <h2>나만의 여행 계획을 만들어보세요.</h2>
            <div id="banner-plan-btn">여행 계획하러가기</div>
          </div>
        </div>
        <a href="#main-desc">
          <i
            class="fa-regular fa-circle-down fa-3x"
            style="color: #ffffff"
            id="banner-arrow-down"
          ></i
        ></a>
      </article>
      <!-- 설명 article -->
      <article id="main-desc">
        <a id="main-desc"></a>
        <div>
          <h2>지금 <b>Enjoy Trip</b>과 함께</h2>
          <h2>최고의 휴일을 같이 보내볼까요?</h2>
          <h2>나만의 여행 계획을 짜보고 공유해 보세요.</h2>
        </div>
      </article>
      <!-- 우리지역 관광지 -->
      <article id="main-tourinfo">
        <div id="main-tourinfo-container">
          <h2 class="main-article-title">우리지역 관광지</h2>
          <div class="main-article-desc">
            <h2>우리지역의 숨어있는</h2>
            <h2>아름다운 관광지를 알려드립니다.</h2>
          </div>
          <div id="main-tourinfo-list">
            <div id="main-tourinfo-card">
              <div id="tourinfo-imgDiv">
                <img src="./img/myregion/m-region-7.jpeg" alt="" />
              </div>
              <div id="tourinfo-text">
                <h2>패밀리랜드</h2>
                <p>
                  광주패밀리랜드는 광주광역시 북구 생용동에 있는 도시
                  근린공원으로서 36만 평의 부지 위에 놀이시설 30종, 사계절
                  썰매장과 아이스링크, 수영장을 갖춘 호남 최대의
                  종합위락공원으로 1991년 7월 6일 개원하였다.
                </p>
              </div>
            </div>
            <div id="main-tourinfo-card">
              <div id="tourinfo-imgDiv">
                <img src="./img/myregion/m-region-6.png" alt="" />
              </div>
              <div id="tourinfo-text">
                <h2>동명동 카페거리</h2>
                <p>
                  동명동 카페거리는 광주 내 핫플레이스로 떠오르고 있는 거리이다.
                  카페들이 점차 개성을 가지고 독특한 외관과 내부 인테리어,
                  음료의 맛을 추구하면서 다양한 스타일의 카페가 들어섰고 지금의
                  동명동 카페거리가 형성되었다.
                </p>
              </div>
            </div>
            <div id="main-tourinfo-card">
              <div id="tourinfo-imgDiv">
                <img src="./img/myregion/m-region-5.jpeg" alt="" />
              </div>
              <div id="tourinfo-text">
                <h2>광주 대인시장 (대인예술시장)</h2>
                <p>
                  광주의 전통시장의 변화는 바로 매주 토요일 저녁 발 디딜 틈 없이
                  붐비는 대인시장의 성공적인 변화에서부터 시작 되었다. 충장로와
                  금남로가 번화가로서 중심을 이루고 있는 광주의 상업구도에서
                  대인시장은 유일하게 시내에 자리 잡고 있어 그 인기와 효용가치는
                  매우 컸다.
                </p>
              </div>
            </div>
          </div>
        </div>
      </article>
      <!-- 나만의 여행계획 -->
      <article id="main-myplan">
        <div id="main-myplan-container">
          <h2 class="main-article-title">추천 여행계획</h2>
          <div class="main-article-desc" id="main-myplan-desc">
            <h2>어디로 가볼까요?</h2>
          </div>
          <div
            id="demo main-myplan-carousel"
            class="carousel carousel-dark slide"
            data-bs-ride="carousel"
          >
            <!-- Indicators/dots -->
            <div class="carousel-indicators m-0">
              <button
                type="button"
                data-bs-target="#demo"
                data-bs-slide-to="0"
                class="active"
              ></button>
              <button
                type="button"
                data-bs-target="#demo"
                data-bs-slide-to="1"
              ></button>
              <button
                type="button"
                data-bs-target="#demo"
                data-bs-slide-to="2"
              ></button>
            </div>
            <!-- The slideshow/carousel -->
            <div class="carousel-inner">
              <div class="carousel-item mb-5 active">
                <div class="row g-5">
                  <div class="col-sm-12 col-md-6 col-lg-3">
                    <div class="card">
                      <img
                        src="./img/myregion/m-region-1.jpg"
                        class="card-img-top"
                        alt=""
                      />
                      <div class="card-body">
                        <h5 class="card-title">신혼여행코스</h5>
                        <div class="ellipsis card-text">
                          위 경로처럼 가려는데 어떨까요? 조언부탁드려요!
                        </div>
                        <a href="#" class="btn btn-sm mt-3 btn-primary"
                          >더보기...</a
                        >
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-12 col-md-6 col-lg-3">
                    <div class="card">
                      <img
                        src="./img/myregion/m-region-2.jpg"
                        class="card-img-top"
                        alt=""
                      />
                      <div class="card-body">
                        <h5 class="card-title">심심하신분 모이세요</h5>
                        <div class="ellipsis card-text">
                          7일코스 전국을 누비며 우리나라에 숨어있는 아름다운
                          명소 돌아 다니실 분!!! 인원 모집하고 금액이나 경로
                          정하려 합니다. 지원해주세요.
                        </div>
                        <a href="#" class="btn btn-sm mt-3 btn-primary"
                          >더보기...</a
                        >
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-12 col-md-6 col-lg-3">
                    <div class="card">
                      <img
                        src="./img/myregion/m-region-3.jpg"
                        class="card-img-top"
                        alt=""
                      />
                      <div class="card-body">
                        <h5 class="card-title">군대투어</h5>
                        <div class="ellipsis card-text">
                          본인이 다녀온 군부대를 중심으로 돌아다니는 군대투어~~
                          다녀오지 않으신분들은 구경가봐요!!
                        </div>
                        <a href="#" class="btn btn-sm mt-3 btn-primary"
                          >더보기...</a
                        >
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-12 col-md-6 col-lg-3">
                    <div class="card">
                      <img
                        src="./img/myregion/m-region-4.jpg"
                        class="card-img-top"
                        alt=""
                      />
                      <div class="card-body">
                        <h5 class="card-title">심심하면 떠나자</h5>
                        <div class="ellipsis card-text">
                          북한을 제외한 8도를 돌아다니는 여행으로 건강도 챙기고
                          맨탈도 챙기는 트레킹 코스입니다. 함께하실 분은
                          연락주세요!
                        </div>
                        <a href="#" class="btn btn-sm mt-3 btn-primary"
                          >더보기...</a
                        >
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="carousel-item mb-5">
                <div class="row g-5">
                  <div class="col-sm-12 col-md-6 col-lg-3">
                    <div class="card">
                      <img
                        src="./img/myregion/m-region-5.jpeg"
                        class="card-img-top"
                        alt=""
                      />
                      <div class="card-body">
                        <h5 class="card-title">혼자 여행하기 좋은 곳 추천</h5>
                        <div class="ellipsis card-text">
                          혼자 여행하기 좋은 코스 추천합니다.
                        </div>
                        <a href="#" class="btn btn-sm mt-3 btn-primary"
                          >더보기...</a
                        >
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-12 col-md-6 col-lg-3">
                    <div class="card">
                      <img
                        src="./img/myregion/m-region-6.png"
                        class="card-img-top"
                        alt=""
                      />
                      <div class="card-body">
                        <h5 class="card-title">서해 여행</h5>
                        <div class="ellipsis card-text">
                          우리나라 서해는 잔잔한 물결에 노을을 보면서 힐링하기
                          좋은데요. 오늘은 그 명소들을 추천하겠습니다.
                        </div>
                        <a href="#" class="btn btn-sm mt-3 btn-primary"
                          >더보기...</a
                        >
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-12 col-md-6 col-lg-3">
                    <div class="card">
                      <img
                        src="./img/myregion/m-region-7.jpeg"
                        class="card-img-top"
                        alt=""
                      />
                      <div class="card-body">
                        <h5 class="card-title">광주 싸피 여행</h5>
                        <div class="ellipsis card-text">
                          싸피 광주캠은 보안이 아주 심해서 여행으로 부적격이지만
                          입과만 하신다면 광주의 맛있는 밥을 먹을 수 있습니다.
                        </div>
                        <a href="#" class="btn btn-sm mt-3 btn-primary"
                          >더보기...</a
                        >
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-12 col-md-6 col-lg-3">
                    <div class="card">
                      <img
                        src="./img/myregion/m-region-1.jpg"
                        class="card-img-top"
                        alt=""
                      />
                      <div class="card-body">
                        <h5 class="card-title">Card title</h5>
                        <div class="ellipsis card-text">
                          Some quick example text to build on the card title and
                          make up the bulk of the card's content.
                        </div>
                        <a href="#" class="btn btn-sm mt-3 btn-primary"
                          >더보기...</a
                        >
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="carousel-item mb-5">
                <div class="row g-5">
                  <div class="col-sm-12 col-md-6 col-lg-3">
                    <div class="card">
                      <img
                        src="./img/myregion/m-region-2.jpg"
                        class="card-img-top"
                        alt=""
                      />
                      <div class="card-body">
                        <h5 class="card-title">전북 익산 여행</h5>
                        <div class="ellipsis card-text">
                          보석의 도시 익산여행 코스 공유합니다. 익산은 보석이
                          유명하다는데요 닉값을 하는 만큼 사리가 나왔다고
                          합니다.
                        </div>
                        <a href="#" class="btn btn-sm mt-3 btn-primary"
                          >더보기...</a
                        >
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-12 col-md-6 col-lg-3">
                    <div class="card">
                      <img
                        src="./img/myregion/m-region-3.jpg"
                        class="card-img-top"
                        alt=""
                      />
                      <div class="card-body">
                        <h5 class="card-title">Card title</h5>
                        <div class="ellipsis card-text">
                          Some quick example text to build on the card title and
                          make up the bulk of the card's content.
                        </div>
                        <a href="#" class="btn btn-sm mt-3 btn-primary"
                          >더보기...</a
                        >
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-12 col-md-6 col-lg-3">
                    <div class="card">
                      <img
                        src="./img/myregion/m-region-4.jpg"
                        class="card-img-top"
                        alt=""
                      />
                      <div class="card-body">
                        <h5 class="card-title">Card title</h5>
                        <div class="ellipsis card-text">
                          Some quick example text to build on the card title and
                          make up the bulk of the card's content.
                        </div>
                        <a href="#" class="btn btn-sm mt-3 btn-primary"
                          >더보기...</a
                        >
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-12 col-md-6 col-lg-3">
                    <div class="card">
                      <img
                        src="./img/myregion/m-region-5.jpeg"
                        class="card-img-top"
                        alt=""
                      />
                      <div class="card-body">
                        <h5 class="card-title">Card title</h5>
                        <div class="ellipsis card-text">
                          Some quick example text to build on the card title and
                          make up the bulk of the card's content.
                        </div>
                        <a href="#" class="btn btn-sm mt-3 btn-primary"
                          >더보기...</a
                        >
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </article>
    </section>
<%@ include file="/include/footer.jsp" %>