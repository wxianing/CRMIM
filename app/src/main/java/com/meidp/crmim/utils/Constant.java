package com.meidp.crmim.utils;

import com.meidp.crmim.MyApplication;

/**
 * Created by John on 2016/6/29.
 */
public class Constant {
    public static final int RESULT_OK = 1;
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
    //默认头像
    public static final String HEADPHOTO = "http://www.qqbody.com/uploads/allimg/201411/18-200811_37.jpg";
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
    //获取我的成果
    public static final String GET_MY_ACHIEVEMENT = BASE_URL + "common/user/getmyachievement";
    //我的样机
    public static final String MY_MODEL_LIST = BASE_URL + "order/getsellsamplepagelist";
    //保存工作计划
    public static final String SAVA_WORK_PLAN = BASE_URL + "office/office/planaimsave";
    //过去个人计划列表
    public static final String GET_PERSONAL_PLAN = BASE_URL + "office/office/getplanaimpagelist";
    //保存反馈意见
    public static final String SAVE_FEEDBACK_URL = BASE_URL + "office/office/personaladvicesendsave";
    //我的专业知识
    public static final String MY_KNOWLEDGE_URL = BASE_URL + "office/office/getculturedocpagelist";
    //获取待审核样机列表
    public static final String FORCHECK_LIST = BASE_URL + "order/getsellsamplepagelistforcheck";
    //公海池
    public static final String OPEN_SEAR_PROJECT = BASE_URL + "projects/getpublicprojectpagelist";
    //未读消息
    public static final String UNREADER_MESSAGE_URL = BASE_URL + "common/mpublic/getalertlist";
    //标记已读消息
    public static final String SAVE_UNREADER = BASE_URL + "common/mpublic/readsave";
    //样机详情
    public static final String PROTOTYPE_DETAILS_URL = BASE_URL + "order/getsellsample";
    //流程保存审核
    public static final String CHECKFOR_SAVE_URL = BASE_URL + "common/mpublic/flowstepcheck";
    //公司制度
    public static final String REGULATORY_FRAMEWORK_URL = BASE_URL + "office/office/getculturedocpagelist";
    //资讯类分
    public static final String CLASSIFY_URL = BASE_URL + "office/office/getculturetypes";
    //获取个人信息
    public static final String GET_PERSON_INFORMATION = BASE_URL + "systemset/account/getuser";
    //获取系统消息
    public static final String GET_SYSTEM_MESSAGE = BASE_URL + "common/mpublic/getjpushpagelist";
    //获取联系人
    public static final String GET_CONTACTS_URL = BASE_URL + "common/user/getdeptusers";
    //费用详情
    public static final String FEELAPPLY_URL = BASE_URL + "office/office/getfeeapply";
    //获取我的建议列表
    public static final String GET_FEEDBACK_LIST_URL = BASE_URL + "office/office/getpersonaladvicesendpagelist";
    //保存我的团队
    public static final String SAVE_TEAM_LIST = BASE_URL + "office/employee/employeeteamsave";
    //我的团队列表
    public static final String MY_TEAM_LIST = BASE_URL + "office/employee/getemployeeteampagelist";
    //团队详情
    public static final String TEAM_DETAILS_URL = BASE_URL + "office/employee/getemployeeteam";
    //保存真会申请
    public static final String SAVE_CONVERNTIONAPPLYFOR = BASE_URL + "order/sellexhibitionsave";
    //商品中心
    public static final String PRODUCE_LIST = BASE_URL + "product/getproductpagelist";
    //展会申请
    public static final String CONVERSATION_LIST = BASE_URL + "order/getsellexhibitionpagelist";
    //重要事项详情
    public static final String IMPORTANT_DETAILS_URL = BASE_URL + "office/office/getpersonaldatearrange";
    //重要事项列表
    public static final String IMPORTANT_LIST_URL = BASE_URL + "office/office/getpersonaldatearrangepagelist";
    //保存重要事项
    public static final String ADD_IMPORTANT_URL = BASE_URL + "office/office/personaldatearrangesave";
    //保存人生导航
    public static final String SAVE_NAV_URL = BASE_URL + "office/employee/saveemployeenav";
    //获取我的人生导航
    public static final String GET_LIFE_NAV = BASE_URL + "office/employee/getemployeenav";
    //保存客户联系人
    public static final String CUSTOMER_CONTACTS_URL = BASE_URL + "crm/getminelinkmanpagelist";

    public static final String CUSTOMER_CONTACTS_DETAILS = BASE_URL + "crm/getcustinfo";
    //展会详情
    public static final String CONVERSATION_DETAILS_URL = BASE_URL + "order/getsellexhibition";
    //人生导航
    public static final String LIFE_NAV_URL = "http://beacon.meidp.com:98/Mobi/Office/EmployeeNav?UserId=2";
    //获取费用审批列表
    public static final String GET_COST_CHECK = BASE_URL + "office/office/getfeeapplypagelistforcheck";
    //业绩统计报表
    public static final String PERFORMANCE_URL = BASE_URL + "common/mpublic/getachievementtotal";
    //项目领域
    public static final String PROJECT_DIRECTION_URL = BASE_URL + "office/office/getcodepublictypelist";
    //保存客户联系人
    public static final String SAVE_CUSTOMER_CONTACT_URL = BASE_URL + "crm/custlinkmansave";
    //申请备货
    public static final String STOCK_UP_URL = BASE_URL + "order/sellordersave";
    //版本升级
    public static final String VERSION_UPDATE = BASE_URL + "systemset/getlatestversoin";
    //获取群成员
    public static final String GROUP_MEMBER_URL = BASE_URL + "common/rongcloud/getdiscussion";
    //客户详情
    public static final String LINKMAN_DETAILS_URL = BASE_URL + "crm/getlinkman";
    //审批未读数
    public static final String GETNOCHECKTOTAL_URL = BASE_URL + "common/mpublic/getnochecktotal";
    //极光推送未读消息
    public static final String JPUSH_NORESDER_URL = BASE_URL + "common/mpublic/getnoreadtotal";
    //备货列表
    public static final String STOCK_LIST_URL = BASE_URL + "order/getsellorderpagelistforcheck";
    //备货详情
    public static final String GET_STOCKUP_DETAILS = BASE_URL + "order/getsellorder";
    //标记极光推送未已读消息
    public static final String JPUSH_HASREADER_SAVE = BASE_URL + "common/mpublic/jpushreadsave";
    //添加团队联系人
    public static final String SAVE_TEAMLINKMAN_URL = BASE_URL + "office/employee/personallinkmansave";
    //团队联系人列表
    public static final String TEAM_LINKMAN_URL = BASE_URL + "office/employee/getpersonallinkmanpagelist";
    //修改密码
    public static final String RESET_PWD_URL = BASE_URL + "systemset/account/updatepassword";
}
