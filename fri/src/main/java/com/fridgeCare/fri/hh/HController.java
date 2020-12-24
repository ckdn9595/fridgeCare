package com.fridgeCare.fri.hh;


import java.io.File;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fridgeCare.fri.hh.util.Fileuploader;
import com.fridgeCare.fri.hh.util.Navermail;
import com.fridgeCare.fri.hh.util.Thumbnail;
import com.fridgeCare.fri.hh.vo.InputVO;
import com.fridgeCare.fri.hh.vo.LatelyUploadVO;
import com.fridgeCare.fri.hh.vo.MemberVO;
import com.fridgeCare.fri.hh.vo.SideRankVO;
import com.fridgeCare.fri.hh.vo.ThumbVO;
@Controller
@RequestMapping("/hh")
public class HController {
	int cnt;
	static Logger logger = LoggerFactory.getLogger(HController.class);
	@Autowired
	DAO hdao;
	@RequestMapping("/main.fri")
	public String getMain(HttpSession s) {
		String sid = (String) s.getAttribute("SID");
		LatelyUploadVO luvo = hdao.getLUVO();
		SideRankVO srvo = hdao.getWR();
		try {
			if(luvo.getTname() == null) {
				luvo.setTname("noimage.jpg");
			}
			if(luvo.getSavename() == null) {
				luvo.setSavename("noimage.jpg");
			}
			s.setAttribute("LUVO", luvo);
		}catch(NullPointerException e) {
//			e.printStackTrace();
			System.out.println("no any board or boardpart");
		}
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
	@RequestMapping("/pwfind.fri")
	public String pwfind() {
		
		return "hh/pwfind";
	}
	@RequestMapping("/myinfo.fri")
	public ModelAndView myinfo(ModelAndView mv , RedirectView rv , HttpSession s) {
		mv.setViewName("hh/myinfo");
		String sid = (String) s.getAttribute("SID");
		MemberVO mvo = hdao.getmvo(sid);
		mv.addObject("MVO", mvo);
		String tname = hdao.getThumb(sid);
		if(tname == null) {
			s.setAttribute("AVT", "noimage.jpg");
		}else {
			s.setAttribute("AVT", tname);
		}
		return mv;
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
	@RequestMapping("/ajaxtest.fri")
	@ResponseBody
	public String ajaxtest(String[] ajaxdata) {
		String view = "{\"result\" : \"NO\"}";
		System.out.println(ajaxdata[0]);
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
			logger.info("new member " + ivo.getInputid() + " has join");
		}
		if(!inputavt.getOriginalFilename().equals("")) {
			Fileuploader uploader = new Fileuploader(inputavt);
			f = uploader.upload();
			if(inputavt.getSize() > 6000) {
				Thumbnail thumb = new Thumbnail(f.getPath());
				f = thumb.make(100, 100);
			}
			f = uploader.export_avt(f, ivo.getInputid());
			if(f.exists()) {
				logger.info(ivo.getInputid() + "has make " + f.getName());
			}
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
	@RequestMapping("/infoedit.fri")
	public ModelAndView infoedit(ModelAndView mv , RedirectView rv , HttpSession s , MultipartFile inputavt , InputVO ivo) {
		rv.setUrl("/fri/hh/myinfo.fri?edit");
		String sid = (String) s.getAttribute("SID");
		ivo.setInputid(sid);
		File f;
		cnt = hdao.infoedit(ivo);
		if(cnt == 0) {
			System.out.println("edit fail");
			rv.setUrl("/fri/hh/myinfo.fri");
		}else {
			logger.info(sid + " has edit member info");
		}
		if(!inputavt.getOriginalFilename().equals("")) {
			Fileuploader uploader = new Fileuploader(inputavt);
			f = uploader.upload();
			if(inputavt.getSize() > 6000) {
				Thumbnail thumb = new Thumbnail(f.getPath());
				f = thumb.make(100, 100);
			}
			f = uploader.export_avt(f, sid);
			if(f.exists()) {
				logger.info(sid + " has make " + f.getName());
			}
			ThumbVO tvo = new ThumbVO();
			tvo.setDir(f.getPath());
			tvo.setTname(f.getName());
			tvo.setId(sid);
			cnt = hdao.editAvt(tvo);
			if(cnt == 0) {
				cnt = hdao.setAvt(tvo);
				if(cnt == 0) {
					System.out.println("edit avt fail");
				}
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
			cnt = hdao.submitCondate(ivo.getInputid());
			if(cnt == 0) {
				System.out.println("submit condate fail");
			}
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
	@RequestMapping("/mailtest.fri")
	@ResponseBody
	public String mailtest(String ajaxdata) {
		String view = "{\"result\" : \"NO\"}";
		System.out.println(ajaxdata);
		String hanhoon12 = "hanhoon12@naver.com";
		HSD hsd = new HSD();
		String naverpw = hanhoon12.substring(7) + "q" + hsd.data1 + Integer.toString(5+7);
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.naver.com");
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(hanhoon12, naverpw);
			}
		});
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(hanhoon12));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("hanpawu@gmail.com"));
			message.setSubject("test title");
			message.setText("test body");
			Transport.send(message);
			System.out.println("Let's check");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return view;
	}
	@RequestMapping("transtest.fri")
	@Transactional
	public ModelAndView transtest(ModelAndView mv , RedirectView rv , HttpSession s) {
		rv.setUrl("/fri/");
		mv.setView(rv);
		int cnt = hdao.transtest(5);
		cnt += hdao.transtest(4);
		cnt += hdao.transtest("a");
		cnt += hdao.transtest("3");
		System.out.println(cnt);
		return mv;
	}
	@RequestMapping(value="/sendpwfindmail.fri" , produces="application/json;charset=utf8")
	@ResponseBody
	public String sendPWfindmail(String id , String mail , HttpSession s) {
		String view = "{\"result\" : \"OK\"}";
		InputVO ivo = new InputVO();
		ivo.setInputid(id);
		ivo.setInputmail(mail);
		cnt = hdao.membercheck(ivo);
		if(cnt == 0) {
			view = "{\"result\" : \"그런회원은 없다\"}";
		}else {
			int AN = (int) (9999 - Math.random()*5000);
			cnt = hdao.newAN(AN);
			if(cnt == 0) {
				view = "{\"result\" : \"잠시후 다시 시도해라\"}";
				System.out.println("error occur");
			}else {
				s.setAttribute("changer", id);
				Navermail nm = new Navermail();
				nm.send(mail, AN);
			}
		}
		return view;
	}
	@RequestMapping("/pwfindproc.fri")
	@ResponseBody
	public String pwfindproc(String AN) {
		String view = "{\"result\" : \"OK\"}";
		cnt = hdao.pwfindproc(AN);
		if(cnt == 0) {
			view = "{\"result\" : \"NO\"}";
		}
		return view;
	}
	@RequestMapping("/pwchange.fri")
	public String pwchange(HttpSession s) {
		return "hh/pwchange";
	}
	@RequestMapping("/pwchangeproc.fri")
	public ModelAndView pwchangeproc(ModelAndView mv , RedirectView rv , HttpSession s , String inputpw) {
		rv.setUrl("/fri/hh/main.fri?pwchange");
		String changer = (String) s.getAttribute("changer");
		MemberVO mvo = new MemberVO();
		mvo.setId(changer);
		mvo.setPw(inputpw);
		cnt = hdao.pwchangeproc(mvo);
		if(cnt == 0) {
			rv.setUrl("/fri/hh/pwfind.fri?fail");
		}
		mv.setView(rv);
		return mv;
	}
}

class HSD{
	String data1 = "f";
	public HSD() {
		if(data1.equals("z")) {
			System.out.println("unknown print");
		}else {
			data1 = "w";
		}
	}
}