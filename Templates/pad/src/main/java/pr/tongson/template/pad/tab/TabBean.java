package pr.tongson.template.pad.tab;

/**
 * @Email:289286298@qq.com
 * @Author v_luzhanneng
 * @Date 2020/5/6
 * @Version
 * @Since
 * @Description
 */
public class TabBean {
    private String name;
    private CommonType commonType;
    private boolean checked;

    public TabBean(String name, boolean checked) {
        this.name = name;
        this.checked = checked;
    }

    public TabBean(CommonType commonType, boolean checked) {
        this.commonType = commonType;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommonType getCommonType() {
        return commonType;
    }

    public void setCommonType(CommonType commonType) {
        this.commonType = commonType;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
