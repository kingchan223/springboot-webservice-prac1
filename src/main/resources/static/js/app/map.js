function ppp(){
    var data = $("#searchData").val();

    $.ajax({
        method: "GET",
        url: "https://dapi.kakao.com/v2/local/search/address.json",
        data: { query: data },
        headers:{ Authorization: "KakaoAK c1f15749ccd53f61d5d0b23041d71f29"}
    })
        .done(function (msg) {
            console.log(msg);
            $( "p" ).append( +msg.documents[0].address.x+"<br>"+msg.documents[0].address.y );
            let loca_x = msg.documents[0].address.x;
            let loca_y = msg.documents[0].address.y;
            let numLoca_x = Number(loca_x);
            let numLoca_y = Number(loca_y);
            console.log(numLoca_x, numLoca_y);
            var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
            var options = { //지도를 생성할 때 필요한 기본 옵션
                center: new kakao.maps.LatLng(numLoca_y, numLoca_x), //지도의 중심좌표.
                level: 3 //지도의 레벨(확대, 축소 정도)
            };
            var map = new kakao.maps.Map(container, options);
            //console.log(options);
        });
}

//console.log("나와라",loca_x, loca_y);

