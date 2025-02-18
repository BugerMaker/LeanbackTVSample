package com.bugermaker.tvapplication.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Content implements Parcelable {

    private int code;
    private int total_count;
    private List<DataBean> data;

    protected Content(Parcel in) {
        code = in.readInt();
        total_count = in.readInt();
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel in) {
            return new Content(in);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeInt(total_count);
    }

    public static class DataBean implements Parcelable{
        private int id;
        private String title;
        private String showTitle;
        private int contentCode;
        private List<WidgetsBean> widgets;

        protected DataBean(Parcel in) {
            id = in.readInt();
            title = in.readString();
            showTitle = in.readString();
            contentCode = in.readInt();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShowTitle() {
            return showTitle;
        }

        public void setShowTitle(String showTitle) {
            this.showTitle = showTitle;
        }

        public int getContentCode() {
            return contentCode;
        }

        public void setContentCode(int contentCode) {
            this.contentCode = contentCode;
        }

        public List<WidgetsBean> getWidgets() {
            return widgets;
        }

        public void setWidgets(List<WidgetsBean> widgets) {
            this.widgets = widgets;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(title);
            dest.writeString(showTitle);
            dest.writeInt(contentCode);
        }

        public static class WidgetsBean implements Parcelable{
            private int id;
            private String contentCode;
            private String name;
            private String desc;
            private String url;

            protected WidgetsBean(Parcel in) {
                id = in.readInt();
                contentCode = in.readString();
                name = in.readString();
                desc = in.readString();
                url = in.readString();
            }

            public static final Creator<WidgetsBean> CREATOR = new Creator<WidgetsBean>() {
                @Override
                public WidgetsBean createFromParcel(Parcel in) {
                    return new WidgetsBean(in);
                }

                @Override
                public WidgetsBean[] newArray(int size) {
                    return new WidgetsBean[size];
                }
            };

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContentCode() {
                return contentCode;
            }

            public void setContentCode(String contentCode) {
                this.contentCode = contentCode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeString(contentCode);
                dest.writeString(name);
                dest.writeString(desc);
                dest.writeString(url);
            }
        }
    }
}
