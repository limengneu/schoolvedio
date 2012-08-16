/**
 * @文件名称：Vedio.java
 * @类路径：com.school.biz.model
 * @版权:Copyright (c)2012
 * @公司名称：杭州商友全球网信息技术有限公司
 * @作者：limeng
 * @时间：Aug 16, 201211:05:47 AM
 */
package com.school.biz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import com.school.biz.thrid.model.SuccessVedio;
import com.school.common.utils.StringCollectionUtils;



/**
 * @描述：
 * @部门：研发部
 * @作者：limeng
 * @创建时间：Aug 16, 201211:05:47 AM
 */
@Entity
public class Vedio extends BaseModel {

	@Column
	private String author;
	@Column
	private String title;
	@Column
	private String type;
	@Column
	private String tags;
	@Column
	private String summary;
	@Column
	private String path;
	@Column
	private String image;
	@Column
	private String status;
	@Column
	private String vid;
	@Column
	private String vpublic;


	@Transient
	private List<String> tagsList;
	
	
	public Vedio() {
		tagsList = new ArrayList<String>();
	}
	
	public Vedio(SuccessVedio successVedio) {
		this.title=successVedio.getSubject();
		this.author=successVedio.getSid();
		this.path=successVedio.getPlayer();
		this.image=successVedio.getCover();
		this.type=successVedio.getChk();
		this.status=successVedio.getResult();
		this.summary=successVedio.getAttach();
		this.vid=successVedio.getVid();
		this.vpublic=successVedio.getCoop_public();
	}
	
	
	@PostLoad
	void postLoad() {
		tagsList = StringCollectionUtils.deserialize(tags);
	}

	public void prePersist() {
		super.prePersist();
		tags = StringCollectionUtils.serialize(tagsList);

		
	}

	public void preUpdate() {
		super.preUpdate();
		tags = StringCollectionUtils.serialize(tagsList);

	}
	


	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the vid
	 */
	public String getVid() {
		return vid;
	}

	/**
	 * @param vid the vid to set
	 */
	public void setVid(String vid) {
		this.vid = vid;
	}

	/**
	 * @return the vpublic
	 */
	public String getVpublic() {
		return vpublic;
	}

	/**
	 * @param vpublic the vpublic to set
	 */
	public void setVpublic(String vpublic) {
		this.vpublic = vpublic;
	}
	
	

}
