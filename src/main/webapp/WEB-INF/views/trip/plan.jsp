<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/head.jsp"%>
<link rel="stylesheet" href="${root}/css/plan.css" />
</head>
<body>
	<%@ include file="/include/nav.jsp"%>
	<section>
		<article id="top">
			<h2>나만의 여행계획을</h2>
			<h2>세워보세요.</h2>
			<div id="subment">
				<h2>여행 경로, 숙박, 관광지, 예상금액 등</h2>
				<h2>나만의 멋진 계획을 세워 공유해보세요.</h2>
			</div>
			<div id="btnBox">
				<a href="#plan">
					<button id="utBtn">계획 세우기</button>
				</a>
			</div>
			<a href="#plan"> <i class="fa-solid fa-chevron-down fa-3x"
				style="color: #000" id="plan-arrow-down"></i></a>
		</article>

		<article id="plan-container">
			<a href="#plan" id="plan"></a>
			<div id="plan-text">
				<h2>나만의 여행 계획 세우기</h2>
				<h3>특별한 여행 계획을 세워 공유해보세요.</h3>
			</div>
			<div id="plan-box">
				<div class="plan-box-left">
					<input type="text" placeholder="여행 제목을 입력해주세요." id="plan-title">
					<label for="start_date">여행 시작 날짜:</label>
					 <input type="date" id="start_date" name="start_date" required pattern="\d{4}-\d{2}-\d{2}">
					 <label for="end_date">여행 종료 날짜:</label> 
						<input type="date" id="end_date" name="end_date" required pattern="\d{4}-\d{2}-\d{2}">

					<!-- 여행 플래너 장소 목록 -->
					<div class="plan-list-box"></div>
					<div id="btnDiv">
						<button id="share-plan-btn">공유하기</button>
					</div>
				</div>
				<div class="plan-box-right">
					<!-- 검색 영역 -->
					<div id="searchBox" class="box">
						<input id="searchInput" type="text" placeholder="여행지를 검색해보세요." />
						<i class="fa-solid fa-magnifying-glass fa-2x"
							style="color: #8b95a1;" id="searchImg"></i>
					</div>
					<!-- 지도 -->
					<div id="mapBox">
						<div id="map"></div>
					</div>
				</div>
			</div>
		</article>
	</section>
	<script
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f969680d19602de1fea5ade9c6d7e784"></script>
	<script type="text/javascript">
	let dataList = []; // 검색한 지역 담을 배열
	document.querySelector("#searchImg").addEventListener("click", () => {
	  let keyword = document.querySelector("#searchInput").value;
	  let url = "${root}/trip?action=searchTourinfoByKeyword&keyword="+keyword;
	  fetch(url)
	    .then((response) => response.json())
	    .then((data) => {
	      data.forEach((item) => dataList.push(item));
	      showMarks();
	    });
	});
	
	// ============ 지도 생성 =============
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

	// 다중 마커 찍기
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
	  dataList.forEach((e) => {
	    let obj = {
	      title: e.title,
	      addr1: e.addr1,
	      image: e.firstImage,
	      latlng: new kakao.maps.LatLng(e.latitude, e.longitude),
	    };
	    positions.push(obj);
	  });

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

	    // 플래너에 장소 추가버튼
	    let link = document.createElement("button");
	    link.className = "link";
	    link.innerText = "추가하기";
	    link.addEventListener("click", () => {
	      let planListBox = document.querySelector(".plan-list-box");
	      let item = document.createElement("div");
	      item.className = `plan-list-item`;
	      let itemId = pos.title.split(" ").join("");
	      itemId = itemId.split("[").join("");
	      itemId = itemId.split("]").join("");
	      item.id = itemId;
	      // 관광지명
	      let title = document.createElement("div");
	      title.className = "title";
	      title.innerText = pos.title;
	      // 삭제 버튼
	      let deleteBtn = document.createElement("div");
	      deleteBtn.className = `icon `;
	      let icon = document.createElement("i");
	      icon.className = "fa-solid fa-xmark";
	      icon.style.color = "#8b95a1";
	      let queryName = "#" + itemId;
	      deleteBtn.addEventListener("click", () => {
	        // 플래너에서 장소 삭제
	        let btn = document.querySelector(queryName);
	        btn.remove();
	      });
	      deleteBtn.appendChild(icon);

	      item.appendChild(title);
	      item.appendChild(deleteBtn);
	      planListBox.appendChild(item);
	    });
	    bottom.appendChild(link);

	    desc.appendChild(ellipsis);
	    desc.appendChild(jibun);
	    desc.appendChild(bottom);

	    body.appendChild(imgDiv);
	    body.appendChild(desc);

	    content.appendChild(info);

	    overlay.setContent(content);

	    // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
	    kakao.maps.event.addListener(marker, "click", function () {
	      overlay.setMap(map);
	    });
	  });

	  // 지도를 재설정할 범위정보를 가지고 있을 LatLngBounds 객체를 생성합니다
	  var bounds = new kakao.maps.LatLngBounds();

	  for (i = 0; i < positions.length; i++) {
	    // LatLngBounds 객체에 좌표를 추가합니다
	    bounds.extend(positions[i].latlng);
	  }
	  setBounds();
	  function setBounds() {
	    map.setBounds(bounds);
	  }

	  // 플래너 공유하기
	  document.querySelector("#share-plan-btn").addEventListener("click", () => {
	    alert("플래너가 공유되었습니다!");
	    // window.location.href = "plan.html";
	  });
	}

	</script>
	<%@ include file="/include/footer.jsp"%>