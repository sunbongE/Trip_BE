<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/head.jsp" %>
	<link rel="stylesheet" href="${root}/css/tourinfo.css" />
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
	
	<section id="top-section">
      <!-- ment -->
      <div class="ment box">
        <h2>여행 가고 싶은 지역을</h2>
        <h2>검색해보세요.</h2>
      </div>

      <form id="search-form" method="get">
        <select name="search-area" id="search-area" class="selectBox">
          <option value="">지역</option>
          <c:forEach var="item" items="${sidoList}">
          	<option value="${item.sidoCode}">${item.sidoName}</option>
          </c:forEach>
        </select>
        <select name="search-city" id="search-city" class="selectBox">
          <option value="">시군구</option>
        </select>
        <select
          name="search-content-id"
          id="search-content-id"
          class="selectBox"
        >
          <option value="">관광지 유형</option>
          <option value="12">관광지</option>
          <option value="14">문화시설</option>
          <option value="15">축제공연행사</option>
          <option value="25">여행코스</option>
          <option value="28">레포츠</option>
          <option value="32">숙박</option>
          <option value="38">쇼핑</option>
          <option value="39">음식점</option>
        </select>
        <input type="button" value="검색" />
      </form>

      <!-- kakao map -->
      <div id="mapBox">
        <div id="map"></div>
      </div>
    </section>
    <section id="youtubeBox">        
        <p>바로 유튜브에서 찾아볼까요?</p>
        <p>선택한 관광지의 영상을 확인할 수 있어요.</p>
        <div id="btnBox">
          <button id="utBtn">검색하기</button>
        </div>
        <div id="flexBox"></div>
      </div>
     </section>
      
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f969680d19602de1fea5ade9c6d7e784"></script>
<script>
	// ===========지역변하면 시군구 받아오기======================
	document.querySelector("#search-area").addEventListener("change", function(e) {
	  let subOptionsLen = document.querySelector("#search-city").options.length;
	  let target = e.target.value;
	  let url = "${root}/trip?action=gugunList&sidoCode="+target;
	  fetch(url)
	  	.then(function(res) {return res.json()})
	  	.then(function(data) {return makeSubOption(data)});
	  
	});

	// =========== 시군구 채우기 ======================
	function makeSubOption(data) {
		let sel = document.getElementById("search-city");
		sel.innerHTML = '<option value="-1">시군구</option>';
		data.forEach(function(city) {
		   let opt = document.createElement("option");
		    opt.setAttribute("value", city.gugunCode);
		    opt.setAttribute("class", "opcity");
		    opt.appendChild(document.createTextNode(city.gugunName));
		    sel.appendChild(opt);
		});
	}
	
	let dataList = [];
	document.querySelector("#search-form input").addEventListener("click", function() {
	  // 데이터를 저장할 배열 초기화
	  dataList = [];
	  // 지역값
	  let sido = document.querySelector("#search-area").value;
	  // 시군구값
	  let gugun = document.querySelector("#search-city").value;
	  // 관광유형
	  let content = document.querySelector("#search-content-id").value;
	  
	  let url = "${root}/trip?action=searchTourinfo&sido="+sido+"&gugun="+gugun+"&content="+content;
	  fetch(url)
	  	.then(function(res) {return res.json()})
	  	.then((data)=> {
	  		data.forEach((item) => dataList.push(item));
	  		console.log(dataList);
	  		showMarks();
	  	})
	});
	
	function showMarks() {
		  // 기존에 표시된 맵을 지우도 다시 만든다.
		  document.querySelector("#map").innerHTML = "";
		  var map = new kakao.maps.Map(mapContainer, mapOption);
		  // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		  var zoomControl = new kakao.maps.ZoomControl();
		  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

		  // 지도가 확대 또는 축소되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
		  kakao.maps.event.addListener(map, "zoom_changed", function () {
		    // 지도의 현재 레벨을 얻어옵니다
		    var level = map.getLevel();
		  });
		  var positions = [];
		  dataList.forEach(function(e) {
		    let obj = {
		      title: e.title,
		      image: e.firstImage,
		      addr1: e.addr1,
		      latlng: new kakao.maps.LatLng(e.latitude, e.longitude),
		    };
		    positions.push(obj);
		  });

		  // 마커 이미지의 이미지 주소입니다
		  var imageSrc =
		    "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

		  positions.forEach((pos) => {
		    var marker = new kakao.maps.Marker({
		      map: map, // 마커를 표시할 지도
		      position: pos.latlng, // 마커의 위치
		    });

		    var overlay = new kakao.maps.CustomOverlay({
		      map: map,
		      yAnchor: 3,
		      position: marker.getPosition(),
		    });

		    let content = document.createElement("div");
		    content.className = "wrap";

		    let info = document.createElement("div");
		    info.className = "info";

		    let title = document.createElement("div");
		    title.className = "title";
		    title.innerText = pos.title;
		    let close = document.createElement("div");
		    close.className = "close";
		    close.addEventListener("click", () => overlay.setMap(null));

		    info.appendChild(title);
		    title.appendChild(close);

		    let body = document.createElement("div");
		    body.className = "body";
		    info.appendChild(body);

		    let imgDiv = document.createElement("div");
		    imgDiv.className = "img";
		    let img = document.createElement("img");
		    img.src =
		      pos.image ||
		      "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/thumnail.png";
		    img.width = 73;
		    img.height = 70;
		    imgDiv.appendChild(img);

		    let desc = document.createElement("div");
		    desc.className = "desc";

		    let ellipsis = document.createElement("div");
		    ellipsis.className = "ellipsis";
		    ellipsis.innerText = pos.addr1;
		    let jibun = document.createElement("div");
		    jibun.className = "jibun ellipsis";
		    jibun.innerText = pos.addr1;
		    let bottom = document.createElement("div");
		    let link = document.createElement("a");
		    link.target = "_blank";
		    link.className = "link";
		    link.innerText = "바로가기";
		    bottom.appendChild(link);

		    desc.appendChild(ellipsis);
		    desc.appendChild(jibun);
		    desc.appendChild(bottom);

		    body.appendChild(imgDiv);
		    body.appendChild(desc);

		    content.appendChild(info);

		    overlay.setContent(content);

		    // $(document).on("click", "#close", function () {
		    //  overlay.setMap(null);
		    //});

		    // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
		    kakao.maps.event.addListener(marker, "click", function () {
		      overlay.setMap(map);
		    });
		  });

		  // 지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
		  var bounds = new kakao.maps.LatLngBounds();

		  for (i = 0; i < positions.length; i++) {
		    // 배열의 좌표들이 잘 보이게 마커를 지도에 추가합니다

		    // LatLngBounds 객체에 좌표를 추가합니다
		    bounds.extend(positions[i].latlng);
		  }
		  setBounds();
		  function setBounds() {
		    // LatLngBounds 객체에 추가된 좌표들을 기준으로 지도의 범위를 재설정합니다
		    // 이때 지도의 중심좌표와 레벨이 변경될 수 있습니다
		    map.setBounds(bounds);
		  }
		}

		//=============kakaomap==================
		// KAKAO MAP API
		var mapContainer = document.getElementById("map"), // 지도를 표시할 div
		  mapOption = {
		    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		    level: 3, // 지도의 확대 레벨
		  };

		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		var zoomControl = new kakao.maps.ZoomControl();
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

		// 지도가 확대 또는 축소되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
		kakao.maps.event.addListener(map, "zoom_changed", function () {
		  // 지도의 현재 레벨을 얻어옵니다
		  var level = map.getLevel();
		});

</script>
<%@ include file="/include/footer.jsp" %>