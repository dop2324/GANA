/*
 * 二쇱냼 蹂���
 */

var lat, lng;
function getGeolocation()
{
	if(navigator.geolocation)
	{
		try
		{
			navigator.geolocation.getCurrentPosition(initPosition);
		}
		catch(e)
		{
			alert(e.message);
		}
	}
	else
	{
		//writeKakaoMap(35.89247729058632, 128.60075974239763);
		//coord2addr();
	}
}

function initPosition(position)
{
	if(position != null)
	{
		lat = position.coords.latitude;
		lng = position.coords.longitude;
	}
}



var map, marker;//, markerPosition;

function writeKakaoMap(latitude, longitude)
{
	map =  new kakao.maps.Map(document.getElementById("map"), {
			center: new kakao.maps.LatLng(latitude, longitude)
			, level: 2
		}); // 吏��꾨� �앹꽦�⑸땲��

	writeMarker(latitude, longitude);

	kakao.maps.event.addListener(map, "click", function(MouseEvent)
	{
		var latLng = MouseEvent.latLng;
		latitude = latLng.getLat();
		longitude = latLng.getLng();

		if(marker != null){
			marker.setVisible(false);
		}

		writeMarker(latitude, longitude);
		searchDetailAddrFromCoords(MouseEvent.latLng, coord2Address);
	});
}

function fixedKakaoMap(latitude, longitude)
{
	map =  new kakao.maps.Map(document.getElementById("map"), {
			center: new kakao.maps.LatLng(latitude, longitude)
			, level: 2
		}); // 吏��꾨� �앹꽦�⑸땲��

	writeMarker(latitude, longitude);
}

var marker;
function writeMarker(latitude, longitude)
{
	var markerPosition  = new kakao.maps.LatLng(latitude, longitude);
	marker = new kakao.maps.Marker({
		position: markerPosition
	});

	marker.setMap(map);
	map.setCenter(markerPosition);
	//if($("#map_address").val() != null)
	//{
		$(".map_latitude").val(latitude);
		$(".map_longitude").val(longitude);
	//}
}


function findLocation(latitude, longitude)
{

	if(marker != null)
	{
		marker.setVisible(false);
	}

	writeMarker(latitude, longitude);
}


function searchDetailAddrFromCoords(coords, callback) {
	var geocoder = new kakao.maps.services.Geocoder();
	geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

function coord2Address(result, status)
{
	if (status === kakao.maps.services.Status.OK)
    {
    	//�꾨줈紐낆＜��
    	var roadAddr = !!result[0].road_address ? result[0].road_address.address_name : '';
    	//吏�踰덉＜��
    	var jibunAddr = result[0].address.address_name;
        $(".map_address").val(roadAddr != '' ? roadAddr:jibunAddr);
    }
}



function getAddr2Cord(address, latitude, longitude)
{
	address = $("#"+address).val();
	var geocoder = new kakao.maps.services.Geocoder();

	geocoder.addressSearch(address, function(result, status) {

	    // �뺤긽�곸쑝濡� 寃��됱씠 �꾨즺�먯쑝硫�
	     if (status === kakao.maps.services.Status.OK) {

	        //var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	        lat = result[0].y;
			lng = result[0].x;

	        $("#"+latitude).val(lat);
			$("#"+longitude).val(lng);


	    }
	});

	//addr2coord(latitude, longitude, address);
}

//二쇱냼 -> 醫뚰몴
function addr2coord(latitude, longitude, address)
{
	var kakaoKey = $("#kakaoKey").val();
	$.ajax({
		type:"get"
			, url:"http://apis.daum.net/maps/addr2coord?apikey="+kakaoKey+"&output=json&callback=?&q="+encodeURI(address)
			, dataType:"jsonp"
			, success:function(data)
			{
				if(data.channel.totalCount > 0)
				{
					lat = data.channel.item[0].lat;
					lng = data.channel.item[0].lng;

					$("#"+latitude).val(lat);
					$("#"+longitude).val(lng);
				}
				else
				{
					alert("二쇱냼 -> 醫뚰몴 寃곌낵媛� �놁뒿�덈떎.");
				}

				//writeDaumMap(latitude, longitude);
			}
			, error:function(jqXHR, textStatus, errorThrown)
			{
				console.log(textStatus+"\n"+errorThrown);
			}
	});
}


function latLngToCoords(latitude, longitude)
{
	var regExp = /[\)/ /(\"]/gi;
	var latlng   = new kakao.maps.LatLng(latitude, longitude);
	var coords = latlng.toCoords().toString();

	coords = coords.replace(regExp, "");
	console.log("coords : "+coords);

	return coords;
}

function checkMobile()
{
	var flag = true;
	var filter = "win16|win32|win64|mac";
    if (navigator.platform && filter.indexOf(navigator.platform.toLowerCase()) >= 0) {
    	flag =  false;
    }



    return flag;
}

var currLat, currLng;
function findNaviMap(latitude, longitude, eName)
{
	var findNavi;
	if(checkMobile() == true)
	{
		findNavi = $("#naviLink").val();
	}
	else
	{
		var start = $("#startPlace").val();
		var sName = $("#startPlace option:checked").text();

		var splitStart = start.split(",");
		var sCoords = latLngToCoords(splitStart[0], splitStart[1]);

		var arrayScoords = sCoords.split(",");
		//console.log(splitStart[0]+":"+splitStart[1]);
		//console.log(arrayScoords[0]+":"+arrayScoords[1]);

		var eCoords = latLngToCoords(latitude, longitude);
		var arrayEcoords = eCoords.split(",");

		//console.log(arrayEcoords[0]+":"+arrayEcoords[1]);

		findNavi = "https://map.kakao.com/?sName="+encodeURI(sName)+"&eName="+encodeURI(eName)+""
		 			+ "&eX="+parseInt(arrayEcoords[0])+"&eY="+parseInt(arrayEcoords[1])+""
		 			+ "&sX="+parseInt(arrayScoords[0])+"&sY="+parseInt(arrayScoords[1]);
		 //console.log("findNavi : "+findNavi);
	}


	 window.open(findNavi, "naviMap");
}