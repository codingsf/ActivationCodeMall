package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Account;
import com.nieyue.bean.Mer;
import com.nieyue.bean.MerRelation;
import com.nieyue.dao.MerRelationDao;
import com.nieyue.service.AccountService;
import com.nieyue.service.MerRelationService;
import com.nieyue.service.MerService;
@Service
public class MerRelationServiceImpl implements MerRelationService{
	@Resource
	MerRelationDao merRelationDao;
	@Resource
	AccountService accountService;
	@Resource
	MerService merService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addMerRelation(MerRelation merRelation) {
		merRelation.setCreateDate(new Date());
		boolean b = merRelationDao.addMerRelation(merRelation);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delMerRelation(Integer merRelationId) {
		boolean b = merRelationDao.delMerRelation(merRelationId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateMerRelation(MerRelation merRelation) {
		boolean b = merRelationDao.updateMerRelation(merRelation);
		return b;
	}

	@Override
	public MerRelation loadMerRelation(Integer merRelationId) {
		MerRelation r = merRelationDao.loadMerRelation(merRelationId);
		Mer mer=merService.loadMer(r.getSellerMerId());
		Account a=accountService.loadAccount(r.getSellerAccountId());
		r.setSellerAccount(a);
		r.setSellerMer(mer);
		return r;
	}

	@Override
	public int countAll(
			Integer platformMerId,
			Integer sellerMerId,
			Integer sellerAccountId) {
		int c = merRelationDao.countAll(platformMerId,sellerMerId,sellerAccountId);
		return c;
	}

	@Override
	public List<MerRelation> browsePagingMerRelation(
			Integer platformMerId,
			Integer sellerMerId,
			Integer sellerAccountId,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<MerRelation> l = merRelationDao.browsePagingMerRelation(platformMerId,sellerMerId,sellerAccountId,pageNum-1, pageSize, orderName, orderWay);
		l.forEach((mr)->{
			Mer mer=merService.loadMer(mr.getSellerMerId());
			Account a=accountService.loadAccount(mr.getSellerAccountId());
			mr.setSellerAccount(a);
			mr.setSellerMer(mer);
		});
		return l;
	}

	
}
