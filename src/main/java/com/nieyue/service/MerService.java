package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.Mer;

/**
 * 商品逻辑层接口
 * @author yy
 *
 */
public interface MerService {
	/** 新增商品 */	
	public boolean addMer(Mer mer) ;	
	/** 删除商品 */	
	public boolean delMer(Integer merId) ;
	/** 更新商品*/	
	public boolean updateMer(Mer mer);
	/** 装载商品 */	
	public Mer loadMer(Integer merId);	
	/** 商品总共数目 */	
	public int countAll(
			Integer region,
			Integer type,
			String name,
			Integer recommend,
			Double unitPrice,
			Integer saleNumber,
			Double merScore,
			Integer merCateId,
			Integer sellerAccountId,
			Date createDate,
			Date updateDate,
			Integer status
			);
	/** 分页商品信息 */
	public List<Mer> browsePagingMer(
			Integer region,
			Integer type,
			String name,
			Integer recommend,
			Double unitPrice,
			Integer saleNumber,
			Double merScore,
			Integer merCateId,
			Integer sellerAccountId,
			Date createDate,
			Date updateDate,
			Integer status,
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
