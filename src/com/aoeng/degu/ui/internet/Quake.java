/**
 * 
 */
package com.aoeng.degu.ui.internet;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.location.Location;

/**
 * Jun 10, 2014 10:53:48 AM
 * 
 */
public class Quake {

	private Date date;
	private String details;
	private Location location;
	private double magnitude;
	private String link;

	public Date getDate() {
		return date;
	}

	public String getDetails() {
		return details;
	}

	public Location getLocation() {
		return location;
	}

	public double getMagnitude() {
		return magnitude;
	}

	public String getLink() {
		return link;
	}

	public Quake(Date _d, String _det, Location _loc, double _mag, String _link) {
		date = _d;
		details = _det;
		location = _loc;
		magnitude = _mag;
		link = _link;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
		String dateString = sdf.format(date);
		return dateString + ": " + magnitude + " " + details;
	}

}