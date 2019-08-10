package com.ts.system.newsManager.dao;

import com.ts.entity.ResultInfo;
import com.ts.system.newsManager.UI.NewsInfo;
import com.ts.user.UI.UserInfo;
import org.springframework.web.multipart.MultipartFile;

/***
 * 新闻  接口
 *
 */
public interface NewsDao {
    /***
     * 新增
     *
     * @param newsInfo 新闻
     * @param b  图片
     * @return
     */
    public ResultInfo add(NewsInfo newsInfo, byte [] b);

    /***
     * 修改
     *
     * @param newsInfo  新闻
     * @param b 图片
     * @return
     */
    public ResultInfo edit(NewsInfo newsInfo, byte [] b);

    /***
     * 删除
     *
     * @param iID 新闻ID
     * @return
     */
    public ResultInfo delete(int iID);

    /***
     * 查询
     *
     * @return
     */
    public ResultInfo queryData();


    /***
     * 查看
     *
     * @param iID 新闻ID
     * @return
     */
    public ResultInfo get(int iID);



}
