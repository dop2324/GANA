window.Clipboard = (function(window, document, navigator) {
    var textArea,
        copy;

    function isOS() {
        return navigator.userAgent.match(/ipad|iphone/i);
    }

    function createTextArea(text) {
        textArea = document.createElement('textArea');
        textArea.value = text;
        document.body.appendChild(textArea);
    }

    function selectText() {
        var range,
            selection;

        if (isOS()) {
            range = document.createRange();
            range.selectNodeContents(textArea);
            selection = window.getSelection();
            selection.removeAllRanges();
            selection.addRange(range);
            textArea.setSelectionRange(0, 999999);
        } else {
            textArea.select();
        }
    }

    function copyToClipboard() {
        document.execCommand('copy');
        document.body.removeChild(textArea);
    }

    copy = function(text) {
        createTextArea(text);
        selectText();
        copyToClipboard();
    };

    return {
        copy: copy
    };
})(window, document, navigator);

function copyToClipboard(element)
{
    var copyText = $(element).text();

    Clipboard.copy(copyText);
    alert("Copied the text: " + copyText);
}



function numberCommas(x) {
	var parts=x.toString().split(".");
    return parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + (parts[1] ? "." + parts[1] : "");
}

/* **********************************************************************************
 * ID 유효성 체크
 * **********************************************************************************/
var regPw = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$/;

function validateID(src)
{
	var partten = /^[a-z0-9]{4,64}$/;
	var arrayMatch = src.match(partten);
	if(arrayMatch == null) {return false;}
	if(src == "admin" || src == "guest" || src == "root") {return false;}
	return true;
}



/* **********************************************************************************
 * 영문숫자만
 * **********************************************************************************/
function validateEng(src)
{
	var partten = /^[A-Za-z0-9_]+$/;

	var arrayMatch = src.match(partten);

	if(arrayMatch == null) {return false;}

	return true;
}

function onlyNumber(obj) {
    $(obj).keyup(function(){
         $(this).val($(this).val().replace(/[^0-9]/g,""));
    });
}

/* **********************************************************************************
 * 메일주소 유효성 체크
 * **********************************************************************************/
function validateMail(src)
{
	var regEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

	if(!regEmail.test(src))
	{
		return false;
	}
	else
	{
		return true;
	}
}

/* **********************************************************************************
 * IP 형식 체크
 * **********************************************************************************/
function validateIP(src)
{
	var partten = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
	var arrayMatch = src.match(partten);

	if(arrayMatch == null) return false;

	for(i = 0; i < arrayMatch.length; i++)
	{
		if(arrayMatch[i] > 255) return false;
	}

	return true;
}

/*
function openViewFiles(file_uid)
{
	var url = '/synap/board_fileViewer.do?file_uid='+file_uid;
	window.open(url,'_blank');
	return false;
}
*/

/*
  * 새주소 검색
  */
function goJusoPopup()
{
 	// 주소검색을 수행할 팝업 페이지를 호출합니다.
 	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
 	var pop = window.open("/common/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes");
}

function openPopup(url)
{
 	var pop = window.open(url,"openPop","width=630,height=480, scrollbars=yes, resizable=yes");
}


var url = location.href;

function kakaoLink() {
  Kakao.Story.share({
    url: encodeURI(url),
    text: $("#title").text()
  });
}
function twitter()
{
	var href = "http://twitter.com/home?status=" + encodeURIComponent($("#title").text()) + " " + encodeURIComponent(url);
	snsPopup(href);
}
function facebook()
{
	var title	= $("#title").text();
    var href = "https://www.facebook.com/sharer/sharer.php?u="+encodeURIComponent(url);
    snsPopup(href);
}
function snsPopup(value)
{
	var win = window.open(value, '_sns', 'width=600,height=500');
	if(win)
	{
		win.focus();
	}
}

function band()
{
	 var shareUrl = "http://www.band.us/plugin/share?body="+encodeURIComponent($("#title").text())+"\n"+encodeURIComponent(url);
	 window.open(shareUrl, "share_band", "width=410, height=540, resizable=no");
}

function printPage() {
	window.open("/common/inc_print.jsp", "printPopup", "width=750,height=600,top=100,left=100,scrollbars=yes,resizable=no");
}

function getPageCssHrefs()
{
	var objHead = document.getElementsByTagName("head")[0];
	var cssHrefs = new Array();

	for(var i = 0; i < objHead.childNodes.length; i++)
	{
		if(objHead.childNodes[i].tagName == "LINK")
		{
			if(objHead.childNodes[i].href.length != 0)
			{
				cssHrefs.push(objHead.childNodes[i].href);
			}
		}
	}

	return cssHrefs;
}

function getPageDocHtml()
{
	return document.getElementById("bodyArea").innerHTML;
}

function printDiv(divName)
{
    var printContents = document.getElementById(divName).innerHTML;
    var originalContents = document.body.innerHTML;

    window.onbeforeprint = function(){
    	document.body.innerHTML = printContents;
    }
    window.onafterprint = function(){
    	document.body.innerHTML = originalContents;
    }

    window.print();
}

/* **********************************************************************************
 * Zoom Font
 * **********************************************************************************/
var defaultFontSize = 16;

function zoomFont(id, val)
{
	if(val == 0)
	{
		defaultFontSize = 16;
		$("#"+id).css({"font-size":defaultFontSize+"px"});
	}
	else
	{
		defaultFontSize += val;

		if(defaultFontSize < 14)
		{
			alert("더이상 줄일 수 없습니다");
			return false;
		}
		else if(defaultFontSize > 62)
		{
			alert("더이상 늘릴 수 없습니다");
			return false;
		}

		$("#"+id+" *").css({"font-size":defaultFontSize+"px"});
		$("#"+id+" *").css({"line-height":(defaultFontSize+6)+"px"});
	}
}


function setCookie(name,value,days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

/***********************************************************************************
* 좌표간 거리계산
* **********************************************************************************/
function calcDistance(lat1, lon1, lat2, lon2)
{
	 var theta = lon1 - lon2;
	 dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))
         * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	 dist = Math.acos(dist);
	 dist = rad2deg(dist);
	 dist = dist * 60 * 1.1515;
	 dist = dist * 1.609344;
	 return Number(dist).toFixed(2);
}

 function deg2rad(deg) {
	 return (deg * Math.PI / 180);
 }
 function rad2deg(rad) {
	 return (rad * 180 / Math.PI);
 }


 function autoLink(id)
 {
    var container = document.getElementById(id);
    var doc = container.innerHTML;
    var regURL = new RegExp("(http|https|ftp|telnet|news|irc)://([-/.a-zA-Z0-9_~#%$?&=:200-377()]+)","gi");
    var regEmail = new RegExp("([xA1-xFEa-z0-9_-]+@[xA1-xFEa-z0-9-]+\.[a-z0-9-]+)","gi");
    container.innerHTML = doc.replace(regURL,"<a href=\"$1://$2\" target=\"_blank\" style=\"color:#0473b3\">$1://$2</a>").replace(regEmail,"<a href=\"mailto:$1\" style=\"color:#0473b3\">$1</a>");

}

function getUtcDateTime(timestamp)
{
	var d = new Date(Date.UTC(2018, 8, 30, 0, 0, 0, 0));
	var t = Math.floor(d.getTime() / 1000) * 1000;
	var realDate = t + timestamp * 1000;
	var date = new Date(realDate);

	return date;
}

function stripTag(html)
{
	return html.replace(/(<([^>]+)>)/gi, "");
}

function openEmployeeInfo(dept_code)
{
    var popupLink = "/programs/org/saeol/employee.do?dep_code="+dept_code;
    window.open(popupLink, "_emp", "width=1200, height=800, resizable=no");
    return false;
}
