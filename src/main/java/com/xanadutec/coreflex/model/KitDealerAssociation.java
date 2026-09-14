package com.xanadutec.coreflex.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="KitDealerAssociation")
public class KitDealerAssociation {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="dealer")
	private Dealer dealer;
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade(value={CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="pack")
	private Package pack;
	private Integer totalKit;
	private Integer usedKit;
	private Integer returnKit;
	private Integer remKit;
	
	public Integer getRemKit() {
		return remKit;
	}
	public void setRemKit(Integer remKit) {
		this.remKit = remKit;
	}
	public Integer getReturnKit() {
		return returnKit;
	}
	public void setReturnKit(Integer returnKit) {
		this.returnKit = returnKit;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Dealer getDealer() {
		return dealer;
	}
	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}
	public Package getPack() {
		return pack;
	}
	public void setPack(Package pack) {
		this.pack = pack;
	}
	public Integer getTotalKit() {
		return totalKit;
	}
	public void setTotalKit(Integer totalKit) {
		this.totalKit = totalKit;
	}
	public Integer getUsedKit() {
		return usedKit;
	}
	public void setUsedKit(Integer usedKit) {
		this.usedKit = usedKit;
	}

	
	
	
	


}
