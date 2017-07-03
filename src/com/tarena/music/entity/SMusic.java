package com.tarena.music.entity;

import java.io.Serializable;

import org.xutils.db.annotation.Column;

/**
 * 此类用来表示收藏的歌曲
 * 
 * @author Administrator
 * 
 */
public class SMusic implements Serializable {

	@Column(name = "id", isId = true, autoGen = true, property = "NOT NULL")
	private int id;
	@Column(name = "title")
	private String title;
	@Column(name = "songname")
	private String songname;
	@Column(name = "songid")
	private String songid;
	@Column(name = "pic")
	private String pic;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSongname() {
		return songname;
	}

	public void setSongname(String songname) {
		this.songname = songname;
	}

	public String getSongid() {
		return songid;
	}

	public void setSongid(String songid) {
		this.songid = songid;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public SMusic(int id, String title, String songname, String songid,
			String pic) {
		super();
		this.id = id;
		this.title = title;
		this.songname = songname;
		this.songid = songid;
		this.pic = pic;
	}

	public SMusic() {
		super();
	}

	@Override
	public String toString() {
		return "SMusic [id=" + id + ", title=" + title + ", songname="
				+ songname + ", songid=" + songid + ", pic=" + pic + "]";
	}

}
