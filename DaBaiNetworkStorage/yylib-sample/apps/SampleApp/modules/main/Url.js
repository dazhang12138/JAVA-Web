/**
 * Created by ICOP on 2017-10-24.
 */

var RestUrl = require('../../actions/RestUrl');

var ADDR = RestUrl.ADDR;
var ROOT_PATH = RestUrl.ROOT_PATH;
var SHOWPATH = "http://localhost:8081/";
var SHOWCODE = "apps/SampleApp/#";

var MainUrl = {
    LOGIN: ADDR + ROOT_PATH +  "/User/Login",      //登录
    VERIFY: ADDR + ROOT_PATH + "/User/UserVerify", //校验
    REGISTER: ADDR + ROOT_PATH + "/User/Register", //注册
    GETUSERFILES: ADDR + ROOT_PATH + "/Files/getFiles",//获取用户文件列表
    UPLOADFILE: ADDR + ROOT_PATH + "/Files/UploadFile", //上传文件
    NEWFOLDER: ADDR + ROOT_PATH + "/Files/newFolder",//新建文件夹
    DELETEFILES: ADDR + ROOT_PATH + "/Files/deleteFiles",//删除所选文件夹


    LOGINPATH: SHOWPATH + SHOWCODE + "/DB", //Login地址
    MAINPATH:SHOWPATH + SHOWCODE + "/DB/mainPage",//首页地址
}
module.exports = MainUrl;