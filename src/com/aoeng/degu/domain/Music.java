/**
 * 
 */
package com.aoeng.degu.domain;

import java.io.Serializable;

/**
 * @author sczhang 2015年1月30日 上午11:24:17
 */
public class Music implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6788884467538131686L;

	private String title;
	private String artist;
	private String id;
	private String path;
	private String duration;
	private long size;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Music [title=" + title + ", artist=" + artist + ", id=" + id + ", path=" + path + ", duration=" + duration + ", size=" + size + "]";
	}

}
