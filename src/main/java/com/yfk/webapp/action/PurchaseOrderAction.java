package com.yfk.webapp.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.yfk.model.PurchaseOrder;
import com.yfk.model.PurchaseOrderDetail;


/**
 * Action for facilitating Role Management feature.
 */
public class PurchaseOrderAction extends BaseAction {



	/**
	 * 
	 */
	private static final long serialVersionUID = -569882426981216104L;
	private List<PurchaseOrder> purchaseOrders;
	private List<PurchaseOrderDetail> purchaseOrderDetails;
	private PurchaseOrder purchaseOrder;
	private String xpyhmstro_yhdnbr;

	/**
	 * Holder for purchaseOrders to display on list screen
	 *
	 * @return list of purchaseOrders
	 */
	public List<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}

	public List<PurchaseOrderDetail> getPurchaseOrderDetails() {
		return purchaseOrderDetails;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public String getXpyhmstro_yhdnbr() {
		return xpyhmstro_yhdnbr;
	}

	public void setXpyhmstro_yhdnbr(String xpyhmstro_yhdnbr) {
		this.xpyhmstro_yhdnbr = xpyhmstro_yhdnbr;
	}


	/**
	 * Grab the purchaseOrder from the database based on the "purchaseOrderName" passed in.
	 *
	 * @return success if purchaseOrder found
	 * @throws IOException
	 *             can happen when sending a "forbidden" from
	 *             response.sendError()
	 */
	public String edit() throws IOException {

		// if a purchaseOrderCode is passed in
		if (xpyhmstro_yhdnbr != null) {
			// lookup the purchaseOrder using code
			//purchaseOrder = (PurchaseOrder) universalManager.get(PurchaseOrder.class, xpyhmstro_yhdnbr);
			
			purchaseOrder = new PurchaseOrder();
			purchaseOrder.setXpyhmstro_yhdnbr("ORD000001");
			purchaseOrder.setXpyhmstro_priority("High");
			purchaseOrder.setXpyhmstro_creator("admin");
			purchaseOrder.setXpyhmstro_receptdt("20150901");
			purchaseOrder.setXpyhmstro_startdt("20150831");
			purchaseOrder.setXpyhmstro_seq(1);
			purchaseOrder.setXpyhmstro_suppcode("ADKJ");
			purchaseOrder.setXpyhmstro_shipto("秀浦路426号");
			purchaseOrder.setXpyhmstro_stat(2);
			
			purchaseOrderDetails = new ArrayList<PurchaseOrderDetail>();
			PurchaseOrderDetail podet = new PurchaseOrderDetail();
			podet.setXpyhddeto_seq(new BigDecimal(10));
			podet.setXpyhddeto_yhdnbr("ORD000001");
			podet.setXpyhddeto_partnbr("1000001");
			podet.setXpyhddeto_partdesc("螺丝");
			podet.setXpyhddeto_spq(new BigDecimal(100));
			podet.setXpyhddeto_uom("件");
			podet.setXpyhddeto_reqqty(new BigDecimal(2000));
			podet.setXpyhddeto_ordqty(new BigDecimal(2000));
			purchaseOrderDetails.add(podet);
			
			PurchaseOrderDetail podet1 = new PurchaseOrderDetail();
			podet1.setXpyhddeto_seq(new BigDecimal(20));
			podet1.setXpyhddeto_yhdnbr("ORD000001");
			podet1.setXpyhddeto_partnbr("1000002");
			podet1.setXpyhddeto_partdesc("螺母");
			podet1.setXpyhddeto_spq(new BigDecimal(100));
			podet1.setXpyhddeto_uom("件");
			podet1.setXpyhddeto_reqqty(new BigDecimal(2000));
			podet1.setXpyhddeto_ordqty(new BigDecimal(2000));
			purchaseOrderDetails.add(podet1);
			
		} else {
			purchaseOrder = new PurchaseOrder();
			// purchaseOrder.addRole(new Role(Constants.USER_ROLE));
		}

		return SUCCESS;
	}

	


	public String confirm() {
		xpyhmstro_yhdnbr = "ORD000001";
		return SUCCESS;
	}
	
	public String close() {
		xpyhmstro_yhdnbr = "ORD000001";
		return SUCCESS;
	}
	
	/**
	 * Fetch all purchaseOrders from database and put into local "purchaseOrders" variable for
	 * retrieval in the UI.
	 *
	 * @return "success" if no exceptions thrown
	 */
	public String list() {
		query();
		return SUCCESS;
	}


	private void query() {
		
		purchaseOrders = new ArrayList<PurchaseOrder>();
		PurchaseOrder po = new PurchaseOrder();
		po.setXpyhmstro_yhdnbr("ORD000001");
		po.setXpyhmstro_priority("High");
		po.setXpyhmstro_creator("admin");
		po.setXpyhmstro_receptdt("20150901");
		po.setXpyhmstro_startdt("20150831");
		po.setXpyhmstro_seq(1);
		po.setXpyhmstro_suppcode("ADKJ");
		po.setXpyhmstro_shipto("秀浦路426号");
		purchaseOrders.add(po);
	}
}
