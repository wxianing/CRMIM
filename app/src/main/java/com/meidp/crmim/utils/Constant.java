package com.meidp.crmim.utils;

import com.meidp.crmim.MyApplication;

/**
 * Created by John on 2016/6/29.
 */
public class Constant {

    public static final String BASE_URL = "http://115.28.180.26:98/";

    public static final String APPID = "102";
    //    public static final String CODE = SharedPreferencesUtils.getUser(MyApplication.getmInstance()).getCode();
    public static final String CODE = (String) SPUtils.get(MyApplication.getmInstance(), "CODE", "");

    public static final String LOGIN_URL = BASE_URL + "systemset/account/login";

    //第三方登录类型
    public static final int QQ = 1;
    public static final int WECHAT = 2;
    public static final int FILE_SELECT_CODE = 1001;
    public static final String ACTION_NEW_VERSION = "com.meten.ifuture.ACTION_NEW_VERSION";
    public static final String VERSION_NAME = "versionName";

    //广告轮播图
    public static final String BANNER_URL = BASE_URL + "systemset/getadvertiselist";//
    //资讯列表
    public static final String INFORMATIONS_URL = BASE_URL + "article/article/getarticlelist";
    //产品列表
    public static final String PRODUCE_LIST_URL = BASE_URL + "product/home/getproductlist";
    //获取最新版本
    public static final String GET_NEW_VERSION = BASE_URL + "systemset/getlatestversoin";
    //10010账号token
//    public static final String TOKEN = "velOrsGsBqFcnNd6sG8PeZWWa9eXtvB/MYH0YpK1Y2AS9IvuDT8GcFYCKKfGIo5xNNNlQ5nV78+DZrYBJbS6VA==";

    public static final String TOKEN = (String) SPUtils.get(MyApplication.getmInstance(), "TOKEN", "");

    public static final int RESULTCODE = 1;
    //公告
    public static final String ANNOUCEMENT_URL = BASE_URL + "systemset/getnoticepagelist";
    //我的项目、公海池
    public static final String PROJECT_MANAGER = BASE_URL + "projects/getprojectpagelist";
    //修改个人资料
    public static final String UPDATE_MESSAGE = BASE_URL + "systemset/account/update";
    //上传头像
    public static final String UPDATE_HEADER = BASE_URL + "systemset/account/uploadphoto";
    //获取我的客户和分配给的客户列表
    public static final String CUSTOMER_LIST_URL = BASE_URL + "crm/getminecustpagelist";
    //获取某客户所有联系人列表
    public static final String CUSTOMER_CONTACT_URL = BASE_URL + "crm/getcustlinkmanlist";
    //保存客户拜访
    public static final String SAVE_VISIT_CUSTOMER = BASE_URL + "crm/custcontact/custcontactsave";
    //历史拜访记录
    public static final String VISIT_RECORD_URL = BASE_URL + "crm/custcontact/getcustcontactpagelist";
    //保存项目
    public static final String SAVE_PROJECT = BASE_URL + "projects/projectsave";
    //好友列表
    public static final String FRIEND_LIST_URL = BASE_URL + "common/user/getrongclouduserpagelist";
    //获取群组列表
    public static final String GROUP_LIST_URL = BASE_URL + "common/rongcloud/getminediscussionlist";
    //创建群组
    public static final String CREATE_GROUP_URL = BASE_URL + "common/rongcloud/creatediscussion";
    //聊天记录
    public static final String CHATTING_RECODE_URL = BASE_URL + "common/rongcloud/getusertalkpagelist";
    //添加新客户
    public static final String NEW_CUSTOMEER_URL = BASE_URL + "crm/custinfosave";
    //保存客户联系人
    public static final String SAVE_CUSTOMER_CONTACT = BASE_URL + "crm/custlinkmansave";
    //获取单个项目详情
    public static final String GET_PROJECT_DETAILS = BASE_URL + "projects/getproject";
    //将我的项目放入公海池
    public static final String PUT_TO_OPENPOOL = BASE_URL + "projects/projecttranspub";
    //接公海池项目
    public static final String FOLLOW_OPENPOOL_PROJECT = BASE_URL + "projects/projectpubtransmine";
    //产品管理
    public static final String MODEL_APPLY_URL = BASE_URL + "project/getproductpagelist";
    //获取公共分类
    public static final String GET_PUBLIC_TYPE = BASE_URL + "office/office/getcodepublictypelist";
    //费用类别
    public static final String COST_TYPE_DETAIL = BASE_URL + "office/office/getcodefeetypelist";
    //费用申请保存
    public static final String COST_SAVE_URL = BASE_URL + "office/office/feeapplysave";
    //获取我的费用列表
    public static final String GET_MY_COSTLIST_URL = BASE_URL + "office/office/getfeeapplypagelist";
    //产品列表
    public static final String PROTOTYPE_LIST_URL = BASE_URL + "product/getproductpagelist";
    //保存样机申请
    public static final String PROTOTYPE_SAVE_URL = BASE_URL + "order/sellsamplesave";
    //保存进度
    public static final String SAVE_PROJECT_PROGRESS = BASE_URL + "projects/projectconstructiondetailssave";

}