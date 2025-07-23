package egovframework.transfer.mcs.service.impl;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.transfer.mcs.service.McsService;
import egovframework.transfer.mcs.service.McsVO;

@Service("McsService")
public class McsServiceImpl extends EgovAbstractServiceImpl implements McsService {

	@Autowired
	McsMapper mapper;
	
	@Override
	public void sendMMS(McsVO insertVO) throws Exception {
		mapper.sendMMS(insertVO);
	}

}
