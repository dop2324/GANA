<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%-- holdOn --%>
<script src="${pageContext.request.contextPath }/common/Holdon/HoldOn.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/common/Holdon/HoldOn.min.css" />

<div class="board_wrap">
	<div class="board_write form_style">
		<dl>
			<dt><label for="orgNm">기관명</label></dt>
			<dd>
				<c:out value='${orgVo.orgNm}'/>
				<div class="board_opt_frm">
					<c:choose>
						<c:when test="${orgVo.useYn eq 'Y' }"><span class="green">사용</span></c:when>
						<c:when test="${orgVo.useYn eq 'N' }"><span class="red">미사용</span></c:when>
					</c:choose>
				</div>
				<div class="board_desc">
					<strong>등록일자</strong> <c:out value="${orgVo.regDt }" />
				</div>
			</dd>
			<dt><label for="hmpgUrl">홈페이지</label></dt>
			<dd>
				<c:out value='${orgVo.hmpgUrl}'/>
			</dd>
			<dt><label for="addr">주소</label></dt>
			<dd>
				<c:out value='${orgVo.zip}'/>&nbsp;<c:out value='${orgVo.roadNmAddr}'/>&nbsp;<c:out value='${orgVo.dtlAddr}'/>
			</dd>
			<dt><label for="addr">담당자정보</label></dt>
			<dd>
				<div class="flex-column">
					<p><strong>담당자명</strong> <c:out value='${orgVo.picNm}'/></p>
					<p><strong>이메일</strong> <c:out value='${orgVo.picEmlAddr}'/></p>
					<p><strong>연락처</strong> <c:out value='${orgVo.picTelno}'/></p>
				</div>
			</dd>
			<dt><label for="addr">유지보수 업체정보</label></dt>
			<dd>
				<div class="flex-column">
					<p><strong>업체명</strong> <c:out value='${orgVo.mtncoNm}'/></p>
					<p><strong>담당자</strong> <c:out value='${orgVo.mtncoPicNm}'/></p>
					<p><strong>이메일</strong> <c:out value='${orgVo.mtncoPicEmlAddr}'/></p>
				</div>
			</dd>
		</dl>
	</div>

	<div class="board_area" style="display: flex;flex-direction: column;gap: 5rem;margin:11rem 0; width:100%;">
		<h4>연계주소관리</h4>
		<div class="board_desc">
			<ul>
				<li>등록된 연계주소에서 제공하는 데이터를 수집합니다.</li>
				<li>연계주소는 반드시 443포트로 통신하며 (https) 데이터 형식은 JSON입니다.</li>
			</ul>
		</div>

		<table class="board_table">
			<caption>연계주소 설정</caption>
			<thead>
				<tr>
					<th scope="col" style="width:7%;">순번</th>
					<th scope="col" style="width:10%;">구분</th>
					<th scope="col">url</th>
					<th scope="col" style="width:10%;">사용유무</th>
					<th scope="col" style="width:14%;">관리</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>추가</td>
					<td>
						<select name="dataDivCd" id="dataDivCd">
						<c:forEach var="list" items="${moa:getCodeList(pageContext.request, 'RSRC_CL') }" varStatus="status">
							<option value="<c:out value='${list.CODE_VAL }' />"><c:out value="${list.CODE_NM }" /></option>
						</c:forEach>
						</select>
					</td>
					<td style="text-align:left;"><input type="text" name="url" id="url" class="inp_txt w100" maxlength="200" placeholder="URL" /></td>
					<td><input type="checkbox" name="useYn" id="useYn" value="Y" /></td>
					<td>
						<a href="#self" onclick="return lnkorgUrlSave();" class="btn blue">저장</a>
					</td>
				</tr>
				<c:forEach var="list" items="${urlList }" varStatus="status">
				<tr>
					<td><c:out value="${status.count }" /></td>
					<td><c:out value="${moa:getCodeNm(pageContext.request, '15', list.dataDivCd)}" /></td>
					<td style="text-align:left;"><c:out value="${list.url }" /></td>
					<td>
						<input type="checkbox" value="Y" <c:out value="${list.useYn eq 'Y'?' checked':'' }" /> />
					</td>
					<td>
						<a href="#self" class="btn" onclick="return lnkorgUrlUpdt(this, '<c:out value="${list.lnkorgUrlId }" />');">수정</a>
						<a href="#self" class="btn pink" onclick="return lnkorgUrlRemove('<c:out value="${list.lnkorgUrlId }" />');">삭제</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<div class="board_btn">
	<a href="<c:out value="${listLink }"/>">목록</a>
</div>

<script type="text/javascript">
	const orgUnqId = '<c:out value="${orgVo.orgUnqId}" />';
	
	// 수정
	function lnkorgUrlUpdt(el, urlId) {
		const tr  = el.closest('tr');
		const chk = tr.querySelector('input[type=checkbox]');
		const useYn = chk && chk.checked ? 'Y' : 'N';
		
	    HoldOn.open({
			theme: 'sk-circle',
			message: '처리 중입니다. 잠시만 기다려주세요...'
		});
	    
		$.ajax({
			type:"post"
			, url:globalConfigJs.contextPath + "/SiteManager/func/lnkorgUrl/update_process.do"
			, dataType:"json"
			, data: {
				mnu_code : '${mnu_code}',
				lnkorgUrlId  : urlId,
				useYn : useYn
			}
			, success:function(data)
			{
				HoldOn.close();
				if (data.result) {
					alert("저장되었습니다.");
					location.reload();
				} else {
					alert("오류: " + (data.msg || "처리 중 오류가 발생했습니다."));
				}
				
			}
			, error:function(jqXHR, textStatus, errorThrown)
			{
				alert('통신중에 장애가 발생했습니다.');
				HoldOn.close();
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}		
		});
	  
		return false;
	}

	// 삭제
	function lnkorgUrlRemove(lnkorgUrlId){
		if(confirm('정말로 삭제하시겠습니까?') !== false){
		    
		    HoldOn.open({
				theme: 'sk-circle',
				message: '처리 중입니다. 잠시만 기다려주세요...'
			});
		    
			$.ajax({
				type:"post"
				, url:globalConfigJs.contextPath + "/SiteManager/func/lnkorgUrl/remove_process.do"
				, dataType:"json"
				, data: {
					mnu_code : '${mnu_code}',
					lnkorgUrlId  : lnkorgUrlId
				}
				, success:function(data)
				{
					HoldOn.close();
					if (data.result) {
						alert("저장되었습니다.");
						location.reload();
					} else {
						alert("오류: " + (data.msg || "처리 중 오류가 발생했습니다."));
					}
					
				}
				, error:function(jqXHR, textStatus, errorThrown)
				{
					alert('통신중에 장애가 발생했습니다.');
					HoldOn.close();
					console.log(jqXHR);
					console.log(textStatus);
					console.log(errorThrown);
				}		
			});
			
		}
		
	}
	
	// 등록
	function lnkorgUrlSave(){
	    const dataDivCd = document.getElementById("dataDivCd").value;
	    const url = document.getElementById("url").value.trim();
	    const useYn = document.getElementById("useYn").checked ? "Y" : "N";
	    
	    if (!url) {
	        alert("URL을 입력해주세요.");
	        document.getElementById("url").focus();
	        return false;
	    }
	    
	    HoldOn.open({
			theme: 'sk-circle',
			message: '처리 중입니다. 잠시만 기다려주세요...'
		});
	    
		$.ajax({
			type:"post"
			, url:globalConfigJs.contextPath + "/SiteManager/func/lnkorgUrl/save_process.do"
			, dataType:"json"
			, data: {
				mnu_code : '${mnu_code}',
				orgUnqId  : orgUnqId,
				dataDivCd : dataDivCd,
				url       : url,
				useYn     : useYn
			}
			, success:function(data)
			{
				HoldOn.close();
				if (data.result) {
					alert("저장되었습니다.");
					location.reload();
				} else {
					alert("오류: " + (data.msg || "처리 중 오류가 발생했습니다."));
				}
				
			}
			, error:function(jqXHR, textStatus, errorThrown)
			{
				alert('통신중에 장애가 발생했습니다.');
				HoldOn.close();
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}		
		});
	}
</script>
