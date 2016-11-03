package com.example.zren.wallpaperdemo3.domain;

import java.util.List;

/**
 * Created by 84168 on 2016/11/3.
 */
public class Recommend_Images {
    private String code;
    private Data data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonTest{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    public class Data {
        private int TotalCount;
        private List<WallpaperListInfo> WallpaperListInfo;

        public int getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(int totalCount) {
            TotalCount = totalCount;
        }

        public List<WallpaperListInfo> getWallpaperListInfo() {
            return WallpaperListInfo;
        }

        public void setWallpaperListInfo(List<WallpaperListInfo> WallpaperListInfo) {
            this.WallpaperListInfo = WallpaperListInfo;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "TotalCount=" + TotalCount +
                    ", WallpaperListInfo=" + WallpaperListInfo +
                    '}';
        }

        public class WallpaperListInfo{
            private int BigCategoryId;
            private String CreateTime;
            private int DownloadCount;
            private int GoodCount;
            private int ID;
            private String PicName;
            private int SecondCategoryId;
            private String UserName;
            private String WallPaperBig;
            private String WallPaperDownloadPath;
            private String WallPaperMiddle;
            private String WallPaperSource;
            private String passtime;
            private String pic_path;
            private String tags;
            private String weixin_url;

            public int getBigCategoryId() {
                return BigCategoryId;
            }

            public void setBigCategoryId(int bigCategoryId) {
                BigCategoryId = bigCategoryId;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String createTime) {
                CreateTime = createTime;
            }

            public int getDownloadCount() {
                return DownloadCount;
            }

            public void setDownloadCount(int downloadCount) {
                DownloadCount = downloadCount;
            }

            public int getGoodCount() {
                return GoodCount;
            }

            public void setGoodCount(int goodCount) {
                GoodCount = goodCount;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getPicName() {
                return PicName;
            }

            public void setPicName(String picName) {
                PicName = picName;
            }

            public int getSecondCategoryId() {
                return SecondCategoryId;
            }

            public void setSecondCategoryId(int secondCategoryId) {
                SecondCategoryId = secondCategoryId;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String userName) {
                UserName = userName;
            }

            public String getWallPaperBig() {
                return WallPaperBig;
            }

            public void setWallPaperBig(String wallPaperBig) {
                WallPaperBig = wallPaperBig;
            }

            public String getWallPaperDownloadPath() {
                return WallPaperDownloadPath;
            }

            public void setWallPaperDownloadPath(String wallPaperDownloadPath) {
                WallPaperDownloadPath = wallPaperDownloadPath;
            }

            public String getWallPaperMiddle() {
                return WallPaperMiddle;
            }

            public void setWallPaperMiddle(String wallPaperMiddle) {
                WallPaperMiddle = wallPaperMiddle;
            }

            public String getWallPaperSource() {
                return WallPaperSource;
            }

            public void setWallPaperSource(String wallPaperSource) {
                WallPaperSource = wallPaperSource;
            }

            public String getPasstime() {
                return passtime;
            }

            public void setPasstime(String passtime) {
                this.passtime = passtime;
            }

            public String getPic_path() {
                return pic_path;
            }

            public void setPic_path(String pic_path) {
                this.pic_path = pic_path;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public String getWeixin_url() {
                return weixin_url;
            }

            public void setWeixin_url(String weixin_url) {
                this.weixin_url = weixin_url;
            }

            @Override
            public String toString() {
                return "WallpaperListInfo{" +
                        "BigCategoryId=" + BigCategoryId +
                        ", CreateTime='" + CreateTime + '\'' +
                        ", DownloadCount=" + DownloadCount +
                        ", GoodCount=" + GoodCount +
                        ", ID=" + ID +
                        ", PicName='" + PicName + '\'' +
                        ", SecondCategoryId=" + SecondCategoryId +
                        ", UserName='" + UserName + '\'' +
                        ", WallPaperBig='" + WallPaperBig + '\'' +
                        ", WallPaperDownloadPath='" + WallPaperDownloadPath + '\'' +
                        ", WallPaperMiddle='" + WallPaperMiddle + '\'' +
                        ", WallPaperSource='" + WallPaperSource + '\'' +
                        ", passtime='" + passtime + '\'' +
                        ", pic_path='" + pic_path + '\'' +
                        ", tags='" + tags + '\'' +
                        ", weixin_url='" + weixin_url + '\'' +
                        '}';
            }
        }
    }

}
