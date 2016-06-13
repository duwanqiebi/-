package com.zhangqiang.sqgl.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhangqiang.sqgl.domain.Bookmark;
import com.zhangqiang.sqgl.repository.BookmarkRepository;
import com.zhangqiang.sqgl.util.DateUtil;


@Service
public class BookmarkUploadService {
	
	@Autowired
	private BookmarkRepository repository;

	public void upload(MultipartFile[] files) {
		for(MultipartFile multiFile : files){
			//备份
			File file = backupBookMarks(multiFile);
			//解析
			List<Bookmark> list = parse(file);
			//更新
			for(Bookmark bookmark : list){
				repository.save(bookmark);
			}
		}
		
		
	}
	
	
	/**
	 * 备份文件
	 * */
	public  File backupBookMarks(MultipartFile file){
		File dir = new File("bak");
		if(!dir.isDirectory()){
			dir.mkdir();
		}
		File fileBak = new File(dir.getAbsolutePath() + "/"+ file.getOriginalFilename());
		try {
			file.transferTo(fileBak);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fileBak;
	}
	
	
	/**
	 * 解析书签html文件
	 * 
	 * */
	public  List<Bookmark> parse(File file) {
		Document doc =  null;
		List<Bookmark> bookmarks = new ArrayList<>();
		try {
			Date nowDate = DateUtil.getNowDate();  //当前日期
			String filename = file.getName();	   //文件名
			
			doc = Jsoup.parse(file, "UTF-8");
			Elements elements = doc.getElementsByTag("a");
			for(int i = 0 ; i < elements.size(); i ++){
				Element element = elements.get(i);
				//生成bookmark对象
				Bookmark bookmark = new Bookmark();
				bookmark.setName(element.text());
				bookmark.setURL(element.attr("HREF"));
				bookmark.setCreatedate(DateUtil.refFormatNowDate(Long.parseLong(element.attr("ADD_DATE") + "000")));
				bookmark.setClicksnum(0L);
				bookmark.setModifydate(nowDate);
				bookmark.setFilename(filename);
				bookmark.setIcon(element.attr("ICON"));
				bookmark.setClassid(1L);      		//默认为1,即未分类
				bookmark.setLastdate(nowDate);
				//将bookmark对象加入数组中
				bookmarks.add(bookmark);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookmarks;

	}

}
