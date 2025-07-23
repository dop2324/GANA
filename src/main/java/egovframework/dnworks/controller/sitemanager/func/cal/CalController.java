package egovframework.dnworks.controller.sitemanager.func.cal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.dnworks.base.WebDefault;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.cmmncd.service.CmmnCdService;
import egovframework.dnworks.func.cmm.utl.JavaScriptUtil;
import egovframework.dnworks.func.cal.service.CalInfoService;
import egovframework.dnworks.func.cal.service.CalInfoVO;
import egovframework.dnworks.cmm.PageNavi;

@Controller
@RequestMapping("/SiteManager/func/cal")
public class CalController extends WebDefault {

    @Resource(name = "CalInfoService")
    private CalInfoService calInfoService;

    @Autowired
    private CmmnCdService cmmnCdService;

    /**
     * 정산 리스트 뷰
     */
    @RequestMapping("/info.do")
    public String info(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
        String mnu_code = Util.nvl(request.getParameter("mnu_code"));
        int cmd = Util.nvl(request.getParameter("cmd"), 1);

        HashMap<String, Object> udp = new HashMap<>();
        udp.put("mnu_code", "");
        udp.put("srchSe", "");
        udp.put("srchUseYn", "");
        udp.put("pageNo", 1);
        udp.put("pageSize", 20);
        

        String queryString = super.getParameters(request, udp, true, false);
        model.addAttribute("queryString", queryString);
        model.addAttribute("listLink", String.format("?%s", queryString));
        model.addAttribute("mnu_code", mnu_code);

        switch (cmd) {
            default:
                this.list(request, session, model, queryString);
                return managerDir + "/func/cal/list";
        }
    }

    /**
     * 정산 리스트 데이터 바인딩
     */
    public void list(HttpServletRequest request, HttpSession session, ModelMap model, String queryString) throws Exception {
        int pageNo = Util.nvl(request.getParameter("pageNo"), 1);
        int pageSize = Util.nvl(request.getParameter("pageSize"), 20);
        String dateType = Util.nvl(request.getParameter("dateType"), "paidOutDate");  // 기본값 정산일
        String status = Util.nvl(request.getParameter("status"));  // "payment" or "refund"
        String startDate = Util.nvl(request.getParameter("startDate"));
        String endDate = Util.nvl(request.getParameter("endDate"));
        String orgUnqId = this.mngrOrgUnqId(session);

        Map<String, Object> param = new HashMap<>();
        param.put("offset", (pageNo - 1) * pageSize);
        param.put("pageSize", pageSize);
        param.put("pageNo", pageNo);
        param.put("orgUnqId", orgUnqId);
        param.put("startDate", startDate); // 정산 지급일 조건
        param.put("endDate", endDate); // 정산 지급일 조건
        param.put("dateType", dateType);
        param.put("status", status);

        int totalCnt = 0;
        List<CalInfoVO> calList = null;

        
        totalCnt = calInfoService.selectListCnt(param);
        calList = calInfoService.selectList(param);
        
        // 기관별 결제금액/환불금액 집계
        Map<String, Integer> payMap = new HashMap<>();
        Map<String, Integer> refundMap = new HashMap<>();

        for (CalInfoVO vo : calList) {
            String orgNm = vo.getOrgNm();
            long amt = vo.getAmount();

            if (amt >= 0) {
                payMap.put(orgNm, payMap.getOrDefault(orgNm, 0) + (int) amt);
            } else {
                refundMap.put(orgNm, refundMap.getOrDefault(orgNm, 0) + Math.abs((int) amt));
            }
        }

        // 집계 결과를 각 VO에 세팅
        for (CalInfoVO vo : calList) {
            vo.setTotalPayment(payMap.getOrDefault(vo.getOrgNm(), 0));
            vo.setTotalRefund(refundMap.getOrDefault(vo.getOrgNm(), 0));
        }

        int no = totalCnt - ((pageNo - 1) * pageSize);

        model.addAttribute("pageNo", pageNo);
        model.addAttribute("no", no);
        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("calList", calList);
        model.addAttribute("srchMap", param);
        model.addAttribute("dateType", dateType);
        model.addAttribute("status", status);
        model.addAttribute("gridJson", new ObjectMapper().writeValueAsString(calList));

        PageNavi pageNavi = new PageNavi();
        model.addAttribute("paging", pageNavi.getPageNaviTag(totalCnt, pageSize, pageNo, "", queryString));
    }

    /**
     * 수동 정산 동기화 (지급일자 기준)
     */
    @RequestMapping(value = "/syncManual.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> syncManual(HttpServletRequest request, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        String orgUnqId = null;
        
        try {
            orgUnqId = this.mngrOrgUnqId(session);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        try {
            calInfoService.tossSettlementByDate(orgUnqId, startDate, endDate);
            result.put("success", true);
            result.put("message", "정산 수동 동기화가 완료되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "정산 동기화 중 오류가 발생했습니다.");
        }

        return result;
    }
    
    @RequestMapping("/excelDown.do")
    public void excelDown(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        String dateType = Util.nvl(request.getParameter("dateType"), "paidOutDate");
        String startDate = Util.nvl(request.getParameter("startDate"));
        String endDate = Util.nvl(request.getParameter("endDate"));
        String orgUnqId = this.mngrOrgUnqId(session);

        Map<String, Object> param = new HashMap<>();
        param.put("orgUnqId", orgUnqId);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        param.put("dateType", dateType);

        List<CalInfoVO> list = calInfoService.selectList(param);

        String fileName = "정산내역_" + startDate.replaceAll("-", "") + "_" + endDate.replaceAll("-", "") + ".xlsx";
        fileName = fileName.replaceAll("\\s+", "");

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("정산내역");
        sheet.setDefaultColumnWidth(20);

     // 1. 헤더 스타일
        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 11);

        CellStyle headerStyle = wb.createCellStyle();
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderTop(BorderStyle.THICK);
        headerStyle.setBorderBottom(BorderStyle.THICK);
        headerStyle.setBorderLeft(BorderStyle.THICK);
        headerStyle.setBorderRight(BorderStyle.THICK);
        headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        // 2. 기본 데이터 셀: 가운데 정렬
        CellStyle centerStyle = wb.createCellStyle();
        centerStyle.setAlignment(HorizontalAlignment.CENTER);
        centerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        centerStyle.setBorderTop(BorderStyle.THIN);
        centerStyle.setBorderBottom(BorderStyle.THIN);
        centerStyle.setBorderLeft(BorderStyle.THIN);
        centerStyle.setBorderRight(BorderStyle.THIN);
        centerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        centerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        centerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        centerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        // 3. 금액 셀: 오른쪽 정렬
        CellStyle rightStyle = wb.createCellStyle();
        rightStyle.cloneStyleFrom(centerStyle);  // 테두리 등 그대로 복사
        rightStyle.setAlignment(HorizontalAlignment.RIGHT);  // 정렬만 변경

        

        // 2. 헤더 생성
        String[] headers = {"기관명", "거래ID", "결제수단", "승인일자", "매출일", "정산일", "결제/취소금액"};
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // 3. 데이터 행 생성
        int rowNum = 1;
        for (CalInfoVO vo : list) {
            Row row = sheet.createRow(rowNum++);

            Cell c0 = row.createCell(0); c0.setCellValue(vo.getOrgNm());       c0.setCellStyle(centerStyle);
            Cell c1 = row.createCell(1); c1.setCellValue(vo.getOrderId());     c1.setCellStyle(centerStyle);
            Cell c2 = row.createCell(2); c2.setCellValue(vo.getMethod());      c2.setCellStyle(centerStyle);
            Cell c3 = row.createCell(3); c3.setCellValue(vo.getApprovedAt());  c3.setCellStyle(centerStyle);
            Cell c4 = row.createCell(4); c4.setCellValue(vo.getSoldDate());    c4.setCellStyle(centerStyle);
            Cell c5 = row.createCell(5); c5.setCellValue(vo.getPayoutDate());  c5.setCellStyle(centerStyle);
            Cell c6 = row.createCell(6); c6.setCellValue(vo.getAmount());      c6.setCellStyle(rightStyle); // 결제/취소금액

        }


        // 5. 다운로드 처리
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + java.net.URLEncoder.encode(fileName, "UTF-8") + "\"");

        try (ServletOutputStream out = response.getOutputStream()) {
            wb.write(out);
        }
    }




    
}
