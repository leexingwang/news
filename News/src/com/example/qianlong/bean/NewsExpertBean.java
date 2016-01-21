package com.example.qianlong.bean;

import java.io.Serializable;

/**
 * <p>
 * <h1>获取实体新闻的专家 专家用来回答用户提问</h1>
 * <p>
 * Id int 指派ID，每次指派的ID是Identity
 * <p>
 * ExpertID int 专家ID，每个专家的ID是固定的
 * <p>
 * ExpertName string 专家姓名
 * <p>
 * ExpertTitle string 专家的头衔，比如媒体人、专栏作家或专栏作家
 * <p>
 * ExpertMemo string 专家的描述，可能为空
 * <p>
 * ExpertPic string 专家头像，相对地址，完整url请用 http://图片CDN域名/uppics/expert/ExpertPic
 * 
 * @author lixingwang
 * 
 */
public class NewsExpertBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int Id;

	private int ExpertID;

	private String ExpertName;

	private String ExpertTitle;

	private String ExpertMemo;

	private String ExpertPic;

	public void setId(int Id) {
		this.Id = Id;
	}

	public int getId() {
		return this.Id;
	}

	public void setExpertID(int ExpertID) {
		this.ExpertID = ExpertID;
	}

	public int getExpertID() {
		return this.ExpertID;
	}

	public void setExpertName(String ExpertName) {
		this.ExpertName = ExpertName;
	}

	public String getExpertName() {
		return this.ExpertName;
	}

	public void setExpertTitle(String ExpertTitle) {
		this.ExpertTitle = ExpertTitle;
	}

	public String getExpertTitle() {
		return this.ExpertTitle;
	}

	public void setExpertMemo(String ExpertMemo) {
		this.ExpertMemo = ExpertMemo;
	}

	public String getExpertMemo() {
		return this.ExpertMemo;
	}

	public void setExpertPic(String ExpertPic) {
		this.ExpertPic = ExpertPic;
	}

	public String getExpertPic() {
		return this.ExpertPic;
	}
}
