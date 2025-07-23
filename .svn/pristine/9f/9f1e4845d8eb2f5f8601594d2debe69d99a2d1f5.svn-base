/* **********************************************************************************
 * Date Handler
 * srcDate : 기준일자
 * jumpNum : 범위
 * ymdType : 단위 구분 (년/월/일)
 * **********************************************************************************/
 
 function getToday()
{
    var date = new Date();
    var year = date.getFullYear();
    var month = ("0" + (1 + date.getMonth())).slice(-2);
    var day = ("0" + date.getDate()).slice(-2);

    return year + "-" + month + "-" + day;
}

function getDateScope(srcDate, jumpNum, ymdType)
{
	if(srcDate == "")
	{
		srcDate = getToday();
	}
	var dateScope = new Array(2);
	var tempDate = srcDate.split("-");
	var sDate, eDate;
	var sYYYY, sMM, sDD;
	var eYYYY, eMM, eDD;

	switch(ymdType)
	{
		case "y" : 
			
			if(jumpNum < 0)
			{
				sDate = new Date(parseInt(tempDate[0], 10) + jumpNum,	1 - 1,		1);
				eDate = new Date(parseInt(tempDate[0], 10) - 1, 		12 - 1,		31);
			}
			else
			{
				sDate = new Date(parseInt(tempDate[0], 10) + 1,			1 - 1,		1);
				eDate = new Date(parseInt(tempDate[0], 10) + jumpNum,	12 - 1, 	31);
			}
			break;
			
		case "m" :
			
			if(jumpNum < 0)
			{
				sDate = new Date(parseInt(tempDate[0], 10), parseInt(tempDate[1], 10) - 1 + jumpNum,		1);
				eDate = new Date(parseInt(tempDate[0], 10), parseInt(tempDate[1], 10) - 1,					1 - 1);
			}
			else
			{
				sDate = new Date(parseInt(tempDate[0], 10), parseInt(tempDate[1], 10) - 1 + 1,				1);
				eDate = new Date(parseInt(tempDate[0], 10), parseInt(tempDate[1], 10) - 1 + jumpNum + 1,	1 - 1);
			}
			break;
			
		case "d" : 
			
			sDate = new Date(parseInt(tempDate[0], 10), parseInt(tempDate[1], 10) - 1, parseInt(tempDate[2], 10) + jumpNum);
			eDate = new Date(parseInt(tempDate[0], 10), parseInt(tempDate[1], 10) - 1, parseInt(tempDate[2], 10) + jumpNum);
			break;	
	}
	
	sYYYY = sDate.getFullYear();
	sMM = sDate.getMonth() + 1;
	sDD = sDate.getDate();

	eYYYY = eDate.getFullYear();
	eMM = eDate.getMonth() + 1;
	eDD = eDate.getDate();
	
	dateScope[0] = sYYYY + "-" + formatNum(sMM) + "-" + formatNum(sDD);
	dateScope[1] = eYYYY + "-" + formatNum(eMM) + "-" + formatNum(eDD);
	
	return dateScope;
}

function formatNum(num) 
{
    return (num.toString().length == 1 ? "0" + num : num);
}

/*
 * 접속통계
 */
function searchPre(ymdType)
{
	var srchStartDate = document.getElementById("srchSDate");
	var srchEndDate = document.getElementById("srchEDate");
	
	var viewDate = getDateScope(srchEndDate.value, -1, ymdType);

	srchStartDate.value = viewDate[0];
	srchEndDate.value = viewDate[1];
		
	searchData();
}

function searchNxt(ymdType)
{
	var srchStartDate = document.getElementById("srchSDate");
	var srchEndDate = document.getElementById("srchEDate");

	var viewDate = getDateScope(srchEndDate.value, +1, ymdType);

	srchStartDate.value = viewDate[0];
	srchEndDate.value = viewDate[1];
		
	searchData();
}