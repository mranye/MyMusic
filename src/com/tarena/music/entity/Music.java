package com.tarena.music.entity;

import java.io.Serializable;

/**
 * 封装本地音乐数据 歌曲名 演唱者 歌曲地址
 */
public class Music implements Serializable {
	private String song;
	private String singer;
	private String path;

	public Music(String song, String singer, String path) {
		super();
		this.song = song;
		this.singer = singer;
		this.path = path;
	}

	public Music() {
		super();
	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Music [song=" + song + ", singer=" + singer + ", path=" + path
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((singer == null) ? 0 : singer.hashCode());
		result = prime * result + ((song == null) ? 0 : song.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Music other = (Music) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (singer == null) {
			if (other.singer != null)
				return false;
		} else if (!singer.equals(other.singer))
			return false;
		if (song == null) {
			if (other.song != null)
				return false;
		} else if (!song.equals(other.song))
			return false;
		return true;
	}

}
