/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.mwsproject.model;

import java.io.Serializable;
import java.util.Date;

public class Fact implements Serializable {

	private String name;

	private String text;

	private Date timestamp;

	public Fact() {
		this.timestamp = new Date();
	}

	public Fact(String name, String text) {
		this.name = name;
		this.text = text;
		this.timestamp = new Date();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
