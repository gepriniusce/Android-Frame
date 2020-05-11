package pr.tongson.template.pad.tab;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author by yuepingxu, Date on 2018/11/28.
 * 常用分类
 */
public class CommonType implements Parcelable {
    private int typeId;
    private String typeName;
    private int drawableId;
    private String url;

    private boolean isFromServer; // 是否来自云端分类

    public CommonType(int typeId, String typeName, int drawableId) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.drawableId = drawableId;
    }

    public CommonType(int typeId, String typeName, String url, boolean isFromServer) {
        this.typeId = typeId;
        this.typeName = typeName;

        this.url = url;
        this.isFromServer = isFromServer;
    }

    protected CommonType(Parcel in) {
        typeId = in.readInt();
        typeName = in.readString();
        drawableId = in.readInt();
        url = in.readString();
        isFromServer = in.readByte() != 0;
    }

    public String getUrl() {
        return url;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public boolean isFromServer() {
        return isFromServer;
    }

    public static final Creator<CommonType> CREATOR = new Creator<CommonType>() {
        @Override
        public CommonType createFromParcel(Parcel in) {
            return new CommonType(in);
        }

        @Override
        public CommonType[] newArray(int size) {
            return new CommonType[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(typeId);
        dest.writeString(typeName);
        dest.writeInt(drawableId);
        dest.writeString(url);
        dest.writeByte((byte) (isFromServer ? 1 : 0));
    }
}
