package com.tarena.music.entity;

import java.io.Serializable;

public class SearMusic implements Serializable {
	private String bitrate_fee;// private String {\private String 0\;//\private
								// String 0|0\private String ,\private String
								// 1\;//\private String 0|0\private String
								// }private String ,
	private String weight;// private String 13050private String ,
	private String songname;// private String ∞¨¿Ô¿…∫Õ»¸ƒÀƒ∑private String ,
	private String songid;// private String 228635private String ,
	private String has_mv;// private String 0private String ,
	private String yyr_artist;// private String 0private String ,
	private String resource_type_ext;// private String 0private String ,
	private String artistname;// private String µ∂¿…private String ,
	private String info;// private String private String ,
	private String resource_provider;// private String 1private String ,
	private String control;// private String 0000000000private String ,
	private String encrypted_songid;// private String
									// 440537d1b0956236b01Lprivate String

	public String getBitrate_fee() {
		return bitrate_fee;
	}

	public void setBitrate_fee(String bitrate_fee) {
		this.bitrate_fee = bitrate_fee;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
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

	public String getHas_mv() {
		return has_mv;
	}

	public void setHas_mv(String has_mv) {
		this.has_mv = has_mv;
	}

	public String getYyr_artist() {
		return yyr_artist;
	}

	public void setYyr_artist(String yyr_artist) {
		this.yyr_artist = yyr_artist;
	}

	public String getResource_type_ext() {
		return resource_type_ext;
	}

	public void setResource_type_ext(String resource_type_ext) {
		this.resource_type_ext = resource_type_ext;
	}

	public String getArtistname() {
		return artistname;
	}

	public void setArtistname(String artistname) {
		this.artistname = artistname;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getResource_provider() {
		return resource_provider;
	}

	public void setResource_provider(String resource_provider) {
		this.resource_provider = resource_provider;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public String getEncrypted_songid() {
		return encrypted_songid;
	}

	public void setEncrypted_songid(String encrypted_songid) {
		this.encrypted_songid = encrypted_songid;
	}

	public SearMusic(String bitrate_fee, String weight, String songname,
			String songid, String has_mv, String yyr_artist,
			String resource_type_ext, String artistname, String info,
			String resource_provider, String control, String encrypted_songid) {
		super();
		this.bitrate_fee = bitrate_fee;
		this.weight = weight;
		this.songname = songname;
		this.songid = songid;
		this.has_mv = has_mv;
		this.yyr_artist = yyr_artist;
		this.resource_type_ext = resource_type_ext;
		this.artistname = artistname;
		this.info = info;
		this.resource_provider = resource_provider;
		this.control = control;
		this.encrypted_songid = encrypted_songid;
	}

	public SearMusic() {
		super();
	}

	@Override
	public String toString() {
		return "SearMusic [bitrate_fee=" + bitrate_fee + ", weight=" + weight
				+ ", songname=" + songname + ", songid=" + songid + ", has_mv="
				+ has_mv + ", yyr_artist=" + yyr_artist
				+ ", resource_type_ext=" + resource_type_ext + ", artistname="
				+ artistname + ", info=" + info + ", resource_provider="
				+ resource_provider + ", control=" + control
				+ ", encrypted_songid=" + encrypted_songid + "]";
	}

}
