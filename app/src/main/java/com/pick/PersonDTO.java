package com.pick;

import java.util.List;

/**
 * Created by 10 on 2016-08-03.
 */
public class PersonDTO {

    /**
     * total : 10
     * data : [{"_id":"579222f81ba99cc65e63b282","type":1,"part":["g"],"v_url":"","userObject":[{"name":"김용철"}]},{"_id":"579223fd1ba99cc65e63b284","type":1,"part":["d"],"v_url":"","userObject":[{"name":"안태형"}]}]
     */

    private int total;
    /**
     * _id : 579222f81ba99cc65e63b282
     * type : 1
     * part : ["g"]
     * v_url :
     * userObject : [{"name":"김용철"}]
     */

    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String _id;
        private int type;
        private String v_url;
        private List<String> part;
        /**
         * name : 김용철
         */

        private List<UserObjectBean> userObject;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getV_url() {
            return v_url;
        }

        public void setV_url(String v_url) {
            this.v_url = v_url;
        }

        public List<String> getPart() {
            return part;
        }

        public void setPart(List<String> part) {
            this.part = part;
        }

        public List<UserObjectBean> getUserObject() {
            return userObject;
        }

        public void setUserObject(List<UserObjectBean> userObject) {
            this.userObject = userObject;
        }

        public static class UserObjectBean {
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
