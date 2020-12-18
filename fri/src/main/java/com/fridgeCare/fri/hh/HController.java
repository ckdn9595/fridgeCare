package com.fridgeCare.fri.hh;


import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fridgeCare.fri.hh.util.Fileuploader;
import com.fridgeCare.fri.hh.util.Thumbnail;
import com.fridgeCare.fri.hh.vo.InputVO;
import com.fridgeCare.fri.hh.vo.LatelyUploadVO;
import com.fridgeCare.fri.hh.vo.SideRankVO;
import com.fridgeCare.fri.hh.vo.ThumbVO;
@Controller
@RequestMapping("/hh")
public class HController {
	int cnt;
	@Autowired
	DAO hdao;
	@RequestMapping("/main.fri")
	public String getMain(HttpSession s) {
		String sid = (String) s.getAttribute("SID");
		LatelyUploadVO luvo = hdao.getLUVO();
		SideRankVO srvo = hdao.getWR();
		if(luvo.getTname() == null) {
			luvo.setTname("noimage.jpg");
		}
		if(luvo.getSavename() == null) {
			luvo.setSavename("noimage.jpg");
		}
		s.setAttribute("LUVO", luvo);
		s.setAttribute("WVO", srvo);
		srvo = hdao.getMR();
		s.setAttribute("MVO", srvo);
		if(sid != null) {
			String tname = hdao.getThumb(sid);
			if(tname == null) {
				s.setAttribute("AVT", "noimage.jpg");
			}else {
				s.setAttribute("AVT", tname);
			}
		}
		return "hh/main";
	}
	@RequestMapping("/joinpage.fri")
	public String joinpage() {
		
		return "hh/joinpage";
	}
	@RequestMapping("/idCheck.fri")
	@ResponseBody
	public String idcheck(String id) {
		String view = "{\"result\" : \"NO\"}";
		cnt = hdao.idcheck(id);
		if(cnt == 0) {
			view = "{\"result\" : \"OK\"}";
		}
		return view;
	}
	@RequestMapping("/mailcheck.fri")
	@ResponseBody
	public HashMap<String, String> mailcheck(String mail) {
		cnt = hdao.mailcheck(mail);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("result", (cnt == 0? "OK" : "NO"));
		return map;
	}
	@RequestMapping("/joinproc.fri")
	public ModelAndView joinproc(ModelAndView mv , RedirectView rv , HttpSession s , MultipartFile inputavt , InputVO ivo) {
		rv.setUrl("/fri/");
		File f;
		cnt = hdao.joinproc(ivo);
		if(cnt == 0) {
			System.out.println("join fail");
			rv.setUrl("/fri/hh/joinpage.fri");
		}else {
			s.setAttribute("SID", ivo.getInputid());
		}
		if(!inputavt.getOriginalFilename().equals("")) {
			Fileuploader uploader = new Fileuploader(inputavt);
			f = uploader.upload();
			if(inputavt.getSize() > 6000) {
				Thumbnail thumb = new Thumbnail(f.getPath());
				f = thumb.make(100, 100);
			}
			f = uploader.export_avt(f, ivo.getInputid());
			ThumbVO tvo = new ThumbVO();
			tvo.setDir(f.getPath());
			tvo.setTname(f.getName());
			tvo.setId(ivo.getInputid());
			cnt = hdao.setAvt(tvo);
			if(cnt == 0) {
				System.out.println("set avt fail");
			}
		}
		mv.setView(rv);
		return mv;
	}
	@RequestMapping("/logincheck.fri")
	public ModelAndView logincheck(ModelAndView mv , RedirectView rv , HttpSession s , InputVO ivo) {
		rv.setUrl("/fri/");
		cnt = hdao.logincheck(ivo);
		if(cnt == 0) {
			rv.setUrl("/fri/hh/main.fri?fail");
		}else {
			s.setAttribute("SID", ivo.getInputid());
		}
		mv.setView(rv);
		return mv;
	}
	@RequestMapping("/logout.fri")
	public ModelAndView logout(ModelAndView mv , RedirectView rv , HttpSession s) {
		rv.setUrl("/fri/");
		s.removeAttribute("SID");
		mv.setView(rv);
		return mv;
	}
	@RequestMapping("/noticelist.fri")
	public String getNoticeList(HttpSession s) {
		String sid = (String) s.getAttribute("SID");
		LatelyUploadVO luvo = hdao.getLUVO();
		SideRankVO srvo = hdao.getWR();
		if(luvo.getTname() == null) {
			luvo.setTname("noimage.jpg");
		}
		if(luvo.getSavename() == null) {
			luvo.setSavename("noimage.jpg");
		}
		s.setAttribute("LUVO", luvo);
		s.setAttribute("WVO", srvo);
		srvo = hdao.getMR();
		s.setAttribute("MVO", srvo);
		if(sid != null) {
			String tname = hdao.getThumb(sid);
			if(tname == null) {
				s.setAttribute("AVT", "noimage.jpg");
			}else {
				s.setAttribute("AVT", tname);
			}
		}
		return "hh/main";
	}
}
