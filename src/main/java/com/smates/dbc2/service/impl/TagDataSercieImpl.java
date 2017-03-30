package com.smates.dbc2.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smates.dbc2.mapper.TagDataDao;
import com.smates.dbc2.po.DateAndTagnum;
import com.smates.dbc2.po.PieData;
import com.smates.dbc2.po.TagLocationData;
import com.smates.dbc2.po.TrailData;
import com.smates.dbc2.service.TagDataService;
import com.smates.dbc2.utils.ClearListSameData;

@Service
public class TagDataSercieImpl implements TagDataService {

	@Autowired
	private TagDataDao tagDataDao;

	@Override
	public List<TagLocationData> getAllLocation() {
		List<TagLocationData> locationDatas = tagDataDao.getAllLocation();
		for (int i = 0; i < locationDatas.size(); i++) {
			try {
				if (!locationDatas.get(i).getEnd().equals("未离开")) {
					locationDatas.get(i).setRoomName("不在范围内");// 如果end有数据，说明该标签已经从该房间离开
				}

			} catch (NullPointerException e) {
				locationDatas.get(i).setRoomName("新用户，暂无数据");
			}
		}
		return locationDatas;
	}

	@Override
	public List<PieData> getPieData(DateAndTagnum dateAndTagnum) {
		int cnt = 0;
		List<PieData> pieDatas = tagDataDao.getPieData(dateAndTagnum);
		for (int i = 0; i < pieDatas.size(); i++) {
			cnt += pieDatas.get(i).getCount();
		}
		pieDatas.add(new PieData(0, "0", "其他", 1440 - cnt));// 加一条不在范围的数据，方便前台插入数据
		return pieDatas;
	}

	@Override
	public List<TrailData> getTrailByDateAndTagnum(String date, String tagNum) {
		List<TrailData> trailDataList = tagDataDao.getTrailByDateAndTagnum(new DateAndTagnum(date, tagNum));
		// 为每条数据添加颜色数据用于画图，相同房间是相同的颜色
		String[] color = new String[] { "#C0FF8C", "#FFF78C", "#FFD08C", "#8CEAFF", "#FF8C9D", "#D9508A",
				"#FE9507", "#FEF778", "#6AA786", "#35C2D1", "#C12552", "#FF6600", "#F5C700", "#6A961F", "#B36435",
				"#CFF8F6", "#94D4D4", "#88B4BB", "#76AEAF", "#2A6D82", "#405980", "#95A57C", "#D9B8A2", "#BF8686",
				"#B33050" };// 建立颜色数据表
		List<String> roomNumList = new ArrayList<String>();
		List<String> roomNumListUniq = new ArrayList<String>();
		for (TrailData t : trailDataList) {
			roomNumList.add(t.getRoomNum());
			roomNumListUniq.add(t.getRoomNum());
		}
		roomNumListUniq = ClearListSameData.removeDuplicate(roomNumListUniq);
		for (int i = 0; i < roomNumListUniq.size(); i++) {
			for (int j = 0; j < roomNumList.size(); j++) {
				if (roomNumListUniq.get(i).equals(roomNumList.get(j))) {
					trailDataList.get(j).setColor(color[i]);
				}
			}
		}
		return trailDataList;
	}

}
