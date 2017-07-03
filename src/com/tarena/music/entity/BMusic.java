package com.tarena.music.entity;

import java.io.Serializable;
/**
 * 百度音乐实体类 
 *
 */
public class BMusic implements Serializable {

	private String artist_id;// private String 1814private String ,
    private String language;// private String 国语private String ,
    private String pic_big;// private String http://musicdata.baidu.com/data2/pic/7cc403f4620d1b1c42dc1ab770a41a6b/541680638/541680638.jpg@s_0,w_150private String ,
    private String pic_small;// private String http://musicdata.baidu.com/data2/pic/7cc403f4620d1b1c42dc1ab770a41a6b/541680638/541680638.jpg@s_0,w_90private String ,
    private String country;// private String 内地private String ,
    private String area;// private String 0private String ,
    private String publishtime;// private String 2017-05-30private String ,
    private String album_no;// private String 1private String ,
    private String lrclink;// private String http://musicdata.baidu.com/data2/lrc/cd580f83cac336a9bd812351b47ed514/541680696/541680696.lrcprivate String ,
    private String copy_type;// private String 1private String ,
    private String hot;// private String 156757private String ,
    private String all_artist_ting_uid;// private String 7898private String ,
    private String resource_type;// private String 0private String ,
    private String is_new;// private String 1private String ,
    private String rank_change;// private String 0private String ,
    private String rank;// private String 1private String ,
    private String all_artist_id;// private String 1814private String ,
    private String style;// private String private String ,
    private String del_status;// private String 0private String ,
    private String relate_status;// private String 0private String ,
    private String toneid;// private String 0private String ,
    private String all_rate;// private String flac,320,256,128,64private String ,
    private String file_duration;// 220,
    private String has_mv_mobile;// 1,
    private String versions;// private String private String ,
    private String bitrate_fee;// private String {\private String 0\;//\private String 0|0\private String ,\private String 1\;//\private String 0|0\private String }private String ,
    private String biaoshi;// private String losslessprivate String ,
    private String song_id;// private String 541680641private String ,
    private String title;// private String 桃花诺 private String ,
    private String ting_uid;// private String 7898private String ,
    private String author;// private String G.E.M.邓紫棋private String ,
    private String album_id;// private String 541680639private String ,
    private String album_title;// private String 桃花诺private String ,
    private String is_first_publish;// 0,
    private String havehigh;// 2,
    private String charge;// 0,
    private String has_mv;// 1,
    private String learn;// 0,
    private String song_source;// private String webprivate String ,
    private String piao_id;// private String 0private String ,
    private String korean_bb_song;// private String 0private String ,
    private String resource_type_ext;// private String 0private String ,
    private String mv_provider;// private String 0000000000private String ,
    private String artist_name;// private String G.E.M.邓紫棋private String 
    
    @Override
	public String toString() {
		return "BMusic [artist_id=" + artist_id + ", language=" + language
				+ ", pic_big=" + pic_big + ", pic_small=" + pic_small
				+ ", country=" + country + ", area=" + area + ", publishtime="
				+ publishtime + ", album_no=" + album_no + ", lrclink="
				+ lrclink + ", copy_type=" + copy_type + ", hot=" + hot
				+ ", all_artist_ting_uid=" + all_artist_ting_uid
				+ ", resource_type=" + resource_type + ", is_new=" + is_new
				+ ", rank_change=" + rank_change + ", rank=" + rank
				+ ", all_artist_id=" + all_artist_id + ", style=" + style
				+ ", del_status=" + del_status + ", relate_status="
				+ relate_status + ", toneid=" + toneid + ", all_rate="
				+ all_rate + ", file_duration=" + file_duration
				+ ", has_mv_mobile=" + has_mv_mobile + ", versions=" + versions
				+ ", bitrate_fee=" + bitrate_fee + ", biaoshi=" + biaoshi
				+ ", song_id=" + song_id + ", title=" + title + ", ting_uid="
				+ ting_uid + ", author=" + author + ", album_id=" + album_id
				+ ", album_title=" + album_title + ", is_first_publish="
				+ is_first_publish + ", havehigh=" + havehigh + ", charge="
				+ charge + ", has_mv=" + has_mv + ", learn=" + learn
				+ ", song_source=" + song_source + ", piao_id=" + piao_id
				+ ", korean_bb_song=" + korean_bb_song + ", resource_type_ext="
				+ resource_type_ext + ", mv_provider=" + mv_provider
				+ ", artist_name=" + artist_name + "]";
	}
	public BMusic() {
		super();
	}
	public BMusic(String artist_id, String language, String pic_big,
			String pic_small, String country, String area, String publishtime,
			String album_no, String lrclink, String copy_type, String hot,
			String all_artist_ting_uid, String resource_type, String is_new,
			String rank_change, String rank, String all_artist_id,
			String style, String del_status, String relate_status,
			String toneid, String all_rate, String file_duration,
			String has_mv_mobile, String versions, String bitrate_fee,
			String biaoshi, String song_id, String title, String ting_uid,
			String author, String album_id, String album_title,
			String is_first_publish, String havehigh, String charge,
			String has_mv, String learn, String song_source, String piao_id,
			String korean_bb_song, String resource_type_ext,
			String mv_provider, String artist_name) {
		super();
		this.artist_id = artist_id;
		this.language = language;
		this.pic_big = pic_big;
		this.pic_small = pic_small;
		this.country = country;
		this.area = area;
		this.publishtime = publishtime;
		this.album_no = album_no;
		this.lrclink = lrclink;
		this.copy_type = copy_type;
		this.hot = hot;
		this.all_artist_ting_uid = all_artist_ting_uid;
		this.resource_type = resource_type;
		this.is_new = is_new;
		this.rank_change = rank_change;
		this.rank = rank;
		this.all_artist_id = all_artist_id;
		this.style = style;
		this.del_status = del_status;
		this.relate_status = relate_status;
		this.toneid = toneid;
		this.all_rate = all_rate;
		this.file_duration = file_duration;
		this.has_mv_mobile = has_mv_mobile;
		this.versions = versions;
		this.bitrate_fee = bitrate_fee;
		this.biaoshi = biaoshi;
		this.song_id = song_id;
		this.title = title;
		this.ting_uid = ting_uid;
		this.author = author;
		this.album_id = album_id;
		this.album_title = album_title;
		this.is_first_publish = is_first_publish;
		this.havehigh = havehigh;
		this.charge = charge;
		this.has_mv = has_mv;
		this.learn = learn;
		this.song_source = song_source;
		this.piao_id = piao_id;
		this.korean_bb_song = korean_bb_song;
		this.resource_type_ext = resource_type_ext;
		this.mv_provider = mv_provider;
		this.artist_name = artist_name;
	}
	public String getArtist_id() {
		return artist_id;
	}
	public void setArtist_id(String artist_id) {
		this.artist_id = artist_id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPic_big() {
		return pic_big;
	}
	public void setPic_big(String pic_big) {
		this.pic_big = pic_big;
	}
	public String getPic_small() {
		return pic_small;
	}
	public void setPic_small(String pic_small) {
		this.pic_small = pic_small;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
	public String getAlbum_no() {
		return album_no;
	}
	public void setAlbum_no(String album_no) {
		this.album_no = album_no;
	}
	public String getLrclink() {
		return lrclink;
	}
	public void setLrclink(String lrclink) {
		this.lrclink = lrclink;
	}
	public String getCopy_type() {
		return copy_type;
	}
	public void setCopy_type(String copy_type) {
		this.copy_type = copy_type;
	}
	public String getHot() {
		return hot;
	}
	public void setHot(String hot) {
		this.hot = hot;
	}
	public String getAll_artist_ting_uid() {
		return all_artist_ting_uid;
	}
	public void setAll_artist_ting_uid(String all_artist_ting_uid) {
		this.all_artist_ting_uid = all_artist_ting_uid;
	}
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public String getIs_new() {
		return is_new;
	}
	public void setIs_new(String is_new) {
		this.is_new = is_new;
	}
	public String getRank_change() {
		return rank_change;
	}
	public void setRank_change(String rank_change) {
		this.rank_change = rank_change;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getAll_artist_id() {
		return all_artist_id;
	}
	public void setAll_artist_id(String all_artist_id) {
		this.all_artist_id = all_artist_id;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getDel_status() {
		return del_status;
	}
	public void setDel_status(String del_status) {
		this.del_status = del_status;
	}
	public String getRelate_status() {
		return relate_status;
	}
	public void setRelate_status(String relate_status) {
		this.relate_status = relate_status;
	}
	public String getToneid() {
		return toneid;
	}
	public void setToneid(String toneid) {
		this.toneid = toneid;
	}
	public String getAll_rate() {
		return all_rate;
	}
	public void setAll_rate(String all_rate) {
		this.all_rate = all_rate;
	}
	public String getFile_duration() {
		return file_duration;
	}
	public void setFile_duration(String file_duration) {
		this.file_duration = file_duration;
	}
	public String getHas_mv_mobile() {
		return has_mv_mobile;
	}
	public void setHas_mv_mobile(String has_mv_mobile) {
		this.has_mv_mobile = has_mv_mobile;
	}
	public String getVersions() {
		return versions;
	}
	public void setVersions(String versions) {
		this.versions = versions;
	}
	public String getBitrate_fee() {
		return bitrate_fee;
	}
	public void setBitrate_fee(String bitrate_fee) {
		this.bitrate_fee = bitrate_fee;
	}
	public String getBiaoshi() {
		return biaoshi;
	}
	public void setBiaoshi(String biaoshi) {
		this.biaoshi = biaoshi;
	}
	public String getSong_id() {
		return song_id;
	}
	public void setSong_id(String song_id) {
		this.song_id = song_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTing_uid() {
		return ting_uid;
	}
	public void setTing_uid(String ting_uid) {
		this.ting_uid = ting_uid;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(String album_id) {
		this.album_id = album_id;
	}
	public String getAlbum_title() {
		return album_title;
	}
	public void setAlbum_title(String album_title) {
		this.album_title = album_title;
	}
	public String getIs_first_publish() {
		return is_first_publish;
	}
	public void setIs_first_publish(String is_first_publish) {
		this.is_first_publish = is_first_publish;
	}
	public String getHavehigh() {
		return havehigh;
	}
	public void setHavehigh(String havehigh) {
		this.havehigh = havehigh;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getHas_mv() {
		return has_mv;
	}
	public void setHas_mv(String has_mv) {
		this.has_mv = has_mv;
	}
	public String getLearn() {
		return learn;
	}
	public void setLearn(String learn) {
		this.learn = learn;
	}
	public String getSong_source() {
		return song_source;
	}
	public void setSong_source(String song_source) {
		this.song_source = song_source;
	}
	public String getPiao_id() {
		return piao_id;
	}
	public void setPiao_id(String piao_id) {
		this.piao_id = piao_id;
	}
	public String getKorean_bb_song() {
		return korean_bb_song;
	}
	public void setKorean_bb_song(String korean_bb_song) {
		this.korean_bb_song = korean_bb_song;
	}
	public String getResource_type_ext() {
		return resource_type_ext;
	}
	public void setResource_type_ext(String resource_type_ext) {
		this.resource_type_ext = resource_type_ext;
	}
	public String getMv_provider() {
		return mv_provider;
	}
	public void setMv_provider(String mv_provider) {
		this.mv_provider = mv_provider;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	
}
