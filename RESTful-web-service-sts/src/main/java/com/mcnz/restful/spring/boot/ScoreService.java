package com.mcnz.restful.spring.boot;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreService {

	
	private static String pattern = "{ \"wins\":\"%s\", \"losses\":\"%s\", \"ties\": \"%s\", \"hostname\": \"%s\"}";
	
	private static String hostName = null;

	public static String getHostName() {
		try {
			if(hostName == null) {
				hostName = InetAddress.getLocalHost().getHostName();
			}
		} catch (UnknownHostException e) {
			return "Not Found";
		}
		return hostName;
	}

	//{ "wins":"5", "losses":"3", "ties": "0"}
	@RequestMapping(value="/score", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String getScore() {
		return String.format(pattern,  Score.WINS, Score.LOSSES, Score.TIES, getHostName());
	
	}
	
	@RequestMapping(value="/score", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public String update(int wins, int losses, int ties) {
		Score.WINS   = wins;
		Score.TIES   = ties;
		Score.LOSSES = losses;
//		String pattern = "{ \"wins\":\"%s\", \"losses\":\"%s\", \"ties\": \"%s\"}";
		return String.format(pattern,  Score.WINS, Score.LOSSES, Score.TIES, getHostName());	
	}
	

	@RequestMapping(value="/score/wins", method=RequestMethod.POST)
	public int increaseWins() {
		Score.WINS++;
		return Score.WINS;
	}
	@RequestMapping(value="/score/losses", method=RequestMethod.POST)
	public int increaseLosses() {
		Score.LOSSES++;
		return Score.LOSSES;
	}
	@RequestMapping(value="/score/ties", method=RequestMethod.POST)
	public int increaseTies() {
		Score.TIES ++;
		return Score.TIES ;
	}

	@RequestMapping(value="/score/wins", method=RequestMethod.GET)
	public int getWins() {
		return Score.WINS;
	}
	@RequestMapping(value="/score/losses", method=RequestMethod.GET)
	public int getLosses() {
		return Score.LOSSES;
	}
	@RequestMapping(value="/score/ties", method=RequestMethod.GET)
	public int getTies() {
		return Score.TIES ;
	}

}
