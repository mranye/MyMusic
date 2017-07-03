package com.tarena.music.entity;

import java.io.Serializable;

public class Album implements Serializable {

	private String albumname;// private String µ∂¿… ¢Ûprivate String ,
	private String weight;// private String 225310private String ,
	private String artistname;// private String µ∂¿…private String ,
	private String resource_type_ext;// private String 0private String ,
	private String artistpic;// private String
								// http://qukufile2.qianqian.com/data2/pic/89841994/89841994.jpg@s_0,w_40private
								// String ,
	private String albumid;// private String 7311711private String

	@Override
	public String toString() {
		return "Album [albumname=" + albumname + ", weight=" + weight
				+ ", artistname=" + artistname + ", resource_type_ext="
				+ resource_type_ext + ", artistpic=" + artistpic + ", albumid="
				+ albumid + "]";
	}

	public Album() {
		super();
	}

	public Album(String albumname, String weight, String artistname,
			String resource_type_ext, String artistpic, String albumid) {
		super();
		this.albumname = albumname;
		this.weight = weight;
		this.artistname = artistname;
		this.resource_type_ext = resource_type_ext;
		this.artistpic = artistpic;
		this.albumid = albumid;
	}

	public String getAlbumname() {
		return albumname;
	}

	public void setAlbumname(String albumname) {
		this.albumname = albumname;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getArtistname() {
		return artistname;
	}

	public void setArtistname(String artistname) {
		this.artistname = artistname;
	}

	public String getResource_type_ext() {
		return resource_type_ext;
	}

	public void setResource_type_ext(String resource_type_ext) {
		this.resource_type_ext = resource_type_ext;
	}

	public String getArtistpic() {
		return artistpic;
	}

	public void setArtistpic(String artistpic) {
		this.artistpic = artistpic;
	}

	public String getAlbumid() {
		return albumid;
	}

	public void setAlbumid(String albumid) {
		this.albumid = albumid;
	}
}
