package com.example.qianlong.bean;

import java.io.Serializable;

/**
 * <p>
 * <h1>��ȡʵ�����ŵ�ר�� ר�������ش��û�����</h1>
 * <p>
 * Id int ָ��ID��ÿ��ָ�ɵ�ID��Identity
 * <p>
 * ExpertID int ר��ID��ÿ��ר�ҵ�ID�ǹ̶���
 * <p>
 * ExpertName string ר������
 * <p>
 * ExpertTitle string ר�ҵ�ͷ�Σ�����ý���ˡ�ר�����һ�ר������
 * <p>
 * ExpertMemo string ר�ҵ�����������Ϊ��
 * <p>
 * ExpertPic string ר��ͷ����Ե�ַ������url���� http://ͼƬCDN����/uppics/expert/ExpertPic
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
