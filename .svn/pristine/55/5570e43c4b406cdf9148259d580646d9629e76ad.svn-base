package egovframework.dnworks.func.cal.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.Globals;
import egovframework.dnworks.func.cal.service.CalInfoService;
import egovframework.dnworks.func.cal.service.CalInfoVO;
import egovframework.dnworks.func.org.service.OrgInfoService;
import egovframework.dnworks.func.org.service.OrgInfoVO;
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CalInfoService")
public class CalInfoServiceImpl extends EgovAbstractServiceImpl implements CalInfoService {

    @Autowired
    private CalInfoMapper calInfoMapper;

    @Autowired
    private OrgInfoService orgInfoService;

    @Resource(name = "egovOrgIdGnrService")
    private EgovIdGnrService idgenService;

    private static final String TAG = "calController";
    private static final Logger LOGGER = LoggerFactory.getLogger("ServiceInfoLogger");

    @Override
    public void scheduleTossSettlementAll() throws Exception {
    	List<Map<String, String>> propList = EgovProperties.loadPropertyFile(Globals.YES_CONF_PATH);
        Set<String> orgUnqIdSet = new HashSet<>();

        for (Map<String, String> map : propList) {
            for (String key : map.keySet()) {
                if (key.startsWith("toss.apiKey.")) {
                    String[] parts = key.split("\\.");
                    if (parts.length >= 4) {
                        String orgUnqId = parts[2];
                        orgUnqIdSet.add(orgUnqId);
                    }
                }
            }
        }

        for (String orgUnqId : orgUnqIdSet) {
            try {
                this.tossSettlement(orgUnqId);
            } catch (Exception e) {
                LOGGER.info("[{}] 정산 실패: 기관ID={}, 이유={}", TAG, orgUnqId, e.getMessage());
            }
        }
    }

    @Override
    public void tossSettlement(String orgUnqId) {
    	 String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
         this.tossSettlementByDate(orgUnqId, today, today);
    }
    
    @Override
    public void tossSettlementByDate(String orgUnqId, String startDate, String endDate ) {
        try {
            Properties props = new Properties();
            try (InputStream input = new FileInputStream(Globals.YES_CONF_PATH)) {
                props.load(new InputStreamReader(input, StandardCharsets.UTF_8));
            } catch (IOException e) {
                LOGGER.error("[{}] yes.properties 로딩 실패: {}", TAG, e.getMessage(), e);
                return;
            }

            Set<String> orgSet = new HashSet<>();

            // orgUnqId가 null이면 전체 기관 처리
            if (orgUnqId == null || orgUnqId == "") {
                for (String key : props.stringPropertyNames()) {
                    if (key.startsWith("toss.apiKey.") && (key.endsWith(".onl") || key.endsWith(".off"))) {
                        String[] parts = key.split("\\.");
                        if (parts.length == 4) {
                            orgSet.add(parts[2]); // orgUnqId
                        }
                    }
                }
            } else {
                orgSet.add(orgUnqId);
            }

            for (String id : orgSet) {
                deleteCalInfoOnce(id, startDate, endDate);
                String onlKey = props.getProperty("toss.apiKey." + id + ".onl");
                String offKey = props.getProperty("toss.apiKey." + id + ".off");

                if (onlKey != null) {
                    List<Map<String, Object>> onlList = callTossApi(startDate, endDate, onlKey);
                    if (!onlList.isEmpty()) {
                        for (Map<String, Object> row : onlList) {
                            saveCalInfo(id, "ONL", row);
                        }
                    }
                } else {
                    LOGGER.warn("[{}] onlKey 없음 - 기관: {}", TAG, id);
                }

                if (offKey != null) {
                    List<Map<String, Object>> offList = callTossApi(startDate, endDate, offKey);
                    if (!offList.isEmpty()) {
                        for (Map<String, Object> row : offList) {
                            saveCalInfo(id, "OFF", row);
                        }
                    }
                } else {
                    LOGGER.warn("[{}] offKey 없음 - 기관: {}", TAG, id);
                }
            }

        } catch (Exception e) {
            LOGGER.error("[{}] tossSettlementByDate 예외 발생: {}", TAG, e.getMessage(), e);
        }
    }




    @Override
    public List<CalInfoVO> selectList(Map<String, Object> param) throws Exception {
        return calInfoMapper.selectList(param);
    }

    @Override
    public int selectListCnt(Map<String, Object> param) throws Exception {
        return calInfoMapper.selectListCnt(param);
    }

    private List<Map<String, Object>> callTossApi(String startDate, String endDate, String apiKey) throws Exception {
        String urlStr = String.format("https://api.tosspayments.com/v1/settlements?startDate=%s&endDate=%s&dateType=paidOutDate",
                startDate, endDate);

        String encodedKey = Base64.getEncoder().encodeToString((apiKey + ":").getBytes());

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(60000);
        conn.setReadTimeout(60000);
        conn.setRequestProperty("Authorization", "Basic " + encodedKey);
        conn.setRequestProperty("Content-Type", "application/json");

        int responseCode = conn.getResponseCode();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                responseCode == 200 ? conn.getInputStream() : conn.getErrorStream(), "UTF-8"));

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
            System.out.println("Response Body: " + line);
        }
        reader.close();
        


        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.toString(), List.class);
    }

    private void saveCalInfo(String orgUnqId, String calSeCd, Map<String, Object> data) {
        try {
            String payoutDateStr = (String) data.get("paidOutDate");
            if (payoutDateStr == null) return;

            String orderId = (String) data.get("orderId");
            if (orderId == null) return;


            CalInfoVO vo = new CalInfoVO();
            vo.setCalId(UUID.randomUUID().toString().replace("-", ""));
            vo.setOrgUnqId(orgUnqId);
            vo.setMid((String) data.get("mId"));
            vo.setOrderId(orderId);
            vo.setMethod((String) data.get("method"));
            vo.setAmount(toLong(data.get("amount")));
            vo.setFee(extractFee(data));
            vo.setInterestFee(toLong(data.get("interestFee")));
            vo.setSupplyAmount(toLong(data.get("supplyAmount")));
            vo.setVat(toLong(data.get("vat")));
            vo.setPayoutAmount(toLong(data.get("payOutAmount")));
            vo.setApprovedAt(data.get("approvedAt") != null ? data.get("approvedAt").toString().substring(0, 10) : null);
            vo.setSoldDate(data.get("soldDate") != null ? data.get("soldDate").toString().substring(0, 10) : null);
            vo.setPayoutDate(payoutDateStr.substring(0, 10));
            vo.setCreatedAt(new java.util.Date());

            calInfoMapper.insertCalInfo(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Long extractFee(Map<String, Object> data) {
        try {
            List<Map<String, Object>> fees = (List<Map<String, Object>>) data.get("fees");
            if (fees != null && !fees.isEmpty()) {
                Object fee = fees.get(0).get("fee");
                return toLong(fee);
            }
        } catch (Exception e) {
            // 무시
        }
        return 0L;
    }

    private Long toLong(Object obj) {
        if (obj == null) return 0L;
        try {
            return Long.valueOf(obj.toString());
        } catch (Exception e) {
            return 0L;
        }
    }

    private java.util.Date parseDate(Object isoDate) {
        if (isoDate == null) return null;
        try {
            return javax.xml.bind.DatatypeConverter.parseDateTime(isoDate.toString()).getTime();
        } catch (Exception e) {
            return null;
        }
    }
    
    private void deleteCalInfoOnce(String orgUnqId, String startDate , String endDate) {
    	 Map<String, Object> delParam = new HashMap<>();
         delParam.put("orgUnqId", orgUnqId);
         delParam.put("startDate", startDate.substring(0, 10));
         delParam.put("endDate", endDate.substring(0, 10));
         calInfoMapper.deleteCalInfoByDate(delParam);
    }

    
   

}
